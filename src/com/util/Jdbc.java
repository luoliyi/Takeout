package com.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * @author 大顺
 *
 */
public class Jdbc {

	static String DRIVE;//数据库驱动
	static String URL;//数据库连接路径
	static String USER;//数据库用户
	static String PASSWORD;//数据库用户密码
	
	//静态块
	static {
		Properties prop = new Properties();
		try {
			prop.load(Thread.currentThread().getContextClassLoader().
					getResourceAsStream("/resources/dbconfig.properties"));
			DRIVE = prop.getProperty("DRIVE");
			URL = prop.getProperty("URL");
			USER = prop.getProperty("USER");
			PASSWORD = prop.getProperty("PASSWORD");
			try {
				Class.forName(DRIVE);//加载驱动
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * get connection
	 * @return connection
	 */
	public static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * colse
	 * @param ps PreparedStatement
	 * @param con Connection
	 */
	public static void colse(PreparedStatement ps, Connection con) {
		try {
			try {
				if(ps != null) {
					ps.close();
				}
			} finally {
				if(con != null) {
					con.close();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * close
	 * @param rs ResultSet
	 * @param st Statement
	 * @param con Connection
	 */
	public static void close(ResultSet rs, Statement st, Connection con) {
        try {
            try {
                if (rs != null) {
                    rs.close();
                }
            } finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                } finally {
                    if (con != null)
                        con.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * insert/update/delete
	 * @param sql
	 * @param args
	 * @return implement Number
	 */
	public static int update (String sql, Object... args) {
		int result = 0;
		Connection con = getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			if(args != null) {
				for(int i=0; i<args.length; i++) {
					ps.setObject((i+1), args[i]);
				}
			}
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			colse(ps,con);
		}
		return result;
	}
	
	
	 /**
     * Query a single record
     * 
     * @param sql
     * @param args
     * @return Map<String,Object>
     */
    public static Map<String, Object> queryForMap(String sql, Object... args) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list = queryForList(sql, args);
        if (list.size() > 0) {
            result = list.get(0);
        }
        return result;
    }

    /**
     * Query a single record
     * 
     * @param sql
     * @param args
     * @return <T>
     */
    public static <T> T queryForObject(String sql, Class<T> clz, Object... args) {
        T result = null;
        List<T> list = queryForList(sql, clz, args);
        if (list.size() > 0) {
            result = list.get(0);
        }
        return result;
    }

    /**
     * Query a single record
     * 
     * @param sql
     * @param args
     * @return List<Map<String,Object>>
     */
    public static List<Map<String, Object>> queryForList(String sql, Object... args) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(sql);
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject((i + 1), args[i]);
                }
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));
                }
                result.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, ps, con);
        }
        return result;
    }

    /**
     * Query a single record
     * 
     * @param sql
     * @param args
     * @return List<T>
     */
    public static <T> List<T> queryForList(String sql, Class<T> clz, Object... args) {
        List<T> result = new ArrayList<T>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(sql);
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject((i + 1), args[i]);
                }
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();//获取查询列数
            while (rs.next()) {
                T obj = clz.newInstance();//实例对象
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = rsmd.getColumnName(i);
                    String methodName = "set" + columnName.substring(0, 1).toUpperCase()
                            + columnName.substring(1, columnName.length());
                    Method method[] = clz.getMethods();
                    for (Method meth : method) {
                        if (methodName.equals(meth.getName())) {
                           meth.invoke(obj, rs.getObject(i));
                        }
                    }
                }
                
                result.add(obj);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            close(rs, ps, con);
        }
        return result;
    }
    
	//调用存储过程  proc_Insert(?,?,?)
	public static Object executeProcedure(String procName,Object[] in) {
		 Connection con = null;
		try {
			con = getConnection();
			procName = "{call "+procName+"(";
			String link="";
			for(int i=0;i<in.length;i++) {
				procName+=link+"?";
				link=",";
			}
			procName+=")}";
			CallableStatement cstmt = con.prepareCall(procName);
			for(int i=0;i<in.length;i++) {
				cstmt.setObject(i+1, in[i]);
			}
			if(cstmt.execute())
			{
				return cstmt.getResultSet();
			}
			else {
				return cstmt.getUpdateCount();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}    
}
