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
        <div class="sectitle">Profile</div>
   		<div class="m">$message</div>
    	<div id="infoform"/>
    	<form method="post">
	        <table>
	        	<tr>
	        		<td>Band Name</td>
	        		<td>#springFormInput("command.artistName" "")</td>
	        		<td>#springShowErrors("" "")</td>
        		</tr>
				<tr>
					<td>Genres</td>
					<td>#springFormSingleSelect( "command.genre1" $genres "")</td>
					<td>#springShowErrors("" "")</td>
				</tr>
				<tr>
					<td></td>
					<td>#springFormSingleSelect( "command.genre2" $genres "")</td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td>#springFormSingleSelect( "command.genre3" $genres "")</td>
					<td></td>
				</tr>
				<tr>
	        		<td>Website</td>
	        		<td>#springFormInput("command.artistInfo.website" "")</td>
	        		<td>#springShowErrors("" "")</td>
        		</tr>
				<tr>
					<td colspan="3"><input type="submit" value="Save" /></td>
				</tr>
	        </table>
        </form>
        </div>
    </div>
</body>
</html>