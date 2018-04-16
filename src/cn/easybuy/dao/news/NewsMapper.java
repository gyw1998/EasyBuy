package cn.easybuy.dao.news;

import java.util.List;

import cn.easybuy.dao.IBaseDao;
import cn.easybuy.entity.News;

public interface NewsMapper extends IBaseDao{

	//增加新闻 
	public int add(News news);
	//修改新闻
	public int update(News news);
	//删除新闻
	public int deleteById(Integer id);
	//查询新闻
	public News getNewsById(Integer id);
	//查询新闻列表
	public List<News> queryNewsList(News news);
	//查询新闻数目
	public int queryNewscount();
}
