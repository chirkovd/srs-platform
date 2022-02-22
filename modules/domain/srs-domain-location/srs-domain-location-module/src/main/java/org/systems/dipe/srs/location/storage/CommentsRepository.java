package org.systems.dipe.srs.location.storage;

import org.systems.dipe.srs.location.Comment;
import org.systems.dipe.srs.location.CommentsSearch;

import java.util.Collection;

public interface CommentsRepository {

    void create(Collection<Comment> comments);

    void update(Collection<Comment> comments);

    Collection<Comment> search(CommentsSearch search);

}
