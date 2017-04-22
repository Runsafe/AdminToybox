package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.entity.ProjectileEntity;

public class Bomb extends PlayerCommand
{
	public Bomb()
	{
		super("bomb",
			"Fires a wither bomb",
			"runsafe.toybox.bomb"
		);
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		executor.Fire(ProjectileEntity.WitherSkull);
		return null;
	}
}
