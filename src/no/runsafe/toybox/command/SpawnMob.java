package no.runsafe.toybox.command;

import no.runsafe.framework.api.IWorld;
import no.runsafe.framework.api.command.argument.EntityType;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.WholeNumber;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.minecraft.RunsafeEntityType;
import no.runsafe.framework.api.player.IPlayer;

public class SpawnMob extends PlayerCommand
{
	public SpawnMob()
	{
		super(
			"spawnmob", "Spawns a mob", "runsafe.toybox.spawnmob",
			new EntityType(NAME).require(),
			new WholeNumber(COUNT).withDefault(1)
		);
	}

	private static final String NAME = "name";
	private static final String COUNT = "count";

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		int n = parameters.getRequired(COUNT);
		if (n > 255)
			return "&cMaximum amount of mobs: 255";

		RunsafeEntityType mobType = parameters.getValue(NAME);

		if (mobType != null && mobType.getName().equalsIgnoreCase("EntityHorse"))
			return "&cPlease use /spawnhorse for that.";

		for (int i = 0; i < n; ++i)
		{
			IWorld world = executor.getWorld();
			if (world != null)
				world.spawn(executor.getLocation(), mobType);
		}
		return null;
	}
}
