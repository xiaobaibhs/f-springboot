/**
 * @Title: User.java
 * @Description: TODO
 * @author xiaobaibhs
 * @date 2020-03-05 03:02:47
 */
package cn.xiaobai.admin.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * @Entity 表示这是一个实体类
 * 
 * @Table(name=“user”) 表示对应的表名是 user 为了简化对数据库的操作，我们使用了 Java Persistence API（JPA）， 对于 @JsonIgnoreProperties({
 * “handler”,“hibernateLazyInitializer” })， 解释起来比较复杂，下面的话看不懂可以忽略： 因为是做前后端分离，而前后端数据交互用的是 json格式。 那么 Use 对象就会被转换为 json 数据。
 * 而本项目使用 jpa来做实体类的持久化，jpa默认会使用 hibernate, 在 jpa 工作过程中，就会创造代理类来继承 User ， 并添加 handler 和 hibernateLazyInitializer 这两个无须
 * json 化的属性， 所以这里需要用 JsonIgnoreProperties 把这两个属性忽略掉。
 * 
 */
/**
 * @ClassName: User
 * @Description: TODO
 * @version 1.0
 * @author xiaobaibhs
 * @date 2020-03-05 15:02:47
 */
@Entity
@Table(name = "user")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Username.
     */
    private String username;

    /**
     * Password.
     */
    private String password;

    /**
     * Salt for encoding.
     */
    private String salt;

    /**
     * Real name.
     */
    private String name;

    /**
     * Phone number.
     */
    private String phone;

    /**
     * Email address.
     */
    private String email;

    /**
     * User status.
     */
    private boolean enabled;

    /**
     * Transient property for storing role owned by current user.
     */
    @Transient
    private List<AdminRole> roles;

    // 默认构造函数
    public User() {}

    // 用于配合自定义查询的构造函数
    public User(int id,String username, String name, String phone, String email, boolean enabled) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<AdminRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AdminRole> roles) {
        this.roles = roles;
    }
}