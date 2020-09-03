package com.lqw.business.pojo;

/**
 * @description: 角色实体诶
 * @author: linqiwei
 * @createDate: 2020/4/2
 * @version: 1.0
 */
public class Role {
    //编号
    private Integer uuid;
    //名称
    private String name;
    /**
     * @Title:  getUuid <BR>
     * @Description: please write your description <BR>
     * @return: Integer <BR>
     */
    public Integer getUuid() {
        return uuid;
    }
    /**
     * @param uuid the uuid to set
     */
    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }
    /**
     * @Title:  getName <BR>
     * @Description: please write your description <BR>
     * @return: String <BR>
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

}
