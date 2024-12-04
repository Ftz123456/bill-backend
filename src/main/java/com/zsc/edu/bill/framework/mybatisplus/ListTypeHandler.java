package com.zsc.edu.bill.framework.mybatisplus;


import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : wshuo
 * @date : 2023/1/11 15:59
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({ArrayList.class})
public class ListTypeHandler extends BaseTypeHandler<ArrayList<String>> {

    private static final String DELIM = ",";

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, ArrayList<String> strings, JdbcType jdbcType) throws SQLException {
        String value = StringUtils.collectionToDelimitedString(strings, DELIM);
        preparedStatement.setString(i, value);
    }

    @Override
    public ArrayList<String> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String value = resultSet.getString(s);
        return new ArrayList(List.of(StringUtils.tokenizeToStringArray(value, DELIM)));
    }

    @Override
    public ArrayList<String> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String value = resultSet.getString(i);
        return new ArrayList(List.of(StringUtils.tokenizeToStringArray(value, DELIM)));
    }

    @Override
    public ArrayList<String> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String value = callableStatement.getString(i);
        return new ArrayList(List.of(StringUtils.tokenizeToStringArray(value, DELIM)));
    }
}