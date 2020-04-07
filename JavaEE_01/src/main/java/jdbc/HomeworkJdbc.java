package jdbc;

import model.Homework;

import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HomeworkJdbc {

    public static void addHomework(String title,String content){


        Date date= new Date();//创建一个时间对象，获取到当前的时间

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间显示格式

        String time = sdf.format(date);//将当前时间格式化为需要的类型

        //String url = "jdbc:mysql://localhost:3306/school?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        String sqlString = "INSERT INTO s_homework ( homework_title, homework_content,add_time ) VALUES ( \""+title+"\",\""+content+"\",\""+time+"\");";

        //String driverName = "com.mysql.cj.jdbc.Driver";
        System.out.println(sqlString);
//        try {
//            // 加载驱动
//            Class.forName(driverName);
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        try(Connection connection= DatabasePool.getHikariDataSource().getConnection()) {
            try(Statement statement = connection.createStatement()){
                statement.executeUpdate(sqlString);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }




    public static List<Homework> selectAll(){

        //String url = "jdbc:mysql://localhost:3306/school?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        //String driverName = "com.mysql.cj.jdbc.Driver";

        String sqlString = "SELECT * FROM s_homework";

//        try {
//            // 加载驱动
//            Class.forName(driverName);
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        List<Homework> list = new ArrayList<>();
        try(Connection connection= DatabasePool.getHikariDataSource().getConnection()) {
            try(Statement statement = connection.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sqlString)){
                    // 获取执行结果
                    while (resultSet.next()){
                        Homework sh = new Homework();
                        sh.setId(resultSet.getLong("id"));
                        sh.setHomeworkContent(resultSet.getString("homework_content"));
                        sh.setHomeworkTitle(resultSet.getString("homework_title"));
                        list.add(sh);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
