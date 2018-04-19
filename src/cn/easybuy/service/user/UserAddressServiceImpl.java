package cn.easybuy.service.user;

import cn.easybuy.dao.order.UserAddressDao;
import cn.easybuy.dao.order.UserAddressDaoImpl;
import cn.easybuy.dao.order.UserAddressMapper;
import cn.easybuy.entity.UserAddress;
import cn.easybuy.mybatis.util.MyBatisUtil;
import cn.easybuy.param.UserAddressParam;
import cn.easybuy.utils.DataSourceUtil;
import cn.easybuy.utils.Params;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

/**
 * Created by bdqn on 2016/5/12.
 */
public class UserAddressServiceImpl implements UserAddressService {
    /**
     * 查询用户地址列表
     *
     * @param id
     * @return
     * @throws Exception
     */
    public List<UserAddress> queryUserAdressList(Integer id) throws Exception{
        Connection connection = null;
        SqlSession session=null;
        List<UserAddress> userAddressList = null;
        try {
            connection = DataSourceUtil.openConnection();
            session=MyBatisUtil.createSession();
            UserAddressDao userAddressDao = new UserAddressDaoImpl(connection);
            UserAddressMapper userAddressMapper=session.getMapper(UserAddressMapper.class);
            UserAddressParam params = new UserAddressParam();
            params.setUserId(id);
            userAddressList = userAddressMapper.queryUserAddressList(params);
            		//userAddressDao.queryUserAddressList(params);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	DataSourceUtil.closeConnection(connection);
        	MyBatisUtil.closeSession(session);
        }
        return userAddressList;
    }
    /**
     * 添加用户地址
     *
     * @param id
     * @param address
     * @return
     */
    @Override
    public Integer addUserAddress(Integer id, String address,String remark) {
        Connection connection = null;
        SqlSession session=null;
        Integer userAddressId = null;
        try {
            connection = DataSourceUtil.openConnection();
            session=MyBatisUtil.createSession();
            UserAddressDao userAddressDao = new UserAddressDaoImpl(connection);
            UserAddressMapper userAddressMapper=session.getMapper(UserAddressMapper.class);
            UserAddress userAddress=new UserAddress();
            userAddress.setUserId(id);
            userAddress.setAddress(address);
            userAddress.setRemark(remark);
            userAddressId = userAddressMapper.add(userAddress);
            		//userAddressDao.add(userAddress);
            session.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	DataSourceUtil.closeConnection(connection);
        	MyBatisUtil.closeSession(session);
        }
        return userAddressId;
    }

    @Override
    public UserAddress getUserAddressById(Integer id) {
        Connection connection = null;
        SqlSession session=null;
        UserAddress userAddress= null;
        try {
            connection = DataSourceUtil.openConnection();
            session=MyBatisUtil.createSession();
            UserAddressDao userAddressDao = new UserAddressDaoImpl(connection);
            UserAddressMapper userAddressMapper=session.getMapper(UserAddressMapper.class);
            userAddress = userAddressMapper.getUserAddressById(id);
            		//(UserAddress) userAddressDao.getUserAddressById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DataSourceUtil.closeConnection(connection);
            MyBatisUtil.closeSession(session);
        }
        return  userAddress;
    }
}
