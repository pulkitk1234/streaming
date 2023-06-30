var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/stomp-endpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/streaming', function (greetings) {
            showGreeting(JSON.parse(greetings.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {

    var date= new Date();
    var time=date.toISOString().slice(0,23).replace('T',' ').toString();
    var data={
        message:$("#message").val(),
        time

    }
    stompClient.send("/app/stream", {}, JSON.stringify(data));
}


function showGreeting({message,time}) {
    $("#greetings").append("<tr><td>" + message +'     ' +time + "</td></tr>");
    console.log("my message " ,message)
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
}); 

