package net.gremlinarvinr.gremlinpets.entity.variant;

import net.minecraft.util.ByIdMap;

import java.util.function.IntFunction;

public enum RatEyeColor {
    DEFAULT(0),
    BLACK(1),
    PINK(2);

    private static final IntFunction<RatEyeColor> BY_ID = ByIdMap.continuous(RatEyeColor::getId, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
    private final int id;

    private RatEyeColor(int pId) {
        this.id = pId;
    }

    public int getId() {
        return this.id;
    }

    public static RatEyeColor byId(int pId) {
        return BY_ID.apply(pId);
    }
}
