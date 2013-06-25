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
        <div class="sectitle">Band Login</div>
        <div class="m">${message}</div>
    	<div id="regform" />
    	<form method="post">
	        <table>
				<tr>
					<td>Email</td>
					<td>#springFormInput("command.email" "")</td>
					<td>#springShowErrors("" "")</td>
				</tr>
				<tr>				
					<td>Password</td>
					<td>#springFormPasswordInput("command.password" "")</td>
					<td>#springShowErrors("" "")</td>
				</tr>
				<tr>
					<td></td><td><a class="smallnu" href="forgotpassword.html">[forgot password]</a></td><td></td>
				</tr>
				<tr>
					<td></td><td colspan="2"><input type="submit" value="Login" /></td>
				</tr>
	        </table>
        </form>
        </div>
		Don't have an account? Click <a href="register.html">here</a> to sign up!
    </div>
</body>
</html>