package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.ExecutableCommand;
import no.runsafe.framework.api.command.ICommandExecutor;
import no.runsafe.framework.api.command.argument.EnumArgument;
import no.runsafe.framework.api.command.argument.WorldArgument;
import no.runsafe.framework.api.log.IDebug;

import java.util.Map;

public class Time extends ExecutableCommand
{
	public Time(IDebug debug)
	{
		super("time", "Change the time in a world", "runsafe.toybox.time", new WorldArgument(true), new EnumArgument("time", WellKnownTimes.values(), true));
		this.debug = debug;
	}

	@Override
	public String OnExecute(ICommandExecutor executor, Map<String, String> parameters)
	{
		int time = WellKnownTimes.valueOf(parameters.get("time")).getTime();
		debug.debugFine("Setting time for %s to %s..", parameters.get("world"), time);
		return null;
	}

	public enum WellKnownTimes
	{
		Morning(0);

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

	private final IDebug debug;
}
