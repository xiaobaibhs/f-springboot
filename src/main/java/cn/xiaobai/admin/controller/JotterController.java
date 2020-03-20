package cn.xiaobai.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import cn.xiaobai.admin.entity.JotterArticle;
import cn.xiaobai.admin.result.Result;
import cn.xiaobai.admin.result.ResultFactory;
import cn.xiaobai.admin.service.JotterArticleService;

/**
 * 
  * @ClassName: JotterController 
  * @Description: TODO
  * @version 1.0 
  * @author xiaobaibhs
  * @date 2020-03-12 15:19:27
 */
@RestController
public class JotterController {
    @Autowired
    JotterArticleService jotterArticleService;

    @PostMapping("api/admin/content/article")
    public Result saveArticle(@RequestBody JotterArticle article) {
        if(jotterArticleService.addOrUpdate(article)) {
            return ResultFactory.buildSuccessResult("保存成功");
        }
        return ResultFactory.buildFailResult("参数错误，保存失败");
    }

    @GetMapping("/api/article/{size}/{page}")
    public Page listArticles(@PathVariable("size") int size, @PathVariable("page") int page) {
        return jotterArticleService.list(page - 1, size);
    }

    @GetMapping("/api/article/{id}")
    public JotterArticle getOneArticle(@PathVariable("id") int id) {
        return jotterArticleService.findById(id);
    }

    @DeleteMapping("/api/admin/content/article/{id}")
    public Result deleteArticle(@PathVariable("id") int id) {
        if(jotterArticleService.delete(id)) {
            return ResultFactory.buildSuccessResult("删除成功");
        }
        return ResultFactory.buildFailResult("参数错误，删除失败");
    }
}
