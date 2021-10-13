package eu.crystal.BlogApiMongoDB.model;

import java.sql.Timestamp;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "articles")
public class Article implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private Integer id;

    private String title;

    private String tag;

    private String author;

    private String date;

    private String imgUrl;

    private String content;

    private Timestamp dateTimestamp;

    public Article() {
    	
    }

    public Article(Integer id, String title, String tag, String author, String date, String imgUrl, String content, Timestamp dateTimestamp) {
        this.id = id;
        this.title = title;
        this.tag = tag;
        this.author = author;
        this.date = date;
        this.imgUrl = imgUrl;
        this.content = content;
        this.dateTimestamp = dateTimestamp;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDateTimestamp() {
        return dateTimestamp;
    }

    public void setDateTimestamp(Timestamp dateTimestamp) {
        this.dateTimestamp = dateTimestamp;
    }

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", tag=" + tag + ", author=" + author + ", date=" + date
				+ ", imgUrl=" + imgUrl + ", content=" + content + ", dateTimestamp=" + dateTimestamp + "]";
	}
    
}