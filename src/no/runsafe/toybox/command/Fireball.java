package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.minecraft.entity.ProjectileEntity;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

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
