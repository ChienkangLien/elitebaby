package com.tibame.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tibame.web.dao.MealDAO;
import com.tibame.web.vo.MealVO;

import redis.clients.jedis.Jedis;

public class MealDAOImpl implements MealDAO {
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/elitebaby?useUnicode=yes&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Taipei";
//	String username = "root";
//	String password = "password";
	
	private DataSource ds;
	
	public MealDAOImpl() {
		try {
			ds = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/example");
        } catch (NamingException e) {
            e.printStackTrace();
        }
	}

	@Override
	public int insert(MealVO meal) {
		String sql = "insert into MEAL (MEAL_NAME, MEAL_PIC, MEAL_PRICE, MEAL_INFO, MEAL_STATUS) values (?,?,?,?,?);";
		
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, meal.getMealName());
			ps.setBytes(2, meal.getMealPic());
			ps.setInt(3, meal.getMealPrice());
			ps.setString(4, meal.getMealInfo());
			ps.setInt(5, meal.getMealStatus());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		jedis.close();
		return -1;
	}

	@Override
	public int update(MealVO meal) {
		String sql = "UPDATE Meal set MEAl_NAME=?, MEAL_PIC=?, MEAL_PRICE=?, MEAL_INFO=?, MEAL_STATUS=?  where meal_Id = ?";

		

		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, meal.getMealName());
			ps.setBytes(2, meal.getMealPic());
			ps.setInt(3, meal.getMealPrice());
			ps.setString(4, meal.getMealInfo());
			ps.setInt(5, meal.getMealStatus());
			ps.setInt(6, meal.getMealId());

			return ps.executeUpdate();

			// Handle any driver errors
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;

	}

	@Override
	public int delete(Integer id) {
		String sql = "delete from MEAL where MEAL_ID = ?;";

		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public MealVO findByPrimaryKey(Integer id) {
		String sql = "SELECT MEAL_ID, MEAL_NAME, MEAL_PRICE, MEAL_INFO, MEAL_STATUS FROM MEAL WHERE MEAL_ID = ?;";


		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Integer mealId = rs.getInt(1);
					String mealname = rs.getString(2);
					Integer mealprice = rs.getInt(3);
					String mealinfo = rs.getString(4);
					Integer mealstatus = rs.getInt(5);
					
					MealVO meal = new MealVO();
					meal.setMealId(mealId);
					meal.setMealName(mealname);
					meal.setMealPrice(mealprice);
					meal.setMealInfo(mealinfo);
					meal.setMealStatus(mealstatus);
					System.out.println(meal);
					return meal;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MealVO> getAll() {
		String sql = "SELECT MEAL_ID, MEAL_NAME, MEAL_PRICE, MEAL_INFO, MEAL_STATUS FROM MEAL;";
		List<MealVO> list = new ArrayList<MealVO>();
		
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					MealVO meal = new MealVO();
					Integer mealId = rs.getInt(1);
					String mealname = rs.getString(2);
//					byte[] mealpic = rs.getBytes(3);
					Integer mealprice = rs.getInt(3);
					String mealInfo = rs.getString(4);
					Integer mealstatus = rs.getInt(5);

					meal.setMealId(mealId);
					meal.setMealName(mealname);
//					meal.setMealPic(mealpic);
					meal.setMealPrice(mealprice);
					meal.setMealInfo(mealInfo);;
					meal.setMealStatus(mealstatus);
					list.add(meal);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getlength() {
		String sql = "SELECT count(MEAL_ID) FROM MEAL;";
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Integer count = rs.getInt(1);
					return count;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public byte[] getpic(Integer mealId) {
		String sql = "SELECT MEAL_PIC FROM MEAL WHERE MEAL_ID = ?;";
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, mealId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					byte[] pic = rs.getBytes(1);
					return pic;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
