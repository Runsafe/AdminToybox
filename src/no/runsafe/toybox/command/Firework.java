package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.minecraft.entity.ProjectileEntity;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.Map;

public class Firework extends PlayerCommand
{
	public Firework()
	{
		super("firework", "Sets off a firework", "runsafe.toybox.firework");
	}

	@Override
	public String OnExecute(RunsafePlayer executor, Map<String, String> parameters)
	{
		executor.Fire(ProjectileEntity.Firework);
		return null;
	}
}
