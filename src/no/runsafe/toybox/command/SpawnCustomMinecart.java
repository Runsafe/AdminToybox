package no.runsafe.toybox.command;

import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.command.argument.*;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.entity.PassiveEntity;
import no.runsafe.framework.minecraft.entity.RunsafeMinecart;

public class SpawnCustomMinecart extends PlayerCommand
{
	public SpawnCustomMinecart()
	{
		super("spawncustomminecart",
				"Spawn a custom minecart!",
				"runsafe.toybox.spawnminecart",
				new RequiredArgument("blockName"),
				new WholeNumber("blockOffset").withDefault(8)
		);
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		ILocation location = executor.getLocation();
		if (location == null)
			return "Invalid location.";

		//Create minecart
		RunsafeMinecart minecart = (RunsafeMinecart) PassiveEntity.Minecart.spawn(location);

		//Create block in minecart
		minecart.setDisplayBlock((org.bukkit.Material) parameters.getValue("blockName"));

		//Set block offset
		minecart.setDisplayBlockOffset((Integer) parameters.getValue("blockOffset"));

		return null;
	}
}
