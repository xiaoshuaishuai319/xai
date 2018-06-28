package cn.xsshome.mvcdo.controller.rest;

import java.net.URLEncoder;
import java.util.Date;

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

import cn.xsshome.mvcdo.common.AIConstant;
import cn.xsshome.mvcdo.common.HandTrackEnum;
import cn.xsshome.mvcdo.common.YouTuAPIS;
import cn.xsshome.mvcdo.interceptor.BDFactory;
import cn.xsshome.mvcdo.pojo.ai.tencent.dbo.YouTuDetectFuseDO;
import cn.xsshome.mvcdo.pojo.ai.tencent.po.HandTrackingBean;
import cn.xsshome.mvcdo.pojo.ai.tencent.po.HandWritingOCRBean;
import cn.xsshome.mvcdo.pojo.ai.tencent.po.YouTuFaceBean;
import cn.xsshome.mvcdo.service.ai.tencent.YouTuFuseService;
import cn.xsshome.mvcdo.util.FileUtil;
import cn.xsshome.mvcdo.util.PrintUtil;
import cn.xsshome.mvcdo.util.QQSendEmailUtil;
import cn.xsshome.mvcdo.util.youtu.HttpUtil4YouTu;
import cn.xsshome.mvcdo.util.youtu.YouTuSign;
import cn.xsshome.mvcdo.vo.BDConstant;
import cn.xsshome.mvcdo.vo.RestResponse;
import cn.xsshome.mvcdo.vo.YouTuFuseResponse;
import cn.xsshome.taip.util.Base64Util;

/**
 * 
 * @author 小帅丶
 * @date 2018年5月31日
 * <p>Description: 腾讯优图rest服务</p>
 */
@Controller
@RequestMapping(value="rest/youtu")
@Scope("prototype")
public class YouTuRestController {
	 private static Logger logger = LoggerFactory.getLogger(YouTuRestController.class);
	 @Autowired
	 private YouTuFuseService youTuFuseService;
	 @RequestMapping(value = "/detect",method = {RequestMethod.POST})
	 public String uploadOcr(@RequestParam(value = "file")MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception{
			String resultData = "";	
			String clientType = request.getParameter("clientType");
			String apiType = request.getParameter("apiType");
	        String openId = ServletRequestUtils.getStringParameter(request, "openId","");
	        String nickName = ServletRequestUtils.getStringParameter(request, "nickName","");
			logger.info("=======访问的IP"+request.getRemoteAddr()+"======访问的User-Agent:"+request.getHeader("User-Agent"));
			logger.info("=======访问的类型"+clientType+"=======访问的接口类型"+apiType); 
	        if(null==clientType||null==apiType){
				 RestResponse restResponse = new RestResponse();
				 restResponse.setCode(BDConstant.BD_NULL.getCode().toString());
				 restResponse.setMsg(BDConstant.BD_NULL.getMsg());
	             resultData = JSON.toJSONString(restResponse);
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
						 RestResponse restResponse = new RestResponse();
						 restResponse.setCode(BDConstant.BD_403.getCode().toString());
						 restResponse.setMsg(BDConstant.BD_403.getMsg());
		                 resultData = JSON.toJSONString(restResponse);
		                 logger.info("=====接口返回的内容:"+resultData);
		                 PrintUtil.printJson(response,resultData);
					}else{
						 RestResponse restResponse = new RestResponse();
						 restResponse.setCode(BDConstant.BD_NOTFUND.getCode().toString());
						 restResponse.setMsg(BDConstant.BD_NOTFUND.getMsg());
		                 resultData = JSON.toJSONString(restResponse);
		                 logger.info("=====接口返回的内容:"+resultData);
		                 PrintUtil.printJson(response,resultData);
					}
				}else if (clientType!=null&&clientType.equals("wcs")) {
					String authCode = request.getParameter("authCode");
	            	if(null==authCode||!authCode.equals(AIConstant.AUTH_CODE)){
	            		RestResponse restResponse = new RestResponse();
	            		restResponse.setCode(BDConstant.BD_NOTFUND.getCode().toString());
	            		restResponse.setMsg(BDConstant.BD_NOTFUND.getMsg());
	                    resultData = JSON.toJSONString(restResponse);
	                    logger.info("=====接口返回的内容:"+resultData);
	                    PrintUtil.printJson(response,resultData);
	            	}
				}
				FileUtil.uploadFile(file.getBytes(),filePath,fileName);
				System.out.println("end====="+new Date().getTime());
				 //图片的本地路径
	            String imagePath = filePath+fileName;
	            dbPath += fileName;
	            resultData = getYoutuResult(apiType,clientType,dbPath,imagePath,openId,nickName);
	            PrintUtil.printJson(response,resultData);
			} catch (Exception e) {
				logger.info("优图接口出错了"+e.getMessage()+"====接口类型："+apiType);
				RestResponse restResponse = new RestResponse();
				restResponse.setCode(BDConstant.BD_ERROR.getCode().toString());
				restResponse.setMsg(BDConstant.BD_ERROR.getMsg());
	            resultData = JSON.toJSONString(restResponse);
	            QQSendEmailUtil.send_email("优图接口错误", "接口类型："+apiType+"\n"+e.getMessage(), AIConstant.EMAIL_ADDRESS);
	            System.out.println(resultData);
	            PrintUtil.printJson(response,resultData);
			}
	        }
		return null;
	 }
	 /**
	  * 获取请求接口返回内容
	  * @param apiType
	  * @param clientType
	  * @param dbPath
	  * @param imagePath
	  * @return
	  * @throws Exception
	  */
	 private String getYoutuResult(String apiType, String clientType, String dbPath, String imagePath,String openId,String nickName) throws Exception {
		String imgStr = Base64Util.encode(FileUtil.readFileByBytes(imagePath));
		String result = "";
		String resultJSON = "";
		String params = "{\"app_id\":\""+AIConstant.YOUTU_AppID+"\",\"image\":\""+imgStr+"\"}";
		String sign = YouTuSign.getSign(AIConstant.YOUTU_userQQ, AIConstant.YOUTU_AppID, AIConstant.YOUTU_SecretID, AIConstant.YOUTU_SecretKey);
		if(apiType.equals("hw")){
			resultJSON = HttpUtil4YouTu.post(YouTuAPIS.OCRAPI_HANDWRITINGOCR, params, sign);
			result = getYouTuData(resultJSON,apiType,dbPath,imagePath,clientType,openId,nickName);
		}else if (apiType.equals("ht")) {
			resultJSON = HttpUtil4YouTu.post(YouTuAPIS.HANDTRACKING_CLASSIFY, params, sign);
			result = getYouTuData(resultJSON,apiType,dbPath,imagePath,clientType,openId,nickName);
		}else if (apiType.equals("face")) {
			resultJSON = HttpUtil4YouTu.post(YouTuAPIS.FACEDETECT, params, sign);
			result = getYouTuData(resultJSON,apiType,dbPath,imagePath,clientType,openId,nickName);
		}else {
			result = null;
		}
		return result;
	}
	private String getYouTuData(String resultJSON, String apiType, String dbPath, String imagePath,String clientType,String openId,String nickName) throws Exception {
		JSONObject object = new JSONObject(resultJSON);
		YouTuDetectFuseDO youTuDetectFuseDO = new YouTuDetectFuseDO();
		youTuDetectFuseDO.setOpenId(openId);
		youTuDetectFuseDO.setNikeName(nickName);
		String result = "";
		if(object.getInt("errorcode")!=0){
			RestResponse response = new RestResponse();
			if(object.getString("errormsg").equals("HANDAR_NO_HANDS")){
				response.setCode(BDConstant.YT_NOHT.getCode().toString());
				response.setMsg(BDConstant.YT_NOHT.getMsg());
				youTuDetectFuseDO.setErrorcode(object.getInt("errorcode"));
				youTuDetectFuseDO.setErrormsg(object.getString("errormsg"));
				youTuDetectFuseDO.setApiType(apiType);
				youTuDetectFuseDO.setEnterType(clientType);
				youTuDetectFuseDO.setImagePath(dbPath);
				youTuFuseService.saveYouTuFuse(youTuDetectFuseDO);
				result = JSON.toJSONString(response);
			} else if (object.getString("errormsg").equals("HWOCR_RECOG_FAILED")) {
				response.setCode(BDConstant.YT_NOHWOCR.getCode().toString());
				response.setMsg(BDConstant.YT_NOHWOCR.getMsg());
				youTuDetectFuseDO.setErrorcode(object.getInt("errorcode"));
				youTuDetectFuseDO.setErrormsg(object.getString("errormsg"));
				youTuDetectFuseDO.setApiType(apiType);
				youTuDetectFuseDO.setEnterType(clientType);
				youTuDetectFuseDO.setImagePath(dbPath);
				youTuFuseService.saveYouTuFuse(youTuDetectFuseDO);
				result = JSON.toJSONString(response);
			} else if (object.getString("errormsg").equals("SDK_IMAGE_FACEDETECT_FAILED")) {
				response.setCode(BDConstant.BD_NOFACE.getCode().toString());
				response.setMsg(BDConstant.BD_NOFACE.getMsg());
				youTuDetectFuseDO.setErrorcode(object.getInt("errorcode"));
				youTuDetectFuseDO.setErrormsg(object.getString("errormsg"));
				youTuDetectFuseDO.setApiType(apiType);
				youTuDetectFuseDO.setEnterType(clientType);
				youTuDetectFuseDO.setImagePath(dbPath);
				youTuFuseService.saveYouTuFuse(youTuDetectFuseDO);
				result = JSON.toJSONString(response);
			} else {
				QQSendEmailUtil.send_email("优图接口异常", "接口类型："+apiType+"\n"+object.toString(2), AIConstant.EMAIL_ADDRESS);
				response.setCode(BDConstant.BD_ERROR.getCode().toString());
				response.setMsg(BDConstant.BD_ERROR.getMsg());
				result = JSON.toJSONString(response);
			}
		} else {
			if(apiType.equals("face")){
				YouTuFuseResponse youTuFuse = new YouTuFuseResponse();
				youTuFuse.setCode(BDConstant.BD_SUCCESS.getCode().toString());
	            youTuFuse.setMsg(BDConstant.BD_SUCCESS.getMsg());
	            YouTuFaceBean bean = JSON.parseObject(object.toString(), YouTuFaceBean.class);
	            youTuFuse.setAge(String.valueOf(bean.getFace().get(0).getAge()));
	            youTuFuse.setGlasses(getGlasses(bean.getFace().get(0).getGlasses()));
	            youTuFuse.setExpression(getExpression(bean.getFace().get(0).getExpression()));
	            youTuFuse.setBeauty(String.valueOf(bean.getFace().get(0).getBeauty()));
	            youTuDetectFuseDO.setErrorcode(bean.getErrorcode());
				youTuDetectFuseDO.setErrormsg(bean.getErrormsg());
				youTuDetectFuseDO.setApiType(apiType);
				youTuDetectFuseDO.setEnterType(clientType);
				youTuDetectFuseDO.setImagePath(dbPath);
				youTuDetectFuseDO.setFaceId(bean.getFace().get(0).getFace_id());
				youTuDetectFuseDO.setAge(bean.getFace().get(0).getAge());
				youTuDetectFuseDO.setGender(bean.getFace().get(0).getGender());
				youTuDetectFuseDO.setExpression(bean.getFace().get(0).getExpression());
				youTuDetectFuseDO.setGlasses(bean.getFace().get(0).getGlasses());
				youTuDetectFuseDO.setBeauty(bean.getFace().get(0).getBeauty());
				youTuDetectFuseDO.setHat(bean.getFace().get(0).getHat());
				youTuDetectFuseDO.setMask(bean.getFace().get(0).getMask());
				youTuFuseService.saveYouTuFuse(youTuDetectFuseDO);
	            result = JSON.toJSONString(youTuFuse);
			}else if (apiType.equals("ht")) {
                String label = "";
                String confidence = "";
                YouTuFuseResponse youTuFuse = new YouTuFuseResponse();
				HandTrackingBean bean = JSON.parseObject(object.toString(), HandTrackingBean.class);
				  if(bean.getClassify_cnt()==0){
				    RestResponse response = new RestResponse();
					response.setCode(BDConstant.YT_NOHT.getCode().toString());
					response.setMsg(BDConstant.YT_NOHT.getMsg());
					result = JSON.toJSONString(response);
				  }else{
				     for (int i = 0; i < bean.getItems().size(); i++) {
						 label +=  HandTrackEnum.getLabelName(bean.getItems().get(i).getLabel())+",";
						 confidence += bean.getItems().get(i).getConfidence()+"%,";
		              } 
		              youTuFuse.setCode(BDConstant.BD_SUCCESS.getCode().toString());
		              youTuFuse.setMsg(BDConstant.BD_SUCCESS.getMsg());
		              youTuFuse.setLabel(label.substring(0, label.length()-1));
		              youTuFuse.setClassifyCnt(bean.getClassify_cnt());
		              youTuFuse.setConfidence(confidence.substring(0,confidence.length()-1));
		              youTuDetectFuseDO.setErrorcode(bean.getErrorcode());
					  youTuDetectFuseDO.setErrormsg(bean.getErrormsg());
					  youTuDetectFuseDO.setApiType(apiType);
					  youTuDetectFuseDO.setEnterType(clientType);
					  youTuDetectFuseDO.setImagePath(dbPath);
					  youTuDetectFuseDO.setLabel(youTuFuse.getLabel());
					  youTuDetectFuseDO.setConfidence(youTuFuse.getConfidence());
					  youTuDetectFuseDO.setClassifyCnt(youTuFuse.getClassifyCnt());
					  youTuFuseService.saveYouTuFuse(youTuDetectFuseDO);
		              result = JSON.toJSONString(youTuFuse);
				  }
			}else if (apiType.equals("hw")) {
				 YouTuFuseResponse youTuFuse = new YouTuFuseResponse();
				 HandWritingOCRBean bean = JSON.parseObject(object.toString(), HandWritingOCRBean.class);
	              String label = "";
	              for (int i = 0; i < bean.getItems().size(); i++) {
					 label +=  bean.getItems().get(i).getItemstring()+",";
	              }
	              youTuFuse.setCode(BDConstant.BD_SUCCESS.getCode().toString());
	              youTuFuse.setMsg(BDConstant.BD_SUCCESS.getMsg());
	              youTuFuse.setLabel(label.substring(0, label.length()-1));
		          youTuDetectFuseDO.setErrorcode(bean.getErrorcode());
				  youTuDetectFuseDO.setErrormsg(bean.getErrormsg());
				  youTuDetectFuseDO.setApiType(apiType);
				  youTuDetectFuseDO.setEnterType(clientType);
				  youTuDetectFuseDO.setImagePath(dbPath);
				  youTuDetectFuseDO.setItemstring(youTuFuse.getLabel());
				  youTuFuseService.saveYouTuFuse(youTuDetectFuseDO);
	              result = JSON.toJSONString(youTuFuse);
			}else{
				RestResponse response = new RestResponse();
				QQSendEmailUtil.send_email("优图接口异常", "接口类型："+apiType+"\n"+object.toString(2), AIConstant.EMAIL_ADDRESS);
				response.setCode(BDConstant.BD_ERROR.getCode().toString());
				response.setMsg(BDConstant.BD_ERROR.getMsg());
				result = JSON.toJSONString(response);
			}
		}
		return result;
	}
	/**
	 * 获取眼睛中文说明
	 * @param glasses 眼镜[0不戴眼镜 1戴眼镜 2戴墨镜]
	 * @return
	 */
	private String getGlasses(int glasses) {
		if(glasses==0){
			return "不戴眼镜";
		}else if (glasses==1) {
			return "戴眼镜";
		}else if (glasses==2) {
			return "戴墨镜";
		}else{
			return "未知";
		}
	}
	/**
	 * 获取表情中文说明
	 * @param expression 微笑[0(normal)~50(smile)~100(laugh)]
	 * @return
	 */
	private String getExpression(int expression) {
		if(expression<50){
			return "无表情";
		}else if (expression<99) {
			return "微笑";
		}else{
			return "大笑";
		}
	}
	/**
     * 根据类型存不同的文件夹
     * @param apiType
     * @return String
     */
	private String getPerfix(String apiType) {
		if(apiType.equals("face")){
			return "youtuFace/";
		}else if (apiType.equals("hw")) {
			return "youtuHw/";
		}else if (apiType.equals("ht")) {
			return "youtuHt/";
		}else {
			return "youtuNull/";
		}
	}
}
