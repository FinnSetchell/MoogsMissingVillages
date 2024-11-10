package com.finndog.mmv.modinit;

import com.finndog.mmv.MSCommon;
import com.finndog.mmv.modinit.registry.RegistryEntry;
import com.finndog.mmv.modinit.registry.ResourcefulRegistries;
import com.finndog.mmv.modinit.registry.ResourcefulRegistry;
import com.finndog.mmv.world.structures.GenericJigsawStructure;
import com.finndog.mmv.world.structures.GenericNetherJigsawStructure;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.StructureType;


public final class MSStructures {
    public static final ResourcefulRegistry<StructureType<?>> STRUCTURE_TYPE = ResourcefulRegistries.create(BuiltInRegistries.STRUCTURE_TYPE, MSCommon.MODID);

    public static RegistryEntry<StructureType<GenericJigsawStructure>> GENERIC_JIGSAW_STRUCTURE = STRUCTURE_TYPE.register("ms_generic_jigsaw_structure", () -> () -> GenericJigsawStructure.CODEC);
    public static RegistryEntry<StructureType<GenericNetherJigsawStructure>> GENERIC_NETHER_JIGSAW_STRUCTURE = STRUCTURE_TYPE.register("ms_generic_nether_jigsaw_structure", () -> () -> GenericNetherJigsawStructure.CODEC);
}


