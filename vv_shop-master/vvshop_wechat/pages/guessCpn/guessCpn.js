// pages/guessCpn/guessCpn.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    header:{
      type:Array,
      value:'[]'
    },
    choose1:{
      type:Array,
      value:[]
    },
    choose2:{
      type:Array,
      value:[]
    },
    img:{
      type:Array,
      value:[]
    },
    img1:{
      type:Array,
      value:[]
    },
    dataItem:{
      type:Array,
      value:[]
    },
    dataItem1:{
      type:Array,
      value:[]
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    currentIndex:0,
    isShow1:false,
    isShow2:false,
    chooseData:0,
    che:' '
  },

  /**
   * 组件的方法列表
   */
  methods: {
    handleHeader(res){
      // console.log(res);
      const index=res.currentTarget.dataset.index;
      this.triggerEvent('handleIma',index);
      // console.log(index);
      this.setData({
        currentIndex:index,
        che:-1
      })
      
    },
    handleChoose(res){
      console.log(res);
      const value=res.detail.value;
      // console.log(value);
      this.setData({
        che:value
      })
      this.triggerEvent('chooseChange',value,'')
    },
    handleDelete(){
      var self=this
      wx.showModal({
        title: '提示',
        content: '是否重置预测数据',
        success (res) {
          if (res.confirm){
            self.setData({
              che:null
            })
          } else if (res.cancel) {
            console.log("用户点击了取消");
          }
        }
      })
    }
  }
})
