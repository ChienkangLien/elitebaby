package com.tibame.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tibame.web.dao.MealOrderDetailDAO;
import com.tibame.web.vo.MealOrderDetailVO;
import com.tibame.web.vo.MealVO;

public class MealOrderDetailDAOImpl implements MealOrderDetailDAO {

	private DataSource ds;

	public MealOrderDetailDAOImpl() {
		try {
			ds = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/example");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(MealOrderDetailVO MealOrderDetailVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(MealOrderDetailVO MealOrderDetailVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer mealOrderDetailId) {
		// TODO Auto-generated method stub

	}

	@Override
	public MealOrderDetailVO findByPrimaryKey(Integer mealOrderDetailId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MealOrderDetailVO> findByMealOrderId(Integer id) {
		String sql = "SELECT MEAL_ORDER_DETAIL_ID, MEAL_ORDER_ID, od.MEAL_ID, ORDER_COUNT, MEAL_PRICE FROM MEAL_ORDER_DETAIL od JOIN MEAL m ON m.MEAL_ID = od.MEAL_ID where MEAL_ORDER_ID = ?;";
		List<MealOrderDetailVO> list = new ArrayList<MealOrderDetailVO>();

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					MealOrderDetailVO mealOrderDetail = new MealOrderDetailVO();
					Integer mealOrderDetailId = rs.getInt(1);
					Integer mealOrderId = rs.getInt(2);
					Integer mealId = rs.getInt(3);
					Integer orderCount = rs.getInt(4);
					Integer mealPrice = rs.getInt(5);
					System.out.println(mealOrderDetailId + ", " + mealOrderId + ", " + mealId + ", "
							+ orderCount + ", " + mealPrice);

					mealOrderDetail.setMealOrderDetailId(mealOrderDetailId);;
					mealOrderDetail.setMealOrderId(mealOrderId);
					mealOrderDetail.setMealId(mealId);
					mealOrderDetail.setOrderCount(orderCount);
					mealOrderDetail.setMealPrice(mealPrice);
					list.add(mealOrderDetail);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
//		return null;
	}

	@Override
	public List<MealOrderDetailVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
