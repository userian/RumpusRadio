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
        <div class="sectitle">Change Password</div>
		<div class="m">$message</div>
    	<div id="infoform">
       	<form method="post">
       		<table>
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
					<td></td><td><input type="submit" value="Change Password" /></td>
				</tr>
	        </table>
        </form>
        </div>
    </div>
</body>
</html>