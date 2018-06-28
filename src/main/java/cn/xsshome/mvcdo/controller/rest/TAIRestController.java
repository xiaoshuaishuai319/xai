package cn.xsshome.mvcdo.controller.rest;

import java.io.File;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import com.baidu.aip.util.Base64Util;

import cn.xsshome.mvcdo.common.AIConstant;
import cn.xsshome.mvcdo.interceptor.BDFactory;
import cn.xsshome.mvcdo.pojo.ai.baidu.po.FaceV3MatchBean;
import cn.xsshome.mvcdo.pojo.ai.tencent.po.FaceAgeBean;
import cn.xsshome.mvcdo.pojo.ai.tencent.po.FaceAgeBean.Data;
import cn.xsshome.mvcdo.util.FileUtil;
import cn.xsshome.mvcdo.util.PrintUtil;
import cn.xsshome.mvcdo.util.QQSendEmailUtil;
import cn.xsshome.mvcdo.vo.BDConstant;
import cn.xsshome.mvcdo.vo.BDDishResponse;
import cn.xsshome.mvcdo.vo.BdFaceResponse;
import cn.xsshome.taip.face.TAipFace;
import cn.xsshome.taip.ptu.TAipPtu;
/**
 * @author 小帅丶
 * @date 2018年5月29日
 * <p>Description: 腾讯ai人脸融合 人脸对比</p>
 */
@Controller
@RequestMapping(value="rest/ptu")
@Scope("prototype")
public class TAIRestController {
	private static Logger logger = LoggerFactory.getLogger(TAIRestController.class);
	TAipPtu aipPtu = new TAipPtu(AIConstant.QQ_APPID, AIConstant.QQ_APPKEY);
	TAipFace aipFace = new TAipFace(AIConstant.QQ_APPID, AIConstant.QQ_APPKEY);
	AipFace aipFaceBD = BDFactory.getAipFace();
	private static final String perfix = "faceCrossAge/";
	/**
	 * 人脸融合接口
	 * @param file
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/facemerge",method={RequestMethod.POST})
	public void uoloadFaceMerge(@RequestParam(value = "file")MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		String resultData = "";	
		String clientType = request.getParameter("clientType");
		int model = Integer.parseInt(request.getParameter("model"));
		logger.info("=======访问的IP"+request.getRemoteAddr()+"======访问的User-Agent:"+request.getHeader("User-Agent"));
		logger.info("=======访问的类型"+clientType+"=======模板id："+model); 
		try {
			if(!clientType.equals("wsc")){
				 BDDishResponse bdDishResponse = new BDDishResponse();
				 bdDishResponse.setCode(BDConstant.BD_403.getCode().toString());
				 bdDishResponse.setMsg("缺少必要参数");
                resultData = JSON.toJSONString(bdDishResponse);
                logger.info("=====接口返回的内容:"+resultData);
                PrintUtil.printJson(response,resultData);
			}else{
				String authCode = request.getParameter("authCode");
				if(null==authCode||!authCode.equals(AIConstant.AUTH_CODE)){
					BDDishResponse bdDishResponse = new BDDishResponse();
            		bdDishResponse.setCode(BDConstant.BD_NOTFUND.getCode().toString());
            		bdDishResponse.setMsg(BDConstant.BD_NOTFUND.getMsg());
                    resultData = JSON.toJSONString(bdDishResponse);
                    logger.info("=====接口返回的内容:"+resultData);
                    PrintUtil.printJson(response,resultData);
				}else{
					byte[] image = file.getBytes();
					resultData = aipPtu.faceMerge(image, model);
					JSONObject object = JSON.parseObject(resultData);
					if(object.getInteger("ret")!=0){
						QQSendEmailUtil.send_email("人脸融合接口错误警报", "接口返回内容：\n"+resultData+"\n访问接口的ip："+request.getRemoteAddr(), AIConstant.EMAIL_ADDRESS);
					}
					PrintUtil.printJson(response, resultData);
				}
			}
		} catch (Exception e) {
			logger.info("人脸融合接口出错了"+e.getMessage());
			BDDishResponse bdDishResponse = new BDDishResponse();
			bdDishResponse.setCode(BDConstant.BD_ERROR.getCode().toString());
			bdDishResponse.setMsg(BDConstant.BD_ERROR.getMsg());
            resultData = JSON.toJSONString(bdDishResponse);
            System.out.println(resultData);
            PrintUtil.printJson(response,resultData);
		}
	}
	 @RequestMapping(value = "/uploadFA",method = {RequestMethod.POST})
	public void uploadFA(@RequestParam(value = "file")MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String resultData = "";
        String clientType = request.getParameter("clientType");
        String openId = ServletRequestUtils.getStringParameter(request, "openId","");
        String nickName = ServletRequestUtils.getStringParameter(request, "nickName","");
		try {
			nickName = URLEncoder.encode(nickName, "UTF-8");
	        String fileName = "faceCrossAge"+new Date().getTime()/1000+FileUtil.fileType(file.getOriginalFilename());
	        String filePath = request.getSession().getServletContext().getRealPath(perfix);
	        logger.info("=======保存的路径"+filePath+"/"+fileName);
	        if(null!=clientType&&clientType.equals("wsc")){
	    	  String authCode = request.getParameter("authCode");
	          logger.info("=======authCode:"+authCode);
	          if(null==authCode||!authCode.equals(AIConstant.AUTH_CODE)){
	         	 BdFaceResponse bdFaceResponse = new BdFaceResponse();
	              bdFaceResponse.setCode(BDConstant.BD_NOTFUND.getCode().toString());
	              bdFaceResponse.setMsg(BDConstant.BD_NOTFUND.getMsg());
	              resultData = JSON.toJSONString(bdFaceResponse);
	              logger.info("=====接口返回的内容:"+resultData);
	              PrintUtil.printJson(response,resultData);
	         }else{
	        	 FileUtil.uploadFile(file.getBytes(),filePath,fileName);
	             resultData = "{\"code\":\"0\",\"fileName\":\""+fileName+"\"}";
	             System.out.println(resultData);
	             PrintUtil.printJson(response,resultData);
	         }
	        }else{
	        	 BdFaceResponse bdFaceResponse = new BdFaceResponse();
	             bdFaceResponse.setCode(BDConstant.BD_NOTFUND.getCode().toString());
	             bdFaceResponse.setMsg("亲，接口貌似丢失了参数哦");
	             resultData = JSON.toJSONString(bdFaceResponse);
	             logger.info("=====接口返回的内容:"+resultData);
	             PrintUtil.printJson(response,resultData);
	        }
		} catch (Exception e) {
			QQSendEmailUtil.send_email("人脸对比接口上传图片异常", "openId："+openId+"\nnickName："+nickName, AIConstant.EMAIL_ADDRESS);
			logger.error("人脸对比接口上传图片出错了"+e.getMessage());
            BdFaceResponse bdFaceResponse = new BdFaceResponse();
            bdFaceResponse.setCode(BDConstant.BD_ERROR.getCode().toString());
            bdFaceResponse.setMsg(BDConstant.BD_ERROR.getMsg());
            resultData = JSON.toJSONString(bdFaceResponse);
            System.out.println(resultData);
            PrintUtil.printJson(response,resultData);
		}
	}
	/**
	 * 人脸跨年龄对比
	 * @throws Exception
	 */
	@RequestMapping(value="/detectFA",method=RequestMethod.GET)
	public void detectFA(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String openId = ServletRequestUtils.getStringParameter(request, "openId","");
        String nickName = ServletRequestUtils.getStringParameter(request, "nickName","");
        logger.info("openId："+openId+"\nnickName："+nickName);
		Data data = new Data();
		nickName = URLEncoder.encode(nickName, "UTF-8");
		DecimalFormat df = new DecimalFormat("#.00");   
		 String result = "";
		 String imgNameA = request.getParameter("imgNameA");
		 String imgNameB = request.getParameter("imgNameB");
		 if(null==imgNameA||null==imgNameB){
			 result = "{\"ret\":\"fail\",\"msg\":\"系统错误，请等待修复\"}";
	         PrintUtil.printJson(response, result); 
	         QQSendEmailUtil.send_email("人脸跨年龄对比接口异常", "openId："+openId+"\nnickName："+nickName+"接口获取的参数:\nimgNameA:"+imgNameA+"imgNameB"+imgNameB+"\n请求的IP:"+request.getRemoteAddr(), AIConstant.EMAIL_ADDRESS);
		 }else{
			 String despath = perfix;
		     String baseDespath = getRealPath(despath,request);
		     String filePathA = baseDespath+"/"+imgNameA;
		     String filePathB = baseDespath+"/"+imgNameB;
		     File fileA = new File(filePathA);
		     File fileB = new File(filePathB);
		     logger.info("路径A:"+filePathA+"==路径B:"+filePathB);
		     if(!fileA.exists()||!fileB.exists()){
				 result = "{\"ret\":31910,\"msg\":\"系统错误，请等待修复\"}";
		         PrintUtil.printJson(response, result);  
		         QQSendEmailUtil.send_email("人脸跨年龄对比接口异常", "openId："+openId+"\nnickName："+nickName+"文件不存在\n文件的路径A:"+filePathA+"\n文件的路径B:"+filePathB+"\n接口获取的参数:\nimgNameA:"+imgNameA+"\nimgNameB:"+imgNameB+"\n请求的IP:"+request.getRemoteAddr(), AIConstant.EMAIL_ADDRESS);
		     }else{
		 		String authCode = request.getParameter("authCode");
				List<MatchRequest> faceLists = new ArrayList<MatchRequest>();
				MatchRequest matchRequestA = new MatchRequest(Base64Util.encode(FileUtil.readFileByBytes(filePathA)), "BASE64");
				MatchRequest matchRequestB = new MatchRequest(Base64Util.encode(FileUtil.readFileByBytes(filePathB)), "BASE64");
				faceLists.add(matchRequestA);faceLists.add(matchRequestB);
				if(null==authCode||!authCode.equals(AIConstant.AUTH_CODE)){
					result = "{\"ret\":31910,\"msg\":\"亲,您不能直接访问接口地址  代码都是开源的。有需要加QQ:783021975\"}";
					logger.info("返回的结果:"+result+"====访问的IP"+request.getRemoteAddr());
					PrintUtil.printJson(response, result);
					QQSendEmailUtil.send_email("接口异常", "openId："+openId+"\nnickName："+nickName+"接口错误:\n"+result+"\n访问的IP:"+request.getRemoteAddr(), AIConstant.EMAIL_ADDRESS);
				}else{
					result = aipFace.detectCrossage(FileUtil.readFileByBytes(filePathA), FileUtil.readFileByBytes(filePathB));
					FaceAgeBean ageBean = JSONObject.toJavaObject(JSON.parseObject(result), FaceAgeBean.class);
					if(ageBean.getRet()!=0){
						QQSendEmailUtil.send_email("人脸跨年龄对比接口异常", "openId："+openId+"\nnickName："+nickName+"接口返回:\n"+result+"\n请求的IP:"+request.getRemoteAddr(), AIConstant.EMAIL_ADDRESS);
					}
					//百度对比
					org.json.JSONObject object = aipFaceBD.match(faceLists);
					FaceV3MatchBean faceV3MatchBean = JSONObject.toJavaObject(JSON.parseObject(object.toString()), FaceV3MatchBean.class);
					if(faceV3MatchBean.getError_code()==0&&faceV3MatchBean.getError_msg().equals("SUCCESS")){
						 double scoreBD = faceV3MatchBean.getResult().getScore();
							data.setScores(df.format(scoreBD));
							if(scoreBD<30){
								data.setInfo("同一个人的可能性极低");
							}else if (scoreBD>30&&scoreBD<60) {
								data.setInfo("同一个人的可能性低");
							}else if(scoreBD>60&&scoreBD<80){
								data.setInfo("同一个人的可能性高");
							} else {
								data.setInfo("同一个人的可能性极高");
							}
					}else{
						QQSendEmailUtil.send_email("人脸对比V3接口异常", "openId："+openId+"\nnickName："+nickName+"接口返回:\n"+object.toString(2)+"\n请求的IP:"+request.getRemoteAddr(), AIConstant.EMAIL_ADDRESS);
					}
					double score = ageBean.getData().getScore()*100;
					data.setDegree(df.format(score));
					ageBean.setData(data);
					logger.info("===接口返回："+JSON.toJSONString(ageBean));
					PrintUtil.printJson(response, JSON.toJSONString(ageBean));
				}	 
		     }
		 }

	}
	/**
	 * 
	 * @param path
	 * @param request
	 * @return String
	 */
	public String getRealPath(String path,HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath(path);
	}
}
