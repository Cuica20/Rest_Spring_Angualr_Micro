package org.joedayz.corespringtest.test.product;


import java.math.BigDecimal;
import java.util.List;

import org.joedayz.corespringtest.dao.ProductDao;
import org.joedayz.corespringtest.model.Category;
import org.joedayz.corespringtest.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:org/joedayz/corespringtest/test/system-test-config.xml"})
public class ProductDaoTests {

	@Autowired
	private ProductDao productDao;
	
	private final int ID_IPOD_TOUCH = 1;
	private Product productoNuevo;
	private boolean productoEncontrado;
	
	@Before
	public void setUp(){
		List<Category> categorias = this.productDao.getCategoriesTypesList();
		Category categoria = categorias.get(0);
		
		productoNuevo = new Product();
		productoNuevo.setCategory(categoria);
		productoNuevo.setCode("2");
		productoNuevo.setDescription("Nueva Descripcion");
		productoNuevo.setName("Nuevo Producto");
		productoNuevo.setPrice(new BigDecimal("100.00"));
		
		productoEncontrado = false;
	}
	
	@Test
	public void testGetProductsList(){		
		List<Product> list = this.productDao.getProductsList(null);
		Assert.assertNotNull(list);
	}

	@Test
	public void testIsRepeatDescription(){		
		boolean isRepeatDescription = productDao.isRepeatDescription("iPod Touch 32Gb", null);
		Assert.assertEquals(true, isRepeatDescription);
	}
	
	@Test
	public void testGetProductById(){
		Product producto = this.productDao.getProductById(ID_IPOD_TOUCH);
		Assert.assertNotNull(producto);
	}
	
	@Test
	public void testGetCategoriesTypesList(){
		List<Category> categorias = this.productDao.getCategoriesTypesList();
		Assert.assertTrue(categorias.size() > 0);
	}
	
	@Test
	public void testSaveProductAdd(){
		this.productDao.saveProduct(productoNuevo);
		productoEncontrado = this.productDao.isRepeatDescription("Nuevo Producto", null);
		Assert.assertTrue(productoEncontrado);
	}
	
	@Test
	public void testSaveProductUpdate(){
		Product producto = this.productDao.getProductById(ID_IPOD_TOUCH);
		producto.setName("NOMBRE MODIFICADO");
		
		this.productDao.saveProduct(producto);
		productoEncontrado = this.productDao.isRepeatDescription("NOMBRE MODIFICADO", null);
		
		Assert.assertTrue(productoEncontrado);
	}	
}


