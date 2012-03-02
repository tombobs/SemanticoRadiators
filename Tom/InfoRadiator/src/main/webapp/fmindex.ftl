<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Commit Radiator</title>
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/OpenProjects.css">
	</head>
	
	<body>
		<div class="header"></div>
                
		<div class="row-fluid board">
			<div class="span12">
				<div class="row-fluid" >
					<div class="span3 project"> Project Name </div>
					<div class="span5 people"> People on the Project </div>					</div>
					<div class="span2 date">Last Commit</div>	
				</div>
                        </div>
                </div>
              <#list list as item>${item}</#list>
</body>
</html>