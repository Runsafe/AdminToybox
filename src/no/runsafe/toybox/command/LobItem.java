package no.runsafe.toybox.command;

import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.entity.ProjectileEntity;
import no.runsafe.framework.minecraft.entity.RunsafeEntity;
import no.runsafe.framework.minecraft.entity.RunsafeItem;
import no.runsafe.framework.minecraft.inventory.RunsafeInventory;
import no.runsafe.framework.minecraft.item.meta.RunsafeMeta;

import java.util.List;

public class LobItem extends PlayerCommand
{
	public LobItem()
	{
		super("lob", "Throw an item stack from the chest at your feet", "runsafe.toybox.lob");
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		ILocation location = executor.getLocation();
		if (location != null)
		{
			RunsafeInventory inventory = executor.getInventory();
			List<RunsafeMeta> items = inventory.getContents();

			if (!items.isEmpty())
			{
				RunsafeMeta dropItem = items.get(0);
				inventory.remove(dropItem);
				RunsafeItem item = location.getWorld().dropItem(location, dropItem);
				RunsafeEntity entity = executor.Fire(ProjectileEntity.Snowball);

				entity.setPassenger(item);
			}
		}
		return null;
	}
}
