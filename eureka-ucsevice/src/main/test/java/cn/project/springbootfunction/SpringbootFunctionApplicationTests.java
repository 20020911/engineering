package cn.project.springbootfunction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringbootFunctionApplicationTests {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void contextLoads() {
    }
    @Test
    public void qwqe(){
        String date =simpleDateFormat.format(new Date());
        System.out.println(date);
        int s = Integer.parseInt(date);
        s = s-1;
        System.out.println(s);
        String count = "10001";
        String dd = (String)redisTemplate.opsForHash().get("shijian:"+date,date);
        if(dd==null){
            String ss =(String)redisTemplate.opsForHash().get("shijian:"+s,s);
            if(ss!=null && !ss.equals("")){
                redisTemplate.delete("shijian:"+ss);
            }
            redisTemplate.opsForHash().put("shijian:"+date,date,count);
        }else{
            System.out.println(dd);
            String ssa = dd;
            int css = Integer.parseInt(ssa)+1;
            count =String.valueOf(css);
            System.out.println(css);
            redisTemplate.opsForHash().put("shijian:"+date,date,count);
        }
    }
    @Test
    public void sasa(){
        String ka = "";
        String a = "0202020202";
        String dd = (String)redisTemplate.opsForHash().get("kahao","kahao");
        if(dd==null){

            redisTemplate.opsForHash().put("kahao","kahao",a);

        }else{
            //生成随机数
            Random random = new Random();
            String sum = random.nextInt(10)+"";
            System.out.println(sum);
            int sums = Integer.parseInt(sum);


            String ssa = dd;
            int css = Integer.parseInt(ssa)+sums;
            a =String.valueOf(css);
            System.out.println(css);
            redisTemplate.opsForHash().put("kahao","kahao",a);
        }
    }
}
