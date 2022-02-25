package org.systems.dipe.srs.squad;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.systems.dipe.srs.SrsDbTest;
import org.systems.dipe.srs.squad.config.TestConfig;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest(classes = TestConfig.class)
class SquadsClientImplTest extends SrsDbTest {

    @Autowired
    private SquadsClient squadsClient;

    @Test
    void create() {
        Squad squad = new Squad();

        Equipment equipment = new Equipment();
        equipment.setInventoryId(UuidUtils.newStr());

        List<Member> members = IntStream.range(0, 10).mapToObj(i -> {
            Member member = new Member();
            member.setVolunteerId(UuidUtils.newStr());
            return member;
        }).collect(Collectors.toList());
        members.get(0).setHead(true);

        squad.setEquipments(List.of(equipment));
        squad.setMembers(members);

        Squad result = squadsClient.create(squad);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getMembers()).hasSize(10);
        Assertions.assertThat(result.getEquipments()).hasSize(1);
    }

    @Test
    void update() {
        Squad squad = new Squad();

        Equipment equipment = new Equipment();
        equipment.setInventoryId(UuidUtils.newStr());

        List<Member> members = IntStream.range(0, 10).mapToObj(i -> {
            Member member = new Member();
            member.setVolunteerId(UuidUtils.newStr());
            return member;
        }).collect(Collectors.toList());
        members.get(0).setHead(true);

        squad.setEquipments(List.of(equipment));
        squad.setMembers(members);

        Squad result = squadsClient.create(squad);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getMembers()).hasSize(10);
        Assertions.assertThat(result.getEquipments()).hasSize(1);

        result.getEquipments().get(0).setInventoryId(UuidUtils.newStr());
        result.setMembers(IntStream.range(0, 20).mapToObj(i -> {
            Member member = new Member();
            member.setVolunteerId(UuidUtils.newStr());
            return member;
        }).collect(Collectors.toList()));
        result.getMembers().get(1).setHead(true);

        result = squadsClient.update(result);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getMembers()).hasSize(20);
        Assertions.assertThat(result.getEquipments()).hasSize(1);
    }
}