function review() {
	const review_taste = document.getElementById('review_taste').value;
	const review_service = document.getElementById('review_service').value;
	const review_total = document.getElementById('review_total').value;
	alert("맛 = " + review_taste + " 서비스 = " + review_service + " 총평 = " + review_total);
	location.href = "main.html";
}

let today = new Date();

let year = today.getFullYear(); // 년도
let month = today.getMonth() + 1;  // 월
let date = today.getDate();  // 날짜
let day = today.getDay();  // 요일

let hours = today.getHours(); // 시
let minutes = today.getMinutes();  // 분
let seconds = today.getSeconds();  // 초
let milliseconds = today.getMilliseconds(); // 밀리초



var currentDate = year + '-' + month + '-' + date
var currentTime = hours + ':' + minutes + ':' + seconds


var review_btn = document.getElementById('reviewBtn')
review_btn.addEventListener('click', e => {
	point = 0;
	for(i = 0; i < 5; i++){
		if (document.getElementsByName('rating')[i].checked){
			point = 5 - i;
		}
	}
	document.getElementsByName('rating').forEach(e => {
		
	
	})
	var url = '/review/add_review?rid=1&date=' + currentDate + '&time=' + currentTime +
		'&content=' + document.getElementById("review_total").value + '&point=' + point
	fetch(url, { method: 'POST' })
		.then(d => d.json())
		.then(returnData => {
			if (returnData){
				alert("리뷰가 작성되었습니다!")
				location.href = "/";
			}
		})
})