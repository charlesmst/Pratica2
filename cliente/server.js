var express = require('express');
var app = express();
var http = require('http').Server(app);
var bodyParser = require('body-parser')
var url = require('url');

app.use(bodyParser.json());       // to support JSON-encoded bodies
app.use(bodyParser.urlencoded({// to support URL-encoded bodies
    extended: true
}));

http.listen(3000, function () {
    console.log('listening on *:3000');
});
app.use('/', express.static(__dirname + '/'));

var defaultResponse = {
    success: true
}
function paginate(data, request) {
    var url_parts = url.parse(request.url, true);
    var paginated = []
    var page = url_parts.query.page - 1;
    var max = (page * url_parts.query.limit) + parseInt(url_parts.query.limit)
    var min = page * url_parts.query.limit
    if (max > data.length)
        max = data.length

    for (var i = min; i < max; i++) {
        paginated.push(data[i])
    }

    return paginated;
}
function filter(data, request) {
    var url_parts = url.parse(request.url, true);

    return data.filter(function (item) {
        return item.user.indexOf(url_parts.query.filter) >= 0
    })
}
var data = [];
app.get('/api', function (req, res) {
    res.json(paginate(filter(data, req), req));
})
app.post('/api', function (req, res) {
    data.push(req.body)
    res.json(defaultResponse)
})

app.get('/api/count', function (req, res) {
    res.json({
        "count": filter(data, req).length
    })
})
app.post('/api/:id', function (req, res) {
    console.log("Updating " + req.params.id)
    data.filter(function (item) {
        return item.id == req.params.id
    }).forEach(function (item) {
        for (var a in req.body)
            item[a] = req.body[a]
    })
    setTimeout(function () {
        res.json(defaultResponse)

    }, 1000)
})
app.delete('/api/:id', function (req, res) {
    data = data.filter(function (item) {
        return item.id != req.params.id
    })

    res.json(defaultResponse)
})
app.get('/api/:id', function (req, res) {
    var item = data.filter(function (item) {
        return item.id == req.params.id
    })[0] || {};
    setTimeout(function () {
        res.json(item)

    }, 1000)
})



var randomData = [
    {
        id: 601,
        user: 'Frozen joghurt'
    },
    {
        id: 602,
        user: 'Ice cream sandwitch',
    },
    {
        id: 603,
        user: 'Eclair',
    },
    {
        id: 604,
        user: 'Cupkake',
    },
    {
        id: 605,
        user: 'Gingerbread',
    },
    {
        id: 606,
        user: 'Jelly bean',
    },
    {
        id: 607,
        user: 'Lollipop',
    },
    {
        id: 608,
        user: 'Honeycomb',
    },
    {
        id: 609,
        user: 'Donut',
    },
    {
        id: 610,
        user: 'KitKat',
    }
];
var index = 0
for (var i = 1, max = 1000; i < max; i++) {
    if (index >= randomData.length)
        index = 0;
    data.push({
        id: i,
        user: randomData[index++].user
    });
}
console.log(data)