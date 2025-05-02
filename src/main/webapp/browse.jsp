<html>
<head>
    <title>IOT Sales</title>
    <%--  KEEP THIS LINE, IMPORTANT FOR HEADER AND FOOTER  --%>
    <%@ include file="template.jsp" %>
    <%-- Also includes style.css--%>
</head>
<body>
    <h1>IOT shop</h1>
    <div class="content">
        <div class="bg"></div>
        <div class="header-grid"></div>
        Main content goes here

<%--        <%
            for (Items items : db.Albums().allAlbums()) {
                String imagePath = "image/album/" + album.getTitle().toLowerCase().replace(' ', '_') + ".jpg";
        %>
        <div class="album-card">
            <p>items</p>
            <img src=<%=imagePath%> />
            <p><%=album.getArtist()%></p>
            <form method="post" action="/PurchaseServlet">
                <input type="hidden" name="id" value="<%=album.getId()%>">
                <input id="reg" type="submit" value="PURCHASE">
            </form>
        </div>

        <%
            }
        %>--%>
    </div>
</body>
</html>

