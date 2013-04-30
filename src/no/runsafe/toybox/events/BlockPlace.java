package no.runsafe.toybox.events;

import no.runsafe.framework.event.block.IBlockPlace;
import no.runsafe.framework.server.block.RunsafeBlock;
import no.runsafe.framework.server.block.RunsafeBlockState;
import no.runsafe.framework.server.block.RunsafeSign;
import no.runsafe.framework.server.player.RunsafePlayer;

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
