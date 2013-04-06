package no.runsafe.toybox.command;

import no.runsafe.framework.command.player.PlayerCommand;
import no.runsafe.framework.server.player.RunsafePlayer;

import java.util.HashMap;

public class SpawnMob extends PlayerCommand
{
	public SpawnMob()
	{
		super("spawnmob", "Spawns a mob", "runsafe.toybox.spawnmob", "name", "count");
	}

	@Override
	public String OnExecute(RunsafePlayer executor, HashMap<String, String> parameters)
	{
		int n = Integer.parseInt(parameters.get("count"));
		for (int i = 0; i < n; ++i)
			executor.getWorld().spawnCreature(executor.getLocation(), parameters.get("name"));
		return null;
	}
}
