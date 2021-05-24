function review(){
    const review_taste = document.getElementById('review_taste').value;
    const review_service= document.getElementById('review_service').value;
    const review_total = document.getElementById('review_total').value;
    alert("맛 = "+review_taste+" 서비스 = "+ review_service +" 총평 = "+  review_total);
    location.href = "main.html";
}