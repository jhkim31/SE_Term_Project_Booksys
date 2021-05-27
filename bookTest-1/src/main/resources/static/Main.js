/**
 * 
 */	

d = document.getElementById('getTime')
d.addEventListener('click', () => {
	val = document.getElementById('appt').value
	if (val <= "22:00" && val >= "18:00" && val.slice(3, 5) % 30 == 0) {
		console.log(document.getElementById('appt').value + ":00")
	}

})