package br.com.otavio.CursoSpringBoot3.vo.v2;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PersonVO2 implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String firstName;
    private String lastname;
    private String address;
    private String gender;
    
    private Date birthDay;

    public PersonVO2() {
    }

    public PersonVO2(Long id, String firstName, String lastname, String address, String gender, Date birthDay) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastname = lastname;
		this.address = address;
		this.gender = gender;
		this.birthDay = birthDay;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonVO2 personVO2 = (PersonVO2) o;

        return Objects.equals(id, personVO2.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
