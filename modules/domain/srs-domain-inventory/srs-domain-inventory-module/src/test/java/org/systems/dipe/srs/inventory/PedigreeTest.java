package org.systems.dipe.srs.inventory;


import io.qameta.allure.internal.shadowed.jackson.core.type.TypeReference;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import io.qameta.allure.internal.shadowed.jackson.databind.json.JsonMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.record.Record;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParser;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.boot.test.context.SpringBootTest;
import org.systems.dipe.srs.SrsDbTest;
import org.systems.dipe.srs.inventory.config.TestConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Slf4j
@SpringBootTest(classes = TestConfig.class)
public class PedigreeTest extends SrsDbTest {

    @Test
    void testTree() {
        PGraph graph = new PGraph();
        try (InputStream stream = PedigreeTest.class.getResourceAsStream("/pedigree_pairs.csv")) {
            CsvParser csvParser = new CsvParser(new CsvParserSettings());
            for (Record record : csvParser.iterateRecords(stream)) {
                String progenyId = record.getString(0).trim();
                String parentId = record.getString(1).trim();

                if (isUuid(progenyId) && isUuid(parentId)) {
                    graph.addNodes(progenyId, parentId);
                } else {
                    log.debug("Skip {}/{} pair", progenyId, parentId);
                }
            }
            graph.detectCycles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testMap() {
        Map<String, String> mapping = new HashMap<>();
        try (InputStream stream = PedigreeTest.class.getResourceAsStream("/s.json")) {
            ObjectMapper objectMapper = new JsonMapper();
            List<Link> links = objectMapper.readValue(stream, new TypeReference<>() {
            });
            for (Link link : links) {
                mapping.put(link.getSampleId(), link.getAnimalId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> wrongSamples = new ArrayList<>();
        try (InputStream stream = PedigreeTest.class.getResourceAsStream("/s_pedigree.csv")) {
            CsvParser csvParser = new CsvParser(new CsvParserSettings());
            for (Record record : csvParser.iterateRecords(stream)) {
                String sampleId = record.getString(0);
                if (mapping.containsKey(sampleId)) {
                    String parentId = record.getString(2);
                    String newProgenyId = mapping.get(sampleId);
                    if (parentId.trim().equals(newProgenyId.trim())) {
                        wrongSamples.add(sampleId);
                    } else {
                        log.debug("{},{},0", newProgenyId, parentId);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        log.debug("Wrong samples: {}", wrongSamples);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static class Link {
        private String sampleId;
        private String animalId;
    }

    @Getter
    @RequiredArgsConstructor
    class PNode {
        private final String id;
        private final Set<PNode> parents = new HashSet<>();

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            PNode pNode = (PNode) o;

            return new EqualsBuilder().append(id, pNode.id).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(id).toHashCode();
        }
    }

    class PGraph {
        private final Map<String, PNode> nodes = new HashMap<>();

        private List<String> paths = new ArrayList<>();
        private List<String> cycles = new ArrayList<>();

        public void addNodes(String progenyId, String parentId) {
            nodes.computeIfAbsent(progenyId, PNode::new)
                    .getParents()
                    .add(nodes.computeIfAbsent(parentId, PNode::new));
        }

        public void detectCycles() {
            log.debug("Graph contains {} nodes", nodes.size());

            paths = new ArrayList<>();
            cycles = new ArrayList<>();

            nodes.forEach((rootId, node) -> {
                List<String> path = new ArrayList<>();
                iterate(node, path);
            });

            paths.sort(Comparator.comparing(String::length).reversed());
            log.debug("Top 10 paths:");
            paths.stream().limit(10).forEach(p -> {
                log.debug("{}", p);
            });

            log.debug("Cycles: {}", cycles.size());
            for (String cycle : cycles) {
                log.debug("{}", cycle);
            }
        }

        private void iterate(PNode node, List<String> path) {
            if (path.contains(node.getId())) {
                path.add(node.getId());
                cycles.add(String.join(" -> ", path));
                return;
            }
            path.add(node.getId());

            if (CollectionUtils.isNotEmpty(node.getParents())) {
                for (PNode parent : node.getParents()) {
                    iterate(parent, new ArrayList<>(path));
                }
            } else {
                paths.add(String.join(" -> ", path));
            }
        }
    }

    private static boolean isUuid(String id) {
        try {
            UUID.fromString(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
