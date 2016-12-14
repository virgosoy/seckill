package com.soy.seckill.web;

import com.soy.seckill.dto.Exposer;
import com.soy.seckill.dto.SeckillExecution;
import com.soy.seckill.dto.SeckillResult;
import com.soy.seckill.entity.Seckill;
import com.soy.seckill.enums.StateEnum;
import com.soy.seckill.exception.RepeatKillException;
import com.soy.seckill.exception.SeckillCloseException;
import com.soy.seckill.exception.SeckillException;
import com.soy.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Soy on 2016/12/12.
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    /**
     * 获取秒杀列表
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        List<Seckill> seckillList = seckillService.getSeckillList();
        model.addAttribute("list",seckillList);
        return "list";
    }


    /**
     * 详情页
     * @return
     */
    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Integer seckillId, Model model){
        if(seckillId==null){
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if(seckill==null){
            model.addAttribute("system.message","查不到指定的秒杀详情");
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill",seckill);
        return "detail";
    }

    /**
     * 系统时间
     * @return
     */
    @RequestMapping(value = "/time/now",
            method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public SeckillResult<Long> time(){
        Date date = new Date();
        return new SeckillResult<Long>(true,date.getTime());
    }

    /**
     * 暴露秒杀
     * @return
     */
    @RequestMapping(value = "/{seckillId}/exposer",
            method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Integer seckillId){
        Exposer exposer = seckillService.exposerSeckillUrl(seckillId);
        return new SeckillResult<Exposer>(true,exposer);
    }

    /**
     * 执行秒杀
     * @return
     */
    @RequestMapping(value = "/{seckillId}/{md5}/execution",
            method = RequestMethod.POST,
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public SeckillResult<SeckillExecution> execution(@PathVariable("seckillId") Integer seckillId, @PathVariable("md5") String md5,
                                                     @CookieValue(value = "userPhone", required = false)String userPhone){
        if (userPhone==null){
            return new SeckillResult<SeckillExecution>(false,"用户未登录！");
        }
        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
            return new SeckillResult<SeckillExecution>(true,seckillExecution);
        } catch (SeckillCloseException e) {
            return new SeckillResult<SeckillExecution>(true,
                    new SeckillExecution(seckillId,StateEnum.END));
        } catch (RepeatKillException e) {
            return new SeckillResult<SeckillExecution>(true,
                    new SeckillExecution(seckillId,StateEnum.REPEAT_KILL));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return new SeckillResult<SeckillExecution>(true,
                    new SeckillExecution(seckillId,StateEnum.INNER_ERROR));
        }
    }

}
