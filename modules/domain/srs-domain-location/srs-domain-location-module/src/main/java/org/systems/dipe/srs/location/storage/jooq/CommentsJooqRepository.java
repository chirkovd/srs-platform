package org.systems.dipe.srs.location.storage.jooq;

import lombok.AllArgsConstructor;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.location.Comment;
import org.systems.dipe.srs.location.CommentsSearch;
import org.systems.dipe.srs.location.storage.CommentsRepository;
import org.systems.dipe.srs.location.storage.mapper.CommentsMapper;

import java.util.Collection;

@Repository
@Transactional
@AllArgsConstructor
public class CommentsJooqRepository implements CommentsRepository {

    private final DefaultDSLContext dsl;
    private final CommentsMapper mapper;

    @Override
    public void create(Collection<Comment> comments) {

    }

    @Override
    public Collection<Comment> search(CommentsSearch search) {
        return null;
    }
}
