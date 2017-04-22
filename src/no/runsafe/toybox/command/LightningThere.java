package no.runsafe.toybox.command;

import no.runsafe.framework.api.block.IBlock;
import no.runsafe.framework.api.command.IBranchingExecution;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;

public class LightningThere extends PlayerCommand implements IBranchingExecution
{
	public LightningThere()
	{
		super("lightning",
			"Fires lightning at the location you are looking",
			"runsafe.toybox.lightning"
		);
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		IBlock target = executor.getTargetBlock();
		if (target == null)
			return "No block in sight";

		target.getWorld().strikeLightning(target.getLocation());
		return null;
	}
}
