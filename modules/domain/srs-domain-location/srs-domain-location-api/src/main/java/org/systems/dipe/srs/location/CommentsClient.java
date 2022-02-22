package org.systems.dipe.srs.location;

import java.util.Collection;

public interface CommentsClient {

    void create(Collection<Comment> comments);

    void update(Collection<Comment> comments);

    Collection<Comment> search(CommentsSearch search);
}
