package cn.xsshome.mvcdo.dao.system;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.xsshome.mvcdo.pojo.system.UserDO;

/**
 * 用户管理
 */
@Mapper
public interface UserDao {
	public UserDO retrieve(@Param("username")String userName,@Param("password")String passWord);
	public UserDO selectByUserName(@Param("username")String userName);
	UserDO get(Long userId);
	List<UserDO> list(Map<String,Object> map);
	int count(Map<String,Object> map);
	int save(UserDO user);
	int update(UserDO user);
	int updatePwd(UserDO user);
	int remove(Long userId);
	int batchRemove(Long[] userIds);
	Long[] listAllDept();
}
