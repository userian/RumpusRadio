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
        <div class="sectitle">Music</div>
   		<div class="m"></div>
		## **** SONGS ****
		<b>Songs</b>
    	<hr />
    	<div id="songs">
    	<table width="100%">
    	#foreach( $song in $model.songs )
    		<tr>
    			<td><a href="song.html?s=$song.songId">$song.songName</a></td>
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
    	</div>
    	<div id="albums">
 	    <a href="newsong.html">[Add New Song]</a><br /><br />
		## **** Albums ****
    	<b>Albums</b>
    	<hr />
    	<table>
    	#foreach( $album in $model.albums )
    		<tr><td><a href="album.html?a=$album.albumId">$album.albumName</a></td></tr>
   		#end
    	</table>
 	    <a href="newalbum.html">[Add New Album]</a>
 	    </div>
    </div>
</body>
</html>