https://blog-api-with-mongo-db.herokuapp.com/articles - default url


GET                 -> for all articles             + you can add param for author, tag, title, content, date\
GET /{id}           -> one article with that id\
POST /save          -> adds new article\
PUT /{id}/update    -> update article with that id\
DELETE /{id}/delete -> deletes article with that id

Filters:\
/searchArticleByTitle\
/searchArticleByAuthor\
/searchArticleByTag\
/searchArticleByAuthorAndTag
