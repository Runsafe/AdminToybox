package no.runsafe.toybox.command;

import no.runsafe.framework.api.IServer;
import no.runsafe.framework.api.command.ExecutableCommand;
import no.runsafe.framework.api.command.ICommandExecutor;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.PlayerListArgument;
import no.runsafe.framework.api.player.IAmbiguousPlayer;
import no.runsafe.framework.api.player.IPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kill extends ExecutableCommand
{
	public Kill(IServer server)
	{
		super("kill", "Kills the targeted player", "runsafe.toybox.kill", new PlayerListArgument(false));
		this.server = server;
	}

	@Override
	public String OnExecute(ICommandExecutor executor, IArgumentList parameters)
	{
		List<String> hitList = new ArrayList<String>();

		if (executor instanceof IPlayer)
		{
			IPlayer executingPlayer = (IPlayer) executor;
			if (!executingPlayer.hasPermission("runsafe.toybox.kill.others"))
			{
				if (!parameters.containsKey("players"))
				{
					executingPlayer.explode(0, false, false);
					executingPlayer.damage(500.0D);
					return "&fWell, if you insist.";
				}
				return "&cYou do not have permission to kill other players like this.";
			}
			else
			{
				if (!parameters.containsKey("players"))
				{
					executingPlayer.explode(0, false, false);
					executingPlayer.damage(500.0D);
					return "&fWell, if you insist.";
				}
			}
		}
		else
		{
			if (!parameters.containsKey("players"))
				return "&cPlease provide some players you wish to kill.";
		}
		Collections.addAll(hitList, parameters.get("players").split("\\s+"));

		for (String targetName : hitList)
		{
			IPlayer target = server.getPlayer(targetName);

			if (target == null)
				continue;

			if (target instanceof IAmbiguousPlayer)
				return target.toString();

			if (target.isOnline())
			{
				target.explode(0, false, false);
				target.damage(500.0D);
			}
		}
		return "&cDeath shall find those you seek quickly.";
	}

	private final IServer server;
}
