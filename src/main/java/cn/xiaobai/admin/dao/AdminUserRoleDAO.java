package cn.xiaobai.admin.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import cn.xiaobai.admin.entity.AdminUserRole;

import java.util.List;

/**
 * 
  * @ClassName: AdminUserRoleDAO 
  * @Description: TODO
  * @version 1.0 
  * @author xiaobaibhs
  * @date 2020-03-10 09:57:49
 */
public interface AdminUserRoleDAO extends JpaRepository<AdminUserRole,Integer> {
    List<AdminUserRole> findAllByUid(int uid);
    void deleteAllByUid(int uid);
}
