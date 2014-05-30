package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.ICommandExecutor;
import no.runsafe.framework.api.command.argument.ITabComplete;
import no.runsafe.framework.api.command.argument.IValueExpander;
import no.runsafe.framework.api.command.argument.RequiredArgument;
import no.runsafe.framework.api.player.IPlayer;

import javax.annotation.Nullable;
import java.util.List;

public class WorldEffectArgument extends RequiredArgument implements ITabComplete, IValueExpander
{
	public WorldEffectArgument()
	{
		super("effect");
	}

	@Override
	public List<String> getAlternatives(IPlayer executor, String partial)
	{
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Nullable
	@Override
	public String expand(ICommandExecutor context, @Nullable String value)
	{
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}
}
