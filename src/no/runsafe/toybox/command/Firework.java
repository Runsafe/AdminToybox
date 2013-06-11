package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.minecraft.entity.ProjectileEntity;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

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
