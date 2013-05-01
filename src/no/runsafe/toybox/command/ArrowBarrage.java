package no.runsafe.toybox.command;

import no.runsafe.framework.command.player.PlayerCommand;
import no.runsafe.framework.server.entity.ProjectileEntity;
import no.runsafe.framework.server.entity.RunsafeEntityType;
import no.runsafe.framework.server.player.RunsafePlayer;

import java.util.HashMap;

public class ArrowBarrage extends PlayerCommand
{
	public ArrowBarrage()
	{
		super("arrowbarrage", "Shoots a barrage of arrows", "runsafe.toybox.arrowbarrage", "radius");
	}

	@Override
	public String OnExecute(RunsafePlayer executor, HashMap<String, String> parameters)
	{
		int radius = Integer.valueOf(parameters.get("radius"));
		executor.Launch(ProjectileEntity.Arrow);
		return null;
	}
}
