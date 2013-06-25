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
			<div class="sectitle">Album</div>
			<div class="m">$message</div>
    	<div id="infoform"/>
		<form id="sform" method="post">
		<input type="hidden" id="d" name="d" value="0" />
			<table>
				<tr>
					<td>Album Name:</td>
					<td>#springFormInput("command.albumName" "")</td>
					<td>#springShowErrors("" "")</td>
				</tr>
				<tr>
					<td>&nbsp;</td><td><input type="submit" value="Save"/></td>
					<td align="right"><input type="button" value="Delete Album" onclick="return deletealbum();"/></td>
				</tr>
			</table>
		</form>
		<b>Songs</b>
    	<hr />
	    	<table width="100%">
	    	<tr><td align="center" width="50">Track</td><td align="left">Title</td><td>&nbsp;</td></tr>
	    	#foreach( $song in $songs )
	    		<tr>
	    			<td align="center" width="50">$song.trackNumber</td>
	    			<td align="left"><a href="song.html?s=$song.songId">$song.songName</a></td>
	    			<td>
    				#if( $song.status == 0 )	
    					<span class="s_message">Awaiting Validation</span> 
   					#elseif ( $song.status == 1 )
    					<span class="s_message"></span> 
   					#elseif ( $song.status == -1 )		
    					<span class="s_message">Invalid MP3 file ( Delete or Re-Upload )</span> 
    				#end
				</td>
    			</tr>
	   		#end
	    	</table>
	 	    <a href="newsong.html?a=$command.albumId">[Add New Song]</a>
 	    </div>
    </div>
    
</body>
</html>