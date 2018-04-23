package com.sk.hide.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.UUID;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.sk.hide.entity.Annotation;
import com.sk.hide.entity.Book;
import com.sk.hide.entity.KeWen;
import com.sk.hide.entity.OnePage;
import com.sk.hide.entity.Page;
import com.sk.hide.util.DataUtil;

@RestController
public class NoteController {
	
	@PostMapping("/note/save")
	public String save(@RequestBody OnePage onePage) {
		System.out.println("------------进入保存笔记controller-------------------");

		String divContent = onePage.getKeWen().getContent();
		System.out.println("------------打印课文内容-------------------");
		System.out.println(divContent);
		System.out.println("---------------解码之后的样子---------------");
		System.out.println(URLDecoder.decode(divContent));
		
		if (onePage.getBook() != null) {
			//获取已存的book
			List bookList = DataUtil.getList("book");
			//重复的就更新
			if (bookList.size() > 0) {
				ListIterator<Map> li = bookList.listIterator();
				while (li.hasNext()) {
					Book book = DataUtil.mapToBean(li.next(), Book.class);
					if (book.getBookId().equals(onePage.getBook().getBookId())) {
						li.remove();
					}
				}
			}
			bookList.add(onePage.getBook());
			//存储book
			DataUtil.saveData(JSON.toJSONString(bookList), "book");
		}
		
		if (onePage.getPage() != null && !StringUtils.isEmpty(onePage.getPage().getPageId())) {
			//获取已存的page
			List pageIdList = DataUtil.getList("page");
			// 重复的就更新
			if (pageIdList.size() > 0) {
				ListIterator<Map> li = pageIdList.listIterator();
				while (li.hasNext()) {
					Page page = DataUtil.mapToBean(li.next(), Page.class);
					if (page.getPageId().equals(onePage.getPage().getPageId())) {
						li.remove();
					}
				}
			}
			pageIdList.add(onePage.getPage());
			//存储页面
			DataUtil.saveData(JSON.toJSONString(pageIdList), "page");
		}
		
		if (onePage.getAnnos().size() > 0) {
			//获取已存的anno
			List annoList = DataUtil.getList("anno");
			// 重复的就更新
			if (annoList.size() > 0) {
				ListIterator<Map> li = annoList.listIterator();
				while (li.hasNext()) {
					Annotation annotation = DataUtil.mapToBean(li.next(), Annotation.class);
					if (annotation.getPageId().equals(onePage.getPage().getPageId())) {
						li.remove();
					}
				}
			}
			annoList.addAll(onePage.getAnnos());
			//存储注释
			DataUtil.saveData(JSON.toJSONString(annoList), "anno");
		}
		
		if (onePage.getKeWen() != null && !StringUtils.isEmpty(onePage.getKeWen().getContent())) {
			//获取已存的kewen
			List kewenList = DataUtil.getList("keWen");
			// 重复的就更新
			if (kewenList.size() > 0) {
				ListIterator<Map> li = kewenList.listIterator();
				while (li.hasNext()) {
					KeWen keWen = DataUtil.mapToBean(li.next(), KeWen.class);
					if (keWen.getId().equals(onePage.getPage().getPageId())) {
						li.remove();
					}
				}
			}
			kewenList.add(onePage.getKeWen());
			//存储课文
			DataUtil.saveData(JSON.toJSONString(kewenList), "keWen");
		}
		
		return "success";
	}

	
	
	@GetMapping("/getBooks")
	public List getBooks() {
		System.out.println("--------------进入getBooksController------------------");
		
		return DataUtil.getList("book");
	}
	
	@GetMapping("/uniqueId")
	public String uniqueId() {
		System.out.println("--------------进入uniqueIdController------------------");
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	@GetMapping("/getPageIds")
	public List getPages(String bookId) {
		System.out.println("--------------进入getPageIdsController------------------");
		
		List<Page> theBookPages = new ArrayList();
		if(StringUtils.isEmpty(bookId)){
			return theBookPages;
		}
		
		List<Map> pages = DataUtil.getList("page");
		
		if(pages.size()>0) {
			for (Map map : pages) {
				Page page = DataUtil.mapToBean(map, Page.class);
				if(page.getBookId().equals(bookId)) {
					theBookPages.add(page);
				}
			}
		} 
		
		return theBookPages;
	}
	
	@GetMapping("/getKeWenAnnos")
	public OnePage getKeWenAnnos(String pageId) {
		System.out.println("--------------进入getPageIdsController------------------");
		
		OnePage op = new OnePage();
		List<Annotation> retAnnos = new ArrayList();
		op.setAnnos(retAnnos);
		if(StringUtils.isEmpty(pageId)){
			return op;
		}
		
		List<Map> annos = DataUtil.getList("anno");
		List<Map> keWens = DataUtil.getList("keWen");
		
		if(annos.size()>0) {
			for (Map map : annos) {
				Annotation anno = DataUtil.mapToBean(map, Annotation.class);
				if(anno.getPageId().equals(pageId)) {
					retAnnos.add(anno);
				}
			}
		} 
		if(keWens.size()>0) {
			for (Map map : keWens) {
				KeWen keWen = DataUtil.mapToBean(map, KeWen.class);
				if(keWen.getId().equals(pageId)) {
					op.setKeWen(keWen);
				}
			}
		}
		
		return op;
	}
}
