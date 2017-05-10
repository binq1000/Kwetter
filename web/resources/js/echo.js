/**
 * Created by Nekkyou on 8-5-2017.
 */
//WebSocket placeholder
var websocket=null;

function getWsUri() {
    //Name of endpoint here.
    var wsUriSuffix = "receive";
    var wsUri = document.URL.replace("http", "ws");
    if(wsUri.match("allkweets.xhtml$")){
        wsUri=wsUri.replace("allkweets.xhtml", wsUriSuffix);
    } else {
        wsUri += wsUri.match("/$") ? "receive" : "/receive";
    }

    return wsUri;
}


function connect() {
    //construct ws URI
    var wsURI = getWsUri();
    //create socket
    websocket = new WebSocket(wsURI);
    //atach event listeneres
    websocket.onopen = function() {
        log('CONNECTED');
        doSend("WebSockets rock");
    };
    websocket.onclose = function() {
        log('DISCONNECTED');
    };
    websocket.onmessage = function(evt) {
        //convert json to javascript object
        var message = JSON.parse(evt.data);

        var div = document.createElement("p");
        var node = document.createTextNode(message);

        div.appendChild(node);

        var element = document.getElementById("kweets");
        element.appendChild(div);

        //write message.text to screen
        log('I: ' + message.text);
    };
    websocket.onerror = function(event) {
        log('ERROR: ' + event.data);
    };
}

function onLoad() {
    log('OnLoad');
    connect();
}

function doSend(text) {
    log(text);
    var message = JSON.stringify({'text': text});
    websocket.send(message);
}

function log(message) {
    console.log(message);
}