package org.systems.dipe.srs.inventory;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.systems.dipe.srs.SrsDbTest;
import org.systems.dipe.srs.inventory.config.TestConfig;

@SpringBootTest(classes = TestConfig.class)
class InventoryClientImplTest extends SrsDbTest {

    @Autowired
    private InventoryClient inventoryClient;

    @Test
    void create() {
        Inventory inventory = new Inventory();
        inventory.setName("Equipment 1");

        Inventory result = inventoryClient.create(inventory);

        Assertions.assertThat(result).isNotNull();
    }
}