// pages/history/history.js
const app = getApp()

Page({

    /**
     * 页面的初始数据
     */
    data: {
        list:[
            {
                day:"22-04-01",
                time:"上午",
                time2:"12:00",
                text:"我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王"
            },
            {
                day:"22-04-01",
                time:"上午",
                time2:"12:00",
                text:"我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王"
            },
            {
                day:"22-04-01",
                time:"上午",
                time2:"12:00",
                text:"我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王"
            },
            {
                day:"22-04-01",
                time:"上午",
                time2:"12:00",
                text:"我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王我好喜欢小王"
            }
        ],
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        var that = this
        wx.request({
            method: 'GET',
            dataType: 'json',
            header: {
                "session": wx.getStorageSync('session'),
                'content-type': 'application/x-www-form-urlencoded;charset=utf-8'
            },
            url: app.data.url + '/ScoreHistory?start=0&end=20',
            success(e) {
                app.HideToast();
                console.log(e.data)
                if (e.data.code == 200) {
                    app.ShowToast("数据获取成功！", "success", 1000)
                    that.setData({
                        list: that.dealWithTime(e.data.data)
                    })
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
    // 处理时间格式
    dealWithTime(e){
        for(let i=0;i<e.length;i++){
            console.log(e[i].time.indexOf('T'));
            let num = e[i].time.indexOf('T');
            e[i].time = e[i].time.substr(0,num)+ " " + e[i].time.substr(num+1,8)
        }
        return e;
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