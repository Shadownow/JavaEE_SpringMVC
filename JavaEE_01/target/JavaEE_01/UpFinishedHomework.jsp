<%--
  Created by IntelliJ IDEA.
  User: 18221
  Date: 2020/3/10
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>提交作业-选择姓名</title>
    <script src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript">
        function load(){
            $.ajax({
                url: "/UpFinishedHomeworkServlet",
                type: "POST",
                data:{},
                cache:false,
                dataType: "json",
                success: function(data){
                    for(var j=0;j<data.length;j++){
                        $("#1").append("<option value='"+data[j].StudentId+"'>"+data[j].StudentId+"</option>");
                    }
                },
                error:function(err){
                    alert("error");
                }
            });
        }
    </script>
    <script type="text/javascript">
        function beforeSure() {
            if(document.getElementById("1").value=="null"){
                alert("您尚未选择学号");
            }
            else{
                window.open('UpHomeworkAfterChoseName.jsp')
            }
        }
    </script>
</head>

<body onload="load();">
<div align="center">
    <h1>作业提交入口</h1>
    <select id="1" style="width: 150px; height:35px;">
        <option value ="null">请选择您的学号</option>
    </select><br><br>
    <input type="button" value="确认" style="width: 70px; height: 30px;"  onclick="beforeSure()"><br><br>
    <a href="index.jsp">上一页</a>
</div>
</body>
</html>
