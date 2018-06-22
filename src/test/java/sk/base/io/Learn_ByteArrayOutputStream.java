package sk.base.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Learn_ByteArrayOutputStream {
	public static void main(String[] args) throws IOException {
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bao);
		dos.writeLong(345l);
		dos.close();
		
		ByteArrayInputStream bai = new ByteArrayInputStream(bao.toByteArray());
		DataInputStream di = new DataInputStream(bai);
		long l = di.readLong();
		System.out.println(l);
	}
}
