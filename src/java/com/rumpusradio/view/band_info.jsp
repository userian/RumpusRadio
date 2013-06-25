<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link href="style.css" rel="stylesheet" type="text/css" />
	<title>Rumpus Radio | Internet Radio for Independent Bands - DEV SITE</title>
	<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
</head>
<body>

    <div id="logobox">
		<img src="images/rumpusradio.jpg" alt="Rumpus Radio" />
	</div>
	<div id="menu">
		<a href="index.html">Home</a> | <a href="listeners.html">Listeners</a> | <a href="bands.html">Bands</a>
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
    	<div id="regform" style="text-align:center;" />
    	<form:form >
	        <table>
	        	<tr>
	        		<td>Band Name</td>
	        		<td><form:input path="bandName" /></td>
	        		<td><form:errors path="bandName" /></td>
        		</tr>
				<tr>
					<td>Genre 1</td>
					<td>
						<form:select path="genre1" />
							<form:option value="-" label="Please Select"/>
            				<form:options items="${genreList}" itemValue="genreId" itemLabel="genreName"/>
						</form:select>
					</td>
					<td><form:errors path="genre1" /></td>
				</tr>
				<tr>
					<td>Genre 2</td>
					<td>
						<form:select path="genre2" />
							<form:option value="-" label="Please Select"/>
            				<form:options items="${genreList}" itemValue="genreId" itemLabel="genreName"/>
						</form:select>
					</td>
					<td><form:errors path="genre2" /></td>
				</tr>
				<tr>
					<td>Genre 3</td>
					<td>
						<form:select path="genre3" />
							<form:option value="-" label="Please Select"/>
            				<form:options items="${genreList}" itemValue="genreId" itemLabel="genreName"/>
						</form:select>
					</td>
					<td><form:errors path="genre3" /></td>
				</tr>
				<tr>
					<td>City</td>
					<td><form:password path="city" /></td>
					<td><form:errors path="city" /></td>
				</tr>
				<tr>
					<td>State</td>
					<td><form:password path="state" /></td>
					<td><form:errors path="state" /></td>
				</tr>
				<tr>
					<td>Description</td>
					<td><form:textarea  path="description" rows="3" cols="20"  /></td>
					<td><form:errors path="description" /></td>
				</tr>
				<tr>
					<td colspan="3"><input type="submit" value="Save" /></td>
				</tr>
	        </table>
        </form:form>
        </div>
    </div>
</body>
</html>