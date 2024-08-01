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

            ws.onopen = function() {
                document.getElementById("status").innerHTML = "WebSocket is connected.";
                ws.send("Request latest weather data");
            };

            ws.onmessage = function(event) {
                try {
                    const json = JSON.parse(event.data);
                    document.getElementById("messages").innerHTML =
                        "Message from server: " + JSON.stringify(json) + "<br/>";
                } catch (e) {
                    document.getElementById("messages").innerHTML =
                        "Error parsing JSON: " + e.message + "<br/>";
                }
            };

            ws.onerror = function(error) {
                document.getElementById("status").innerHTML = "WebSocket error: " + error.message;
            };

            ws.onclose = function() {
                document.getElementById("status").innerHTML = "WebSocket is closed.";
            };
        }
    </script>
</head>
<body onload="startWebSocket()">
    <h1>WebSocket Test Page</h1>
    <div id="status">Connecting...</div>
    <div id="messages"></div>
</body>
</html>
