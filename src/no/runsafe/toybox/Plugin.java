package no.runsafe.toybox;

import no.runsafe.framework.RunsafePlugin;
import no.runsafe.toybox.command.*;

public class Plugin extends RunsafePlugin
{
	@Override
	protected void PluginSetup()
	{
		addComponent(Bomb.class);
		addComponent(Dynamite.class);
		addComponent(Fireball.class);
		addComponent(Firework.class);
		addComponent(Lightning.class);
		addComponent(SpawnMob.class);
	}
}
