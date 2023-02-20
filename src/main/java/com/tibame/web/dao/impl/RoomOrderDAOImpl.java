package com.tibame.web.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tibame.web.dao.RoomOrderDAO;
import com.tibame.web.vo.RoomOrderVO;


public class RoomOrderDAOImpl implements RoomOrderDAO{
	private DataSource ds;
	
	public RoomOrderDAOImpl() {
		try {
			ds = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/example");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int insert(RoomOrderVO order) {
		String sql = "insert into ROOM_ORDER(ROOM_ID, ORDER_START_DATE, ORDER_END_DATE, ORDER_RESIDENT, "
				+ "ORDER_PRICE, ORDER_REMARK, USER_ID, ADMIN_ID, ORDER_STATUS) values (?,?,?,?,?,?,?,?,?);";


		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
//			ps.setInt(1, order.getRoomId());
			ps.setDate(2, order.getOrderStartDate());
			ps.setDate(3, order.getOrderEndDate());
			ps.setString(4, order.getOrderResident());
			ps.setInt(5, order.getOrderPrice());
			ps.setString(6, order.getOrderRemark());
			ps.setInt(7, order.getUserId());
			ps.setInt(8, order.getAdminId());
			ps.setString(9, order.getOrderStatus());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;

	}

	@Override
	public int update(RoomOrderVO order) {
		String sql = "update ROOM_ORDER set ROOM_ID = ?, ORDER_START_DATE = ?, ORDER_END_DATE = ?, ORDER_RESIDENT = ?, ORDER_PRICE = ?,  ORDER_REMARK = ? where ROOM_order_ID = ?;";


		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
//			ps.setInt(1, order.getRoomId());
			ps.setDate(2, order.getOrderStartDate());
			ps.setDate(3, order.getOrderEndDate());
			ps.setString(4, order.getOrderResident());
			ps.setInt(5, order.getOrderPrice());
			ps.setString(6, order.getOrderRemark());
			ps.setInt(7, order.getRoomOrderId());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int delete(RoomOrderVO order) {
		String sql = "delete from ROOM_ORDER where ROOM_ORDER_ID = ?;";


		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, order.getRoomOrderId());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}


	@Override
	public List<RoomOrderVO> getAll() {
		String sql = "SELECT o.ROOM_ORDER_ID, t.ROOM_TYPE_NAME, r.ROOM_NAME, o.ORDER_START_DATE, o.ORDER_END_DATE, o.ORDER_CREATE_TIME, o.ORDER_CHANGE_TIME, o.ORDER_RESIDENT, o.ORDER_PRICE, m.USER_NAME, a.ADMIN_NAME, o.ORDER_REMARK, o.ORDER_STATUS FROM ROOM_ORDER o left join MEMBER m on o.USER_ID = m.USER_ID left join ADMINISTRATOR a on o.ADMIN_ID = a.ADMIN_ID left join ROOM r on o.ROOM_ID = r.ROOM_ID join ROOM_TYPE t on r.ROOM_TYPE_ID = t.ROOM_TYPE_ID;";
		List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();


		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					RoomOrderVO order = new RoomOrderVO();
					Integer roomOrderId = rs.getInt(1);
					String roomTypeName = rs.getString(2);
					String roomName = rs.getString(3);
					Date orderStartDate = rs.getDate(4);
					Date orderEndDate = rs.getDate(5);
					Timestamp orderCreateTime = rs.getTimestamp(6);
					Timestamp orderChangeTime = rs.getTimestamp(7);
					String orderResident = rs.getString(8);
					Integer orderPrice = rs.getInt(9);
					String userName = rs.getString(10);
					String adminName = rs.getString(11);
					String orderRemark = rs.getString(12);
					String orderStatus = rs.getString(13);
					

					order.setRoomOrderId(roomOrderId);
					order.setRoomTypeName(roomTypeName);
					order.setRoomName(roomName);
					order.setOrderStartDate(orderStartDate);
					order.setOrderEndDate(orderEndDate);
//					order.setOrderCreateTime(orderCreateTime);
//					order.setOrderChangeTime(orderChangeTime);
					order.setFormattedCreateTimestamp(orderCreateTime);
					if(orderChangeTime!=null) {
						order.setFormattedChangeTimestamp(orderChangeTime);
					}
					order.setOrderResident(orderResident);
					order.setOrderPrice(orderPrice);
					order.setUserName(userName);
					order.setAdminName(adminName);
					order.setOrderRemark(orderRemark);
					order.setOrderStatus(orderStatus);
					list.add(order);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updateStatus(RoomOrderVO order) {
		String sql = "update ROOM_ORDER set ORDER_STATUS = ? where ROOM_order_ID = ?;";

		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, order.getOrderStatus());
			ps.setInt(2, order.getRoomOrderId());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}
