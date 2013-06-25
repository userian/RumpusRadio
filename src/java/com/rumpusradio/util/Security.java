package com.rumpusradio.util;

import java.security.*;
import java.math.BigInteger;

public class Security {

		public static String MD5( String string ) {
			
			String hashString = null;
			
			 try {
				 MessageDigest md5 = MessageDigest.getInstance("MD5");
				 md5.update(string.getBytes());
				 BigInteger hash = new BigInteger(1, md5.digest());
				 hashString = hash.toString(16);
			 } catch (NoSuchAlgorithmException nsae) {
				 //ignore
			 }
				
			return hashString;
		}
}
