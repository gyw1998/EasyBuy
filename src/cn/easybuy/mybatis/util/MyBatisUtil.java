package cn.easybuy.mybatis.util;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//工具类
public class MyBatisUtil {
	private static SqlSessionFactory  factory;
	static {
			try {
				InputStream in=Resources.getResourceAsStream("mybatis-config.xml");
				factory=new SqlSessionFactoryBuilder().build(in);
		
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	public static SqlSession createSession() {
		return factory.openSession(false);
	}
	public static void closeSession (SqlSession session) {
		if(null!=session) {
			session.close();
		}
	}
}
