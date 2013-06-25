#
#	playlist.py
#	Ian Bruce
#	20080822
#	
#	Used to update the radio's playlist
#

import MySQLdb


#Database
dbhost = "localhost"
dbuser = "dbuser"
dbpass = "dbpass"
dbdatabase = "RumpusRadio"

dbconn = MySQLdb.connect( dbhost, dbuser, dbpass, dbdatabase )  
cursor = dbconn.cursor();
   


def getNextSong():
    global cursor
    
    query = "SELECT COUNT( SongID ) FROM tblSong WHERE Status = '1' "
    
    cursor.execute( query )
    
    song_count = cursor.fetchone()
    
    query = "SELECT ArtistID, AlbumID, FileName, SongID FROM tblSong "
    query += " WHERE Status = '1' "
    query += " AND SongID NOT IN ( "
    query += "    SELECT SongID "
    query += "    FROM tblPlaylist "
    query += "    WHERE ( CURRENT_TIMESTAMP - DatePlayed ) < 600 ) "
    query += " ORDER BY RAND() "
    query += " Limit 1 "
    
    #Get all songs that haven't been verified 
    cursor.execute( query )
    
    result = cursor.fetchone()
    
    query = " INSERT INTO tblPlaylist ( SongID ) VALUES ('" + str( result[3] ) + "')"
    cursor.execute( query )
    
    song_file = str( result[0] ) + "/" + str( result[1] ) + "/" + str( result[2] )
    
    return song_file


def getCurrentSong():
    global cursor
    
    query = "SELECT tblArtist.ArtistName, tblSong.SongName FROM tblSong, tblArtist "
    query += " WHERE tblArtist.ArtistID = tblSong.ArtistID "
    query += " AND SongID = ( SELECT SongID FROM tblPlaylist ORDER BY DatePlayed DESC LIMIT 1 ) "

    cursor.execute( query )
    
    result = cursor.fetchone()
    
    track = result[0] + " - " + result[1]
    
    return track