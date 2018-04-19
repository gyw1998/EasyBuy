package cn.easybuy.dao.order;

import java.util.List;

import cn.easybuy.entity.UserAddress;
import cn.easybuy.param.UserAddressParam;

public interface UserAddressMapper {

	//分页显示用户地址列表
	public List<UserAddress> queryUserAddressList(UserAddressParam param);
	//添加用户地址
	public Integer add(UserAddress userAddress);
	//查询用户地址列表
	public UserAddress getUserAddressById(Integer addressId);

}
