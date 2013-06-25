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
		<div class="sectitle">Song</div>
		<div class="m">$message</div>
		<div class="m"></div>
		<form id="sform" method="post" enctype="multipart/form-data">
		<input type="hidden" id="d" name="d" value="0" />
		<table>
			<tr>
				<td>Name:</td><td>#springFormInput("command.songName" "")</td>
				<td align="right"><input type="button" value="Delete Song" onclick="return deletesong();"/></td>
			</tr>
			#if( $command.albumId != 0 )
			<tr><td>Ablum:</td><td>#springFormSingleSelect( "command.albumId" $albums "")</td></tr>
			<tr>
				<td>Track:</td>
				<td>#springFormInput("command.trackNumber" "")</td>
				<td>#springShowErrors("" "")</td>
			</tr>
			#end
			<tr><td></td><td><input type="submit" value="Save" /></td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td colspan="2">Re-Upload Song</td></tr>
			<tr><td>File:</td><td><input type="file" name="file" id="file"/></td></tr>
			<tr><td></td><td><input type="submit" value="Upload" id="uploadbutton"  onclick="startProgress();"/></td></tr>
		</table>
		</form>
		<div id="progressBar" style="display: none;">
			<div id="progressBarText"></div>
			<div id="progressBarBox">
			    <div id="progressBarBoxContent"></div>
			    [upload speeds my vary and are dependent on your internet connection]
			</div>
			
        </div>
    </div>
</body>
</html>