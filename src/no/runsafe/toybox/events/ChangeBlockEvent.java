package no.runsafe.toybox.events;

import no.runsafe.framework.event.entity.IEntityChangeBlockEvent;
import no.runsafe.framework.output.IOutput;
import no.runsafe.framework.server.block.RunsafeBlock;
import no.runsafe.framework.server.entity.RunsafeLivingEntity;
import no.runsafe.framework.server.material.RunsafeMaterial;
import no.runsafe.toybox.handlers.CarePackageHandler;

public class ChangeBlockEvent implements IEntityChangeBlockEvent
{
	public ChangeBlockEvent(CarePackageHandler handler, IOutput output)
	{
		this.handler = handler;
		this.output = output;
	}

	@Override
	public void OnEntityChangeBlockEvent(RunsafeLivingEntity entity, RunsafeBlock block, RunsafeMaterial material)
	{
		this.output.write("Block change event fired: " + block.getTypeId());
		Integer entityID = entity.getEntityId();
		if (this.handler.CheckDrop(entityID))
		{
			this.output.write("CHEST DETECTED");
			this.handler.ProcessDrop(entityID, block);
		}
	}

	private CarePackageHandler handler;
	private IOutput output;
}
