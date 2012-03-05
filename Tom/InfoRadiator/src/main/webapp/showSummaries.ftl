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

<center>
<div class="row-fluid board_jira">
<div class="span12"> 
<p>JIRA</p>
<div class="row-fluid">
<div class="span2"> 
<div class="ticketID rj1">${JIRAstats[0].title}</div>
</div>
<div class="span7"> 
<div class="people rj1">${JIRAstats[0].summary}</div>
</div>
<div class="span3"> 
<div class="date rj1">${JIRAstats[0].lastUpdate}</div>
</div>	
</div>
<div class="row-fluid">
<div class="span2"> 
<div class="ticketID rj2">${JIRAstats[1].title}</div>
</div>
<div class="span7"> 
<div class="people rj2">${JIRAstats[1].summary}</div>
</div>
<div class="span3"> 
<div class="date rj2">${JIRAstats[1].lastUpdate}</div>
</div>	
</div>
<div class="row-fluid">
<div class="span2"> 
<div class="ticketID rj3">${JIRAstats[2].title}</div>
</div>
<div class="span7"> 
<div class="people rj3">${JIRAstats[2].summary}</div>
</div>
<div class="span3"> 
<div class="date rj3">${JIRAstats[2].lastUpdate}</div>
</div>	
</div>
<div class="row-fluid">
<div class="span2"> 
<div class="ticketID rj4">${JIRAstats[3].title}</div>
</div>
<div class="span7"> 
<div class="people rj4">${JIRAstats[3].summary}</div>
</div>
<div class="span3"> 
<div class="date rj4">${JIRAstats[3].lastUpdate}</div>
</div>	
</div>
<div class="row-fluid">
<div class="span2"> 
<div class="ticketID rj5">${JIRAstats[4].title}</div>
</div>
<div class="span7"> 
<div class="people rj5">${JIRAstats[4].summary}</div>
</div>
<div class="span3"> 
<div class="date rj5">${JIRAstats[4].lastUpdate}</div>
</div>	
</div>
</div>	
</div>

<div class="row-fluid board_jira">
<div class="span12"> 
<p>RT</p>
<div class="row-fluid">
<div class="span2"> 
<div class="ticketID rj1">XXX-12345</div>
</div>
<div class="span7"> 
<div class="people rj1">People on the Project</div>
</div>
<div class="span3"> 
<div class="date rj1">12 May</div>
</div>	
</div>
<div class="row-fluid">
<div class="span2"> 
<div class="ticketID rj2">XXX-12345</div>
</div>
<div class="span7"> 
<div class="people rj2">People on the Project</div>
</div>
<div class="span3"> 
<div class="date rj2">12 May</div>
</div>	
</div>
<div class="row-fluid">
<div class="span2"> 
<div class="ticketID rj3">XXX-12345</div>
</div>
<div class="span7"> 
<div class="people rj3">People on the Project</div>
</div>
<div class="span3"> 
<div class="date rj3">12 May</div>
</div>	
</div>
<div class="row-fluid">
<div class="span2"> 
<div class="ticketID rj4">XXX-12345</div>
</div>
<div class="span7"> 
<div class="people rj4">People on the Project</div>
</div>
<div class="span3"> 
<div class="date rj4">12 May</div>
</div>	
</div>
<div class="row-fluid">
<div class="span2"> 
<div class="ticketID rj5">XXX-12345</div>
</div>
<div class="span7"> 
<div class="people rj5">People on the Project</div>
</div>
<div class="span3"> 
<div class="date rj5">12 May</div>
</div>	
</div>
</div>	
</div>
</center>


</body>
</html>