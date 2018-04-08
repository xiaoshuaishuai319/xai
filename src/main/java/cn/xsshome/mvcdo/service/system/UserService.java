package cn.xsshome.mvcdo.service.system;


import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.xsshome.mvcdo.common.Tree;
import cn.xsshome.mvcdo.pojo.system.DeptDO;
import cn.xsshome.mvcdo.pojo.system.UserDO;


/**
 * 用户接口类
 * @author 小帅丶
 *
 */
public interface UserService {
	public UserDO retrieve(String username,String password);
	public UserDO selectByUserName(String username);
	UserDO get(Long id);
	List<UserDO> list(Map<String, Object> map);
	int count(Map<String, Object> map);
	int save(UserDO user);
	int update(UserDO user);
	int remove(Long userId);
	int batchremove(Long[] userIds);
	boolean exit(Map<String, Object> params);
	Set<String> listRoles(Long userId);
	int resetPwd(UserDO user);
	int updatePwd(UserDO user);
	Tree<DeptDO> getTree();
	public int updatePersonal(UserDO userDO);
}
