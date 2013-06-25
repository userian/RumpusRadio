<html xmlns="http://www.w3.org/1999/xhtml">
	#parse("header.jsp")
<body onload="get_radio_info()">

    <div id="logobox">
		<img src="images/rumpusradio.jpg" alt="Rumpus Radio" />
        <div id="radio">
            <h2>Now Playing</h2>
            <div id="nowplaying"></div>
            <img id="radiotower" src="images/radiotower_small.gif" />
        </div>  
	</div>
    <div id="left">
        <div id="navmenu">
            #parse("nav_menu.jsp")
        </div>
    </div>

    <div id="right">

    </div>

    <div id="content">
        <div class="sectitle">Welcome!</div>
        <div>
        <p>
			Rumpus Radio is a new online radio station for Punk music. 
		</p>
		<p>
			The idea behind Rumpus Radio is that any independent Punk band is able to 
			upload their music. The music will then be played on the streaming radio 
			for the community to enjoy. 
		</p>
        <p>
        	Want to get your music heard? <a href="register.html">Sign Up Now!</a>
        </p>
        </div>
		<div><br /><br /><br /><br /></div>
		<div style="width:100%;text-align:center;" >
			<a style="decoration:none" href="http://radio.rumpusradio.com/rumpusradio.m3u" alt="Listen to Rumpus Radio">
			<img src="images/tunein.png" />
			</a>
		</div>
    </div>
</body>
</html>
