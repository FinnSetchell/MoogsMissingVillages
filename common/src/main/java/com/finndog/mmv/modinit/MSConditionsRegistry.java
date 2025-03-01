package com.finndog.mmv.modinit;

import com.finndog.mmv.MSCommon;
import com.finndog.mmv.modinit.registry.CustomRegistry;
import com.finndog.mmv.modinit.registry.RegistryEntry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public final class MSConditionsRegistry {
    private MSConditionsRegistry() {}

    public static final ResourceKey<Registry<Supplier<Boolean>>> MS_JSON_CONDITIONS_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(MSCommon.MODID, "json_conditions"));
    public static final CustomRegistry<Supplier<Boolean>> MS_JSON_CONDITIONS_REGISTRY = CustomRegistry.of(MSCommon.MODID, MS_JSON_CONDITIONS_KEY, false, false, true);
    public static final RegistryEntry<Supplier<Boolean>> ALWAYS_TRUE = MS_JSON_CONDITIONS_REGISTRY.register("always_true", () -> () -> true);
    public static final RegistryEntry<Supplier<Boolean>> ALWAYS_FALSE = MS_JSON_CONDITIONS_REGISTRY.register("always_false", () -> () -> true);

    /*
     * This registry is for hooking up the pool_additions json files to a code base config to enable/disable it.
     * Best for direct mod compat where a mod wants to add houses to Repurposed Structures by the pool_additions
     * json files like the many Repurposed Structures datapacks works but want a code config to control it.
     *
     * Add "condition" to the individual entries in the template pool in pool_additions folder and give it the
     * ResourceLocation of the condition you registered. The rs_pieces_spawn_counts folder files can also take
     * a "condition" field for its entries as well.
     *
     * You can register what the condition is to this registry by doing the below in your mod so now your config can control the json files.
     * NOTE: DO THIS CODE ONLY AT MOD INIT. Do not run it when a world is being made! The registry will be frozen after mod init.

     * FABRIC/QUILT:
         BuiltInRegistries.REGISTRY.getOptional(new ResourceLocation("repurposed_structures", "json_conditions"))
             .ifPresent(registry -> Registry.register(
                 (Registry<Supplier<Boolean>>)registry,
                 new ResourceLocation("repurposed_structures", "test"),
                 () -> SomeConfig.EnableJson()));

     * FORGE:
        public static final DeferredRegister<Supplier<Boolean>> RS_CONDITIONS_REGISTRY = DeferredRegister.createOptional(
                new ResourceLocation("repurposed_structures", "json_conditions"), "modid");

        // If the typing here doesn't work, make a helper method that takes a Supplier<Boolean> and returns a Supplier<Boolean>
        public static final RegistryObject<Supplier<Boolean>> CUSTOM_MOD_CONFIG_CONDITION = RS_CONDITIONS_REGISTRY.register(
                "test", () -> () -> SomeConfig.EnableJson());
     */
}
