package no.runsafe.toybox.events;

import no.runsafe.framework.api.event.block.IBlockPlace;
import no.runsafe.framework.minecraft.block.RunsafeBlock;
import no.runsafe.framework.minecraft.block.RunsafeBlockState;
import no.runsafe.framework.minecraft.block.RunsafeSign;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

public class BlockPlace implements IBlockPlace
{
	@Override
	public boolean OnBlockPlace(RunsafePlayer player, RunsafeBlock block)
	{
		RunsafeBlockState blockState = block.getBlockState();
		if (blockState instanceof RunsafeSign)
		{
			RunsafeSign sign = (RunsafeSign) blockState;
			if (sign.getLine(0).equals("[Infinite]"))
				if (!player.hasPermission("runsafe.toybox.infinitedispensers"))
				{
					player.sendColouredMessage(String.format("&cI'm sorry %s, I'm afraid I can't let you do that", player.getName()));
					return false;
				}
		}
		return true;
	}
}
