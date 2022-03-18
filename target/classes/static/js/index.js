$(document).ready(function(){
	getCode();
	var h=$(window).height();
	$("#main").css("height",h);
	$("#loginBtn").click(function(){
		login();
	})
	$("#code").click(function(){
		getCode();
	})
	$("#addUser").click(function () {
		register();
	})
})


//获取验证码
function getCode(){
	$("#code").attr("src","./user/createImage?code="+Math.random());
}

//登录
function register(){
	var user=$("#inputAccount").val();
	var pwd=$("#inputPsw").val();
	var phone=$("#inputPhone").val();
	$.ajax({
		type:"POST",
		url:"./user/addUser.do",
		dataType:"JSON",
		data:{
			"useraccount":user,
			"password":pwd,
			"phonenumber":phone,
			"power":4,
			"username":"顾客",
			"age":0
		},
		success:function(data){
			console.log(data)
			if(data.code=="0"){
				alert("注册成功");
			}
			else if(data.code=="-1"){
				alert("注册失败");
			}
		},
		error:function(){
			alert("注册 发生错误");
		}
	});
}

//注册
function login(){
	var user=$("#inputName").val();
	var pwd=$("#inputPassword").val();
	var code=$("#inputCode").val();
	$.ajax({
		type:"POST",
		url:"./user/login.do",
		dataType:"JSON",
		data:{
			"useraccount":user,
			"password":pwd,
			"icode":code
		},
		success:function(data){
			console.log(data)
			if(data.code=="0"){
				var urlString="pages/myCenter.html?power="+data.power+"&userid="+data.userid;
				window.location.href=urlString;
			}
			else if(data.code=="-1"){
				alert("验证码或密码错误")
			}
		},
		error:function(){
			alert("登录 发生错误");
		}
	});
}