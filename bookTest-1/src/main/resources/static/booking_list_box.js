/**
 * 
 */
window.addEventListener('load', () => {
	console.log(1)
	document.getElementById('booking_list_box').innerHTML =
		`
		<input type="date" id="selectDate" name="조회날짜" value="2021-05-31"
			min="2020-12-31" max="2022-12-31">			
		<button id="get_reservation_list">조회</button>
    	<table id = "table1"></table>
    			
`
	a = document.getElementById('get_reservation_list')
	a.addEventListener("click", () => {
		for (i = 1; i < tableArr.length; i++) {
			for (j = 1; j < 14; j++) {
				tableArr[i][j].className = ""
				tableArr[i][j].innerText = ""
			}
		}
		url = '/booking/get_booking_list?' +
			'date=' + document.getElementById("selectDate").value
		fetch(url)
			.then(e => e.json())
			.then(data => {
				console.log(data)
				index = 0
				data.forEach(e => {
					for (i = 1; i < 14; i++) {
						if (tableArr[0][i].innerText == e.time.slice(0, 5)) {
							tableArr[e.table.number][i].className += "bookingSection"
							tableArr[e.table.number][i].innerText = index;
							tableArr[e.table.number][i + 1].className += "bookingSection"
							tableArr[e.table.number][i + 1].innerText = index;
							tableArr[e.table.number][i + 2].className += "bookingSection"
							tableArr[e.table.number][i + 2].innerText = index;
							tableArr[e.table.number][i + 3].className += "bookingSection"
							tableArr[e.table.number][i + 3].innerText = index;
							index++;
						}
					}

				})
			})
	})


	table = document.getElementById("table1")
	window.tableArr = []
	row = []
	r = table.insertRow();
	c = r.insertCell(0);
	row.push(c)
	c.innerText = "table \\ time"

	startTime = new Date("2021-05-05 18:00")
	for (i = 0; i < 13; i++) {
		c = r.insertCell(i + 1);
		row.push(c)
		time = String(startTime.getHours() + Math.floor(i / 2)) + ":" +
			("00" + String(startTime.getMinutes() + 30 * (i % 2))).slice(-2)
		c.innerText = time
		c.className = "basicTable"
	}
	tableArr.push(row)

	fetch('/get_table')
		.then(e => e.json())
		.then(data => {

			console.log(data)
			data.forEach(e => {
				row = []
				r = table.insertRow();
				c = r.insertCell(0);
				row.push([c])
				c.innerText = e[1];
				for (i = 0; i < 13; i++) {
					c = r.insertCell(i + 1);
					row.push(c)
				}
				tableArr.push(row)
			})

		})
})






