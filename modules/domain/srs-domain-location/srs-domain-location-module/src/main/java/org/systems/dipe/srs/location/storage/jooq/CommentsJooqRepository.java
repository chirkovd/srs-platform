package org.systems.dipe.srs.location.storage.jooq;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.jooq.Condition;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.location.Comment;
import org.systems.dipe.srs.location.CommentsSearch;
import org.systems.dipe.srs.location.jooq.tables.JComment;
import org.systems.dipe.srs.location.jooq.tables.records.JCommentRecord;
import org.systems.dipe.srs.location.storage.CommentsRepository;
import org.systems.dipe.srs.location.storage.mapper.CommentsMapper;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.ArrayList;
import java.util.Collection;

@Repository
@Transactional
@AllArgsConstructor
public class CommentsJooqRepository implements CommentsRepository {

    private final DefaultDSLContext dsl;
    private final CommentsMapper mapper;

    @Override
    public void create(Collection<Comment> comments) {
        dsl.batchInsert(mapper.toJooq(comments)).execute();
    }

    @Override
    public void update(Collection<Comment> comments) {
        for (Comment comment : comments) {
            JCommentRecord record = mapper.toJooq(comment);
            dsl.insertInto(JComment.COMMENT)
                    .set(record)
                    .onDuplicateKeyUpdate()
                    .set(record)
                    .execute();
        }
    }

    @Override
    public Collection<Comment> search(CommentsSearch search) {
        Collection<Condition> conditions = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(search.getCommentIds())) {
            conditions.add(JComment.COMMENT.COMMENT_ID.in(UuidUtils.fromStr(search.getCommentIds())));
        }
        if (CollectionUtils.isNotEmpty(search.getPointIds())) {
            conditions.add(JComment.COMMENT.POINT_ID.in(UuidUtils.fromStr(search.getPointIds())));
        }
        if (CollectionUtils.isNotEmpty(search.getAuthorIds())) {
            conditions.add(JComment.COMMENT.AUTHOR_ID.in(UuidUtils.fromStr(search.getAuthorIds())));
        }

        return dsl.selectFrom(JComment.COMMENT)
                .where(conditions)
                .fetch()
                .map(mapper::fromJooq);
    }
}
