
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.prunus.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.levelgen.feature.Feature;

import net.mcreator.prunus.world.features.StartPrunusFeature;
import net.mcreator.prunus.PrunusMod;

@Mod.EventBusSubscriber
public class PrunusModFeatures {
	public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, PrunusMod.MODID);
	public static final RegistryObject<Feature<?>> START_PRUNUS = REGISTRY.register("start_prunus", StartPrunusFeature::feature);
}
