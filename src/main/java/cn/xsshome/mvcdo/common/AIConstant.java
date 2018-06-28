package cn.xsshome.mvcdo.common;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 应用信息
 * @author 小帅丶
 *
 */
public class AIConstant {
	private static Logger logger = LoggerFactory.getLogger(AIConstant.class);
    private static Properties props;
    static{
        loadProps();
    }
    //演示系统账户
    public static String DEMO_ACCOUNT = "test";
    //自动去除表前缀
    public static String AUTO_REOMVE_PRE = "true";
    //停止计划任务
    public static String STATUS_RUNNING_STOP = "stop";
    //开启计划任务
    public static String STATUS_RUNNING_START = "start";
    //通知公告阅读状态-未读
    public static String OA_NOTIFY_READ_NO = "0";
    //通知公告阅读状态-已读
    public static int OA_NOTIFY_READ_YES = 1;
    //部门根节点id
    public static Long DEPT_ROOT_ID = 0l;
    //部门根节点名称
    public static String DEPT_ROOT_NAME = "小帅丶科技";
	//百度图像识别
	public static String BD_FACE_APPID;
	public static String BD_FACE_APPKEY;
	public static String BD_FACE_SECRETKEY;
	//百度文字识别
	public static String BD_OCR_APPID;
	public static String BD_OCR_APPKEY;
	public static String BD_OCR_SECRETKEY;
	//百度图像识别
	public static String BD_ICR_APPID;
	public static String BD_ICR_APPKEY;
	public static String BD_ICR_SECRETKEY;
	//腾讯AI
	public static String QQ_APPID;
	public static String QQ_APPKEY;
	//腾讯优图
    public static String YOUTU_AppID;  
    public static String YOUTU_SecretID;  
    public static String YOUTU_SecretKey;  
    public static String YOUTU_userQQ;  
    //接口验证码 防止别人直接访问接口
    public static String AUTH_CODE;
    //异常报警邮箱
    public static String EMAIL_ADDRESS;

	synchronized static private void loadProps(){
		logger.info("加载配置文件内容");
		props = new Properties();
		InputStream in = null;
		try {
			in = AIConstant.class.getClassLoader().getResourceAsStream("xai-constant.properties");
			props.load(in);
			BD_FACE_APPID=props.getProperty("BD_FACE_APPID");
			BD_FACE_APPKEY=props.getProperty("BD_FACE_APPKEY");
			BD_FACE_SECRETKEY=props.getProperty("BD_FACE_SECRETKEY");
			BD_OCR_APPID=props.getProperty("BD_OCR_APPID");
			BD_OCR_APPKEY=props.getProperty("BD_OCR_APPKEY");
			BD_OCR_SECRETKEY=props.getProperty("BD_OCR_SECRETKEY");
			BD_ICR_APPID=props.getProperty("BD_ICR_APPID");
			BD_ICR_APPKEY=props.getProperty("BD_ICR_APPKEY");
			BD_ICR_SECRETKEY=props.getProperty("BD_ICR_SECRETKEY");
			QQ_APPID=props.getProperty("QQ_APPID");
			QQ_APPKEY=props.getProperty("QQ_APPKEY");
			YOUTU_AppID=props.getProperty("YOUTU_AppID");
			YOUTU_SecretID=props.getProperty("YOUTU_SecretID");
			YOUTU_SecretKey=props.getProperty("YOUTU_SecretKey");
			YOUTU_userQQ=props.getProperty("YOUTU_userQQ");
			AUTH_CODE=props.getProperty("AUTH_CODE");
			EMAIL_ADDRESS=props.getProperty("EMAIL_ADDRESS");
		} catch (Exception e) {
			logger.error("加载失败xai-constant.properties "+e.getMessage());
		} finally {
		 try {
                if(null != in) {
                    in.close();
                }
            } catch (Exception e) {
                logger.error("xai-constant.properties文件流关闭出现异常"+e.getMessage());
            }
		}
	}
	
}
