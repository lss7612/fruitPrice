<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
const contextPath = '${pageContext.request.contextPath}';
</script>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/main/main.js"></script>
</head>
<body>
<div>
    <select id="category" onChange="getProductList()">
    	<option value="0">category 선택</option>
    </select>
    <select id="product" onChange="getPrice()">
    	<option value="0">category 먼저 선택하세요</option>
    </select>
    <button id="btn_search">조회</button>
</div>
<div>
	
</div>

</body>
</html>