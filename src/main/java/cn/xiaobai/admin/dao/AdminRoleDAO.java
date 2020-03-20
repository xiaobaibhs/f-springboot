package cn.xiaobai.admin.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import cn.xiaobai.admin.entity.AdminRole;

/**
 * 
  * @ClassName: AdminRoleDAO 
  * @Description: TODO
  * @version 1.0 
  * @author xiaobaibhs
  * @date 2020-03-10 09:57:31
 */
public interface AdminRoleDAO extends JpaRepository<AdminRole, Integer> {
    AdminRole findById(int id);
}
