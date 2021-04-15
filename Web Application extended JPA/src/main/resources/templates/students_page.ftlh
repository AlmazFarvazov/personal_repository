<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <form method="post" ACTION="/students">
        <input type="text" name="firstName">
        <input type="text" name="lastName">
        <input type="submit" value="Add">
    </form>
    <table>
        <tr>
            <th>Имя</th>
            <th>Фамилия</th>
        </tr>
        <#list students as student>
            <tr>
                <td>${student.firstName}</td>
                <td>${student.lastName}</td>
            </tr>
        </#list>
    </table>
</body>
</html>