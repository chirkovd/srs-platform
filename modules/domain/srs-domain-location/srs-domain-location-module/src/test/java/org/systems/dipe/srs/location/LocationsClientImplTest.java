package org.systems.dipe.srs.location;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.systems.dipe.srs.SrsDbTest;
import org.systems.dipe.srs.location.config.TestConfig;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.List;
import java.util.Random;

@SpringBootTest(classes = TestConfig.class)
class LocationsClientImplTest extends SrsDbTest {

    @Autowired
    private LocationsClient locationsClient;

    @Test
    void create() {
        Random random = new Random();

        Location location = new Location();

        Point point1 = new Point();
        point1.setLatitude(random.nextDouble());
        point1.setLongitude(random.nextDouble());

        Comment comment11 = new Comment();
        comment11.setAuthorId(UuidUtils.newStr());
        comment11.setComment("comment 11");

        point1.setComments(List.of(comment11));

        Point point2 = new Point();
        point2.setLatitude(random.nextDouble());
        point2.setLongitude(random.nextDouble());

        Comment comment21 = new Comment();
        comment21.setAuthorId(UuidUtils.newStr());
        comment21.setComment("comment 21");
        Comment comment22 = new Comment();
        comment22.setAuthorId(UuidUtils.newStr());
        comment22.setComment("comment 22");

        point2.setComments(List.of(comment21, comment22));

        location.setPoints(List.of(
                point1, point2
        ));

        Location result = locationsClient.create(location);

        // checks
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getPoints()).isNotEmpty();
        for (Point point : result.getPoints()) {
            Assertions.assertThat(point.getComments()).isNotEmpty();
        }
    }

    @Test
    void update() {
        Random random = new Random();

        Location location = new Location();

        Point point1 = new Point();
        point1.setLatitude(random.nextDouble());
        point1.setLongitude(random.nextDouble());

        Comment comment11 = new Comment();
        comment11.setAuthorId(UuidUtils.newStr());
        comment11.setComment("comment 11");

        point1.setComments(List.of(comment11));
        location.setPoints(List.of(point1));

        Location result = locationsClient.create(location);

        Point point2 = new Point();
        point2.setLatitude(random.nextDouble());
        point2.setLongitude(random.nextDouble());

        result.getPoints().add(point2);

        Comment comment12 = new Comment();
        comment12.setAuthorId(UuidUtils.newStr());
        comment12.setComment("comment 12");

        result.getPoints().get(0).getComments().add(comment12);

        result.getPoints().get(0).setLatitude(random.nextDouble());
        result.getPoints().get(0).getComments().get(0).setComment("Updated comment 11");

        result = locationsClient.update(result);

        // checks
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getPoints()).hasSize(2);
        Assertions.assertThat(result.getPoints().get(0).getComments()).hasSize(2);
    }
}