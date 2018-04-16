package cn.easybuy.dao.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.easybuy.dao.IBaseDao;
import cn.easybuy.entity.Product;


public interface ProductMapper {
		//更新库存
	    public Integer updateStock(@Param("productId") Integer productId,@Param("stock") Integer stock);
		//增加商品
		public Integer add(Product product);
		//修改商品
		public Integer update(Product product);
		//删除商品
		public Integer deleteById(Integer id);
		//查询商品
		public Product getProductById(Integer id);
		//分页查询商品列表
		public List<Product> getProductList(@Param("currentPageNo")Integer currentPageNo,@Param("pageSize")Integer pageSize,
				@Param("name")String name,@Param("categoryId")Integer categoryId,@Param("level")Integer level);
		//查询商品数目
		public Integer queryProductCount(@Param("name")String name,@Param("categoryId")Integer categoryId,@Param("level")Integer level);
}
