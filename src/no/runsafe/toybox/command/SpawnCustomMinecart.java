package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.WholeNumber;
import no.runsafe.framework.api.command.argument.ByteValue;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.internal.wrapper.ObjectUnwrapper;
import org.bukkit.entity.Minecart;
import org.bukkit.material.MaterialData;

public class SpawnCustomMinecart extends PlayerCommand
{
	public SpawnCustomMinecart()
	{
		super("spawncustomminecart",
				"Spawn a custom minecart!",
				"runsafe.toybox.spawnminecart",
				new WholeNumber("id").require(),
				new ByteValue("data"),
				new WholeNumber("blockOffset")
		);
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		//Get input
		int blockID = parameters.getValue("id");

		byte blockData = 0;
		if(parameters.getValue("data") != null)
			blockData = parameters.getValue("data");

		int blockOffset = 8;
		if(parameters.getValue("blockOffset") != null)
			blockOffset = parameters.getValue("blockOffset");

		//Create minecart
		Minecart ema = ((org.bukkit.World) ObjectUnwrapper.convert(executor.getWorld())).spawn(
				(org.bukkit.Location) ObjectUnwrapper.convert(executor.getLocation()),
				Minecart.class
		);

		//Create block in minecart
		MaterialData minecartBlock = new MaterialData(blockID, blockData);
		ema.setDisplayBlock(minecartBlock);

		//Set block offset
		ema.setDisplayBlockOffset(blockOffset);

		return null;
	}
}
