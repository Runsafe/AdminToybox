package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.ExecutableCommand;
import no.runsafe.framework.api.command.ICommandExecutor;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.ListOf;
import no.runsafe.framework.api.command.argument.Player;
import no.runsafe.framework.api.player.IPlayer;

import java.util.List;

public class Kill extends ExecutableCommand
{
	public Kill()
	{
		super("kill", "Kills the targeted player", "runsafe.toybox.kill", new ListOf<Player.Online.Required>(new Player.Online.Required()));
	}

	@Override
	public String OnExecute(ICommandExecutor executor, IArgumentList parameters)
	{
		List<IPlayer> players = parameters.getValue("player");
		if (players == null)
			return null;
		if (executor instanceof IPlayer)
		{
			IPlayer executingPlayer = (IPlayer) executor;
			if (players.isEmpty())
			{
				return killPlayer(executingPlayer);
			}
			if (!executingPlayer.hasPermission("runsafe.toybox.kill.others"))
				return "&cYou do not have permission to kill other players like this.";
		}
		else
		{
			if (players.isEmpty())
				return "&cPlease provide some players you wish to kill.";
		}
		for (IPlayer target : players)
		{
			if (target == null)
				continue;

			if (target.isOnline())
				killPlayer(target);
		}
		return "&cDeath shall find those you seek quickly.";
	}

	private String killPlayer(IPlayer victim)
	{
		victim.explode(0, false, false);
		victim.damage(500.0D);
		return "&fWell, if you insist.";
	}
}
