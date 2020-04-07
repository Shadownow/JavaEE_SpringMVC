<%--
  Created by IntelliJ IDEA.
  User: 18221
  Date: 2020/3/11
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>作业提交情况（详细）</title>
    <script src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript">
        function load(){
            var HID = window.opener.document.getElementById("HID").value;
            $.ajax({
                url: "/QueryHomeworkDetailedServlet",
                type: "POST",
                data:{HID:HID},
                cache:false,
                dataType: "json",
                success: function(data){
                    var tableData="<tr><tr align=\"center\" bgcolor=\"#7fffd4\" height=\"50\"><td>学号</td><td>姓名</td><td>作业编号</td><td>作业标题</td><td>作业内容</td><td>回答</td><td>提交时间</td></tr>"
                    //动态增加5个td,并且把data数组的五个值赋给每个td
                    for(var i=0;i<data.length;i++){
                        tableData+="<tr align=\"center\" bgcolor=\"white\" height=\"30\"><td>"+data[i].学号+"</td>"+"<td>"+data[i].姓名+"</td>"+"<td>"+data[i].作业编号+"</td><td>"+data[i].作业标题+"</td><td>"+data[i].作业内容+"</td><td>"+data[i].回答+"</td><td>"+data[i].提交时间+"</td></tr>"
                    }
                    //现在tableData已经生成好了，把他赋值给上面的tbody
                    $("#tbody1").html(tableData)
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
    <h1>作业提交详情</h1>
</div>
<table align="center" width="960" border="1" bgcolor="black" cellpadding="1" cellspacing="1">
    <tbody id="tbody1">
    </tbody>
</table>
<br>
<div align="center">
    <a href="QueryHomework.jsp">上一页</a>
</div>
</body>
</html>
