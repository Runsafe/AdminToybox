package no.runsafe.toybox.command;

import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.WholeNumber;
import no.runsafe.framework.api.command.argument.ByteValue;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.entity.PassiveEntity;
import no.runsafe.framework.minecraft.entity.RunsafeMinecart;
import org.bukkit.material.MaterialData;

public class SpawnCustomMinecart extends PlayerCommand
{
	public SpawnCustomMinecart()
	{
		super(
			"spawncustomminecart", "Spawn a custom minecart!", "runsafe.toybox.spawnminecart",
			new WholeNumber(BLOCK_ID),
			new ByteValue(BLOCK_DATA),
			new WholeNumber(BLOCK_OFFSET)
		);
	}

	private static final String BLOCK_ID = "id";
	private static final String BLOCK_DATA = "data";
	private static final String BLOCK_OFFSET = "blockOffset";

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		ILocation location = executor.getLocation();
		if (location == null)
			return "Invalid location.";

		//Create minecart
		RunsafeMinecart minecart = (RunsafeMinecart) PassiveEntity.Minecart.spawn(location);

		//Create block in minecart
		if(parameters.getValue("id") == null)
			return null;
		MaterialData minecartBlock = new MaterialData(
				(Integer) parameters.getValue(BLOCK_ID),
				(Byte) parameters.getValue(BLOCK_DATA)
		);
		minecart.setDisplayBlock(minecartBlock);

		//Set block offset
		minecart.setDisplayBlockOffset((Integer) parameters.getValue(BLOCK_OFFSET));

		return null;
	}
}
