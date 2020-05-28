package com.yb.util.ds;

public class runtesthmac {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static String getHmac(String inputHmacStr,String hmacKey) {



		String hmac = Md5Utils.encoderHmacSha256(inputHmacStr, hmacKey);


		return hmac;
	}

}
