package no.runsafe.toybox.command;

import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.entity.IEnderDragon;
import no.runsafe.framework.api.entity.IEntity;
import no.runsafe.framework.api.entity.ILivingEntity;
import no.runsafe.framework.api.entity.monsters.IWitherBoss;
import no.runsafe.framework.api.player.IPlayer;

import java.util.List;

public class KillBoss extends PlayerCommand
{
	public KillBoss()
	{
		super(
			"killboss", "Kills all boss mobs around the player.", "runsafe.toybox.killboss"
		);
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		ILocation playerLocation = executor.getLocation();
		if (playerLocation == null)
			return "&cInvalid location.";

		int numberKilled = 0;
		List<IEntity> entities = playerLocation.getWorld().getEntities();

		for (IEntity entity : entities)
		{
			if (!(entity instanceof ILivingEntity) || entity instanceof IPlayer)
				continue;

			if (!(entity instanceof IEnderDragon) && !(entity instanceof IWitherBoss))
				continue;

			if (playerLocation.distance(entity.getLocation()) > 300)
				continue;

			((ILivingEntity) entity).setHealth(0);
			numberKilled += 1;
		}

		return String.format("&aKilled %d boss mobs.", numberKilled);
	}
}
