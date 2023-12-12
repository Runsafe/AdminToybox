package no.runsafe.toybox.events;

import no.runsafe.framework.api.block.IBlock;
import no.runsafe.framework.api.event.block.ISignChange;
import no.runsafe.framework.api.player.IPlayer;

public class SignChange implements ISignChange
{
	@Override
	public boolean OnSignChange(IPlayer player, IBlock runsafeBlock, String[] strings)
	{
		if (!player.hasPermission("runsafe.admintoybox.signcolour"))
			return true;

		strings[0] = strings[0].replace("&", "§");
		strings[1] = strings[1].replace("&", "§");
		strings[2] = strings[2].replace("&", "§");
		strings[3] = strings[3].replace("&", "§");
		return true;
	}
}
