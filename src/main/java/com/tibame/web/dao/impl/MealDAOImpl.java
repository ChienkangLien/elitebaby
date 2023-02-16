package com.tibame.web.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tibame.web.dao.MealDAO;
import com.tibame.web.vo.MealVO;
import com.tibame.web.vo.RoomVO;

public class MealDAOImpl implements MealDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/elitebaby?useUnicode=yes&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Taipei";
	String username = "root";
	String password = "password";
	
	@Override
	public int insert(MealVO meal) {
		String sql = "insert into MEAL (MEAL_NAME, MEAL_QUANTITY, MEAL_PRICE, RESERVE_PRICE) values (?,?,?,?);";
		
		try (Connection con = DriverManager.getConnection(url,  username,  password);
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1,  meal.getMealName());
			ps.setInt(2,  meal.getMealQuantity());
			ps.setInt(3,  meal.getMealPrice());
			ps.setInt(4,  meal.getReserverPrice());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	@Override
	public void update(MealVO MealVO) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Integer mealId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public MealVO findByPrimaryKey(Integer mealId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<MealVO> getAll() {
		String sql = "SELECT MEAL_ID, MEAL_NAME, MEAL_PIC, MEAL_QUANTITY, MEAL_PRICE, RESERVE_PRICE, MEAL_STATUS FROM MEAL;";
		List<MealVO> list = new ArrayList<MealVO>();
		
//		try {
//			Class.forName(driver);
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		}
		
		try (Connection con = DriverManager.getConnection(url,  username,  password);
				PreparedStatement ps = con.prepareStatement(sql);) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					MealVO meal = new MealVO();
					Integer mealId = rs.getInt(1);
					String mealname = rs.getString(2);
					byte[] mealpic = rs.getBytes(3);
					Integer mealquantity = rs.getInt(4);
					Integer mealprice = rs.getInt(5);
					Integer reserverprice = rs.getInt(6);
					Integer mealstatus = rs.getInt(7);
					System.out.println(mealId+", "+mealname+", "+mealpic+", "+mealquantity+", "+mealprice+", "+reserverprice+", "+mealstatus);

					meal.setMealId(mealId);
					meal.setMealName(mealname);
					meal.setMealPic(mealpic);
					meal.setMealQuantity(mealquantity);
					meal.setMealPrice(mealprice);
					meal.setReservePrivce(reserverprice);
					meal.setMealStatus(mealstatus);
					list.add(meal);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void main(String[] args) {
		MealDAO meal = new MealDAOImpl();
		meal.getAll();
		System.out.println(meal);
	}
}
