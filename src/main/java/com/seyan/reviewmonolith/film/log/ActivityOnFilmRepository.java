package com.seyan.reviewmonolith.film.log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityOnFilmRepository extends JpaRepository<ActivityOnFilm, ActivityOnFilmId> {
    /*@Query(value = "select * from activity_on_film where id.user_id = :userId", nativeQuery = true)
    List<ActivityOnFilm> findByUserId(@Param("userId") Long userId);*/
    @Query(value = "select * from activity_on_film where user_id = :userId", nativeQuery = true)
    List<ActivityOnFilm> findByUserId(@Param("userId") Long userId);

    /*@Query(value = "select avg(rating) from activity_on_film where id.film_id = :filmId and rating > 0.0", nativeQuery = true)
    Optional<Double> getFilmAvgRating(@Param("filmId") Long filmId);*/
    @Query(value = "select avg(rating) from activity_on_film where film_id = :filmId and rating > 0.0", nativeQuery = true)
    Optional<Double> getFilmAvgRating(@Param("filmId") Long filmId);

    /*@Query(value = "select * from activity_on_film where id.user_id = :userId and is_watched = true", nativeQuery = true)
    List<ActivityOnFilm> findWatchedFilmsActivities(@Param("userId") Long userId);*/
    @Query(value = "select * from activity_on_film where user_id = :userId and is_watched = true", nativeQuery = true)
    List<ActivityOnFilm> findWatchedFilmsActivities(@Param("userId") Long userId);

    @Query(value = "select * from activity_on_film where user_id = :userId and is_liked = true", nativeQuery = true)
    List<ActivityOnFilm> findLikedFilmsActivities(@Param("userId") Long userId);

    List<Long> findIdFilmIdByIdUserIdAndRatingGreaterThanEqual(Long userId, Double rating);

    @Query(value = "select film_id from activity_on_film where user_id = :userId and rating > :rating", nativeQuery = true)
    List<Long> findFilmIdByUserIdAndByRatingGreaterThan(@Param("userId") Long userId, @Param("rating") Double rating);

    @Query(value = "select * from activity_on_film where user_id = :userId and rating > :rating", nativeQuery = true)
    List<ActivityOnFilm> findActivityByUserIdAndByRatingGreaterThan(@Param("userId") Long userId, @Param("rating") Double rating);

    //countByJobIdAndToDeleteIsTrue
}
