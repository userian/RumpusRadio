#!/usr/bin/python
import urllib, cookielib, urllib2, re

import MySQLdb

import time,datetime


urllib2.debuglevel = 1

PROFILE_HOSTNAME = 'profile.myspace.com'
PROFILE_PAGE = 'index.cfm?fuseaction=user.viewprofile'
MUSIC_DIRECTORY_HOSTNAME = 'collect.myspace.com'
MUSIC_DIRECTORY_PAGE = 'index.cfm?fuseaction=music.directory'


DB_HOST = "localhost"
DB_USER = "dbuser"
DB_PASS = "dbpass"
DB_DATABASE = "MyspaceBot"



cj = cookielib.CookieJar()
opener = urllib2.build_opener( urllib2.HTTPCookieProcessor(cj) )
opener.addheaders = [
    ('User-agent', 'Mozilla/5.0 (compatible; MSIE 5.5; Windows NT)')
]


def get_genres():

	global cursor, db

	response = opener.open( 'http://' + MUSIC_DIRECTORY_HOSTNAME + "/" + MUSIC_DIRECTORY_PAGE )
	data = response.read()
	current_url = response.geturl()

	matches = re.findall('<option value="(.*?)">(.*?)</option>', data )
	for match in matches:
		#print match
		if match[1] != 'Any Genre':
		    #query = """INSERT INTO tblGenres ( GenreID, GenreName ) VALUES ( '%s', '%s' ) """, match
		    #print query
		    cursor.execute( """INSERT INTO tblGenres ( GenreID, GenreName ) VALUES ( %s, %s ) """, match )
		    db.commit()
            
def scan_directory( genre_id ):

	global cursor, db

	page = 0
	my_token = ''
	alpha = ''


	#response = opener.open( 'http://' + HOSTNAME + "/" + MUSIC_DIRECTORY_PAGE )
	#data = response.read()

	while 1:
		print '---------------------- ' + str(page) + ' ----------------------'
		directory_post_data = {}	

		if my_token != '':
			directory_post_data['Mytoken'] = my_token

		if alpha != '':
			directory_post_data['alpha'] = alpha

		directory_post_data['GenreID'] = genre_id
		directory_post_data['fuseaction'] = 'music.directory'
		directory_post_data['page'] = page

		post_data = urllib.urlencode( directory_post_data )
		print post_data
		response = opener.open( 'http://' + MUSIC_DIRECTORY_HOSTNAME + "/" + MUSIC_DIRECTORY_PAGE, post_data )
		data = response.read()

		#Grab session token MyToken
		matches = re.findall("<input type=hidden name=Mytoken value=(.*?)>", data )
		my_token = matches[0]

		#Grab alpha : no clue what this is
		matches = re.findall('<input type="hidden" name="alpha" value="(.*?)">', data )
		alpha = matches[0]

    	
		#print data
        
		no_bands = re.search("No Bands To List", data)	
		if no_bands:
			return
        
		matches = re.findall('friendID=(.*?)"><img', data )
		for match in matches:
			print match;
			#see if band is already in the database
			cursor.execute( """SELECT BandID FROM tblBands WHERE MySpaceFriendID = %s """, match )
			row = cursor.fetchone()

			#if not then add it
			if row == None:	
				cursor.execute( """INSERT INTO tblBands ( MySpaceFriendID ) VALUES ( %s ) """, match )
				db.commit()


		#time.sleep(1)
		page += 1

#
#	scan_profiles()
#	
#	Get list of profiles to check
#
def scan_profiles():
	global cursor, db

	query = "SELECT MySpaceFriendID FROM tblBands WHERE DateUpdated = 0 OR DateDiff( DateUpdated, Now() ) > 7"
	cursor.execute( query )
	results = cursor.fetchall()
	for row in results:
		check_profile( row[0] )
	
#
#	check_profile( friend_id )
#
#	Load a profile and update the db with their current info
#
def check_profile( friend_id ):
	global cursor, db

	last_login = ''
	band_name = ''
	myspace_url = ''

	response = opener.open( 'http://' + PROFILE_HOSTNAME + "/" + PROFILE_PAGE + "&friendID=" + str( friend_id ) )
	data = response.read()
	#print data

	#make sure its valid 
	invalid_profile = re.search("Invalid Friend ID.", data)	
	if invalid_profile:
			return

	#get their special myspace url
	matches = re.findall('<span class="searchMonkey-displayURL">(.*?)</span>', data )
	for match in matches:
		myspace_url = match.strip();

	#get band name
	matches = re.findall('<span class="nametext">(.*?)</span>', data )
	for match in matches:
		band_name = match.strip();

	#get their last login
	matches = re.findall("Last Login:&nbsp;\r\s(.*?)<br>", data )
	for match in matches:
		last_login = datetime.datetime(*time.strptime( match.strip(), "%m/%d/%Y" )[0:5])
		

	print band_name

	#update the db
	try:
		cursor.execute( """UPDATE tblBands SET BandName = %s, LastLogin = %s, MySpaceURL = %s, DateUpdated = Now() WHERE MySpaceFriendID = %s """, ( band_name, last_login.strftime("%Y-%m-%d"), myspace_url, friend_id ) )
		db.commit()
	except:
		print "something broke"

	

if __name__ == "__main__":
    
	#connect to database
	db = MySQLdb.connect( host=DB_HOST, user=DB_USER, passwd=DB_PASS, db=DB_DATABASE )
	cursor = db.cursor()


	#directory_post_data = {}
	#directory_post_data['fuseaction'] = 'music.directory'
	#post_data = urllib.urlencode( directory_post_data )

	#punk is GenreID 34
	#scan_directory( 34 )
		

	scan_profiles()
	#check_profile( 74959178 )

	#print data
    
