package rest.backend.dto;

public class UserDto {
	
	private String coduser;

	private String nomusr;

	private String passusr;

	private String nivel;

	private String cargo;

	public String getCoduser() {
		return coduser;
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

	@Override
	public String toString() {
			return this.coduser + " " + this.passusr;
	}
		

}
