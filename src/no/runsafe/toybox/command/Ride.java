package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.EntityTypeArgument;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.entity.IEntity;
import no.runsafe.framework.api.player.IPlayer;

public class Ride extends PlayerCommand
{
	public Ride()
	{
		super(
			"ride", "Spawns an entity and mounts you to it", "runsafe.toybox.ride",
			new EntityTypeArgument(true)
		);
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		IEntity entity = executor.getWorld().spawnCreature(executor.getLocation(), parameters.get("entityType"));
		if (entity == null)
			return "Invalid entity name";
		entity.setPassenger(executor);
		return null;
	}
}
