package com.fang.doit.frame.spring.mybatis;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

/**
 * created by fang on 2019/7/13/013 14:05
 */
public class UserResultHandler implements ResultHandler {

    @Override
    public void handleResult(ResultContext resultContext) {

        resultContext.getResultObject();
    }
}
