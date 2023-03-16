package com.tibame.web.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		} else if (order.getOrderPrice() != null) { // 若有價格、代表是房客預約單
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
		String sql = "SELECT  o.ORDER_START_DATE, o.ORDER_END_DATE, o.ORDER_RESIDENT, o.ORDER_REMARK, o.ORDER_PRICE, o.ORDER_STATUS FROM ROOM_ORDER o where o.ROOM_ORDER_ID = ?;";

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
					String orderStatus = rs.getString(6);

					order.setOrderStartDate(orderStartDate);
					order.setOrderEndDate(orderEndDate);
					order.setOrderResident(orderResident);
					order.setOrderRemark(orderRemark);
					order.setOrderPrice(orderPrice);
					order.setOrderStatus(orderStatus);

					return order;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int checkExistingNumForUpdate(Map<String, String> map) {
		// 欲變更的日期若存在超過一筆訂單、代表有其他既有訂單
		String sql = "select count(*) from ROOM_ORDER  where ROOM_ID = ? and(? between ORDER_START_DATE and ORDER_END_DATE or ? between ORDER_START_DATE and ORDER_END_DATE or (? < ORDER_START_DATE and ?> ORDER_END_DATE)) and ORDER_STATUS != '完成單'";
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, Integer.parseInt(map.get("roomId")));
			ps.setString(2, map.get("startDate"));
			ps.setString(3, map.get("endDate"));
			ps.setString(4, map.get("startDate"));
			ps.setString(5, map.get("endDate"));

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					if (rs.getInt(1) > 1)
						return 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public int checkbelong(Map<String, String> map) {
		// 欲變更的日期若存在只有一筆訂單、但是該訂單不屬於這位使用者、代表是屬於其他住客
		String sql = "select ROOM_ORDER_ID=? from ROOM_ORDER  where ROOM_ID = ? and(? between ORDER_START_DATE and ORDER_END_DATE or ? between ORDER_START_DATE and ORDER_END_DATE or (? < ORDER_START_DATE and ?> ORDER_END_DATE)) and ORDER_STATUS != '完成單'";
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, Integer.parseInt(map.get("orderId")));
			ps.setInt(2, Integer.parseInt(map.get("roomId")));
			ps.setString(3, map.get("startDate"));
			ps.setString(4, map.get("endDate"));
			ps.setString(5, map.get("startDate"));
			ps.setString(6, map.get("endDate"));

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					if (rs.getInt(1) != 1)
						return 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int checkExistingNumForInsert(RoomOrderVO order) {
		// 驗證是否晚了一步新增訂單
		String sql = "select count(*) from ROOM_ORDER  where ROOM_ID = ? and(? between ORDER_START_DATE and ORDER_END_DATE or ? between ORDER_START_DATE and ORDER_END_DATE or (? < ORDER_START_DATE and ?> ORDER_END_DATE)) and ORDER_STATUS != '完成單'";
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, order.getRoomId());
			ps.setDate(2, order.getOrderStartDate());
			ps.setDate(3, order.getOrderEndDate());
			ps.setDate(4, order.getOrderStartDate());
			ps.setDate(5, order.getOrderEndDate());

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					if (rs.getInt(1) > 0)
						return 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int checkExistingNumForUpdate(RoomOrderVO order) {
		// 驗證是否晚了一步新增訂單
		String sql = "select count(*) from ROOM_ORDER  where ROOM_ID = ? and(? between ORDER_START_DATE and ORDER_END_DATE or ? between ORDER_START_DATE and ORDER_END_DATE or (? < ORDER_START_DATE and ?> ORDER_END_DATE)) and ORDER_STATUS != '完成單'";
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, order.getRoomId());
			ps.setDate(2, order.getOrderStartDate());
			ps.setDate(3, order.getOrderEndDate());
			ps.setDate(4, order.getOrderStartDate());
			ps.setDate(5, order.getOrderEndDate());

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					if (rs.getInt(1) > 1)
						return 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public int checkbelong(RoomOrderVO order) {
		String sql = "select ROOM_ORDER_ID=? from ROOM_ORDER  where ROOM_ID = ? and(? between ORDER_START_DATE and ORDER_END_DATE or ? between ORDER_START_DATE and ORDER_END_DATE or (? < ORDER_START_DATE and ?> ORDER_END_DATE)) and ORDER_STATUS != '完成單'";
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, order.getRoomOrderId());
			ps.setInt(2, order.getRoomId());
			ps.setDate(3, order.getOrderStartDate());
			ps.setDate(4, order.getOrderEndDate());
			ps.setDate(5, order.getOrderStartDate());
			ps.setDate(6, order.getOrderEndDate());

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					if (rs.getInt(1) != 1)
						return 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

}
