package org.systems.dipe.srs.location;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.location.storage.CommentsRepository;
import org.systems.dipe.srs.utils.TimeUtils;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class CommentsClientImpl implements CommentsClient {

    private final CommentsRepository repository;

    @Override
    public void create(Collection<Comment> comments) {
        if (CollectionUtils.isEmpty(comments)) {
            return;
        }
        prepareComments(comments);
        repository.create(comments);
    }

    @Override
    public void update(Collection<Comment> comments) {
        if (CollectionUtils.isEmpty(comments)) {
            return;
        }
        prepareComments(comments);
        repository.update(comments);
    }

    @Override
    public Collection<Comment> search(CommentsSearch search) {
        if (CollectionUtils.isEmpty(search.getCommentIds())
                && CollectionUtils.isEmpty(search.getAuthorIds())
                && CollectionUtils.isEmpty(search.getPointIds())) {
            return Collections.emptyList();
        }
        return repository.search(search);
    }

    private static void prepareComments(Collection<Comment> comments) {
        for (Comment comment : comments) {
            if (Objects.isNull(comment.getCommentId())) {
                comment.setCommentId(UuidUtils.newStr());
            }
            if (Objects.isNull(comment.getCreated())) {
                comment.setCreated(TimeUtils.now());
            }
        }
    }
}
