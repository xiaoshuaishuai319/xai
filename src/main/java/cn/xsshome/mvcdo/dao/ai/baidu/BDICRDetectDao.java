package cn.xsshome.mvcdo.dao.ai.baidu;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDICRDishDO;
import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDICRFuseDO;
/**
 * 
 * @author 小帅丶
 * @date 2018年5月18日
 * <p>Description: 图像识别dao</p>
 */
@Mapper
public interface BDICRDetectDao {
	/**菜品接口**/
	List<BDICRDishDO> listDish(Map<String, Object> map);
	int countDish(Map<String,Object> map);
	int saveDish(BDICRDishDO bdicrDishDO);
	int removeDish(Long icrId);
	int batchRemoveDish(Long[] icrIds);
	/**车型接口、植物接口、动物接口、LOGO接口、食材接口**/
	List<BDICRFuseDO> listFuse(Map<String, Object> map);
	int countFuse(Map<String,Object> map);
	int saveFuse(BDICRFuseDO bdicrFuseDO);
	int removeFuse(Long icrId);
	int batchRemoveFuse(Long[] icrIds);
}
