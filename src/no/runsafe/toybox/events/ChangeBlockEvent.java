package no.runsafe.toybox.events;

import no.runsafe.framework.event.entity.IEntityChangeBlockEvent;
import no.runsafe.framework.output.IOutput;
import no.runsafe.framework.server.RunsafeLocation;
import no.runsafe.framework.server.block.RunsafeBlock;
import no.runsafe.framework.server.event.entity.RunsafeEntityChangeBlockEvent;
import no.runsafe.toybox.handlers.CarePackageHandler;
import org.bukkit.Material;

public class ChangeBlockEvent implements IEntityChangeBlockEvent
{
	public ChangeBlockEvent(CarePackageHandler handler)
	{
		this.handler = handler;
	}

	@Override
	public void OnEntityChangeBlockEvent(RunsafeEntityChangeBlockEvent event)
	{
		Integer entityID = event.getEntity().getEntityId();

		if (this.handler.CheckDrop(entityID))
		{
			RunsafeLocation blockLocation = event.getBlock().getLocation();
			blockLocation.getBlock().setTypeId(Material.CHEST.getId());

			this.handler.ProcessDrop(entityID, blockLocation.getBlock());
			event.setCancelled(true);
		}

	}

	private CarePackageHandler handler;
}
