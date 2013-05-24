package no.runsafe.toybox.command;

import no.runsafe.framework.command.player.PlayerCommand;
import no.runsafe.framework.server.network.RunsafePacketSender;
import no.runsafe.framework.server.player.RunsafePlayer;

import java.util.HashMap;

public class PacketMessage extends PlayerCommand
{
	public PacketMessage()
	{
		super("packetmessage", "Sends a chat packet to your client", "runsafe.admintoybox.test", "message");
	}

	@Override
	public String OnExecute(RunsafePlayer executor, HashMap<String, String> parameters)
	{
		RunsafePacketSender packetSender = new RunsafePacketSender(executor);
		packetSender.sendNamedEntitySpawnPacket(executor);
		return "&2The server has sent you a chat packet";
	}
}
