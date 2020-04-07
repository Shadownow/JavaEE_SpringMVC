package jdbc;

import model.StudentHomework;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * StudentHomeworkJdbc
 *
 * @author 李浩冉
 * @date 2020-03-11
 */
//使用作业号查找 添加作业 使用人名查找
public class StudentHomeworkJdbc {

    public static List<StudentHomework> selectAll(){

        //String url = "jdbc:mysql://localhost:3306/school?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        //String driverName = "com.mysql.cj.jdbc.Driver";

        String sqlString = "SELECT * FROM s_student_homework" ;
        //System.out.println(sqlString);
//        try {
//            // 加载驱动
//            Class.forName(driverName);
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        List<StudentHomework> list = new ArrayList<>();
        try(Connection connection= DatabasePool.getHikariDataSource().getConnection()) {
            try(Statement statement = connection.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sqlString)){
                    // 获取执行结果
                    while (resultSet.next()){
                        StudentHomework sh = new StudentHomework();
                        sh.setHomeworkId(resultSet.getLong("s_student_homework.homework_id"));
                        list.add(sh);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void addStudentHomework(Long SID,Long HID,String answer){


        java.util.Date date= new Date();//创建一个时间对象，获取到当前的时间

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间显示格式

        String time = sdf.format(date);//将当前时间格式化为需要的类型

        //String url = "jdbc:mysql://localhost:3306/school?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        String sqlString = "INSERT INTO s_student_homework ( student_id, homework_id,answer,answer_time ) VALUES ( "+SID+","+HID+",\""+answer+"\",\""+time+"\");";

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

    public static List<StudentHomework> selectByHID(Long HID){

        //String url = "jdbc:mysql://localhost:3306/school?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        //String driverName = "com.mysql.cj.jdbc.Driver";

        String sqlString = "SELECT * FROM s_student_homework,s_homework,s_student "+"where homework_id="+HID+" and s_student.student_id=s_student_homework.student_id and s_homework.id=s_student_homework.homework_id;";;

//        try {
//            // 加载驱动
//            Class.forName(driverName);
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        List<StudentHomework> list = new ArrayList<>();
        try(Connection connection= DatabasePool.getHikariDataSource().getConnection()) {
            try(Statement statement = connection.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sqlString)){
                    // 获取执行结果
                    while (resultSet.next()){
                        StudentHomework sh = new StudentHomework();
                        sh.setStudentId(resultSet.getLong("s_student_homework.student_id"));
                        sh.setHomeworkId(resultSet.getLong("s_student_homework.homework_id"));
                        sh.setAnswer(resultSet.getString("s_student_homework.answer"));
                        sh.setAnswerTime(resultSet.getString("s_student_homework.answer_time"));
                        sh.setHomeworkContent(resultSet.getString("s_homework.homework_content"));
                        sh.setHomeworkTitle(resultSet.getString("s_homework.homework_title"));
                        sh.setStudentName(resultSet.getString("s_student.student_name"));
                        list.add(sh);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<StudentHomework> selectBySID(Long SID){

        //String url = "jdbc:mysql://localhost:3306/school?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        //String driverName = "com.mysql.cj.jdbc.Driver";

        String sqlString = "SELECT * FROM s_student_homework,s_homework,s_student "+"where s_student_homework.student_id="+SID+" and s_student.student_id=s_student_homework.student_id and s_homework.id=s_student_homework.homework_id;";;
        System.out.println(sqlString);
//        try {
//            // 加载驱动
//            Class.forName(driverName);
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        List<StudentHomework> list = new ArrayList<>();
        try(Connection connection= DatabasePool.getHikariDataSource().getConnection()) {
            try(Statement statement = connection.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sqlString)){
                    // 获取执行结果
                    while (resultSet.next()){
                        StudentHomework sh = new StudentHomework();
                        sh.setHomeworkId(resultSet.getLong("s_student_homework.homework_id"));
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
