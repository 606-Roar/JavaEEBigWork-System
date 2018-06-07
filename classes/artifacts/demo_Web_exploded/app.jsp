<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>vuetest</title>
    <link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <h1>Vue demo</h1>
        <div id="app">
            <div>{{message}}</div>
            <input type="text" v-model="message">
        </div>
    </div>
</div>
<script src='./static/js/vue.js'></script>
<script src="./app.js"></script>
</body>
</html>