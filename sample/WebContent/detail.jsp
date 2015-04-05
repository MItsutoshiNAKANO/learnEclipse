<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<jsp:useBean id="Bean" scope="request" class="jp.ne.zaq.rinku.bkbin005.SampleBean" type="jp.ne.zaq.rinku.bkbin005.SampleBean"/>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>詳細</title>
</head>
<body>
<h1>詳細</h1>

番号 <jsp:getProperty name="Bean" property="code" />
年月 <jsp:getProperty name="Bean" property="YYYYMM" />

<form method="post" action="Search">

<input type="submit"/>
</form>

</body>
</html>