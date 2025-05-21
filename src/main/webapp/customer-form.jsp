<%@ page language="java" %>
<%@ page import="isd.group_4.Customer" %>
<!DOCTYPE html>
<html>
<head>
  <title>Customer Form</title>
  <%@ include file="template.jsp" %>
</head>
<body>
<%
  Customer c = (Customer)request.getAttribute("customer");
  if (c==null) { c = new Customer(); }
%>

<div class="center_container">
  <h1><%=c.getId()==0?"Add New Customer":"Edit Customer"%></h1>
</div>

<div class="page_body">
  <form action="CustomerServlet" method="post" class="form_standard">
    <input type="hidden" name="id" value="<%=c.getId()%>"/>

    <label>Name:</label><br/>
    <input
            type="text"
            name="name"
            value="<%=c.getName()==null?"":c.getName()%>"
            required class="input_box"
    /><br/><br/>

    <label>Email:</label><br/>
    <input
            type="email"
            name="email"
            value="<%=c.getEmail()==null?"":c.getEmail()%>"
            required class="input_box"
    /><br/><br/>

    <label>Type:</label><br/>
    <select name="type" class="input_box">
      <option value="individual" <%= "individual".equals(c.getType())?"selected":"" %>>
        Individual
      </option>
      <option value="company" <%= "company".equals(c.getType())?"selected":"" %>>
        Company
      </option>
    </select><br/><br/>

    <label>Address:</label><br/>
    <textarea
            name="address"
            rows="3"
            cols="50"
            required
            class="input_box"
    ><%=c.getAddress()==null?"":c.getAddress()%></textarea><br/><br/>

    <label>Active:</label>
    <input
            type="checkbox"
            name="active"
            <%=c.isActive()?"checked":""%>
    /><br/><br/>

    <button type="submit" class="link_box">Save</button>
    <a href="CustomerServlet" class="link_box">Cancel</a>
  </form>
</div>
</body>
</html>
