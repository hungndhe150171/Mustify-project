<%-- 
    Document   : addSong
    Created on : Jan 20, 2025, 10:41:08 PM
    Author     : thuhu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mustify | Add song</title>
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
            <div>
                <h1 class="spotify-green fw-bold text-center">Add Song</h1>

                <button class="btn btn-dark" data-mdb-ripple-init onclick="window.history.back()">Back</button>

                <!-- form add song -->
                <div class="mt-5" style="width: 50%;">
                    <form action="addsong" id="addsong" method="Post" enctype="multipart/form-data">
                        <div class="row mb-4">
                            <div class="form-outline d-flex" data-mdb-input-init>
                                <input type="text" id="title" name="title" class="form-control" required oninput="toggleCheckButton()"/>
                                <button type="button" class="btn btn-sm btn-danger m-auto p-0 w-auto h-auto" id="checkBtn" style="display:none;">check</button>
                                <label class="form-label" for="title">Song title</label>                            
                            </div>
                            <div class="text-danger fw-bold" id="check-msg"></div> 
                        </div>
                        <div class="row mb-4">
                            <div class="form-outline" data-mdb-input-init>
                                <input type="text" id="artist" name="artist" class="form-control" required/>
                                <label class="form-label" for="artist">Artist</label>
                            </div>
                        </div>
                        <div class="row mb-4">
                            <div class="form-outline" data-mdb-input-init>
                                <input type="text" id="album" name="album" class="form-control"/>
                                <label class="form-label" for="album">Album</label>
                            </div>
                        </div>
                        <div class="row mb-4">
                            <div class="form-floating p-0">
                                <select class="form-select" id="typeSong" name="typeSong" required aria-label="Floating label select example">
                                    <option disabled selected>Select type of song</option>
                                    <c:forEach items="${listST}" var="l">
                                        <option value="${l.id}">${l.name}</option>
                                    </c:forEach>
                                </select>
                                <label for="typeSong">Type of song</label>
                            </div>
                        </div>
                        <div class="row mb-4">
                            <label class="form-label" for="musicFile">Input music file</label>
                            <input type="file" class="form-control" id="musicFile" name="musicFile" required/>
                        </div>
                        <div class="row mb-4">
                            <label class="form-label" for="musicImg">Music image</label>
                            <input type="file" class="form-control" id="musicImg" name="musicImg" required/>
                        </div>
                        <div class="text-danger">${msg}</div>
                        <div class="row">
                            <div class="d-flex p-0">
                                <button type="submit" class="btn btn-primary me-2" data-mdb-ripple-init>Add</button>
                                <button type="button" class="btn btn-danger" onclick="document.getElementById('addsong').reset()" data-mdb-ripple-init>Clear</button>
                            </div>
                        </div>
                    </form>
                </div>

            </div>
        </div>
        <!-- MDB -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
                                    function toggleCheckButton() {
                                        var title = document.getElementById("title").value;
                                        var checkButton = document.getElementById("checkBtn");
                                        var checkMsg = document.getElementById("check-msg");

                                        // Hiển thị nút "check" nếu title trống, ẩn nếu có nội dung
                                        if (title.trim() === "") {
                                            checkMsg.style.display = "none";
                                            checkButton.style.display = "none";
                                        } else {
                                            checkMsg.style.display = "none";
                                            checkButton.style.display = "inline-block";
                                        }
                                    }
        </script>
        <script>

            $(document).ready(function () {
                $('#checkBtn').on('click', function (e) {
                    e.preventDefault();
                    let songName = $('#title').val();
                    $.ajax({
                        url: 'CheckSongExistServlet',
                        type: 'GET',
                        data: {songName: songName},
                        success: function (data) {
                            if (data) {
                                $('#check-msg').show();
                                $('#check-msg').html("Song is already exist!");
                            } else {
                                $('#check-msg').show();
                                $('#check-msg').html("You can use this song name!");
                            }
                        },
                        error: function (err) {
                            $('#check-msg').show();
                            $('#check-msg').html("You can use this song name!");
                        }
                    });
                });
            });
        </script>

        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.3.0/mdb.umd.min.js"></script>

    </body>
</html>
