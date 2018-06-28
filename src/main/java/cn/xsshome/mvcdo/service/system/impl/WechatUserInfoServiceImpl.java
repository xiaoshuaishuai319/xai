package cn.xsshome.mvcdo.service.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xsshome.mvcdo.dao.system.WechatUserInfoDao;
import cn.xsshome.mvcdo.pojo.system.WeChatUserInfoDO;
import cn.xsshome.mvcdo.service.system.WechatUserInfoService;
/**
 * 
 * @author 小帅丶
 * @date 2018年6月26日
 * <p>Description: 微信用户管理</p>
 */
@Service
public class WechatUserInfoServiceImpl implements WechatUserInfoService{
	@Autowired
	private WechatUserInfoDao wechatUserInfoMapper;
	public List<WeChatUserInfoDO> list(Map<String, Object> map) {
		return wechatUserInfoMapper.list(map);
	}

	public int count(Map<String, Object> map) {
		return wechatUserInfoMapper.count(map);
	}

	public int save(WeChatUserInfoDO weChatUserInfoDO) {
		return wechatUserInfoMapper.save(weChatUserInfoDO);
	}

	public int remove(Long cid) {
		return wechatUserInfoMapper.remove(cid);
	}

	public int batchRemove(Long[] cids) {
		return wechatUserInfoMapper.batchRemove(cids);
	}

	public WeChatUserInfoDO get(String openId) {
		return wechatUserInfoMapper.get(openId);
	}

	public int updateWechatUserInfo(WeChatUserInfoDO userInfoDO) {
		return wechatUserInfoMapper.updateWechatUserInfo(userInfoDO);
	}

}
