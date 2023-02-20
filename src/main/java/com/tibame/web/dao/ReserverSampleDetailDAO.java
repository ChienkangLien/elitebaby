package com.tibame.web.dao;

import java.util.List;

import com.tibame.web.vo.ReserverSampleDetailVO;

public interface ReserverSampleDetailDAO {

	public void insert(ReserverSampleDetailVO ReserverSampleDetailVO);
    public void update(ReserverSampleDetailVO ReserverSampleDetailVO);
    public void delete(Integer reserverDatailId);
    public ReserverSampleDetailVO findByPrimaryKey(Integer reserverDatailId);
    public List<ReserverSampleDetailVO> getAll();
    
}
