package com.fang.doit.frame.spring.mybatis;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Feiyue
 * @Description:
 * @Date: Created in 2019/6/6 11:54
 */
public class User implements Serializable {

    public User(Long id) {
        this.id = id;
    }

    // TODO
    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Long id;

    private String name;

    private List<Grade> gradeList;

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
