package com.sky.videoondemand.movie.service;


import com.sky.videoondemand.movie.service.exception.TechnicalFailureException;
import com.sky.videoondemand.movie.service.exception.TitleNotFoundException;

public interface MovieService {

    String getParentalControlLevel(String movieId) throws TechnicalFailureException, TitleNotFoundException;

}
