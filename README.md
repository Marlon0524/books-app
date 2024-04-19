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
