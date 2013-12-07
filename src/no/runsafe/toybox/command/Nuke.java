package no.runsafe.toybox.command;

import no.runsafe.framework.api.IWorld;
import no.runsafe.framework.api.command.argument.RequiredArgument;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.RunsafeLocation;
import no.runsafe.framework.minecraft.RunsafeWorld;
import org.bukkit.entity.TNTPrimed;

import java.util.Map;

public class Nuke extends PlayerCommand
{
	public Nuke()
	{
		super(
			"nuke", "Nukes in a certain radius", "runsafe.toybox.nuke",
			new RequiredArgument("radius"), new RequiredArgument("power")
		);
	}

	@Override
	public String OnExecute(IPlayer executor, Map<String, String> parameters)
	{
		IWorld world = executor.getWorld();
		int radius = Integer.valueOf(parameters.get("radius"));
		int power = Integer.valueOf(parameters.get("power"));
		RunsafeLocation location = executor.getLocation();

		location.decrementX(radius);
		location.decrementZ(radius);

		radius = radius * 2;

		int current = 0;

		while (current < radius)
		{
			int subCurrent = 0;
			while (subCurrent < radius)
			{
				TNTPrimed tnt = ((RunsafeWorld)world).spawn(location, TNTPrimed.class);
				tnt.setIsIncendiary(true);
				tnt.setYield(power);
				location.incrementZ(1);
				subCurrent++;
			}
			location.incrementX(1);
			location.decrementZ(radius);
			current++;
		}

		return null;
	}
}
