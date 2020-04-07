package jdbc;

import model.Student;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * StudentJdbc
 *
 * @author 李浩冉
 * @date 2020-03-11
 */
//添加学生 查找学生
public class StudentJdbc {

    public static void addStudent(Long studentId,String studentName){


        java.util.Date date= new Date();//创建一个时间对象，获取到当前的时间

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间显示格式

        String time = sdf.format(date);//将当前时间格式化为需要的类型

        //String url = "jdbc:mysql://localhost:3306/school?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        String sqlString = "INSERT INTO s_student ( student_name, student_id,add_time ) VALUES ( \""+studentName+"\","+studentId+",\""+time+"\");";

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

    public static List<Student> selectAll(){

        //String url = "jdbc:mysql://localhost:3306/school?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        //String driverName = "com.mysql.cj.jdbc.Driver";

        String sqlString = "SELECT * FROM s_student";

//        try {
//            // 加载驱动
//            Class.forName(driverName);
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        List<Student> list = new ArrayList<>();
        try(Connection connection= DatabasePool.getHikariDataSource().getConnection()) {
            try(Statement statement = connection.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sqlString)){
                    // 获取执行结果
                    while (resultSet.next()){
                        Student sh = new Student();
                        sh.setId(resultSet.getLong("id"));
                        sh.setStudentId(resultSet.getLong("student_id"));
                        sh.setStudentName(resultSet.getString("student_name"));
                        sh.setAddTime(resultSet.getString("add_time"));
                        list.add(sh);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static Boolean queryByStudentId(Long SID){

        //String url = "jdbc:mysql://localhost:3306/school?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        //String driverName = "com.mysql.cj.jdbc.Driver";

        String sqlString = "SELECT * FROM s_student "+"where student_id="+SID+";";
        System.out.println(sqlString);
//        try {
//            // 加载驱动
//            Class.forName(driverName);
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        List<Student> list = new ArrayList<>();
        try(Connection connection= DatabasePool.getHikariDataSource().getConnection()) {
            try(Statement statement = connection.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sqlString)){
                    // 获取执行结果
                    while (resultSet.next()){
                        Student sh = new Student();
                        sh.setId(resultSet.getLong("id"));
                        sh.setStudentId(resultSet.getLong("student_id"));
                        sh.setStudentName(resultSet.getString("student_name"));
                        sh.setAddTime(resultSet.getString("add_time"));
                        list.add(sh);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.size()==0){
            return true;
        }
        else{
            return false;
        }
    }
}