package cn.xsshome.mvcdo.service.ai.baidu;

import java.util.List;
import java.util.Map;

import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDFaceDetectDO;

public interface BDFaceDetectService {
	List<BDFaceDetectDO> list(Map<String, Object> map);
	int count(Map<String,Object> map);
	int save(BDFaceDetectDO detectDO);
	int remove(Long cid);
	int batchRemove(Long[] cids);
}
