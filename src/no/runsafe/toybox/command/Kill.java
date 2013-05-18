package no.runsafe.toybox.command;

import no.runsafe.framework.command.ExecutableCommand;
import no.runsafe.framework.server.ICommandExecutor;
import no.runsafe.framework.server.RunsafeServer;
import no.runsafe.framework.server.player.RunsafeAmbiguousPlayer;
import no.runsafe.framework.server.player.RunsafePlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Kill extends ExecutableCommand
{
	public Kill()
	{
		super("kill", "Kills the targeted player", "runsafe.toybox.kill");
	}

	@Override
	public String OnExecute(ICommandExecutor executor, HashMap<String, String> parameters)
	{
		return null;
	}

	@Override
	public String OnExecute(ICommandExecutor executor, HashMap<String, String> parameters, String[] arguments)
	{
		List<String> hitList = new ArrayList<String>();

		if (executor instanceof RunsafePlayer)
		{
			RunsafePlayer executingPlayer = (RunsafePlayer) executor;
			if (!executingPlayer.hasPermission("runsafe.toybox.kill.others") || arguments.length == 0)
			{
				executingPlayer.explode(0, false, false);
				executingPlayer.damage(500);
				return "&fWell, if you insist.";
			}
		}
		else
		{
			if (arguments.length == 0)
				return "&cPlease provide some players you wish to kill.";
		}
		Collections.addAll(hitList, arguments);

		for (String targetName : hitList)
		{
			RunsafePlayer target = RunsafeServer.Instance.getPlayer(targetName);

			if (target instanceof RunsafeAmbiguousPlayer)
				return target.toString();

			if (target.isOnline())
			{
				target.explode(0, false, false);
				target.damage(500);
			}
		}
		return "&cDeath shall find those you seek quickly.";
	}
}
