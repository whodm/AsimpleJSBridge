(function(){
console.log("loaded");
if(window.webBridge){
return;
}
var bridgeMap = {};
var bridgeCallBack = {};

function registerHandler(handlerName,handler){
console.log(handlerName);
bridgeMap[handlerName] = handler;
}

function unregisterHandler(handlerName){
bridgeMap[handlerName].delete;
}

function handleMessage(handlerName,message){
console.log(handlerName);
var handler = bridgeMap[handlerName];
try{
handler(message,handlerName);
}catch (exception){
if (typeof console != 'undefined') {
console.log("没找到指定handler",handlerName);
}}
}

function bridgeSend(target,Message){
window.location.href = "control://"+target+"/"+Message;
}
var webBridge = window.webBridge = {
registerHandler:registerHandler,
unregisterHandler:unregisterHandler,
handleMessage:handleMessage,
bridgeSend:bridgeSend
}
})();