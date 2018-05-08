package cn.xsshome.mvcdo.service.ai.baidu.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xsshome.mvcdo.dao.ai.baidu.BDFaceDetectDao;
import cn.xsshome.mvcdo.pojo.ai.baidu.BDFaceDetectDO;
import cn.xsshome.mvcdo.service.ai.baidu.BDFaceDetectService;
@Service
public class BDFaceDetectServiceImpl implements BDFaceDetectService{
	@Autowired
	private BDFaceDetectDao bdFaceDetectMapper;
	public List<BDFaceDetectDO> list(Map<String, Object> map) {
		return bdFaceDetectMapper.list(map);
	}

	public int count(Map<String, Object> map) {
		return bdFaceDetectMapper.count(map);
	}

	public int save(BDFaceDetectDO detectDO) {
		return bdFaceDetectMapper.save(detectDO);
	}

	public int remove(Long faceId) {
		return bdFaceDetectMapper.remove(faceId);
	}

	public int batchRemove(Long[] faceIds) {
		return bdFaceDetectMapper.batchRemove(faceIds);
	}

}
