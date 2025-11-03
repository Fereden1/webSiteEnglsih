<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<h2>Регистрация</h2>
<form action="register" method="post">
    <label>Имя:</label><br>
    <input type="text" name="name" required><br><br>

    <label>Email:</label><br>
    <input type="email" name="email" required><br><br>

    <label>Пароль:</label><br>
    <input type="password" name="password" required><br><br>

    <input type="submit" value="Зарегистрироваться">
</form>

<p>Уже есть аккаунт? <a href="login.jsp">Войти</a></p>
</body>
</html>
