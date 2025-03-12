<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>
  <div class="header">Davey's Record Store</div>
  <div class="content">
    <div class="bg"></div>
    <h1>Register here!</h1>
    <form action="welcome.jsp" method="post">
      <label for="email">Email</label>
      <input id="email" name="email" type="text"/>
      <label for="password">Password</label>
      <input id="password" name="password" type="password"/>
      <label for="genre">Favourite Genre</label>
      <select name="genre" id="genre">
        <option value="Kpop">Kpop</option>
        <option value="Hardstyle">Hardstyle</option>
        <option value="Trash">Trash</option>
      </select>
      <input type="submit" value="Register">
    </form>
  </div>
  <div class="footer">Stylesheet is not to be used in assessable submissions</div>
</body>
</html>
