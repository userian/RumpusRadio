<html xmlns="http://www.w3.org/1999/xhtml">
	#parse("header.jsp")
<body>

    <div id="logobox">
		<img src="images/rumpusradio.jpg" alt="Rumpus Radio" />
	</div>	
    <div id="left">
        <div id="navmenu">
			#parse("nav_menu_band.jsp")
        </div>
    </div>

    <div id="right"> 
        <div id="radio">
            
        </div>  
    </div>

    <div id="content">
        <div class="sectitle">Personal Info</div>
        <div class="m">$message</div>
    	<div id="infoform"/>
    	<form method="post">
	        <table>
	        	<tr>
	        		<td>First Name</td>
	        		<td>#springFormInput("command.firstName" "")</td>
	        		<td>#springShowErrors("" "")</td>
        		</tr>
        		<tr>
	        		<td>Last Name</td>
	        		<td>#springFormInput("command.lastName" "")</td>
	        		<td>#springShowErrors("" "")</td>
        		</tr>
	        	<tr>
	        		<td>Email</td>
	        		<td>#springFormInput("command.email" "")</td>
	        		<td>#springShowErrors("" "")</td>
        		</tr>
				<tr>
					<td></td><td><input type="submit" value="Save" /></td>
				</tr>
       		</table>
       	</form>
        </div>
    </div>
</body>
</html>