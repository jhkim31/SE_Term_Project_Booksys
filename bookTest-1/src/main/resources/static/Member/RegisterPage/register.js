function register(){
    const userName = document.getElementById('userName').value;
    const userId = document.getElementById('userId').value;
    const userPass = document.getElementById('userPass').value;
    const userPhoneNumber = document.getElementById('userPhoneNumber').value;
    alert("아이디 = "+userId+" 비밀번호 = "+userPass +" 이름 = "+userName +" 전화번호 = "+userPhoneNumber);
    location.href = "login.html";
}