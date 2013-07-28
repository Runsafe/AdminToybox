package no.runsafe.toybox.command;

import no.runsafe.framework.api.IScheduler;
import no.runsafe.framework.api.command.argument.RequiredArgument;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.minecraft.RunsafeLocation;
import no.runsafe.framework.minecraft.entity.RunsafeEntity;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.Map;

public class Bazooka extends PlayerCommand
{
	public Bazooka(IScheduler scheduler)
	{
		super(
			"bazooka", "Fire an entity and make it explode", "runsafe.toybox.bazooka",
			new RequiredArgument("entityName"), new RequiredArgument("delay"), new RequiredArgument("strength")
		);
		this.scheduler = scheduler;
	}

	@Override
	public String OnExecute(RunsafePlayer executor, Map<String, String> parameters)
	{
		final int delay = Integer.parseInt(parameters.get("delay"));
		final float strength = Float.parseFloat(parameters.get("strength"));
		final RunsafeEntity projectile = executor.Launch(parameters.get("entityName"));
		if (projectile != null)
		{
			scheduler.startSyncTask(
				new Runnable()
				{
					@Override
					public void run()
					{
						RunsafeLocation location = projectile.getLocation();
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
