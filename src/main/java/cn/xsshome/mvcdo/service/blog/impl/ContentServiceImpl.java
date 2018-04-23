package cn.xsshome.mvcdo.service.blog.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xsshome.mvcdo.dao.blog.ContentDao;
import cn.xsshome.mvcdo.pojo.blog.ContentDO;
import cn.xsshome.mvcdo.service.blog.ContentService;
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private ContentDao bContentMapper;
	
	public ContentDO get(Long cid){
		return bContentMapper.get(cid);
	}
	
	public List<ContentDO> list(Map<String, Object> map){
		return bContentMapper.list(map);
	}
	
	public int count(Map<String, Object> map){
		return bContentMapper.count(map);
	}
	
	public int save(ContentDO bContent){
		return bContentMapper.save(bContent);
	}
	
	public int update(ContentDO bContent){
		return bContentMapper.update(bContent);
	}
	
	public int remove(Long cid){
		return bContentMapper.remove(cid);
	}
	
	public int batchRemove(Long[] cids){
		return bContentMapper.batchRemove(cids);
	}
	
}
