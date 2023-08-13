package br.com.otavio.CursoSpringBoot3.integrationtests.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;

public class PersonVO implements Serializable {

    private static final long serialVersionUID = 1L;
    

    private Long id;
    private String firstName;
    private String lastname;
    private String address;
    private String gender;

    public PersonVO() {
    	
    }

    public PersonVO(Long id, String firstName, String lastname, String address, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastname = lastname;
        this.address = address;
        this.gender = gender;
    }

    

    public Long getid() {
		return id;
	}

	public void setid(Long id) {
		this.id = id;
	}

	public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonVO other = (PersonVO) obj;
		return Objects.equals(id, other.id);
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id);
		return result;
	}
}
