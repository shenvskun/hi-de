package sk.base.dateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Learn_DateFormat {
	/**
          SimpleDateFormat函数语法：
         
          G 年代标志符
          y 年
          M 月
          d 日
          h 时 在上午或下午 (1~12)
          H 时 在一天中 (0~23)
          m 分
          s 秒
          S 毫秒
          E 星期
          D 一年中的第几天
          F 一月中第几个星期几
          w 一年中第几个星期
          W 一月中第几个星期
          a 上午 / 下午 标记符
          k 时 在一天中 (1~24)
          K 时 在上午或下午 (0~11)
          z 时区
     */
	
	public static void main(String[] args) throws ParseException {
		String s = "Oct 9, 2010 10:12:00 AM";
		SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy HH:mm:ss aa", Locale.US);
		Date parse = sdf.parse(s);
		System.out.println(parse.getTime());
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf2.format(parse));
	}
}
