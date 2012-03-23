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
<!--
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
-->

<div class="row-fluid board_jira">
<div class="span12"> 
<p>RT</p>
<div class="row-fluid">
<div class="span2"> 
<div class="ticketID rj1">${RTstats[0].ticketNum}</div>
</div>
<div class="span7"> 
<div class="people rj1">${RTstats[0].summary}</div>
</div>
<div class="span3"> 
<div class="date rj1">${RTstats[0].queue}</div>
</div>	
</div>
<div class="row-fluid">
<div class="span2"> 
<div class="ticketID rj2">${RTstats[1].ticketNum}</div>
</div>
<div class="span7"> 
<div class="people rj2">${RTstats[1].summary}</div>
</div>
<div class="span3"> 
<div class="date rj2">${RTstats[1].queue}</div>
</div>	
</div>
<div class="row-fluid">
<div class="span2"> 
<div class="ticketID rj3">${RTstats[2].ticketNum}</div>
</div>
<div class="span7"> 
<div class="people rj3">${RTstats[2].summary}</div>
</div>
<div class="span3"> 
<div class="date rj3">${RTstats[2].queue}</div>
</div>	
</div>
<div class="row-fluid">
<div class="span2"> 
<div class="ticketID rj4">${RTstats[3].ticketNum}</div>
</div>
<div class="span7"> 
<div class="people rj4">${RTstats[3].summary}</div>
</div>
<div class="span3"> 
<div class="date rj4">${RTstats[3].queue}</div>
</div>	
</div>
<div class="row-fluid">
<div class="span2"> 
<div class="ticketID rj5">${RTstats[4].ticketNum}</div>
</div>
<div class="span7"> 
<div class="people rj5">${RTstats[4].summary}</div>
</div>
<div class="span3"> 
<div class="date rj5">${RTstats[4].queue}</div>
</div>	
</div>
</div>	
</div>
</center>


</body>
</html>