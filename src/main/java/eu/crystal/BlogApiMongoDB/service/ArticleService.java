package eu.crystal.BlogApiMongoDB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.crystal.BlogApiMongoDB.model.Article;
import eu.crystal.BlogApiMongoDB.repository.ArticleRepository;

@Service
public class ArticleService {

	@Autowired
	ArticleRepository articleRepository;

	public List<Article> findArticles(String author, String tag, String title, String content, String date) {
		return articleRepository.findAllByAuthorLikeAndTitleLikeAndTagLikeAndContentLikeAndDateLike(author, tag, title,
				content, date);
	}

	public Article findArticleById(Integer id) {
		return articleRepository.findById(id).get();
	}

	public void deleteArticleById(Integer id) {
		articleRepository.deleteById(id);
	}

	public Article saveArticle(Article article) {
		return articleRepository.save(article);
	}

	public Article updateArticle(Integer id, Article article) {
		Article existingArticle = articleRepository.findById(id).get();

		if (article.getTitle() != null)
			existingArticle.setTitle(article.getTitle());
		if (article.getTag() != null)
			existingArticle.setTag(article.getTag());
		if (article.getAuthor() != null)
			existingArticle.setAuthor(article.getAuthor());
		if (article.getDate() != null)
			existingArticle.setDate(article.getDate());
		if (article.getDateTimestamp() != null)
			existingArticle.setDateTimestamp(article.getDateTimestamp());
		if (article.getImgUrl() != null)
			existingArticle.setImgUrl(article.getImgUrl());
		if (article.getContent() != null)
			existingArticle.setContent(article.getContent());

		return articleRepository.save(existingArticle);
	}

	public boolean existsById(Integer id) {
		return articleRepository.existsById(id);
	}
	
	public List<Article> getArticleByTitle(String title) {
		return articleRepository.getArticleByTitle(title);
	}
	
	public List<Article> getArticleByAuthor(String author) {
		return articleRepository.getArticleByAuthor(author);
	}
	
	public List<Article> getArticleByTag(String tag) {
		return articleRepository.getArticleByTag(tag);
	}
	
	public List<Article> getArticleByAuthorAndTag(String author, String tag) {
		return articleRepository.getArticleByAuthorAndTag(author, tag);
	}

}
