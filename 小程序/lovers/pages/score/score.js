// pages/score/score.js
const app = getApp()
Page({

    /**
     * 页面的初始数据
     */
    data: {
        TabCur: 0,
        scrollLeft: 0,
        integration: 0,
        gold: 0,
        day: 0,
        nav_name: ['加分规则', '减分规则'],
        add_List: [{
            text: "每日签到",
            score: "10"
        }, ],
        sub_List: [{
                text: "惹对方生气",
                score: "10"
            },
            {
                text: "情绪不说",
                score: "20"
            },
        ],
    },
    tabSelect(e) {
        this.setData({
            TabCur: e.currentTarget.dataset.id,
            scrollLeft: (e.currentTarget.dataset.id - 1) * 60
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        console.log(options.id)
        var that = this
        var url = app.data.url
        if (options.id == 1) {
            url = url + '/Score/getOneselfScore'
        } else if (options.id == 2) {
            url = url + '/Score/getLoversScore'
        } else {
            app.ShowToast("参数错误", "error", 999999);
        }
        app.ShowToast("登录中...", "loading", 999999);
        wx.request({
            method: 'POST',
            dataType: 'json',
            header: {
                "session": wx.getStorageSync('session'),
                'content-type': 'application/x-www-form-urlencoded;charset=utf-8'
            },
            // data: {
            //     session: wx.getStorageSync('session')
            // },
            url: url,
            success(e) {
                app.HideToast();
                console.log(e.data)
                if (e.data.code == 200) {
                    that.setData({
                        integration: e.data.data.score,
                        gold: e.data.data.gold,
                        day: wx.getStorageSync('lovesDay'),
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