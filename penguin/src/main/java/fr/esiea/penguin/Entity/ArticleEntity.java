package fr.esiea.penguin.Entity;
import java.io.Serializable;
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

	@Column(name="body", nullable = false)
	private String body;


	public ArticleEntity() {
	}
	
	public ArticleEntity(String title, UserEntity author, String body) {
		this.title = title;
		this.author = author;
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
