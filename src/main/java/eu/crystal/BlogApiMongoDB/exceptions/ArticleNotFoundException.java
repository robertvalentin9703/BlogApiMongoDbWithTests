package eu.crystal.BlogApiMongoDB.exceptions;

public class ArticleNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ArticleNotFoundException(Integer articleId) {
        super("Could not find article "+articleId);
    }
	
}
