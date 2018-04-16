package cn.easybuy.dao.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.easybuy.dao.IBaseDao;
import cn.easybuy.entity.Order;

public interface OrderMapper{

	//添加订单
	public void add(Order order);
	//删除订单
	public void deleteById(Integer id); 
	//查询订单
	public Order getOrderById(Integer id); 
	//分页查询订单列表
	public List<Order> getOrderList(@Param("userId")Integer userId,@Param("currentPageNo")Integer currentPageNo,@Param("pageSize")Integer pageSize);
	//查询所有订单数目
	public Integer count(Integer userId);
}
