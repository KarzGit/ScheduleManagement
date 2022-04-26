'use strict'

let updateSchedule = document.getElementById('update_detail')
let scheduleDetail =document.getElementById('show_detail')

updateSchedule.style.display="none";

document.getElementById('edit').addEventListener('click',function(){
	
	updateSchedule.style.display=" block";
	scheduleDetail.style.display="none";
})

document.getElementById('update_end_time').addEventListener('change',function(){
dateTimeErrorCheck();	
})
document.getElementById('update_end_date').addEventListener('change',function(){
	dateTimeErrorCheck();
})
document.getElementById('update_title').addEventListener('change',function(){
	if(document.getElementById('update_title').value ==""){
		document.getElementById('titleError').style.display="block"
	}else{
		document.getElementById('titleError').style.display="none"
	}
})


function dateTimeErrorCheck(){
	console.log("処理")
	const startDate=document.getElementById('update_start_date').value
	const endDate= document.getElementById('update_end_date').value
	const endTime= document.getElementById('update_end_time').value
	const startTime= document.getElementById('update_start_time').value
	const startDateTime=new Date(startDate+" "+startTime);　//開始日時
	const endDateTime=new Date(endDate+" "+endTime);　//終了日時
	const scheduleTime=endDateTime-startDateTime;  //終了日時ー開始日時
	//日時設定のエラーチェック
	if(scheduleTime <=0){
		document.getElementById('datetimeError').style.display="block";
	}else{
		document.getElementById('datetimeError').style.display="none";
	}
};


