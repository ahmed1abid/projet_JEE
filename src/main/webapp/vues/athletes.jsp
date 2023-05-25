<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<jsp:include page="includes/header.jsp" />
<head>
    <title>Athlètes</title>
</head>
<body>
	<!-- Example : Upload CSV file into database -->
    <form enctype="multipart/form-data" action="/projet_JEE/athlete-management/upload" method="post"
    		accept-charset="ISO-8859-1">
        <label for="file">Select a file:</label>
        <input type="file" name="file" id="file">
        <input type="submit" value="Submit">
    </form>
</body>
<jsp:include page="includes/footer.jsp" />

</html>