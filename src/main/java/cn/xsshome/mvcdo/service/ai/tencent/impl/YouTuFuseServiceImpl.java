package cn.xsshome.mvcdo.service.ai.tencent.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xsshome.mvcdo.dao.ai.tencent.YouTuFuseDao;
import cn.xsshome.mvcdo.pojo.ai.tencent.dbo.YouTuDetectFuseDO;
import cn.xsshome.mvcdo.service.ai.tencent.YouTuFuseService;
@Service
public class YouTuFuseServiceImpl implements YouTuFuseService{
	@Autowired
	private YouTuFuseDao youTuFuseMapper;
	public List<YouTuDetectFuseDO> listYouTuFuse(Map<String, Object> map) {
		return youTuFuseMapper.listYouTuFuse(map);
	}

	public int countYouTuFuse(Map<String, Object> map) {
		return youTuFuseMapper.countYouTuFuse(map);
	}

	public int saveYouTuFuse(YouTuDetectFuseDO youTuDetectFuseDO) {
		return youTuFuseMapper.saveYouTuFuse(youTuDetectFuseDO);
	}

	public int removeYouTuFuse(Long youtuId) {
		return youTuFuseMapper.removeYouTuFuse(youtuId);
	}

	public int batchRemoveYouTuFuse(Long[] youtuIds) {
		return youTuFuseMapper.batchRemoveYouTuFuse(youtuIds);
	}

}
