package no.runsafe.toybox.command;

import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.IScheduler;
import no.runsafe.framework.api.command.argument.*;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.entity.IEntity;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.entity.EntityType;

public class Bazooka extends PlayerCommand
{
	public Bazooka(IScheduler scheduler)
	{
		super(
			"bazooka", "Fire an entity and make it explode", "runsafe.toybox.bazooka",
			new EntityTypeArgument(true), new IntegerArgument("delay", true), new FloatArgument("strength", true)
		);
		this.scheduler = scheduler;
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		final Integer delay = parameters.getValue("delay");
		final Float strength = parameters.getValue("strength");
		final IEntity projectile = executor.Launch(EntityType.Get(parameters.get("entityType")));
		if (projectile != null && delay != null && strength != null)
		{
			scheduler.startSyncTask(
				new Runnable()
				{
					@Override
					public void run()
					{
						ILocation location = projectile.getLocation();
						projectile.getWorld().createExplosion(location, strength, false);
						projectile.remove();
					}
				},
				delay
			);
		}
		return null;
	}

	private final IScheduler scheduler;
}
