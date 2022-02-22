package org.systems.dipe.srs.location.storage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.location.Comment;
import org.systems.dipe.srs.location.jooq.tables.records.JCommentRecord;
import org.systems.dipe.srs.mappers.CommonMapper;

import java.util.Collection;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CommentsMapper extends CommonMapper {

    JCommentRecord toJooq(Comment comment);

    Collection<JCommentRecord> toJooq(Collection<Comment> comments);

    Comment fromJooq(JCommentRecord record);

}
