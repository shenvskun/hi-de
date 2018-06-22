package com.sk.hide.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sk.hide.dao.PMapper;
import com.sk.hide.entity.P;

@Component
public class PPtask {
	@Autowired
	private PMapper pm;
	
	@Scheduled(cron = "0/10 * * * * ?")
	public void excute() {
		List<P> cyc30list = pm.findCYC30List();
		Calendar c2 = Calendar.getInstance();
		c2.add(Calendar.DATE, 1);
		String newBeginDate = new SimpleDateFormat("yyyyMMdd").format(c2.getTime());
		for (P p : cyc30list) {
			p.setBeginDate(newBeginDate);
			p.setSevenDate(newBeginDate);
			pm.update(p);
		}
	}
}
