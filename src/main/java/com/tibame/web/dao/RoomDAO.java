package com.tibame.web.dao;

import java.util.List;

import com.tibame.web.vo.RoomVO;


public interface RoomDAO {
	public int insert(RoomVO room);
	public int update(RoomVO room);
	public int delete(RoomVO room);
	public RoomVO findByPrimaryKey(RoomVO room);
	public List<RoomVO> getAll();	
}
