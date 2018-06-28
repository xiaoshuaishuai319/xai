package cn.xsshome.mvcdo.dao.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cn.xsshome.mvcdo.pojo.system.WeChatUserInfoDO;
/**
 * 
 * @author 小帅丶
 * @date 2018年6月26日
 * <p>Description: Mapper</p>
 */
@Mapper
public interface WechatUserInfoDao {
	List<WeChatUserInfoDO> list(Map<String, Object> map);
	int count(Map<String,Object> map);
	int save(WeChatUserInfoDO detectDO);
	int remove(Long cid);
	int batchRemove(Long[] cids);
	WeChatUserInfoDO get(String openId);
	int updateWechatUserInfo(WeChatUserInfoDO userInfoDO);
}
