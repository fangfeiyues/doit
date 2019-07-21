package com.fang.doit.spring.mybatis;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author created by fang on 2019/7/14/014 10:53
 * Statement prepare(Connection connection, Integer transactionTimeout) throws SQLException;
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class RecordSqlPlugin implements Interceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();

        MetaObject metaObject = SystemMetaObject.forObject(invocation);
        System.out.println("��ǰ���ص��Ķ���" + metaObject.getValue("target"));
        System.out.println("SQL��䣺" + metaObject.getValue("target.delegate.boundSql.sql"));
        System.out.println("SQL�����Σ�" + metaObject.getValue("target.delegate.parameterHandler.parameterObject"));
        System.out.println("SQL������ͣ�" + metaObject.getValue("target.delegate.parameterHandler.mappedStatement.sqlCommandType"));
        System.out.println("Mapper����ȫ·������" + metaObject.getValue("target.delegate.parameterHandler.mappedStatement.id"));
        return invocation.proceed();
    }

    /**
     * ΪĿ����󴴽�һ���������ʹ�� Plugin.wrap(target,this) ����
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * ��ȡ�Զ������ò���
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        String dialect = properties.getProperty("dialect");
        logger.info("mybatis intercept dialect:{}", dialect);
    }
}
