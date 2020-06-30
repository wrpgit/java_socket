import com.wrpxcx.mapper.UserMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Random;

/**
 * @author: wrp
 * @TODO: 测试Mapper
 * @time: 2020-05-26 09:05
 **/
public class Test {

    @org.junit.Test
    public void userMapperTest(){


        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = (UserMapper) context.getBean("userMapper");

        int res = userMapper.checkUser("123", "123");
        System.out.println(res);

    }
    @org.junit.Test
    public void t(){
        Random r = new Random();
        String res = "1";
        for(int i = 0; i < 10; i++){
            int t = r.nextInt(10);
            res += t;
        }
        System.out.println(res);

    }
}
