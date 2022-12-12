package com.yf.exam.aspect.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.yf.exam.modules.sys.user.dto.response.SysUserLoginDTO;
import lombok.extern.log4j.Log4j2;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.shiro.SecurityUtils;

import java.io.StringReader;
import java.sql.Connection;
import java.util.Properties;

/**
 * 查询拦截器，用于拦截处理通用的信息、如用户ID、多租户信息等；
 * 特别注意：此处继承了PaginationInterceptor分页，分页必须在拦截数据后执行，否则容易出现分页不准确，分页计数大于实际数量等问题
 * @author bool
 */
@Log4j2
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}),})
public class QueryInterceptor extends PaginationInterceptor implements Interceptor {

    /**
     * 客户ID
     */
    private static final String USER_FILTER = "{{userId}}";



    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

        //sql语句类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        // 只过滤查询的
        if (SqlCommandType.SELECT == sqlCommandType) {
            // 获得原始SQL
            String sql = statementHandler.getBoundSql().getSql();

            // 不处理
            if(!sql.contains(USER_FILTER)){
                return super.intercept(invocation);
            }
            // 处理SQL语句
            String outSql = this.parseSql(sql);
            // 设置SQL
            metaObject.setValue("delegate.boundSql.sql", outSql);
            // 再分页
            return super.intercept(invocation);
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }



    /**
     * 获取当前登录用户
     * @return
     */
    private SysUserLoginDTO getLoginUser() {

        try {
            return SecurityUtils.getSubject().getPrincipal() != null ? (SysUserLoginDTO) SecurityUtils.getSubject().getPrincipal() : null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 替换用户ID
     * @param sql
     * @return
     */
    private String processUserId(String sql) {

        // 当前用户
        SysUserLoginDTO user = this.getLoginUser();
        String userId = user.getId();
        if(StringUtils.isNotBlank(userId)){
            return sql.replace(USER_FILTER, userId);
        }
        return null;
    }

    /**
     * 处理注入用户信息
     * @param src
     * @return
     */
    private String parseSql(String src) {

        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        try {

            Select select = (Select) parserManager.parse(new StringReader(src));
            PlainSelect selectBody = (PlainSelect) select.getSelectBody();

            // 过滤客户
            String sql = selectBody.toString();

            // 过滤用户ID
            sql = this.processUserId(sql);

            // 获得SQL
            return sql;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return src;
    }


}
