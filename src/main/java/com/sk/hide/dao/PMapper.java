package com.sk.hide.dao;

import com.sk.hide.entity.P;


public interface PMapper {
	public P find(P p);
	
	int add(P p);
	
	int update(P p);
}
