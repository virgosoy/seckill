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
        <div class="panel panel-info text-center">
            <div class="panel-heading">
                <h1>${seckill.name}</h1>
            </div>
            <div class="panel-body">
                <h2 class="text-danger">
                    <!-- 显示time图标 -->
                    <span class="glyphicon glyphicon-time"></span>
                    <!-- 展示倒计时 -->
                    <span id="seckill-box"></span>
                </h2>
            </div>
        </div>
    </div>
    <!-- 按钮触发模态框 -->
    <%--<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#userPhoneModal">开始演示模态框</button>--%>
    <!-- 登录弹出层，输入电话。禁止位置关闭、键盘关闭 -->
    <div id="userPhoneModal" class="modal fade" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">
                        <span class="glyphicon glyphicon-phone"></span>秒杀电话：
                    </h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-8 col-xs-offset-2">
                            <input class="form-control" id="userPhoneKey" name="userPhone"
                                   placeholder="请填写手机号" type="text" />
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <!-- 验证信息显示 -->
                    <span id="userPhoneMessage" class="glyphicon"></span>
                    <button type="button" id="userPhoneBtn" class="btn btn-success">
                        <span class="glyphicon glyphicon-phone"></span>
                        Submit
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>
    <%@include file="/WEB-INF/jsps/commons/script.jsp" %>
    <!-- jQuery cookie操作插件 -->
    <script type="text/javascript" src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <!-- jQuery countdown倒计时插件 -->
    <script type="text/javascript" src="http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/seckill/seckill.js"></script>
    <script type="text/javascript">
        $(function(){
            seckill.detail.init({
                basePath:'${pageContext.request.contextPath}',
                seckillId:${seckill.seckillId},
                startTime:${seckill.startTime.time}, //毫秒
                endTime:${seckill.endTime.time}
            })
        })
    </script>
</body>
</html>
