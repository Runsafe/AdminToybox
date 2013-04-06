package no.runsafe.toybox.command;

import no.runsafe.framework.command.player.PlayerCommand;
import no.runsafe.framework.server.RunsafeServer;
import no.runsafe.framework.server.player.RunsafePlayer;

import java.util.HashMap;

public class Mount extends PlayerCommand
{
	public Mount()
	{
		super("mount", "Mounts you on the given player or entity ID", "runsafe.toybox.mount", "target");
	}

	@Override
	public String OnExecute(RunsafePlayer executor, HashMap<String, String> parameters)
	{
		RunsafePlayer player = RunsafeServer.Instance.getOnlinePlayer(executor, parameters.get("target"));
		if (player == null)
		{
			try
			{
				executor.getWorld().getEntityById(Integer.parseInt(parameters.get("target"))).setPassenger(executor);
				return null;
			}
			catch (Exception e)
			{
				return "Unable to find target..";
			}
		}
		player.setPassenger(executor);
		return null;
	}
}
