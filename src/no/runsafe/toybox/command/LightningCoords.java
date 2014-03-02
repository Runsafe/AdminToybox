package no.runsafe.toybox.command;

import no.runsafe.framework.api.IWorld;
import no.runsafe.framework.api.command.IBranchingExecution;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.WholeNumber;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;

public class LightningCoords extends PlayerCommand implements IBranchingExecution
{
	public LightningCoords()
	{
		super(
			"lightning", "Fires lightning at a coordinate", "runsafe.toybox.lightning",
			new WholeNumber("x").require(), new WholeNumber("y").require(), new WholeNumber("z").require()
		);
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		Float x = parameters.getValue("x");
		Float y = parameters.getValue("y");
		Float z = parameters.getValue("z");
		assert (x != null && y != null && z != null);
		IWorld world = executor.getWorld();
		if (world != null)
			world.strikeLightning(world.getLocation((double) x, (double) y, (double) z));
		return null;
	}
}
