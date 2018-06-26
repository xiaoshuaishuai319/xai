package cn.xsshome.mvcdo.service.ai.baidu;

import java.util.List;
import java.util.Map;

import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDOCRBankCardDO;
import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDOCRGeneralDO;
import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDOCRIdCardDO;

/**
 * 
 * @author 小帅丶
 * @date 2018年5月18日
 * <p>Description: 文字识别接口类</p>
 */
public interface BDOCRDetectService {
	/**文字通用识别接口类**/
	List<BDOCRGeneralDO> listOcrGeneral(Map<String, Object> map);
	int countOcrGeneral(Map<String,Object> map);
	int saveOcrGeneral(BDOCRGeneralDO bdocrGeneralDO);
	int removeOcrGeneral(Long ocrId);
	int batchRemoveOcrGeneral(Long[] ocrIds);
	/**文字身份证识别接口类**/
	List<BDOCRIdCardDO> listOcrIdCard(Map<String, Object> map);
	int countOcrIdCard(Map<String,Object> map);
	int saveOcrIdCard(BDOCRIdCardDO bdocrIdCardDO);
	int removeOcrIdCard(Long ocrId);
	int batchRemoveOcrIdCard(Long[] ocrIds);
	/**文字银行卡识别接口类**/
	List<BDOCRBankCardDO> listOcrBankCard(Map<String, Object> map);
	int countOcrBankCard(Map<String,Object> map);
	int saveOcrBankCard(BDOCRBankCardDO bdocrBankCardDO);
	int removeOcrBankCard(Long ocrId);
	int batchRemoveBankIdCard(Long[] ocrIds);
}
