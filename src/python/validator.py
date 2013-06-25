#
#	validator.py
#	Ian Bruce
#	20080725
#	
#	Used to validate MP3 that have been recenly uploaded
#



import MySQLdb, os

#Database
dbhost = "localhost"
dbuser = "dbuser"
dbpass = "dbpass"
dbdatabase = "RumpusRadio"
#Music
musicroot = "/data/music/"

dbconn = MySQLdb.connect( dbhost, dbuser, dbpass, dbdatabase )
cursor = dbconn.cursor();

#Get all songs that haven't been verified 
cursor.execute("SELECT * FROM tblSong WHERE Status = '0'")

results = cursor.fetchall()
cursor.execute("set autocommit = 1")

# Run through list
for song in results:
	file = musicroot + str(song[1]) + "/" + str(song[2]) + "/" + str( song[6] )
	#print file
	check = os.popen("mp3check -es3 " + file ).read()
	#print results
	if check.rfind("not an audio mpeg stream") != -1:
		#do something
		print "Not MP3"
		cursor.execute("UPDATE tblSong SET Status = '-1' WHERE SongID = '" + str( song[0] ) + "'")		
	elif check.rfind("can't stat file") != -1:
		print "File Missing"
		cursor.execute("UPDATE tblSong SET Status = '-1' WHERE SongID = '" + str( song[0] ) + "'")
	else:
		#do something
		print "Song is good" + str( song[0] )
		cursor.execute("UPDATE tblSong SET Status = '1' WHERE SongID = '" + str( song[0] ) + "'")
		
