package no.runsafe.toybox.horses;

public enum SpawnableHorseType
{
	NORMAL("horse"),
	DONKEY("donkey"),
	MULE("mule"),
	UNDEAD("zombie_horse"),
	SKELETAL("skeleton_horse");

	private SpawnableHorseType(String typeName)
	{
		this.typeName = typeName;
	}

	public String getName()
	{
		return typeName;
	}

	private String typeName;
}
