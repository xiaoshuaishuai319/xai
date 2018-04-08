package cn.xsshome.mvcdo.dao.system;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cn.xsshome.mvcdo.pojo.system.DeptDO;


/**
 * 部门管理
 */
@Mapper
public interface DeptDao {

	DeptDO get(Long deptId);
	
	List<DeptDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(DeptDO dept);
	
	int update(DeptDO dept);
	
	int remove(Long deptId);
	
	int batchRemove(Long[] deptIds);
	
	Long[] listParentDept();
	
	int getDeptUserNumber(Long deptId);
}
