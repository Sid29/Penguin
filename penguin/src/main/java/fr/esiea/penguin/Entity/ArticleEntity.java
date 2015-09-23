package fr.esiea.penguin.Entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Articles")
public class ArticleEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Column(name="title", nullable = false)
	private String title;

	@Column(name="author", nullable = false)
	private UserEntity author;
	
	@Column(name="dateCreation", nullable = false)
	private Date dateCreation;
	
	@Column(name="dateLastUpdate", nullable = true)
	private Date dateLastUpdate;

	@Column(name="body", nullable = false)
	private String body;


	public ArticleEntity() {
	}
	
	public ArticleEntity(String title, UserEntity author, Date dateCreation, Date dateLastUpdate, String body) {
		this.title = title;
		this.author = author;
		this.dateCreation = dateCreation;
		this.dateLastUpdate = dateLastUpdate;
		this.body = body;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public UserEntity getAuthor() {
		return author;
	}

	public void setAuthor(UserEntity author) {
		this.author = author;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateLastUpdate() {
		return dateLastUpdate;
	}

	public void setDateLastUpdate(Date dateLastUpdate) {
		this.dateLastUpdate = dateLastUpdate;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
