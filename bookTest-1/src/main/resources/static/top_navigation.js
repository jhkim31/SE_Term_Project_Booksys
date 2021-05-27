/**
 * 
 */
Kakao.init('2c2f477c646e64788c0bd06c42954278');
Kakao.isInitialized();
function loginWithKakao() {
	Kakao.Auth.login({
		success: function(authObj) {
			console.log(authObj)
			Kakao.Auth.setAccessToken(authObj.access_token);
			Kakao.API.request({
				url: '/v2/user/me',
				data: {
					property_keys: ["kakao_account.profile", "kakao_account.gender"]
				},
				success: function(response) {
					console.log(response);
					url = '/login/kakao_login?' +
						'userId=' + response.id +
						'&userName=' + response.kakao_account.profile.nickname
					fetch(url, { method: 'POST' })
						.then(e => e.json())
						.then(data => {
							console.log(data)
							if (data) {
								location.href = "/";
							} else {
								alert("로그인에 실패하셨습니다");
							}
						})

				},
				fail: function(error) {
					console.log(error);
				}
			});

		},
		fail: function(err) {
			alert(JSON.stringify(err))
		},
	})
}
window.addEventListener('load', () => {

	var session = ""
	var admin_page = "";
	var login = ""
	fetch('/session_check')
		.then(e => e.json())
		.then((returnData) => {
			console.log(returnData);
			console.log(returnData[0]);
			console.log(returnData[1]);
			if (returnData.length == 0) {
				login = "login"
				session = "로그인이 필요합니다!"
			} else {
				if (returnData[0] == "admin") {
					admin_page = "<a class='nav-link' href='/admin_page'>ADMIN_PAGE</a>";
				}
				login = "logout"
				session = `<a class = 'nav-link' href = "mypage" >${returnData[1]}님 안녕하세요!</a>`
			}
			document.getElementById('top-navigation-bar').innerHTML = `
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="/">레스토랑 예약 시스템</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarText" aria-controls="navbarText"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarText">
			<ul class="navbar-nav mr-auto">


				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#"
					id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> 예약 </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="/booking/show_booking_list">예약
							조회</a> <a class="dropdown-item" href="/booking/booking_page">예약
							하기</a> <a class="dropdown-item" href="/booking/change_booking">예약
							변경</a> <a class="dropdown-item" href="/booking/cancel_booking">예약
							취소</a>
					</div></li>
				<li class="nav-item"><a class="nav-link" href="/menu">메뉴 보기</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="/review">리뷰
						작성</a></li>

			</ul>
			${admin_page} 
			<div class = "nav-item">${session}</div>
			<a class="nav-link" id="logoutBtn" href="#">${login}</a>
		</div>
	</nav> 
	`
			document.getElementById("logoutBtn").addEventListener('click', () => {
				if (document.getElementById("logoutBtn").innerText == "logout") {

					fetch("/logout", { method: 'POST' })
						.then(e => {
							console.log(e)
							location.href = "/";
						})

					alert("로그아웃 되었습니다!");
				} else {
					console.log('login')
					location.href = "/login_page";
				}
			})
		})
})
