package br.com.otavio.CursoSpringBoot3.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","firstName","lastname","address","gender"})//ordem do json
public class PersonVO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    @JsonProperty("first_name")//nome do campo no json
    private String firstName;
    
    @JsonProperty("last_name")//nome do campo no json
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonVO personVO = (PersonVO) o;

        return Objects.equals(id, personVO.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
