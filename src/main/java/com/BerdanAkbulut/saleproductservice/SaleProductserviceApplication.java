package com.BerdanAkbulut.saleproductservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
public class SaleProductserviceApplication {
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ctx = SpringApplication.run(SaleProductserviceApplication.class, args);

		StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);

		//while (true) {
		//	template.convertAndSend("chat", "NEW MESSAGE");
		//	Thread.sleep(3000);
		//}
	}

}
