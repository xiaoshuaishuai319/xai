package cn.xsshome.mvcdo.service.system.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsshome.mvcdo.common.Tree;
import cn.xsshome.mvcdo.dao.system.DeptDao;
import cn.xsshome.mvcdo.dao.system.UserDao;
import cn.xsshome.mvcdo.dao.system.UserRoleDao;
import cn.xsshome.mvcdo.pojo.system.DeptDO;
import cn.xsshome.mvcdo.pojo.system.UserDO;
import cn.xsshome.mvcdo.pojo.system.UserRoleDO;
import cn.xsshome.mvcdo.service.system.UserService;
import cn.xsshome.mvcdo.util.BuildTree;


/**
 * 用户管理实现类
 * @author 小帅丶
 *
 */
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao userMapper;
	@Autowired
	UserRoleDao userRoleMapper;
	@Autowired
	DeptDao deptMapper;
	/**
	 * 登录验证
	 */
	public UserDO retrieve(String userName, String passWord) {
		UserDO users = userMapper.retrieve(userName, passWord);
		return users;
	}
	/**
	 * 根据用户名称查询用户对象
	 */
	public UserDO selectByUserName(String username) {
		UserDO users = userMapper.selectByUserName(username);
		return users;
	}
	/**
	 * 根据id查询用户
	 */
	public UserDO get(Long id) {
		List<Long> roleIds = userRoleMapper.listRoleId(id);
		UserDO user = userMapper.get(id);
		user.setDeptName(deptMapper.get(user.getDeptId()).getName());
		user.setRoleIds(roleIds);
		return user;
	}
	/**
	 * 根据条件查询用户列表数据
	 */
	public List<UserDO> list(Map<String, Object> map) {
		return userMapper.list(map);
	}
	/**
	 * 根据条件统计个数
	 */
	public int count(Map<String, Object> map) {
		return userMapper.count(map);
	}
	/**
	 * 新增加用户信息
	 */
	@Transactional
	public int save(UserDO user) {
		int count = userMapper.save(user);
		Long userId = user.getUserId();
		List<Long> roles = user.getRoleIds();
		userRoleMapper.removeByUserId(userId);
		List<UserRoleDO> list = new ArrayList<UserRoleDO>();
		for (Long roleId : roles) {
			UserRoleDO ur = new UserRoleDO();
			ur.setUserId(userId);
			ur.setRoleId(roleId);
			list.add(ur);
		}
		if (list.size() > 0) {
			userRoleMapper.batchSave(list);
		}
		return count;
	}
	/**
	 * 更新用户
	 */
	public int update(UserDO user) {
		int r = userMapper.update(user);
		Long userId = user.getUserId();
		List<Long> roles = user.getRoleIds();
		userRoleMapper.removeByUserId(userId);
		List<UserRoleDO> list = new ArrayList<UserRoleDO>();
		for (Long roleId : roles) {
			UserRoleDO userRoleDO = new UserRoleDO();
			userRoleDO.setUserId(userId);
			userRoleDO.setRoleId(roleId);
			list.add(userRoleDO);
		}
		if(list.size()>0) {
			userRoleMapper.batchSave(list);
		}
		return r;
	}
	/**
	 * 删除用户根据id
	 */
	public int remove(Long userId) {
		userRoleMapper.removeByUserId(userId);
		return userMapper.remove(userId);
	}
	/**
	 * 批量删除
	 */
	public int batchremove(Long[] userIds) {
		int count = userMapper.batchRemove(userIds);
		userRoleMapper.batchRemoveByUserId(userIds);
		return count;
	}
	/**
	 * 用户退出 暂时未用
	 */
	@Deprecated
	public boolean exit(Map<String, Object> params) {
		boolean exit;
		exit = userMapper.list(params).size()>0;
		return exit;
	}
	@Deprecated
	public Set<String> listRoles(Long userId) {
		return null;
	}
	/**
	 * 重置密码
	 */
	public int resetPwd(UserDO user) {
		int r = userMapper.update(user);
		return r;
	}
	/**
	 * 部门树形结构数据拼接
	 */
	public Tree<DeptDO> getTree() {
		List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
		//部门信息
		List<DeptDO> depts = deptMapper.list(new HashMap<String, Object>());
		//查询所有部门根目录id
		Long[] pDepts = deptMapper.listParentDept();
		//查询用户表中使用的 部门id
		Long[] uDepts = userMapper.listAllDept();
		//2个查询结果合并
		Long[] allDepts = (Long[]) ArrayUtils.addAll(pDepts, uDepts);
		//根据查询的部门信息进行拼接
		for (DeptDO dept : depts) {
			//如果当前部门id不在结果集中则跳出本次循环
			if(!ArrayUtils.contains(allDepts, dept.getDeptId())){
				continue;
			}
			//拼接tree部门对象
			Tree<DeptDO> tree = new Tree<DeptDO>();
			tree.setId(dept.getDeptId().toString());
			tree.setParentId(dept.getParentId().toString());
			tree.setText(dept.getName());
			//拼接tree所需数据格式内容
			Map<String, Object> state = new HashMap<String, Object>();
			state.put("opened", true);
			state.put("mType", "dept");
			tree.setState(state);
			trees.add(tree);
		}
		//查询所有用户信息
		List<UserDO> users = userMapper.list(new HashMap<String, Object>());
		//根据每个用户信息所对应的部门id进行拼接数据
		for (UserDO user : users) {
			Tree<DeptDO> tree = new Tree<DeptDO>();
			tree.setId(user.getUserId().toString());
			tree.setParentId(user.getDeptId().toString());
			tree.setText(user.getName());
			Map<String, Object> state = new HashMap<String, Object>();
			state.put("opened", true);
			state.put("mType", "user");
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为0，根据数据库实际情况调整
		Tree<DeptDO> t = BuildTree.build(trees);
		return t;
	}
	/**
	 * 只更新用户信息
	 */
	public int updatePersonal(UserDO userDO) {
		int r = userMapper.update(userDO);
		return r;
	}
	/**
	 * 更新密码
	 */
	public int updatePwd(UserDO user) {
		int r = userMapper.updatePwd(user);
		return r;
	}

}
