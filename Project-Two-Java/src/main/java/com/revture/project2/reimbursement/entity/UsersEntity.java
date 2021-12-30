package com.revture.project2.reimbursement.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import com.revture.project2.reimbursement.service.UsersTypes;


@Entity // mandatory
@Table(name="users") // optional if the entity class name and the table name match, , preferred is specify it even if it matches
public class UsersEntity {

	// Hibernate expects us to specify the primary key column
	@Id
	// we specify the primary key auto generation strategy used in the underlying DB 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id") // optional if the variable name and the column name match, preferred is specify it even if it matches
	private  int id; 
	
	@Column(name="name")
	private  String name; 
	
	@Column(name="password")
	private  String password; 
	
	@Column(name="email")
	private String email;
	
	@Enumerated(EnumType.ORDINAL)
	//@Enumerated(EnumType.STRING)
	@Column(name="usertype_id")
	private  UsersTypes usertypeId; 
	
	@Column(name="addres")
	private String addres; 
	
	@Column(name="contact")
	private String contact;

	public UsersEntity() {
		super();
	}

	public UsersEntity(int id, @NotNull String name, @NotNull String password, @NotNull String email,
			@NotNull UsersTypes usertypeId, String addres, String contact) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.usertypeId = usertypeId;
		this.addres = addres;
		this.contact = contact;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public UsersTypes getUsertypeId() {
		return usertypeId;
	}



	public void setUsertypeId(UsersTypes usertypeId) {
		this.usertypeId = usertypeId;
	}



	public String getAddres() {
		return addres;
	}



	public void setAddres(String addres) {
		this.addres = addres;
	}



	public String getContact() {
		return contact;
	}



	public void setContact(String contact) {
		this.contact = contact;
	}



	@Override
	public String toString() {
		return "UsersEntity [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email
				+ ", usertypeId=" + usertypeId + ", addres=" + addres + ", contact=" + contact + "]";
	}




	
	
}
