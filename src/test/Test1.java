package test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.ymw.dao.UserDao;
import com.ymw.model.Easybuy_user;
import com.ymw.tools.BaseDao;
import com.ymw.tools.Pages;


public class Test1 {

	@Test
	public void test() {
		UserDao dao = new UserDao();
		Pages pages = new Pages("easybuy_user", 10);
		String s = null;
		pages.setCurrentPage(Integer.parseInt(s==null?"1":s));
		List<Easybuy_user> list = dao.query(pages);
		for (Easybuy_user user : list) {
			System.out.println(user.toString());
		}
		
		Assert.assertTrue(true);
	}

}
