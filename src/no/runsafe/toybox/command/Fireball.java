package no.runsafe.toybox.command;

import no.runsafe.framework.command.player.PlayerCommand;
import no.runsafe.framework.server.entity.ProjectileEntity;
import no.runsafe.framework.server.player.RunsafePlayer;

import java.util.HashMap;

public class Fireball extends PlayerCommand
{
	public Fireball()
	{
		super("fireball", "Fires a fireball", "runsafe.toybox.fireball");
	}

	@Override
	public String OnExecute(RunsafePlayer executor, HashMap<String, String> parameters)
	{
		executor.Fire(ProjectileEntity.SmallFireball);
		return null;
	}
}
