package no.runsafe.toybox.command;

import no.runsafe.framework.api.IWorld;
import no.runsafe.framework.api.command.argument.EntityType;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.entity.IEntity;
import no.runsafe.framework.api.player.IPlayer;

public class Ride extends PlayerCommand
{
	public Ride()
	{
		super("ride",
			"Spawns an entity and mounts you to it",
			"runsafe.toybox.ride",
			new EntityType().require()
		);
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		IWorld world = executor.getWorld();
		if (world == null)
			return null;
		IEntity entity = executor.getWorld().spawnCreature(executor.getLocation(), parameters.get("entityType"));
		entity.setPassenger(executor);
		return null;
	}
}
