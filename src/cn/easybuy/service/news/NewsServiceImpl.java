package cn.easybuy.service.news;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.easybuy.dao.news.NewsDao;
import cn.easybuy.dao.news.NewsDaoImpl;
import cn.easybuy.dao.news.NewsMapper;
import cn.easybuy.entity.News;
import cn.easybuy.mybatis.util.MyBatisUtil;
import cn.easybuy.param.NewsParams;
import cn.easybuy.utils.DataSourceUtil;
import cn.easybuy.utils.Pager;
import cn.easybuy.utils.Params;


public class NewsServiceImpl implements NewsService {

	public void deleteNews(String id) {// 删除新闻
		SqlSession session=null;
		Integer count=0;
		try {
			session=MyBatisUtil.createSession();
			count=session.getMapper(NewsMapper.class).deleteById(id);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSession(session);
		}
	}

	public News findNewsById(String id) {// 根据ID获取新闻
		News news = null;
		SqlSession session=null;
		try {
			session=MyBatisUtil.createSession();
			news=session.selectOne("cn.easybuy.dao.news.NewsMapper",id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyBatisUtil.closeSession(session);
		}
		return news;
	}

	public void addNews(News news) {// 保存新闻
			SqlSession session=null;
			Integer count=0;
		try {
			session=MyBatisUtil.createSession();
			count=session.getMapper(NewsMapper.class).add(news);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyBatisUtil.closeSession(session);
			session.rollback();
		}
	}

	public void updateNews(News news) {// 更新留言
			SqlSession session =null;
			Integer count=0;
		try {
			session=MyBatisUtil.createSession();
			count=session.getMapper(NewsMapper.class).update(news);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyBatisUtil.closeSession(session);
			session.rollback();
		}
	}

	
	public List<News> queryNewsPageList(NewsParams param) throws SQLException {
		List<News> newsList=new ArrayList<News>();
		Connection connection = null;
		NewsDao newsDao =null;
		try {
			connection = DataSourceUtil.openConnection();
			newsDao= new NewsDaoImpl(connection);
			newsList=newsDao.queryNewsList(param);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(connection.isClosed());
			DataSourceUtil.closeConnection(connection);
		}
		return newsList;
	}
	
	@Override
	public List<News> queryNewsList(NewsParams param) {
		List<News> newsList=new ArrayList<News>();
		SqlSession session=null;
		try {
			session=MyBatisUtil.createSession();
			newsList=session.getMapper(NewsMapper.class).queryNewsList(param);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyBatisUtil.closeSession(session);
		}
		return newsList;
	}

	@Override
	public Integer queryNewsCount(NewsParams param) {
		SqlSession session=null;
		Integer count=0;
		try {
			session=MyBatisUtil.createSession();
			count=session.getMapper(NewsMapper.class).queryNewscount(param);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyBatisUtil.closeSession(session);
		}
		return count;
	}

}

	
	