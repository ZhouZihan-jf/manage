var pageNum=1;
var pageSize=8;
var l;

$(document).ready(function(){
    getlogList();
    $("#pre").on('click',function(){
        getPre();
    });
    $("#next").on('click',function(){
        getNext();
    });

})

//判断对象/JSON是否为空 空返回1 非空返回0
function isEmptyObject(e) {
    var t;
    for (t in e)
        return 0;
    return 1;
}



var list;
function getlogList(){
    $.ajax({
        type:"post",
        url:"../log/getAllLog.do",
        dataType:"JSON",
        data:{
            "pageNum":pageNum,
            "pageSize":pageSize
        },
        success:function(data){
            if(isEmptyObject(data.List)&&pageNum>0){
                pageNum=pageNum-1;
                getlogList();
            }
            else{
                list=data.List;
                var htmlStr=" ";
                var btnStr1=" ";
                var sdate;
                var edate;
                l=0;
                $("#pre").css("display","block");
                $("#next").css("display","block");
                $("#logList").empty();
                $("#logList").append("<tr><th>入住人</th><th>电话号码</th><th>开始时间</th><th>结束时间</th><th>总金额</th><th>详细</th></tr>")
                for(i in list){
                    sdate=/\d{4}-\d{1,2}-\d{1,2}/g.exec(list[i].starttime);
                    edate=/\d{4}-\d{1,2}-\d{1,2}/g.exec(list[i].endtime);
                    btnStr1="<input type=\"button\"  class=\"btn btn-info\" data-roomid=\""+list[i].roomid+"\" id=\"showRoom\"  data-toggle=\"modal\" data-target=\"#showRoomT\" value=\"查看房间\"> " +
                        "<input type=\"button\"  class=\"btn btn-info\"  data-userid=\""+list[i].userid+"\" id=\"showUser\" data-toggle=\"modal\" data-target=\"#showUserT\" value=\"经办人\">";
                    htmlStr="<tr data-listid=\""+list[i].listid+"\"><td>"+list[i].householdname+"</td><td>"+list[i].holdphone+"</td><td>"+sdate+"</td><td>"+edate+"</td><td>"+list[i].money+"</td><td>"+btnStr1+"</td></tr>";
                    $("#logList").append(htmlStr);
                    l++;
                }
                if(pageNum=="1") $("#pre").css("display","none");
                if(pageSize>l) $("#next").css("display","none");
                btnOn();
            }

        },
        error:function(){
            alert("获取订单列表发生错误")
        }
    })
}

function btnOn(){

    $("input").filter("#setPageBtn").on('click',function( ){
        setPage();
    });
    $("input").filter("#showRoom").on('click',function(event){
        showRoom(event);
    });
    $("input").filter("#showUser").on('click',function(event){
        showUser(event);
    });
    $("input").filter("#deleteLog").on('click',function () {
        deleteLog();
    })
    $("input").filter("#exportLog").on('click',function () {
        exportLog();
    })

}

function getPre(){
    pageNum=pageNum-1;
    getlogList();
}

function getNext(){
    pageNum=pageNum+1;
    getlogList();
}

function setPage(){

    if($("#inputPage").val()<0 || $("#inputPage").val()==0)
        alert("请输入正确页码");
    else{
        pageNum=$("#inputPage").val();
        getlogList();
    }

}

function deleteLog() {
    $.ajax({
        type:"POST",
        url:"../log/delLog.do",
        datatype:"JSON",
        data:{},
        success:function(){
                alert("删除成功");
                getlogList();
        },
        error:function(){
            alert("删除出现错误");
        }
    })
}

function exportLog() {
    var url="http://localhost:8888/log/export.do?pageNum=1&pageSize=8";
    /*
    $.ajax({
        type:"post",
        url: url,
        complete: function(){
            if(str!=0){
                alert("导出成功");
            }else{
                alert("导出失败")
            }
        }
    })
    */
}

function showRoom(event){
    var roomid=$(event.target).data("roomid");
    $.ajax({
        type:"POST",
        url:"../room/getRoomById.do",
        dataType:"JSON",
        data:{
            "roomid":roomid
        },
        success:function(data){
            if(data.code==0){
                var htmlStr=" ";
                var state=" ";
                var type=" ";
                var room=data.room;
                $("#roomTable").empty();
                if(room.state=="0")
                    state="停用";
                else if(room.state=="1")
                    state="未预定";
                else if(room.state=="2")
                    state="已预定(入住)";
                else
                    state="待清扫";
                if(room.type=="1")
                    type="单人间";
                else if(room.type=="2")
                    type="双人间";
                else if(room.type=="3")
                    type="大床房";
                else
                    type="套房";
                htmlStr="<tr><th>位置</th><td>"+room.local+"</td></tr><tr><th>价格</th><td>"+room.money+"</td></tr><tr><th>类型</th><td>"+type+"</td></tr><tr><th>状态</th><td>"+state+"</td></tr>"
                $("#roomTable").append(htmlStr);
            }
            else
                alert("获取失败")
        },
        error:function(){
            alert("获取信息出现错误");
        }
    })
}

function showUser(event) {
    var userid=$(event.target).data("userid");
    $.ajax({
        type:"POST",
        url:"../user/getUserById.do",
        dataType:"JSON",
        data:{
            "userid":userid
        },
        success:function(data){
            if(data.code==0){
                var htmlStr=" ";
                var user=data.user;
                $("#userTable").empty();
                htmlStr="<tr><th>姓名</th><td>"+user.username+"</td></tr><tr><th>电话</th><td>"+user.phonenumber+"</td></tr>"
                $("#userTable").append(htmlStr);
            }
            else
                alert("获取失败")
        },
        error:function(){
            alert("获取信息出现错误");
        }
    })

}