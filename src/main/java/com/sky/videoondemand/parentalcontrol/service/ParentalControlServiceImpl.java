package com.sky.videoondemand.parentalcontrol.service;

import com.sky.videoondemand.movie.service.MovieService;
import com.sky.videoondemand.movie.service.exception.TechnicalFailureException;
import com.sky.videoondemand.movie.service.exception.TitleNotFoundException;


public class ParentalControlServiceImpl implements ParentalControlService {

    private MovieService movieService;

    public ParentalControlServiceImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    public boolean canWatchTheMovie(ParentControlLevel preferredParentControlLevel,
                                    String movieId) throws TitleNotFoundException {

        if (preferredParentControlLevel == null) {
            throw new NullPointerException("Preferred Parent Control level cannot be null");
        }

        boolean canWatchTheMovie;
        try {
            String movieParentControlCategory = this.movieService.getParentalControlLevel(movieId);
            ParentControlLevel movieParentControlLevel = ParentControlLevel.resolveLevel(movieParentControlCategory);

            canWatchTheMovie = movieParentControlLevel.getContentCategoryLevel() <= preferredParentControlLevel.getContentCategoryLevel();

        } catch (TechnicalFailureException technicalFailureException) {
            canWatchTheMovie = false;
        }

        return canWatchTheMovie;
    }

}
