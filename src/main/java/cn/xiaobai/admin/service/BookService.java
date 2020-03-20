/**  
 * @Title: BookService.java
 * @Description: TODO
 * @author xiaobaibhs
 * @date 2020-03-06 11:44:22 
 */ 
 package cn.xiaobai.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cn.xiaobai.admin.dao.BookDAO;
import cn.xiaobai.admin.entity.Book;
import cn.xiaobai.admin.entity.Category;

/**
  * @ClassName: BookService 
  * @Description: TODO
  * @version 1.0 
  * @author xiaobaibhs
  * @date 2020-03-06 11:44:22
  */
 @Service
 public class BookService {
     @Autowired
     BookDAO bookDAO;
     @Autowired
     CategoryService categoryService;

     public List<Book> list() {
         //Sort sort = new Sort(Sort.Direction.DESC, "id");
        
         return bookDAO.findAll( Sort.by(Sort.Order.desc("id")));
     }

     public void addOrUpdate(Book book) {
         bookDAO.save(book);
     }

     public void deleteById(int id) {
         bookDAO.deleteById(id);
     }

     public List<Book> listByCategory(int cid) {
         Category category = categoryService.get(cid);
     
         return bookDAO.findAllByCategory(category);
     }
     public List<Book> Search(String keywords) {
         return bookDAO.findAllByTitleLikeOrAuthorLike('%' + keywords + '%', '%' + keywords + '%');
     }
 }
