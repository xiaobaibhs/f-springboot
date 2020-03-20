package cn.xiaobai.admin.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import cn.xiaobai.admin.entity.AdminMenu;

import java.util.List;

/**
 * 
  * @ClassName: AdminMenuDAO 
  * @Description: TODO
  * @version 1.0 
  * @author xiaobaibhs
  * @date 2020-03-10 09:57:23
 */
public interface AdminMenuDAO extends JpaRepository<AdminMenu, Integer> {
     AdminMenu findById(int id);
     List<AdminMenu> findAllByParentId(int parentId);
}

