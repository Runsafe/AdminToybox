package no.runsafe.toybox.command;

import net.minecraft.server.v1_6_R1.EntityCow;
import net.minecraft.server.v1_6_R1.World;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.minecraft.player.RunsafePlayer;
import no.runsafe.toybox.horses.SpawnableHorse;
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
		int type = (arguments.length > 0) ? Integer.valueOf(arguments[0]) : this.random.nextInt(5);
		int variant = (arguments.length > 1) ? Integer.valueOf(arguments[1]) : this.random.nextInt(7);
		int markings = (arguments.length > 2) ? Integer.valueOf(arguments[2]) : this.random.nextInt(5);

		if (type > 0)
			variant = markings = 0;

		if (type < 0 || type > 4)
			return "&cInvalid horse type: " + type;

		if (variant < 0 || variant > 6)
			return "&cInvalid variant value: " + variant;

		if (markings < 0 || markings > 4)
			return "&cInvalid markings values: " + markings;

		int count = Integer.valueOf(parameters.get("count"));

		World world = ((CraftWorld) executor.getWorld().getRaw()).getHandle();
		Location playerLocation = executor.getLocation().getRaw();
		for (int i = 0; i < count; ++i)
		{
			//SpawnableHorse horse = new SpawnableHorse(world, type, variant, markings);
			//horse.teleportTo(playerLocation, false);
			//horse.setPosition(playerLocation.getX(), playerLocation.getY(), playerLocation.getZ());
			//world.addEntity(horse);
			EntityCow cow = new EntityCow(world);
			cow.teleportTo(playerLocation, false);
		}
		return null;
	}

	private Random random = new Random();
}
