package com.dao;

import java.util.List;

import com.util.Jdbc;
import com.vo.VipTypevo;

/**
 * vipType ∑√Œ ≤„
 * 
 * @author ¥ÛÀ≥
 *
 */
public class VipTypedao {

	// ÃÌ
	public int insert(String name, double discount) {
		String sql = "insert into vipType( vtname, vtdiscount) values (?, ?)";
		return Jdbc.update(sql, discount);
	}

	// –ﬁ
	public int update(int id, String name, double discount) {
		String sql = "update vipType set vtname=?,vtdiscount=? where vtid=?";
		return Jdbc.update(sql, name, discount, id);
	}

	// …æ
	public int delete(int id) {
		String sql = "delete from vipType where vtid=?";
		return Jdbc.update(sql, id);
	}

	public List<VipTypevo> selectAll() {
		String sql = "select * from vipType";
		return Jdbc.queryForList(sql, VipTypevo.class);
	}
	
	public VipTypevo selectType(int id) {
		String sql = "select * from vipType where vtid=?";
		return Jdbc.queryForObject(sql, VipTypevo.class,id);
	}

}
