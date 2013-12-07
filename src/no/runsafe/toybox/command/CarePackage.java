package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.toybox.handlers.CarePackageHandler;

import java.util.Map;

public class CarePackage extends PlayerCommand
{
	public CarePackage(CarePackageHandler handler)
	{
		super("carepackage", "Creates a drop-package", "runsafe.toybox.carepackage");
		this.handler = handler;
	}

	@Override
	public String OnExecute(IPlayer executor, Map<String, String> parameters)
	{
		this.handler.CreateCarePackage(executor);
		return null;
	}

	private final CarePackageHandler handler;
}
