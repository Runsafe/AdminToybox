package no.runsafe.toybox.command;

import no.runsafe.framework.api.block.IBlock;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;

public class BiteThere extends PlayerCommand
{
	public BiteThere()
	{
		super(
			"bite", "Bites at the location you are looking", "runsafe.toybox.bite"
		);
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		IBlock target = executor.getTargetBlock();
		if (target == null)
			return "&cNo block in sight";

		target.getWorld().spawnCreature(target.getLocation(), "evocation_fangs");
		return null;
	}
}
