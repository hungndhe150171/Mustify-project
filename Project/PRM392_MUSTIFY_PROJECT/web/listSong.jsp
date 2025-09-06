<%-- 
    Document   : listSong
    Created on : Jan 20, 2025, 6:36:35 PM
    Author     : thuhu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="images/96x96px.png" type="image/x-icon">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List song</title>
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
            <div class="fs-1 fw-bold text-center spotify-green">List Song</div>
            <div class="">
                <button class="btn btn-dark" data-mdb-ripple-init onclick="window.history.back()">Back</button>
                <!-- pagination -->
                <input type="hidden"id="page" value="${currentPage}">
                <c:if test="${numberOfPages > 1}">
                    <nav arial-labe="page navigation">
                        <ul class="pagination justify-content-center m-0">
                            <!-- previous -->

                            <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                <a class="page-link" href="listsong?page=${currentPage - 1}" aria-label="Previous">
                                    <i class="fa-solid fa-chevron-left"></i></a>
                            </li>
                            <c:forEach begin="1" end="${numberOfPages}" var="page">
                                <c:choose>
                                    <c:when test="${page == currentPage}">
                                        <li class="page-item-active" aria-current="page">
                                            <span class="page-link">${page}</span>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" id="page" value="${currentPage}"/>
                                        <li class="page-item">
                                            <a class="page-link" href="listsong?page=${page}">${page}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                            <!-- nut next -->
                            <li class="page-item ${currentPag == numberOfPages ? 'disabled':''}">
                                <a class="page-link" href="listsong?page=${currentPage+1}" aria-label="Next">
                                    <i class="fa-solid fa-chevron-right"></i></a>
                            </li>
                        </ul>
                    </nav>
                </c:if>
                <!-- pagination -->

                <table class="table table-hover">
                    <thead style="background-color: #1DB954 !important;">
                        <tr >
                            <th scope="col">#</th>
                            <th scope="col">Title</th>
                            <th scope="col">Artist</th>
                            <th scope="col">Type</th>
                            <th scope="col">Album</th>
                            <th scope="col">Duration</th>
                            <th scope="col">Image</th>
                            <th scope="col">Created date</th>
                            <th scope="col">Action</th>
                        </tr>
                        <c:forEach items="${listForPage}" var="l" varStatus="status">
                            <tr>
                                <td>${status.index +1}</td>
                                <td class="text-nowrap">${l.title}</td>
                                <td>${l.artist}</td>
                                <td>${l.type.name}</td>
                                <td>${l.album == '' or l.album == null || l.album=="" ? 'none':l.album}</td>
                                <td>
                                    <c:set var="minutes" value="${l.duration / 60}"/>
                                    <c:set var="seconds" value="${l.duration % 60}"/>
                                    ${minutes > 10 ?'':'0'}<fmt:formatNumber value="${minutes}" pattern="0" /> : ${seconds > 9?'':'0'}${seconds}
                                </td>
                                <td>
                                    <img src="images/Music image/${l.image}" width="50" height="50"/>
                                </td>
                                <td><fmt:formatDate value="${l.created_date}" pattern="dd/MM/yyyy"/></td>
                                <td>
                                    <a class="btn btn-sm btn-dark btn-floating" data-mdb-ripple-init href="url"><i class="fa-solid fa-pen fs-6" style="color: #fff;"></i></a>
                                    <a class="btn btn-sm btn-dark btn-floating" data-mdb-ripple-init href="/deleteSong?id=${l.song_id}"><i class="fa-solid fa-trash fs-6" style="color: #fff;"></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </thead>
                </table>

                <!-- pagination -->
                <input type="hidden"id="page" value="${currentPage}">
                <c:if test="${numberOfPages > 1}">
                    <nav arial-labe="page navigation">
                        <ul class="pagination justify-content-center m-0">
                            <!-- previous -->
                            <li class="page-item ${currentPage == 1 ? 'disabled':''}">
                                <a class="page-link" href="listsong?page=${currentPage - 1}" aria-label="Previous">
                                    <i class="fa-solid fa-chevron-left"></i></a>
                            </li>
                            <c:forEach begin="1" end="${numberOfPages}" var="page">
                                <c:choose>
                                    <c:when test="${page == currentPage}">
                                        <li class="page-item-active" aria-current="page">
                                            <span class="page-link">${page}</span>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" id="page" value="${currentPage}"/>
                                        <li class="page-item">
                                            <a class="page-link" href="listsong?page=${page}">${page}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                            <!-- nut next -->
                            <li class="page-item ${currentPage == numberOfPages ? 'disabled':''}">
                                <a class="page-link" href="listsong?page=${currentPage+1}" aria-label="Next">
                                    <i class="fa-solid fa-chevron-right"></i></a>
                            </li>
                        </ul>
                    </nav>
                </c:if>
                <!-- pagination -->
            </div>
        </div>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.3.0/mdb.umd.min.js"></script>
    </body>
</html>
