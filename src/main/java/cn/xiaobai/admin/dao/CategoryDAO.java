/**  
 * @Title: CategoryDAO.java
 * @Description: TODO
 * @author xiaobaibhs
 * @date 2020-03-06 11:17:10 
 */ 
 package cn.xiaobai.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.xiaobai.admin.entity.Category;

/**
  * @ClassName: CategoryDAO 
  * @Description: TODO
  * @version 1.0 
  * @author xiaobaibhs
  * @date 2020-03-06 11:17:10
  */
 public interface CategoryDAO extends JpaRepository<Category, Integer> {

 }
