package net.gremlinarvinr.gremlinpets.entity.variant;

import net.minecraft.util.ByIdMap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.IntFunction;

public enum RatWhiteMarkings {
    NONE(0);

    private static final IntFunction<RatWhiteMarkings> BY_ID = ByIdMap.continuous(RatWhiteMarkings::getId, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
    private final int id;

    private RatWhiteMarkings(int pId) {
        this.id = pId;
    }

    public int getId() {
        return this.id;
    }

    public static RatWhiteMarkings byId(int pId) {
        return BY_ID.apply(pId);
    }
}
