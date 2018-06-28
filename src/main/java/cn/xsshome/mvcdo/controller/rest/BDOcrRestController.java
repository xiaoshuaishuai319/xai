package cn.xsshome.mvcdo.controller.rest;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.ocr.AipOcr;

import cn.xsshome.mvcdo.common.AIConstant;
import cn.xsshome.mvcdo.common.AipAdded;
import cn.xsshome.mvcdo.interceptor.BDFactory;
import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDOCRBankCardDO;
import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDOCRGeneralDO;
import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDOCRIdCardDO;
import cn.xsshome.mvcdo.pojo.ai.baidu.po.BDOCRBankCardBean;
import cn.xsshome.mvcdo.pojo.ai.baidu.po.BDOCRGeneralBean;
import cn.xsshome.mvcdo.pojo.ai.baidu.po.BDOCRIdCardBean;
import cn.xsshome.mvcdo.service.ai.baidu.BDOCRDetectService;
import cn.xsshome.mvcdo.util.FileUtil;
import cn.xsshome.mvcdo.util.PrintUtil;
import cn.xsshome.mvcdo.vo.BDConstant;
import cn.xsshome.mvcdo.vo.BDDishResponse;
import cn.xsshome.mvcdo.vo.BDOCRBankCardResponse;
import cn.xsshome.mvcdo.vo.BDOCRGeneralResponse;
import cn.xsshome.mvcdo.vo.BDOCRIdCardResponse;
/**
 * 
 * @author 小帅丶
 * @date 2018年5月16日
 * <p>Description: </p>
 */
@Controller
@RequestMapping(value="rest/ocr")
@Scope("prototype")
public class BDOcrRestController {
	private static Logger logger = LoggerFactory.getLogger(BDOcrRestController.class);
	AipOcr aipOcr = BDFactory.getAipOcr();
	AipAdded aipAdded = BDFactory.getAipAdded();
	@Autowired
	private BDOCRDetectService bdocrDetectService;
	/**
	 * 文字识别网页上传
	 * @param request request对象
	 * @param response response对象
	 * @return 页面
	 */
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		logger.info("index跳转图像识别网页==访问的ip:"+request.getRemoteAddr());
		return "rest/bdicrfile";
	}
	 @RequestMapping(value = "/detect",method = {RequestMethod.POST})
	 public String uploadOcr(@RequestParam(value = "file")MultipartFile file, HttpServletRequest request, HttpServletResponse response){
			String resultData = "";	
			String ocrtype = request.getParameter("ocrtype");
			String clientType = request.getParameter("clientType");
	        String openId = ServletRequestUtils.getStringParameter(request, "openId","");
	        String nickName = ServletRequestUtils.getStringParameter(request, "nickName","");
			String apiType = getStrClientType(ocrtype);
			logger.info("=======访问的IP"+request.getRemoteAddr()+"======访问的User-Agent:"+request.getHeader("User-Agent"));
			logger.info("=======访问的类型"+clientType+"=======访问的接口类型"+apiType); 
	        if(null==ocrtype||null==apiType){
				 BDDishResponse bdDishResponse = new BDDishResponse();
				 bdDishResponse.setCode(BDConstant.BD_NULL.getCode().toString());
				 bdDishResponse.setMsg(BDConstant.BD_NULL.getMsg());
	             resultData = JSON.toJSONString(bdDishResponse);
	             logger.info("=====接口返回的内容:"+resultData);
	             PrintUtil.printJson(response,resultData);
	        }else{
	        	String perfix = getPerfix(apiType);
	            String dbPath ="/"+perfix;
	            String fileName = "ocrBD"+new Date().getTime()/1000+FileUtil.fileType(file.getOriginalFilename());
	            String filePath = request.getSession().getServletContext().getRealPath(perfix);
	            logger.info("=======保存的路径"+filePath+"/"+fileName);
	        try {
	        	nickName = URLEncoder.encode(nickName, "UTF-8");
	        	if(clientType.equals("web")){
					if(null==request.getHeader("User-Agent")){
						 BDDishResponse bdDishResponse = new BDDishResponse();
						 bdDishResponse.setCode(BDConstant.BD_403.getCode().toString());
						 bdDishResponse.setMsg(BDConstant.BD_403.getMsg());
		                 resultData = JSON.toJSONString(bdDishResponse);
		                 logger.info("=====接口返回的内容:"+resultData);
		                 PrintUtil.printJson(response,resultData);
					}else{
						 BDDishResponse bdDishResponse = new BDDishResponse();
						 bdDishResponse.setCode(BDConstant.BD_NOTFUND.getCode().toString());
						 bdDishResponse.setMsg(BDConstant.BD_NOTFUND.getMsg());
		                 resultData = JSON.toJSONString(bdDishResponse);
		                 logger.info("=====接口返回的内容:"+resultData);
		                 PrintUtil.printJson(response,resultData);
					}
				}else if (clientType!=null&&clientType.equals("wsc")) {
					String authCode = request.getParameter("authCode");
	            	if(null==authCode||!authCode.equals(AIConstant.AUTH_CODE)){
	            		BDDishResponse bdDishResponse = new BDDishResponse();
	            		bdDishResponse.setCode(BDConstant.BD_NOTFUND.getCode().toString());
	            		bdDishResponse.setMsg(BDConstant.BD_NOTFUND.getMsg());
	                    resultData = JSON.toJSONString(bdDishResponse);
	                    logger.info("=====接口返回的内容:"+resultData);
	                    PrintUtil.printJson(response,resultData);
	            	}
				}
				FileUtil.uploadFile(file.getBytes(),filePath,fileName);
				System.out.println("end====="+new Date().getTime());
				 //图片的本地路径
	            String imagePath = filePath+fileName;
	            HashMap<String, String> option = new HashMap<String, String>();
	            option.put("detect_direction", "true");
	            dbPath += fileName;
	            resultData = getOCRResult(apiType,clientType,dbPath,option,imagePath,openId,nickName);
	            PrintUtil.printJson(response,resultData);
			} catch (Exception e) {
				logger.info("文字识别百度接口出错了"+e.getMessage()+"====接口类型："+apiType);
				BDDishResponse bdDishResponse = new BDDishResponse();
				bdDishResponse.setCode(BDConstant.BD_ERROR.getCode().toString());
				bdDishResponse.setMsg(BDConstant.BD_ERROR.getMsg());
	            resultData = JSON.toJSONString(bdDishResponse);
	            System.out.println(resultData);
	            PrintUtil.printJson(response,resultData);
			}
	    }
		return null;
	}
	 /**
	  * 根据索引列表给定返回的接口类型
	  * @param ocrtype
	  * @return String
	  */
	 private String getStrClientType(String ocrtype) {
		 String clientType = "";
		 if(ocrtype.equals("0")){
			 clientType = "ocr";
		} else if (ocrtype.equals("1")) {
			 clientType = "idcardf";
		} else if (ocrtype.equals("2")) {
			 clientType = "idcardb";
		} else if (ocrtype.equals("3")) {
			 clientType = "bank";
		} else if (ocrtype.equals("4")) {
			 clientType = "handwriting";
		} else {
			 clientType = "ocr";
		}
		 return clientType;
	}
	/**
	  * 根据相关参数返回数据并保存相关数据
	  * @param apiType 接口类型
	  * @param clientType 访问类型
	  * @param dbPath 图片路径
	  * @param option 具体参数
	  * @param imagePath 识别图片路径
	  * @return
	  */
	 private String getOCRResult(String apiType, String clientType,String dbPath,HashMap<String, String> option,String imagePath,String openId,String nickName) {
		 String result = "";
		 JSONObject jsonObject = getOCRJSON(apiType,imagePath, option);
		 logger.info("==百度文字识别接口返回\n"+jsonObject.toString(2));
		 if(apiType.equals("ocr")||apiType.equals("handwriting")){
			BDOCRGeneralBean bdocrGeneralBean = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(), BDOCRGeneralBean.class);
			if(null!=bdocrGeneralBean.getError_msg()||bdocrGeneralBean.getWords_result_num()==0){
            	BDOCRGeneralResponse bdocrGeneralResponse = new BDOCRGeneralResponse();
            	bdocrGeneralResponse.setCode(BDConstant.BD_ERROR.getCode().toString());
            	if(bdocrGeneralBean.getWords_result_num()==0){
            		bdocrGeneralResponse.setMsg("没有识别出文字 Sorry");
            	}else{
            		bdocrGeneralResponse.setMsg("服务出错了 请稍后再试");
            	}
            	BDOCRGeneralDO bdocrGeneralDO = new BDOCRGeneralDO();
            	bdocrGeneralDO.setOpenId(openId);
            	bdocrGeneralDO.setNikeName(nickName);
            	bdocrGeneralDO.setLogId(bdocrGeneralBean.getLog_id());
            	bdocrGeneralDO.setErrorCode(bdocrGeneralBean.getError_code());
            	bdocrGeneralDO.setErrorMsg(bdocrGeneralBean.getError_msg());
        		bdocrGeneralDO.setApiType(apiType);
				bdocrGeneralDO.setEnterType(clientType);
				bdocrGeneralDO.setImagePath(dbPath);
				int count = bdocrDetectService.saveOcrGeneral(bdocrGeneralDO);
				logger.info("识别错误====保存成功了 "+count);
    			result = JSON.toJSONString(bdocrGeneralResponse);
			}else{
				BDOCRGeneralResponse bdocrGeneralResponse = new BDOCRGeneralResponse();
				bdocrGeneralResponse.setCode(BDConstant.BD_SUCCESS.getCode().toString());
				bdocrGeneralResponse.setMsg(BDConstant.BD_SUCCESS.getMsg());
				String words = "";
				for (int i = 0; i < bdocrGeneralBean.getWords_result().size(); i++) {
					words += bdocrGeneralBean.getWords_result().get(i).getWords()+",";
				}
				words.substring(0, words.length()-1);
				bdocrGeneralResponse.setOcrType(apiType);
				bdocrGeneralResponse.setWords(words);
				bdocrGeneralResponse.setWordsesultNum(bdocrGeneralBean.getWords_result_num());
				BDOCRGeneralDO bdocrGeneralDO = new BDOCRGeneralDO();
				bdocrGeneralDO.setOpenId(openId);
            	bdocrGeneralDO.setNikeName(nickName);
				bdocrGeneralDO.setApiType(apiType);
				bdocrGeneralDO.setEnterType(clientType);
				bdocrGeneralDO.setImagePath(dbPath);
				bdocrGeneralDO.setWords(words);;
				bdocrGeneralDO.setDirection(bdocrGeneralBean.getDirection());
				bdocrGeneralDO.setLogId(bdocrGeneralBean.getLog_id());
				bdocrGeneralDO.setWordsResultNum(bdocrGeneralBean.getWords_result_num());
				int count = bdocrDetectService.saveOcrGeneral(bdocrGeneralDO);
				logger.info("====保存成功了 "+count);
				result = JSON.toJSONString(bdocrGeneralResponse);
			}
		 }else if (apiType.equals("idcardb")||apiType.equals("idcardf")) {
			BDOCRIdCardBean bdocrIdCardBean = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(), BDOCRIdCardBean.class);
			if(null!=bdocrIdCardBean.getError_msg()){
				BDOCRIdCardResponse bdocrIdCardResponse = new BDOCRIdCardResponse();
				bdocrIdCardResponse.setCode(BDConstant.BD_ERROR.getCode().toString());
        		bdocrIdCardResponse.setMsg("识别身份证错误 Sorry");
        		BDOCRIdCardDO bdocrIdCardDO = new BDOCRIdCardDO();
        		bdocrIdCardDO.setOpenId(openId);
        		bdocrIdCardDO.setNikeName(nickName);
        		bdocrIdCardDO.setLogId(bdocrIdCardBean.getLog_id());
        		bdocrIdCardDO.setErrorCode(bdocrIdCardBean.getError_code());
        		bdocrIdCardDO.setErrorMsg(bdocrIdCardBean.getError_msg());
        		bdocrIdCardDO.setEnterType(clientType);
        		bdocrIdCardDO.setApiType(apiType);
        		bdocrIdCardDO.setImagePath(dbPath);
        		int count = bdocrDetectService.saveOcrIdCard(bdocrIdCardDO);
        		logger.info("识别错误====保存成功了 "+count);
        		result = JSON.toJSONString(bdocrIdCardResponse);
			}else{
				BDOCRIdCardResponse bdocrIdCardResponse = new BDOCRIdCardResponse();
				bdocrIdCardResponse.setCode(BDConstant.BD_SUCCESS.getCode().toString());
				bdocrIdCardResponse.setMsg(BDConstant.BD_SUCCESS.getMsg());
				bdocrIdCardResponse.setOcrType(apiType);
				if(apiType.equals("idcardb")){
					bdocrIdCardResponse.setImageStatus(getImageStatus(bdocrIdCardBean.getImage_status()));
					bdocrIdCardResponse.setIssueDate(bdocrIdCardBean.getWords_result().get(0).getIssueDate().getWords());
					bdocrIdCardResponse.setExpiryDate(bdocrIdCardBean.getWords_result().get(0).getExpiryDate().getWords());
					bdocrIdCardResponse.setAuthority(bdocrIdCardBean.getWords_result().get(0).getAuthority().getWords());
					bdocrIdCardResponse.setRiskType(getRiskType(bdocrIdCardBean.getRisk_type()));
					BDOCRIdCardDO bdocrIdCardDO = new BDOCRIdCardDO();
					bdocrIdCardDO.setOpenId(openId);
	        		bdocrIdCardDO.setNikeName(nickName);
	        		bdocrIdCardDO.setLogId(bdocrIdCardBean.getLog_id());
	        		bdocrIdCardDO.setEnterType(clientType);
	        		bdocrIdCardDO.setApiType(apiType);
	        		bdocrIdCardDO.setImagePath(dbPath);
	        		bdocrIdCardDO.setImageStatus(getImageStatus(bdocrIdCardBean.getImage_status()));
	        		bdocrIdCardDO.setIssueDate(bdocrIdCardBean.getWords_result().get(0).getIssueDate().getWords());
	        		bdocrIdCardDO.setExpiryDate(bdocrIdCardBean.getWords_result().get(0).getExpiryDate().getWords());
	        		bdocrIdCardDO.setAuthority(bdocrIdCardBean.getWords_result().get(0).getAuthority().getWords());
	        		bdocrIdCardDO.setDirection(bdocrIdCardBean.getDirection());
	        		bdocrIdCardDO.setWordsResultNum(bdocrIdCardBean.getWords_result_num());
	        		bdocrIdCardDO.setRiskType(getRiskType(bdocrIdCardBean.getRisk_type()));
	        		int count = bdocrDetectService.saveOcrIdCard(bdocrIdCardDO);
	        		logger.info("识别idcardb====保存成功了 "+count);
					result = JSON.toJSONString(bdocrIdCardResponse);
				}else if (apiType.equals("idcardf")) {
					bdocrIdCardResponse.setImageStatus(getImageStatus(bdocrIdCardBean.getImage_status()));
					bdocrIdCardResponse.setAddress(bdocrIdCardBean.getWords_result().get(0).getAddress().getWords());
					bdocrIdCardResponse.setBirth(bdocrIdCardBean.getWords_result().get(0).getBirth().getWords());
					bdocrIdCardResponse.setName(bdocrIdCardBean.getWords_result().get(0).getName().getWords());
					bdocrIdCardResponse.setIdCardNum(bdocrIdCardBean.getWords_result().get(0).getIdCardNum().getWords());
					bdocrIdCardResponse.setSex(bdocrIdCardBean.getWords_result().get(0).getSex().getWords());
					bdocrIdCardResponse.setNation(bdocrIdCardBean.getWords_result().get(0).getNation().getWords());
					bdocrIdCardResponse.setRiskType(getRiskType(bdocrIdCardBean.getRisk_type()));
					BDOCRIdCardDO bdocrIdCardDO = new BDOCRIdCardDO();
					bdocrIdCardDO.setOpenId(openId);
	        		bdocrIdCardDO.setNikeName(nickName);
	        		bdocrIdCardDO.setLogId(bdocrIdCardBean.getLog_id());
	        		bdocrIdCardDO.setImageStatus(getImageStatus(bdocrIdCardBean.getImage_status()));
	        		bdocrIdCardDO.setAddress(bdocrIdCardBean.getWords_result().get(0).getAddress().getWords());
	        		bdocrIdCardDO.setBirth(bdocrIdCardBean.getWords_result().get(0).getBirth().getWords());
	        		bdocrIdCardDO.setName(bdocrIdCardBean.getWords_result().get(0).getName().getWords());
	        		bdocrIdCardDO.setIdCardNum(bdocrIdCardBean.getWords_result().get(0).getIdCardNum().getWords());
	        		bdocrIdCardDO.setSex(bdocrIdCardBean.getWords_result().get(0).getSex().getWords());
	        		bdocrIdCardDO.setNation(bdocrIdCardBean.getWords_result().get(0).getNation().getWords());
	        		bdocrIdCardDO.setEnterType(clientType);
	        		bdocrIdCardDO.setApiType(apiType);
	        		bdocrIdCardDO.setImagePath(dbPath);
	        		bdocrIdCardDO.setDirection(bdocrIdCardBean.getDirection());
	        		bdocrIdCardDO.setWordsResultNum(bdocrIdCardBean.getWords_result_num());
	        		bdocrIdCardDO.setRiskType(getRiskType(bdocrIdCardBean.getRisk_type()));
	        		int count = bdocrDetectService.saveOcrIdCard(bdocrIdCardDO);
	        		logger.info("识别front====保存成功了 "+count);
					result = JSON.toJSONString(bdocrIdCardResponse);
				}else{
				bdocrIdCardResponse.setCode(BDConstant.BD_ERROR.getCode().toString());
        		bdocrIdCardResponse.setMsg("识别身份证错误 Sorry");
        		result = JSON.toJSONString(bdocrIdCardResponse);
				}
			}
		}else if (apiType.equals("bank")) {
			BDOCRBankCardBean bankCardBean = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(), BDOCRBankCardBean.class);
			if(null!=bankCardBean.getError_msg()){
				BDOCRBankCardResponse bankCardResponse = new BDOCRBankCardResponse();
				bankCardResponse.setCode(BDConstant.BD_ERROR.getCode().toString());
				bankCardResponse.setMsg("识别银行卡错误 Sorry");
        		BDOCRBankCardDO bankCardDO = new BDOCRBankCardDO();
        		bankCardDO.setOpenId(openId);
        		bankCardDO.setNikeName(nickName);
        		bankCardDO.setLogId(bankCardBean.getLog_id());
        		bankCardDO.setErrorCode(bankCardBean.getError_code());
        		bankCardDO.setErrorMsg(bankCardBean.getError_msg());
        		bankCardDO.setEnterType(clientType);
        		bankCardDO.setApiType(apiType);
        		bankCardDO.setImagePath(dbPath);
        		int count = bdocrDetectService.saveOcrBankCard(bankCardDO);
        		logger.info("识别错误====保存成功了 "+count);
        		result = JSON.toJSONString(bankCardResponse);
			}else{
				BDOCRBankCardResponse bankCardResponse = new BDOCRBankCardResponse();
				bankCardResponse.setCode(BDConstant.BD_SUCCESS.getCode().toString());
				bankCardResponse.setMsg(BDConstant.BD_SUCCESS.getMsg());
				bankCardResponse.setOcrType(apiType);
				bankCardResponse.setBankCardNumber(bankCardBean.getResult().getBank_card_number());
				bankCardResponse.setBankCardType(getBankCardType(bankCardBean.getResult().getBank_card_type()));
				bankCardResponse.setBankName(bankCardBean.getResult().getBank_name());
				BDOCRBankCardDO bankCardDO = new BDOCRBankCardDO();
	    		bankCardDO.setOpenId(openId);
        		bankCardDO.setNikeName(nickName);
        		bankCardDO.setLogId(bankCardBean.getLog_id());
        		bankCardDO.setEnterType(clientType);
        		bankCardDO.setApiType(apiType);
        		bankCardDO.setImagePath(dbPath);
        		bankCardDO.setBankCardNumber(bankCardBean.getResult().getBank_card_number());
        		bankCardDO.setBankCardType(bankCardBean.getResult().getBank_card_type());
        		bankCardDO.setBankName(bankCardBean.getResult().getBank_name());
        		int count = bdocrDetectService.saveOcrBankCard(bankCardDO);
        		logger.info("识别成功====保存成功了 "+count);
        		result = JSON.toJSONString(bankCardResponse);
			}
		} else {
			
		}
		 return result;
	}
	 /**
	  * 获取银行卡类型中文说明
	  * @param bank_card_type 0:不能识别; 1: 借记卡; 2: 信用卡
	  * @return String
	  */
	 private String getBankCardType(String bank_card_type) {
		 String result = "";
		 if(bank_card_type.equals("0")){
			 result = "不能识别";
	    }else if (bank_card_type.equals("1")) {
	    	 result = "借记卡";
		}else if (bank_card_type.equals("2")) {
			 result = "信用卡";
		}else {
			 result = "未知";
		}
	    return result;
	}
	/**
	  * 获取身份证状态中文说明
	  * @param image_status
	  * @return
	  */
	 private String getImageStatus(String image_status) {
		 String result = "";
			 if(image_status.equals("normal")){
				 result = "识别正常";
		    }else if (image_status.equals("reversed_side")) {
		    	 result = "身份证正反面颠倒";
			}else if (image_status.equals("non_idcard")) {
				 result = "上传的图片中不包含身份证";
			}else if (image_status.equals("blurred")) {
				 result = "身份证模糊";
			}else if (image_status.equals("other_type_card")) {
				 result = "其他类型证照";
			}else if (image_status.equals("over_exposure")) {
				 result = "身份证关键字段反光或过曝";
			}else if (image_status.equals("unknown")) {
				 result = "未知状态";
			}else {
				 result = "未知";
			}
		 return result;
	}
	 /**
	  * 身份证类型: normal-正常身份证；copy-复印件；temporary-临时身份证；screen-翻拍；unknow-其他未知情况
	  * @param riskType
	  * @return
	  */
	 private String getRiskType(String riskType) {
		 String result = "";
			 if(riskType.equals("normal")){
				 result = "正常身份证";
		    }else if (riskType.equals("copy")) {
		    	 result = "复印件";
			}else if (riskType.equals("screen")) {
				 result = "翻拍";
			}else if (riskType.equals("unknow")) {
				 result = "其他未知情况";
			}else if (riskType.equals("temporary")) {
				 result = "临时身份证";
			}else {
				 result = "未知";
			}
		 return result;
	}
	/**
	 *  获取文字识别结果
	 * @param apiType
	 * @param imagePath
	 * @param option
	 * @return
	 */
	private JSONObject getOCRJSON(String apiType, String imagePath, HashMap<String, String> option) {
		 JSONObject jsonObject = null;
		 if(apiType.equals("ocr")){
			 jsonObject = aipOcr.basicGeneral(imagePath, option);
		 }else if (apiType.equals("idcardb")) {
			option.put("detect_risk", "true");
			jsonObject = aipOcr.idcard(imagePath, "back", option);
		 }else if (apiType.equals("idcardf")) {
			option.put("detect_risk", "true");
		    jsonObject = aipOcr.idcard(imagePath, "front", option);
		 }else if (apiType.equals("bank")) {
			 option.put("detect_risk", "true");
			 jsonObject = aipOcr.bankcard(imagePath,option);
		}else if (apiType.equals("handwriting")) {
			jsonObject = aipAdded.handwritingDetect(imagePath, null);
		}else {
			jsonObject = null;
		}
		return jsonObject;
	}
	/**
     * 根据类型存不同的文件夹
     * @param apiType
     * @return
     */
	private String getPerfix(String apiType) {
		if(apiType.equals("ocr")){
			return "ocrGeneral/";
		}else if (apiType.equals("idcardb")) {
			return "ocrIdcardb/";
		}else if (apiType.equals("idcardf")) {
			return "ocrIdcardf/";
		}else if (apiType.equals("bank")) {
			return "ocrBank/";
		} else if (apiType.equals("handwriting")) {
			return "ocrHandWrite/";
		}else {
			return "ocrNull/";
		}
	}
}
