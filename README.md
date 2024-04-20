Endpoints y bodies para consumir:
Crear autor:
POST http://localhost:8020/authors
{
    "name": "Estiven"
}
Crear libros:
POST http://localhost:8020/books
{
    "title": "Libro 3",
    "description": "djesutlarjskdkd jdirkdkreidi  jdjfk",
    "price": "225000",
	  "gender": "Terror",
    "authors": {
    "author_id": 2
  }
}
Obtener todos los libros
GET http://localhost:8020/books

Obtener libros por ID:
GET http://localhost:8020/books/1

Obtener libro por filtros precio maximo, precio minimo, genero, autor:
GET 
    http://localhost:8020/books/filters?gender=comedia
    http://localhost:8020/books/filters?authorId=1 
    http://localhost:8020/books/filters?minPrice=30000 
    http://localhost:8020/books/filters?maxPrice=100000

CONSULTAS UTILES PARA LA BD MYSQL
SELECT * FROM booksdb.books;
SELECT * FROM booksdb.books b 
WHERE 
    (b.author_id = 2 OR 2 IS NULL) AND 
    (b.price >= null OR null IS NULL) AND 
    (b.price <= null OR null IS NULL) AND 
    (b.gender = null OR null IS NULL)
LIMIT 0, 1000;
esta la usé en el repositorio de books BooksRepository para acceder a ella por peticiones rest
SELECT * FROM booksdb.books b 
WHERE 
    (b.author_id = ? OR ? IS NULL) AND 
    (b.price >= ? OR ? IS NULL) AND 
    (b.price <= ? OR ? IS NULL) AND 
    (b.gender = ? OR ? IS NULL)
LIMIT 0, 1000;


