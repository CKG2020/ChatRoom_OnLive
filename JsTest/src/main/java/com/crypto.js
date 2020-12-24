// const crypto = require('crypto');
//
// const hmac = crypto.createHmac('sha256', 'secret-key');
//
// hmac.update('Hello, world!');
// hmac.update('Hello, nodejs!');
//
// console.log(hmac.digest('hex')); // 80f7e22570...


// / 导入WebSocket模块:
// const WebSocket = require('ws');
//
// // 引用Server类:
// const WebSocketServer = WebSocket.Server;
//
// // 实例化:
// const wss = new WebSocketServer({
//     port: 3000
// });
//
// wss.on('connection', function (ws) {
//     console.log(`[SERVER] connection()`);
//     ws.on('message', function (message) {
//         console.log(`[SERVER] Received: ${message}`);
//         ws.send(`ECHO: ${message}`, (err) => {
//             if (err) {
//                 console.log(`[SERVER] error: ${err}`);
//             }
//         });
//     })
// });



// const Koa = require('koa');
//
// // 创建一个Koa对象表示web app本身:
// const app = new Koa();
//
// // 对于任何请求，app将调用该异步函数处理请求：
// app.use(async (ctx, next) => {
//     await next();
//     ctx.response.type = 'text/html';
//     ctx.response.body = '<h1>Hello, koa2!</h1>';
// });
//
// // 在端口3000监听:
// app.listen(3000);
// console.log('app started at port 3000...');