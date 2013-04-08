package no.runsafe.toybox.command;

import no.runsafe.framework.command.player.PlayerCommand;
import no.runsafe.framework.server.entity.ProjectileEntity;
import no.runsafe.framework.server.player.RunsafePlayer;

import java.util.HashMap;

public class Bomb extends PlayerCommand
{
	public Bomb()
	{
		super("bomb", "Fires a wither bomb", "runsafe.toybox.bomb");
	}

	@Override
	public String OnExecute(RunsafePlayer executor, HashMap<String, String> parameters)
	{
		executor.Fire(ProjectileEntity.WitherSkull);
		return null;
	}
}
