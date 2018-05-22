package cn.xsshome.mvcdo.pojo.ai.baidu.po;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author 小帅丶
 * @date 2018年5月21日
 * <p>Description: 文字识别身份证识别对象</p>
 */
public class BDOCRIdCardBean {
   /**
    * 日志id
    */
	private long log_id;
	/**
	 * 错误code
	 */
	private long error_code;
	/**
	 * 错误信息
	 */
	private String error_msg;
	/**
	 * 图像方向，当detect_direction=true时存在。
	 *  -1:未定义，
	 *  - 0:正向，
	 *  - 1: 逆时针90度，
	 *  - 2:逆时针180度，
	 *  - 3:逆时针270度
	 */
	private Integer direction;
    /**
     * normal-识别正常
     * reversed_side-身份证正反面颠倒
     * non_idcard-上传的图片中不包含身份证
     * blurred-身份证模糊
     * other_type_card-其他类型证照
     * over_exposure-身份证关键字段反光或过曝
     * unknown-未知状态
     */
    private String image_status;
	/**
	 * 识别结果数，表示words_result的元素个数
	 */
	private Integer words_result_num;
	/**
	 * normal-正常身份证；copy-复印件；temporary-临时身份证；screen-翻拍；unknow-其他未知情况
	 */
	private String risk_type;
	/**
	 * 识别结果数组
	 */
	private List<WordsResult> words_result;
	
    public String getRisk_type() {
		return risk_type;
	}
	public void setRisk_type(String risk_type) {
		this.risk_type = risk_type;
	}
	public long getError_code() {
		return error_code;
	}
	public void setError_code(long error_code) {
		this.error_code = error_code;
	}
	public String getError_msg() {
		return error_msg;
	}
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}
	public long getLog_id() {
		return log_id;
	}
	public void setLog_id(long log_id) {
		this.log_id = log_id;
	}
	public Integer getDirection() {
		return direction;
	}
	public void setDirection(Integer direction) {
		this.direction = direction;
	}
	public String getImage_status() {
		return image_status;
	}
	public void setImage_status(String image_status) {
		this.image_status = image_status;
	}
	public Integer getWords_result_num() {
		return words_result_num;
	}
	public void setWords_result_num(Integer words_result_num) {
		this.words_result_num = words_result_num;
	}
	public List<WordsResult> getWords_result() {
		return words_result;
	}
	public void setWords_result(List<WordsResult> words_result) {
		this.words_result = words_result;
	}
	public static class WordsResult {
        @JSONField(name="住址")
        private Address address;
        @JSONField(name="出生")
        private Birth birth;
        @JSONField(name="姓名")
        private Name name;
        @JSONField(name="公民身份号码")
        private IdCardNum idCardNum;
        @JSONField(name="性别")
        private Sex sex;
        @JSONField(name="民族")
        private Nation nation;
        @JSONField(name="签发日期")
        private IssueDate issueDate;
        @JSONField(name="签发机关")
        private Authority authority;
        @JSONField(name="失效日期")
        private ExpiryDate expiryDate;
		public Address getAddress() {
			return address;
		}
		public void setAddress(Address address) {
			this.address = address;
		}
		public Birth getBirth() {
			return birth;
		}
		public void setBirth(Birth birth) {
			this.birth = birth;
		}
		public Name getName() {
			return name;
		}
		public void setName(Name name) {
			this.name = name;
		}
		public IdCardNum getIdCardNum() {
			return idCardNum;
		}
		public void setIdCardNum(IdCardNum idCardNum) {
			this.idCardNum = idCardNum;
		}
		public Sex getSex() {
			return sex;
		}
		public void setSex(Sex sex) {
			this.sex = sex;
		}
		public Nation getNation() {
			return nation;
		}
		public void setNation(Nation nation) {
			this.nation = nation;
		}
		public IssueDate getIssueDate() {
			return issueDate;
		}
		public void setIssueDate(IssueDate issueDate) {
			this.issueDate = issueDate;
		}
		public Authority getAuthority() {
			return authority;
		}
		public void setAuthority(Authority authority) {
			this.authority = authority;
		}
		public ExpiryDate getExpiryDate() {
			return expiryDate;
		}
		public void setExpiryDate(ExpiryDate expiryDate) {
			this.expiryDate = expiryDate;
		}
    }
    public static class Nation {
        private Location location;
        private String words;
        public void setLocation(Location location) {
            this.location = location;
        }
        public Location getLocation() {
            return location;
        }
        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }
    public static class Authority {
        private Location location;
        private String words;
        public void setLocation(Location location) {
            this.location = location;
        }
        public Location getLocation() {
            return location;
        }

        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }
    public static class ExpiryDate {
        private Location location;
        private String words;
        public void setLocation(Location location) {
            this.location = location;
        }
        public Location getLocation() {
            return location;
        }

        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }
    public static class Sex {
        private Location location;
        private String words;
        public void setLocation(Location location) {
            this.location = location;
        }
        public Location getLocation() {
            return location;
        }

        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }
    public static class IdCardNum {
        private Location location;
        private String words;
        public void setLocation(Location location) {
            this.location = location;
        }
        public Location getLocation() {
            return location;
        }

        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }
    public static class Name {
        private Location location;
        private String words;
        public void setLocation(Location location) {
            this.location = location;
        }
        public Location getLocation() {
            return location;
        }

        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }
    public static class IssueDate {
        private Location location;
        private String words;
        public void setLocation(Location location) {
            this.location = location;
        }
        public Location getLocation() {
            return location;
        }

        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }
    public static class IdNum {
        private Location location;
        private String words;
        public void setLocation(Location location) {
            this.location = location;
        }
        public Location getLocation() {
            return location;
        }

        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }
    public static class Address {
        private Location location;
        private String words;
        public void setLocation(Location location) {
            this.location = location;
        }
        public Location getLocation() {
            return location;
        }

        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }
    public static class Birth {
        private Location location;
        private String words;
        public void setLocation(Location location) {
            this.location = location;
        }
        public Location getLocation() {
            return location;
        }

        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }
    public static class Location {
        private int width;
        private int top;
        private int height;
        private int left;
        public void setWidth(int width) {
            this.width = width;
        }
        public int getWidth() {
            return width;
        }

        public void setTop(int top) {
            this.top = top;
        }
        public int getTop() {
            return top;
        }

        public void setHeight(int height) {
            this.height = height;
        }
        public int getHeight() {
            return height;
        }

        public void setLeft(int left) {
            this.left = left;
        }
        public int getLeft() {
            return left;
        }
    }
}
