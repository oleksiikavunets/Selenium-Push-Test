package utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MapUtil {

    public static <K, V> Map<K, V> toMap(List<K> k, List<V> v ){
        return IntStream.range(0, k.size())
                .boxed()
                .collect(Collectors.toMap(i -> k.get(i), i -> v.get(i)));
    }
}
