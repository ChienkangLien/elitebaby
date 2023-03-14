package com.tibame.web.dao;

import java.util.List;
import java.util.Set;

import com.tibame.web.vo.CartVO;
import com.tibame.web.vo.MealVO;

public interface CartDAO {
	public int insert(CartVO cart);
    public int update(CartVO cart);
    public int delete(CartVO cart);
    public Set<String> findByPrimaryKey(Integer id);
    public List<MealVO> getAll(Integer id);
}
