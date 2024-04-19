package com.app.books.repositories;

import com.app.books.models.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {
    @Query(value = "SELECT * FROM booksdb.books b " +
            "WHERE " +
            "    (b.author_id = ?1 OR ?1 IS NULL) AND " +
            "    (b.price >= ?2 OR ?2 IS NULL) AND " +
            "    (b.price <= ?3 OR ?3 IS NULL) AND " +
            "    (b.gender = ?4 OR ?4 IS NULL)\n" +
            "LIMIT 0, 1000;\n", nativeQuery = true)
    List<Books> findBooksByFilters(
            Integer authorId, Double minPrice, Double maxPrice, String gender);
}
