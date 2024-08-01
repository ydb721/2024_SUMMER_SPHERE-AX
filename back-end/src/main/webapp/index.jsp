<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>WebSocket Test</title>
    <script type="text/javascript">
        // websocket client code		// WEB_PORT : 상대의 웹 포트번호. 주로 8080 사용
        var ws;

        function startWebSocket() {
        	var SERVER_IP = "<%= System.getenv("SERVER_IP") %>";
        	var WEB_PORT = "<%= System.getenv("WEB_PORT") %>";
            ws = new WebSocket("ws://" + SERVER_IP + ":" + WEB_PORT + "/testweb/websocket");

         	// WebSocket 연결이 열리면 실행
            ws.onopen = function() {
                document.getElementById("status").innerHTML = "WebSocket is connected.";
                ws.send("Request latest weather data"); // 서버로 메시지 전송
            };

         	// 서버로부터 메시지를 받으면 실행
            ws.onmessage = function(event) {
                try {
                    const json = JSON.parse(event.data); // 받은 데이터를 JSON으로 파싱
                    document.getElementById("messages").innerHTML =
                        "Message from server: " + JSON.stringify(json) + "<br/>";
                } catch (e) {
                    document.getElementById("messages").innerHTML =
                        "Error parsing JSON: " + e.message + "<br/>";
                }
            };

            // WebSocket 오류가 발생하면 실행
            ws.onerror = function(error) {
                document.getElementById("status").innerHTML = "WebSocket error: " + error.message;
            };

         	// WebSocket 연결이 닫히면 실행
            ws.onclose = function() {
                document.getElementById("status").innerHTML = "WebSocket is closed.";
            };
        }
    </script>
</head>
<body onload="startWebSocket()"> <!-- 페이지 로드 시 WebSocket 연결 시작 -->
    <h1>WebSocket Test Page</h1>
    <div id="status">Connecting...</div> <!-- WebSocket 상태를 표시 -->
    <div id="messages"></div> <!-- 서버로부터 받은 메시지를 표시 -->
</body>
</html>
