bookingBtn = document.getElementById("bookingBtn")
bookingBtn.addEventListener('click', () => {
	url = '/reservation/new_reservation?' +
		'covers=' + document.getElementById('bookingCovers').value +
		'&date=' + document.getElementById('bookingDate').value +
		'&time=' + document.getElementById('bookingTime').value +
		'&tno=' + document.getElementById('bookingTable').value +
		'&name=' + document.getElementById('bookingUserName').value +
		'&phone=' + document.getElementById('bookingPhoneNumber').value

	fetch(url, {
		method: 'POST'
	})
		.then(e => e.json())
		.then(data => console.log(data))
})