package cn.xsshome.mvcdo.dao.ai.baidu;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDFaceDetectDO;


/**
 * 百度人脸检测dao
 * @author 小帅丶
 */
@Mapper
public interface BDFaceDetectDao {
	List<BDFaceDetectDO> list(Map<String, Object> map);
	int count(Map<String,Object> map);
	int save(BDFaceDetectDO detectDO);
	int remove(Long cid);
	int batchRemove(Long[] cids);
}
