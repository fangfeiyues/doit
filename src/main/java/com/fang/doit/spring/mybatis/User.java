package com.fang.doit.spring.mybatis;

import java.io.Serializable;

/**
 * @Author Feiyue
 * @Description:
 * @Date: Created in 2019/6/6 11:54
 */
public class User implements Serializable {

    private Long id;

    private String name;

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
