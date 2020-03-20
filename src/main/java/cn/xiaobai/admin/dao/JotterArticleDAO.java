package cn.xiaobai.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.xiaobai.admin.entity.JotterArticle;

/**
 * 
  * @ClassName: JotterArticleDAO 
  * @Description: TODO
  * @version 1.0 
  * @author xiaobaibhs
  * @date 2020-03-12 15:15:39
 */
public interface JotterArticleDAO  extends JpaRepository<JotterArticle,Integer> {
    JotterArticle findById(int id);
}
