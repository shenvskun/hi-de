package sk.advance.mybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sk.hide.config.RootConfig;
import com.sk.hide.dao.UserMapper;
import com.sk.hide.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)    
@ContextConfiguration(classes = RootConfig.class) 
//@ContextConfiguration(classes = MyWebAppInitializer.class) 
public class Learn_Mybatis {
	@Autowired
	UserMapper um;
	
	@Test
	public void test() {
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//		ctx.register(RootConfig.class);
//		ctx.refresh();
//		UserMapper um = (UserMapper)ctx.getBean("userMapper");
		User user = new User();
		user.setId(4);
		user.setName("");
		user = um.find(user);
		System.out.println(user.getName());
	}

	}

