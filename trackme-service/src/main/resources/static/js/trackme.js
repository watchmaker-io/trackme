var phoneLocation;

function changeLocation(locationParam) {
    phoneLocation = locationParam;
}

var map;

function reloadMap() {
    if (phoneLocation) {
        var currentLocation = new google.maps.LatLng(phoneLocation.latitude, phoneLocation.longitude);
        map = new google.maps.Map(document.getElementById('map'), {
            center: currentLocation,
            zoom: 8
        });

        var coordInfoWindow = new google.maps.InfoWindow();
        coordInfoWindow.setContent(createInfoWindowContent(currentLocation, map.getZoom()));
        coordInfoWindow.setPosition(currentLocation);
        coordInfoWindow.open(map);

        map.addListener('zoom_changed', function() {
            coordInfoWindow.setContent(createInfoWindowContent(currentLocation, map.getZoom()));
            coordInfoWindow.open(map);
        });

    } else {
        map = new google.maps.Map(document.getElementById('map'), {
            center: new google.maps.LatLng(52.259, 21.020),
            zoom: 8
        });
    }
}

var TILE_SIZE = 256;

function createInfoWindowContent(latLng, zoom) {
    var scale = 1 << zoom;

    var worldCoordinate = project(latLng);

    var pixelCoordinate = new google.maps.Point(
        Math.floor(worldCoordinate.x * scale),
        Math.floor(worldCoordinate.y * scale));

    var tileCoordinate = new google.maps.Point(
        Math.floor(worldCoordinate.x * scale / TILE_SIZE),
        Math.floor(worldCoordinate.y * scale / TILE_SIZE));

    return [
        "Telefon: " + phoneLocation.name,
        "(" + phoneLocation.latitude + ", " + phoneLocation.longitude + ")",
        "Wysokość: " + phoneLocation.altitude,
        "Prędkość: " + phoneLocation.speed + "m/s",
        "",
        phoneLocation.time + "@" + phoneLocation.accuracy
    ].join('<br>');
}

function project(latLng) {
    var siny = Math.sin(latLng.lat() * Math.PI / 180);

    // Truncating to 0.9999 effectively limits latitude to 89.189. This is
    // about a third of a tile past the edge of the world tile.
    siny = Math.min(Math.max(siny, -0.9999), 0.9999);

    return new google.maps.Point(
        TILE_SIZE * (0.5 + latLng.lng() / 360),
        TILE_SIZE * (0.5 - Math.log((1 + siny) / (1 - siny)) / (4 * Math.PI)));
}

var stompClient = null;

function reloadWs() {
    disconnect();
    connect();
}

function connect() {
    var socket = new SockJS('/hello');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/phoneLocationChange', function(location){
            changeLocation(JSON.parse(location.body));
            reloadMap();
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}