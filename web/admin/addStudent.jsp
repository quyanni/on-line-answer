<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title>学生主页</title>

<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.css" />
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div class="container">
		<!--登录表单-->
			<div style="margin: auto;padding-top: 10%">
				<form enctype="multipart/form-data" class="form-horizontal col-sm-offset-3" action="${ pageContext.request.contextPath }/AdminServlet?method=importStudent" method="post">
					<div class="form-group">
							<label><input type="file" name="excel"    accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" /></label>
							<button type="submit" class="btn btn-primary">导入学生</button>
					</div>
				</form>
		</div>
	</div>
</body>

</html>