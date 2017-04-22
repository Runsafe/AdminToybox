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
		super("lightning",
			"Fires lightning at a coordinate",
			"runsafe.toybox.lightning",
			new WholeNumber(X).require(),
			new WholeNumber(Y).require(),
			new WholeNumber(Z).require()
		);
	}

	private static final String X = "x";
	private static final String Y = "y";
	private static final String Z = "z";

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		Integer x = parameters.getValue(X);
		Integer y = parameters.getValue(Z);
		Integer z = parameters.getValue(Z);
		assert (x != null && y != null && z != null);
		IWorld world = executor.getWorld();
		if (world != null)
			world.strikeLightning(world.getLocation(x + 0.5, y + 0.5, z + 0.5));
		return null;
	}
}
