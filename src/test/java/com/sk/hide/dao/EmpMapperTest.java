package com.sk.hide.dao;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sk.hide.config.RootConfig;
import com.sk.hide.pojo.Emp;
import com.sk.hide.pojo.EmpExample;
import com.sk.hide.pojo.EmpExample.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)    
@ContextConfiguration(classes = RootConfig.class) 
public class EmpMapperTest {

	@Autowired
	private EmpMapper em;
	
	@Test
	public void testCountByExample() {
		EmpExample ee = new EmpExample();
		EmpExample.Criteria cc = ee.createCriteria();
		cc.andDeptnoEqualTo(10);
		int count = em.countByExample(ee);
		System.out.println(count);
	}

	@Test
	public void testSelectByprimaryKey() {
		Emp emp = em.selectByPrimaryKey(7369);
		System.out.println(emp.getEname());
	}
	
	@Test
	public void testInsert() {
		
	}

}
