package cn.xiaobai.admin.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import cn.xiaobai.admin.entity.AdminPermission;

/**
 * 
  * @ClassName: AdminPermissionDAO 
  * @Description: TODO
  * @version 1.0 
  * @author xiaobaibhs
  * @date 2020-03-10 09:54:03
 */
public interface AdminPermissionDAO extends JpaRepository<AdminPermission, Integer> {
    AdminPermission findById(int id);
}
