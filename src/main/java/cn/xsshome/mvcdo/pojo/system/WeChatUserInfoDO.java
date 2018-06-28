package cn.xsshome.mvcdo.pojo.system;

public class WeChatUserInfoDO {
	private Integer id;//主键id
	private String openId;//用户唯一标识
	private String nickName;//昵称
	private Integer gender;//性别
	private String language;//语言
	private String city;//城市
	private String province;//省份
	private String country;//国家
	private String avatarUrl;//头像url
	@Override
	public boolean equals(Object obj) {
		if(obj==null){
			return false;
		}
		if(this==obj){
			return true;
		}
		if(obj instanceof WeChatUserInfoDO){
			WeChatUserInfoDO infoDO = (WeChatUserInfoDO) obj;
			if(infoDO.getOpenId().equals(this.openId)&&infoDO.getNickName().equals(this.nickName)&&infoDO.getGender()==this.gender
					&&infoDO.getLanguage().equals(this.language)&&infoDO.getCity().equals(this.city)&&infoDO.getProvince().equals(this.province)
					&&infoDO.getCountry().equals(this.country)&&infoDO.getAvatarUrl().equals(this.avatarUrl)){
				return true;
			}else {
				return false;
			}
		}
		return false;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
}
