package test;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.ymw.dao.ProductCategoryDao;
import com.ymw.dao.ProductDao;
import com.ymw.dao.UserDao;
import com.ymw.model.Easybuy_product;
import com.ymw.model.Easybuy_product_category;
import com.ymw.model.Easybuy_user;

public class Test1 {

	@Test
	public void test() {
		UserDao dao = new UserDao();
		Easybuy_user user;
		for (int i = 1; i < 50; i++) {
			user = new Easybuy_user();
			user.setEu_user_name("user" + i);
			user.setEu_password("123456");
			user.setEu_name("�����û�" + i);
			user.setEu_sex(i % 2);
			user.setEu_birthday(new Date());
			user.setEu_email("email" + 1 + "@test.com");
			user.setEu_identity_code(String.format("%017dX", i));
			user.setEu_mobile(String.format("130%08d", i));
			user.setEu_status(1);
			user.setEu_address("���Եص�" + i);
			dao.add(user);
		}

		Assert.assertTrue(true);
	}

	@Test
	public void test2() {
		Easybuy_product_category epc;
		ProductCategoryDao dao = new ProductCategoryDao();
		
		for(int i=1 ; i<=10;i++ ){
			epc = new Easybuy_product_category();
			epc.setEpc_name("�����"+i);
			epc.setEpc_parent_id(0);
			dao.add(epc);
		}
		for(int i=1 ; i<=10;i++ ){
			for(int j=1;j<10;j++){
				epc = new Easybuy_product_category();
				epc.setEpc_name("���"+i+"-"+j);
				epc.setEpc_parent_id(i);
				dao.add(epc);
				
			}
		}
	}
	@Test
	public void test3(){
		int count = 0;
		ProductDao pdao = new ProductDao();
		ProductCategoryDao pcdao = new ProductCategoryDao();
		List<Easybuy_product_category> plist = pcdao.query(0);
		for (Easybuy_product_category epc : plist) {
			List<Easybuy_product_category> clist = pcdao.query(epc.getEpc_id());
			for (Easybuy_product_category cepc : clist) {
				for(int i = 0 ; i<6;i++){
					Easybuy_product product = new Easybuy_product();
					product.setEp_file_name("1454312076049.jpg");
					product.setEp_name("������Ʒ"+count++);
					product.setEpc_id(epc.getEpc_id());
					product.setEpc_child_id(cepc.getEpc_id());
					pdao.add(product);
				}
			}
		}
		
	}
}
