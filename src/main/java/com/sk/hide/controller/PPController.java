package com.sk.hide.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.hide.dao.PMapper;
import com.sk.hide.dao.PjnlMapper;
import com.sk.hide.entity.P;
import com.sk.hide.entity.Pjnl;
import com.sk.hide.entity.User;
import com.sk.hide.exception.HideException;

@SuppressWarnings({"unchecked","rawtypes"})
@RestController
public class PPController {
	@Autowired
	private PMapper pm;
	
	@Autowired
	private PjnlMapper pjm;
	
	@GetMapping("/qd")
	public Map qd(HttpServletRequest request) throws Exception {
		System.out.println("======进入PPController=====");
		
		//利用transDate addType uid type查流水 避免重复签到
		
		Pjnl pj = new Pjnl();
		pj.setUid(Integer.parseInt(request.getParameter("uid")));
		pj.setTransDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		pj.setAddType("05");
		pj.setPstatus("0");
		pj.setType("0");
		pj.setValue(1);
		Pjnl find = pjm.find(pj);
		if(find != null ) {
			throw new HideException(400, "不允许重复签到");
		}
		
		pjm.add(pj);
		//查积分 有记录更新  没记录插入
		P p = new P();
		p.setUid(Integer.parseInt(request.getParameter("uid")));
		p.setType("0");
		P p2 = pm.find(p);
		
		if(p2 == null ) {
			p.setValue(1);
			p.setBeginDate(pj.getTransDate());
			p.setSevenDate(pj.getTransDate());
			pm.add(p);
		} else {
			p2.setValue(p2.getValue()+1);
			//第一次签到
			if(p2.getBeginDate()==null || p2.getBeginDate().isEmpty()) {
				p2.setBeginDate(pj.getTransDate());
				p2.setSevenDate(pj.getTransDate());
				pm.update(p2);
			}else {
				//7
				//从sevenDate起查流水
				Pjnl pj2 = new Pjnl();
				pj2.setUid(pj.getUid());
				pj2.setAddType("05");
				pj2.setStatus("0");
				pj2.setTransDate(p2.getSevenDate());
				
				//查7条 不够7条 或者第7条的日期日期！=当前calendar-6 或者第七条条的日期在开始日期之前
				List<Pjnl> pjList = pjm.findList(pj2);
				Calendar c2 = Calendar.getInstance();
				c2.add(Calendar.DATE, 1);
				String newBeginDate = new SimpleDateFormat("yyyyMMdd").format(c2.getTime());
				
				c2.add(Calendar.DATE, -7);
				String seventh = new SimpleDateFormat("yyyyMMdd").format(c2.getTime());
				//有7条连续的记录
				if(pjList.size() >= 7 && seventh.equals(pjList.get(6).getTransDate())) {
					
					p2.setValue(p2.getValue()+5); 
					p2.setSevenDate(newBeginDate);
				} 
				//不为空
				//30
				//定时任务把30周期往后推
				//value+20外 sevenDate beginDate更新
				pj2.setTransDate(p2.getBeginDate());
				List<Pjnl> pjl2 = pjm.findList(pj2);
				if(pjl2.size() == 30) {
					p2.setValue(p2.getValue() + 20);
					
					p2.setBeginDate(newBeginDate);
					p2.setSevenDate(newBeginDate);
				}
				pm.update(p2);
			}
		}
		Map retMap  = new HashMap();
		return retMap;
	}
	@GetMapping("/bq")
	public Map bq(HttpServletRequest request) throws Exception {
		System.out.println("======进入testMybatisController=====");
		String id = request.getParameter("id");
		
		User user = new User();
		user.setId(Integer.parseInt(id));
//		user = um.find(user);
		
		Thread.sleep(2000);
		Map retMap  = new HashMap();
		retMap.put("name", user.getName());
		return retMap;
	}
	@GetMapping("/q")
	public Map q(HttpServletRequest request) throws Exception {
		System.out.println("======进入testMybatisController=====");
		String id = request.getParameter("id");
		
		User user = new User();
		user.setId(Integer.parseInt(id));
//		user = um.find(user);
		
		Thread.sleep(2000);
		Map retMap  = new HashMap();
		retMap.put("name", user.getName());
		return retMap;
	}

	
	
}
