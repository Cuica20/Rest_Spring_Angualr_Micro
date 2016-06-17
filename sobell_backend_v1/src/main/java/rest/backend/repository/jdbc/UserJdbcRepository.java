package rest.backend.repository.jdbc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import rest.backend.dto.UserDto;
import rest.backend.util.StringUtility;


@Repository
public class UserJdbcRepository implements UserRepository{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserJdbcRepository.class);
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	private final String LOGIN_USER_PASSW = "select \n" + " nom_usr as \"nomusr\",\n"
			+ "  pass_usr as \"passusr\",\n"
			+ "  from user " + " where nom_usr = :nomusr and pass_usr = :passusr";
	private final String ALL_USER = "select  \n" + "  cod_user as \"coduser\",\n" + "  cargo as \"cargo\",\n"
			+ "  from user";
	
//	@Autowired
//	DataSource dataSource;
	
	@Autowired
	UserJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
//	@PostConstruct
//	private void initialize(){
//		setDataSource(dataSource);
//	}

	@Transactional(readOnly = true)
	@Override
	public boolean checkCredentials(String uname, String pwd) {
		
		System.out.println("Inside checkCredentials");
		boolean result = false;
		
		if(StringUtility.isNotNull(uname) && StringUtility.isNotNull(pwd)){
			try {
				result = checkLogin(uname, pwd);
			} catch (Exception e) {
				result = false;
			}
		}else{
			result = false;
		}
		return result;
	}

	@Override
	public boolean checkLogin(String uname, String pwd) throws Exception {

		boolean isUserAvailable = false;
		
		LOGGER.info("Finding users for authentication ", uname);
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("nomusr", uname);
		queryParams.put("passusr", pwd);
		
		List<UserDto> listUsers = jdbcTemplate.query(ALL_USER, new BeanPropertyRowMapper<>(UserDto.class));
		for(UserDto xx : listUsers){
			System.out.println("syso" + xx.toString());
		}
		listUsers.forEach(System.out::println);
			
		
		List<UserDto> searchResults = jdbcTemplate.query(LOGIN_USER_PASSW, queryParams,
				new BeanPropertyRowMapper<>(UserDto.class));
		LOGGER.info("Found user {}", searchResults);
		System.out.println(Arrays.toString(searchResults.toArray()));
		if (searchResults.size() > 0) {
			return true;
		}
		
		return isUserAvailable;
		
	}

}
