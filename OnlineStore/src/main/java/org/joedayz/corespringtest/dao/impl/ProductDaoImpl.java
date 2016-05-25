package org.joedayz.corespringtest.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.joedayz.corespringtest.dao.ProductDao;
import org.joedayz.corespringtest.model.Category;
import org.joedayz.corespringtest.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {
			
	private HibernateTemplate hibernateTemplate;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public ProductDaoImpl(SessionFactory sessionFactory/*, DataSource dataSource*/) {	
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		//this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
	}

	@Transactional(readOnly=false)
	public void deleteProduct(Integer idProduct) {			
		Product product = getProductById(idProduct);
		this.hibernateTemplate.delete(product);
	}

	
	public Product getProductById(Integer idProduct) {
		Product producto = this.hibernateTemplate.get(Product.class, idProduct);
		return producto;
	}

	@SuppressWarnings("unchecked")
	public List<Product> getProductsList(Product product) {			
		
		StringBuffer query = new StringBuffer(" from Product ");
		
		if(product != null 
				&& product.getName() != null 
				&& product.getName().length() > 0){
			
			String filter = product.getName().toUpperCase();			
			query.append(" where upper(name) like '%").append(filter.toUpperCase()).append("%' ");
		}
		
		List<Product> list = this.hibernateTemplate.find(query.toString());
		return list;
	}

	@Transactional(readOnly=false)
	public void saveProduct(Product product) {	
		this.hibernateTemplate.saveOrUpdate(product);
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> getCategoriesTypesList() {
		
		StringBuffer query = new StringBuffer("from Category");
		
		List<Category> categorias = this.hibernateTemplate.find(query.toString());
		return categorias;
	}	
	
	public boolean isRepeatDescription(String name, Integer idProduct){
		
		StringBuffer query = new StringBuffer();
		query.append("select count(*) from product ");
		query.append(" where upper(trim(name)) = '").append(name.trim().toUpperCase()).append("' ");
		
		if(idProduct != null){
			query.append(" and id_product != ").append(idProduct);
		}		

		int count = -1;
		count = this.jdbcTemplate.queryForInt(query.toString());
		if(count > 0){
			return true;
		}
		return false;			
	}
}