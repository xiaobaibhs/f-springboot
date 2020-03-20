/**
 * @Title: UserDao.java
 * @Description: TODO
 * @author xiaobaibhs
 * @date 2020-03-05 03:52:16
 */
package cn.xiaobai.admin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.xiaobai.admin.entity.User;

/**
 * @ClassName: UserDao
 * @Description: 这里关键的地方在于方法的名字。由于使用了 JPA，无需手动构建 SQL 语句，而只需要按照规范提供方法的名字即可实现对数据库的增删改查。 如 findByUsername，就是通过
 *               username字段查询到对应的行，并返回给 User 类。
 * @version 1.0
 * @author xiaobaibhs
 * @date 2020-03-05 15:52:16
 */
public interface UserDAO extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User getByUsernameAndPassword(String username, String password);

    // JPA 默认查询出来的就是全量的数据，如果想指定字段，就需要自定义查询语句了。自定义查询语句写在 JpaRepository 类的方法上面，
    @Query(value = "select new User(u.id,u.username,u.name,u.phone,u.email,u.enabled) from User u")
    List<User> list();
}
