package no.runsafe.toybox.command;

import no.runsafe.framework.command.player.PlayerCommand;
import no.runsafe.framework.server.RunsafeLocation;
import no.runsafe.framework.server.entity.RunsafeEntity;
import no.runsafe.framework.server.player.RunsafePlayer;
import no.runsafe.framework.timer.IScheduler;

import java.util.HashMap;

public class Bazooka extends PlayerCommand
{
	public Bazooka(IScheduler scheduler)
	{
		super("bazooka", "Fire an entity and make it explode", "runsafe.toybox.bazooka", "entityName", "delay", "strength");
		this.scheduler = scheduler;
	}

	@Override
	public String OnExecute(RunsafePlayer executor, HashMap<String, String> parameters)
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

	private IScheduler scheduler;
}
