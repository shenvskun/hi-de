package sk.advance.signandencrypt;

import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.springframework.util.Base64Utils;

public class Learn_RSA_DSA_ECDSA {
	public static String priKey;
	public static String pubKey;
	
	public static void main(String[] args) throws Exception{
//		genKeypairBycode();
//		genDSAKeypairBycode();
		genKeypairFromFile();
//		validateDSAKeys(priKey, pubKey); //DSA只能用签名验签方式验证
//		validateRSAKeys(priKey, pubKey);
		
	}
	
	//代码方式生成密钥对 并转换为Base64字符串
	static void genKeypairBycode() {
		KeyPairGenerator keyPairGen = null;
		try {
			keyPairGen = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		keyPairGen.initialize(1024); //初始化密钥长度
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		
		String base64PrivateKey = Base64Utils.encodeToString(privateKey.getEncoded());
		System.out.println("base64字符串私钥："  + base64PrivateKey);
		String base64PublicKey = Base64Utils.encodeToString(publicKey.getEncoded());
		System.out.println("base64字符串公钥："  + base64PublicKey);
		
		priKey = base64PrivateKey;
		pubKey = base64PublicKey;
	}
	
	static void genDSAKeypairBycode() {
		KeyPairGenerator keyPairGen = null;
		try {
			keyPairGen = KeyPairGenerator.getInstance("DSA"); //只能用DSA RSA【加密 生成密钥算法 DSA不能加解密】 不能用SHA1withDSA【签名算法】
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		keyPairGen.initialize(1024); //初始化密钥长度
		KeyPair keyPair = keyPairGen.generateKeyPair();
		DSAPublicKey publicKey = (DSAPublicKey) keyPair.getPublic();
		DSAPrivateKey privateKey = (DSAPrivateKey) keyPair.getPrivate();
		
		String base64PrivateKey = Base64Utils.encodeToString(privateKey.getEncoded());
		System.out.println("base64字符串私钥："  + base64PrivateKey);
		String base64PublicKey = Base64Utils.encodeToString(publicKey.getEncoded());
		System.out.println("base64字符串公钥："  + base64PublicKey);
		
		priKey = base64PrivateKey;
		pubKey = base64PublicKey;
	}
	
	static void genKeypairFromFile() throws Exception {
		//从keystore文件中获取私钥
		FileInputStream fi = new FileInputStream("d:/1.keystore");
		KeyStore ks = KeyStore.getInstance("JKS");
		ks.load(fi, "123456".toCharArray());
		 //一个库中多个密钥对 使用下面这句的密码区分不同密钥对？=用keyAlias区分
		PrivateKey myPrivateKey = (PrivateKey)ks.getKey("sk", "123456".toCharArray());
		String privateKeyStr = Base64Utils.encodeToString(myPrivateKey.getEncoded());
		
		//从keystore中拿到公钥
		Certificate certificate = ks.getCertificate("sk");
		PublicKey publicKey2 = certificate.getPublicKey();
		String publicKey2Str = Base64Utils.encodeToString(publicKey2.getEncoded());
		
		//从证书文件中获取公钥
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		FileInputStream in = new FileInputStream("d:/sk.cer");
		  //生成一个证书对象并使用从输入流 inStream 中读取的数据对它进行初始化。
		Certificate c = cf.generateCertificate(in);
		PublicKey publicKey = c.getPublicKey();
		String publicKeyStr = Base64Utils.encodeToString(publicKey.getEncoded());
		
		System.out.println("base64字符串私钥："  + privateKeyStr);
		System.out.println("base64字符串公钥："  + publicKeyStr);
		System.out.println("两种方式获得公钥对比 ：" + publicKey2Str.equals(publicKeyStr));
		//字符串转成公私钥
		PKCS8EncodedKeySpec pkcs8 = new PKCS8EncodedKeySpec(Base64Utils.decodeFromString(privateKeyStr)); 
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PrivateKey newPrivateKey = kf.generatePrivate(pkcs8);
		
		X509EncodedKeySpec x509 = new X509EncodedKeySpec(Base64Utils.decodeFromString(publicKeyStr));
		PublicKey newPublicKey = kf.generatePublic(x509);
		//				        通过下面这段代码提取的私钥是否正确
		 String before = "asdf";
		 byte[] plainText = before.getBytes("UTF-8");
		 String cipherAlg = kf.getAlgorithm();
		 System.out.println(cipherAlg);
		 Cipher cipher = Cipher.getInstance(cipherAlg);  // RSA/ECB/PKCS1Padding   RSA
		 cipher.init(Cipher.ENCRYPT_MODE, newPublicKey);
		 // 用公钥进行加密，返回一个字节流
		 byte[] cipherText = cipher.doFinal(plainText);
		 cipher.init(Cipher.DECRYPT_MODE, newPrivateKey);
		 // 用私钥进行解密，返回一个字节流
		 byte[] newPlainText = cipher.doFinal(cipherText);
		 System.out.println(new String(newPlainText, "UTF-8"));
	}
	
	//验证字符串公私钥
	static void validateKeysByEncrypt(String privateKeyStr, String publicKeyStr) throws Exception {
//		System.out.println("base64字符串私钥："  + privateKeyStr);
//		System.out.println("base64字符串公钥："  + publicKeyStr);
		
		//字符串转成公私钥
		PKCS8EncodedKeySpec pkcs8 = new PKCS8EncodedKeySpec(Base64Utils.decodeFromString(privateKeyStr)); 
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PrivateKey newPrivateKey = kf.generatePrivate(pkcs8);
		
		X509EncodedKeySpec x509 = new X509EncodedKeySpec(Base64Utils.decodeFromString(publicKeyStr));
		PublicKey newPublicKey = kf.generatePublic(x509);
		//				        通过下面这段代码提取的私钥是否正确
		 String before = "asdf";
		 byte[] plainText = before.getBytes("UTF-8");
		 String cipherAlg = kf.getAlgorithm();
		 System.out.println(cipherAlg);
		 Cipher cipher = Cipher.getInstance("RSA");  // RSA/ECB/PKCS1Padding   RSA
		 cipher.init(Cipher.ENCRYPT_MODE, newPublicKey);
		 // 用公钥进行加密，返回一个字节流
		 byte[] cipherText = cipher.doFinal(plainText);
		 cipher.init(Cipher.DECRYPT_MODE, newPrivateKey);
		 // 用私钥进行解密，返回一个字节流
		 byte[] newPlainText = cipher.doFinal(cipherText);
		 System.out.println(new String(newPlainText, "UTF-8"));
	}
	//验证字符串公私钥RSA
	static void validateRSAKeys(String privateKeyStr, String publicKeyStr) throws Exception {
//		System.out.println("base64字符串私钥："  + privateKeyStr);
//		System.out.println("base64字符串公钥："  + publicKeyStr);
		
		//字符串转成公私钥
		PKCS8EncodedKeySpec pkcs8 = new PKCS8EncodedKeySpec(Base64Utils.decodeFromString(privateKeyStr)); 
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PrivateKey newPrivateKey = kf.generatePrivate(pkcs8);
		
		X509EncodedKeySpec x509 = new X509EncodedKeySpec(Base64Utils.decodeFromString(publicKeyStr));
		PublicKey newPublicKey = kf.generatePublic(x509);
		//				        通过下面这段代码提取的私钥是否正确
		
		//签名
		String text = "签名的字符串";
		Signature signature = Signature.getInstance("SHA224withRSA");
		signature.initSign(newPrivateKey);
		signature.update(text.getBytes("utf-8"));
		byte[] result = signature.sign();
		
		Signature signature1 = Signature.getInstance("SHA224withRSA");
		signature1.initVerify(newPublicKey);
		signature1.update(text.getBytes("utf-8"));
		boolean verify = signature1.verify(result);
		System.out.println(verify);
		
	}
	//验证字符串公私钥DSA只能用于签名
	static void validateDSAKeys(String privateKeyStr, String publicKeyStr) throws Exception {
		System.out.println("base64字符串私钥："  + privateKeyStr);
		System.out.println("base64字符串公钥："  + publicKeyStr);
		
		//字符串转成公私钥
		PKCS8EncodedKeySpec pkcs8 = new PKCS8EncodedKeySpec(Base64Utils.decodeFromString(privateKeyStr)); 
		KeyFactory kf = KeyFactory.getInstance("DSA");
		PrivateKey newPrivateKey = kf.generatePrivate(pkcs8);
		
		X509EncodedKeySpec x509 = new X509EncodedKeySpec(Base64Utils.decodeFromString(publicKeyStr));
		PublicKey newPublicKey = kf.generatePublic(x509);
		//				        通过下面这段代码提取的私钥是否正确
		
		//签名
		String text = "签名的字符串";
		Signature signature = Signature.getInstance("SHA224withDSA");
		signature.initSign(newPrivateKey);
		signature.update(text.getBytes("utf-8"));
		byte[] result = signature.sign();
		
		signature.initVerify(newPublicKey);
		signature.update(text.getBytes("utf-8"));
		boolean verify = signature.verify(result);
		System.out.println(verify);
		
	}
	
}
