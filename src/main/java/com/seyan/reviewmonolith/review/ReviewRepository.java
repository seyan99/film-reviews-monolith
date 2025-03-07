package com.seyan.reviewmonolith.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    //int countByUserIdAndByFilmId(Long userId, Long filmId);
    int countByUserIdAndFilmId(Long userId, Long filmId);
    List<Review> findByFilmId(Long filmId);

    @Query(value = "select film_id from reviews where creation_date >= :date", nativeQuery = true)
    List<Long> findFilmIdBasedOnReviewCreationDateAfter(@Param("date") LocalDate date);

    @Query(value = "select film_id from reviews where creation_date <= :date", nativeQuery = true)
    List<Long> findFilmIdBasedOnReviewCreationDateBefore(@Param("date") LocalDate date);

    @Query(value = "select film_id from reviews", nativeQuery = true)
    List<Long> findAllFilmIds();
}
