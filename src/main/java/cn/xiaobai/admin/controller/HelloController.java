package cn.xiaobai.admin.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.xiaobai.admin.entity.Hello;


/**
 * @author xiaobaibhs
 * @version 创建时间：2020年2月6日 下午5:31:43 类说明
 * 1、资源控制类
 *    控制器代码很简洁，但是却做了很多事情。
      1) @RestController将该类标注为一个spring bean，并标记其中的每个方法返回一个实体对象。
                        相当于@Controller+@ResponsBody。
      2) @RequestMapping提供url和方法的映射，当接收到一个/greeting的请求时，能执行greeting()方法。
                       注：上面的例子没有指定http动作(GET, PUT, POST, DELETE)，所以会与所有的动作匹配。
                       更好的方法是使用@RequestMapping(method=GET)，这样只会接收到GET请求。
      3)@RequestParam通过指定的value绑定url中的参数。本例中，url参数是可选的，因为提供了默认值“World”。
      4)方法体创建Greeting()对象并返回。
  2、注：传统的MVC 控制器和RESTful 控制器的区别在于http响应是怎样被创建的。
                  传统的MVC 控制器会通过视图转换将Greeting对象渲染成html返回到前端。
                  而RESTful 控制器直接返回Greeting对象，通过JSON转换器变成一个JSON字符串。
 */
@RestController
public class HelloController {

    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    // logback
    private final static Logger logger = LoggerFactory.getLogger(HelloController.class);
    @RequestMapping("/hello")
    public Hello greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        logger.debug("记录debug日志");
        logger.info("访问了index方法");
        logger.error("记录了error错误日志");
        System.out.println("hahaha");
        return new Hello(counter.incrementAndGet(), String.format(TEMPLATE, name));
    }
}