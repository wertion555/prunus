
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.prunus.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.biome.Biome;

import net.mcreator.prunus.world.biome.BiomeBiome;
import net.mcreator.prunus.PrunusMod;

public class PrunusModBiomes {
	public static final DeferredRegister<Biome> REGISTRY = DeferredRegister.create(ForgeRegistries.BIOMES, PrunusMod.MODID);
	public static final RegistryObject<Biome> BIOME = REGISTRY.register("biome", BiomeBiome::createBiome);
}
