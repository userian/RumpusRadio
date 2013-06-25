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
        <div class="sectitle">Terms and Conditions</div>
   		<div class="m">$message</div>
   		<div id="infoform">
   		<form method="post"> 
			<p>By uploading your music to Rumpus Radio you give Rumpus Radio the right to stream your music over the internet.</p>
			<p>
				#springFormCheckboxes("command.agreementCheckbox" $checkboxes, "", "")
				#springShowErrors("" "")
				<br />
				<input type="submit" value="Submit" />
			</p>
		</form>
 	    </div>
    </div>
</body>
</html>