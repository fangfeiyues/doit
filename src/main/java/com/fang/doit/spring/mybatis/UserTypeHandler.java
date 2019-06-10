package com.fang.doit.spring.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

/**
 * @Author Feiyue
 * @Description:
 * @Date: Created in 2019/6/10 11:53
 */
public class UserTypeHandler extends BaseTypeHandler<User> {

    /**
     *
     * @param ps
     * @param i
     * @param parameter
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, User parameter, JdbcType jdbcType)
        throws SQLException {

    }

    @Override
    public User getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return null;
    }

    @Override
    public User getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public User getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
