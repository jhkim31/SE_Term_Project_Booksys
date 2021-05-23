function login(){
    const userId = document.getElementById('userId').value;
    const userPass = document.getElementById('userPass').value;
    alert("아이디 = "+userId+" 비밀번호 = "+userPass);
    location.href = "main.html";
}