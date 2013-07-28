package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.RequiredArgument;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.minecraft.Sound;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.Map;

public class PlaySound extends PlayerCommand
{
	public PlaySound()
	{
		super(
			"playsound", "Plays a sound", "runsafe.toybox.playsound",
			new RequiredArgument("sound"), new RequiredArgument("volume"), new RequiredArgument("pitch")
		);
	}

	@Override
	public String OnExecute(RunsafePlayer executor, Map<String, String> parameters)
	{
		float volume = Float.valueOf(parameters.get("volume"));
		float pitch = Float.valueOf(parameters.get("pitch"));

		Sound sound = Sound.Get(parameters.get("sound"));
		if (sound == null)
			return "&cThat sound does not exist.";

		executor.getWorld().playSound(executor.getLocation(), sound, volume, pitch);
		return "&2Sound played.";
	}
}
