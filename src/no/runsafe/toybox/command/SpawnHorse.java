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
import no.runsafe.toybox.horses.SpawnableHorseType;

import java.util.Random;

public class SpawnHorse extends PlayerCommand
{
	public SpawnHorse(HorseSpawner horseSpawner)
	{
		super(
			"spawnhorse", "Spawns a horse", "runsafe.toybox.spawnmob",
			new Enumeration(TYPE, SpawnableHorseType.values()),
			new WholeNumber(COUNT).withDefault(1),
			new BooleanArgument(TAME).withDefault(true),
			new Enumeration(COLOUR, HorseColour.values()),
			new Enumeration(STYLE, HorseStyle.values())
		);
		this.horseSpawner = horseSpawner;
	}

	private static final String COUNT = "count";
	private static final String TAME = "tame";
	private static final String TYPE = "type";
	private static final String COLOUR = "colour";
	private static final String STYLE = "style";

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		try
		{
			SpawnableHorseType type = parameters.getValue(TYPE);
			if (type == null)
				type = this.getRandomHorseType();

			HorseColour colour = parameters.getValue(COLOUR);
			HorseStyle style = parameters.getValue(STYLE);

			int count = parameters.getValue(COUNT);
			if (count > 255)
				return "&cMaximum amount: 255";

			for (int i = 0; i < count; ++i)
				this.horseSpawner.spawnHorse(executor.getLocation(), type, colour, style, parameters.getValue(TAME));
		}
		catch (IllegalArgumentException exception)
		{
			return "&cThat type of horse does not exist.";
		}

		return null;
	}

	public SpawnableHorseType getRandomHorseType()
	{
		SpawnableHorseType[] values = SpawnableHorseType.values();
		return values[this.random.nextInt(values.length)];
	}

	private Random random = new Random();
	private HorseSpawner horseSpawner;
}
