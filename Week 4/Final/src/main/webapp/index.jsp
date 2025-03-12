
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="style.css">
</head>
    <body>
    <div class="header">Davey's Record Store</div>
            <div class="content">
                <div class="bg"></div>
                <h1>Daveys Record Store</h1>
                <p>Kpop, Hardstyle and more!</p>
                <div>
                <%
                    out.println("Registered Users: " + 6 );
                %>
                </div>
                <br>
                <a href="register.jsp"><button>Enter the record store</button></a>
            </div>
        <div class="footer">Stylesheet is not to be used in assessable submissions</div>
    </body>
</html>
