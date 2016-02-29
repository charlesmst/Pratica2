var express = require('express');
var app = express();
var http = require('http').Server(app);
var bodyParser = require('body-parser')

app.use( bodyParser.json() );       // to support JSON-encoded bodies
app.use(bodyParser.urlencoded({     // to support URL-encoded bodies
  extended: true
}));
 
http.listen(3000, function () {
    console.log('listening on *:3000');
});
app.use('/', express.static(__dirname + '/'));

var defaultResponse = {
	success:true
}
var data;
app.get('/api',function(req,res){
	res.json(data)	
})
app.post('/api',function(req,res){
	data.push(req.body)
	res.json(defaultResponse)
})
app.put('/api/:id',function(req,res){
	data.filter(function(item){
		return item.id == req.params.id
	}).forEach(function(item){
		item.name = req.body.name
	})
	
	res.json(defaultResponse)
})
app.delete('/api/:id',function(req,res){
	data = data.filter(function(item){
		return item.id != req.params.id
	})
	
	res.json(defaultResponse)
})
app.get('/api/:id',function(req,res){
	var item  = data.filter(function(item){
		return item.id == req.params.id
	})[0] || {};
	res.json(item)
})


 data = [
            {
                id: 601,
                name: 'Frozen joghurt'
            },
            {
                id: 602,
                name: 'Ice cream sandwitch',
                
            },
            {
                id: 603,
                name: 'Eclair',
                
            },
            {
                id: 604,
                name: 'Cupkake',
                
            },
            {
                id: 605,
                name: 'Gingerbread',
                
            },
            {
                id: 606,
                name: 'Jelly bean',
                
            },
            {
                id: 607,
                name: 'Lollipop',
                
            },
            {
                id: 608,
                name: 'Honeycomb',
                
            },
            {
                id: 609,
                name: 'Donut',
                
            },
            {
                id: 610,
                name: 'KitKat',
                
            }
        ];