package com.sky.videoondemand.parentalcontrol.service;

import com.sky.videoondemand.movie.service.exception.TitleNotFoundException;

public interface ParentalControlService {

    boolean canWatchTheMovie(ParentControlLevel customerParentControlLevel, String movieId) throws TitleNotFoundException;

}
