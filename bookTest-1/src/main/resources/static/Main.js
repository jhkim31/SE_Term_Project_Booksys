/**
 * 
 */	
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(37.272459, 126.997522), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };


var map = new kakao.maps.Map(mapContainer, mapOption); 

var markerPosition  = new kakao.maps.LatLng(37.272459, 126.997522); 


var marker = new kakao.maps.Marker({
    position: markerPosition
});

marker.setMap(map);