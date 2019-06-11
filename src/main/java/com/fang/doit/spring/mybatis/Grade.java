package com.fang.doit.spring.mybatis;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author Feiyue
 * @Description:
 * @Date: Created in 2019/6/10 10:17
 */
public class Grade {

    private Long id;

    private Long userId;

    private BigDecimal grade;

    private List<String> grades;

    private Integer type;

    private User user;

    public List<String> getGrades() {
        return grades;
    }

    public void setGrades(List<String> grades) {
        this.grades = grades;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getGrade() {
        return grade;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
