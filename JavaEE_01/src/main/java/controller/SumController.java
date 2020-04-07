package controller;

import jdbc.StudentJdbc;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jdbc.HomeworkJdbc;
import jdbc.StudentHomeworkJdbc;
import model.Homework;
import model.Student;
import model.StudentHomework;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SumController {

    @ResponseBody
    @RequestMapping("/UpFinishedHomeworkServlet")
    public String UpFinishedHomeworkServlet( ) {
        List<Student> students = StudentJdbc.selectAll();
        List<JSONObject> jsonList = new ArrayList<JSONObject>();

        for(int i=0 ; i<students.size(); i++){
            jsonList.add(new JSONObject());
            jsonList.get(i).put("StudentId",students.get(i).getStudentId());
        }
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(jsonList));
        System.out.println(String.valueOf(jsonList));
        System.out.println(array);
        return String.valueOf(jsonList);
    }

    @ResponseBody
    @RequestMapping("/UpHomeworkAfterChoseName")
    public String UpHomeworkAfterChoseName(@RequestBody String dataString) throws UnsupportedEncodingException {
        String data = URLDecoder.decode(dataString, "UTF-8");
        //System.out.println("test");
        //System.out.println(data);
        //JSONObject json = JSONObject.parseObject(data);
        String studentId = data.split("=")[1];
        System.out.println(studentId);
        //String studentId="123123";
        //System.out.println(studentId);
        List<Homework> homework = HomeworkJdbc.selectAll();
        List<StudentHomework>studentHomework = StudentHomeworkJdbc.selectBySID(Long.parseLong(studentId));
        List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
        System.out.println(studentHomework.size());
        //System.out.println(homework.size());
        int k = 0;
        for(int i=0;i<homework.size();i++){
            if(studentHomework.size()==0){
                jsonObjects.add(new JSONObject());
                jsonObjects.get(k).put("作业号",homework.get(i).getId());
                jsonObjects.get(k).put("标题",homework.get(i).getHomeworkTitle());
                jsonObjects.get(k).put("内容",homework.get(i).getHomeworkContent());
                jsonObjects.get(k).put("提交情况","未提交");
                k++;
            }
            else{
                for(int j=0;j<studentHomework.size();j++){
                    if(homework.get(i).getId()==studentHomework.get(j).getHomeworkId()){
                        jsonObjects.add(new JSONObject());
                        jsonObjects.get(k).put("作业号",homework.get(i).getId());
                        jsonObjects.get(k).put("标题",homework.get(i).getHomeworkTitle());
                        jsonObjects.get(k).put("内容",homework.get(i).getHomeworkContent());
                        jsonObjects.get(k).put("提交情况","已提交");
                        k++;
                        break;
                    }
                    else if(j==studentHomework.size()-1){
                        jsonObjects.add(new JSONObject());
                        jsonObjects.get(k).put("作业号",homework.get(i).getId());
                        jsonObjects.get(k).put("标题",homework.get(i).getHomeworkTitle());
                        jsonObjects.get(k).put("内容",homework.get(i).getHomeworkContent());
                        jsonObjects.get(k).put("提交情况","未提交");
                        k++;
                    }

                }
            }
        }
        return String.valueOf(jsonObjects);
    }

    @ResponseBody
    @RequestMapping("/AddStudentServlet")
    public  String AddStudentServlet(@RequestBody String dataStringo) throws UnsupportedEncodingException {
        String dataString = URLDecoder.decode(dataStringo, "UTF-8");
        String studentId;
        String studentName;
        if(dataString.split("&")[0].split("=").length==1){
            studentId="";
        }
        else {
            studentId = dataString.split("&")[0].split("=")[1];
        }

        if(dataString.split("&")[1].split("=").length==1){
            studentName="";
        }
        else {
            studentName = dataString.split("&")[1].split("=")[1];
        }

        if(studentId.equals("")){
            JSONObject data = new JSONObject();
            //获取PrintWriter输出流
            data.put("result","学号不能为空！");
            return String.valueOf(data);
        }
        else if(studentName.equals("")){
            JSONObject data = new JSONObject();
            //获取PrintWriter输出流
            data.put("result","学生姓名不能为空！");
            return String.valueOf(data);
        }
        else if(studentId.length()!=6){
            JSONObject data = new JSONObject();
            //获取PrintWriter输出流
            data.put("result","学号应为6位，请重新填写！");
            return String.valueOf(data);
        }
        else if(!StudentJdbc.queryByStudentId( Long.parseLong(studentId))){
            JSONObject data = new JSONObject();
            //获取PrintWriter输出流
            data.put("result","该学号已存在，请重新输入！");
            return String.valueOf(data);
        }
        else{
            JSONObject data = new JSONObject();
            //获取PrintWriter输出流
            data.put("result","添加成功！");
            StudentJdbc.addStudent(Long.parseLong(studentId),studentName);
            return String.valueOf(data);
        }
    }

    @ResponseBody
    @RequestMapping("/FinalUpHomeworkServlet")
    public String FinalUpHomeworkServlet(@RequestBody String dataString) throws UnsupportedEncodingException {
        String data = URLDecoder.decode(dataString, "UTF-8");
        String HID = data.split("&")[0].split("=")[1];
        String SID = data.split("&")[1].split("=")[1];
        String answer = data.split("&")[2].split("=")[1];
        StudentHomeworkJdbc.addStudentHomework(Long.parseLong(SID),Long.parseLong(HID),answer);
        JSONObject json = new JSONObject();
        json.put("boolean","true");
        return String.valueOf(json);
    }

    @ResponseBody
    @RequestMapping("/QueryHomeworkDetailedServlet")
    public String QueryHomeworkDetailedServlet(@RequestBody String data) {
        String HID = data.split("=")[1];
        List<StudentHomework> studentHomeworks = StudentHomeworkJdbc.selectByHID(Long.parseLong(HID));
        List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
        for(int i=0;i<studentHomeworks.size();i++){
            jsonObjects.add(new JSONObject());
            jsonObjects.get(i).put("学号",studentHomeworks.get(i).getStudentId());
            jsonObjects.get(i).put("姓名",studentHomeworks.get(i).getStudentName());
            jsonObjects.get(i).put("作业编号",studentHomeworks.get(i).getHomeworkId());
            jsonObjects.get(i).put("作业标题",studentHomeworks.get(i).getHomeworkTitle());
            jsonObjects.get(i).put("作业内容",studentHomeworks.get(i).getHomeworkContent());
            jsonObjects.get(i).put("回答",studentHomeworks.get(i).getAnswer());
            jsonObjects.get(i).put("提交时间",studentHomeworks.get(i).getAnswerTime());
        }
        return String.valueOf(jsonObjects);
    }

    @ResponseBody
    @RequestMapping("/QueryHomeworkServlet")
    public String QueryHomeworkServlet() {
        List<Homework> homework = HomeworkJdbc.selectAll();
        List<StudentHomework>studentHomework = StudentHomeworkJdbc.selectAll();
        int studentsum = StudentJdbc.selectAll().size();
        List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
        for(int i=0;i<homework.size();i++){
            int sum = 0;
            for(int j=0;j<studentHomework.size();j++){
                if(studentHomework.get(j).getHomeworkId()==homework.get(i).getId()){
                    sum++;
                }
            }
            jsonObjects.add(new JSONObject());
            jsonObjects.get(i).put("作业号",homework.get(i).getId());
            jsonObjects.get(i).put("标题",homework.get(i).getHomeworkTitle());
            jsonObjects.get(i).put("已提交",sum+"/"+studentsum);
        }
        return String.valueOf(jsonObjects);
    }

    @ResponseBody
    @RequestMapping("/UploadHomeworkServlet")
    public String UploadHomeworkServlet(@RequestBody String dataStringo) throws UnsupportedEncodingException {
        String dataString = URLDecoder.decode(dataStringo, "UTF-8");
        String title;
        String content;
        if(dataString.split("&")[0].split("=").length==1){
            title="";
        }
        else {
            title = dataString.split("&")[0].split("=")[1];
        }

        if(dataString.split("&")[1].split("=").length==1){
            content="";
        }
        else {
            content = dataString.split("&")[1].split("=")[1];
        }
        if(title.equals("")){
            JSONObject data = new JSONObject();
            //获取PrintWriter输出流
            data.put("result","作业名不能为空");
            return String.valueOf(data);
        }
        if(content.equals("")){
            JSONObject data = new JSONObject();
            //获取PrintWriter输出流
            data.put("result","作业内容不能为空");
            return String.valueOf(data);
        }

        HomeworkJdbc.addHomework(title,content);
        JSONObject data = new JSONObject();
        //获取PrintWriter输出流
        data.put("result","发布成功");
        return String.valueOf(data);
    }
}


