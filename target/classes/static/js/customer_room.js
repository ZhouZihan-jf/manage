(function($){
    $.getData=function(name){
        var reg=new RegExp("(^|&)"+name+"=([^&]+)(&|$)?");
        var result = window.location.search.substr(1).match(reg);
        if (result!= null) return result[2]; return null;
    }
})(jQuery);

var staffid=$.getData("userid");

var pageNum=1;
var pageSize=8;

$(document).ready(function(){
    matchUrl();
})



//判断对象/JSON是否为空 空返回1 非空返回0
function isEmptyObject(e) {
    var t;
    for (t in e)
        return 0;
    return 1;
}

//判断字符串是否为空 空返回1 非空返回0
function isEmptyString(str){
    if(str=='null'||str=='')
        return 1;
    return 0;
}


var list;

function matchUrl(){
    $.ajax({
        type:"post",
        url:"../room/getRoom.do",
        dataType:"JSON",
        data:{
            "state":"1",
            "type":"-1",
            "pageNum":pageNum,
            "pageSize":pageSize
        },
        success:function(data){
            if(isEmptyObject(data.List)&&pageNum>0){
                pageNum=pageNum-1;
                matchUrl();
            }
            else{
                list=data.List;
                var htmlStr=" ";
                $("#roomlist").empty();

                for(i in list){
                    htmlStr="<div class=\"col-md-4 col-sm-6\">\n" +
                        "        <div class=\"box\">\n" +
                        "            <img  src=\"../"+list[i].roomUrl+"\">\n" +
                        "            <div class=\"box-content\">\n" +
                        "                <h3 class=\"title\">"+list[i].local+"</h3>\n" +
                        "                <span class=\"post\">Web designer</span>\n" +
                        "            </div>\n" +
                        "            <ul class=\"icon\">\n" +
                        "                <li><a href=\"room_info.html?roomid="+list[i].roomid+"&userid="+staffid+"\"><i class=\"fa fa-search\"></i></a></li>\n" +
                        "            </ul>\n" +
                        "        </div>\n" +
                        "    </div>";
                    /*
                    htmlStr="<li class=\"list-group-item\">\n" +
                        "                <div class=\"nav-divider\">\n" +
                        "                    <h4 class=\"h4\" >"+list[i].local+"</h4>\n" +
                        "                </div>\n" +
                        "                <div style=\"border: 3px gold\">\n" +
                        "                    <a href=\"room_info.html?roomid="+list[i].roomid+"&userid="+staffid+"\" ><img src=\"../"+list[i].roomUrl+"\" title=\"详情\"></a>\n" +
                        "                </div>\n" +
                        "            </li>";
                     */
                    $("#roomlist").append(htmlStr);
                }

            }
        },
        error:function(){
            alert("获取房间列表发生错误")
        }
    })
}




