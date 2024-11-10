package com.finndog.mst.modinit;

import com.finndog.mst.MSCommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public final class MSTags {
    public static void initTags() {}

    public static TagKey<Structure> LARGER_LOCATE_SEARCH = TagKey.create(Registries.STRUCTURE,
            ResourceLocation.fromNamespaceAndPath(MSCommon.MODID, "larger_locate_search"));

}
