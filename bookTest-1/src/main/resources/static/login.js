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

