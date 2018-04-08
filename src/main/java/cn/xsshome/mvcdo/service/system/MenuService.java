package cn.xsshome.mvcdo.service.system;

import java.util.List;

import cn.xsshome.mvcdo.common.Tree;
import cn.xsshome.mvcdo.pojo.system.MenuDO;

/**
 * 菜单接口类
 * @author 小帅丶
 *
 */
public interface MenuService {
	Tree<MenuDO> getSysMenuTree(Long id);
	List<Tree<MenuDO>> listMenuTree(Long id);
	Tree<MenuDO> getTree();
	Tree<MenuDO> getTree(Long id);
	List<MenuDO> list();
	int remove(Long id);
	int save(MenuDO menu);
	int update(MenuDO menu);
	MenuDO get(Long id);
}
