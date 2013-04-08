package no.runsafe.toybox.command;

import no.runsafe.framework.command.player.PlayerCommand;
import no.runsafe.framework.server.entity.ProjectileEntity;
import no.runsafe.framework.server.player.RunsafePlayer;

import java.util.HashMap;

public class Dynamite extends PlayerCommand
{
	public Dynamite()
	{
		super("dynamite", "Fires a primed TNT", "runsafe.toybox.dynamite");
	}

	@Override
	public String OnExecute(RunsafePlayer executor, HashMap<String, String> parameters)
	{
		executor.Launch(ProjectileEntity.PrimedTNT);
		return null;
	}
}
