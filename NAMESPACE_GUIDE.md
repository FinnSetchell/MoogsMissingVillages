# Namespace Usage Guide for MoogsStructureLib-Based Mods

## Purpose
This document provides clear instructions on when to use the `moogs_structures` namespace versus your mod's own namespace when creating or modifying datapack files for structure mods built with the MoogsStructureLib framework.

---

## Getting Started

**First, identify your mod's namespace:**
- Your mod namespace is defined as `modId` in `gradle.properties`
- Common examples: `mtr`, `mvs`, `mes`, `mns`, etc.
- Throughout this guide, replace `{mod_namespace}` with your actual mod ID

---

## Overview

Structure mods built with MoogsStructureLib use three main namespaces:
- **`moogs_structures`** - References to MoogsStructureLib library features (types, functions, systems)
- **`{mod_namespace}`** - References to your mod's own resources (structures, pools, loot tables, biome tags)
- **`minecraft`** - References to vanilla Minecraft features (items, blocks, element types, processors)

---

## When to Use `moogs_structures:`

Use the `moogs_structures` namespace when referencing **types and systems** provided by the MoogsStructureLib library. This library provides the core structure generation framework that this mod depends on.

### Common Use Cases:

#### 1. Structure Type Definitions
When defining the structure type in worldgen structure JSON files:

```json
{
    "type": "moogs_structures:moogs_structures_generic_jigsaw_structure",
    ...
}
```

**Files:** `src/main/resources/data/{mod_namespace}/worldgen/structure/*.json`

**Examples:**
- `desert_temple.json`
- `jungle_temple.json`
- `ocean_temple.json`
- `stronghold.json`

---

#### 2. Structure Set Placement Types
When defining the placement algorithm type in structure_set JSON files:

```json
{
    "structures": [...],
    "placement": {
        "type": "moogs_structures:advanced_random_spread",
        "salt": 225874987,
        "spacing": 50,
        "separation": 8
    }
}
```

**Files:** `src/main/resources/data/{mod_namespace}/worldgen/structure_set/*.json`

**Examples:**
- `desert_temple.json`
- `jungle_temple.json`
- `ocean_temple.json`
- `stronghold.json`

---

#### 3. Mod Dependencies
When declaring dependencies in mod metadata files:

**fabric.mod.json:**
```json
{
    "depends": {
        "moogs_structures": "*"
    }
}
```

**neoforge.mods.toml:**
```toml
[[dependencies.{mod_namespace}]]
modId = "moogs_structures"
mandatory = true
versionRange = "${structure_lib_version_range}"
```

**Files:**
- `src/main/resources/fabric.mod.json`
- `src/main/resources/quilt.mod.json`
- `src/main/resources/META-INF/neoforge.mods.toml`
- `src/main/resources/META-INF/mods.toml`

---

## When to Use `{mod_namespace}:`

Use your mod's namespace (e.g., `mtr`, `mse`, `mvp`) when referencing **your mod's own resources** - anything created and maintained within your project.

### Common Use Cases:

#### 1. Structure References
When referencing a structure defined by your mod:

```json
{
    "structures": [
        {
            "structure": "{mod_namespace}:desert_temple",
            "weight": 1
        }
    ]
}
```

**Files:** `src/main/resources/data/{mod_namespace}/worldgen/structure_set/*.json`

---

#### 2. Template Pool Names
When defining or referencing template pool names:

```json
{
    "type": "moogs_structures:moogs_structures_generic_jigsaw_structure",
    "start_pool": "{mod_namespace}:desert-mega-temple/start_pool",
    ...
}
```

```json
{
    "name": "{mod_namespace}:jungle_temple/start_pool",
    "fallback": "minecraft:empty",
    ...
}
```

**Files:** 
- `src/main/resources/data/{mod_namespace}/worldgen/structure/*.json` (references)
- `src/main/resources/data/{mod_namespace}/worldgen/template_pool/**/*.json` (definitions)

**Examples:**
- `{mod_namespace}:desert-mega-temple/start_pool`
- `{mod_namespace}:desert-mega-temple/side_pool/lower`
- `{mod_namespace}:jungle_temple/start_pool`
- `{mod_namespace}:ocean_temple_start_pool`
- `{mod_namespace}:stronghold_start_pool`

---

#### 3. Structure File Locations
When referencing NBT structure files within template pool elements:

```json
{
    "element": {
        "location": "{mod_namespace}:desert-mega-temple/desert-mega-temple-bl",
        "processors": "minecraft:empty",
        "projection": "rigid",
        "element_type": "minecraft:single_pool_element"
    }
}
```

**Files:** `src/main/resources/data/{mod_namespace}/worldgen/template_pool/**/*.json`

**Note:** These locations point to NBT files in `src/main/resources/data/{mod_namespace}/structure/`

---

#### 4. Biome Tags
When referencing custom biome tags for structure placement:

```json
{
    "biomes": "#{mod_namespace}:has_structure/desert_biomes",
    ...
}
```

**Files:** 
- `src/main/resources/data/{mod_namespace}/worldgen/structure/*.json` (references)
- `src/main/resources/data/{mod_namespace}/tags/worldgen/biome/has_structure/*.json` (definitions)

**Examples:**
- `#{mod_namespace}:has_structure/desert_biomes`
- `#{mod_namespace}:has_structure/jungle_biomes`
- `#{mod_namespace}:has_structure/ocean_biomes`
- `#{mod_namespace}:has_structure/overworld_biomes`

**Note:** The `#` prefix indicates this is a tag reference, not a direct biome ID.

---

#### 5. Jigsaw/Side Pool References
When referencing additional jigsaw pools for complex structures:

```json
{
    "type": "moogs_structures:moogs_structures_generic_jigsaw_structure",
    "start_pool": "{mod_namespace}:stronghold_start_pool",
    "jigsaw": "{mod_namespace}:stronghold_side_pool",
    ...
}
```

**Files:** `src/main/resources/data/{mod_namespace}/worldgen/structure/*.json`

---

## When to Use `minecraft:`

Use the `minecraft` namespace when referencing **vanilla Minecraft features** provided by the base game.

### Common Use Cases:

#### 1. Pool Element Types
When specifying the type of template pool element:

```json
{
    "element_type": "minecraft:single_pool_element"
}
```

**Files:** `src/main/resources/data/mtr/worldgen/template_pool/**/*.json`

---

#### 2. Processors and Fallbacks
When using vanilla processors or fallback pools:

```json
{
    "processors": "minecraft:empty",
    "fallback": "minecraft:empty"
}
```

**Files:** `src/main/resources/data/mtr/worldgen/template_pool/**/*.json`

---

#### 3. Loot Table Types and Functions
When defining loot table types, item names, and functions:

```json
{
    "type": "minecraft:chest",
    "pools": [
        {
            "entries": [
                {
                    "type": "minecraft:item",
                    "name": "minecraft:diamond",
                    "functions": [
                        {
                            "function": "minecraft:set_count",
                            "count": {
                                "type": "minecraft:uniform",
                                "min": 2,
                                "max": 4
                            }
                        }
                    ]
                }
            ]
        }
    ]
}
```

**Files:** `src/main/resources/data/{mod_namespace}/loot_table/*.json`

---

#### 4. Biome Tag References (Vanilla)
When referencing vanilla biome tags:

```json
{
    "values": [
        "#minecraft:is_jungle"
    ]
}
```

**Files:** `src/main/resources/data/{mod_namespace}/tags/worldgen/biome/has_structure/*.json`

---

## Quick Reference Table

| Context | Namespace | Example |
|---------|-----------|---------|
| Structure generation type | `moogs_structures:` | `moogs_structures:moogs_structures_generic_jigsaw_structure` |
| Placement algorithm type | `moogs_structures:` | `moogs_structures:advanced_random_spread` |
| Mod dependency declaration | `moogs_structures` | `"moogs_structures": "*"` |
| Your structure reference | `{mod_namespace}:` | `{mod_namespace}:desert_temple` |
| Your template pool name | `{mod_namespace}:` | `{mod_namespace}:desert-mega-temple/start_pool` |
| Your NBT structure location | `{mod_namespace}:` | `{mod_namespace}:desert-mega-temple/desert-mega-temple-bl` |
| Your custom biome tag | `#{mod_namespace}:` | `#{mod_namespace}:has_structure/desert_biomes` |
| Vanilla pool element type | `minecraft:` | `minecraft:single_pool_element` |
| Vanilla processor/fallback | `minecraft:` | `minecraft:empty` |
| Vanilla items/blocks | `minecraft:` | `minecraft:diamond` |
| Vanilla loot functions | `minecraft:` | `minecraft:set_count` |
| Vanilla biome tags | `#minecraft:` | `#minecraft:is_jungle` |

---

## Directory Structure Reference

This shows where files using each namespace are typically located:

```
src/main/resources/data/{mod_namespace}/
├── loot_table/              # Uses minecraft: for types/functions
│   ├── desert_temple_*.json
│   ├── jungle_temple_*.json
│   ├── ocean_temple_*.json
│   └── stronghold_*.json
├── structure/               # NBT files (referenced as {mod_namespace}:path/to/file)
│   ├── desert-mega-temple/
│   ├── jungle_temple/
│   ├── ocean_temple.nbt
│   └── stronghold/
├── tags/worldgen/biome/has_structure/  # Define {mod_namespace}: tags
│   ├── desert_biomes.json
│   ├── jungle_biomes.json
│   └── ocean_biomes.json
└── worldgen/
    ├── structure/           # Uses moogs_structures: for type
    │   ├── desert_temple.json        # References {mod_namespace}: pools/tags
    │   ├── jungle_temple.json
    │   ├── ocean_temple.json
    │   └── stronghold.json
    ├── structure_set/       # Uses moogs_structures: for placement type
    │   ├── desert_temple.json        # References {mod_namespace}: structures
    │   ├── jungle_temple.json
    │   ├── ocean_temple.json
    │   └── stronghold.json
    └── template_pool/       # Define {mod_namespace}: pools
        ├── desert-mega-temple/       # Use minecraft: for element types
        ├── jungle_temple/            # Reference {mod_namespace}: structure files
        ├── ocean_temple_start_pool.json
        ├── stronghold_start_pool.json
        └── stronghold_side_pool.json
```

---

## Common Mistakes to Avoid

### ❌ Wrong: Using your mod namespace for library features
```json
{
    "type": "{mod_namespace}:moogs_structures_generic_jigsaw_structure"
}
```

### ✅ Correct: Using moogs_structures for library features
```json
{
    "type": "moogs_structures:moogs_structures_generic_jigsaw_structure"
}
```

---

### ❌ Wrong: Using moogs_structures for your own structures
```json
{
    "structure": "moogs_structures:desert_temple"
}
```

### ✅ Correct: Using your mod namespace for your own structures
```json
{
    "structure": "{mod_namespace}:desert_temple"
}
```

---

### ❌ Wrong: Using your mod namespace for vanilla elements
```json
{
    "element_type": "{mod_namespace}:single_pool_element"
}
```

### ✅ Correct: Using minecraft for vanilla elements
```json
{
    "element_type": "minecraft:single_pool_element"
}
```

---

## Summary

**Simple Rule of Thumb:**
- **`moogs_structures:`** → Library types and systems (what the library provides)
- **`{mod_namespace}:`** → Your mod's resources (what you create)
- **`minecraft:`** → Vanilla game features (what Minecraft provides)

When in doubt, ask yourself: "Who provides this feature?"
- MoogsStructureLib → `moogs_structures:`
- Your Mod (check `modId` in `gradle.properties`) → `{mod_namespace}:`
- Vanilla Minecraft → `minecraft:`

---

## Additional Notes

- The namespace is always followed by a colon `:` (e.g., `{mod_namespace}:desert_temple`)
- Tags are prefixed with `#` (e.g., `#{mod_namespace}:has_structure/desert_biomes`)
- File paths within namespaces use forward slashes `/` even on Windows
- Your mod's namespace is defined in `gradle.properties` as `modId={mod_namespace}`
- All custom structure files must be placed in `src/main/resources/data/{mod_namespace}/structure/`
- All custom loot tables must be placed in `src/main/resources/data/{mod_namespace}/loot_table/`
- All worldgen files must be placed in `src/main/resources/data/{mod_namespace}/worldgen/`

---

## Real-World Example

**If your mod has `modId=mtr` in `gradle.properties`:**
- Structure reference: `mtr:desert_temple`
- Pool name: `mtr:desert-mega-temple/start_pool`
- Biome tag: `#mtr:has_structure/desert_biomes`

**If your mod has `modId=mse` in `gradle.properties`:**
- Structure reference: `mse:desert_temple`
- Pool name: `mse:desert-mega-temple/start_pool`
- Biome tag: `#mse:has_structure/desert_biomes`

---

**Last Updated:** October 11, 2025  
**Applicable to all MoogsStructureLib-based mods**

