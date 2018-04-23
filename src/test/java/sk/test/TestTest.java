package sk.test;


import java.util.List;
import java.util.Map;

import org.springframework.cglib.beans.BeanMap;

import com.google.gson.Gson;
import com.sk.hide.entity.Book;
import com.sk.hide.util.DataUtil;

public class TestTest {

	public static void main(String[] args) {
		String s = "[{\"bookId\":\"5da65744d44047e4977becbd1fa48132\",\"bookName\":\"第一个笔记本\"}]";
//		List<Map> bookList = JSON.parseObject(s, List.class);
//		System.out.println(bookList.get(0).get("bookId"));
		
		Gson gson = new Gson();
		List<Map> bookList = gson.fromJson(s, List.class);
		
		Book book = DataUtil.mapToBean(bookList.get(0), Book.class);
		System.out.println(book.getBookId());
	}

}
