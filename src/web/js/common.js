function deletesong() {
	var answer = confirm("Permenently Delete Song?")
	if (answer){
		document.getElementById('d').value=1;
		document.getElementById('sform').submit();
		return true;
	} else {
		return false;
	}
}
function deletealbum() {
	var answer = confirm("Permenently Delete Album and Songs?")
	if (answer){
		document.getElementById('d').value=1;
		document.getElementById('sform').submit();
		return true;
	} else {
		return false;
	}
}

function get_radio_info() {
    new Ajax.PeriodicalUpdater('nowplaying', '/nowplaying.html', {
            method: 'get', frequency: 5
        });    
}