package no.runsafe.toybox.command;

import no.runsafe.framework.command.player.PlayerCommand;
import no.runsafe.framework.server.entity.RunsafeEntity;
import no.runsafe.framework.server.player.RunsafePlayer;

import java.util.HashMap;

public class Ride extends PlayerCommand
{
	public Ride()
	{
		super("ride", "Spawns an entity and mounts you to it", "runsafe.toybox.ride", "entityName");
	}

	@Override
	public String OnExecute(RunsafePlayer executor, HashMap<String, String> parameters)
	{
		RunsafeEntity entity = executor.getWorld().spawnCreature(executor.getLocation(), parameters.get("entityName"));
		if(entity == null)
			return "Invalid entity name";
		entity.setPassenger(executor);
		return null;
	}
}
