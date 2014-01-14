package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.entity.ProjectileEntity;

public class Fireball extends PlayerCommand
{
	public Fireball()
	{
		super("fireball", "Fires a fireball", "runsafe.toybox.fireball");
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		executor.Fire(ProjectileEntity.SmallFireball);
		return null;
	}
}
