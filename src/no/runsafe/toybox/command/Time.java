package no.runsafe.toybox.command;

import no.runsafe.framework.api.IUniverseManager;
import no.runsafe.framework.api.IWorld;
import no.runsafe.framework.api.command.ExecutableCommand;
import no.runsafe.framework.api.command.ICommandExecutor;
import no.runsafe.framework.api.command.argument.AutoWorldArgument;
import no.runsafe.framework.api.command.argument.EnumArgument;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.log.IConsole;
import no.runsafe.framework.api.player.IPlayer;

public class Time extends ExecutableCommand
{
	public Time(IConsole console, IUniverseManager manager)
	{
		super("time", "Change the time in a world", "runsafe.toybox.time", new EnumArgument("time", WellKnownTimes.values(), true), new AutoWorldArgument());
		this.console = console;
		this.manager = manager;
	}

	@Override
	public String OnExecute(ICommandExecutor executor, IArgumentList parameters)
	{
		IWorld target = parameters.getWorld("world");

		if (target == null)
			return "You must specify a world from the console!";

		WellKnownTimes time = WellKnownTimes.valueOf(parameters.get("time"));
		target.setTime(time.getTime());
		console.logInformation("Time in world %s set to %s by %s", target.getName(), time, executor.getName());
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
	private final IUniverseManager manager;
}
