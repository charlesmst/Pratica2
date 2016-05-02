var express = require('express');
var app = express();
var http = require('http').Server(app);
var url = require('url');


http.listen(3000, function () {
    console.log('listening on *:3000');
});
app.use('/', express.static(__dirname + '/'));
