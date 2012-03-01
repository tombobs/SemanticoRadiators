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
<!--
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

		<#list JIRAstats as dispObj>
		<div class="row-fluid board">
			<div class="span12"> 
				<div class="row-fluid">
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
<div class="row-fluid board_jira">
			<div class="span12"> 
				<div class="row-fluid" >
					<div class="span2 ticketID rj1"> ${JIRAstats[0].title}
					</div>
					<div class="span7 people rj1"> ${JIRAstats[0].summary}
					</div>
					<div class="span2 date rj1"> ${JIRAstats[0].lastUpdate}
					</div>	
				</div>
				<div class="row-fluid" >
					<div class="span2 ticketID rj2"> ${JIRAstats[1].title}
					</div>
					<div class="span8 people rj2"> ${JIRAstats[1].summary}
					</div>
					<div class="span2 date rj2"> ${JIRAstats[1].lastUpdate}
					</div>	
				</div>
				<div class="row-fluid" >
					<div class="span2 ticketID rj3"> ${JIRAstats[2].title}
					</div>
					<div class="span8 people rj3"> ${JIRAstats[2].summary}
					</div>
					<div class="span2 date rj3"> ${JIRAstats[2].lastUpdate}
					</div>	
				</div>
				<div class="row-fluid" >
					<div class="span2 ticketID rj4"> ${JIRAstats[3].title}
					</div>
					<div class="span8 people rj4"> ${JIRAstats[3].summary}
					</div>
					<div class="span2 date rj4"> ${JIRAstats[3].lastUpdate}
					</div>	
				</div>
				<div class="row-fluid" >
					<div class="span2 ticketID rj5"> ${JIRAstats[4].title}
					</div>
					<div class="span8 people rj5"> ${JIRAstats[4].summary}
					</div>
					<div class="span2 date rj5"> ${JIRAstats[4].lastUpdate}
					</div>	
				</div>
			</div>	
		</div>
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