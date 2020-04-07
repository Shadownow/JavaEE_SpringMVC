<%--
  Created by IntelliJ IDEA.
  User: 18221
  Date: 2020/3/10
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加学生</title>
    <script src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript">
        function beforeSubmit(){
            var studentId=document.getElementById("1").value;
            var studentName=document.getElementById("2").value;
            $.ajax({
                url: "/AddStudentServlet",
                type: "POST",
                data:{
                    studentId:studentId,
                    studentName:studentName
                },
                cache:false,
                dataType: "json",
                success: function(data){
                    if(data.result=="添加成功！"){
                        alert("添加成功！")
                        window.location.href="";
                    }
                    else if(data.result=="该学号已存在，请重新输入！") {
                        alert("该学号已存在，请重新输入！")
                        document.getElementById("1").focus();

                    }
                    else if(data.result=="学号应为6位，请重新填写！") {
                        alert("学号应为6位，请重新填写！")
                        document.getElementById("1").focus();
                    }
                    else if(data.result=="学号不能为空！") {
                        alert("学号不能为空！")
                        document.getElementById("1").focus();
                    }
                    else if(data.result=="学生姓名不能为空！") {
                        alert("学生姓名不能为空！")
                        document.getElementById("2").focus();
                    }
                },
                error:function(err){
                    alert("error");
                }
            });

        }
    </script>
</head>
<body>
<div align="center">
    <h1>添加学生</h1>
    <form action="AddStudentServlet" method="post" >
        学生学号:<br>
        <input type="text" name="studentId" style="width: 200px; height:35px;" value="" oninput = "value=value.replace(/[^\d]/g,'')" id="1">
        <br>
        学生姓名:<br>
        <input type="text" name="studentName" style="width: 200px; height:35px;" value="" id="2">
        <br><br>
        <input type="button" value="确认" style="width: 60px; height: 30px;" onclick="beforeSubmit()">&nbsp&nbsp<input type="reset" style="width: 60px; height: 30px;" value="清空">
    </form>
    <br>
    <a href="index.jsp">返回首页</a>
</div>
</body>
</html>
