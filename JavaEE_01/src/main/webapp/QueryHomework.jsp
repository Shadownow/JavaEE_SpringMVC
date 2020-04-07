<%--
  Created by IntelliJ IDEA.
  User: 18221
  Date: 2020/3/10
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>作业提交情况一览</title>
    <script src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript">
        function load(){
            $.ajax({
                url: "/QueryHomeworkServlet",
                type: "POST",
                data:{},
                cache:false,
                dataType: "json",
                success: function(data){
                    var tableData="<tr><tr align=\"center\" bgcolor=\"#7fffd4\" height=\"50\"><td>作业编号</td><td>标题</td><td>已提交</td><td>详情</td></tr>"
                    //动态增加5个td,并且把data数组的五个值赋给每个td
                    for(var i=0;i<data.length;i++){
                        tableData+="<tr align=\"center\" bgcolor=\"white\" height=\"30\"><td>"+data[i].作业号+"</td>"+"<td>"+data[i].标题+"</td>"+"<td>"+data[i].已提交+"</td>"+"<td><input type=\"button\" value=\"详情\" /></td></tr>"

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
    <h1>作业提交情况一览表</h1>
</div><br>
<table align="center" width="960" border="1" bgcolor="black" cellpadding="1" cellspacing="1">
    <tbody id="tbody1">
    </tbody>
</table><br>
<div align="center">
    <a href="index.jsp">上一页</a>
</div>
<br>

<input type="hidden" value="" id="HID"></input>

<script type="text/javascript">
    $(function(){
        $("#tbody1").on("click", ":button", function(event){
            $("#HID").val($(this).closest("tr").find("td").eq(0).text());
            window.open("QueryHomeworkDetailed.jsp");
        });
    });
</script>

</body>
</html>
