package com.tibame.web.dao;

import java.util.List;

import com.tibame.web.vo.ReserveSampleVO;

public interface ReserverSampleDAO {
	
	public void insert(ReserveSampleVO ReserveSampleVO);
    public void update(ReserveSampleVO ReserveSampleVO);
    public void delete(Integer reserverId);
    public ReserveSampleVO findByPrimaryKey(Integer reserverId);
    public List<ReserveSampleVO> getAll();
}
