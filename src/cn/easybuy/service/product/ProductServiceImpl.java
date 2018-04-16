package cn.easybuy.service.product;
import java.util.List;
import cn.easybuy.dao.product.ProductMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import cn.easybuy.entity.Product;
import cn.easybuy.mybatis.util.MyBatisUtil;
import cn.easybuy.utils.Pager;
/**
 * 商品的业务类
 */
public class ProductServiceImpl implements ProductService {
	
	private Logger logger = Logger.getLogger(ProductServiceImpl.class);
	
	@Override
	public boolean add(Product product) {
		SqlSession session=null;
		Integer count=0;
		try {
			session = MyBatisUtil.createSession();
			count=session.insert("cn.easybuy.dao.product.ProductMapper.add",product);
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
	public boolean update(Product product) {
		SqlSession session=null;
		Integer count=0;
		try {
			session = MyBatisUtil.createSession();
			count=session.update("cn.easybuy.dao.product.ProductMapper.update",product);
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
	public boolean deleteProductById(Integer productId) {
		SqlSession session=null;
		Integer count=0;
		try {
			session = MyBatisUtil.createSession();
			count=session.delete("cn.easybuy.dao.product.ProductMapper.deleteProductById",productId);
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			MyBatisUtil.closeSession(session);
		}
		return count>0;
	}

	@Override
	public Product getProductById(Integer productId) {
		SqlSession session=null;
		Product p=null;
		try {
			session=MyBatisUtil.createSession();
			p=session.getMapper(ProductMapper.class).getProductById(productId);
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			MyBatisUtil.closeSession(session);
		}
		return p;
	}

	@Override
	public List<Product> getProductList(Integer currentPageNo,Integer pageSize,
			String name,Integer categoryId,Integer level) {
			SqlSession session=null;
		List<Product> productList=null;
		try {
			session=MyBatisUtil.createSession();
			int total = count(name,categoryId,level);
			Pager pager = new Pager(total, pageSize, currentPageNo);
			productList=session.getMapper(ProductMapper.class).getProductList((pager.getCurrentPage() - 1) * pager.getRowPerPage(), 
					pager.getRowPerPage(), name, categoryId, level);		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyBatisUtil.closeSession(session);
		}
		return productList;
	}

	@Override
	public int count(String proName,Integer categoryId,Integer level) {
		SqlSession session=null;
		int count=0;
		try {
			session=MyBatisUtil.createSession();
			count=session.getMapper(ProductMapper.class).queryProductCount(proName, categoryId, level);		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyBatisUtil.closeSession(session);
		}
		return count;
	}

	@Override
	public boolean updateStock(Integer productId, Integer stock) {
		SqlSession session=null;
		Integer count=0;
		try {
			session=MyBatisUtil.createSession();
			count=session.getMapper(ProductMapper.class).updateStock(productId, stock);	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyBatisUtil.closeSession(session);
		}
		return count>0;
	}
   
}
/*package cn.easybuy.service.product;
import java.sql.Connection;
import java.util.List;
import cn.easybuy.dao.product.ProductDao;
import cn.easybuy.dao.product.ProductDaoImpl;
import cn.easybuy.utils.DataSourceUtil;
import org.apache.log4j.Logger;
import cn.easybuy.entity.Product;
*//**
 * 商品的业务类
 *//*
public class ProductServiceImpl implements ProductService {
	
	private Logger logger = Logger.getLogger(ProductServiceImpl.class);
	
	@Override
	public boolean add(Product product) {
		Connection connection = null;
		Integer count=0;
		try {
			connection = DataSourceUtil.openConnection();
			ProductDao productDao = new ProductDaoImpl(connection);
			count=productDao.add(product);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
			return count>0;
		}
	}

	@Override
	public boolean update(Product product) {
		Connection connection = null;
		Integer count=0;
		try {
			connection = DataSourceUtil.openConnection();
			ProductDao productDao = new ProductDaoImpl(connection);
			count=productDao.update(product);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
			return count>0;
		}
	}

	@Override
	public boolean deleteProductById(Integer productId) {
		Connection connection = null;
		Integer count=0;
		try {
			connection = DataSourceUtil.openConnection();
			ProductDao productDao = new ProductDaoImpl(connection);
			count=productDao.deleteProductById(productId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
			return count>0;
		}
	}

	@Override
	public Product getProductById(Integer productId) {
		Connection connection = null;
		Product product=null;
		try {
			connection = DataSourceUtil.openConnection();
			ProductDao productDao = new ProductDaoImpl(connection);
			product=productDao.getProductById(productId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
			return product;
		}
	}

	@Override
	public List<Product> getProductList(Integer currentPageNo,Integer pageSize,String proName, Integer categoryId, Integer level) {
		Connection connection = null;
		List<Product> productList=null;
		try {
			connection = DataSourceUtil.openConnection();
			ProductDao productDao = new ProductDaoImpl(connection);
			productList=productDao.getProductList(currentPageNo,pageSize,proName,categoryId,level);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
			return productList;
		}
	}

	@Override
	public int count(String proName,Integer categoryId, Integer level) {
		Connection connection = null;
		Integer count=0;
		try {
			connection = DataSourceUtil.openConnection();
			ProductDao productDao = new ProductDaoImpl(connection);
			count=productDao.queryProductCount(proName,categoryId,level);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
			return count;
		}
	}

	@Override
	public boolean updateStock(Integer productId, Integer stock) {
		Connection connection = null;
		Integer count=0;
		try {
			connection = DataSourceUtil.openConnection();
			ProductDao productDao = new ProductDaoImpl(connection);
			count=productDao.updateStock(productId,stock);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
			return count>0;
		}
	}
   
}

*/