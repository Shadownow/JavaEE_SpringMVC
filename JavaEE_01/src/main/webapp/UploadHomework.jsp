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
    <title>发布作业</title>
    <script src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript">
        function beforeSubmit(){
            var title=document.getElementById("1").value;
            var content=document.getElementById("2").value;
            $.ajax({
                url: "/UploadHomeworkServlet",
                type: "POST",
                data:{
                    title:title,
                    content:content
                },
                cache:false,
                dataType: "json",
                success: function(data){
                    if(data.result=="发布成功"){
                        alert("发布成功！")
                        window.location.href="";
                    }
                    else if(data.result=="作业名不能为空") {
                        alert("作业名不能为空！")
                        document.getElementById("1").focus();

                    }
                    else if(data.result=="作业内容不能为空") {
                        alert("作业内容不能为空！")
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
    <h1>发布作业</h1>
    <form action="UploadHomeworkServlet" method="post" >
        作业标题:<br>
        <input type="text" name="title" value="" style="width: 200px; height:35px;" id="1">
        <br>
        作业内容:<br>
        <input type="text" name="content" value="" style="width: 200px; height:35px;" id="2">
        <br><br>
        <input type="button" value="确认" style="width: 60px; height: 30px;" onclick="beforeSubmit()">&nbsp&nbsp<input type="reset" style="width: 60px; height: 30px;" value="清空">
    </form>
    <br>
    <a href="index.jsp">返回首页</a>
</div>

</body>
</html>
