package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.RequiredArgument;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.entity.RunsafeEntity;

import java.util.Map;

public class Ride extends PlayerCommand
{
	public Ride()
	{
		super(
			"ride", "Spawns an entity and mounts you to it", "runsafe.toybox.ride",
			new RequiredArgument("entityName")
		);
	}

	@Override
	public String OnExecute(IPlayer executor, Map<String, String> parameters)
	{
		RunsafeEntity entity = executor.getWorld().spawnCreature(executor.getLocation(), parameters.get("entityName"));
		if (entity == null)
			return "Invalid entity name";
		entity.setPassenger(executor);
		return null;
	}
}
