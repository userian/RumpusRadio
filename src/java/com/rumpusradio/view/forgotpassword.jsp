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
        <div class="sectitle">Forgot Your Password?</div>
        <div class="m"></div>
    	<div id="regform" />
    	Enter your email address bellow and we'll reset it for you.<br /><br />
    	<form method="post">
	        <table>
				<tr>
					<td>Email</td>
					<td>#springFormInput("command.email" "")</td>
					<td>#springShowErrors("" "")</td>
				</tr>
				<tr>
					<td></td><td colspan="2"><input type="submit" value="Submit" /></td>
				</tr>
	        </table>
        </form>
        </div>
    </div>
</body>
</html>