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
var all_booking_list_url = 'booking/get_all_booking_list'
var ctx = document.getElementById('myChart');



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

line_chart_btn = document.getElementById("refresh_line_chart")

line_chart_btn.addEventListener('click', () => {
	if ($('#endDate').val() != undefined && $('#endDate').val() != undefined) {
		booking_number_url = `/booking/get_reservation_number?sDate=${$('#startDate').val()}&eDate=${$('#endDate').val()}`
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
				data.forEach((d) => {
					myChart.data.datasets[0].data.push(d)
					myChart.update()
				})
				

				myChart.options.scales.y.max = maxVal + 3
				console.log(labels)
				console.log(data)
				//myChart.update()
			})
	} else {
		alert("날짜를 선택하세요!!")
	}


})


