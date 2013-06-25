<html>

<script src="/js/prototype.js" type="text/javascript"></script>

<script type="text/javascript">

function get_radio_into() {
	new Ajax.Request('/playlist.html',
	  {
	    method:'get',
	    onSuccess: function(transport){
	      var response = transport.responseText || "no response text";
	      alert("Success! \n\n" + response);
	    },
	    onFailure: function(){ alert('Something went wrong...') }
	  });
}

</script>

<body onload="get_radio_info();">
	<div>
		<table>
		<tr>
			<td>Band:</td><td><span id="radio_band"></span></td>
		</tr>
		<tr>
			<td>Song:</td><td><span id="radio_song"></span></td>
		</tr>
		</table>
	</div>
</body>
</html>