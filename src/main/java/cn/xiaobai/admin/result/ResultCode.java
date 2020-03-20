package cn.xiaobai.admin.result;

/**
 * 
  * @ClassName: ResultCode 
  * @Description: TODO
  * @version 1.0 
  * @author xiaobaibhs
  * @date 2020-03-08 14:17:23
 */
public enum ResultCode {
    SUCCESS(200),
    FAIL(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}
