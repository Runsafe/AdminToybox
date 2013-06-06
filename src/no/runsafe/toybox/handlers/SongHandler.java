package no.runsafe.toybox.handlers;

import no.runsafe.framework.minecraft.Sound;
import no.runsafe.framework.output.IOutput;
import no.runsafe.framework.server.RunsafeLocation;
import no.runsafe.framework.server.RunsafeWorld;
import no.runsafe.framework.timer.IScheduler;
import no.runsafe.toybox.Plugin;

import javax.sound.midi.*;
import java.io.File;

public class SongHandler
{
	public SongHandler(Plugin adminToybox, IOutput output, IScheduler scheduler)
	{
		this.output = output;
		this.scheduler = scheduler;
		this.path = String.format("plugins/%s/songs/", adminToybox.getName());

		if (!new File(this.path).mkdirs())
			this.output.warning("Failed to create song directory at: " + this.path);
	}

	private boolean songExists(String song)
	{
		return new File(this.path + song).exists();
	}

	public boolean playSong(String song, final RunsafeLocation location, final float volume)
	{
		if (this.songExists(song))
		{
			try
			{
				final RunsafeWorld world = location.getWorld();
				Sequence sequence = MidiSystem.getSequence(new File(this.path + song));

				int trackNumber = 0;
				for (Track track : sequence.getTracks())
				{
					trackNumber++;
					this.output.writeColoured("Track %s has size %s", trackNumber, track.size());

					for (int i = 0; i < track.size(); i++)
					{
						StringBuilder output = new StringBuilder();
						MidiEvent event = track.get(i);
						MidiMessage message = event.getMessage();

						long tick = event.getTick();
						output.append("@").append(tick).append(" ");

						if (message instanceof ShortMessage)
						{
							ShortMessage shortMessage = (ShortMessage) message;
							output.append("Channel: ").append(shortMessage.getChannel()).append(" ");

							output.append(shortMessage.getCommand()).append(" - ");

							if (shortMessage.getCommand() == NOTE_ON)
							{
								int key = shortMessage.getData1();
								int octave = (key / 12) - 1;
								final int note = key % 12;
								int velocity = shortMessage.getData2();

								this.scheduler.startSyncTask(new Runnable() {
									@Override
									public void run()
									{
										world.playSound(location, Sound.NOTE_PIANO, volume, (float) Math.pow(2.0, ((double) NOTE_PITCH[note] - 12.0) / 12.0));
									}
								}, tick / 100);

								output.append("Note on, ");
								output
										.append(NOTE_NAMES[note]).append(octave)
										.append(" key=").append(key)
										.append(" velocity=").append(velocity);
							}
							else
							{
								output.append("Command: ").append(shortMessage.getCommand());
								continue;
							}
						}
						else
						{
							output.append("Other: ").append(message.getClass());
						}
						this.output.write(output.toString());
					}
				}
				return true;
			}
			catch (Exception e)
			{
				return false;
			}
		}
		return false;
	}

	private String path;
	private IOutput output;
	private static final int NOTE_ON = 0x90;
	private static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
	private static final int[] NOTE_PITCH = {6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
	private IScheduler scheduler;
}
