package no.runsafe.toybox.command;

import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.command.argument.EntityType;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.WholeNumber;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.toybox.handlers.MobDropHandler;

public class MobDrop extends PlayerCommand
{
	public MobDrop(MobDropHandler handler)
	{
		super(
			"mobdrop", "Drops a block full of mobs", "runsafe.toybox.mobdrop",
			new EntityType(MOB_TYPE).require(),
			new WholeNumber(AMOUNT).withDefault(1)
		);
		this.handler = handler;
	}

	private static final String MOB_TYPE = "mobType";
	private static final String AMOUNT = "amount";

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		int amount = parameters.getRequired(AMOUNT);
		if (amount > 255)
			return "&cMaximum amount: 255";

		ILocation location = executor.getLocation();
		if (location == null)
			return "&cInvalid location.";

		handler.createMobDropper(
			executor.getLocation(),
			parameters.getValue(MOB_TYPE),
			amount
		);
		return null;
	}

	private final MobDropHandler handler;
}
