// JavaScript Document
function Delete(id)
{
	if(confirm("确定要删除吗？")) {
		location.href = "user-delete.html?id=" + id;
	}
}
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