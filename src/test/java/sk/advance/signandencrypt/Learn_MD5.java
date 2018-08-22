package sk.advance.signandencrypt;

import org.apache.commons.codec.binary.Hex;
import org.springframework.util.DigestUtils;

public class Learn_MD5 {

	public static void main(String[] args) {
		byte[] md5Digest = DigestUtils.md5Digest("cfsapppass".getBytes());
		System.out.println(Hex.encodeHexString(md5Digest));
	}

}
