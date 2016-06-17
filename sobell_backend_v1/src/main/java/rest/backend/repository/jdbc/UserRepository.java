package rest.backend.repository.jdbc;

public interface UserRepository {

	boolean checkCredentials(String uname, String pwd);
    boolean checkLogin(String uname, String pwd) throws Exception;
	
}
