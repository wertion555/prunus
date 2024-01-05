package net.mcreator.prunus.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.ServerChatEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.client.Minecraft;

import net.mcreator.prunus.network.PrunusModVariables;
import net.mcreator.prunus.PrunusMod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ChatPrunusProcedure {
	@SubscribeEvent
	public static void onChat(ServerChatEvent.Submitted event) {
		execute(event, event.getPlayer().level, event.getPlayer().getX(), event.getPlayer().getY(), event.getPlayer().getZ(), event.getPlayer(), event.getRawText());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, String text) {
		execute(null, world, x, y, z, entity, text);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, String text) {
		if (entity == null || text == null)
			return;
		String chatmessage = "";
		chatmessage = text;
		if ((chatmessage).equals("prunus") && !(entity.getCapability(PrunusModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PrunusModVariables.PlayerVariables())).inprunus) {
			{
				double _setval = x;
				entity.getCapability(PrunusModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.X = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = y;
				entity.getCapability(PrunusModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Y = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = z;
				entity.getCapability(PrunusModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Z = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/execute in prunus:prunus run tp @p 0 200 0");
			{
				entity.getCapability(PrunusModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					String _setval = "" + (world instanceof Level _lvl ? _lvl.dimension() : Level.OVERWORLD);
					//_setval = _setval.split(":")[_setval.split(":").length];
					_setval = _setval.split(":")[2].substring(0, _setval.split(":")[2].length() - 1);
					capability.world = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				boolean _setval = true;
				entity.getCapability(PrunusModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inprunus = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((chatmessage).equals("malus") == (entity.getCapability(PrunusModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PrunusModVariables.PlayerVariables())).inprunus) {
			if (entity instanceof Player _player) {
				_player.getAbilities().invulnerable = true;
				_player.onUpdateAbilities();
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						(((("/execute in world run tp @p plx ply plz".replace("plx", "" + (entity.getCapability(PrunusModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PrunusModVariables.PlayerVariables())).X)).replace("ply",
								"" + (entity.getCapability(PrunusModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PrunusModVariables.PlayerVariables())).Y))
								.replace("plz", "" + (entity.getCapability(PrunusModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PrunusModVariables.PlayerVariables())).Z))
								.replace("world", (entity.getCapability(PrunusModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PrunusModVariables.PlayerVariables())).world)));
			{
				boolean _setval = false;
				entity.getCapability(PrunusModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inprunus = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			PrunusMod.queueServerWork(40, () -> {
				if (new Object() {
					public boolean checkGamemode(Entity _ent) {
						if (_ent instanceof ServerPlayer _serverPlayer) {
							return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SURVIVAL;
						} else if (_ent.level.isClientSide() && _ent instanceof Player _player) {
							return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
									&& Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.SURVIVAL;
						}
						return false;
					}
				}.checkGamemode(entity) || new Object() {
					public boolean checkGamemode(Entity _ent) {
						if (_ent instanceof ServerPlayer _serverPlayer) {
							return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.ADVENTURE;
						} else if (_ent.level.isClientSide() && _ent instanceof Player _player) {
							return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
									&& Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.ADVENTURE;
						}
						return false;
					}
				}.checkGamemode(entity)) {
					if (entity instanceof Player _player) {
						_player.getAbilities().invulnerable = false;
						_player.onUpdateAbilities();
					}
				}
			});
		}
	}
}
