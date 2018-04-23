package com.sk.hide.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.sk.hide.exception.HideException;

public class DataUtil {
	public static List getList(String fileName) {
		List pageIdList;
		String pageIds = getData(fileName);
		if(StringUtils.isEmpty(pageIds)) {
			pageIdList = new ArrayList();
		} else {
			pageIdList = JSON.parseObject(pageIds, List.class);
		}
		return pageIdList;
	}

	public static void saveData(String onpageJson, String fileName) {
		FileOutputStream fo = null;
		try {
			fo = new FileOutputStream("C:/devTool/data/hidetemp/" + fileName + ".txt");
			fo.write(onpageJson.getBytes());
		} catch (Exception e) {
			throw new HideException(400, "保存数据出错!");
		} finally {
			if(fo != null) {
				try {
					fo.close();
				} catch (IOException e) {
					throw new HideException(400, "关闭文件输出流出错!");
				}
			}
		}
	}

	public static String getData(String fileName) {
		FileInputStream fi = null;
		StringBuffer sb = new StringBuffer();
		try {
			fi = new FileInputStream("C:/devTool/data/hidetemp/" + fileName + ".txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fi));
			String line = null;
			
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			
//			if(StringUtils.isEmpty(sb.toString())) {
//				return null;
//			} 
			
			return sb.toString();
		} catch (Exception e) {
			System.out.println("----controller读取本地数据出错！！-----");
			return sb.toString();
		} finally {
			try {
				if(fi!=null) {
					fi.close();
				}
			} catch (IOException e) {
				System.out.println("----controller读取本地数据关闭输入流出错！！-----");
			}
		}
	}
	
	public static <T> T mapToBean(Map<String, Object> map,Class<T> clazz) { 
		T bean = null;
		try {
			bean = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		BeanMap beanMap = BeanMap.create(bean); 
		beanMap.putAll(map); 
		return bean; 
	} 
}
