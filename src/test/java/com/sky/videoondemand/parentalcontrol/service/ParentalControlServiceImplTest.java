package com.sky.videoondemand.parentalcontrol.service;

import com.sky.videoondemand.movie.service.MovieService;
import com.sky.videoondemand.movie.service.exception.TechnicalFailureException;
import com.sky.videoondemand.movie.service.exception.TitleNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ParentalControlServiceImplTest {

    @Mock
    private MovieService movieService;

    private ParentalControlService parentalControlService;

    private String movieId = "movieId";

    @Before
    public void setUp() {
        parentalControlService = new ParentalControlServiceImpl(movieService);
    }

    @Test
    public void shouldBeAbleToWatchTheMovieWhenTheMoviesParentControlLevelIsUniversalLessThanThePreferredParentControlLevel()
            throws TitleNotFoundException, TechnicalFailureException {
        // given
        when(movieService.getParentalControlLevel(movieId)).thenReturn("U");

        // when
        boolean canWatchTheMovie = parentalControlService.canWatchTheMovie(ParentControlLevel.FIFTEEN, movieId);

        // then
        assertThat(canWatchTheMovie, is(true));
    }

    @Test
    public void shouldBeAbleToWatchTheMovieWhenTheMoviesParentControlLevelIsEqualToThePreferredParentControlLevel()
            throws TitleNotFoundException, TechnicalFailureException {
        // given
        when(movieService.getParentalControlLevel(movieId)).thenReturn("U");

        // when
        boolean canWatchTheMovie = parentalControlService.canWatchTheMovie(ParentControlLevel.UNIVERSAL, movieId);

        // then
        assertThat(canWatchTheMovie, is(true));
    }

    @Test
    public void shouldNotBeAbleToWatchTheMovieWhenTheMoviesParentControlLevelIsGreaterThanThePreferredParentControlLevel()
            throws TitleNotFoundException, TechnicalFailureException {
        // given
        when(movieService.getParentalControlLevel(movieId)).thenReturn("18");

        // when
        boolean canWatchTheMovie = parentalControlService.canWatchTheMovie(ParentControlLevel.UNIVERSAL, movieId);

        // then
        assertThat(canWatchTheMovie, is(false));
    }

    @Test
    public void shouldNotBeAbleToWatchTheMovieWhenMovieServiceFailsWithTechnicalFailureException()
            throws TitleNotFoundException, TechnicalFailureException {
        // given
        when(movieService.getParentalControlLevel(movieId)).thenThrow(TechnicalFailureException.class);

        // when
        boolean canWatchTheMovie = parentalControlService.canWatchTheMovie(ParentControlLevel.UNIVERSAL, movieId);

        // then
        assertThat(canWatchTheMovie, is(false));
    }

    @Test(expected = TitleNotFoundException.class) //then
    public void shouldThrowTitleNotFoundExceptionWhenTheMovieServiceThrowsTitleNotFoundException() throws TitleNotFoundException, TechnicalFailureException {
        // given
        when(movieService.getParentalControlLevel(movieId)).thenThrow(TitleNotFoundException.class);

        // when
        parentalControlService.canWatchTheMovie(ParentControlLevel.UNIVERSAL, movieId);
    }

    @Test
    public void testAllCombinationsOfPreferredAndMovieControlLevels()
            throws TitleNotFoundException, TechnicalFailureException {

        boolean canWatchTheMovie;

        for (ParentControlLevel movieParentalControlLevel : ParentControlLevel.values()) {
            // given
            when(movieService.getParentalControlLevel(movieId)).thenReturn(movieParentalControlLevel.getContentCategory());

            for (ParentControlLevel preferredParentalControlLevel : ParentControlLevel.values()) {
                // when
                canWatchTheMovie = parentalControlService.canWatchTheMovie(preferredParentalControlLevel, movieId);

                // then
                assertThat("When Movie Category is: " + movieParentalControlLevel.getContentCategory()
                                + ", AND Preferred Parental control is: " + preferredParentalControlLevel.getContentCategory(),
                        canWatchTheMovie,
                        is(movieParentalControlLevel.getContentCategoryLevel() <= preferredParentalControlLevel.getContentCategoryLevel()));
            }

            reset(movieService);
        }

    }

    @Test(expected = NullPointerException.class) // then
    public void shouldThrowNullPointerExceptionWhenThePreferredControlLevelIsNull() throws TitleNotFoundException {
        // when
        parentalControlService.canWatchTheMovie(null, movieId);
    }

}