// app.js
App({

 data:{
    // url:'http://localhost:8081',
    url:'https://love.fshoo.cn'
 },

  onLaunch() {
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
  },
  globalData: {
    userInfo: null
  },
  
  ShowToast(a,b,c){
    //参数b包含 success,error,loading,none
    wx.showToast({
      title: a,
      icon: b,
      mask:true,
      duration: c
    })
  },
  HideToast(){
    wx.hideToast({
      success: (res) => {

      },
    })
  },
  guid() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = Math.random() * 16 | 0,
            v = c == 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
},
getDay(e) {
    var start = new Date(e); //开始的时间
    var end = new Date(); //结束的时间
    var se = end - start; //计算两个时间之间的秒数
    var days = Math.floor(se / (24 * 3600 * 1000)); // 计算天数
    return days + 1;
},
})
