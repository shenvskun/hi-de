package sk.base.contantpool;

public class Learn_ContantPool {

	public static void main(String[] args) {
		String s2 = "sdsf";
		String s = new String("sdsf");
		String s1 = s.intern();
		System.out.println(s2==s1);
	}

}
