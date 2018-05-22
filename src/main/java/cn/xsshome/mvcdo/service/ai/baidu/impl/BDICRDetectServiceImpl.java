package cn.xsshome.mvcdo.service.ai.baidu.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xsshome.mvcdo.dao.ai.baidu.BDICRDetectDao;
import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDICRDishDO;
import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDICRFuseDO;
import cn.xsshome.mvcdo.service.ai.baidu.BDICRDetectService;
/**
 * 
 * @author 小帅丶
 * @date 2018年5月9日
 * <p>Description: 图像识别Service实现类</p>
 */
@Service
public class BDICRDetectServiceImpl implements BDICRDetectService{
	@Autowired
	private BDICRDetectDao bdicrDetectMapper;
	public List<BDICRDishDO> listDish(Map<String, Object> map) {
		return bdicrDetectMapper.listDish(map);
	}

	public int countDish(Map<String, Object> map) {
		return bdicrDetectMapper.countDish(map);
	}

	public int saveDish(BDICRDishDO bdicrDishDO) {
		return bdicrDetectMapper.saveDish(bdicrDishDO);
	}

	public int removeDish(Long icrId) {
		return bdicrDetectMapper.removeDish(icrId);
	}

	public int batchRemoveDish(Long[] icrIds) {
		return bdicrDetectMapper.batchRemoveDish(icrIds);
	}

	public List<BDICRFuseDO> listFuse(Map<String, Object> map) {
		return bdicrDetectMapper.listFuse(map);
	}

	public int countFuse(Map<String, Object> map) {
		return bdicrDetectMapper.countFuse(map);
	}

	public int saveFuse(BDICRFuseDO bdicrFuseDO) {
		return bdicrDetectMapper.saveFuse(bdicrFuseDO);
	}

	public int removeFuse(Long icrId) {
		return bdicrDetectMapper.removeFuse(icrId);
	}

	public int batchRemoveFuse(Long[] icrIds) {
		return bdicrDetectMapper.batchRemoveFuse(icrIds);
	}

}
