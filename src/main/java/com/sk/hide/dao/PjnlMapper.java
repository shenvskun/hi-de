package com.sk.hide.dao;

import java.util.List;

import com.sk.hide.entity.Pjnl;


public interface PjnlMapper {
	public Pjnl find(Pjnl p);
	
	List<Pjnl> findList(Pjnl p);
	
	int add(Pjnl p);
	
	int update(Pjnl p);
}
