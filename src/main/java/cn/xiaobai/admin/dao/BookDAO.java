/**
 * @Title: BookDAO.java
 * @Description: TODO
 * @author xiaobaibhs
 * @date 2020-03-06 11:13:13
 */
package cn.xiaobai.admin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.xiaobai.admin.entity.Book;
import cn.xiaobai.admin.entity.Category;

/**
 * @ClassName: BookDAO
 * @Description: TODO
 * @version 1.0
 * @author xiaobaibhs
 * @date 2020-03-06 11:13:13
 */
public interface BookDAO extends JpaRepository<Book, Integer> {
    /**
     * 
     * @MethodName: findAllByCategory
     * @Description: 这里延续之前 JPA 的写法，findAllByCategory() 之所以能实现，是因为在 Book 类中有如下注解：@ManyToOne@JoinColumn(name="cid")
     *               private Category category;
     * 
     * @author xiaobaibhs
     * @param category
     * @return List<Book>
     * @date 2020-03-06 11:15:46
     */
    List<Book> findAllByCategory(Category category);

    List<Book> findAllByTitleLikeOrAuthorLike(String keyword1, String keyword2);
}