package cn.easybuy.service.user;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.easybuy.dao.order.OrderDetailMapper;
import cn.easybuy.dao.order.UserAddressDao;
import cn.easybuy.dao.order.UserAddressDaoImpl;
import cn.easybuy.dao.user.UserDao;
import cn.easybuy.dao.user.UserDaoImpl;
import cn.easybuy.dao.user.UserMapper;
import cn.easybuy.entity.UserAddress;
import cn.easybuy.mybatis.util.MyBatisUtil;
import cn.easybuy.utils.DataSourceUtil;
import cn.easybuy.utils.Pager;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import cn.easybuy.entity.OrderDetail;
import cn.easybuy.entity.User;

public class UserServiceImpl implements UserService {
	
	private Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Override
	public boolean add(User user){
		SqlSession session=null;
    	int count=0;
        try {
            session=MyBatisUtil.createSession();
           count=session.getMapper(UserMapper.class).add(user);
           session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
        	 MyBatisUtil.closeSession(session);
        }
        return count>0;
	}

	@Override
	public boolean update(User user) {
		SqlSession session=null;
    	int count=0;
        try {
            session=MyBatisUtil.createSession();
           count=session.getMapper(UserMapper.class).update(user);
           session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
        	 MyBatisUtil.closeSession(session);
        }
        return count>0;
	}

	@Override
	public boolean deleteUserById(Integer userId) {
		SqlSession session=null;
    	int count=0;
        try {
            session=MyBatisUtil.createSession();
           count=session.getMapper(UserMapper.class).deleteUserById(userId);
           session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
        	 MyBatisUtil.closeSession(session);
        }
        return count>0;
	}

	@Override
	public User getUser(Integer id, String loginName) {
		SqlSession session=null;
		User user=null;
		try {
			session=MyBatisUtil.createSession();
			user=session.getMapper(UserMapper.class).getUser(id, loginName);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MyBatisUtil.closeSession(session);
			session.rollback();
		}
		return user;
	}

	@Override
	public List<User> getUserList(Integer currentPageNo, Integer pageSize) {
		SqlSession session=null;
    	List<User> list=new ArrayList<User>();
        try {
            session=MyBatisUtil.createSession();
            int total = count();
			Pager pager = new Pager(total, pageSize, currentPageNo);
			
          list=session.getMapper(UserMapper.class).getUserList((pager.getCurrentPage() - 1) * pager.getRowPerPage(), pager.getRowPerPage());
           session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	 MyBatisUtil.closeSession(session);
        }
        return list;
	}

	@Override
	public int count() {
		SqlSession session=null;
    	int count=0;
        try {
            session=MyBatisUtil.createSession();
            count=session.getMapper(UserMapper.class).count();
           session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	 MyBatisUtil.closeSession(session);
        }
        return count;
	}
}
