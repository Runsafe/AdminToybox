package no.runsafe.toybox.command;

import no.runsafe.framework.api.IWorld;
import no.runsafe.framework.api.command.argument.EntityType;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.RequiredArgument;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;

public class SpawnMob extends PlayerCommand
{
	public SpawnMob()
	{
		super(
			"spawnmob", "Spawns a mob", "runsafe.toybox.spawnmob",
			new EntityType(NAME).require(),
			new RequiredArgument(COUNT)
		);
	}

	private static final String NAME = "name";
	private static final String COUNT = "count";

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		int n = Integer.parseInt(parameters.get(COUNT));
		String name = parameters.get(NAME);

		if (name != null && name.equalsIgnoreCase("horse"))
			return "&cPlease use /spawnhorse for that.";

		for (int i = 0; i < n; ++i)
		{
			IWorld world = executor.getWorld();
			if (world != null)
				world.spawn(executor.getLocation(), no.runsafe.framework.minecraft.entity.EntityType.getTypeByName(name));
		}
		return null;
	}
}
