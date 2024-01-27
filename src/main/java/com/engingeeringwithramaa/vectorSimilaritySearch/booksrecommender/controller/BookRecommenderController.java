package com.engingeeringwithramaa.vectorSimilaritySearch.booksrecommender.controller;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.engingeeringwithramaa.vectorSimilaritySearch.booksrecommender.config.AstraDBConnector;
import com.engingeeringwithramaa.vectorSimilaritySearch.booksrecommender.dto.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/promotions")
public class BookRecommenderController {
    private final AstraDBConnector astraDBConnector;
    private static final String SELECT_BOOK_BY_NAME = "SELECT * FROM book_store_vectors WHERE book_name = ?";
    private static final String SELECT_ALL_BOOKS_BY_VECTOR = "SELECT * FROM book_store_vectors ORDER BY category_vector ANN OF ? LIMIT 3;";

    public BookRecommenderController(AstraDBConnector astraDBConnector) {
        this.astraDBConnector = astraDBConnector;
    }

    @GetMapping("/books/{bookName}")
    public ResponseEntity<List<Book>> getPromotionsForBook(@PathVariable(value = "bookName") String bookName) {
        CqlSession session = astraDBConnector.getCqlSession();

        // Get original product detail
        PreparedStatement qpPrepared = session.prepare(SELECT_BOOK_BY_NAME);
        BoundStatement qpBound = qpPrepared.bind(bookName);
        ResultSet rs = session.execute(qpBound);
        Row book = rs.one();

        if (book != null) {
            // Product exists, now query by its vector to get the closest product match
            Book originalBook = mapRowToBook(book);

            PreparedStatement qvPrepared = session.prepare(SELECT_ALL_BOOKS_BY_VECTOR);
            BoundStatement qvBound = qvPrepared.bind(originalBook.vector());
            ResultSet rsV = session.execute(qvBound);
            List<Book> promoBooks = rsV.all().stream()
                    .map(this::mapRowToBook)
                    .filter(promo -> !promo.name().equalsIgnoreCase(originalBook.name()))
                    .collect(Collectors.toList());

            if (!promoBooks.isEmpty()) {
                return ResponseEntity.ok(promoBooks);
            }
        }

        return ResponseEntity.notFound().build();
    }

    private Book mapRowToBook(Row row) {
        return new Book(
                row.getString("book_name"),
                row.getString("item_type"),
                row.getBigDecimal("price"),
                row.getString("currency"),
                row.getString("category_name"),
                row.getString("language"),
                row.getString("author"),
                row.getString("publisher"),
                row.getString("format"),
                row.getString("status"),
                row.getInt("no_of_pages"),
                row.getCqlVector("category_vector")
        );
    }
}