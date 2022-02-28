package com.gaian.poc.config;

import static java.sql.DriverManager.getConnection;

import com.alibaba.druid.pool.DruidDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Data
@Configuration
@ConfigurationProperties("postgres")
public class PostgresConfig {

    private String url = "jdbc:postgresql://10.0.0.213:5432/targettingframework?prepareThreshold=0";
    private String username;
    private String password;
    private String driverClass;
    private int minIdle;
    private int maxIdle;
    private int maxActive;
    private int maxWait;
    private boolean autoCommit;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean testWhileIdle;
    private boolean keepAlive;
    private int initialConnectionCount;
    private int connectionRetryAttempts;
    private long validationInterval;
    private String validationQuery;
    private int validationQueryTimeout;
    private int timeBetweenEviction;

    public PostgresConfig() {
        setDefaultSettings();
    }

    private void setDefaultSettings() {
        url = "jdbc:postgresql://10.0.0.213:5432/targettingframework?prepareThreshold=0";
        username = "postgres";
        password = "postgres";
//        keepAlive = true;
        autoCommit = true;
//        testOnBorrow = false;
//        testWhileIdle = true;
//        testOnReturn = false;
        minIdle = 8;
        maxIdle = 150;
        maxActive = 1;
        maxWait = 20000;
        initialConnectionCount = 1;
//        timeBetweenEviction = 60000;
//        validationInterval = 180000;
//        validationQueryTimeout = 1;
//        connectionRetryAttempts = 3;
//        validationQuery = "SELECT 1";
        driverClass = "org.postgresql.Driver";
    }

    @Bean(name = "postgresSqlConnection")
    public Connection getPostgresConnection() throws SQLException {
        return getConnection(url, username, password);
    }

    @Bean(name = "postgreSqlJdbcTemplate")
    public JdbcTemplate postgreSqlJdbcTemplate() {
        return new JdbcTemplate(getPostgresDruidDataSource());
    }

    /**
     * Connection pool using Tomcat data source for postgres
     *
     * @return data source
     */
    public org.apache.tomcat.jdbc.pool.DataSource getPostgresTomcatDataSource() {

        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClass);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.getPoolProperties().setInitialSize(initialConnectionCount);
        dataSource.getPoolProperties().setMaxActive(maxActive);
        dataSource.getPoolProperties().setMinIdle(minIdle);
        dataSource.getPoolProperties().setMaxIdle(maxIdle);
        dataSource.getPoolProperties().setMaxWait(maxWait);
        dataSource.getPoolProperties().setDefaultAutoCommit(autoCommit);
        dataSource.getPoolProperties().setTestOnBorrow(testOnBorrow);
        dataSource.getPoolProperties().setTestWhileIdle(testWhileIdle);
        dataSource.getPoolProperties().setValidationInterval(validationInterval);
        dataSource.getPoolProperties().setValidationQuery(validationQuery);
        dataSource.getPoolProperties().setTimeBetweenEvictionRunsMillis(timeBetweenEviction);
        return dataSource;
    }

    /**
     * Connection pool using Druid data source
     *
     * @return data source
     */
    public DataSource getPostgresDruidDataSource() {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClass);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDefaultAutoCommit(autoCommit);
        dataSource.setMaxActive(maxActive);
//        dataSource.setTestOnBorrow(testOnBorrow);
//        dataSource.setTestOnReturn(testOnReturn);
//        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setKeepAlive(keepAlive);
        dataSource.setKillWhenSocketReadTimeout(true);
        dataSource.setMaxWait(maxWait);
//        dataSource.setValidationQuery(validationQuery);
//        dataSource.setValidationQueryTimeout(validationQueryTimeout);
        dataSource.setInitialSize(initialConnectionCount);
//        dataSource.setConnectionErrorRetryAttempts(connectionRetryAttempts);
//        dataSource.setPoolPreparedStatements(true);
//        dataSource.setUseOracleImplicitCache(true);

        String properties = dataSource.getConnectProperties().entrySet().parallelStream()
            .map(entry -> entry.getKey() + " : " + entry.getValue())
            .collect(Collectors.joining(" , "));
        System.out.println(url);
        System.out.println(maxActive);
        System.out.println(properties);
        System.out.println("Postgres druid connection pool properties : " +
            properties);

        return dataSource;
    }
}
