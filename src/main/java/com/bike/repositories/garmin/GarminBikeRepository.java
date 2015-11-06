package com.bike.repositories.garmin;

import com.bike.entity.Book;
import com.bike.entity.garmin.GarminBike;
import com.bike.entity.garmin.UserBikeFitSession;
import com.bike.entity.test.TestFitMsg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by liuyindong on 2015/11/4.
 */
public interface GarminBikeRepository extends ElasticsearchRepository<UserBikeFitSession,String>
{
    Page<UserBikeFitSession> findByUserId(String userId,Pageable pageable);
}
