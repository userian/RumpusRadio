<html xmlns="http://www.w3.org/1999/xhtml">
	#parse("header.jsp")
<body>

    <div id="logobox">
		<img src="images/rumpusradio.jpg" alt="Rumpus Radio" />
	</div>	
    <div id="left">
        <div id="navmenu">
        	#parse("nav_menu.jsp")
        </div>
    </div>

    <div id="right"> 
        <div id="radio">
            
        </div>  
    </div>

    <div id="content">
        <div class="sectitle">Register</div>
    	<div id="regform"/>
    	<form method="post">
	        <table>
	        	<tr>
	        		<td>First Name</td>
	        		<td>#springFormInput("command.firstName" "")</td>
	        		<td>#springShowErrors("" "")<br></td>
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
					<td>Password</td>
					<td>#springFormPasswordInput("command.password1" "")</td>
					<td>#springShowErrors("" "")</td>
				</tr>
				<tr>
					<td>Confirm Password</td>
					<td>#springFormPasswordInput("command.password2" "")</td>
					<td>#springShowErrors("" "")</td>
				</tr>
				<tr>
					<td></td><td colspan="3"><input type="submit" value="Join Rumpus Radio!" /></td>
				</tr>
	        </table>
        </form>
        </div>
    </div>
</body>
</html>