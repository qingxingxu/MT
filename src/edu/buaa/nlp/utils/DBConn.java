package edu.buaa.nlp.utils;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.mchange.v2.c3p0.ComboPooledDataSource;


public class DBConn {
	
	//三大核心接口
	private static ComboPooledDataSource source=null;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	static{
		source=new ComboPooledDataSource();
		Logger log=Logger.getLogger("com.mchange");
		log.setLevel(Level.WARN);
		try {
			source.setDriverClass(Config.CLASS_NAME);
			source.setJdbcUrl(Config.DATABASE_URL);
			source.setUser(Config.USERNAME);
			source.setPassword(Config.PASSWORD);
			source.setMaxPoolSize(Config.MAX_POOL_SIZE);
			source.setMinPoolSize(Config.MIN_POOL_SIZE);
			source.setMaxStatements(Config.MAX_STATEMENTS);
			source.setMaxIdleTime(Config.MAX_IDLE_TIME);
			source.setMaxConnectionAge(Config.MAX_CONNECTION_AGE);
			source.setMaxStatementsPerConnection(Config.MAX_STATEMENTS_PER_CONN);
			source.setAcquireIncrement(Config.ACQUIRE_INCREMENT);	//连接不足时的增量
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
	//method1: 创建数据库的连接
	public void getConntion(){		
		try {
			conn=source.getConnection();
//			System.out.println("Default conns:"+source.getNumConnectionsDefaultUser());
//			System.out.println("busy conns:"+source.getNumBusyConnectionsDefaultUser());
//			System.out.println("idle conns:"+source.getNumIdleConnectionsDefaultUser());
//			System.out.println("unclosed conns:"+source.getNumUnclosedOrphanedConnectionsDefaultUser());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
/*	synchronized private void getConntion(){		
		try {
			if(conn!=null && !conn.isClosed()) return ;
			//1: 加载连接驱动，Java反射原理
			Class.forName(Config.CLASS_NAME);
			//2:创建Connection接口对象，用于获取MySQL数据库的连接对象。三个参数：url连接字符串    账号  密码
			String url = Config.DATABASE_URL;
			conn = DriverManager.getConnection(url,Config.USERNAME,Config.PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}*/
	
	
	

	//method2：关闭数据库的方法
	public void closeConn(){
		try {
			if( (rs!=null) ){ //&& (!rs.isClosed()
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void closeStatement(){
//		if(rs!=null){
//			try {
//				rs.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    
	//method3: 专门用于发送增删改语句的方法
	public int execUpdate(final String strSQL,final Object[] params ){
		//1、获取数据库连接
		getConntion();
		//2、预先打印出即将执行的SQL语句(便于项目测试，仿Hibernate框架)
		try {
			//3、创建Statement接口对象
			pstmt = conn.prepareStatement(strSQL);
			//4、动态为pstmt对象赋值
			if(params!=null)
				for(int i=0; i< params.length ;i++){
					pstmt.setObject(i+1, params[i]);
				}
			//5、使用Statement对象发送SQL语句
			int affectedRows = pstmt.executeUpdate();
			conn.commit();
//			System.out.println("strSQL11>>"+strSQL);
			//6、返回结果
			return affectedRows;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}


	//method4: 专门用于发送查询语句
	public ResultSet execQuery(final String strSQL,final Object[] params){
		//1、获取数据库连接
		getConntion();
		//2、预先打印出即将执行的SQL语句(便于项目测试，仿Hibernate框架)
		try {
			//3、创建PreparedStatement接口对象
			pstmt = conn.prepareStatement(strSQL);
			//4、动态为pstmt对象赋值
			if(params!=null)
				for(int i=0; i< params.length ;i++){
					pstmt.setObject(i+1, params[i]);
				}		
			//5、使用Statement对象发送SQL语句
			rs = pstmt.executeQuery();
			//6、返回结果
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	

}
