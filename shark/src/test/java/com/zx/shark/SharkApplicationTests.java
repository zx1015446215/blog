package com.zx.shark;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SharkApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Before
	public void setUp(){
//		userRepository.deleteAll();
	}

	@Test
	public void contextLoads() {
		stringRedisTemplate.opsForValue().set("student","张三");

	}

}
