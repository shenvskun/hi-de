package sk.base.io;

public class Learn_Encode {
	public static void main(String[] args) throws Exception{
		System.out.println(new String("中".getBytes("iso8859-1")));
		System.out.println(new String(new byte[]{63}, "utf-8"));
		
		byte[] b = "b".getBytes("utf-8");
		for (byte c : b) {
			System.out.println(c);
		}
		
		System.out.println(System.getProperty("file.encoding"));
		
		
	}
}
