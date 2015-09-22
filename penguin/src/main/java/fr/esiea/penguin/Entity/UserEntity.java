package fr.esiea.penguin.Entity;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "Users")
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Column(name="pseudo", nullable = false)
	private String pseudo;

	@Column(name="lastname", nullable = false)
	private String lastname;

	@Column(name="firstname", nullable = false)
	private String firstname;

	@Column(name="mail", nullable = false)
	private String mail;

	@Column(name="password", nullable = false)
	private String password;


	public UserEntity(String pseudo, String lastname, String firstname, String mail, String password) {
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
