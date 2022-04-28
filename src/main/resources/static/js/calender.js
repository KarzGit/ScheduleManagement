"use strict"
const weeks = ['日', '月', '火', '水', '木', '金', '土']

const date = new Date()
let year = date.getFullYear()
let month = date.getMonth() + 1
let month2= ('00'+month).slice(-2) //一桁の数字に0を追加　1→01
let day = date.getDate()
let dayCount = 1 // 日にちのカウント

let calenderHtml = '' // HTMLを組み立てる変数
let schedule_id = document.getElementsByClassName("scheduleList_id")
let schedule_title = document.getElementsByClassName("scheduleList_title")
let schedule_kinds = document.getElementsByClassName("scheduleList_kinds")
let schedule_startDate = document.getElementsByClassName("scheduleList_startDate")
let schedule_startTime = document.getElementsByClassName("scheduleList_startTime")
let schedule_endDate = document.getElementsByClassName("scheduleList_endDate")
let schedule_endTime = document.getElementsByClassName("scheduleList_endTime")
let schedule_description = document.getElementsByClassName("scheduleList_description")
const selectBox = document.getElementById('month')
selectBox[date.getMonth()].selected="true";
createCalender(year,month)
showClickDate()
//今日の日付にclass"today"を追加
const today=year+'/'+month2+'/'+day
const todayCheck= document.getElementById(today)
todayCheck.classList.add("today")


document.getElementById('prev').addEventListener('click',function(){
	
       if (month == 1) {
            month = 12
            year --
        } else {
            month --
        }
createCalender(year,month)
showClickDate()
selectBox[month-1].selected="true"
document.getElementById('year').placeholder=year
  let tableElement = document.getElementById("calender").querySelectorAll('table')
       
     if(tableElement.length>1){
	for(let i=1 ;i<=tableElement.length; i++){
	
		tableElement[i].remove();

	}
}



    })
    
     
 document.getElementById("next").addEventListener('click',function(){
	document.getElementById('calender').innerHTML = ' ';
	
	if(month == 12){
		month = 1
		year ++
	}else{
		month ++
	}
	createCalender(year,month)
	showClickDate()
	selectBox[month-1].selected="true"
	document.getElementById('year').placeholder=year
	
	 let tableElement = document.getElementById("calender").querySelectorAll('table')
       
     if(tableElement.length>1){
	for(let i=1 ;i<=tableElement.length; i++){
	
		tableElement[i].remove();
	}
}


})


//カレンダーを表示するメソッド
function createCalender(year,month){
month2= ('00'+month).slice(-2) //一桁の数字に0を追加　1→01


document.getElementById('calender').innerHTML = ' ';


calenderHtml += '<table>'
calenderHtml += '<tbody id="calenderBody">'
calenderHtml += '<tr id="week">'
for (let i = 0; i < weeks.length; i++) {
    calenderHtml += '<th>' + weeks[i] + '</th>'
}
calenderHtml +='</tr>'
calenderHtml +='</tbody>'
calenderHtml +='</table>'
document.getElementById('calender').innerHTML = calenderHtml;


	
    const startDate = new Date(year, month - 1, 1) // 月の最初の日を取得
    const endDate = new Date(year, month,  0) // 月の最後の日を取得
    const endDayCount = endDate.getDate() // 月の末日
    const lastMonthEndDate = new Date(year, month - 1, 0) // 前月の最後の日の情報
    const lastMonthendDayCount = lastMonthEndDate.getDate() // 前月の末日
    const startDay = startDate.getDay() // 月の最初の日の曜日を取得
    let dayCount=1;
	
   
    
	let diffMilliSec = endDate - startDate;
					/*ミリ秒を日数に変換*/
	let monthlyDays = parseInt(diffMilliSec / 1000 / 60 / 60 / 24);
  for (let w = 0; w <monthlyDays/7 ; w++) {
  
    let days=document.createElement('tr')
    days.classList.add('day_count')
     let schedulearea = document.createElement('tr') //スケジュール欄の追加
     schedulearea.classList.add('schedule_area')

    for (let d = 0; d < 7; d++) {
        if (w == 0 && d < startDay) {
            // 1行目で1日の曜日の前
            days.appendChild(document.createElement('td'))
            schedulearea.appendChild(document.createElement('td')) //スケジュール欄の追加
        } else if (dayCount > endDayCount) {
            // 末尾の日数を超えた
             days.appendChild(document.createElement('td'))
     	     schedulearea.appendChild(document.createElement('td'))//スケジュール欄の追加
        } else {
	  		let whileDays=document.createElement('td')
	  		let schedulebox=document.createElement('td')
	  		let dayCount2 = ('00'+dayCount).slice(-2)
	  		whileDays.setAttribute("id",year+'/'+month2+'/'+dayCount2)
	  		schedulebox.setAttribute("id",year+'-'+month2+'-'+dayCount2)

	  		whileDays.append(dayCount)
	  		days.appendChild(whileDays)
	  		schedulearea.appendChild(schedulebox)
            dayCount++
        }
    }
   
    document.getElementById('calenderBody').appendChild(days)
    document.getElementById('calenderBody').appendChild(schedulearea)
}
//カレンダーにスケジュールを追加するメソッド
		addSchedule(year,month)
};


   

document.getElementById("yearMonthSelector").addEventListener('click',function(){
	let year = ''
	if(document.getElementById('year').value == ""){
		 year = document.getElementById('year').placeholder
	}else{
	year = document.getElementById('year').value
	}

	const month = selectBox.selectedIndex+1

	createCalender(year,month)
	showClickDate()
	let tableElement = document.getElementById("calender").querySelectorAll('table')
       
     if(tableElement.length>1){
	for(let i=1 ;i<=tableElement.length; i++){
	
		tableElement[i].remove();
	}
}
})
	
  document.getElementById('select_date').innerHTML=year+'年'+month+'月'+day+'日'


	function showClickDate(){
			//class名schedule_areaを全件取得
	let trigger =document.getElementsByClassName('schedule_area')
	//triggerを配列に変換
	let triggers = Array.from(trigger) ;
	//配列全てにイベントを追加
	triggers.forEach(function(target){

		target.addEventListener('click',function(e){
			const clickDate=new Date(e.target.id);
			console.log(clickDate.getMonth())
			document.getElementById('select_date').innerHTML=clickDate.getFullYear()+'年'+(clickDate.getMonth()+1)+'月'+clickDate.getDate()+'日'
		
		})
	})


};


function addSchedule(year,month){
	
	let monthlySchedule_id=[]
	let monthlySchedule_title=[]
	let monthlySchedule_kinds=[]
	let monthlySchedule_startDate=[]
	let monthlySchedule_endDate=[]

	for(let i =0; i<schedule_startDate.length;i++){
		let startDate = new Date(schedule_startDate[i].value)
		
		if(startDate.getMonth()+1 == month && startDate.getFullYear() == year){
			monthlySchedule_id.push(schedule_id[i])
			monthlySchedule_title.push(schedule_title[i])
			monthlySchedule_kinds.push(schedule_kinds[i])
			monthlySchedule_startDate.push(schedule_startDate[i])
			monthlySchedule_endDate.push(schedule_endDate[i])
		}
	};
	 
	//スケジュールをカレンダーに追加する

		for(let i =0; i<monthlySchedule_startDate.length; i++){
			let scheduleDocument=document.createElement('span')  //スケジュール格納用spanを作成（スケジュールの数分）
			let scheduleStart=monthlySchedule_startDate[i].value
			let addScheduleArea = document.getElementById(scheduleStart)  //スケジュール開始日がIDになっているtd要素を取得
				//開始日時
				let loadDate = new Date(monthlySchedule_startDate[i].value);
				//終了日時
				let distDate = new Date(monthlySchedule_endDate[i].value);
				//日時の差をミリ秒単位で取得
				let diffMilliSec = distDate - loadDate;
				//ミリ秒を日数に変換
				let diffDays = parseInt(diffMilliSec / 1000 / 60 / 60 / 24);
				let schedule_period= diffDays+1
				scheduleDocument.style.width=102*schedule_period+'%';

				//スケジュール日数が複数日の場合
				if(monthlySchedule_startDate[i].value != monthlySchedule_endDate[i].value){
						//スケジュールが週を跨ぐ場合（schedule_period スケジュール日数が土曜を超えた場合）
					if(schedule_period>7-loadDate.getDay()){
						//土曜日までの長さを設定
						scheduleDocument.style.width=103*(7-loadDate.getDay())+'%';
					 //翌週にスケジュールを伸ばす
						let scheduleEndDateArea=document.getElementById(monthlySchedule_endDate[i].value) //スケジュール最終日を取得
						let scheduleEndWeekFirstChild = scheduleEndDateArea.parentNode.firstChild  //スケジュール最終日の週の日曜日
						let longScheduleDocument = document.createElement('span')
						longScheduleDocument.style.width=103*(distDate.getDay()+1)+'%'; //日曜日から最終日までの長さ設定
						//スケジュールの種類によって色を変える
						if(monthlySchedule_kinds[i].value =='仕事'){
							longScheduleDocument.style.backgroundColor="green";
						}else if(monthlySchedule_kinds[i].value == 'プライベート'){
							longScheduleDocument.style.backgroundColor="cyan";
							}else{
				longScheduleDocument.style.backgroundColor="#acff7f";
			}

						let longSchedulelink= document.createElement('a')
						longSchedulelink.href="http://localhost:8080/scheduleDetail?id="+monthlySchedule_id[i].value
						scheduleEndWeekFirstChild.appendChild(longSchedulelink)
						longScheduleDocument.classList.add('schedule_list')
						longScheduleDocument.innerText=monthlySchedule_title[i].value
						scheduleEndWeekFirstChild.lastElementChild.appendChild(longScheduleDocument)
						}

						//空の欄を追加する
						let nextStartDate=new Date(loadDate.setDate(loadDate.getDate()+1));
						
				
						for(let d=0 ; d<schedule_period-1;d++){
							if(nextStartDate.getDay() == 0){
								nextStartDate=new Date(nextStartDate.setDate(nextStartDate.getDate()+1));
								continue;
							}
							let month2= ('00'+(nextStartDate.getMonth()+1)).slice(-2) ;
							let day2 = ('00'+nextStartDate.getDate()).slice(-2) ;
							let nextStartDateId=nextStartDate.getFullYear()+'-'+month2+'-'+day2;
							
							let airArea=document.createElement('span')
							airArea.classList.add('schedule_list')
							document.getElementById(nextStartDateId).insertBefore(airArea,document.getElementById(nextStartDateId).firstChild)
							nextStartDate=new Date(nextStartDate.setDate(nextStartDate.getDate()+1));
						}

					}

					
						
			let scheduleLink = document.createElement('a')
			scheduleLink.setAttribute('id',monthlySchedule_id[i].value+'link')
			scheduleLink.href="http://localhost:8080/scheduleDetail?id="+monthlySchedule_id[i].value
			addScheduleArea.appendChild(scheduleLink)
			scheduleDocument.classList.add('schedule_list')
			scheduleDocument.innerText=monthlySchedule_title[i].value
			document.getElementById(monthlySchedule_id[i].value+'link').appendChild(scheduleDocument)

			//スケジュールの種類によって色を変える
			if(monthlySchedule_kinds[i].value =='仕事'){
				scheduleDocument.style.backgroundColor="green";
			}else if(monthlySchedule_kinds[i].value == 'プライベート'){
				scheduleDocument.style.backgroundColor="cyan";
			}else{
				scheduleDocument.style.backgroundColor="#acff7f";
			}
			
	}

}