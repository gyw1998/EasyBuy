package cn.easybuy.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.easybuy.dao.IBaseDao;
import cn.easybuy.entity.User;

public interface UserMapper{
	//新增用户信息
	public int add(User user);
	
	//修改用户信息
	public int update(User user);
	
	//删除用户信息
	public int deleteUserById(Integer userId);
	
	//分页显示用户信息
	public List<User> getUserList(@Param("currentPageNo")Integer currentPageNo,@Param("pageSize")Integer pageSize);
	
	//查询所有用户信息数目
	public Integer  count();
	
	//查询用户信息
	public User getUser(@Param("id")Integer id,@Param("loginName")String loginName);
}
