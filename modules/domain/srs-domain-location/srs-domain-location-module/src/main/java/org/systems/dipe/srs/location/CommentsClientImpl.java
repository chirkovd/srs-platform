package org.systems.dipe.srs.location;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.location.storage.CommentsRepository;

import java.util.Collection;
import java.util.Collections;

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
        repository.create(comments);
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
}
