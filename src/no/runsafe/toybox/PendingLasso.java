package no.runsafe.toybox;

import no.runsafe.framework.minecraft.entity.RunsafeEntity;

public class PendingLasso
{
	public RunsafeEntity getEntity()
	{
		return entity;
	}

	public void setEntity(RunsafeEntity entity)
	{
		this.entity = entity;
	}

	public boolean hasEntity()
	{
		return entity != null;
	}

	private RunsafeEntity entity;
}
