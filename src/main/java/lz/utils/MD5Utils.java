package lz.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Utils {

	public static String getMd5(String str){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for(int offset=0;offset<b.length;offset++){
				i=b[offset];
				if(i<0)
					i+=256;
				if(i<16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
			//return buf.toString().substring(8,24);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
