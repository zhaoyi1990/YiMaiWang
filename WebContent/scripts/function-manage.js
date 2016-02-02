// JavaScript Document
//function Delete(id)
//{
//	$.post("user_delete.do",{id:id},function(list){
//		$('#tb1').children().remove();
//		$.each(list,function(i,user){
//			$('#tb1').append("<tr>")
//			.append("<td class='first w4 c'>"+(user.eu_user_id-1)+"</td>")
//			.append("<td class='w1 c'>"+user.eu_name+"</td>")
//			.append("<td class='w2 c'>"+(user.eu_sex==1?'男':'女')+"</td>")
//			.append("<td>"+user.eu_email+"</td>")
//			.append("<td class='w4 c'>"+user.eu_mobile+"</td>")
//			.append("<td class='w1 c'><a href='user_modify.do?id="+user.eu_user_id+"' >修改</a> " +
//					"<a href='javascript:Delete("+user.eu_user_id+")' >删除</a></td>")
//			.append("</tr>")
//		})
//	},"json")
//}
function setDay(){
	var	year= $('#year').val();
	var	month= $('#month').val();
	var max;
	if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
		max=31;
	}else if(month==4||month==6||month==9||month==11){
		max=30;
	}else{
		if((year%4==0&&year%100!=0)||(year%400==0)){
			max=29;
		}else{
			max=28;
		}
	}
	$("#day").children().remove();
		
	for(var i = 1;i<=max;i++){
		var option = document.createElement("option")
		option.value=i;
		option.innerHTML=i;
		$("#day").append(option);
	}
}

function clist(obj){
	var pid = obj.value;
	$.post("product_clist.do",{pid:pid},function(clist){
		$("#product_clist").children().remove();
		$.each(clist,function(i,epc){
			var option = document.createElement("option");
			option.value=epc.epc_id;
			option.innerHTML=epc.epc_name;
			$("#product_clist").append(option);
		})
	},"json")
}

function xianshi(){
	var docObj=document.getElementById("imgFile"); 
	var imgObjPreview=document.getElementById("fileImg"); 
	if(docObj.files && docObj.files[0]){ 
		document.getElementById("imgtr").style.display = "";
		imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]); 
	}else{ 
		document.getElementById("imgtr").style.display = "none";
	} 

}
