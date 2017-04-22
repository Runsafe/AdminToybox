package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;

public class PlayEffect extends PlayerCommand
{
	public PlayEffect()
	{
		super("playeffect",
			"Play a custom effect at your location",
			"runsafe.toybox.effects"
		);
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}
}
