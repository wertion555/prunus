package net.mcreator.prunus.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.entity.Entity;

import net.mcreator.prunus.network.PrunusModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class PlayerRevivalProcedure {
	@SubscribeEvent
	public static void onPlayerRespawned(PlayerEvent.PlayerRespawnEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		{
			boolean _setval = false;
			entity.getCapability(PrunusModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.inprunus = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
