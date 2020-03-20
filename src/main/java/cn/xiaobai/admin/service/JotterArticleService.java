package cn.xiaobai.admin.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cn.xiaobai.admin.dao.JotterArticleDAO;
import cn.xiaobai.admin.entity.JotterArticle;

/**
 * 
  * @ClassName: JotterArticleService 
  * @Description: TODO
  * @version 1.0 
  * @author xiaobaibhs
  * @date 2020-03-12 15:19:37
 */
@Service
public class JotterArticleService {
    @Autowired
    JotterArticleDAO jotterArticleDAO;

    public Page list(int page, int size) {
      //  Sort sort = new Sort(Sort.Direction.DESC, "id");
        return jotterArticleDAO.findAll(PageRequest.of(page, size, Sort.by(Sort.Order.desc("id"))));
    }

    public JotterArticle findById(int id) {
        return jotterArticleDAO.findById(id);
    }

    public boolean addOrUpdate(JotterArticle article) {
        try {
            jotterArticleDAO.save(article);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public boolean delete(int id) {
        try {
            jotterArticleDAO.deleteById(id);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

}
