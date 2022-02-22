package org.systems.dipe.srs.location;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.location.storage.PointsRepository;
import org.systems.dipe.srs.utils.GroupUtils;
import org.systems.dipe.srs.utils.TimeUtils;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class PointsClientImpl implements PointsClient {

    private final PointsRepository repository;
    private final CommentsClient commentsClient;

    @Override
    public void create(Collection<Point> points) {
        if (CollectionUtils.isEmpty(points)) {
            return;
        }
        preparePoints(points);
        repository.create(points);
        commentsClient.create(collectComments(points));
    }

    @Override
    public void update(Collection<Point> points) {
        if (CollectionUtils.isEmpty(points)) {
            return;
        }
        preparePoints(points);
        repository.update(points);
        commentsClient.update(collectComments(points));
    }

    @Override
    public Collection<Point> search(PointsSearch search) {
        if (CollectionUtils.isEmpty(search.getLocationIds())
                && CollectionUtils.isEmpty(search.getPointIds())) {
            return Collections.emptyList();
        }
        Collection<Point> points = repository.search(search);
        if (!points.isEmpty() && search.isWithDetails()) {
            Collection<Comment> comments = commentsClient.search(
                    CommentsSearch.builder()
                            .pointIds(GroupUtils.extractUnique(
                                    points,
                                    Point::getPointId
                            ))
                            .build()
            );
            Map<String, List<Comment>> commentsMap = GroupUtils.groupMultipleBy(comments, Comment::getPointId);
            for (Point point : points) {
                point.setComments(commentsMap.get(point.getPointId()));
            }
        }
        return points;
    }

    private static Collection<Comment> collectComments(Collection<Point> points) {
        Collection<Comment> comments = new ArrayList<>();
        for (Point point : points) {
            if (CollectionUtils.isNotEmpty(point.getComments())) {
                for (Comment comment : point.getComments()) {
                    comment.setPointId(point.getPointId());
                }
                comments.addAll(point.getComments());
            }
        }
        return comments;
    }

    private static void preparePoints(Collection<Point> points) {
        for (Point point : points) {
            if (Objects.isNull(point.getPointId())) {
                point.setPointId(UuidUtils.newStr());
            }
            if (Objects.isNull(point.getCreated())) {
                point.setCreated(TimeUtils.now());
            }
        }
    }
}
