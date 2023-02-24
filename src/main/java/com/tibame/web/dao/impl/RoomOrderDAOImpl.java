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

public class RoomOrderDAOImpl implements RoomOrderDAO {
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
		if (order.getOrderPrice() == null) {
			String sql = "insert into ROOM_ORDER(ROOM_ID, ORDER_START_DATE, ORDER_END_DATE, "
					+ "ORDER_REMARK, ADMIN_ID, ORDER_STATUS) values (?,?,?,?,?,?);";

			try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setInt(1, order.getRoomId());
				ps.setDate(2, order.getOrderStartDate());
				ps.setDate(3, order.getOrderEndDate());
				ps.setString(4, order.getOrderRemark());
				ps.setInt(5, order.getAdminId());
				ps.setString(6, order.getOrderStatus());
				return ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (order.getOrderPrice() != null) {
			String sql = "insert into ROOM_ORDER(ROOM_ID, ORDER_START_DATE, ORDER_END_DATE, ORDER_RESIDENT, "
					+ "ORDER_PRICE, ORDER_REMARK, USER_ID, ORDER_STATUS) values (?,?,?,?,?,?,?,?);";

			try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setInt(1, order.getRoomId());
				ps.setDate(2, order.getOrderStartDate());
				ps.setDate(3, order.getOrderEndDate());
				ps.setString(4, order.getOrderResident());
				ps.setInt(5, order.getOrderPrice());
				ps.setString(6, order.getOrderRemark());
				ps.setInt(7, order.getUserId());
				ps.setString(8, order.getOrderStatus());
				return ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	@Override
	public int update(RoomOrderVO order) {
		String sql = "update ROOM_ORDER set ORDER_START_DATE = ?, ORDER_END_DATE = ?, ORDER_RESIDENT = ?, ORDER_PRICE = ?,  ORDER_REMARK = ? where ROOM_order_ID = ?;";

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setDate(1, order.getOrderStartDate());
			ps.setDate(2, order.getOrderEndDate());
			ps.setString(3, order.getOrderResident());
			ps.setInt(4, order.getOrderPrice());
			ps.setString(5, order.getOrderRemark());
			ps.setInt(6, order.getRoomOrderId());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int delete(RoomOrderVO order) {
		String sql = "delete from ROOM_ORDER where ROOM_ORDER_ID = ?;";

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, order.getRoomOrderId());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<RoomOrderVO> getAll(String status, Integer limit) {
		String sql = "SELECT o.ROOM_ORDER_ID, t.ROOM_TYPE_NAME, r.ROOM_NAME, o.ORDER_START_DATE, o.ORDER_END_DATE, o.ORDER_CREATE_TIME, o.ORDER_CHANGE_TIME, o.ORDER_RESIDENT, o.ORDER_PRICE, m.USER_NAME, a.ADMIN_NAME, o.ORDER_REMARK, o.ORDER_STATUS FROM ROOM_ORDER o left join MEMBER m on o.USER_ID = m.USER_ID left join ADMINISTRATOR a on o.ADMIN_ID = a.ADMIN_ID left join ROOM r on o.ROOM_ID = r.ROOM_ID join ROOM_TYPE t on r.ROOM_TYPE_ID = t.ROOM_TYPE_ID where o.ORDER_STATUS= ? order by o.ROOM_ORDER_ID limit ?, 5;";
		List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, status);
			ps.setInt(2, limit);
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
					if (orderChangeTime != null) {
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

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, order.getOrderStatus());
			ps.setInt(2, order.getRoomOrderId());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<RoomOrderVO> getByRoomId(Integer roomId) {
		String sql = "select o.ORDER_START_DATE, o.ORDER_END_DATE, o.ORDER_REMARK, o.ORDER_RESIDENT, m.USER_NAME, a.ADMIN_NAME from ROOM_ORDER o left join MEMBER m on o.USER_ID = m.USER_ID left join ADMINISTRATOR a on o.ADMIN_ID = a.ADMIN_ID where o.ROOM_ID= ?;";
		List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, roomId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					RoomOrderVO order = new RoomOrderVO();
					Date orderStartDate = rs.getDate(1);
					Date orderEndDate = rs.getDate(2);
					String orderRemark = rs.getString(3);
					String orderResident = rs.getString(4);
					String userName = rs.getString(5);
					String adminName = rs.getString(6);

					order.setOrderStartDate(orderStartDate);
					order.setOrderEndDate(orderEndDate);
					order.setOrderRemark(orderRemark);
					order.setOrderResident(orderResident);
					order.setUserName(userName);
					order.setAdminName(adminName);

					list.add(order);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<RoomOrderVO> getByUserId(Integer userId) {
		String sql = "SELECT o.ROOM_ORDER_ID, t.ROOM_TYPE_NAME, r.ROOM_NAME, o.ORDER_START_DATE, o.ORDER_END_DATE, o.ORDER_CREATE_TIME, o.ORDER_CHANGE_TIME, o.ORDER_RESIDENT, o.ORDER_PRICE, o.ORDER_REMARK, o.ROOM_ID FROM ROOM_ORDER o left join ROOM r on o.ROOM_ID = r.ROOM_ID join ROOM_TYPE t on r.ROOM_TYPE_ID = t.ROOM_TYPE_ID where o.USER_ID = ?;";
		List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, userId);
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
					String orderRemark = rs.getString(10);
					Integer roomId = rs.getInt(11);

					order.setRoomOrderId(roomOrderId);
					order.setRoomTypeName(roomTypeName);
					order.setRoomName(roomName);
					order.setOrderStartDate(orderStartDate);
					order.setOrderEndDate(orderEndDate);
					order.setFormattedCreateTimestamp(orderCreateTime);
					if (orderChangeTime != null) {
						order.setFormattedChangeTimestamp(orderChangeTime);
					}
					order.setOrderResident(orderResident);
					order.setOrderPrice(orderPrice);
					order.setOrderRemark(orderRemark);
					order.setRoomId(roomId);
					list.add(order);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public RoomOrderVO getByOrderId(Integer orderId) {
		String sql = "SELECT  o.ORDER_START_DATE, o.ORDER_END_DATE, o.ORDER_RESIDENT, o.ORDER_REMARK, o.ORDER_PRICE FROM ROOM_ORDER o where o.ROOM_ORDER_ID = ?;";

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, orderId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					RoomOrderVO order = new RoomOrderVO();
					Date orderStartDate = rs.getDate(1);
					Date orderEndDate = rs.getDate(2);
					String orderResident = rs.getString(3);
					String orderRemark = rs.getString(4);
					Integer orderPrice = rs.getInt(5);

					order.setOrderStartDate(orderStartDate);
					order.setOrderEndDate(orderEndDate);
					order.setOrderResident(orderResident);
					order.setOrderRemark(orderRemark);
					order.setOrderPrice(orderPrice);
					
					return order;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
