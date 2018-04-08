package cn.xsshome.mvcdo.service.system;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.xsshome.mvcdo.pojo.system.RoleDO;



@Service
public interface RoleService {

	RoleDO get(Long id);

	List<RoleDO> list();

	int save(RoleDO role);

	int update(RoleDO role);

	int remove(Long id);

	List<RoleDO> list(Long userId);

	int batchremove(Long[] ids);
}
