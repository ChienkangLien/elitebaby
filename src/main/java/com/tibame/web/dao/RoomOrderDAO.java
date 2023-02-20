package com.tibame.web.dao;

import java.util.List;

import com.tibame.web.vo.RoomOrderVO;


public interface RoomOrderDAO {
	public int insert(RoomOrderVO order);
	public int update(RoomOrderVO order);
	public int updateStatus(RoomOrderVO order);
	public int delete(RoomOrderVO order);
	public List<RoomOrderVO> getAll();
}
