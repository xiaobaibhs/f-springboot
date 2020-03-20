/**  
 * @Title: FunctionInfo.java
 * @Description: TODO
 * @author xiaobaibhs
 * @date 2020-03-14 07:56:19 
 */ 
 package cn.xiaobai.admin.entity;

import java.util.List;

import javax.persistence.Entity;

/**
  * @ClassName: FunctionInfo 
  * @Description: TODO
  * @version 1.0 
  * @author xiaobaibhs
  * @date 2020-03-14 19:56:19
  */
public class FunctionInfo {
  /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the visible
     */
    public Boolean getVisible() {
        return visible;
    }
    /**
     * @param visible the visible to set
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }
    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }
    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }
    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * @return the child
     */
    public List<FunctionInfo> getChild() {
        return child;
    }
    /**
     * @param child the child to set
     */
    public void setChild(List<FunctionInfo> child) {
        this.child = child;
    }
private String  name;
  private Boolean visible;
  private String  path;
  private String  url;
  private List<FunctionInfo> child;
}
