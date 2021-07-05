<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 商品列表
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <el-table
                :data="tableData"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                @selection-change="handleSelectionChange"
            >
                <el-table-column prop="goods_id" label="ID" width="55" align="center"></el-table-column>
                <el-table-column prop="goods_name" label="商品名称"></el-table-column>
                <el-table-column prop="category_id" label="商品类别"></el-table-column>
                <el-table-column prop="sales_actual" label="实际销量"></el-table-column>
                <el-table-column prop="counter_price" label="价格"></el-table-column>
                <el-table-column prop="is_new" label="新品"></el-table-column>
                <el-table-column prop="stock_num" label="库存"></el-table-column>

                <el-table-column label="商品图" align="center">
                    <template slot-scope="scope">
                        <el-image
                            class="table-td-thumb"
                            :src="scope.row.goods_img_url"
                            :preview-src-list="[scope.row.goods_img_url]"
                        ></el-image>
                    </template>
                </el-table-column>

                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                        <el-button
                            type="text"
                            class="red"
                            @click="handleDelete(scope.$index, scope.row)"
                        >下架</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
                    background
                    layout="total, prev, pager, next"
                    :current-page="query.pageIndex"
                    :page-size="query.pageSize"
                    :total="pageTotal"
                    @current-change="handlePageChange"
                ></el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="商品名称">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="商品价格">
                    <el-input v-model="form.price"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import { fetchData } from '../../api/index';
export default {
    name: 'basetable',
    data() {
        return {
            query: {
                price: '',
                name: '',
                pageIndex: 1,
                pageSize: 10
            },
            tableData: [],
            multipleSelection: [],
            delList: [],
            editVisible: false,
            pageTotal: 0,
            form: {},
            idx: -1,
            id: -1
        };
    },
    created() {
        this.getData();
    },
    methods: {
        // 获取 easy-mock 的模拟数据
        getData() {
            fetchData(this.query).then(res => {
                let _this = this;
                // console.log(this.query.pageIndex);
                this.$axios
                    .get('http://localhost:8080/adminGoods/getOnepage', {
                        params: {
                            pageNum: this.query.pageIndex
                        }
                    })
                    .then(function(response) {
                        console.log(response.data.data);
                        if (response.data.code === 200) {
                            _this.tableData = response.data.data.list;
                            _this.pageTotal = response.data.data.total;
                        } else {
                            _this.$message.error('网络超时');
                            return false;
                        }
                    })
                    .catch(function(error) {
                        console.log(error);
                        alert('等待超时');
                    });

                // console.log(res);
                // this.tableData = res.list;
                // this.pageTotal = res.pageTotal || 50;
            });
        },
        // 触发搜索按钮
        handleSearch() {
            this.$set(this.query, 'pageIndex', 1);
            this.getData();
        },
        // 删除操作
        handleDelete(index, row) {
            // 二次确认删除
            // console.log(index);
            // console.log(row.goods_id);
            let _this = this;
            this.$confirm('确定要下架吗？', '提示', {
                type: 'warning'
            })
                .then(() => {
                    this.$axios
                        .post('http://localhost:8080/adminGoods/DeleteGoods', {
                            goods_id: row.goods_id
                        })
                        .then(function(response) {
                            // console.log(response.data.data);
                            if (response.data.code === 200) {
                                _this.$message.success('下架成功');
                                _this.tableData.splice(index, 1);
                            } else {
                                console.log(response);
                                _this.$message.error('下架失败');
                                return false;
                            }
                        })
                        .catch(function(error) {
                            console.log(error);
                            alert('等待超时');
                        });

                    // this.$message.success('下架成功');
                    // this.tableData.splice(index, 1);
                })
                .catch(() => {});
        },
        // 多选操作
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        delAllSelection() {
            const length = this.multipleSelection.length;
            let str = '';
            this.delList = this.delList.concat(this.multipleSelection);
            for (let i = 0; i < length; i++) {
                str += this.multipleSelection[i].name + ' ';
            }
            this.$message.error(`删除了${str}`);
            this.multipleSelection = [];
        },
        // 编辑操作
        handleEdit(index, row) {
            this.idx = index;
            this.form = row;
            this.editVisible = true;
        },
        // 保存编辑
        saveEdit() {
            this.editVisible = false;
            this.$message.success(`修改第 ${this.idx + 1} 行成功`);
            this.$set(this.tableData, this.idx, this.form);
        },
        // 分页导航
        handlePageChange(val) {
            this.$set(this.query, 'pageIndex', val);
            this.getData();
        }
    }
};
</script>

<style scoped>
.handle-box {
    margin-bottom: 20px;
}

.handle-select {
    width: 120px;
}

.handle-input {
    width: 300px;
    display: inline-block;
}
.table {
    width: 100%;
    font-size: 14px;
}
.red {
    color: #ff0000;
}
.mr10 {
    margin-right: 10px;
}
.table-td-thumb {
    display: block;
    margin: auto;
    width: 40px;
    height: 40px;
}
</style>
