package no.runsafe.toybox.command;

import no.runsafe.framework.api.IWorld;
import no.runsafe.framework.api.command.ExecutableCommand;
import no.runsafe.framework.api.command.ICommandExecutor;
import no.runsafe.framework.api.command.argument.EnumArgument;
import no.runsafe.framework.api.command.argument.WorldArgument;
import no.runsafe.framework.api.log.IConsole;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.internal.Multiverse;

import java.util.Map;

public class Time extends ExecutableCommand
{
	public Time(IConsole console)
	{
		super("time", "Change the time in a world", "runsafe.toybox.time", new EnumArgument("time", WellKnownTimes.values(), true), new WorldArgument(false));
		this.console = console;
	}

	@Override
	public String OnExecute(ICommandExecutor executor, Map<String, String> parameters)
	{
		IWorld target = null;
		if (parameters.containsKey("world"))
			target = Multiverse.Get().getWorld(parameters.get("world"));

		if (target == null && executor instanceof IPlayer)
			target = ((IPlayer) executor).getWorld();


		if (target == null)
			return "You must specify a world from the console!";

		int time = WellKnownTimes.valueOf(parameters.get("time")).getTime();
		target.setTime(time);
		console.logInformation("Time in world %s set to %d by %s", target.getName(), time, executor.getName());
		return String.format("Time set for %s", target.getName());
	}

	public enum WellKnownTimes
	{
		Dawn(22000),
		Morning(0),
		Noon(6000),
		Dusk(13000),
		Midnight(18000);

		public int getTime()
		{
			return time;
		}

		private WellKnownTimes(int time)
		{
			this.time = time;
		}

		private final int time;
	}

	private final IConsole console;
}
