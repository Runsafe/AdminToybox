package no.runsafe.toybox.command;

import net.minecraft.server.v1_7_R1.EntityMinecartAbstract;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.WholeNumber;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.entity.IEntity;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.internal.wrapper.ObjectUnwrapper;
import no.runsafe.framework.minecraft.entity.PassiveEntity;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftEntity;

public class SpawnCustomMinecart extends PlayerCommand
{
	public SpawnCustomMinecart()
	{
		super("spawncustomminecart", "Spawn a custom minecart!", "runsafe.toybox.spawnminecart", new WholeNumber("id"), new WholeNumber("data"));
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		IEntity minecartEntity = PassiveEntity.Minecart.spawn(executor.getLocation());
		CraftEntity craftEntity = ObjectUnwrapper.convert(minecartEntity);
		EntityMinecartAbstract ema = (EntityMinecartAbstract) craftEntity.getHandle();

		ema.k((Integer) parameters.getValue("id"));
		ema.l((Integer) parameters.getValue("data"));
		return null;
	}
}
