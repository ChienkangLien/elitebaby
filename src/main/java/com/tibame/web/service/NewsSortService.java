package com.tibame.web.service;

import java.util.List;
import java.util.Set;

import com.tibame.web.dao.LatestNewsDAO;
import com.tibame.web.dao.NewsSortDAO;
import com.tibame.web.dao.NewsSortDAO_interface;
import com.tibame.web.vo.LatestNewsVO;
import com.tibame.web.vo.NewsSortVO;

public class NewsSortService {

	private NewsSortDAO_interface dao;
	
	public NewsSortService() {
		dao= new NewsSortDAO();
	}
	
	public NewsSortVO addNewsSortEmp(String sortName) {
		
		NewsSortVO newsSortVO = new NewsSortVO();
		
		newsSortVO.setSortName(sortName);
		dao.insert(newsSortVO);
		return newsSortVO;
	}
	
	public NewsSortVO updateNewsSortEmp(Integer sortId, String sortName) {
		
		NewsSortVO newsSortVO = new NewsSortVO();
		newsSortVO.setSortId(sortId);
		newsSortVO.setSortName(sortName);
		dao.update(newsSortVO);
		
		return newsSortVO;
	}
	
	public void deleteNewsSortEmp(Integer sortId) {
		dao.delete(sortId);
	}
	
	public  NewsSortVO getOneSort(Integer sortId) {
		return dao.findByPrimaryKey(sortId);
	}
	
	public List<NewsSortVO> getAll() {
		return dao.getAll();
	}
	
	
	public Set<LatestNewsVO> getEmpsByDeptno(Integer sortId) {
		return dao.getLatestNewsBySortId(sortId);
	}
}