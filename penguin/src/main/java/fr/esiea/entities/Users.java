package fr.esiea.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Users implements Serializable {

	@Id
	@GeneratedValue
	private int id;

	@Column(nullable = false)
	private String pseudo;
	
	@Column(nullable = false)
	private String lastname;
	
	@Column(nullable = false)
	private String firstname;

	@Column(nullable = false)
	private String mail;
	
	@Column(nullable = false)
	private String password;

	protected Users() {
		// no-args constructor required by JPA spec
		// this one is protected since it shouldn't be used directly
	}

	public Users(String pseudo, String lastname, String firstname, String mail, String password) {
		this.pseudo = pseudo;
		this.lastname = lastname;
		this.firstname = firstname;
		this.mail = mail;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
