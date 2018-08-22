//function init(){
//    //初始化页面数据
//    //虚拟一个页面Id 结合userid获取页面数据
//    var pageId = 'testpage1';
//    var userId = 'userId1';
//    var params = 'pageId='+pageId + '&userId='+userId;
//    
//    //{‘content’:'str','annos':[{},{}]}
//    function render(obj, serverData) {
//        var jsonData = JSON.parse(serverData);
//        alert(jsonData);
//    }
//    
//    //第一版所有数据一个接口返回 
//    myAjax.post("/hi-de/init", params, render, 'form');
//    
//    //画背景 太阳等的刷新率低的放一起 
//    //蜻蜓等刷新率高的放一起
//    function draw() {
//        var canvas = document.getElementById("canvas");
//        var w = canvas.parentElement.clientWidth;
//        var h = canvas.parentElement.clientHeight;
//        canvas.setAttribute("width",w);
//        canvas.setAttribute("height",h);
//        var ctx = canvas.getContext("2d");
//        ctx.save();
//        ctx.lineWidth=8;
//        ctx.clearRect(0,0,canvas.width,canvas.height);
//        ctx.translate(w/2,h);
//
//        var date = new Date();
//        var hour = date.getHours();
//        var minute = date.getMinutes();
//        var sec = date.getSeconds();
//        var msec = date.getMilliseconds();
//
//        //画太阳
//        if(hour>=6 && hour<=18) {
//           var angle = Math.PI/12*(hour-6) + (Math.PI/12/60)*minute + (Math.PI/12/60/60)*sec;
//            ctx.rotate(-angle);
//
//            //圆心
//            var cirX;
//            if(h>=Math.abs((w/2*Math.tan(angle)))) {
//                cirX = Math.abs(w/2/Math.cos(angle));
//            } else{
//                cirX = h/Math.sin(angle);
//            }
//            ctx.beginPath();
//            ctx.arc(cirX,0,20,0,Math.PI*2,false);
//            ctx.fill();
//            ctx.restore();
//        } 
//        
//        //画星空
//        else {
//
//        }
//
//        window.setTimeout(draw,10000);
////      window.requestAnimationFrame(draw);  //非常占用CPU 内存
//    }
//    draw();
//}

var myAjax = {
    get: function (url, fn) {
        var xhr = new XMLHttpRequest();  // XMLHttpRequest对象用于在后台与服务器交换数据          
        xhr.open('GET', url, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200 || xhr.status == 304) { // readyState == 4说明请求已完成
                fn.call(this, xhr.responseText);  //从服务器获得数据
            }
        };
        xhr.send();
    },
    post: function (url, data, fn, dataType) {         // datat应为'a=a1&b=b1'这种字符串格式，在jq里如果data为对象会自动将对象转成这种字符串格式
        var xhr = new XMLHttpRequest();
        xhr.open("POST", url, true);
        if(dataType==null || dataType == undefined) {
            xhr.setRequestHeader("Content-Type", "application/json;charset=utf-8");  // 添加http头，发送信息至服务器时内容编码类型
        }
    
        if(dataType == 'form') {
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        }
        // 添加http头，发送信息至服务器时内容编码类型
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4 && (xhr.status == 200 || xhr.status == 304)) {  // 304未修改
                if(fn==null || fn==undefined) {
                    return;
                }
                fn.call(this, xhr.responseText);
//                document.write(xhr.responseText);
            }
        };
        xhr.send(data);
    }
}