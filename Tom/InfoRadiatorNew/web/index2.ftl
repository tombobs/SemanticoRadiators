<!doctype html>
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
				  <h3 class="container"> <span style="color:#EF6B00">Semantico</span></h3>
				</div>
			</div>
		</header>
	
		<div id="content">
			<div id="jira" data-page="jiraServlet"> </div>
			<div id="feed" data-page="Feeds.html"> </div>
			<div id="jenkins" data-page="jenkins.html">	</div>
			<div id="subversion" data-page="OpenProjects.html" > </div>				
        
		</div>
		
		<script id="main">
			window.OurJenkins = (function($) { 		

				var jobList, template, loop, builds, prog, pages, count;

				drawHtmls = function() {
					builds.html( );
				};
				window.time = 30; //60 --> 1 minute
				loop = {
					time: 20, // in seconds

					func: function() {	  									
						prog.addClass("notrans").removeClass("hide");
						setTimeout(function() {
							prog.removeClass("notrans").addClass("hide");
						}, 100);
						var page=$(pages[count]);
						pages.hide();
						page.load(page.data("page"));
						page.show();
						if (count >= pages.length-1) {
							count=0;
						}		
						else count++;
						//console.log(count);
						// Call yourself
						setTimeout(arguments.callee, window.time * 1000);						
					 },

					init: function() {
						builds  = $('#builds');
						prog   	= $('.bar');
						sub		= $('#subversion');
						feed	= $('#feed');
						pages 	= $('#content > div'); //all div-s in the #content
						count 	= 0;
						loop.func();						
					}
				};

				// Document Ready
				$(function() {
					loop.init();					
				});

			})(jQuery);
		</script>

		
	</body>
</html>