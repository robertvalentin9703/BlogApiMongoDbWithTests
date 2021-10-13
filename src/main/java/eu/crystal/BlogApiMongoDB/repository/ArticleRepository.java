package eu.crystal.BlogApiMongoDB.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import eu.crystal.BlogApiMongoDB.model.Article;

public interface ArticleRepository extends MongoRepository<Article, Integer> {

	List<Article> findAllByAuthorLikeAndTitleLikeAndTagLikeAndContentLikeAndDateLike
			(String author, String tag, String title, String content, String date);

	@Query("{'title': ?0}")
//	@Query("SELECT a FROM Article a WHERE a.title like ?1%")
	List<Article> getArticleByTitle(String title);

    @Query("{'author': ?0}")
//	@Query("SELECT a FROM Article a WHERE a.author like ?1%")
	List<Article> getArticleByAuthor(String author);

    @Query("{'tag': ?0}")
//	@Query("SELECT a FROM Article a WHERE a.tag like ?1%")
	List<Article> getArticleByTag(String tag);

    @Query("{'author': ?0 , 'tag': ?1}")
//	@Query("SELECT a FROM Article a WHERE a.author like ?1% AND a.tag like ?2%")
	List<Article> getArticleByAuthorAndTag(String author, String tag);

}