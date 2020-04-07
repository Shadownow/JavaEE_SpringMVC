<%--
  Created by IntelliJ IDEA.
  User: 18221
  Date: 2020/3/11
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>作业详情</title>
    <script src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript">
        function load(){
            var title = window.opener.document.getElementById("title").value;
            var content = window.opener.document.getElementById("content").value;
            var state = window.opener.document.getElementById("state").value;
            document.getElementById("title").value=title;
            document.getElementById("content").value=content;
            if(state=="已提交") {
                document.getElementById("submit").disabled=true;
                document.getElementById("answer").value="您已提交~";
            }
        }
    </script>
    <script type="text/javascript">
        function beforeSure(){
            var HID = window.opener.document.getElementById("HID").value;
            var SID = window.opener.document.getElementById("SID").value;
            var answer = document.getElementById("answer").value;
            $.ajax({
                url: "/FinalUpHomeworkServlet",
                type: "POST",
                data:{
                    HID:HID,
                    SID:SID,
                    answer:answer
                },
                cache:false,
                dataType: "json",
                success: function(data){
                    alert("提交成功！");
                    window.location.href="index.jsp";
                    //window.open("index.jsp");
                },
                error:function(err){
                    alert("error");
                }
            });

        }
    </script>
</head>
<body onload="load();">
<div align="center">
    <h1>答题卡</h1>
    <input type="text" name="title" id="title"  style="width:400px; height:60px;border:black 2px solid" value="" onfocus=this.blur()><br>
    <input type="text" name="content" id="content"  style="width:400px; height:180px;border:black 2px solid" value="" onfocus=this.blur()><br>
    <input type="text" name="answer" id="answer"  style="width:400px; height:180px;border:black 2px solid" value="请在此处作答"><br><br>
    <input type="button" value="提交" id="submit"style="width: 80px; height: 30px;" onclick="beforeSure()"> <br><br>
    <a href="index.jsp">返回首页</a>
</div>
</body>
</html>
