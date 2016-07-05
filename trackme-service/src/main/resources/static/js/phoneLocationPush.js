var stompClient = null;

function connect() {
    var socket = new SockJS('/debug');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/phone-location-change', function(location){
            changePhoneLocation(JSON.parse(location.body).content);
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function changePhoneLocation(message) {
    console.log("MES: " + message);
}
