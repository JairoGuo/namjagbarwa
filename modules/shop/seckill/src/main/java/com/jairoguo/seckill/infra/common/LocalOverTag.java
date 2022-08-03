package com.jairoguo.seckill.infra.common;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jairo Guo
 */
@Component
public class LocalOverTag {
    private final Map<Long, Boolean> localOverMap = new HashMap<>();

    public Boolean isOver(Long specsAttributeId) {
        return localOverMap.get(specsAttributeId) != null;
    }

    public void setOver(Long specsAttributeId) {
        localOverMap.put(specsAttributeId, true);
    }

    public void removeTag(Long specsAttributeId) {
        localOverMap.remove(specsAttributeId);
    }
}
