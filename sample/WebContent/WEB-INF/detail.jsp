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
<header>
<h1>詳細</h1>
番号 ${Bean.code}
年月 ${Bean.YYYYMM}
</header>

<form method="post" action="${Bean.state}">
<input type="hidden" name="code" value="${Bean.code}"/>
<input type="hidden" name="nengetu" value="${Bean.YYYYMM}"/>
<label id="days">日数<input type="number" min="0" max="31" name="days" value="${Bean.days}"/></label>
<input type="submit"/>
</form>
<a href="index.jsp">戻る</a>

</body>
</html>
