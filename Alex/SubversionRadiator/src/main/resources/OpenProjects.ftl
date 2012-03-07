		<header class="navbar">
			<div class="navbar-inner">
				<div class="progress">
					<div class="bar notrans"></div>
				</div>
				<div class="fill">
				  <h3 class="container">Open projects<span style="color:#EF6B00"></span></h3>
				</div>
			</div>
		</header>
		<div class="row-fluid board_svn">
            <#list entries?reverse as entry>
				<div class="row-fluid" >
					<div class="span2"> 
						<div class="project r1">${entry.project}</div>
					</div>
					<div class="span1"> 
						<div class="people r1">${entry.author}</div>
					</div>
					<div class="span1"> 
						<div class="date r1">${entry.date}</div>
					</div>
					<div class="span7"> 
						<div class="message r1"> ${entry.message} </div>
					</div>
				</div>
            </#list>
		</div>
