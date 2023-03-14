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
import com.tibame.web.vo.CartVO;
import com.tibame.web.vo.MealOrderDetailVO;

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
	public int insert(Integer mealId, Integer count, String authCode) {
		String sql = "insert into `MEAL_ORDER_DETAIL`(`MEAL_ID`,`ORDER_COUNT`,`AUTH_CODE`) values (?,?,?)";

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, mealId);
			ps.setInt(2, count);
			ps.setString(3, authCode);
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
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
	public List<MealOrderDetailVO> findByAuthCode(String authCode) {
		String sql = "SELECT MEAL_ORDER_DETAIL_ID, od.MEAL_ID, ORDER_COUNT, MEAL_PRICE ,AUTH_CODE FROM MEAL_ORDER_DETAIL od JOIN MEAL m ON m.MEAL_ID = od.MEAL_ID where AUTH_CODE = ?";
		List<MealOrderDetailVO> list = new ArrayList<MealOrderDetailVO>();

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, authCode);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					MealOrderDetailVO mealOrderDetail = new MealOrderDetailVO();
					Integer mealOrderDetailId = rs.getInt(1);
					Integer mealId = rs.getInt(2);
					Integer orderCount = rs.getInt(3);
					Integer mealPrice = rs.getInt(4);
					System.out.println(
							mealOrderDetailId + ", " + mealId + ", " + orderCount + ", " + mealPrice + ", " + authCode);

					mealOrderDetail.setMealOrderDetailId(mealOrderDetailId);
					mealOrderDetail.setMealId(mealId);
					mealOrderDetail.setOrderCount(orderCount);
					mealOrderDetail.setMealPrice(mealPrice);
					mealOrderDetail.setAuthCode(authCode);
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
	public List<MealOrderDetailVO> findByAuthCodeForOrderDetail(String authCode) {
		String sql = "SELECT MEAL_ORDER_DETAIL_ID, od.MEAL_ID, MEAL_NAME, ORDER_COUNT, MEAL_PRICE FROM MEAL_ORDER_DETAIL od JOIN MEAL m ON m.MEAL_ID = od.MEAL_ID where AUTH_CODE = ?;";
		List<MealOrderDetailVO> list = new ArrayList<MealOrderDetailVO>();

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, authCode);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					MealOrderDetailVO mealOrderDetail = new MealOrderDetailVO();
					mealOrderDetail.setMealOrderDetailId(rs.getInt(1));
					mealOrderDetail.setMealId(rs.getInt(2));
					System.out.println(rs.getString(3));
					mealOrderDetail.setMealName(rs.getString(3));
					mealOrderDetail.setOrderCount(rs.getInt(4));
					mealOrderDetail.setMealPrice(rs.getInt(5));
					list.add(mealOrderDetail);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<MealOrderDetailVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
