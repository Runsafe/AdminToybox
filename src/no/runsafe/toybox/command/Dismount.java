package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.entity.IEntity;
import no.runsafe.framework.api.player.IPlayer;

public class Dismount extends PlayerCommand
{
	public Dismount()
	{
		super("dismount", "Dismounts you", "runsafe.toybox.dismount");
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		IEntity vehicle = executor.getVehicle();
		if (vehicle != null)
			vehicle.eject();
		return null;
	}
}
