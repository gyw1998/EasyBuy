package cn.easybuy.dao.order;

import java.util.List;

import cn.easybuy.entity.OrderDetail;
import cn.easybuy.param.OrderDetailParam;

public interface OrderDetailMapper {

		//添加订单详细
		public void add(OrderDetail detail);
		//删除订单详细
		public void deleteById(OrderDetail detail); 
		//查询订单详细
		public OrderDetail getOrderDetailById(Integer id); 
		//查询所有订单列表详细
		public List<OrderDetail> getOrderDetailList(Integer Orderid);
		//查询所有订单数目详细
		public Integer queryOrderDetailCount(OrderDetailParam params);
}
