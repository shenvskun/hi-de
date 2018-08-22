package sk.advance.signandencrypt;

import org.apache.commons.codec.binary.Hex;
import org.springframework.util.Base64Utils;

public class Learn_Base64_Hex {
	public static void main(String[] args) throws Exception{
		hexCompareToBase64();
	}
	/*
	 * 思维过程
	 * 16进制和base64的共性
	 * 加密 
	 * 1 加密对象只能是字节数组 --先对原字符串进行编码
	 * 2解密的结果只能是字节数组
	 * 总结 加解密的起始都是字节数组，加解密是在编码解码的基础上完成的
	 * 另外 ：  hex只有对字符数组的解密方法  
	 */
	static void hexCompareToBase64() throws Exception{
		
		String s1 = "abcd";
		String s2 = "中文";
		System.out.println("字符串按码表编码后用 16进制 加密: " + Hex.encodeHexString(s1.getBytes()));
		System.out.println("16进制加密数据解为字符数组后用16进制解密： " + new String(Hex.decodeHex(Hex.encodeHexString(s1.getBytes()).toCharArray())));
		System.out.println("16进制: " + Hex.encodeHexString(s2.getBytes()));
		
		System.out.println("base64: " + Base64Utils.encodeToString(s1.getBytes()));
		System.out.println("base64: " + Base64Utils.encodeToString(s2.getBytes()));
		System.out.println("base64解密s2: " + new String(Base64Utils.decodeFromString(Base64Utils.encodeToString(s2.getBytes()))));
	}
}
