package cn.xiaobai.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.xiaobai.admin.entity.AdminRoleMenu;

import java.util.List;

/**
 * 
  * @ClassName: AdminRoleMenuDAO 
  * @Description: TODO
  * @version 1.0 
  * @author xiaobaibhs
  * @date 2020-03-10 09:57:37
 */
public interface AdminRoleMenuDAO extends JpaRepository<AdminRoleMenu,Integer> {
    List<AdminRoleMenu> findAllByRid(int rid);
    void deleteAllByRid(int rid);
}
