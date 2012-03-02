<html>
	<head>
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/OpenProjects.css">
		<link rel="stylesheet" href="bootstrap/css/radiator_common.css">
		<script src="bootstrap/js/jquerylatest.js"></script>
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
                <div class="row-fluid board">
			<div class="span12"> 
				<div class="row-fluid r1">
					<div class="span2"> ${JIRAstats[0].title}
					</div>
					<div class="span8">  ${JIRAstats[0].summary}
					</div>
					<div class="span2"> ${JIRAstats[0].lastUpdate}
					</div>	
				</div>
			</div>	
		</div>



<!--
		<#list JIRAstats as dispObj>
		<div class="row-fluid board">
			<div class="span12"> 
				<div class="row-fluid" id="whatev" >
					<div class="span2"> ${dispObj.title}
					</div>
					<div class="span8">  ${dispObj.summary}
					</div>
					<div class="span2"> ${dispObj.lastUpdate}
					</div>	
				</div>
			</div>	
		</div>
                </#list>
-->
	<script id="main">
		window.OurJenkins = (function($) { 

			var jobList, template, loop, builds, prog, sorted;

		    drawHtmls = function() {
				builds.html( );
		    };
			window.time = 60;
			loop = {
				time: 20, // in seconds

				func: function() {	  					 
					prog.addClass("notrans").removeClass("hide");
					setTimeout(function() {
						prog.removeClass("notrans").addClass("hide");
					}, 100);
					// Call yourself
					setTimeout(arguments.callee, window.time * 1000);
				 },

				init: function() {
					builds        = $('#builds');
					prog          = $('.bar');
					sorted        = $('body');
					loop.func();
				}
			};

			// Document Ready
			$(function() {
				loop.init();
			});

			// Public
			return {
				draw: drawHtmls,
				count: 0
			};

		})(jQuery);
	</script>		
	</body>
</html>