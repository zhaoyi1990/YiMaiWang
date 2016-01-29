package test;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.ymw.dao.UserDao;
import com.ymw.model.Easybuy_user;


public class Test1 {

	@Test
	public void test() {
		UserDao dao = new UserDao();
		Easybuy_user user;
		for(int i = 1;i<50;i++){
			user = new Easybuy_user();
			user.setEu_user_name("user"+i);
			user.setEu_password("123456");
			user.setEu_name("测试用户"+i);
			user.setEu_sex(i%2);
			user.setEu_birthday(new Date());
			user.setEu_email("email"+1+"@test.com");
			user.setEu_identity_code(String.format("%017dX", i));
			user.setEu_mobile(String.format("130%08d", i));
			user.setEu_status(1);
			user.setEu_address("测试地点"+i);
			dao.add(user);
		}
		
		Assert.assertTrue(true);
	}

}
