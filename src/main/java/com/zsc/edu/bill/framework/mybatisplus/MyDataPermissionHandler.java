package com.zsc.edu.bill.framework.mybatisplus;

import com.zsc.edu.bill.framework.security.SecurityUtil;
import com.zsc.edu.bill.framework.security.UserDetailsImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.PlainSelect;

/**
 * @author ftz
 * 创建时间:31/1/2024 下午4:26
 * 描述: TODO
 */
@Slf4j
public class MyDataPermissionHandler {

    /**
     * 获取数据权限 SQL 片段
     *
     * @param plainSelect  查询对象
     * @param whereSegment 查询条件片段
     * @return JSqlParser 条件表达式
     */
    @SneakyThrows(Exception.class)
    public Expression getSqlSegment(PlainSelect plainSelect, String whereSegment) {
        // 待执行 SQL Where 条件表达式
        Expression where = plainSelect.getWhere();
        // 获取权限过滤相关信息 -- TODO 这里模拟拿到该操作用户的权限进行处理即可...
        UserDetailsImpl userInfo = SecurityUtil.getUserInfo();
        if (userInfo == null) {
            return where;
        }
        Table fromItem = (Table) plainSelect.getFromItem();
        // 有别名用别名，无别名用表名，防止字段冲突报错
        Alias fromItemAlias = fromItem.getAlias();
        String mainTableName = fromItemAlias == null ? fromItem.getName() : fromItemAlias.getName();

        // 数据权限SQL  --  create_by = userId
        EqualsTo selfEqualsTo = new EqualsTo();
        selfEqualsTo.setLeftExpression(new Column(mainTableName + ".create_by"));
        selfEqualsTo.setRightExpression(new LongValue(userInfo.getId()));
        return new AndExpression(where, selfEqualsTo);
    }

}
