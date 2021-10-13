package eu.crystal.BlogApiMongoDB.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.crystal.BlogApiMongoDB.model.Article;
import eu.crystal.BlogApiMongoDB.service.ArticleService;

@RestController
@RequestMapping("/articles")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	/**
	 * Get all articles list.
	 */
	@GetMapping("")
	public ResponseEntity<List<Article>> getAllArticles(
			@RequestParam(value = "author", required = false, defaultValue = "") String author,
			@RequestParam(value = "tag", required = false, defaultValue = "") String tag,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "content", required = false, defaultValue = "") String content,
			@RequestParam(value = "date", required = false, defaultValue = "") String date) {

		List<Article> articleList = articleService.findArticles(author, title, tag, content, date);
		if (!articleList.isEmpty())
			return new ResponseEntity<>(articleList, HttpStatus.OK);
		else
			return new ResponseEntity("No data found!.", HttpStatus.NOT_FOUND);
	}

	/**
	 * Gets articles by id.
	 *
	 * @param id
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Article> getArticleById(@PathVariable("id") Integer id) {

		Article article = articleService.findArticleById(id);
		if (articleService.existsById(id)) {
			return new ResponseEntity<>(article, HttpStatus.OK);
		} else
			return new ResponseEntity("An article with this ID does not exist.", HttpStatus.NOT_FOUND);
	}

	/**
	 * Create article article.
	 *
	 * @param article
	 */
	@PostMapping("/create")
	public ResponseEntity<String> createArticle(@RequestBody Article article) {

		if (!articleService.existsById(article.getId())) {
			articleService.saveArticle(article);
			return new ResponseEntity<>("Added successfully.", HttpStatus.CREATED);
		} else
			return new ResponseEntity<>("Can't be added ,check if article exist.", HttpStatus.BAD_REQUEST);
	}

	/**
	 * Update article response entity.
	 *
	 * @param id
	 * @param article
	 */
	@PutMapping("/{id}/update")
	public ResponseEntity<String> updateArticle(@PathVariable(value = "id") Integer id, @RequestBody Article article) {
		
		if (articleService.existsById(id)) {
			articleService.updateArticle(id, article);
			return new ResponseEntity<>("Updated successfully.", HttpStatus.OK);
		} else
			return new ResponseEntity<>("Can't be updated ,check if article exist.", HttpStatus.BAD_REQUEST);
	}

	/**
	 * Delete article map.
	 *
	 * @param id
	 */
	@DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteArticleById(@PathVariable(value = "id") Integer id) {
		
        if (articleService.existsById(id)) {
            articleService.deleteArticleById(id);
            return new ResponseEntity<>("Article successfully deleted", HttpStatus.OK);
        } else
            return new ResponseEntity<>("An article with this ID does not exist so it can not be deleted.", HttpStatus.NO_CONTENT);
    }

	/**
	 * Gets articles by title.
	 *
	 * @param title
	 * @return the articles by title
	 */
	@GetMapping("/searchArticleByTitle")
	public ResponseEntity<List<Article>> getArticleByTitle(@RequestParam(value = "title") String title) {
        
		List<Article> articleList = articleService.getArticleByTitle(title);
        if (!articleList.isEmpty())
            return new ResponseEntity<>(articleList, HttpStatus.OK);
        else
            return new ResponseEntity("No data found!.", HttpStatus.NOT_FOUND);
	}

	/**
	 * Gets articles by author.
	 *
	 * @param author
	 * @return the articles by author
	 */
	@GetMapping("/searchArticleByAuthor")
	public ResponseEntity<List<Article>> getArticleByAuthor(@RequestParam(value = "author") String author) {
        
		List<Article> articleList = articleService.getArticleByAuthor(author);
        if (!articleList.isEmpty())
            return new ResponseEntity<>(articleList, HttpStatus.OK);
        else
            return new ResponseEntity("No data found!.", HttpStatus.NOT_FOUND);
	}

	/**
	 * Gets articles by tag.
	 *
	 * @param tag
	 * @return the articles by tag
	 */
	@GetMapping("/searchArticleByTag")
	public ResponseEntity<List<Article>> getArticleByTag(@RequestParam(value = "tag") String tag) {
        
		List<Article> articleList = articleService.getArticleByTag(tag);
        if (!articleList.isEmpty())
            return new ResponseEntity<>(articleList, HttpStatus.OK);
        else
            return new ResponseEntity("No data found!.", HttpStatus.NOT_FOUND);
	}

	/**
	 * Gets articles by author & tag.
	 *
	 * @param author
	 * @param tag
	 * @return the articles by author & tag
	 */
	@GetMapping("/searchArticleByAuthorAndTag")
	public ResponseEntity<List<Article>> getArticleByAuthorAndTag(
			@RequestParam(value = "author") String author,
			@RequestParam(value = "tag") String tag) {
        
		List<Article> articleList = articleService.getArticleByAuthorAndTag(author, tag);
        if (!articleList.isEmpty())
            return new ResponseEntity<>(articleList, HttpStatus.OK);
        else
            return new ResponseEntity("No data found!.", HttpStatus.NOT_FOUND);
	}

}