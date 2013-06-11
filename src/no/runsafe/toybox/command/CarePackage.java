package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.minecraft.player.RunsafePlayer;
import no.runsafe.toybox.handlers.CarePackageHandler;

import java.util.HashMap;

public class CarePackage extends PlayerCommand
{
	public CarePackage(CarePackageHandler handler)
	{
		super("carepackage", "Creates a drop-package", "runsafe.toybox.carepackage");
		this.handler = handler;
	}

	@Override
	public String OnExecute(RunsafePlayer executor, HashMap<String, String> parameters)
	{
		this.handler.CreateCarePackage(executor);
		return null;
	}

	private final CarePackageHandler handler;
}
