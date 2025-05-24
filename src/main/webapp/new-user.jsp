<%@ page language="java" %>
<%-- pottentially all redundant  --%>

<!DOCTYPE html>
<html>
<head>
    <title>Customer Form</title>
    <%@ include file="template.jsp" %>
</head>
<body>
<%
    User u = (User)request.getAttribute("user");
    if (u==null) { u = new User(); }
%>

<div class="center_container">
    <h1><%=u.getUserID()==0?"Add New User":"Edit User"%></h1>
</div>

<div class="page_body">
    <form action="UserServlet" method="post" class="form_standard">
        <input type="hidden" name="id" value="<%=u.getUserID()%>"/>

        <label>First Name:</label><br/>
        <input
                type="text"
                name="firstName"
                value="<%=u.getFirstName()==null?"":u.getFirstName()%>"
                required class="input_box"
        /><br/><br/>
        <label>Last Name:</label><br/>
        <input
                type="text"
                name="lastName"
                value="<%=u.getLastName()==null?"":u.getLastName()%>"
                required class="input_box"
        /><br/><br/>

        <label>Email:</label><br/>
        <input
                type="email"
                name="email"
                value="<%=u.getEmail()==null?"":u.getEmail()%>"
                required class="input_box"
        /><br/><br/>

        <label>Role:</label><br/>
        <select name="role" class="input_box">
            <option value="customer" <%= "customer".equals(u.getRole())?"selected":"" %>>
                customer
            </option>
            <option value="admin" <%= "admin".equals(u.getRole())?"selected":"" %>>
                admin
            </option>

        </select><br/><br/>




        <br/><br/>

        <button type="submit" class="link_box">Save</button>
        <a href="UserServlet" class="link_box">Cancel</a>
    </form>
</div>
</body>
</html>
