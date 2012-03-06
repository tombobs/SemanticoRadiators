<html>
<head>
    <meta charset="utf-8">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="bootstrap/css/radiator_common.css">
		<link rel="stylesheet" href="bootstrap/css/OpenProjects.css">
		<link rel="stylesheet" href="bootstrap/css/jenkins-radiator.css">
		<link rel="stylesheet" href="bootstrap/css/feeds.css">
		<link rel="stylesheet" href="bootstrap/css/jira.css">
		<script src="http://code.jquery.com/jquery-latest.js"></script>		
</head>

<body>

<header class="navbar">
<div class="navbar-inner">
<div class="progress">
<div class="bar notrans"></div>
</div>
<div class="fill">
<h1 class="container"><a href="https://jira.semantico.com/">JIRA</a> feed</h3>
</div>
</div>
</header>

<body>
<#list RTstats as item>
    ${item.ticketNum}
    ${item.summary}
    ${item.queue}
</#list>
</body>
</html>