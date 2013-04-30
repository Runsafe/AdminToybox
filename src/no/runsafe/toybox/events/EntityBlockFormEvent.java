package no.runsafe.toybox.events;

import no.runsafe.framework.event.block.IEntityBlockForm;
import no.runsafe.framework.output.IOutput;
import no.runsafe.framework.server.event.block.RunsafeEntityBlockFormEvent;
import no.runsafe.toybox.handlers.CarePackageHandler;

public class EntityBlockFormEvent implements IEntityBlockForm
{
	public EntityBlockFormEvent(CarePackageHandler handler, IOutput output)
	{
		this.handler = handler;
		this.output = output;
	}

	@Override
	public void OnEntityBlockForm(RunsafeEntityBlockFormEvent event)
	{
		int entityID = event.getEntity().getEntityId();
		this.output.write("Block form event: " + event.getEventName());

		if (this.handler.CheckDrop(entityID))
		{
			this.output.write("This matched our drop");
			this.handler.ProcessDrop(entityID, event.getBlock());
		}
	}

	private CarePackageHandler handler;
	private IOutput output;
}
