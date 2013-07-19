package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.minecraft.entity.ProjectileEntity;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.Map;

public class Dynamite extends PlayerCommand
{
	public Dynamite()
	{
		super("dynamite", "Fires a primed TNT", "runsafe.toybox.dynamite");
	}

	@Override
	public String OnExecute(RunsafePlayer executor, Map<String, String> parameters)
	{
		executor.Launch(ProjectileEntity.PrimedTNT);
		return null;
	}
}
