package com.loongzee.service;

import com.loongzee.dto.Exposer;
import com.loongzee.dto.SeckillExecution;
import com.loongzee.entity.Seckill;
import com.loongzee.exception.RepeatKillException;
import com.loongzee.exception.SeckillCloseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
                        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    @Autowired
    private  SeckillService seckillService;

    @Test
    public void getSeckillList() {
        List<Seckill> seckills=seckillService.getSeckillList();
        System.out.println(seckills);
    }

    @Test
    public void getById() {
        long seckillId=1000;
        Seckill seckill=seckillService.getById(seckillId);
        System.out.println(seckill);
    }

    @Test  //完整逻辑代码测试，注意可重复执行
    public void testSeckillLogic() {
        long seckillId=1000;
        Exposer exposer=seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed())
        {

            System.out.println(exposer);

            long userPhone=12345678900L;
            String md5=exposer.getMd5();

            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
                System.out.println(seckillExecution);
            }catch (RepeatKillException e)
            {
                e.printStackTrace();
            }catch (SeckillCloseException e1)
            {
                e1.printStackTrace();
            }
        }else {
            //秒杀未开启
            System.out.println(exposer);
        }
    }

    @Test
    public void executeSeckill() {
        long seckillId=1000;
        long userPhone=12345678900L;
        String md5="7cca5cdb8f46cb41cb746905b8a02a88";

        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);

            System.out.println(seckillExecution);
        }catch (RepeatKillException e)
        {
            e.printStackTrace();
        }catch (SeckillCloseException e1)
        {
            e1.printStackTrace();
        }

    }
}