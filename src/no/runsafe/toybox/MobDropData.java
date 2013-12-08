package no.runsafe.toybox;

import no.runsafe.framework.api.minecraft.RunsafeEntityType;

public class MobDropData
{
	public MobDropData(RunsafeEntityType entityType, Integer mobAmount)
	{
		this.setEntityType(entityType);
		this.setMobAmount(mobAmount);
	}

	public void setEntityType(RunsafeEntityType entityType)
	{
		this.entityType = entityType;
	}

	public void setMobAmount(Integer mobAmount)
	{
		this.mobAmount = mobAmount;
	}

	public RunsafeEntityType getEntityType()
	{
		return this.entityType;
	}

	public Integer getMobAmount()
	{
		return this.mobAmount;
	}

	private RunsafeEntityType entityType;
	private Integer mobAmount;
}
