package cn.xsshome.mvcdo.interceptor;

import cn.xsshome.mvcdo.common.AIConstant;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.ocr.AipOcr;
/**
 * 加载模块对象
 * @author 小帅丶
 *
 */
public class BDFactory {
	private static AipFace aipFace;
	private static AipOcr aipOcr;
	private static AipImageClassify aipImageClassify;
	public static AipFace getAipFace(){
		if(aipFace==null){
			synchronized (AipFace.class) {
				if(aipFace==null){
					aipFace = new AipFace(AIConstant.BD_FACE_APPID, AIConstant.BD_FACE_APPKEY, AIConstant.BD_FACE_SECRETKEY);
				}
			}
		}
		return aipFace;
	}
	public static AipOcr getAipOcr(){
		if(aipOcr==null){
			synchronized (AipFace.class) {
				if(aipOcr==null){
					aipOcr = new AipOcr(AIConstant.BD_OCR_APPID, AIConstant.BD_OCR_APPKEY, AIConstant.BD_OCR_SECRETKEY);
				}
			}
		}
		return aipOcr;
	}
	public static AipImageClassify getAipImageClassify(){
		if(aipImageClassify==null){
			synchronized (AipFace.class) {
				if(aipImageClassify==null){
					aipImageClassify = new AipImageClassify(AIConstant.BD_ICR_APPID, AIConstant.BD_ICR_APPKEY, AIConstant.BD_ICR_SECRETKEY);
				}
			}
		}
		return aipImageClassify;
	}
}
