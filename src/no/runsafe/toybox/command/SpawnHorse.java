package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.BooleanArgument;
import no.runsafe.framework.api.command.argument.Enumeration;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.WholeNumber;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.entity.animals.horses.HorseColour;
import no.runsafe.framework.minecraft.entity.animals.horses.HorseStyle;
import no.runsafe.toybox.horses.HorseSpawner;

public class SpawnHorse extends PlayerCommand
{
	public SpawnHorse(HorseSpawner horseSpawner)
	{
		super(
			"spawnhorse", "Spawns a horse", "runsafe.toybox.spawnmob",
			new Enumeration(COLOUR, HorseColour.values()),
			new Enumeration(STYLE, HorseStyle.values()),
			new BooleanArgument(TAME).withDefault(true),
			new WholeNumber(COUNT).withDefault(1)
		);
		this.horseSpawner = horseSpawner;
	}

	private static final String COUNT = "count";
	private static final String TAME = "tame";
	private static final String COLOUR = "colour";
	private static final String STYLE = "style";

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		HorseColour colour = parameters.getValue(COLOUR);
		HorseStyle style = parameters.getValue(STYLE);

		Integer count = parameters.getValue(COUNT);
		if (count == null || count > 255)
			return "&cMaximum amount: 255";

		for (int i = 0; i < count; ++i)
			this.horseSpawner.spawnHorse(executor.getLocation(), colour, style, parameters.getValue(TAME));

		return null;
	}

	private final HorseSpawner horseSpawner;
}
