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
var choose;
var l;

$(document).ready(function(){
	getroomList();

	$('#dateStart').datepicker({
		language: 'zh-CN',
		format: 'yyyy-mm-dd',
		autoclose: true
	}).on('changeDate',function(e){
		var startTime = e.date;
		$('#dateEnd').datepicker('setStartDate',startTime);
	});

	$('#dateEnd').datepicker({
		language: 'zh-CN',
		format: 'yyyy-mm-dd',
		autoclose: true
	}).on('changeDate',function(e){
		var endTime = e.date;
		$('#dateStart').datepicker('setEndDate',endTime);
	});


	$("#pre").on('click',function(){
		getPre();
	});
	$("#next").on('click',function(){
		getNext();
	});
	$("#addOrder").on('click',function(){
		addOrder();
	});

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
function getroomList(){
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
				getroomList();
			}
			else{
				list=data.List;
				var power=" ";
				var htmlStr=" ";
				var btnStr=" ";
				var state=" ";
				var type=" ";
				l=0;
				$("#pre").css("display","block");
				$("#next").css("display","block");
				$("#roomList").empty();
				$("#roomList").append("<tr><th>位置</th><th>价格</th><th>状态</th><th>类型</th><th>操作</th></tr>");
				state="未预定";
				for(i in list){		
					if(list[i].type=="1")
						type="单人间";
					else if(list[i].type=="2")
						type="双人间";
					else if(list[i].type=="3")
						type="大床房";
					else
						type="套房"
					btnStr="<input type=\"button\" class=\"btn btn-success\" data-roomid=\""+list[i].roomid+"\" id=\"chooseRoomBtn\" data-toggle=\"modal\" data-target=\"#chooseRoom\" value=\"生成订单\">";
					htmlStr="<tr data-roomid=\""+list[i].roomid+"\"><td>"+list[i].local+"</td><td>"+list[i].money+"</td><td>"+state+"</td><td>"+type+"</td><td>"+btnStr+"</td></tr>";
					$("#roomList").append(htmlStr);
					l++;
				}
				if(pageNum=="1") $("#pre").css("display","none");
				if(pageSize>l) $("#next").css("display","none");
				btnOn();
			}

		},
		error:function(){
			alert("获取房间列表发生错误")
		}
	})
}

function btnOn(){;
	$("input").filter("#setPageBtn").on('click',function( ){
		setPage( );
	});
	$("input").filter("#chooseRoomBtn").on('click',function(event){
		chooseRoomBtn(event);
	});
}

function getPre(){
	pageNum=pageNum-1;
	getroomList();
}

function getNext(){
	pageNum=pageNum+1;
	getroomList();	
}

function setPage(){
	if($("#inputPage").val()<0 || $("#inputPage").val()==0)
		alert("请输入正确页码");
	else{
		pageNum=$("#inputPage").val();
		getroomList();
	}
	
}

function chooseRoomBtn(event){
	var roomid=$(event.target).data("roomid");
	choose=roomid;
	for(i in list){
		if(list[i].roomid=roomid){
			$("span").filter("#inputLocal").text(list[i].local);
		}
	}
}

function checkPhone(p, required = true) {
	if (!p) {
		return required ? false: true;
	} else {
		// 必须是1开头，第二位数字可以是0-9任意一个，总长为11
		let reg = /^1([0-9])\d{9}$/;
		// 必须是1开头，第二位数字可以是3|5|6|7|8|9任意一个，总长为11
		// let reg = /^1([3|5|6|7|8|9])\d{9}$/;
		if (reg.test(p)) {
			return true;
		} else {
			return false;
		}
	}
}

function IdentityCodeValid(code) {
	var city = { 11: "北京", 12: "天津", 13: "河北", 14: "山西", 15: "内蒙古", 21: "辽宁", 22: "吉林", 23: "黑龙江 ", 31: "上海", 32: "江苏", 33: "浙江", 34: "安徽", 35: "福建", 36: "江西", 37: "山东", 41: "河南", 42: "湖北 ", 43: "湖南", 44: "广东", 45: "广西", 46: "海南", 50: "重庆", 51: "四川", 52: "贵州", 53: "云南", 54: "西藏 ", 61: "陕西", 62: "甘肃", 63: "青海", 64: "宁夏", 65: "新疆", 71: "台湾", 81: "香港", 82: "澳门", 91: "国外 " };
	var tip = "";
	var pass = true;

	if (!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)) {
		tip = "身份证号格式错误";
		pass = false;
	}

	if (!city[code.substr(0, 2)]) {
		tip = "地址编码错误";
		pass = false;
	}
	if (code.length == 18) {
		sBirthday = code.substr(6, 4) + "-" + Number(code.substr(10, 2)) + "-" + Number(code.substr(12, 2));
		var d = new Date(sBirthday.replace(/-/g, "/"))
		if (sBirthday != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate())) {
			//alert("非法生日");
			pass = false;
		}
	}
	//18位身份证需要验证最后一位校验位
	if (code.length == 18) {
		code = code.split('');
		//∑(ai×Wi)(mod 11)
		//加权因子
		var factor = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
		//校验位
		var parity = [1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2];
		var sum = 0;
		var ai = 0;
		var wi = 0;
		for (var i = 0; i < 17; i++) {
			ai = code[i];
			wi = factor[i];
			sum += ai * wi;
		}
		var last = parity[sum % 11];
		if (parity[sum % 11] != code[17]) {
			tip = "校验位错误";
			pass = false;
		}

	}
	return pass;
}

function addOrder(){
	if(isEmptyString($("#inputName").val())||isEmptyString($("#inputId").val())||isEmptyString($("#inputPhone").val())||isEmptyString($("#dateStart").val())||isEmptyString($("#dateEnd").val()))
		alert("请填写全内容");
	else{
		if(checkPhone($("#inputPhone").val())||IdentityCodeValid($("#inputId").val())){
			$.ajax({
				type:"POST",
				url:"../order/addOrder.do",
				dataType:"JSON",
				data:{
					"userid":id,
					"roomid":roomid,
					"householdname":$("#inputName").val(),
					"id":$("#inputId").val(),
					"holdphone":$("#inputPhone").val(),
					"starttime":$("#dateStart").val(),
					"endtime":$("#dateEnd").val()
				},
				success:function(data){
					if(data.code==0){
						alert("添加成功");
						$("#inputRoom").val("");
						$("#inputName").val("");
						$("#inputId").val("");
						$("#inputPhone").val("");
						$("#dateStart").val("");
						$("#dateEnd").val("");
						$('#chooseRoom').modal('toggle');
					}
				},
				error:function(){
					alert("添加订单出现错误");
				}
			})
		}
		else {
			alert("电话号码或者身份证不合法");
		}
	}
}

