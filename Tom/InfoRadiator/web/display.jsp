<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
					<div class="span3 project"> Project Name
					</div>
					<div class="span6 people"> People on the Project
					</div>
					<div class="span2 date"> Commits
					</div>	
				</div>
                        
                                <c:forEach var="dispObj" items="${list}">
                                    <div class="row-fluid">
                                        <div class="span3"> ${dispObj.title} </div>
                                        <div class="span6"> ${dispObj.summary} </div>
                                        <div class="span2"> ${dispObj.lastUpdate} </div>
                                    </div>
                                </c:forEach>
				
				
			</div>	
		</div>	
	</body>
</html>