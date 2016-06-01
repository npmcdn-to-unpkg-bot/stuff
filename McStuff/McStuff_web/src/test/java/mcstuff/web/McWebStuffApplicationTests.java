package mcstuff.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class McWebStuffApplicationTests {
	
	@Autowired 
	private ApplicationContext applicationContext;

	@Test
	public void contextLoads() {
		Assert.notNull(applicationContext);
	}

}
