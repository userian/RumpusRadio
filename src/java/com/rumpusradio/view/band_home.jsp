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
    	<h2>Dashboard</h2>
    	
    	#if( $model.showNoMusic )	  
   		<div class="cs">
   			<div class="cst">Note!</div>
			<div class="csb">You have not added any music. Click <a href="bandmusic.html">here</a> to manage your music</div>
    	#end
	    #if( $model.showNoProfile )
    	<div class="cs">
   			<div class="cst">Note!</div>
			<div class="csb">Your profile has not been setup. Click <a href="banddetails.html">here</a> to update your profile</div>
		</div>
    	#end
    </div>
</body>
</html>