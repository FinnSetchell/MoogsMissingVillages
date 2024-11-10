package com.finndog.mmv.modinit;

import com.finndog.mmv.MSCommon;
import com.finndog.mmv.modinit.registry.RegistryEntry;
import com.finndog.mmv.modinit.registry.ResourcefulRegistries;
import com.finndog.mmv.modinit.registry.ResourcefulRegistry;
import com.finndog.mmv.world.structures.placements.AdvancedRandomSpread;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;


public final class MSStructurePlacementType {
    public static final ResourcefulRegistry<StructurePlacementType<?>> STRUCTURE_PLACEMENT_TYPE = ResourcefulRegistries.create(BuiltInRegistries.STRUCTURE_PLACEMENT, MSCommon.MODID);

    public static final RegistryEntry<StructurePlacementType<AdvancedRandomSpread>> ADVANCED_RANDOM_SPREAD = STRUCTURE_PLACEMENT_TYPE.register("advanced_random_spread", () -> () -> AdvancedRandomSpread.CODEC);
}
