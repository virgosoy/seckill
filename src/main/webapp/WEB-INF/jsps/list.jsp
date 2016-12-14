<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsps/commons/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>秒杀列表页</title>
    <%@include file="/WEB-INF/jsps/commons/head.jsp"%>
</head>
<body>

    <!-- 页面显示部分 -->
    <div class="container">
        <div class="panel panel-info">
            <div class="panel-heading text-center">
                <div class="panel-title">
                    <h2>秒杀列表</h2>
                </div>
            </div>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>名称</th>
                        <th>库存</th>
                        <th>开始时间</th>
                        <th>结束时间</th>
                        <th>创建时间</th>
                        <th>详情页</th>
                    </tr>
                </thead>
                <tbody>
                  <c:forEach items="${list}" var="seckill">
                    <tr>
                        <td>${seckill.name}</td>
                        <td>${seckill.number}</td>
                        <td><fmt:formatDate value="${seckill.startTime}" pattern="YYYY-MM-dd HH:mm:ss" /></td>
                        <td><fmt:formatDate value="${seckill.endTime}" pattern="YYYY-MM-dd HH:mm:ss" /></td>
                        <td><fmt:formatDate value="${seckill.createTime}" pattern="YYYY-MM-dd HH:mm:ss" /></td>
                        <td>
                            <a class="btn btn-info" href="${pageContext.request.contextPath}/seckill/${seckill.seckillId}/detail" target="_blank" >查看详情</a>
                        </td>
                    </tr>
                  </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <%@include file="/WEB-INF/jsps/commons/script.jsp" %>

</body>
</html>
