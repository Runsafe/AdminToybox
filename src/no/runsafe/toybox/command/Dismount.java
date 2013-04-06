package no.runsafe.toybox.command;

import no.runsafe.framework.command.player.PlayerCommand;
import no.runsafe.framework.server.entity.RunsafeEntity;
import no.runsafe.framework.server.player.RunsafePlayer;

import java.util.HashMap;

public class Dismount extends PlayerCommand
{
	public Dismount()
	{
		super("dismount", "Dismounts you", "runsafe.toybox.dismount");
	}

	@Override
	public String OnExecute(RunsafePlayer executor, HashMap<String, String> parameters)
	{
		RunsafeEntity vehicle = executor.getVehicle();
		if(vehicle != null)
			vehicle.eject();
		return null;
	}
}
