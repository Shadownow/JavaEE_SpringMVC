package jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
public class DatabasePool {

    private static HikariDataSource hikariDataSource;
    public static HikariDataSource getHikariDataSource(){
        if(null!=hikariDataSource){
            return hikariDataSource;
        }

        synchronized (DatabasePool.class){
            if(null==hikariDataSource){
                String url = "jdbc:mysql://localhost:3306/school?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                String driverName = "com.mysql.cj.jdbc.Driver";
                //String driverName="com.mysql.jdbc.Driver";
                HikariConfig hikariConfig=new HikariConfig();
                hikariConfig.setUsername("root");
                hikariConfig.setPassword("Lhr990405");
                hikariConfig.setDriverClassName(driverName);
                hikariConfig.setJdbcUrl(url);
                hikariDataSource=new HikariDataSource(hikariConfig);
                return hikariDataSource;
            }
        }
        return null;
    }

}
