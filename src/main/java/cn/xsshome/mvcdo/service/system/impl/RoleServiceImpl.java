package cn.xsshome.mvcdo.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsshome.mvcdo.dao.system.RoleDao;
import cn.xsshome.mvcdo.dao.system.RoleMenuDao;
import cn.xsshome.mvcdo.dao.system.UserDao;
import cn.xsshome.mvcdo.dao.system.UserRoleDao;
import cn.xsshome.mvcdo.pojo.system.RoleDO;
import cn.xsshome.mvcdo.pojo.system.RoleMenuDO;
import cn.xsshome.mvcdo.service.system.RoleService;






@Service
public class RoleServiceImpl implements RoleService {

    public static final String ROLE_ALL_KEY = "\"role_all\"";

    public static final String DEMO_CACHE_NAME = "role";

    @Autowired
    RoleDao roleMapper;
    @Autowired
    RoleMenuDao roleMenuMapper;
    @Autowired
    UserDao userMapper;
    @Autowired
    UserRoleDao userRoleMapper;

    @Cacheable(value = DEMO_CACHE_NAME, key = ROLE_ALL_KEY)
    public List<RoleDO> list() {
        List<RoleDO> roles = roleMapper.list(new HashMap<String,Object>());
        return roles;
    }

    public List<RoleDO> list(Long userId) {
        List<Long> rolesIds = userRoleMapper.listRoleId(userId);
        List<RoleDO> roles = roleMapper.list(new HashMap<String,Object>());
        for (RoleDO roleDO : roles) {
            roleDO.setRoleSign("false");
            for (Long roleId : rolesIds) {
                if (Objects.equals(roleDO.getRoleId(), roleId)) {
                    roleDO.setRoleSign("true");
                    break;
                }
            }
        }
        return roles;
    }
    @CacheEvict(value = DEMO_CACHE_NAME, key = ROLE_ALL_KEY)
    @Transactional
    public int save(RoleDO role) {
        int count = roleMapper.save(role);
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getRoleId();
        List<RoleMenuDO> rms = new ArrayList<RoleMenuDO>();
        for (Long menuId : menuIds) {
            RoleMenuDO rmDo = new RoleMenuDO();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        roleMenuMapper.removeByRoleId(roleId);
        if (rms.size() > 0) {
            roleMenuMapper.batchSave(rms);
        }
        return count;
    }

    @CacheEvict(value = DEMO_CACHE_NAME, key = ROLE_ALL_KEY)
    @Transactional
    public int remove(Long id) {
        int count = roleMapper.remove(id);
        roleMenuMapper.removeByRoleId(id);
        return count;
    }

    public RoleDO get(Long id) {
        RoleDO roleDO = roleMapper.get(id);
        return roleDO;
    }

    @CacheEvict(value = DEMO_CACHE_NAME, key = ROLE_ALL_KEY)
    public int update(RoleDO role) {
        int r = roleMapper.update(role);
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getRoleId();
        roleMenuMapper.removeByRoleId(roleId);
        List<RoleMenuDO> rms = new ArrayList<RoleMenuDO>();
        for (Long menuId : menuIds) {
            RoleMenuDO rmDo = new RoleMenuDO();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        if (rms.size() > 0) {
            roleMenuMapper.batchSave(rms);
        }
        return r;
    }
    public int batchremove(Long[] ids) {
        int r = roleMapper.batchRemove(ids);
        return r;
    }

}
