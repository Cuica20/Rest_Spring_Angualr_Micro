package rest.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "cod_user")
	private String coduser;

	@Column(name = "nom_usr")
	private String nomusr;

	@Column(name = "pass_usr")
	private String passusr;

	@Column(name = "nivel")
	private String nivel;

	@Column(name = "cargo")
	private String cargo;
	
	public User(){
		
	}

	public String getCoduser() {
		return coduser;
	}

	public User(String coduser, String nomusr, String passusr, String nivel, String cargo) {
		super();
		this.coduser = coduser;
		this.nomusr = nomusr;
		this.passusr = passusr;
		this.nivel = nivel;
		this.cargo = cargo;
	}

	public void setCoduser(String coduser) {
		this.coduser = coduser;
	}

	public String getNomusr() {
		return nomusr;
	}

	public void setNomusr(String nomusr) {
		this.nomusr = nomusr;
	}

	public String getPassusr() {
		return passusr;
	}

	public void setPassusr(String passusr) {
		this.passusr = passusr;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
}