package com.finndog.mst.modinit;

import com.finndog.mst.MSCommon;
import com.finndog.mst.modinit.registry.RegistryEntry;
import com.finndog.mst.modinit.registry.ResourcefulRegistries;
import com.finndog.mst.modinit.registry.ResourcefulRegistry;
import com.finndog.mst.world.structures.pieces.LegacyOceanBottomSinglePoolElement;
import com.finndog.mst.world.structures.pieces.MirroringSingleJigsawPiece;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;


public final class MSStructurePieces {
    public static final ResourcefulRegistry<StructurePoolElementType<?>> STRUCTURE_POOL_ELEMENT = ResourcefulRegistries.create(BuiltInRegistries.STRUCTURE_POOL_ELEMENT, MSCommon.MODID);
    public static final ResourcefulRegistry<StructurePieceType> STRUCTURE_PIECE = ResourcefulRegistries.create(BuiltInRegistries.STRUCTURE_PIECE, MSCommon.MODID);

    public static final RegistryEntry<StructurePoolElementType<MirroringSingleJigsawPiece>> MIRROR_SINGLE = STRUCTURE_POOL_ELEMENT.register("mirroring_single_pool_element", () -> () -> MirroringSingleJigsawPiece.CODEC);
    public static final RegistryEntry<StructurePoolElementType<LegacyOceanBottomSinglePoolElement>> LEGACY_OCEAN_BOTTOM = STRUCTURE_POOL_ELEMENT.register("legacy_ocean_bottom_single_pool_element", () -> () -> LegacyOceanBottomSinglePoolElement.CODEC);
}