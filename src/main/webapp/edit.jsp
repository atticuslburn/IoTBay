<%--
  Created by IntelliJ IDEA.
  User: niman
  Date: 11/05/2025
  Time: 12:03 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="template.jsp" %>
<html>
<head>
    <title>Edit Account</title>
</head>
<body>

<%
    if (loggedInUser == null) {
%>
<a href="login.jsp">
    <div class="center-box">You are not logged in. Click to login.</div>
</a>
<%
        return;
    }

    String fname = loggedInUser.getFirstName();
    String lname = loggedInUser.getLastName();
    String phoneNumber = loggedInUser.getPhone() != null ? loggedInUser.getPhone() : "";
    String streetNumber = loggedInUser.getStreetNumber() != null ? loggedInUser.getStreetNumber() : "";
    String streetName = loggedInUser.getStreetName() != null ? loggedInUser.getStreetName() : "";
    String suburb = loggedInUser.getSuburb() != null ? loggedInUser.getSuburb() : "";
    String postcode = loggedInUser.getPostcode() != null ? loggedInUser.getPostcode() : "";
%>

<div class="center-box">
    <form method="post" action="EditServlet">
        <label>First Name: <input type="text" name="firstName" value="<%=fname%>"/></label><br>
        <label>Last Name: <input type="text" name="lastName" value="<%=lname%>"/></label><br>
        <label>Phone: <input type="text" name="phoneNumber" value="<%=phoneNumber%>"/></label><br>
        <label>Street Number: <input type="text" name="streetNumber" value="<%=streetNumber%>"/></label><br>
        <label>Street Name: <input type="text" name="streetName" value="<%=streetName%>"/></label><br>
        <label>Suburb: <input type="text" name="suburb" value="<%=suburb%>"/></label><br>
        <label>Postcode: <input type="text" name="postcode" value="<%=postcode%>"/></label><br>
        <button type="submit">Save Changes</button>
    </form>
</div>

<div class="center-box" onclick="location.href='account.jsp';">
    Cancel and go back
</div>

</body>
</html>
