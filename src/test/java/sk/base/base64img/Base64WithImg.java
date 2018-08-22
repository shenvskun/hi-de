package sk.base.base64img;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64WithImg {
	static BASE64Encoder encoder = new sun.misc.BASE64Encoder();   //加密
	static BASE64Decoder decoder = new sun.misc.BASE64Decoder();   //解密
	
	public static void main(String[] args) {
//		String base64Str = getImageBinary(new File("d:/front.jpg"));
//		System.out.println(base64Str);
//		base64StringToImage(base64Str, "d:/ok.jpg");
		
		System.out.println(getImageBinary(new File("d:/front.jpg")));
	}
	
	private  static String getImageBinary(File f){      

        BufferedImage bi;      
        try {      
            bi = ImageIO.read(f);      
            ByteArrayOutputStream baos = new ByteArrayOutputStream();      
            ImageIO.write(bi, "png", baos);      
            byte[] bytes = baos.toByteArray();      

            return encoder.encodeBuffer(bytes).trim();      
        } catch (IOException e) {      
            e.printStackTrace();      
        }      
        return null;      
    }
	
	 private   static File base64StringToImage(String base64String, String filename){      
         try {      
             byte[] bytes1 = decoder.decodeBuffer(base64String);      
             ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);      
             BufferedImage bi1 =ImageIO.read(bais);      
             File w2 = new File(filename);//可以是jpg,png,gif格式      
             ImageIO.write(bi1, "jpg", w2);//不管输出什么格式图片，此处不需改动      改为png会使图片由50kb变成665kb
             return w2;
         } catch (IOException e) {      
             e.printStackTrace();      
         }
        return null;      
     }   
	 
	//加密2
		/**
		 * @Description: 根据图片地址转换为base64编码字符串
		 * @Author: 
		 * @CreateTime: 
		 * @return
		 */
		public static String getImageStr(String imgFile) {
		    InputStream inputStream = null;
		    byte[] data = null;
		    try {
		        inputStream = new FileInputStream(imgFile);
		        data = new byte[inputStream.available()];
		        inputStream.read(data);
		        inputStream.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    // 加密
		    BASE64Encoder encoder = new BASE64Encoder();
		    return encoder.encode(data);
		}
		
		//解密2
		/**
		 * @Description: 将base64编码字符串转换为图片
		 * @Author: 
		 * @CreateTime: 
		 * @param imgStr base64编码字符串
		 * @param path 图片路径-具体到文件
		 * @return
		*/
		public static boolean generateImage(String imgStr, String path) {
			if (imgStr == null)
				return false;
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				// 解密
				byte[] b = decoder.decodeBuffer(imgStr);
				// 处理数据
				for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
				}
				OutputStream out = new FileOutputStream(path);
				out.write(b);
				out.flush();
				out.close();
				return true;
			} catch (Exception e) {
				return false;
			}
		}
}
