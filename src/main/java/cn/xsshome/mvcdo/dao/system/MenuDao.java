package cn.xsshome.mvcdo.dao.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.xsshome.mvcdo.common.Tree;
import cn.xsshome.mvcdo.pojo.system.MenuDO;

@Mapper
public interface MenuDao {
	public List<Tree<MenuDO>> listMenuTree(@Param("id")Long id);
	List<MenuDO> listMenuByUserId(@Param("id")Long id);
	MenuDO get(Long menuId);
	List<MenuDO> list(Map<String,Object> map);
	int count(Map<String,Object> map);
	int save(MenuDO menu);
	int update(MenuDO menu);
	int remove(Long menuId);
	int batchRemove(Long[] menuIds);
}
