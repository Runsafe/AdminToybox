package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.EntityType;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.WholeNumber;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.minecraft.RunsafeEntityType;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.toybox.handlers.MobDropHandler;

public class MobDrop extends PlayerCommand
{
	public MobDrop(MobDropHandler handler)
	{
		super(
			"mobdrop", "Drops a block full of mobs", "runsafe.toybox.mobdrop",
			new EntityType(MOB_TYPE).require(),
			new WholeNumber(AMOUNT).require()
		);
		this.handler = handler;
	}

	private static final String MOB_TYPE = "mobType";
	private static final String AMOUNT = "amount";

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		handler.createMobDropper(
			executor.getLocation(),
			(RunsafeEntityType) parameters.getValue(MOB_TYPE),
			(Integer) parameters.getValue(AMOUNT)
		);
		return null;
	}

	private final MobDropHandler handler;
}
