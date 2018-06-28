package cn.xsshome.mvcdo.dao.ai.tencent;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cn.xsshome.mvcdo.pojo.ai.tencent.dbo.YouTuDetectFuseDO;
/**
 * 
 * @author 小帅丶
 * @date 2018年6月5日
 * <p>Description: 优图Dao</p>
 */
@Mapper
public interface YouTuFuseDao {
	List<YouTuDetectFuseDO> listYouTuFuse(Map<String, Object> map);
	int countYouTuFuse(Map<String,Object> map);
	int saveYouTuFuse(YouTuDetectFuseDO youTuDetectFuseDO);
	int removeYouTuFuse(Long youtuId);
	int batchRemoveYouTuFuse(Long[] youtuIds);
}
