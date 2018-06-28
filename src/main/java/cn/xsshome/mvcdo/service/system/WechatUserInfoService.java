package cn.xsshome.mvcdo.service.system;

import java.util.List;
import java.util.Map;

import cn.xsshome.mvcdo.pojo.system.WeChatUserInfoDO;

//wechar userinfo interface
public interface WechatUserInfoService {
	List<WeChatUserInfoDO> list(Map<String, Object> map);
	int count(Map<String,Object> map);
	int save(WeChatUserInfoDO weChatUserInfoDO);
	int remove(Long cid);
	int batchRemove(Long[] cids);
	WeChatUserInfoDO get(String openId);
	int updateWechatUserInfo(WeChatUserInfoDO userInfoDO);
}
