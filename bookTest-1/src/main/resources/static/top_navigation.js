/**
 * 
 */
window.addEventListener('load', () => {
	document.getElementById('top-navigation-bar').innerHTML = `

<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="/">레스토랑 예약 시스템</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link"
						href="/menu">메뉴판 보기</a></li>

					<li class="nav-item"><a class="nav-link"
						href="/booking/show_booking_list">예약 조회</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/booking/booking_page">예약 하기</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/booking/change_booking">예약 변경</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/booking/cancel_booking">예약 취소</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/review">리뷰 작성</a></li>
						<li class="nav-item"><a class="nav-link"><button id = "logoutBtn">로그 아웃</button></a></li>
				</ul>
			</div>
		</div>
		
	</nav>
	`
	
	document.getElementById("logoutBtn").addEventListener('click', () => {
		fetch("/logout", { method: 'POST' });
		alert("로그아웃 되었습니다!");
		location.href = "/login_page";
	})
})
