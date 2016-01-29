// JavaScript Document
//window.onload = function() {
//	showChater();
//	scrollChater();
//}
//window.onscroll = scrollChater;
//window.onresize = scrollChater;

function FocusItem(obj) {
	obj.parentNode.parentNode.className = "current";
	var msgBox = obj.parentNode.getElementsByTagName("span")[0];
	if (msgBox != null) {
		msgBox.innerHTML = "";
		msgBox.className = "";
	}
}

function CheckItem(obj) {
	obj.parentNode.parentNode.className = "";
	var msgBox = obj.parentNode.getElementsByTagName("span")[0];
	var flag = true;
	switch (obj.name) {
	case "user.eu_user_name":
		if (obj.value == "") {
			msgBox.innerHTML = "用户名不能为空";
			msgBox.className = "error";
			flag = false;
		} else {
			if(obj.id=="red_username"){
				$.post("reg_yz.jsp", {'username' : obj.value}, function(text) {
					if (text > 0) {
						msgBox.innerHTML = "用户名已存在";
						msgBox.className = "error";
						flag = false;
					}
				},"html");	
			}
		}
		break;
	case "user.eu_password":
		if (obj.value == "") {
			msgBox.innerHTML = "密码不能为空";
			msgBox.className = "error";
			flag = false;
		}
		break;
	case "rePassWord":
		if (obj.value == "") {
			msgBox.innerHTML = "确认密码不能为空";
			msgBox.className = "error";
			flag = false;
		} else if (obj.value != document.getElementById("passWord").value) {
			msgBox.innerHTML = "两次输入的密码不相同";
			msgBox.className = "error";
			flag = false;
		}
		break;
	case "veryCode":
		if (obj.value == "") {
			msgBox.innerHTML = "验证码不能为空";
			msgBox.className = "error";
			flag = false;
		}
		break;
	default:
		break;
	}
	return flag;
}

function checkForm(frm) {
	var els = frm.getElementsByTagName("input");
	for (var i = 0; i < els.length; i++) {
		if (els[i].getAttribute("onblur") == "CheckItem(this)") {
			if (!CheckItem(els[i])) {
				return false;
			}
		}
	}
	return true;
}

function showChater() {
	var _chater = document.createElement("div");
	_chater.setAttribute("id", "chater");
	var _dl = document.createElement("dl");
	var _dt = document.createElement("dt");
	var _dd = document.createElement("dd");
	var _a = document.createElement("a");
	_a.setAttribute("href", "#");
	_a.onclick = openRoom;
	_a.appendChild(document.createTextNode("在线聊天"));
	_dd.appendChild(_a);
	_dl.appendChild(_dt);
	_dl.appendChild(_dd);
	_chater.appendChild(_dl);
	document.body.appendChild(_chater);
}

function openRoom() {
	window.open("chat-room.html", "chater",
			"status=0,scrollbars=0,menubar=0,location=0,width=600,height=400");
}

function scrollChater() {
	var chater = document.getElementById("chater");
	var scrollTop = document.documentElement.scrollTop;
	var scrollLeft = document.documentElement.scrollLeft;
	chater.style.left = scrollLeft + document.documentElement.clientWidth - 92
			+ "px";
	chater.style.top = scrollTop + document.documentElement.clientHeight - 25
			+ "px";
}

function inArray(array, str) {
	for (a in array) {
		if (array[a] == str)
			return true;
	}
	return false;
}

function setCookie(name, value) {
	var Days = 30;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + escape(value) + ";expires="
			+ exp.toGMTString();
}

function getCookie(name) {
	var arr = document.cookie
			.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
	if (arr != null)
		return unescape(arr[2]);
	return null;
}

function delCookie(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
	if (cval != null)
		document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}

function goBuy(id, price) {
	var newCookie = "";
	var oldCookie = getCookie("product");
	if (oldCookie) {
		if (inArray(oldCookie.split(","), id)) {
			newCookie = oldCookie;
		} else {
			newCookie = id + "," + oldCookie;
		}
	} else {
		newCookie = id;
	}
	setCookie("product", newCookie);
	location.href = "shopping.html";
}

function delShopping(id) {
	var tr = document.getElementById("product_id_" + id);
	var oldCookie = getCookie("product");
	if (oldCookie) {
		var oldCookieArr = oldCookie.split(",");
		var newCookieArr = new Array();
		for (c in oldCookieArr) {
			var cookie = parseInt(oldCookieArr[c]);
			if (cookie != id)
				newCookieArr.push(cookie);
		}
		var newCookie = newCookieArr.join(",");
		setCookie("product", newCookie);
	}
	if (tr)
		tr.parentNode.removeChild(tr);
}

function reloadPrice(id, status) {
	var price = document.getElementById("price_id_" + id).getElementsByTagName(
			"input")[0].value;
	var priceBox = document.getElementById("price_id_" + id)
			.getElementsByTagName("span")[0];
	var number = document.getElementById("number_id_" + id);
	if (status) {
		number.value++;
	} else {
		if (number.value == 1) {
			return false;
		} else {
			number.value--;
		}
	}
	priceBox.innerHTML = "￥" + price * number.value;
}

function setDay() {
	var year = $('#year').val();
	var month = $('#month').val();
	var max;
	if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
			|| month == 10 || month == 12) {
		max = 31;
	} else if (month == 4 || month == 6 || month == 9 || month == 11) {
		max = 30;
	} else {
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			max = 29;
		} else {
			max = 28;
		}
	}
	$("#day").children().remove();

	for (var i = 1; i <= max; i++) {
		var option = document.createElement("option")
		option.value = i;
		option.innerHTML = i;
		$("#day").append(option);
	}
}