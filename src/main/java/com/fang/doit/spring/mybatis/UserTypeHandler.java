package com.fang.doit.spring.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@MappedJdbcTypes(value = {JdbcType.VARCHAR})
@MappedTypes(value = {List.class})
public class UserTypeHandler extends BaseTypeHandler<List<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType)
            throws SQLException {
        StringBuffer sb = new StringBuffer();
        for (String s : parameter) {
            sb.append(s).append(",");
        }
        ps.setString(i, sb.toString().substring(0, sb.toString().length() - 1));
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String[] arr = rs.getString(columnName).split(",");
        return Arrays.asList(arr);
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String[] arr = rs.getString(columnIndex).split(",");
        return Arrays.asList(arr);
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String[] arr = cs.getString(columnIndex).split(",");
        return Arrays.asList(arr);
    }
}
