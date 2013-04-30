package no.runsafe.toybox.command;

import no.runsafe.framework.command.player.PlayerCommand;
import no.runsafe.framework.server.player.RunsafePlayer;
import no.runsafe.toybox.handlers.MobDropHandler;

import java.util.HashMap;

public class MobDrop extends PlayerCommand
{
	public MobDrop(MobDropHandler handler)
	{
		super("mobdrop", "Drops a block full of mobs", "runsafe.toybox.mobdrop", "mobType", "amount");
		this.handler = handler;
	}

	@Override
	public String OnExecute(RunsafePlayer executor, HashMap<String, String> parameters)
	{
		handler.createMobDropper(executor.getLocation(), parameters.get("mobType"), Integer.valueOf(parameters.get("amount")));
		return null;
	}

	private MobDropHandler handler;
}
