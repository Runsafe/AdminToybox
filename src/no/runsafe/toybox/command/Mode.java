package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.ICommandExecutor;
import no.runsafe.framework.api.command.ExecutableCommand;
import no.runsafe.framework.minecraft.RunsafeServer;
import no.runsafe.framework.minecraft.player.RunsafeAmbiguousPlayer;
import no.runsafe.framework.minecraft.player.RunsafePlayer;
import org.bukkit.GameMode;

import java.util.HashMap;

public class Mode extends ExecutableCommand
{
	public Mode()
	{
		super("mode", "Changes the game-mode of the player", "runsafe.toybox.mode", "mode");
	}

	@Override
	public String OnExecute(ICommandExecutor executor, HashMap<String, String> parameters)
	{
		return null;
	}

	@Override
	public String OnExecute(ICommandExecutor executor, HashMap<String, String> parameters, String[] arguments)
	{
		if (!(executor instanceof RunsafePlayer) && arguments.length == 0)
			return "&cYou need to supply a player for this command.";

		RunsafePlayer target = (arguments.length > 0 ? RunsafeServer.Instance.getPlayer(arguments[0]) : (RunsafePlayer) executor);
		if (target instanceof RunsafeAmbiguousPlayer)
			return target.toString();

		if (!target.isOnline())
			return String.format("&cThe player %s is offline.", target.getName());

		String mode = parameters.get("mode");

		if (mode.equalsIgnoreCase("survival") || mode.equalsIgnoreCase("s"))
			target.setGameMode(GameMode.SURVIVAL);

		if (mode.equalsIgnoreCase("creative") || mode.equalsIgnoreCase("c"))
			target.setGameMode(GameMode.CREATIVE);

		if (mode.equalsIgnoreCase("adventure") || mode.equalsIgnoreCase("a"))
			target.setGameMode(GameMode.ADVENTURE);

		return this.getGameModeUpdateMessage(target);
	}

	private String getGameModeUpdateMessage(RunsafePlayer target)
	{
		String gameModeName = "unknown";

		if (target.getGameMode() == GameMode.SURVIVAL)
			gameModeName = "survival";

		if (target.getGameMode() == GameMode.CREATIVE)
			gameModeName = "creative";

		if (target.getGameMode() == GameMode.ADVENTURE)
			gameModeName = "adventure";

		return String.format("%s now has the game mode %s.", target.getPrettyName(), gameModeName);
	}
}

