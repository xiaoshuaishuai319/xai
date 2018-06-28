package cn.xsshome.mvcdo.controller.rest;


import java.net.URLEncoder;
import java.text.DecimalFormat;
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

import cn.xsshome.mvcdo.common.AIConstant;
import cn.xsshome.mvcdo.common.AipAdded;
import cn.xsshome.mvcdo.interceptor.BDFactory;
import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDICRDishDO;
import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDICRFuseDO;
import cn.xsshome.mvcdo.pojo.ai.baidu.po.BDICRDishBean;
import cn.xsshome.mvcdo.pojo.ai.baidu.po.BDICRFuseBean;
import cn.xsshome.mvcdo.service.ai.baidu.BDICRDetectService;
import cn.xsshome.mvcdo.util.FileUtil;
import cn.xsshome.mvcdo.util.PrintUtil;
import cn.xsshome.mvcdo.vo.BDConstant;
import cn.xsshome.mvcdo.vo.BDDishResponse;
import cn.xsshome.mvcdo.vo.BDICRFuseResponse;

/**
 * 
 * @author 小帅丶
 * @date 2018年5月9日
 * <p>Description: 图像识别rest服务</p>
 */
@Controller
@RequestMapping(value="rest/icr")
@Scope("prototype")
public class BDImageClassifyRestController {
	AipAdded aipAdded = BDFactory.getAipAdded();
	private static Logger logger = LoggerFactory.getLogger(BDImageClassifyRestController.class);
	@Autowired
	private BDICRDetectService bdicrDetectService;
	/**
	 * 图像识别网页上传
	 * @param request request对象
	 * @param response response对象
	 * @return 页面
	 */
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		logger.info("index跳转图像识别网页==访问的ip:"+request.getRemoteAddr());
		return "rest/bdicrfile";
	}
	/**
	 * 图像识别上传百度
	 * @param file 文件
	 * @param request 
	 * @param response
	 * @return
	 */
    @RequestMapping(value = "/detect",method = {RequestMethod.POST})
    public String uploadImageClassify(@RequestParam(value = "file")MultipartFile file, HttpServletRequest request, HttpServletResponse response){
    	 String resultData = "";
    	String clientType = request.getParameter("clientType");
        String apiType = request.getParameter("apiType");
        String openId = ServletRequestUtils.getStringParameter(request, "openId","");
        String nickName = ServletRequestUtils.getStringParameter(request, "nickName","");
        logger.info("=======访问的IP"+request.getRemoteAddr()+"======访问的User-Agent:"+request.getHeader("User-Agent"));
        logger.info("=======访问的类型"+clientType+"=======访问的接口类型"+apiType);
        if(null==clientType||null==apiType){
			 BDDishResponse bdDishResponse = new BDDishResponse();
			 bdDishResponse.setCode(BDConstant.BD_NULL.getCode().toString());
			 bdDishResponse.setMsg(BDConstant.BD_NULL.getMsg());
             resultData = JSON.toJSONString(bdDishResponse);
             logger.info("=====接口返回的内容:"+resultData);
             PrintUtil.printJson(response,resultData);
        }else{
        String perfix = getPerfix(apiType);
        String dbPath ="/"+perfix;
        String fileName = "icrBD"+new Date().getTime()/1000+FileUtil.fileType(file.getOriginalFilename());
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
			}else if (clientType!=null&&clientType.equals("wcs")) {
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
				 //图片的本地路径
	            String imagePath = filePath+fileName;
	            HashMap<String, String> option = new HashMap<String, String>();
	            option.put("top_num", "1");
	            if(apiType.equals("dish")){
		            JSONObject jsonObject = aipAdded.dishDetect(imagePath, option);
		            BDICRDishBean bdDishJson = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(), BDICRDishBean.class);
		            logger.info("百度服务返回=======\n"+jsonObject.toString(2));
		            dbPath += fileName;
		            resultData = getResultDishData(bdDishJson,apiType,clientType,dbPath,openId,nickName);
		            logger.info("=====接口返回的内容:"+resultData);
		            PrintUtil.printJson(response,resultData);
	            } else if (apiType.equals("plant")||apiType.equals("animal")||apiType.equals("ingredient")||apiType.equals("logo")||apiType.equals("car")) {
	            	JSONObject jsonObject = getFuseObject(imagePath,apiType,option);
	            	BDICRFuseBean bdicrFuseBean = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(), BDICRFuseBean.class);
	            	dbPath += fileName;
		            resultData = getResultFuseData(bdicrFuseBean,apiType,clientType,dbPath,openId,nickName);
		            logger.info("=====接口返回的内容:"+resultData);
	                PrintUtil.printJson(response,resultData);
				} else {
	            	BDDishResponse bdDishResponse = new BDDishResponse();
	    			bdDishResponse.setCode(BDConstant.BD_ERROR.getCode().toString());
	    			bdDishResponse.setMsg(BDConstant.BD_ERROR.getMsg());
	                resultData = JSON.toJSONString(bdDishResponse);
	                logger.info("=====接口返回的内容:"+resultData);
	                PrintUtil.printJson(response,resultData);
				}
		} catch (Exception e) {
			logger.info("图像识别百度接口出错了"+e.getMessage()+"====接口类型："+apiType);
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
     * 拼接返回的接口数据
     * @param bdicrFuseBean 接口返回的数据
     * @param apiType 接口类型
     * @param clientType 访问类型
     * @param dbPath 图片路径
     * @return
     */
    private String getResultFuseData(BDICRFuseBean bdicrFuseBean, String apiType, String clientType, String dbPath,String openId, String nickName) {
    	String resultData="";
    	if (apiType.equals("plant")||apiType.equals("animal")||apiType.equals("ingredient")) {
    		if(bdicrFuseBean.getResult().get(0).getName().equals("非动物")||bdicrFuseBean.getResult().get(0).getName().equals("非植物")||bdicrFuseBean.getResult().get(0).getName().equals("非果蔬食材")){
    			BDDishResponse bdDishResponse = new BDDishResponse();
        		bdDishResponse.setCode(BDConstant.BD_NOFACE.getCode().toString());
        		String msg = getNoMsg(apiType);
        		bdDishResponse.setMsg(msg);
                resultData = JSON.toJSONString(bdDishResponse);
    		}else{
    			BDICRFuseResponse fuseResponse = new BDICRFuseResponse();
    			fuseResponse.setCode(BDConstant.BD_SUCCESS.getCode().toString());
    			fuseResponse.setMsg(BDConstant.BD_SUCCESS.getMsg());
    			fuseResponse.setIcrName(bdicrFuseBean.getResult().get(0).getName());
    			fuseResponse.setScore(getPercent(Double.parseDouble(bdicrFuseBean.getResult().get(0).getScore())*100));
    			BDICRFuseDO bdicrFuseDO = new BDICRFuseDO();
    			bdicrFuseDO.setOpenId(openId);
    			bdicrFuseDO.setNikeName(nickName);
    			bdicrFuseDO.setApiType(apiType);
    			bdicrFuseDO.setLogId(String.valueOf(bdicrFuseBean.getLog_id()));
    			if(apiType.equals("ingredient")){
    				bdicrFuseDO.setResultNum(bdicrFuseBean.getResult_num());
    			}
    			bdicrFuseDO.setIcrName(bdicrFuseBean.getResult().get(0).getName());
    			bdicrFuseDO.setScore(bdicrFuseBean.getResult().get(0).getScore());
    			bdicrFuseDO.setImagePath(dbPath);
    			bdicrFuseDO.setEnterType(clientType);
    			int result = bdicrDetectService.saveFuse(bdicrFuseDO);
    			logger.info("====保存成功了："+result);
    			resultData = JSON.toJSONString(fuseResponse);
    		}
    	} else {
			if(apiType.equals("logo")){
				if(bdicrFuseBean.getResult_num()==0){
	    			BDDishResponse bdDishResponse = new BDDishResponse();
	        		bdDishResponse.setCode(BDConstant.BD_NOFACE.getCode().toString());
	        		String msg = getNoMsg(apiType);
	        		bdDishResponse.setMsg(msg);
	                resultData = JSON.toJSONString(bdDishResponse);
				}else{
					BDICRFuseResponse fuseResponse = new BDICRFuseResponse();
	    			fuseResponse.setCode(BDConstant.BD_SUCCESS.getCode().toString());
	    			fuseResponse.setMsg(BDConstant.BD_SUCCESS.getMsg());
	    			fuseResponse.setIcrName(bdicrFuseBean.getResult().get(0).getName());
	    			fuseResponse.setProbability(getPercent(Double.parseDouble(bdicrFuseBean.getResult().get(0).getProbability())*100));
	    			BDICRFuseDO bdicrFuseDO = new BDICRFuseDO();
	    			bdicrFuseDO.setOpenId(openId);
	    			bdicrFuseDO.setNikeName(nickName);
	    			bdicrFuseDO.setApiType(apiType);
	    			bdicrFuseDO.setLogId(String.valueOf(bdicrFuseBean.getLog_id()));
	    			bdicrFuseDO.setResultNum(bdicrFuseBean.getResult_num());
	    			bdicrFuseDO.setIcrName(bdicrFuseBean.getResult().get(0).getName());
	    			bdicrFuseDO.setProbability(bdicrFuseBean.getResult().get(0).getProbability());
	    			bdicrFuseDO.setLocalWidth(bdicrFuseBean.getResult().get(0).getLocation().getWidth());
	    			bdicrFuseDO.setLocalHeight(bdicrFuseBean.getResult().get(0).getLocation().getHeight());
	    			bdicrFuseDO.setLocalTop(bdicrFuseBean.getResult().get(0).getLocation().getTop());
	    			bdicrFuseDO.setLocalLeft(bdicrFuseBean.getResult().get(0).getLocation().getLeft());
	    			bdicrFuseDO.setLogoType(String.valueOf(bdicrFuseBean.getResult().get(0).getLogoType()));
	    			bdicrFuseDO.setImagePath(dbPath);
	    			bdicrFuseDO.setEnterType(clientType);
	    			int result = bdicrDetectService.saveFuse(bdicrFuseDO);
	    			logger.info("====保存成功了："+result);
	    			resultData = JSON.toJSONString(fuseResponse);
				}
			}else if (apiType.equals("car")) {
				if(bdicrFuseBean.getResult().get(0).getName().equals("非车类")){
	    			BDDishResponse bdDishResponse = new BDDishResponse();
	        		bdDishResponse.setCode(BDConstant.BD_NOFACE.getCode().toString());
	        		String msg = getNoMsg(apiType);
	        		bdDishResponse.setMsg(msg);
	                resultData = JSON.toJSONString(bdDishResponse);
				}else{
					BDICRFuseResponse fuseResponse = new BDICRFuseResponse();
	    			fuseResponse.setCode(BDConstant.BD_SUCCESS.getCode().toString());
	    			fuseResponse.setMsg(BDConstant.BD_SUCCESS.getMsg());
	    			fuseResponse.setIcrName(bdicrFuseBean.getResult().get(0).getName());
	    			fuseResponse.setScore(getPercent(Double.parseDouble(bdicrFuseBean.getResult().get(0).getScore())*100));
	    			fuseResponse.setColorResult(bdicrFuseBean.getColor_result());
	    			fuseResponse.setYear(bdicrFuseBean.getResult().get(0).getYear());
	    			BDICRFuseDO bdicrFuseDO = new BDICRFuseDO();
	    			bdicrFuseDO.setOpenId(openId);
	    			bdicrFuseDO.setNikeName(nickName);
	    			bdicrFuseDO.setApiType(apiType);
	    			bdicrFuseDO.setLogId(String.valueOf(bdicrFuseBean.getLog_id()));
	    			bdicrFuseDO.setIcrName(bdicrFuseBean.getResult().get(0).getName());
	    			bdicrFuseDO.setScore(bdicrFuseBean.getResult().get(0).getScore());
	    			bdicrFuseDO.setLocalWidth(bdicrFuseBean.getLocation_result().getWidth());
	    			bdicrFuseDO.setLocalHeight(bdicrFuseBean.getLocation_result().getHeight());
	    			bdicrFuseDO.setLocalTop(bdicrFuseBean.getLocation_result().getTop());
	    			bdicrFuseDO.setLocalLeft(bdicrFuseBean.getLocation_result().getLeft());
	    			bdicrFuseDO.setPyear(bdicrFuseBean.getResult().get(0).getYear());
	    			bdicrFuseDO.setColorResult(bdicrFuseBean.getColor_result());
	    			bdicrFuseDO.setImagePath(dbPath);
	    			bdicrFuseDO.setEnterType(clientType);
	    			int result = bdicrDetectService.saveFuse(bdicrFuseDO);
	    			logger.info("====保存成功了："+result);
	    			resultData = JSON.toJSONString(fuseResponse);
				}
			}else {
				BDDishResponse bdDishResponse = new BDDishResponse();
        		bdDishResponse.setCode(BDConstant.BD_NOTFUND.getCode().toString());
        		bdDishResponse.setMsg(BDConstant.BD_NOTFUND.getMsg());
                resultData = JSON.toJSONString(bdDishResponse);
			}
		}
    	return resultData;
	}
    /**
     * 根据接口类型返回提示数据
     * @param apiType
     * @return
     */
	private String getNoMsg(String apiType) {
		String msg ="";
		if(apiType.equals("plant")){
			msg ="未能识别出植物 Sorry";
		} else if (apiType.equals("animal")) {
			msg ="未能识别出动物 Sorry";
		}else if (apiType.equals("ingredient")) {
			msg ="未能识别出果蔬食材 Sorry";
		}else if (apiType.equals("logo")) {
			msg ="未能识别出logo Sorry";
		}else if (apiType.equals("car")) {
			msg ="未能识别出汽车 Sorry";
		} else {
			msg ="未能识别出内容 Sorry";
		}
		return msg;
	}
	/**
     * 根据接口类型 进行调用接口
     * @param imagePath 识别的图片路径
     * @param apiType 接口类型
     * @param option 接口参数
     * @return JSONObject
     */
    private JSONObject getFuseObject(String imagePath, String apiType, HashMap<String, String> option) {
    	JSONObject jsonObject = new JSONObject();
    	if(apiType.equals("animal")){
    		jsonObject = aipAdded.animalDetect(imagePath, option);
    	}
    	if(apiType.equals("plant")){
    		jsonObject = aipAdded.plantDetect(imagePath, option);
    	}
    	if(apiType.equals("ingredient")){
    		jsonObject = aipAdded.ingredientDetect(imagePath, option);
    	}
    	if(apiType.equals("car")){
    		jsonObject = aipAdded.carDetect(imagePath, option);
    	}
    	if(apiType.equals("logo")){
    		jsonObject = aipAdded.logoSearch(imagePath, option);
    	}
    	logger.info(apiType+"==百度服务返回=======\n"+jsonObject.toString(2));
		return jsonObject;
	}
	/**
     * 拼接数据
     * @param bdDishJson 接口返回的数据
     * @param apiType 接口类型
     * @param clientType 访问类型
     * @param dbPath 图片路径
     * @return
     */
    private String getResultDishData(BDICRDishBean bdDishJson, String apiType, String clientType, String dbPath,String openId,String nickName) {
    	String resultData="";
    	if(bdDishJson.getResult().get(0).getName().equals("非菜")){
    		BDDishResponse bdDishResponse = new BDDishResponse();
    		bdDishResponse.setCode(BDConstant.BD_NOFACE.getCode().toString());
    		bdDishResponse.setMsg("图片中没有检测到菜品");
            resultData = JSON.toJSONString(bdDishResponse);
    	} else {
			BDICRDishDO bdicrDishDO = new BDICRDishDO();
			bdicrDishDO.setOpenId(openId);
			bdicrDishDO.setNikeName(nickName);
			bdicrDishDO.setLogId(String.valueOf(bdDishJson.getLog_id()));
			bdicrDishDO.setResultNum(bdDishJson.getResult_num());
			bdicrDishDO.setCalorie(bdDishJson.getResult().get(0).getCalorie());
			bdicrDishDO.setHasCalorie(String.valueOf(bdDishJson.getResult().get(0).isHas_calorie()));
			bdicrDishDO.setDishName(bdDishJson.getResult().get(0).getName());
			bdicrDishDO.setProbability(bdDishJson.getResult().get(0).getProbability());
			bdicrDishDO.setImagePath(dbPath);
			bdicrDishDO.setEnterType(clientType);
			int result = bdicrDetectService.saveDish(bdicrDishDO);
			BDDishResponse bdDishResponse = new BDDishResponse();
			bdDishResponse.setCode(BDConstant.BD_SUCCESS.getCode().toString());
			bdDishResponse.setMsg(BDConstant.BD_SUCCESS.getMsg());
			bdDishResponse.setCalorie(bdDishJson.getResult().get(0).getCalorie()+"KJ/100g");
			bdDishResponse.setHasCalorie(bdDishJson.getResult().get(0).isHas_calorie()?"是":"否");
			bdDishResponse.setDishName(bdDishJson.getResult().get(0).getName());
			bdDishResponse.setProbability(getPercent(Double.parseDouble(bdDishJson.getResult().get(0).getProbability())*100));
			logger.info("====保存成功了："+result);
			resultData = JSON.toJSONString(bdDishResponse);
			
		}
    	return resultData;
	}
	/**
     * 根据类型存不同的文件夹
     * @param apiType
     * @return
     */
	private String getPerfix(String apiType) {
		if(apiType.equals("dish")){
			return "icrDish/";
		}else if (apiType.equals("logo")) {
			return "icrLogo/";
		}else if (apiType.equals("car")) {
			return "icrCar/";
		}else if (apiType.equals("animal")) {
			return "icrAnimal/";
		} else if (apiType.equals("plant")) {
			return "icrPlant/";
		}else if (apiType.equals("ingredient")) {
			return "icrIngredient/";
		}else {
			return "icr/";
		}
	}
	/**
	 * 获取2位小数
	 * @param num
	 * @return num
	 */
	private String getPercent(double num) {  
        DecimalFormat dFormat=new DecimalFormat("#.00");  
        String yearString=dFormat.format(num);  
        Double temp= Double.valueOf(yearString);  
        return temp+"%";  
   } 
}
