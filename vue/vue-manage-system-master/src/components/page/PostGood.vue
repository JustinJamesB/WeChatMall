<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-calendar"></i> 商品
                </el-breadcrumb-item>
                <el-breadcrumb-item>上架</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form ref="form" :model="form" label-width="80px">
                    <el-form-item label="商品名称">
                        <el-input v-model="form.goods_name"></el-input>
                    </el-form-item>
                    <el-form-item label="商品类别">
                        <el-select v-model="form.category_id" placeholder="请选择">
                            <el-option key="shuguo" label="新鲜蔬菜" value="1"></el-option>
                            <el-option key="shuichang" label="时令水果" value="2"></el-option>
                            <el-option key="shengqin" label="畜牧品" value="3"></el-option>
                            <el-option key="ruzhiping" label="豆制品" value="4"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="商品规格">
                        <el-input v-model="form.spec_type"></el-input>
                    </el-form-item>

                    <el-form-item label="商品库存">
                        <el-input v-model="form.stock_num"></el-input>
                    </el-form-item>
                    <el-form-item label="商品详情">
                        <el-input v-model="form.content"></el-input>
                    </el-form-item>
                    <el-form-item label="商品价格">
                        <el-input v-model="form.counter_price"></el-input>
                    </el-form-item>
                    <el-form-item label="新品">
                        <div>
                            <el-radio v-model="radio1" label="1" border>是</el-radio>
                            <el-radio v-model="radio1" label="2" border>否</el-radio>
                        </div>
                    </el-form-item>
                    <el-form-item label="商品封面">
                        <el-upload
                            action="http://localhost:8080/tool/oss/homeImageUpload"
                            list-type="picture-card"
                            :on-preview="handlePictureCardPreview"
                            :on-remove="handleRemove"
                            :on-success="Upsuccess"
                            :disabled="this.form.count>1?true:false"
                        >
                            <i class="el-icon-plus"></i>
                        </el-upload>
                        <el-dialog :visible.sync="dialogVisible">
                            <img width="100%" :src="dialogImageUrl" alt />
                        </el-dialog>
                    </el-form-item>

                    <el-form-item label="商品轮播">
                        <el-upload
                            action="http://localhost:8080/tool/oss/homeImageUpload"
                            list-type="picture-card"
                            :on-preview="handlePictureCardPreview"
                            :on-remove="handleRemove"
                            :on-success="Upsuccess1"
                        >
                            <i class="el-icon-plus"></i>
                        </el-upload>
                        <el-dialog :visible.sync="dialogVisible">
                            <img width="100%" :src="dialogImageUrl" alt />
                        </el-dialog>
                    </el-form-item>

                    <el-form-item label="商品详情图">
                        <el-upload
                            action="http://localhost:8080/tool/oss/homeImageUpload"
                            list-type="picture-card"
                            :on-preview="handlePictureCardPreview"
                            :on-remove="handleRemove"
                            :on-success="Upsuccess2"
                            :disabled="this.form.count1>1?true:false"
                        >
                            <i class="el-icon-plus"></i>
                        </el-upload>
                        <el-dialog :visible.sync="dialogVisible">
                            <img width="100%" :src="dialogImageUrl" alt />
                        </el-dialog>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="onSubmit">商品上架</el-button>
                        <el-button>取消</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script>
import { client } from '../../utils/alioss.js';
import VueCropper from 'vue-cropperjs';
export default {
    name: 'baseform',
    data() {
        return {
            dialogImageUrl: '',
            dialogVisible: false,
            radio1: '1',
            // defaultSrc: require('../../assets/img/img.jpg'),
            fileList: [],
            imgSrc: '',
            cropImg: '',
            dialogVisible: false,
            form: {
                count: 0,
                count1: 0,
                goods_img_url: '',
                c_img: '',
                b_img: [],
                goods_name: '',
                category_id: '',
                spec_type: '',
                stock_num: '',
                content: '',
                counter_price: '',
                name: '',
                region: '',
                date1: '',
                date2: '',
                delivery: true,
                type: ['步步高'],
                resource: '小天才',
                desc: '',
                options: []
            }
        };
    },
    computed: {
        hideShow() {
            //当图片多于一张的时候，就隐藏上传框
            return this.form.goods_img_url === '' ? false : true;
        },
        hideShow1() {
            //当图片多于一张的时候，就隐藏上传框
            return this.form.c_img === '' ? false : true;
        }
    },
    methods: {
        handlePictureCardPreview(file) {
            // this.dialogImageUrl = file.url;
            this.dialogVisible = true;
            console.log(file);
        },
        Upsuccess(response, file, fileList) {
            // console.log(response);
            // console.log(file);

            if (false === response.success) {
                this.$alert('图片上传失败，请重新上传').catch(error => error);
            } else {
                this.form.count = 2;
                let temp = response.data.split('?')[0].split(':');
                // console.log();

                this.form.goods_img_url = temp[0] + 's:' + temp[1];
            }

            // console.log(response)
        },
        Upsuccess1(response, file, fileList) {
            if (false === response.success) {
                this.$alert('图片上传失败，请重新上传').catch(error => error);
            } else {
                console.log(response.data.split('?')[0]);
                // this.form.b_img.push(response.data.split('?')[0]);
                let temp = response.data.split('?')[0].split(':');
                this.form.b_img.push(temp[0] + 's:' + temp[1]);

                console.log(this.form.b_img);
            }

            // console.log(response)
        },
        Upsuccess2(response, file, fileList) {
            // console.log(response);
            // console.log(file);

            if (false === response.success) {
                this.$alert('图片上传失败，请重新上传').catch(error => error);
            } else {
                // console.log(response.data.split('?')[0]);
                this.form.count1 = 2;
                let temp = response.data.split('?')[0].split(':');
                // this.form.b_img.push();
                this.form.c_img = temp[0] + 's:' + temp[1];
            }
        },
        pu_image(file) {
            console.log(file);
        },
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePreview(file) {
            console.log(file);
        },
        onSubmit() {
            let _this = this;
            this.$axios
                .post('http://localhost:8080/adminGoods/addGoods', {
                    goods_img_url: this.form.goods_img_url,
                    c_img: this.form.c_img,
                    b_img: this.form.b_img,
                    goods_name: this.form.goods_name,
                    category_id: this.form.category_id,
                    spec_type: this.form.spec_type,
                    stock_num: this.form.stock_num,
                    content: this.form.content,
                    counter_price: this.form.counter_price,
                    is_new: this.radio1
                })
                .then(function(response) {
                    console.log(response);
                    if (response.data.code === 200) {
                        console.log(response.data.code);

                        _this.$message.success('上架成功');
                    } else {
                        _this.$message.error('上架失败，重新上架');
                        console.log('error submit!!');
                        return false;
                    }
                })
                .catch(function(error) {
                    console.log(error);
                    _this.$message.alert('上架失败，等待超时');
                });
            // this.$message.success('提交成功！');
        },
        setImage(e) {
            const file = e.target.files[0];
            if (!file.type.includes('image/')) {
                return;
            }
            const reader = new FileReader();
            reader.onload = event => {
                this.dialogVisible = true;
                this.imgSrc = event.target.result;
                this.$refs.cropper && this.$refs.cropper.replace(event.target.result);
            };
            reader.readAsDataURL(file);
        },
        cropImage() {
            this.cropImg = this.$refs.cropper.getCroppedCanvas().toDataURL();
        },
        cancelCrop() {
            this.dialogVisible = false;
            this.cropImg = this.defaultSrc;
        },
        imageuploaded(res) {
            console.log(res);
        },
        handleError() {
            this.$notify.error({
                title: '上传失败',
                message: '图片上传接口上传失败，可更改为自己的服务器接口'
            });
        }
    },
    created() {
        this.cropImg = this.defaultSrc;
    },
    components: {
        VueCropper
    }
};
</script>