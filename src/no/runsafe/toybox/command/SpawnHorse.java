package no.runsafe.toybox.command;

import net.minecraft.server.v1_6_R1.World;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.minecraft.player.RunsafePlayer;
import no.runsafe.toybox.horses.SpawnableHorse;
import no.runsafe.toybox.horses.SpawnableHorseType;
import no.runsafe.toybox.horses.SpawnableHorseVariant;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_6_R1.CraftWorld;

import java.util.HashMap;
import java.util.Random;

public class SpawnHorse extends PlayerCommand
{
	public SpawnHorse()
	{
		super("spawnhorse", "Spawns a horse", "runsafe.toybox.spawnmob", "count");
	}

	@Override
	public String OnExecute(RunsafePlayer executor, HashMap<String, String> parameters)
	{
		return null;
	}

	@Override
	public String OnExecute(RunsafePlayer executor, HashMap<String, String> parameters, String[] arguments)
	{
		SpawnableHorseType type = (arguments.length > 0 ? SpawnableHorseType.valueOf(arguments[0]) : this.getRandomHorseType());
		SpawnableHorseVariant variant = (arguments.length > 1 ? SpawnableHorseVariant.valueOf(arguments[1]) : this.getRandomHorseVariant());

		if (type == null)
			return "&cInvalid horse type.";

		if (variant == null)
			return "&cInvalid horse variant.";

		int count = Integer.valueOf(parameters.get("count"));

		World world = ((CraftWorld) executor.getWorld().getRaw()).getHandle();
		Location playerLocation = executor.getLocation().getRaw();
		for (int i = 0; i < count; ++i)
		{
			SpawnableHorse horse = new SpawnableHorse(world, type, variant);
			horse.setLocation(playerLocation.getX(), playerLocation.getY(), playerLocation.getZ(), playerLocation.getYaw(), playerLocation.getPitch());
			world.addEntity(horse);
			//horse.teleportTo(playerLocation, false);
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
}
