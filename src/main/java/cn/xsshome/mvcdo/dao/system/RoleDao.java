package cn.xsshome.mvcdo.dao.system;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cn.xsshome.mvcdo.pojo.system.RoleDO;


/**
 * 角色
 * @author 小帅丶
 *
 */
@Mapper
public interface RoleDao {

	RoleDO get(Long roleId);
	
	List<RoleDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(RoleDO role);
	
	int update(RoleDO role);
	
	int remove(Long roleId);
	
	int batchRemove(Long[] roleIds);
}
