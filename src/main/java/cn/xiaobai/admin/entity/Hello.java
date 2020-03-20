package cn.xiaobai.admin.entity;

/**
 * @author xiaobaibhs
 * @version 创建时间：2020年2月6日 下午5:28:38 类说明
 *1、 创建资源表示类（resource representation，即RESTful中的R，简单理解就是实体）
 *2、注：spring使用Jackson JSON库将实体类的对象自动转换成JSON。
 */
public class Hello {
    private final long id;
    private final String content;

    public Hello(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

}
