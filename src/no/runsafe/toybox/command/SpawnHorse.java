package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.BooleanArgument;
import no.runsafe.framework.api.command.argument.Enumeration;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.WholeNumber;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.toybox.horses.HorseSpawner;
import no.runsafe.toybox.horses.SpawnableHorseType;
import no.runsafe.toybox.horses.SpawnableHorseVariant;

import java.util.Random;

public class SpawnHorse extends PlayerCommand
{
	public SpawnHorse(HorseSpawner horseSpawner)
	{
		super(
			"spawnhorse", "Spawns a horse", "runsafe.toybox.spawnmob",
			new WholeNumber(COUNT).withDefault(1),
			new BooleanArgument(TAME).withDefault(true),
			new Enumeration(TYPE, SpawnableHorseType.values()),
			new Enumeration(VARIANT, SpawnableHorseVariant.values())
		);
		this.horseSpawner = horseSpawner;
	}

	private static final String COUNT = "count";
	private static final String TAME = "tame";
	private static final String TYPE = "type";
	private static final String VARIANT = "variant";

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		try
		{
			SpawnableHorseType type = parameters.getValue(TYPE);
			if (type == null)
				type = this.getRandomHorseType();
			SpawnableHorseVariant variant = parameters.getValue(VARIANT);
			if (variant == null)
				variant = this.getRandomHorseVariant();

			int count = parameters.getValue(COUNT);
			if (count > 255)
				return "&cMaximum amount: 255";

			for (int i = 0; i < count; ++i)
				this.horseSpawner.spawnHorse(executor.getLocation(), type, variant, parameters.getValue(TAME));
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

	public SpawnableHorseVariant getRandomHorseVariant()
	{
		SpawnableHorseVariant[] values = SpawnableHorseVariant.values();
		return values[this.random.nextInt(values.length)];
	}

	private Random random = new Random();
	private HorseSpawner horseSpawner;
}
