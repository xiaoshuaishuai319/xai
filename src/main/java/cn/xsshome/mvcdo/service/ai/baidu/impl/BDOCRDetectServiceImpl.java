package cn.xsshome.mvcdo.service.ai.baidu.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xsshome.mvcdo.dao.ai.baidu.BDOCRDetectDao;
import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDOCRBankCardDO;
import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDOCRGeneralDO;
import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDOCRIdCardDO;
import cn.xsshome.mvcdo.service.ai.baidu.BDOCRDetectService;
/**
 * 
 * @author 小帅丶
 * @date 2018年5月18日
 * <p>Description: 文字识别实现类</p>
 */
@Service
public class BDOCRDetectServiceImpl implements BDOCRDetectService{
	@Autowired
	private BDOCRDetectDao bdocrDetectMapper;
	public List<BDOCRGeneralDO> listOcrGeneral(Map<String, Object> map) {
		return bdocrDetectMapper.listOcrGeneral(map);
	}

	public int countOcrGeneral(Map<String, Object> map) {
		return bdocrDetectMapper.countOcrGeneral(map);
	}

	public int saveOcrGeneral(BDOCRGeneralDO bdocrGeneralDO) {
		return bdocrDetectMapper.saveOcrGeneral(bdocrGeneralDO);
	}

	public int removeOcrGeneral(Long ocrId) {
		return bdocrDetectMapper.removeOcrGeneral(ocrId);
	}

	public int batchRemoveOcrGeneral(Long[] ocrIds) {
		return bdocrDetectMapper.batchRemoveOcrGeneral(ocrIds);
	}

	public List<BDOCRIdCardDO> listOcrIdCard(Map<String, Object> map) {
		return bdocrDetectMapper.listOcrIdCard(map);
	}

	public int countOcrIdCard(Map<String, Object> map) {
		return bdocrDetectMapper.countOcrIdCard(map);
	}

	public int saveOcrIdCard(BDOCRIdCardDO bdocrIdCardDO) {
		return bdocrDetectMapper.saveOcrIdCard(bdocrIdCardDO);
	}

	public int removeOcrIdCard(Long ocrId) {
		return bdocrDetectMapper.removeOcrIdCard(ocrId);
	}

	public int batchRemoveOcrIdCard(Long[] ocrIds) {
		return bdocrDetectMapper.batchRemoveOcrIdCard(ocrIds);
	}

	public List<BDOCRBankCardDO> listOcrBankCard(Map<String, Object> map) {
		return bdocrDetectMapper.listOcrBankCard(map);
	}

	public int countOcrBankCard(Map<String, Object> map) {
		return bdocrDetectMapper.countOcrBankCard(map);
	}

	public int saveOcrBankCard(BDOCRBankCardDO bdocrBankCardDO) {
		return bdocrDetectMapper.saveOcrBankCard(bdocrBankCardDO);
	}

	public int removeOcrBankCard(Long ocrId) {
		return bdocrDetectMapper.removeOcrBankCard(ocrId);
	}

	public int batchRemoveBankIdCard(Long[] ocrIds) {
		return bdocrDetectMapper.batchRemoveBankIdCard(ocrIds);
	}

}
