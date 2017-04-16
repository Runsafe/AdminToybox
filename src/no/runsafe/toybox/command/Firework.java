package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.entity.ProjectileEntity;

public class Firework extends PlayerCommand
{
	public Firework()
	{
		super("firework",
			"Sets off a firework",
			"runsafe.toybox.firework"
		);
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		executor.Fire(ProjectileEntity.Firework);
		return null;
	}
}
