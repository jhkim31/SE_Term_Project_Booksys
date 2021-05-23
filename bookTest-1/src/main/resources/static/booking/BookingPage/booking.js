function booking(){
    const userId = document.getElementById('userId').value;
    const userMenu= document.getElementById('userMenu').value;
    const tableNum = document.getElementById('tableNum').value;
    const startTime = document.getElementById('startTime').value;
    alert("아이디 = "+userId+" 메뉴 = "+userMenu +" 테이블 = "+tableNum +" 시간 = "+startTime);
    location.href = "main.html";
}