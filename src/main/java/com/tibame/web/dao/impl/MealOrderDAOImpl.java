package com.tibame.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tibame.web.dao.MealOrderDAO;
import com.tibame.web.vo.MealOrderVO;

public class MealOrderDAOImpl implements MealOrderDAO {

	private DataSource ds;

	public MealOrderDAOImpl() {
		try {
			ds = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/example");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(MealOrderVO MealOrderVO) {
		String sql = "insert into `MEAL_ORDER`(`USER_ID`,`ORDER_PAYMENT`, `ADDRESS`,`AUTH_CODE`) values (?,?,?,?);";

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, MealOrderVO.getUserId());
			ps.setInt(2, MealOrderVO.getOrderPayment());
			ps.setString(3, MealOrderVO.getAddress());
			ps.setString(4, MealOrderVO.getAuthCode());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int update(MealOrderVO MealOrderVO) {
		String sql = "UPDATE MEAL_ORDER set ORDER_STATUS=?, ADDRESS=? where MEAL_ORDER_ID = ?";

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, MealOrderVO.getOrderStatus());
			ps.setString(2, MealOrderVO.getAddress());
			ps.setInt(3, MealOrderVO.getMealOrderId());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;

	}

	@Override
	public int delete(Integer mealOrderId) {
		String sql = "delete from MEAL_ORDER where MEAL_ORDER_ID = ?;";

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, mealOrderId);
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<MealOrderVO> findByUserId(Integer id) {
		String sql = "SELECT * FROM MEAL_ORDER WHERE USER_ID = ?;";
		List<MealOrderVO> list = new ArrayList<MealOrderVO>();

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Integer mealOrderId = rs.getInt(1);
					Integer userId = rs.getInt(2);
					Integer orderPayment = rs.getInt(3);
					Integer orderStatus = rs.getInt(4);
					String address = rs.getString(5);
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String orderDate = dateFormat.format(rs.getTimestamp(6));
					String authCode = rs.getString(7);

					MealOrderVO mealorder = new MealOrderVO();
					mealorder.setMealOrderId(mealOrderId);
					mealorder.setUserId(userId);
					mealorder.setOrderPayment(orderPayment);
					mealorder.setOrderStatus(orderStatus);
					mealorder.setAddress(address);
					mealorder.setOrderDate(orderDate);
					mealorder.setAuthCode(authCode);
//					System.out.println(mealorder);
					list.add(mealorder);
				}
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MealOrderVO> getAll() {
		String sql = "SELECT * FROM MEAL_ORDER;";
		List<MealOrderVO> list = new ArrayList<MealOrderVO>();

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					MealOrderVO mealOrder = new MealOrderVO();
					Integer mealOrderId = rs.getInt(1);
					Integer userId = rs.getInt(2);
					Integer orderPayment = rs.getInt(3);
					Integer orderStatus = rs.getInt(4);
					String address = rs.getString(5);
					Timestamp timestamp = rs.getTimestamp(6);
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String orderDate = dateFormat.format(timestamp);
					String authCode = rs.getString(7);

					mealOrder.setMealOrderId(mealOrderId);
					mealOrder.setUserId(userId);
					mealOrder.setOrderPayment(orderPayment);
					mealOrder.setOrderStatus(orderStatus);
					mealOrder.setAddress(address);
					mealOrder.setOrderDate(orderDate);
					mealOrder.setAuthCode(authCode);
					list.add(mealOrder);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list != null ? list : null;
	}

	@Override
	public List<MealOrderVO> findByMealOrderId(Integer id) {
		String sql = "SELECT * FROM MEAL_ORDER WHERE MEAL_ORDER_ID = ?;";
		List<MealOrderVO> list = new ArrayList<MealOrderVO>();

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Integer mealOrderId = rs.getInt(1);
					Integer userId = rs.getInt(2);
					Integer orderPayment = rs.getInt(3);
					Integer orderStatus = rs.getInt(4);
					String address = rs.getString(5);
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String orderDate = dateFormat.format(rs.getTimestamp(6));
					String authCode = rs.getString(7);

					MealOrderVO mealorder = new MealOrderVO();
					mealorder.setMealOrderId(mealOrderId);
					mealorder.setUserId(userId);
					mealorder.setOrderPayment(orderPayment);
					mealorder.setOrderStatus(orderStatus);
					mealorder.setAddress(address);
					mealorder.setOrderDate(orderDate);
					mealorder.setAuthCode(authCode);
					list.add(mealorder);
				}
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MealOrderVO> findByMealOrderIdWithUser(Integer userid, Integer orderId) {
		String sql = "SELECT * FROM MEAL_ORDER WHERE MEAL_ORDER_ID = ? AND USER_ID = " + userid + ";";
		List<MealOrderVO> list = new ArrayList<MealOrderVO>();
		System.out.println("執行到使用者單筆訂單查詢 userID為"+userid+" 訂單ID為 "+orderId);
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, orderId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Integer mealOrderId = rs.getInt(1);
					Integer userId = rs.getInt(2);
					Integer orderPayment = rs.getInt(3);
					Integer orderStatus = rs.getInt(4);
					String address = rs.getString(5);
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String orderDate = dateFormat.format(rs.getTimestamp(6));
					String authCode = rs.getString(7);

					MealOrderVO mealorder = new MealOrderVO();
					mealorder.setMealOrderId(mealOrderId);
					mealorder.setUserId(userId);
					mealorder.setOrderPayment(orderPayment);
					mealorder.setOrderStatus(orderStatus);
					mealorder.setAddress(address);
					mealorder.setOrderDate(orderDate);
					mealorder.setAuthCode(authCode);
					list.add(mealorder);
				}
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
