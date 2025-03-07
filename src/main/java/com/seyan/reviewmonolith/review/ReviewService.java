package com.seyan.reviewmonolith.review;

import com.seyan.reviewmonolith.exception.review.ReviewNotFoundException;
import com.seyan.reviewmonolith.film.FilmService;
import com.seyan.reviewmonolith.review.dto.ReviewCreationDTO;
import com.seyan.reviewmonolith.review.dto.ReviewMapper;
import com.seyan.reviewmonolith.review.dto.ReviewUpdateDTO;
import com.seyan.reviewmonolith.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final FilmService filmService;
    private final UserService userService;

    public Review createReview(ReviewCreationDTO dto) {
        Review review = reviewMapper.mapReviewCreationDTOToReview(dto);
        return reviewRepository.save(review);
    }

    /*public Review createReview(ActivityReviewDiaryRequest request) {
        Review review = reviewMapper.mapActivityReviewDiaryRequestToReview(request);
        return reviewRepository.save(review);
    }*/

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(
                String.format("No review found with the provided ID: %s", id)
        ));
    }

    //todo add sorting by your reviews and your network reviews
    public List<Review> getAllReviewsByFilmId(Long filmId) {
        return reviewRepository.findByFilmId(filmId);
    }

    //todo get by /username/film/film-title

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }


    //todo rework since liked films are based on activity
    public Review updateReview(ReviewUpdateDTO dto) {
        Review review = reviewRepository.findById(dto.id()).orElseThrow(() -> new ReviewNotFoundException(
                String.format("Cannot update review:: No review found with the provided ID: %s", dto.id())
        ));

        /*if (dto.isLikedFilm() != review.getIsLiked()) {
            filmService.updateLikeCount(dto.filmId(), dto.isLikedFilm());
            if (dto.isLikedFilm()) {
                userService.addFilmToLiked(review.getAuthorId(), review.getFilmId());
            } else {
                userService.removeFilmFromLiked(review.getAuthorId(), review.getFilmId());
            }
        }*/

        Review mapped = reviewMapper.mapReviewUpdateDTOToReview(dto, review);
        return reviewRepository.save(mapped);
    }

    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(
                String.format("Cannot delete review:: No review found with the provided ID: %s", id)));

        /*if (review.getIsLiked()) {
            filmService.updateLikeCount(review.getFilmId(), false);
            userService.removeFilmFromLiked(review.getAuthorId(), review.getFilmId());
        }*/

        reviewRepository.deleteById(id);
    }

    public int countUserReviewsForFilm(Long userId, Long filmId) {
        return reviewRepository.countByUserIdAndFilmId(userId, filmId);
    }

    //todo finish methods?
    public List<Review> getPopularReviews(String popularity) {
        return null;
    }

    public List<Long> getFilmIdFromPopularReviews(String popularity) {
        //popularity = week month year
        //get reviews for last week by creation date, sort by film
        //select film, creationDate from reviews where creationDate < :date

        return null;
    }

    public List<Long> getFilmIdFromReviewsWithFilmRatingByUserId(Long userId) {
        return null;
    }

    public List<Long> getFilmIdBasedOnReviewDateAfter(LocalDate date) {
        return reviewRepository.findFilmIdBasedOnReviewCreationDateAfter(date);
    }

    public List<Long> getFilmIdBasedOnReviewDateBefore(LocalDate date) {
        return reviewRepository.findFilmIdBasedOnReviewCreationDateBefore(date);
    }

    public List<Long> getAllFilmIds() {
        return reviewRepository.findAllFilmIds();
    }
}
