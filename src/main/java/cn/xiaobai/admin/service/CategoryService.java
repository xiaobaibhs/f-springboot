/**
 * @Title: CategoryService.java
 * @Description: TODO
 * @author xiaobaibhs
 * @date 2020-03-06 11:18:08
 */
package cn.xiaobai.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiaobai.admin.dao.CategoryDAO;
import cn.xiaobai.admin.entity.Category;

import org.springframework.data.domain.Sort;

/**
 * @ClassName: CategoryService
 * @Description: TODO
 * @version 1.0
 * @author xiaobaibhs
 * @date 2020-03-06 11:18:08
 */
@Service
public class CategoryService {
    @Autowired
    CategoryDAO categoryDAO;

    public List<Category> list() {
        // Sort sort = new Sort(Sort.Direction.DESC,"id");
        // return categoryDAO.findAll(sort);
        return categoryDAO.findAll(Sort.by(Sort.Order.desc("id")));
    }

    public Category get(int id) {
        return categoryDAO.findById(id).orElse(null);
    }
}
