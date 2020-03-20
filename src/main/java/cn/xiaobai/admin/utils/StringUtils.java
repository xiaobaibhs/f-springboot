/**  
 * @Title: StringUtils.java
 * @Description: TODO
 * @author xiaobaibhs
 * @date 2020-03-06 05:32:11 
 */ 
 package cn.xiaobai.admin.utils;

import java.util.Random;

/**
  * @ClassName: StringUtils 
  * @Description: TODO
  * @version 1.0 
  * @author xiaobaibhs
  * @date 2020-03-06 17:32:11
  */
public class StringUtils {
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
