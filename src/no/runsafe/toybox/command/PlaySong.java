package no.runsafe.toybox.command;

import no.runsafe.framework.command.player.PlayerCommand;
import no.runsafe.framework.server.player.RunsafePlayer;
import no.runsafe.toybox.handlers.SongHandler;

import java.util.HashMap;

public class PlaySong extends PlayerCommand
{
	public PlaySong(SongHandler songHandler)
	{
		super("playsong", "Plays a song at your location", "runsafe.toybox.playsong", "song", "radius");
		this.songHandler = songHandler;
	}

	@Override
	public String OnExecute(RunsafePlayer executor, HashMap<String, String> parameters)
	{
		return (this.songHandler.playSong(parameters.get("song"), executor.getLocation(), Float.valueOf(parameters.get("radius"))) ? "&2Playing song." : "&cSong not found.");
	}

	private SongHandler songHandler;
}
