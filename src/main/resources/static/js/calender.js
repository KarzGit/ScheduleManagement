"use strict"
const weeks = ['日', '月', '火', '水', '木', '金', '土']

const date = new Date()
let year = date.getFullYear()
let month = date.getMonth() + 1
let month2= ('00'+month).slice(-2) //一桁の数字に0を追加　1→01
let day = date.getDate()
let dayCount = 1 // 日にちのカウント
let calenderHtml = '' // HTMLを組み立てる変数
let schedule_title = document.getElementsByClassName("scheduleList_title")
let schedule_startDate = document.getElementsByClassName("scheduleList_startDate")
let schedule_startTime = document.getElementsByClassName("scheduleList_startTime")
let schedule_endDate = document.getElementsByClassName("scheduleList_endDate")
let schedule_endTime = document.getElementsByClassName("scheduleList_endTime")
let schedule_description = document.getElementsByClassName("scheduleList_description")
createCalender(year,month)
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
    let dayCount = 1 // 日にちのカウント
    
  for (let w = 0; w < 6; w++) {
  
    let days=document.createElement('tr')
    days.classList.add('day_count')
     let schedulearea = document.createElement('tr') //スケジュール欄の追加
     schedulearea.classList.add('schedule_area')

    for (let d = 0; d < 7; d++) {
        if (w == 0 && d < startDay) {
            // 1行目で1日の曜日の前
            days.appendChild(document.createElement('td'))
            schedulearea.appendChild(document.createElement('td'))　//スケジュール欄の追加
        } else if (dayCount > endDayCount) {
            // 末尾の日数を超えた
             days.appendChild(document.createElement('td'))
             schedulearea.appendChild(document.createElement('td'))//スケジュール欄の追加
        } else {
	  		let whileDays=document.createElement('td')
	  		let schedulebox=document.createElement('td')
	  		whileDays.setAttribute("id",year+'/'+month2+'/'+dayCount)
	  		schedulebox.setAttribute("id",year+'-'+month2+'-'+dayCount)
	  		//スケジュール開始日が一致するところに<span>を追加
	  		let scheduleDocument=document.createElement('span')
	  		for(let i =0; i<schedule_startDate.length; i++){
			if(schedule_startDate[i].value == schedulebox.getAttribute('id')){
				scheduleDocument.innerText=schedule_title[i].value　
				schedulebox.appendChild(scheduleDocument)
			}
	}
	  		whileDays.append(dayCount)
	  		days.appendChild(whileDays)
	  		schedulearea.appendChild(schedulebox)
            dayCount++
        }
    }
   
    document.getElementById('calenderBody').appendChild(days)
    document.getElementById('calenderBody').appendChild(schedulearea)
}

};


   

document.getElementById("yearMonthSelector").addEventListener('click',function(){
	let year = ''
	if(document.getElementById('year').value == ""){
		 year = document.getElementById('year').placeholder
	}else{
	year = document.getElementById('year').value
	}
	const selectBox = document.getElementById('month')
	const month = selectBox.selectedIndex+1
	

	createCalender(year,month)
	let tableElement = document.getElementById("calender").querySelectorAll('table')
       
     if(tableElement.length>1){
	for(let i=1 ;i<=tableElement.length; i++){
	
		tableElement[i].remove();
	}
}
})
	
    
    


