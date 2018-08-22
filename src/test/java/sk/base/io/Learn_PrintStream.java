package sk.base.io;

import java.io.File;
import java.io.PrintWriter;

public class Learn_PrintStream {
	public static void main(String[] args) throws Exception{
		PrintWriter pw = new PrintWriter(new File("d:/b.txt"), "iso-8859-1");
		pw.println("dsfadf打发士大夫");
		pw.flush();
	}
}	
