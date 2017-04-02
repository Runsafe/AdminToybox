package no.runsafe.toybox.command;

import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.EntityMinecartAbstract;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.RequiredArgument;
import no.runsafe.framework.api.command.argument.WholeNumber;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.entity.IEntity;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.internal.wrapper.ObjectUnwrapper;
import no.runsafe.framework.minecraft.entity.PassiveEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;

public class SpawnCustomMinecart extends PlayerCommand
{
	public SpawnCustomMinecart()
	{
		super("spawncustomminecart",
				"Spawn a custom minecart!",
				"runsafe.toybox.spawnminecart",
				new RequiredArgument("block"),
				new WholeNumber("blockOffset")
		);
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		//Get input
		String blockName = parameters.getValue("block");
		blockName.toLowerCase();

		int blockOffset = 8;
		if(parameters.getValue("blockOffset") != null)
			blockOffset = parameters.getValue("blockOffset");

		//Create minecart
		IEntity minecartEntity = PassiveEntity.Minecart.spawn(executor.getLocation());
		CraftEntity craftEntity = ObjectUnwrapper.convert(minecartEntity);
		EntityMinecartAbstract ema = (EntityMinecartAbstract) craftEntity.getHandle();

		//Create block in minecart
		Block minecartBlock = Block.getByName(blockName);
		ema.setDisplayBlock(minecartBlock.getBlockData());

		//Set block offset
		ema.SetDisplayBlockOffset(blockOffset);

		return null;
	}
}
