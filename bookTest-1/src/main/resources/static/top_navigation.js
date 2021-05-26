/**
 * 
 */
window.addEventListener('load', () => {
	var session = ""
	var admin_page = "";
	var login = ""
	fetch('/session_check')
		.then(e => {
			return Promise.all([e.ok, e.text()])
		})
		.then(([responseOk, returnData]) => {
			console.log(returnData);
			if (returnData == "false") {
				login = "login"
				session = "로그인이 필요합니다!"
			} else {
				if (returnData == "admin") {
					admin_page = "<a class='nav-link' href='/admin_page'>ADMIN_PAGE</a>";
				}
				login = "logout"
				session = returnData + "님 안녕하세요!"
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
				<li class="nav-item active"><a class="nav-link" href="/">Home</a>
				</li>

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
			<a class="nav-link" id="logoutBtn" href = "#">${login}</a>
		</div>
	</nav> 
	`
			document.getElementById("logoutBtn").addEventListener('click', () => {
				if (document.getElementById("logoutBtn").value == "login") {
					fetch("/logout", { method: 'POST' });
					alert("로그아웃 되었습니다!");
				}
				location.href = "/login_page";
			})
		})




})
