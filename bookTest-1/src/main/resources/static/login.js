login_btn = document.getElementById("loginBtn")
login_btn.addEventListener('click', () => {
	url = '/login/loginRequest?' +
		'userId=' + document.getElementById('userId').value +
		'&pw=' + document.getElementById('userPass').value 

	fetch(url, { method: 'POST' })
		.then(e => e.json())
		.then(data => {
			console.log(data)
			if (data){
				location.href = "/";
			} else {
				alert("로그인에 실패하셨습니다");
			}
		})
})