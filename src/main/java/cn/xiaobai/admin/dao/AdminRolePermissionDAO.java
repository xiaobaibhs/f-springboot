package cn.xiaobai.admin.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import cn.xiaobai.admin.entity.AdminRolePermission;

import java.util.List;

/**
 * 
  * @ClassName: AdminRolePermissionDAO 
  * @Description: TODO
  * @version 1.0 
  * @author xiaobaibhs
  * @date 2020-03-10 09:57:43
 */
public interface AdminRolePermissionDAO extends JpaRepository<AdminRolePermission, Integer> {
    List<AdminRolePermission> findAllByRid(int rid);
    void deleteAllByRid(int rid);
}
