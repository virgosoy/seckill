//存放主要交互逻辑的js代码

var seckill = {
    //封装秒杀相关的URL
    URL:{
        basePath:"",
        now:function(){
            return seckill.URL.basePath + '/seckill/time/now';
        },
        exposer:function (seckillId) {
            return seckill.URL.basePath + '/seckill/' + seckillId + '/exposer';
        },
        execution:function (seckillId,md5) {
            return seckill.URL.basePath + '/seckill/' + seckillId + '/' + md5 + '/execution';
        }
    },
    //数据字典
    dictionary:{
        phoneError:"手机号错误！"
    },
    //详情页秒杀逻辑
    detail:{
        //详情页初始化
        init:function(params){
            seckill.URL.basePath = params.basePath;
            //手机验证和登录，计时交互
            //规划我们的交互流程
            //在cookie中查找手机号
            var userPhone = $.cookie('userPhone');
            var seckillId = params.seckillId;
            var startTime = params.startTime;
            var endTime = params.endTime;
            //验证手机号
            if(!seckill.validatePhone(userPhone)) {
                //手机号不通过，需绑定phone
                //控制输出
                var $userPhoneModal = $('#userPhoneModal');
                //显示弹出层
                $userPhoneModal.modal({
                    show: true //显示模态框
                });
                //提交按钮
                $('#userPhoneBtn').click(function(){
                    var inputPhone = $('#userPhoneKey').val();
                    if(seckill.validatePhone(inputPhone)){
                        //电话写入cookie
                        $.cookie('userPhone',inputPhone,{
                            expires: 7, path: seckill.URL.basePath+'/seckill'
                        });
                        //刷新页面
                        window.location.reload();
                    }else{
                        $('#userPhoneMessage').hide()
                            .html('<label class="label label-danger">'+seckill.dictionary.phoneError+'</label>').show(300);
                    }
                });
            }else{
                //已经登录
                //计时交互
                $.get(seckill.URL.now(),{},function (data) {
                    if(data && data.success){
                        var nowTime = data.data; //毫秒
                        //时间判断，计时交互
                        seckill.countdown(seckillId,nowTime,startTime,endTime);
                    }else{
                        //如果失败，理论上这里不可能失败。
                        console.log(data);
                    }
                })
            }
        }
    },
    //验证手机号
    validatePhone:function(phone){
        if(phone && phone.length==11 && !isNaN(phone)){
            return true;
        }else{
            return false;
        }
    },
    //时间判断与倒计时
    countdown:function(seckillId,nowTime,startTime,endTime){ //参数均为毫秒
        var $seckillBox = $('#seckill-box');
        //时间判断
        if(nowTime > endTime){
            //秒杀结束
            $seckillBox.html('秒杀结束！');
        }else if(nowTime < startTime){
            //秒杀未开始，计时事件绑定
            var killTime = new Date(startTime) //秒杀开始时间，日期类型
            $seckillBox.countdown(killTime,function(event){
                //时间格式
                var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
                $seckillBox.html(format);
            }).on('finish.countdown',function(){ //时间完成后回调事件
                //获取秒杀地址，控制显示逻辑，执行秒杀。
                seckill.handleSeckill(seckillId,$seckillBox);
            });
        }else{
            //秒杀开始
            seckill.handleSeckill(seckillId,$seckillBox);
        }
    },
    //处理秒杀逻辑：获取秒杀地址，控制显示逻辑，执行秒杀。
    handleSeckill:function (seckillId,$dom) {
        $dom.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button> ');
        $.get(seckill.URL.exposer(seckillId),{},function (data) {
            //执行交互流程
            if(data && data.success){
                var exposer = data.data;
                if(exposer.exposed){
                    //已经暴露，开启秒杀，获取秒杀地址
                    var md5 = exposer.md5;
                    var killUrl = seckill.URL.execution(seckillId,md5); //秒杀地址
                    console.log(killUrl);

                    //绑定一次点击事件，预防重复点击
                    $('#killBtn').one('click',function () {
                        //执行秒杀请求
                        //先禁用按钮
                        $(this).addClass('disabled');
                        //发送秒杀请求执行秒杀
                        $.post(killUrl,{},function (data) {
                            console.dir(data);
                            if(data && data.success){
                                var killResult = data.data;
                                var state = killResult.state;
                                var stateId = state.state;
                                var stateInfo = state.stateInfo;
                                //显示秒杀结果
                                $dom.html('<span class="label label-success">'+stateInfo+'</span>');
                            }
                        })
                    })
                    $dom.show();
                }else {
                    console.dir(exposer);
                    //还没暴露，未开启秒杀
                    var now = exposer.now;
                    var start = exposer.start;
                    var end = exposer.end;
                    //重新计算计时逻辑
                    seckill.countdown(seckillId,now,start,end);
                }
            }else{
                console.log('data='+data);
            }
        })
    }
}