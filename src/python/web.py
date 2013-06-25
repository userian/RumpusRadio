import urllib, cookielib, urllib2, re

urllib2.debuglevel = 1

HOSTNAME = 'www22.verizon.com'
HOME_PAGE = '/spot/common/Login/Login.aspx'
LOGIN_PAGE = '/spot/common/login/login.fcc'
SELECT_LOCATIONS = 	'/spot/resources/Order/SelectLocations.aspx'
LEAD_ENTRY_PAGE = '/spot/resources/Order/leadEntry.aspx'
CUSTOMER_PROFILE_PAGE = '/spot/Resources/Order/CustomerProfile.aspx'

viewstate = ''
cj = cookielib.CookieJar()
opener = urllib2.build_opener( urllib2.HTTPCookieProcessor(cj) )
opener.addheaders = [
	('User-agent', 'Mozilla/5.0 (compatible; MSIE 5.5; Windows NT)')
]
	#might not need to keep track of Referer which would be totally awesome...
	#('Referer', 'https://www22.verizon.com/spot/common/Login/Login.aspx')

def spot_login():
	global opener
	global viewstate

	login_post_data = {}
	login_post_data['__VIEWSTATE'] = '/wEPDwULLTE0NjgxNTEwMDhkZA=='
	login_post_data['target'] = 'https://www22.verizon.com/spot/resources/registration/preprocess.aspx'
	login_post_data['USER'] = 'mitwpadmin1'
	login_post_data['PASSWORD'] = 'anticouni'


	print "Loggingin in..."
	post_data = urllib.urlencode( login_post_data )
	data = opener.open( 'https://' + HOSTNAME + LOGIN_PAGE, post_data ).read()
	#update __VIEWSTATE
	matches = re.findall('id="__VIEWSTATE" value="(.*?)"', data )
	for match in matches:
		viewstate = match

def select_city():
	global opener
	global viewstate
	print "Selecting Santa Barbara..."
	select_sb_post_data = {}
	select_sb_post_data['__EVENTTARGET'] = 'rptLocations$ctl00$lnkLocationName'
	select_sb_post_data['__EVENTARGUMENT'] = ''
	select_sb_post_data['__VIEWSTATE'] = viewstate
	post_data = urllib.urlencode( select_sb_post_data )

	data = opener.open( 'https://' + HOSTNAME + SELECT_LOCATIONS, post_data ).read()
	#update __VIEWSTATE
	matches = re.findall('id="__VIEWSTATE" value="(.*?)"', data )
	for match in matches:
		viewstate = match

def check_phonenumber( phonenumber ):		
	global opener
	global viewstate

	packages = { 'freedom' : 0, 'vas' : 0, 'fios' : 0 , 'fiostv' : 0, 'hsi' : 0 }

	print "Checking Number..."
	lead_post = {}
	lead_post['__VIEWSTATE'] = viewstate 
	lead_post['__EVENTTARGET'] = 'GO'
	lead_post['__EVENTARGUMENT'] = ''
	lead_post['Authorize'] = 'on'
	lead_post['newCustomerField'] = 'Y'
	lead_post['existVZServices'] = 'existVZServicesYes'
	lead_post['existVZhomephone'] = 'existVZhomephoneYes'
	lead_post['existVZwireless'] = 'existVZwirelessNo'
	lead_post['address1'] = ''
	lead_post['address2'] = ''
	lead_post['unitType'] = ''
	lead_post['UnitValue'] = ''
	lead_post['city'] = ''
	lead_post['state'] = ''
	lead_post['Zipcode'] = ''
#	lead_post['NPA'] = '909'
#	lead_post['NXX'] = '469'
#	lead_post['Last4Digits'] = '0575'
	lead_post['NPA'] = phonenumber[0:3]
	lead_post['NXX'] = phonenumber[3:6]
	lead_post['Last4Digits'] = phonenumber[6:10]
	lead_post['WinbackNPA'] = ''
	lead_post['WinbackNXX'] = ''
	lead_post['WinbackLast4Digits'] = ''
	lead_post['vzWNPA'] = ''
	lead_post['vzWNXX'] = ''
	lead_post['vzWLast4'] = ''
	lead_post['last4SSN'] = ''
	lead_post['hdnFlow'] = 'BTN'
	lead_post['hdnQualQuestions'] = 'Y|Y|N'
	print phonenumber[0:3] + " " + phonenumber[3:6] + " " + phonenumber[6:10]
	post_data = urllib.urlencode( lead_post )

	response = opener.open( 'https://' + HOSTNAME + LEAD_ENTRY_PAGE, post_data )
	data = response.read()
	current_url = response.geturl()

	#update __VIEWSTATE
	matches = re.findall('id="__VIEWSTATE" value="(.*?)"', data )
	for match in matches:
		viewstate = match
	if current_url != ( 'https://' + HOSTNAME + CUSTOMER_PROFILE_PAGE ):
		print "Not at Customer Profile URL. We might have a problem..."
		return packages

	#Get Available Packages
	profile_post = {}
	profile_post['__EVENTTARGET'] = 'lnkNext'
	profile_post['__EVENTARGUMENT'] = ''
	profile_post['__VIEWSTATE'] = viewstate
	profile_post['ChkCustomerPrimaryId'] = 'on'
	profile_post['hdnALFlow'] = '0'

	post_data = urllib.urlencode( profile_post )

	response = opener.open( 'https://' + HOSTNAME + CUSTOMER_PROFILE_PAGE, post_data )
	data = response.read()
	current_url = response.geturl()
	#print data

	#Parse for available products
	if 'tag="VZMM_FREEDOM_ORDERING_STATUS"' in data:
		packages['freedom'] = 1
	else:
		packages['freedom'] = 0

	if 'tag="VZMM_VAS_ORDERING_STATUS"' in data:
		packages['vas'] = 1
	else:
		packages['vas'] = 0

	if 'tag="VZMM_FIOS_ORDERING_STATUS"' in data:
		packages['fios'] = 1
	else:
		packages['fios'] = 0

	if 'tag="VZMM_FTV_ORDERING_STATUS"' in data:
		packages['fiostv'] = 1
	else:
		packages['fiostv'] = 0

	if 'tag="VZMM_DSL_ORDERING_STATUS"' in data:
		packages['hsi'] = 1
	else:
		packages['hsi'] = 0



	#return to the lead entry page
	data = opener.open( 'https://' + HOSTNAME + LEAD_ENTRY_PAGE, post_data ).read()
	#update __VIEWSTATE
	matches = re.findall('id="__VIEWSTATE" value="(.*?)"', data )
	for match in matches:
		viewstate = match

	return packages


def main():
	spot_login()
	select_city()
	
	customers = ( ('1', '9094690575'), ('2', '8056893677'), ('3', '7607760932'), ('2', '9514136038') )

	for customer in customers:
		packages = check_phonenumber( customer[1] )
		print packages


if __name__ == "__main__":
    main()





