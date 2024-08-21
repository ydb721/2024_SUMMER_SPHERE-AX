<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>WebSocket Test</title>
</head>
<body>
    <h1>WebSocket Test Page</h1>
    <div id="status"></div>

    <script>
 		// websocket client code	// WEB_PORT : 상대의 웹 포트번호. 주로 8080 사용
	    var SERVER_IP = "<%= System.getenv("SERVER_IP") %>";
		var WEB_PORT = "<%= System.getenv("WEB_PORT") %>";
        var ws = new WebSocket("ws://" + SERVER_IP + ":" + WEB_PORT + "/testweb/websocket");

     	// 서버로부터 메시지를 받으면 실행
        ws.onmessage = function(event) {
            var message = JSON.parse(event.data); // 받은 데이터를 JSON으로 파싱
            if (message.status === "error") { // 오류 메시지 수신 시
            	document.getElementById("status").textContent = "Error parsing JSON";
            	window.location.href = "/testweb/error.jsp";
            } else {
                document.getElementById("status").textContent = "Message from server: " + event.data;
            }
        };

     	// WebSocket 연결이 열리면 실행
        ws.onopen = function() {
            console.log("WebSocket is connected.");
            document.getElementById("status").textContent = "WebSocket is connected.";
            ws.send("Request latest weather data"); // 서버로 메시지 전송
        };

     	// WebSocket 연결이 닫히면 실행
        ws.onclose = function() {
            console.log("WebSocket is closed.");
            document.getElementById("status").textContent = "WebSocket is closed.";
            window.location.href = "/testweb/error.jsp";
        };

     	// WebSocket 오류가 발생하면 실행
        ws.onerror = function(error) {
            console.error("WebSocket error:", error);
            window.location.href = "/testweb/error.jsp";
        };
    </script>
</body>
</html>
