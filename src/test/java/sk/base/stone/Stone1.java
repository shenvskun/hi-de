package sk.base.stone;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class Stone1 {

	@Test
	public void test() {
		BigDecimal b = new BigDecimal((String)validate("3.33"));
		System.out.println(b);
	}
	
	public <T> T  validate(Object val) {
		return (T) val;
	}

}
