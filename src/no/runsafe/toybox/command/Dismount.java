package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.minecraft.entity.RunsafeEntity;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.Map;

public class Dismount extends PlayerCommand
{
	public Dismount()
	{
		super("dismount", "Dismounts you", "runsafe.toybox.dismount");
	}

	@Override
	public String OnExecute(RunsafePlayer executor, Map<String, String> parameters)
	{
		RunsafeEntity vehicle = executor.getVehicle();
		if (vehicle != null)
			vehicle.eject();
		return null;
	}
}
