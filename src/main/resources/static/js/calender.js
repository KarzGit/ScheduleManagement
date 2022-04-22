"use strict"
const weeks = ['日', '月', '火', '水', '木', '金', '土']

const date = new Date()
let year = date.getFullYear()
let month = date.getMonth() + 1
let day = date.getDate()

let dayCount = 1 // 日にちのカウント
let calenderHtml = '' // HTMLを組み立てる変数
let scheduleList=[] //登録されているスケジュールを格納するリスト
createCalender(year,month)
//今日の日付にclass"today"を追加
let today=document.getElementById(year+'/'+month+'/'+day)
today.classList.add("today")
//スケジュールリストに登録されたスケジュールを格納
let schedules= document.getElementsByClassName('scheduleList')
for(let i =0 ; i<schedules.length; i++){
	scheduleList.unshift(schedules[i].value)
}
console.log(scheduleList[0])


document.getElementById('prev').addEventListener('click',function(){
	
       if (month == 1) {
            month = 12
            year --
        } else {
            month --
        }
        
        console.log(month)
        console.log(year) 

        createCalender(year, month) 
       
        let tableElement = document.getElementById("calender").querySelectorAll('table')
       
     if(tableElement.length>1){
	for(let i=1 ;i<=tableElement.length; i++){
	
		tableElement[i].remove();
	}
}

       

    })



function createCalender(year,month){
document.getElementById('calender').innerHTML = ' ';
console.log("calender内を空にする")
		// 曜日の行を作成
console.log("テーブル作成")
calenderHtml += '<table>'
calenderHtml += '<tbody id="calenderBody">'
calenderHtml += '<tr id="week">'
for (let i = 0; i < weeks.length; i++) {
    calenderHtml += '<th>' + weeks[i] + '</th>'
}
calenderHtml +='</tr>'
calenderHtml +='</tbody>'
calenderHtml +='</table>'
console.log("テーブルをdivのなかへ")
document.getElementById('calender').innerHTML = calenderHtml;
console.log("曜日行作成完了")

	
    const startDate = new Date(year, month - 1, 1) // 月の最初の日を取得
    const endDate = new Date(year, month,  0) // 月の最後の日を取得
    const endDayCount = endDate.getDate() // 月の末日
    const lastMonthEndDate = new Date(year, month - 1, 0) // 前月の最後の日の情報
    const lastMonthendDayCount = lastMonthEndDate.getDate() // 前月の末日
    const startDay = startDate.getDay() // 月の最初の日の曜日を取得
    let dayCount = 1 // 日にちのカウント
    let schedulebox;
    let scheduleboxList = []
  for (let w = 0; w < 6; w++) {
  
    let days=document.createElement('tr')
    days.classList.add('day_count')
     let schedulearea = document.createElement('tr')
     schedulearea.classList.add('schedule_area')

    for (let d = 0; d < 7; d++) {
        if (w == 0 && d < startDay) {
            // 1行目で1日の曜日の前
            days.appendChild(document.createElement('td'))
            schedulearea.appendChild(document.createElement('td'))
        } else if (dayCount > endDayCount) {
            // 末尾の日数を超えた
             days.appendChild(document.createElement('td'))
             schedulearea.appendChild(document.createElement('td'))
        } else {
	  		let whileDays=document.createElement('td')
	  		let schedulebox=document.createElement('td')
	  		whileDays.setAttribute("id",year+'/'+month+'/'+dayCount)
	  		schedulebox.setAttribute("id",year+'-'+month+'-'+dayCount)
	  		whileDays.append(dayCount)
	  		days.appendChild(whileDays)
	  		schedulearea.appendChild(schedulebox)
            dayCount++
        }
    }
   
    document.getElementById('calenderBody').appendChild(days)
    document.getElementById('calenderBody').appendChild(schedulearea)
}
console.log("カレンダー作成完了")
};


    
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

document.getElementById("yearMonthSelector").addEventListener('click',function(){
	let year = ''
	if(document.getElementById('year').value == ""){
		 year = document.getElementById('year').placeholder
	}else{
	year = document.getElementById('year').value
	}
	const selectBox = document.getElementById('month')
	const month = selectBox.selectedIndex+1
	
	console.log(year)
	console.log(month)
	createCalender(year,month)
	let tableElement = document.getElementById("calender").querySelectorAll('table')
       
     if(tableElement.length>1){
	for(let i=1 ;i<=tableElement.length; i++){
	
		tableElement[i].remove();
	}
}
})
	
    
    


