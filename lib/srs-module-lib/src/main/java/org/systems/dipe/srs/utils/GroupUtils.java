package org.systems.dipe.srs.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GroupUtils {

    public static <S, T> Set<S> extractUnique(Collection<T> source, Function<T, S> extractor) {
        if (CollectionUtils.isEmpty(source)) {
            return Collections.emptySet();
        }
        return source.stream().map(extractor).collect(Collectors.toSet());
    }

    public static <S, T> Map<S, T> groupBy(Collection<T> source, Function<T, S> extractor) {
        if (CollectionUtils.isEmpty(source)) {
            return Collections.emptyMap();
        }
        return source.stream().collect(Collectors.toMap(extractor, Function.identity()));
    }

    public static <S, T> Map<S, List<T>> groupMultipleBy(Collection<T> source, Function<T, S> extractor) {
        if (CollectionUtils.isEmpty(source)) {
            return Collections.emptyMap();
        }
        return source.stream().collect(Collectors.groupingBy(extractor, HashMap::new, Collectors.toList()));
    }

}
