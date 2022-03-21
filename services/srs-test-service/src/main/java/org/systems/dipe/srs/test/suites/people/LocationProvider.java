package org.systems.dipe.srs.test.suites.people;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.platform.locations.in.CommentInDto;
import org.systems.dipe.srs.platform.locations.in.LocationInDto;
import org.systems.dipe.srs.platform.locations.in.PointInDto;

import java.util.List;
import java.util.Random;

@Component
public class LocationProvider {

    public LocationInDto buildNewLocation() {
        LocationInDto location = new LocationInDto();
        PointInDto point = newPoint();
        location.setPoints(List.of(point));
        return location;
    }

    public PointInDto newPoint() {
        PointInDto point = new PointInDto();
        Random random = new Random();
        point.setLongitude(random.nextDouble());
        point.setLatitude(random.nextDouble());
        return point;
    }

    public CommentInDto newComment(String personId) {
        CommentInDto comment = new CommentInDto();
        comment.setAuthorId(personId);
        comment.setComment(RandomStringUtils.randomAlphabetic(50));
        return comment;
    }

}
