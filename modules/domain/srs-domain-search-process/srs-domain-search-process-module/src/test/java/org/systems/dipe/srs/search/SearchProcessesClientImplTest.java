package org.systems.dipe.srs.search;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.systems.dipe.srs.SrsDbTest;
import org.systems.dipe.srs.search.config.TestConfig;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.List;

@SpringBootTest(classes = TestConfig.class)
class SearchProcessesClientImplTest extends SrsDbTest {

    @Autowired
    private SearchProcessClient searchProcessClient;

    @Test
    void create() {
        SearchProcess process = new SearchProcess();
        process.setStatus(SearchProcessStatus.CREATED);
        process.setRequestId(UuidUtils.newStr());

        SearchLocation location = new SearchLocation();
        location.setLocationId(UuidUtils.newStr());

        SearchSquad squad = new SearchSquad();
        squad.setSquadId(UuidUtils.newStr());

        process.setLocations(List.of(location));
        process.setSquads(List.of(squad));

        SearchProcess result = searchProcessClient.create(process);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getSquads()).hasSize(1);
        Assertions.assertThat(result.getLocations()).hasSize(1);
    }

    @Test
    void update() {
        SearchProcess process = new SearchProcess();
        process.setStatus(SearchProcessStatus.CREATED);
        process.setRequestId(UuidUtils.newStr());

        SearchLocation location = new SearchLocation();
        location.setLocationId(UuidUtils.newStr());

        SearchSquad squad = new SearchSquad();
        squad.setSquadId(UuidUtils.newStr());

        process.setLocations(List.of(location));
        process.setSquads(List.of(squad));

        SearchProcess result = searchProcessClient.create(process);

        Assertions.assertThat(result).isNotNull();

        result.setStatus(SearchProcessStatus.IN_PROGRESS);
        result.getLocations().get(0).setLocationId(UuidUtils.newStr());

        SearchSquad squad2 = new SearchSquad();
        squad2.setSquadId(UuidUtils.newStr());

        result.getSquads().add(squad2);

        SearchProcess updatedProcess = searchProcessClient.update(result);
        Assertions.assertThat(updatedProcess).isNotNull();
        Assertions.assertThat(updatedProcess)
                .hasFieldOrPropertyWithValue("status", SearchProcessStatus.IN_PROGRESS);
        Assertions.assertThat(result.getSquads()).hasSize(2);
        Assertions.assertThat(result.getLocations()).hasSize(1);
    }

}