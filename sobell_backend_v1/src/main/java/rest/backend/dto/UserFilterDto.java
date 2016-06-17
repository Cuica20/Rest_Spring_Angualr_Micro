package rest.backend.dto;

import rest.backend.enums.RoleEnum;

public class UserFilterDto {

	
	private RoleEnum role;

    private String name;

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
