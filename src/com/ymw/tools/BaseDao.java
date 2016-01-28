package com.ymw.tools;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BaseDao {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;

	static {
		try {
			Properties p = new Properties();
			InputStream is = BaseDao.class.getResourceAsStream("/db.properties");
			p.load(is);
			driver = p.getProperty("driver");
			url = p.getProperty("url");
			user = p.getProperty("user");
			password = p.getProperty("password");
			Class.forName(driver);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 打开连接
	private Connection open() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 关闭连接
	private void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 插入
	public void add(Object object) {
		Class<? extends Object> clazz = object.getClass();
		// 写SQL语句
		StringBuffer sql = new StringBuffer();
		sql.append("insert into ");
		// 连接表名
		sql.append("`"+clazz.getSimpleName()+"`");
		// 连接字段
		sql.append("(");
		Field[] fields = clazz.getDeclaredFields();
		List<Field> fs = new ArrayList<Field>();
		for (Field f : fields) {
			f.setAccessible(true);
			try {
				if (f.get(object) != null) {
					fs.add(f);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < fs.size(); i++) {
			sql.append("`"+fs.get(i).getName()+"`");
			if(i!=fs.size()-1){
				sql.append(",");
			}
		}
		sql.append(")");
		// 连接字段值占位符
		sql.append(" values(");
		for (int i = 0; i < fs.size(); i++) {
			sql.append("?");
			if (i < fs.size() - 1) {
				sql.append(',');
			}
		}
		sql.append(")");
		// 连接数据库
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = open();
			ps = conn.prepareStatement(sql.toString());
			for (int i = 0; i < fs.size(); i++) {
				Object value = fs.get(i).get(object);
				if (value instanceof java.util.Date) {
					value = new java.sql.Date(((java.util.Date) value).getTime());
				}
				ps.setObject(i + 1, value);
			}
			if (ps.executeUpdate() > 0) {
				System.out.println("插入成功");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, null);
		}
	}

	// 删除
	public void delete(Object object) {
		Class<? extends Object> clazz = object.getClass();
		// 写SQL语句
		StringBuffer sql = new StringBuffer();
		sql.append("delete from `" + clazz.getSimpleName() + "` where ");
		Field[] fields = clazz.getDeclaredFields();
		List<Field> fs = new ArrayList<Field>();
		for (Field f : fields) {
			f.setAccessible(true);
			try {
				if (f.get(object) != null) {
					fs.add(f);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		for (Field f : fs) {
			sql.append("`"+f.getName() + "`=? and ");
		}
		sql.delete(sql.length() - 5, sql.length());
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = open();
			ps = conn.prepareStatement(sql.toString());
			for (int i = 0; i < fs.size(); i++) {
				Object value = fields[i].get(object);
				if (value instanceof java.util.Date) {
					value = new java.sql.Date(((java.util.Date) value).getTime());
				}
				ps.setObject(i + 1, value);
			}
			if (ps.executeUpdate() > 0) {
				System.out.println("删除成功");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, null);
		}

	}

	// 修改
	public void update(Object object, String... where) {
		Class<? extends Object> clazz = object.getClass();
		// 写SQL语句
		StringBuffer sql = new StringBuffer();
		sql.append("update `" + clazz.getSimpleName() + "` set ");
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			sql.append("`"+fields[i].getName() + "`=?");
			if (i < fields.length - 1) {
				sql.append(',');
			}
		}
		// 写条件
		if (where != null && where.length > 0) {
			sql.append(" where ");
			for (int i = 0; i < where.length; i++) {
				sql.append(where[i]);
				if (i < where.length - 1) {
					sql.append(" and ");
				}
			}
		}
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = open();
			ps = conn.prepareStatement(sql.toString());
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				Object value = fields[i].get(object);
				if (value instanceof java.util.Date) {
					value = new java.sql.Date(((java.util.Date) value).getTime());
				}
				ps.setObject(i + 1, value);
			}
			if (ps.executeUpdate() > 0) {
				System.out.println("修改成功");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, null);
		}
	}

	// 用类对象查询
	public <T> List<T> query(Class<T> clazz,String... where) {
		List<T> list = new ArrayList<T>();
		// 写sql语句
		StringBuffer sql = new StringBuffer();
		sql.append("select * from `" + clazz.getSimpleName()+"`");
		if(where!=null&&where.length>0){
			sql.append(" where ");
			for(int i = 0;i<where.length;i++){
				sql.append(where[i]);
				if(i!=where.length-1){
					sql.append(" and ");
				}
			}
		}
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = open();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				T obj = clazz.newInstance();
				Field[] fields = clazz.getDeclaredFields();
				for (Field f : fields) {
					f.setAccessible(true);
					Object value = rs.getObject(f.getName());
					f.set(obj, TypeConversion(value));
				}
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return list;
	}
	
	// 用类对象分页查询
	public <T> List<T> query(Class<T> clazz,Pages pages,String... where) {
		List<T> list = new ArrayList<T>();
		StringBuffer sb = new StringBuffer();
		if(where!=null&&where.length>0){
			sb.append(" where ");
			for(int i = 0; i<where.length;i++){
				sb.append(where[i]);
				if(i!=where.length-1){
					sb.append(",");
				}
			}
		}
		// 写sql语句
		String sql = String.format("select * from `%s`%s limit %d,%d", clazz.getSimpleName(),sb.toString(),pages.getMin()-1,pages.getPageRow());
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = open();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				T obj = clazz.newInstance();
				Field[] fields = clazz.getDeclaredFields();
				for (Field f : fields) {
					f.setAccessible(true);
					Object value = rs.getObject(f.getName());
					f.set(obj, TypeConversion(value));
				}
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return list;
	}

	// 类型转换
	private Object TypeConversion(Object value) {
		if (value instanceof BigDecimal) {
			String s = value.toString();
			if (s.indexOf('.') == -1) {
				if (Long.parseLong(s) > Integer.MAX_VALUE) {
					return Long.parseLong(s);
				} else {
					return Integer.parseInt(s);
				}
			} else {
				return Double.parseDouble(s);
			}
		} else if (value instanceof Timestamp) {
			return new java.util.Date(((Timestamp) value).getTime());
		} else {
			return value;
		}
	}

	// 用sql语句查询
	public List<Object[]> queryListObject(String sql) {
		List<Object[]> list = new ArrayList<Object[]>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = open();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			int columnCount = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				Object[] objects = new Object[columnCount];
				for (int i = 0; i < columnCount; i++) {
					objects[i] = TypeConversion(rs.getObject(i + 1));
				}
				list.add(objects);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return list;
	}

	// 用sql语句查询单个对象
	public Object[] queryObject(String sql) {
		List<Object[]> list = queryListObject(sql);
		Object[] objects = null;
		if (list != null && list.size() == 1) {
			objects = list.get(0);
		}
		return objects;
	}
	
}
