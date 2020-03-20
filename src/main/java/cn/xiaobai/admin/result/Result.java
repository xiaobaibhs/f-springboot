/**
 * @Title: Result.java
 * @Description: TODO
 * @author xiaobaibhs
 * @date 2020-03-04 08:16:49
 */
package cn.xiaobai.admin.result;

/**
 * @ClassName: Result
 * @Description: Result 类是为了构造 response，主要是响应码。
 * @version 1.0
 * @author xiaobaibhs
 * @date 2020-03-04 20:16:49
 */
public class Result {

    private int code;
    private String message;
    private Object data;

    public Result(int code) {
        this.code = code;
    }

    Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
