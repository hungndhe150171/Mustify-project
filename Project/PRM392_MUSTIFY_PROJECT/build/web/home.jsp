<%-- 
    Document   : home
    Created on : Jan 20, 2025, 6:02:16 PM
    Author     : thuhu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mustify | Home</title>
        <link rel="shortcut icon" href="images/96x96px.png" type="image/x-icon">
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet"/>
        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet"/>
        <!-- MDB -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.3.0/mdb.min.css"rel="stylesheet"/>


        <style>
            @import url('https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap');
            body{
                font-family: nunito;
            }
            .spotify-green{
                color: #1DB954;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <h1 class="spotify-green fw-bold">Mustify</h1>
                <div class="fw-bold">hello mustify is here</div>
            </div>

            <div>
                <button class="btn btn-primary" 
                        data-mdb-ripple-init
                        onclick="location.href = 'listsong'"><i class="fa-solid fa-list-ul me-2" style="color: #ffffff;"></i>List Song</button>
                <button class="btn btn-primary" 
                        data-mdb-ripple-init
                        onclick="location.href='addsong'"><i class="fa-solid fa-plus me-2" style="color: #ffffff;"></i>Add Song</button>
            </div>
        </div>
        <!-- MDB -->
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.3.0/mdb.umd.min.js"></script>
    </body>
</html>
