package fr.esiea.penguin.Entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Comments")
public class CommentEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Column(name="author", nullable = false)
	private UserEntity author;
	
	@Column(name="dateCreation", nullable = false)
	private Date dateCreation;
	
	@Column(name="dateLastUpdate", nullable = true)
	private Date dateLastUpdate;

	@Column(name="body", nullable = false)
	private String body;
	
	@Column(name="id_article", nullable = false)
	private ArticleEntity idArticle;


	public CommentEntity() {
	}
	
	public CommentEntity(UserEntity author, Date dateCreation, Date dateLastUpdate, String body, ArticleEntity idArticle) {
		this.author = author;
		this.dateCreation = dateCreation;
		this.dateLastUpdate = dateLastUpdate;
		this.body = body;
		this.idArticle = idArticle;
	}

	public int getId() {
		return id;
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
	
	public ArticleEntity getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(ArticleEntity body) {
		this.idArticle = idArticle;
	}
}
