package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.ExecutableCommand;
import no.runsafe.framework.api.command.IBranchingExecution;
import no.runsafe.framework.api.command.ICommandExecutor;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.Player;
import no.runsafe.framework.api.player.IPlayer;

public class LightningPlayer extends ExecutableCommand implements IBranchingExecution
{
	public LightningPlayer()
	{
		super("lightning",
			"Fires lightning at a player",
			"runsafe.toybox.lightning",
			new Player().onlineOnly().require()
		);
	}

	private static final String PLAYER = "player";

	@Override
	public String OnExecute(ICommandExecutor executor, IArgumentList parameters)
	{
		IPlayer player = parameters.getValue(PLAYER);
		if (player == null)
			return "Target player is offline!";
		player.strikeWithLightning(false);
		return "Thy will be done.";
	}
}
