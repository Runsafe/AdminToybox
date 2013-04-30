package no.runsafe.toybox.events;

import no.runsafe.framework.event.block.IEntityBlockForm;
import no.runsafe.framework.server.event.block.RunsafeEntityBlockFormEvent;
import no.runsafe.toybox.handlers.CarePackageHandler;

public class EntityBlockFormEvent implements IEntityBlockForm
{
	public EntityBlockFormEvent(CarePackageHandler handler)
	{
		this.handler = handler;
	}

	@Override
	public void OnEntityBlockForm(RunsafeEntityBlockFormEvent event)
	{
		int entityID = event.getEntity().getEntityId();
		if (this.handler.CheckDrop(entityID))
			this.handler.ProcessDrop(entityID, event.getBlock());
	}

	private CarePackageHandler handler;
}
