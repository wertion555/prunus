package net.mcreator.prunus.procedures;

import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.prunus.network.PrunusModVariables;

public class OnPlayerLoadWorldProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (PrunusModVariables.MapVariables.get(world).ONLoadWorld == false) {
			if (entity instanceof Player _player) {
				_player.getAbilities().flying = true;
				_player.onUpdateAbilities();
			}
			if (world instanceof ServerLevel _serverworld) {
				StructureTemplate template = _serverworld.getStructureManager().getOrCreate(new ResourceLocation("prunus", "startprunus"));
				if (template != null) {
					template.placeInWorld(_serverworld, new BlockPos(x - 3, y - 3, z - 3), new BlockPos(x - 3, y - 3, z - 3), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false),
							_serverworld.random, 3);
				}
			}
			PrunusModVariables.MapVariables.get(world).ONLoadWorld = true;
			PrunusModVariables.MapVariables.get(world).syncData(world);
			if (entity instanceof Player _player) {
				_player.getAbilities().flying = false;
				_player.onUpdateAbilities();
			}
		}
	}
}
