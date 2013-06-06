package no.runsafe.toybox.handlers;

import no.runsafe.framework.output.IOutput;
import no.runsafe.toybox.Plugin;

import javax.sound.midi.*;
import java.io.File;

public class SongHandler
{
	public SongHandler(Plugin adminToybox, IOutput output)
	{
		this.output = output;
		this.path = String.format("plugins/%s/scripts/", adminToybox.getName());

		if (!new File(this.path).mkdirs())
			this.output.warning("Failed to create song directory at: " + this.path);
	}

	private boolean songExists(String song)
	{
		return new File(this.path + song).exists();
	}

	public boolean playSong(String song)
	{
		if (this.songExists(song))
		{
			try
			{
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

						output.append("@").append(event.getTick()).append(" ");

						if (message instanceof ShortMessage)
						{
							ShortMessage shortMessage = (ShortMessage) message;
							output.append("Channel: ").append(shortMessage.getChannel()).append(" ");

							int command = shortMessage.getCommand();

							if (command == NOTE_ON || command == NOTE_OFF)
							{
								int key = shortMessage.getData1();
								int octave = (key / 12) - 1;
								int note = key % 12;
								int velocity = shortMessage.getData2();

								output
									.append((command == NOTE_ON ? "Note on," : "Note off,"))
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
	private static final int NOTE_OFF = 0x80;
	private static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
}
