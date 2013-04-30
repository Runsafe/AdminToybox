package no.runsafe.toybox.events;

import no.runsafe.framework.event.entity.IEntityChangeBlockEvent;
import no.runsafe.framework.output.IOutput;
import no.runsafe.framework.server.block.RunsafeBlock;
import no.runsafe.framework.server.event.entity.RunsafeEntityChangeBlockEvent;
import no.runsafe.toybox.handlers.CarePackageHandler;

public class ChangeBlockEvent implements IEntityChangeBlockEvent
{
	public ChangeBlockEvent(CarePackageHandler handler, IOutput output)
	{
		this.handler = handler;
		this.output = output;
	}

	@Override
	public void OnEntityChangeBlockEvent(RunsafeEntityChangeBlockEvent event)
	{
		RunsafeBlock changedBlock = event.getBlock();
		RunsafeBlock chest = changedBlock.getWorld().getBlockAt(changedBlock.getLocation());
		Integer entityID = event.getEntity().getEntityId();

		this.output.write("Block type: " + chest.getTypeId());
		this.output.write("Block class: " + chest.getClass().getName());

		if (this.handler.CheckDrop(entityID))
			this.handler.ProcessDrop(entityID, chest);
	}

	private CarePackageHandler handler;
	private IOutput output;
}
