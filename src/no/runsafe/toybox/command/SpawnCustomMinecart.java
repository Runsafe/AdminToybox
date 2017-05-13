package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.WholeNumber;
import no.runsafe.framework.api.command.argument.ByteValue;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.entity.IEntity;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.internal.wrapper.ObjectUnwrapper;
import no.runsafe.framework.minecraft.entity.PassiveEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Minecart;
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
		//Create minecart
		IEntity minecartEntity = PassiveEntity.Minecart.spawn(executor.getLocation());
		CraftEntity craftEntity = ObjectUnwrapper.convert(minecartEntity);
		Minecart ema = (Minecart) craftEntity.getHandle();

		//Create block in minecart
		if(parameters.getValue("id") == null)
			return null;
		MaterialData minecartBlock = new MaterialData(
				(Integer) parameters.getValue(BLOCK_ID),
				(Byte) parameters.getValue(BLOCK_DATA)
		);
		ema.setDisplayBlock(minecartBlock);

		//Set block offset
		if(parameters.getValue(BLOCK_OFFSET) == null)
			return null;
		ema.setDisplayBlockOffset((Integer) parameters.getValue(BLOCK_OFFSET));

		return null;
	}
}
