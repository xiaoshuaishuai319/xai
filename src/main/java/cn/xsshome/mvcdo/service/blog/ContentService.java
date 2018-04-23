package cn.xsshome.mvcdo.service.blog;


import java.util.List;
import java.util.Map;

import cn.xsshome.mvcdo.pojo.blog.ContentDO;
/**
 * 博客接口
 * @author 小帅丶
 *
 */
public interface ContentService {
	
	ContentDO get(Long cid);
	
	List<ContentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContentDO bContent);
	
	int update(ContentDO bContent);
	
	int remove(Long cid);
	
	int batchRemove(Long[] cids);
}
