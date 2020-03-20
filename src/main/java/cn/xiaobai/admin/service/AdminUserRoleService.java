package cn.xiaobai.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xiaobai.admin.dao.AdminUserRoleDAO;
import cn.xiaobai.admin.entity.AdminRole;
import cn.xiaobai.admin.entity.AdminUserRole;

import java.util.List;

/**
 * 
  * @ClassName: AdminUserRoleService 
  * @Description: TODO
  * @version 1.0 
  * @author xiaobaibhs
  * @date 2020-03-10 10:03:17
 */
@Service
public class AdminUserRoleService {
    @Autowired
    AdminUserRoleDAO adminUserRoleDAO;

    public List<AdminUserRole> listAllByUid(int uid) {
        return adminUserRoleDAO.findAllByUid(uid);
    }
//因为我们执行了删除操作，所以需要加上 @Transactional 注释开启事务，以保证数据的一致性。（不加是会跑出异常的哈）
//    @Modifying
    @Transactional
    public void saveRoleChanges(int uid, List<AdminRole> roles) {
        adminUserRoleDAO.deleteAllByUid(uid);
        for (AdminRole role : roles) {
            AdminUserRole ur = new AdminUserRole();
            ur.setUid(uid);
            ur.setRid(role.getId());
            adminUserRoleDAO.save(ur);
        }
    }
}
