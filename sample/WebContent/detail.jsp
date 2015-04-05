<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<xjsp:useBean id="Bean" scope="request" class="SampleBean" type="SampleBean"/>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>詳細</title>
</head>
<body>
<h1>詳細</h1>

番号 <xjsp:getProperty name="Bean" property="code" />
年月 <xjsp:getProperty name="Bean" property="YYYYMM" />

<form method="post" action="Search">

<input type="submit"/>
</form>

</body>
</html>