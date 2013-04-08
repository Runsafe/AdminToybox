package no.runsafe.toybox.command;

import no.runsafe.framework.command.player.PlayerCommand;
import no.runsafe.framework.server.entity.ProjectileEntity;
import no.runsafe.framework.server.player.RunsafePlayer;

import java.util.HashMap;

public class Firework extends PlayerCommand
{
	public Firework()
	{
		super("firework", "Sets off a firework", "runsafe.toybox.firework");
	}

	@Override
	public String OnExecute(RunsafePlayer executor, HashMap<String, String> parameters)
	{
		executor.Fire(ProjectileEntity.Firework);
		return null;
	}
}
