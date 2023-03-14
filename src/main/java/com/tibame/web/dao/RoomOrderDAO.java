package com.tibame.web.dao;

import java.util.List;
import java.util.Map;

import com.tibame.web.vo.RoomOrderVO;


public interface RoomOrderDAO {
	public int insert(RoomOrderVO order);
	public int update(RoomOrderVO order);
	public int updateStatus(RoomOrderVO order);
	public int delete(RoomOrderVO order);
	public List<RoomOrderVO> getAll(String status, Integer limit);
	public List<RoomOrderVO> getByRoomId(Integer roomId);
	public List<RoomOrderVO> getByUserId(Integer userId);
	public RoomOrderVO getByOrderId(Integer orderId);
	public int checkExistingNumForUpdate(Map<String, String> map);
	public int checkExistingNumForUpdate(RoomOrderVO order);
	public int checkExistingNumForInsert(RoomOrderVO order);
	public int checkbelong(Map<String, String> map);
	public int checkbelong(RoomOrderVO order);
	
}
