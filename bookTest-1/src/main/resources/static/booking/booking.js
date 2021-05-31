

var totalPrice = document.getElementById('totalPrice')
var menu1 = document.getElementById('menu1')
var menu1_price = document.getElementById('menu1_price')

var menu2 = document.getElementById('menu2')
var menu2_price = document.getElementById('menu2_price')

var menu3 = document.getElementById('menu3')
var menu3_price = document.getElementById('menu3_price')

var menu4 = document.getElementById('menu4')
var menu4_price = document.getElementById('menu4_price')

var menu5 = document.getElementById('menu5')
var menu5_price = document.getElementById('menu5_price')

var menu6 = document.getElementById('menu6')
var menu6_price = document.getElementById('menu6_price')

var menu7 = document.getElementById('menu7')
var menu7_price = document.getElementById('menu7_price')

var menu8 = document.getElementById('menu8')
var menu8_price = document.getElementById('menu8_price')

bookingBtn = document.getElementById("bookingBtn")
bookingBtn.addEventListener('click', () => {
	url = '/reservation/new_reservation?' +
		'covers=' + document.getElementById('bookingCovers').value +
		'&date=' + document.getElementById('bookingDate').value +
		'&time=' + document.getElementById('bookingTime').value + ":00" +
		'&tno=' + document.getElementById('bookingTable').value.slice(0, document.getElementById('bookingTable').value.length - 5) +
		'&name=' + document.getElementById('bookingUserName').value +
		'&phone=' + document.getElementById('bookingPhoneNumber').value +
		'&menu1=' + menu1.value +
		'&menu2=' + menu2.value +
		'&menu3=' + menu3.value +
		'&menu4=' + menu4.value +
		'&menu5=' + menu5.value +
		'&menu6=' + menu6.value +
		'&menu7=' + menu7.value +
		'&menu8=' + menu8.value +
		'&totalPrice=' + totalPrice.innerText

	fetch(url, {
		method: 'POST'
	})
		.then(e => e.json())
		.then(data => {
			if (data == true){
				alert("예약이 추가되었습니다!")
				location.href = "/booking/booking_page";
			} else {
				alert("예약을 다시 확인하여 주십시오")
			}
		})
})

var bookingDate = document.getElementById('bookingDate')
bookingDate.addEventListener('change', e => {
	console.log(1)
	document.getElementById('booking_list_box').innerHTML =
		`<table id = "table1"></table>`
	table = document.getElementById("table1")
	window.tableArr = []

	for (i = 1; i < tableArr.length; i++) {
		for (j = 1; j < 14; j++) {
			tableArr[i][j].className = ""
			tableArr[i][j].innerText = ""
		}
	}
	url = '/booking/get_booking_list?' +
		'date=' + bookingDate.value
	fetch(url)
		.then(e => e.json())
		.then(data => {
			console.log(data)
			index = 0
			data.forEach(e => {
				for (i = 1; i < 14; i++) {
					if (tableArr[0][i].innerText == e.time.slice(0, 5)) {
						tableArr[e.table.number][i].className = "bookingSection"
						tableArr[e.table.number][i].innerText = index;
						tableArr[e.table.number][i + 1].className = "bookingSection"
						tableArr[e.table.number][i + 1].innerText = index;
						tableArr[e.table.number][i + 2].className = "bookingSection"
						tableArr[e.table.number][i + 2].innerText = index;
						tableArr[e.table.number][i + 3].className = "bookingSection"
						tableArr[e.table.number][i + 3].innerText = index;
						index++;
					}
				}

			})
		})

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

function totalPrice_update() {
	totalPrice.innerText =
		parseInt(menu1_price.innerText * menu1.value) +
		parseInt(menu2_price.innerText * menu2.value) +
		parseInt(menu3_price.innerText * menu3.value) +
		parseInt(menu4_price.innerText * menu4.value) +
		parseInt(menu5_price.innerText * menu5.value) +
		parseInt(menu6_price.innerText * menu6.value) +
		parseInt(menu7_price.innerText * menu7.value) +
		parseInt(menu8_price.innerText * menu8.value)
}
menu1.addEventListener('change', e => {
	if (menu1.value < 0) {
		alert('0 이하로 내려갈 수 없습니다');
		menu1.value = 0
	} else {
		totalPrice_update()
	}
})

menu2.addEventListener('change', e => {
	if (menu2.value < 0) {
		alert('0 이하로 내려갈 수 없습니다');
		menu2.value = 0
	} else {
		totalPrice_update()
	}
})

menu3.addEventListener('change', e => {
	if (menu3.value < 0) {
		alert('0 이하로 내려갈 수 없습니다');
		menu3.value = 0
	} else {
		totalPrice_update()
	}
})

menu4.addEventListener('change', e => {
	if (menu4.value < 0) {
		alert('0 이하로 내려갈 수 없습니다');
		menu4.value = 0
	} else {
		totalPrice_update()
	}
})

menu5.addEventListener('change', e => {
	if (menu5.value < 0) {
		alert('0 이하로 내려갈 수 없습니다');
		menu5.value = 0
	} else {
		totalPrice_update()
	}
})

menu6.addEventListener('change', e => {
	if (menu6.value < 0) {
		alert('0 이하로 내려갈 수 없습니다');
		menu6.value = 0
	} else {
		totalPrice_update()
	}
})

menu7.addEventListener('change', e => {
	if (menu7.value < 0) {
		alert('0 이하로 내려갈 수 없습니다');
		menu7.value = 0
	} else {
		totalPrice_update()
	}
})

menu8.addEventListener('change', e => {
	if (menu8.value < 0) {
		alert('0 이하로 내려갈 수 없습니다');
		menu8.value = 0
	} else {
		totalPrice_update()
	}
})

