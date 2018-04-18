package cn.easybuy.dao.news;

import java.util.List;

import cn.easybuy.dao.IBaseDao;
import cn.easybuy.entity.News;
import cn.easybuy.param.NewsParams;

public interface NewsMapper extends IBaseDao{

	//增加新闻 
	public Integer add(News news);
	//修改新闻
	public Integer update(News news);
	//删除新闻
	public Integer deleteById(String id);
	//查询新闻
	public News getNewsById(String id);
	//查询新闻列表
	public List<News> queryNewsList(NewsParams params);
	//查询新闻数目
	public Integer queryNewscount(NewsParams params);
}
