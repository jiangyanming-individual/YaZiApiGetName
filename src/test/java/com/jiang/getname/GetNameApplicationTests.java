package com.jiang.getname;

import com.jiang.yaziapiclientsdk.client.YaZiApiClient;
import com.jiang.yaziapiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class GetNameApplicationTests {

    @Resource
    private YaZiApiClient yaZiApiClient;

    @Test
    void contextLoads() {
        String result1 = yaZiApiClient.getNameByGet("jiangyanming");
        System.out.println(result1);
        User user = new User();
        user.setUserName("yupi");
        String result2 = yaZiApiClient.getUsernameByPost(user);
        System.out.println(result2);
    }

}
