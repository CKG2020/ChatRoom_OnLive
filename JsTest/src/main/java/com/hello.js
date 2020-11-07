// console.log(`多行
// 字符串
// 测试`);


// var name = '小明';
// var age = 20;
// var message = `你好, ${name}, 你今年${age}岁了!`;
// alert(message);

'use strict';

var s = 'Hello';

function greet(name) {
    console.log(s + ', ' + name + '!');
}

module.exports = greet;
// 它的意思是，把函数greet作为模块的输出暴露出去，这样其他模块就可以使用greet函数了。