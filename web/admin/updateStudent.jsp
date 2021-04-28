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
			<div style="margin: auto;">
				<div class="text-center">
					<h3>修改学生</h3>
				</div>
				<form class="form-horizontal col-sm-offset-3" action="AdminServlet?method=updateStudent&username=${student.username }" method="post">
					<div class="form-group">
						<label for="tname" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-4">
							<input type="text" id="tname" class="form-control" name="username" value="${ student.username}" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label for="realname" class="col-sm-2 control-label">学生姓名</label>
						<div class="col-sm-4">
							<input type="text" id="realname" class="form-control" name="realname" value="${ student.realname}" placeholder="请输入学生姓名">
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-4">
							<input type="text" id="password" class="form-control" name="password" value="${ student.password}" placeholder="请输入学生密码">
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-2"></div>
						<div class="col-md-2">
							<button class="btn btn-success btn-block">提交</button>
						</div>
						<div class="col-md-2">
							<input type="reset" class="btn btn-warning btn-block" value="重置">
						</div>
					</div>
				</form>
		</div>
	</div>
</body>

</html>