// var stompClient = null;
// function setConnection(connected) {
//     $("#connect").prop("disabled",connected);
//     $("#disconnect").prop("disabled",!connected);
//     if(connected){
//         $("#conversation").show();
//         $("#chat").show();
//     }
//     else {
//         $("#conversation").hide();
//         $("#chat").hide();
//     }
//     $("#greetings").html("");
// }
// function connect() {
//     window.alert(" begin connect");
//     if(!$("#name").val()){
//         return;
//     }
//
//     var socket = new SockJS('/chat')
//     stompClient = Stomp.over(socket);
//     stompClient.connect({},function (frame) {
//         setConnection(true);
//         stompClient.subscribe('/topic/greetings',function (greeting) {
//             showGreeting(JSON.parse(greeting.body));
//         });
//     });
// }
//
// function disconnet() {
//     if(stompClient != null){
//         stompClient.disconnect();
//     }
//     setConnected(false);
// }
//
// function sendName() {
//     stompClient.sent("/app/hello",{},
//         JSON.stringify({'name':$("#name").val(),'content':$("#content").val()}));
// }
//
// function showGreeting(message) {
//     $("greetings").append("<div>"+message.name+":"+message.content+"</div>")
// }
//
// $(function () {
//         $("#connect").click(function () {
//             connect();
//         });
//         $("#disconnect").click(function () {
//             disconnet();
//         });
//         $("#send").click(function () {
//             sendName();
//         });
// });