// pages/operation/operation.js
const app = getApp()
var util = require('../../utils/util.js');

Page({

    /**
     * 页面的初始数据
     */
    data: {
        score_index: 0,
        score_picker: ['加分', '扣分'],
        gold_index: 0,
        gold_picker: ['好感度', '金币'],
        score: 0,
        reason: "",
        id: "",
        time: ""
    },
    getScore(e) {
        this.setData({
            score: e.detail.value
        })
    },
    getReason(e) {
        this.setData({
            reason: e.detail.value
        })
    },
    PickerChangeGold(e) {
        console.log(e);
        this.setData({
            gold_index: e.detail.value
        })
    },    
    PickerChangeScore(e) {
        console.log(e);
        this.setData({
            score_index: e.detail.value
        })
    },
    trimStr(str){
        return str.replace(/(^\s*)|(\s*$)/g,"");
      },
    submoit(e) {
        if(this.data.reason == null || 
            this.trimStr(this.data.reason).length <=1 || 
            this.data.score <= 0 ||
            isNaN(this.data.score)){
            app.ShowToast("请检查内容","error",2000);
            return;
          }
          let operation = 1;
          let score_index=this.data.score_index
          let gold_index=this.data.gold_index
          if (score_index == 0 && gold_index == 0) {
            operation = 1;
          }else if (score_index == 1 && gold_index == 0) {
            operation = 2;
          }else if (score_index == 0 && gold_index == 1) {
            operation = 3;
          }else if (score_index == 1 && gold_index == 1) {
            operation = 4;
          }
          console.log(operation)
          console.log(this.data.score,this.data.reason)
          var that = this
        wx.request({
            method: 'POST',
            dataType: 'json',
            header: {
                "session": wx.getStorageSync('session'),
                'content-type': 'application/x-www-form-urlencoded;charset=utf-8'
            },
            data: {
                uuid: app.guid(),
                openId: "",
                loversOpenId: "",
                operation: operation,
                score: that.data.score,
                reason: that.data.reason,
                time: util.formatTime(new Date())
            },
            url: app.data.url + '/ScoreHistory',
            success(e) {
                app.HideToast();
                console.log(e.data)
                if (e.data.code == 200) {
                    app.ShowToast("成功！", "success", 1000)
                } else {
                    app.ShowToast(e.data.msg, "error", 1000)
                }
            },
            fail(e) {
                app.HideToast();
                app.ShowToast("网络错误或未知错误", "error", 1000)
            }
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {

    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide() {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload() {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom() {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage() {

    }
})