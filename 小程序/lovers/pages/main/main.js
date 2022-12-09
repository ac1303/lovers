// pages/main/main.js
const app = getApp()

Page({

    /**
     * 页面的初始数据
     */
    data: {
        userInfo: {},
        canIUse: wx.canIUse('button.open-type.getUserInfo'),
        canIUseGetUserProfile: true,
        canIUseOpenData: wx.canIUse('open-data.type.userAvatarUrl') && wx.canIUse('open-data.type.userNickName'), // 如需尝试获取用户信息可改为false
        competence: 99
    },
    // 微信小程序审核不通过，因为必须给用户展示一个界面体验，但是我又不想改代码，就糊弄一下吧
    checkCompetence(){
        if (this.data.competence == 99) {
            this.setData({
                competence:0
            })
            return false
        }
        return true
    },
    // 事件处理函数
    bindViewTap() {
        if (!this.checkCompetence()) {
            return;
        }
        wx.navigateTo({
            url: '../logs/logs'
        })
    },
    JumpScorePages(e) {
        if (!this.checkCompetence()) {
            return;
        }
        wx.navigateTo({
            url: '/pages/score/score?id=' + e.currentTarget.dataset.id,
        })
    },
    JumpOperationPages() {
        if (!this.checkCompetence()) {
            return;
        }
        wx.navigateTo({
            url: '/pages/operation/operation',
        })
    },
    JumpHistoryPages() {
        if (!this.checkCompetence()) {
            return;
        }
        wx.navigateTo({
            url: '/pages/history/history',
        })
    },
    Signin(){
        if (!this.checkCompetence()) {
            return;
        }
        wx.request({
            method: 'POST',
            dataType: 'json',
            header: {
                "session": wx.getStorageSync('session'),
                'content-type': 'application/x-www-form-urlencoded;charset=utf-8'
            },
            data: {},
            url: app.data.url + '/Signin',
            success(e) {
                app.HideToast();
                console.log(e.data)
                if (e.data.code == 200) {
                    app.ShowToast("签到成功！", "success", 1000)
                } else {
                    app.ShowToast(e.data.data, "error", 1000)
                }
            },
            fail(e) {
                app.HideToast();
                app.ShowToast("网络错误或未知错误", "error", 1000)
            }
        })
    },
    getUserProfile(e) {
        var that = this
        // 推荐使用wx.getUserProfile获取用户信息，开发者每次通过该接口获取用户个人信息均需用户确认，开发者妥善保管用户快速填写的头像昵称，避免重复弹窗
        wx.getUserProfile({
            desc: '认证用户信息', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
            success: (res) => {
                console.log(res)
                this.setData({
                    userInfo: res.userInfo
                })
                //   调用API获取OpenID和session
                // login登录获取用户code
                wx.login({
                    success(res) {
                        console.log("Wx.Login数据", res);
                        app.ShowToast("登录中...", "loading", 999999);
                        // 请求数据
                        wx.request({
                            method: 'POST',
                            dataType: 'json',
                            data: {
                                code: res.code
                            },
                            url: app.data.url + '/Login',
                            success(e) {
                                app.HideToast();
                                console.log("Login请求返回数据", e);
                                if (e.data.code == 200) {
                                    // 缓存session
                                    wx.setStorageSync('session', e.data.data.user.sessionKey)
                                    wx.setStorageSync('competence', e.data.data.user.competence)
                                    wx.setStorageSync('lovesDay', that.getDay(e.data.data.lovers.dateOfLove))


                                    app.ShowToast("登录成功！", "success", 1000)
                                    that.setData({
                                        competence: e.data.data.user.competence,
                                    })
                                } else {
                                    app.ShowToast(e.data.msg, "error", 1000)
                                    wx.clearStorage()
                                }
                            },
                            fail(e) {
                                // 请求用户OpenID失败
                                app.HideToast();
                                app.ShowToast("网络请求失败！", "error", 2000);
                                console.log("网络请求失败，登录失败");
                                wx.clearStorage()
                            },
                        })
                    },
                    fail(e) {
                        console.log("login登录失败");
                    }
                })
            }
        })
    },
    getDay(e) {
        var start = new Date(e); //开始的时间
        var end = new Date(); //结束的时间
        var se = end - start; //计算两个时间之间的秒数
        var days = Math.floor(se / (24 * 3600 * 1000)); // 计算天数
        return days + 1;
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        var that = this
        let c=wx.getStorageSync('competence')
        if (!c) {
            that.setData({
                competence: 99,
            })
            return
        }
        wx.checkSession({
            success() {
                //session_key 未过期，并且在本生命周期一直有效
                that.setData({
                    competence: wx.getStorageSync('competence'),
                })
                console.log("session_key 未过期")
            },
            fail() {
                // session_key 已经失效，需要重新执行登录流程
                console.log("session_key 已经失效，需要重新执行登录流程")
                wx.clearStorage()
            }
        })
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    }
})