package no.runsafe.toybox.command;

import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.WholeNumber;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.entity.IExplosive;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.entity.ProjectileEntity;

public class Nuke extends PlayerCommand
{
	public Nuke()
	{
		super(
			"nuke", "Nukes in a certain radius", "runsafe.toybox.nuke",
			new WholeNumber(RADIUS).require(),
			new WholeNumber(POWER).require()
		);
	}

	private static final String RADIUS = "radius";
	private static final String POWER = "power";

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		int radius = parameters.getRequired(RADIUS);
		if (radius > 15)
			return "Max radius size: 15";

		int power = parameters.getRequired(POWER);
		if (power > 255)
			return "&cMax power: 255";

		ILocation location = executor.getLocation();
		if (location == null)
			return "&cInvalid location.";

		location.decrementX(radius);
		location.decrementZ(radius);

		radius = radius * 2;

		int current = 0;

		while (current < radius)
		{
			int subCurrent = 0;
			while (subCurrent < radius)
			{
				IExplosive tnt = (IExplosive) ProjectileEntity.PrimedTNT.spawn(location);
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
