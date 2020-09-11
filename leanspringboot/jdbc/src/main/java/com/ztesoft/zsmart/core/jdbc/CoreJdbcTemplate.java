//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ztesoft.zsmart.core.jdbc;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.jdbc.datasource.CoreDataSourceUtils;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public class CoreJdbcTemplate extends JdbcTemplate {
    public CoreJdbcTemplate() {
    }

    public CoreJdbcTemplate(DataSource dataSource) {
        super(dataSource);
    }

    public <T> T execute(ConnectionCallback<T> action) throws DataAccessException {
        this.initConnection();
        return super.execute(action);
    }

    public <T> T execute(StatementCallback<T> action) throws DataAccessException {
        this.initConnection();
        return super.execute(action);
    }

    public <T> T execute(PreparedStatementCreator psc, PreparedStatementCallback<T> action) throws DataAccessException {
        this.initConnection();
        return super.execute(psc, action);
    }

    public <T> T execute(CallableStatementCreator csc, CallableStatementCallback<T> action) throws DataAccessException {
        this.initConnection();
        return super.execute(csc, action);
    }

    public Integer queryForCount(String sql, Object... args) {
        List<Map<String, Object>> list = super.queryForList(sql, args);
        return list.size();
    }

    public Long queryForLong(String sql, String field, Object... args) {
        List<Map<String, Object>> lists = super.queryForList(sql, args);
        System.out.println("shanxi qipa wenti get seq:"+lists);
        if (CollectionUtils.isNotEmpty(lists)) {
            Map<String, Object> map = (Map)lists.get(0);
            if (MapUtils.isNotEmpty(map)) {
                String value = String.valueOf(map.get(field));
                if (StringUtils.isNotEmpty(value)) {
                    return Long.parseLong(value);
                }
            }
        }

        return null;
    }

    protected void initConnection() {
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            CoreDataSourceUtils.getConnection(this.getDataSource());
        }

    }
}
