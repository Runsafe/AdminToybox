package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.RequiredArgument;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.Sound;

public class PlaySound extends PlayerCommand
{
	public PlaySound()
	{
		super(
			"playsound", "Plays a sound", "runsafe.toybox.playsound",
			new RequiredArgument(SOUND),
			new RequiredArgument(VOLUME),
			new RequiredArgument(PITCH)
		);
	}

	private static final String SOUND = "sound";
	private static final String VOLUME = "volume";
	private static final String PITCH = "pitch";

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		float volume = Float.valueOf(parameters.get(VOLUME));
		float pitch = Float.valueOf(parameters.get(PITCH));

		Sound sound = Sound.Get(parameters.get(SOUND));
		if (sound == null)
			return "&cThat sound does not exist.";

		executor.getWorld().playSound(executor.getLocation(), sound, volume, pitch);
		return "&2Sound played.";
	}
}
