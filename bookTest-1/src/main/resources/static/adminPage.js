/**
 * 
 */

function getThisWeek() {
	var value = [];
	var formatDate = function(date) {
		var myMonth = date.getMonth() + 1;
		var myWeekDay = date.getDate();

		var addZero = function(num) {
			if (num < 10) {
				num = "0" + num;
			}
			return num;
		}
		var md = addZero(myMonth) + "-" + addZero(myWeekDay);

		return md;
	}

	var now = new Date();
	var nowDayOfWeek = now.getDay();
	var nowDay = now.getDate();
	var nowMonth = now.getMonth();
	var nowYear = now.getYear();
	nowYear += (nowYear < 2000) ? 1900 : 0;
	var weekStartDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek);
	var weekEndDate = new Date(nowYear, nowMonth, nowDay + (6 - nowDayOfWeek));
	value.push(nowYear + "-" + formatDate(weekStartDate));
	value.push(nowYear + "-" + formatDate(weekEndDate));

	return value;
}
d = getThisWeek();
var booking_number_url = `/booking/get_reservation_number?sDate=${d[0]}&eDate=${d[1]}`
var booking_price_url = `/booking/get_reservation_price?sDate=${d[0]}&eDate=${d[1]}`
var all_booking_list_url = 'booking/get_all_booking_list'
var all_review_list_url = '/review/get_review_list'
var ctx = document.getElementById('myChart');
var ctx2 = document.getElementById('myChart2');



fetch(booking_number_url)
	.then(e => e.json())
	.then(returnData => {
		console.log(returnData)
		var labels = []
		var data = []
		maxVal = 0;
		for (i = 0; i < returnData.length; i++) {
			labels.push(returnData[i++])
			if (maxVal < returnData[i]) {
				maxVal = returnData[i]
			}
			data.push(returnData[i])
		}
		window.myChart = new Chart(ctx, {
			type: 'line',
			data: {
				labels: labels,
				datasets: [{
					//label: '# of Votes',
					data: data,
					backgroundColor: [
						'rgba(255, 99, 132, 0.5)'

					],
					borderColor: [
						'rgba(255, 99, 132, 1)'

					],
					borderWidth: 1
				}]
			},
			options: {
				scales: {
					y: {
						max: maxVal + 3,
						min: 0,
						ticks: {
							stepSize: 1
						}
					}
				}
			}
		});

	})

fetch(booking_price_url)
	.then(e => e.json())
	.then(returnData => {
		console.log(returnData)
		var labels = []
		var data = []
		maxVal = 0;
		for (i = 0; i < returnData.length; i++) {
			labels.push(returnData[i++])
			if (maxVal < returnData[i]) {
				maxVal = returnData[i]
			}
			data.push(returnData[i])
		}
		window.myChart2 = new Chart(ctx2, {
			type: 'line',
			data: {
				labels: labels,
				datasets: [{
					//label: '# of Votes',
					data: data,
					backgroundColor: [
						'rgba(255, 99, 132, 0.5)'

					],
					borderColor: [
						'rgba(255, 99, 132, 1)'

					],
					borderWidth: 1
				}]
			},
			options: {
				scales: {
					y: {
						max: maxVal + 10000,
						min: 0,
						ticks: {
							stepSize: 10000
						}
					}
				}
			}
		});

	})






fetch(all_booking_list_url)
	.then(e => e.json())
	.then(returnData => {
		console.log(returnData)
		var data = []
		for (i = 0; i < returnData.length; i++) {
			var tmp = []
			tmp.push(returnData[i].id)
			if (returnData[i].customer != undefined) {
				tmp.push(returnData[i].customer.name)
				tmp.push(returnData[i].customer.phoneNumber)
			} else {
				tmp.push("walkIn")
				tmp.push("walkIn")
			}

			tmp.push(returnData[i].covers)
			tmp.push(returnData[i].date)
			tmp.push(returnData[i].time)
			tmp.push(returnData[i].tableNumber)
			data.push(tmp)
		}

		$(document).ready(function() {
			$('#dataTable').DataTable({
				data: data
			});
		})
	})


fetch(all_review_list_url)
	.then(e => e.json())
	.then(returnData => {
		console.log(returnData)
		var data = []
		for (i = 0; i < returnData.length; i++) {
			var tmp = []
			tmp.push(returnData[i].rid)
			tmp.push(returnData[i].user)
			tmp.push(returnData[i].date)
			tmp.push(returnData[i].time)
			tmp.push(returnData[i].content)
			tmp.push(returnData[i].point)
			data.push(tmp)
		}

		$(document).ready(function() {
			$('#reviewTable').DataTable({
				data: data
			});
		})
	})


line_chart_btn = document.getElementById("refresh_line_chart")

line_chart_btn.addEventListener('click', () => {
	if ($('#endDate').val() != undefined && $('#endDate').val() != undefined) {
		var booking_number_url = `/booking/get_reservation_number?sDate=${$('#startDate').val()}&eDate=${$('#endDate').val()}`
		var booking_price_url = `/booking/get_reservation_price?sDate=${$('#startDate').val()}&eDate=${$('#endDate').val()}`

		fetch(booking_number_url)
			.then(e => e.json())
			.then(returnData => {
				var labels = []
				var data = []
				maxVal = 0;
				for (i = 0; i < returnData.length; i++) {
					labels.push(returnData[i++])
					if (maxVal < returnData[i]) {
						maxVal = returnData[i]
					}
					data.push(returnData[i])
				}

				myChart.data.labels = labels
				myChart.data.datasets[0].data = []
				myChart.options.scales.y.max = maxVal + 3
				data.forEach((d) => {
					myChart.data.datasets[0].data.push(d)
					myChart.update()
				})
			})

		fetch(booking_price_url)
			.then(e => e.json())
			.then(returnData => {
				var labels = []
				var data = []
				maxVal = 0;
				for (i = 0; i < returnData.length; i++) {
					labels.push(returnData[i++])
					if (maxVal < returnData[i]) {
						maxVal = returnData[i]
					}
					data.push(returnData[i])
				}

				myChart2.data.labels = labels
				myChart2.data.datasets[0].data = []
				myChart2.options.scales.y.max = maxVal + 10000
				data.forEach((d) => {
					myChart2.data.datasets[0].data.push(d)
					myChart2.update()
				})
			})
	} else {
		alert("날짜를 선택하세요!!")
	}


})


var delete_review_number = document.getElementById("delete_review_number");
var review_delete = document.getElementById("review_delete");

review_delete.addEventListener('click', e => {
	fetch('/review/delete_review?rid=' + delete_review_number.value, { method: 'POST' })
		.then(e => e.json())
		.then(returnData => {
			if (returnData){
				alert("리뷰가 삭제되었습니다!");
			}	
		})

})
