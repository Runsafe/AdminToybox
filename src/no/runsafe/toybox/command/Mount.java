package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.Player;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;

public class Mount extends PlayerCommand
{
	public Mount()
	{
		super(
			"mount", "Mounts you on the given player or entity ID", "runsafe.toybox.mount",
			new Player.Online("target").require()
		);
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		IPlayer target = parameters.getValue("target");
		if (target == null)
			return "&cUnable to find target..";

		if (target.getName().equalsIgnoreCase(executor.getName()))
			return "&cYou cannot mount yourself, this would end badly.";

		target.setPassenger(executor);
		return "&aMounted " + target.getName() + ".";
	}
}
