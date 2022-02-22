package org.systems.dipe.srs.location;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.location.storage.PointsRepository;

import java.util.Collection;

@Service
@Transactional
@AllArgsConstructor
public class PointsClientImpl implements PointsClient {

    private final PointsRepository repository;

    @Override
    public void create(Collection<Point> points) {
        repository.create(points);
    }

    @Override
    public Collection<Point> search(PointsSearch search) {
        return repository.search(search);
    }
}
