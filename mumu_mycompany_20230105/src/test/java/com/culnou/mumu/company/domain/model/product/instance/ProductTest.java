package com.culnou.mumu.company.domain.model.product.instance;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.culnou.mumu.company.domain.model.ProductClass;
import com.culnou.mumu.compnay.controller.MemberDto;
import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.ProductDto;
import com.culnou.mumu.compnay.controller.RoleDto;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {
	
	@Autowired
	private MarketProductAdapter adapter;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		/*
		//fail("Not yet implemented");
		ProductDto p = new ProductDto();
		p.setProductId("sagawaTest111");
		AssociatedProductFunction f1 = new AssociatedProductFunction();
		f1.setFunctionId("functionId111");
		f1.setProductFunctionUrl("URL111");
		f1.setProductFunctionId("ProductFunctionId111");
		p.getAssociatedProductFunctions().add(f1);
		AssociatedProductFunction f2 = new AssociatedProductFunction();
		f2.setFunctionId("functionId222");
		f2.setProductFunctionUrl("URL222");
		f2.setProductFunctionId("ProductFunctionId222");
		p.getAssociatedProductFunctions().add(f2);
		p.setProductCategoryId("ProductCategory111");
		p.setCompanyId("111");
		p.setProductClass(ProductClass.ApplicationService);
		p.setProductName("Product1");
		MessageDto message = adapter.createProduct(p);
		System.out.println("*** result " + message.getResult());
		System.out.println("*** error " + message.getErrorMessage());
		*/
	}
	/*
	@Test
	public void testFindAssignableProducts() throws Exception{
		RoleDto role1 = new RoleDto();
		role1.setId(1);
		RoleDto role2 = new RoleDto();
		role2.setId(3);
		List<RoleDto> roles = new ArrayList<>();
		roles.add(role1);
		roles.add(role2);
		List<ProductDto> members = adapter.findAssignableProducts(roles, "78");
		for(ProductDto member : members) {
			System.out.println("****************** " + member.getProductName());
		}
	}
	*/

}
