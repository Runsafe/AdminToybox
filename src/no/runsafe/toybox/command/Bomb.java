package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.minecraft.entity.ProjectileEntity;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

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
