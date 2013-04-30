package no.runsafe.toybox;

import no.runsafe.framework.RunsafePlugin;
import no.runsafe.toybox.command.*;
import no.runsafe.toybox.events.EntityBlockFormEvent;
import no.runsafe.toybox.handlers.CarePackageHandler;

public class Plugin extends RunsafePlugin
{
	@Override
	protected void PluginSetup()
	{
		// Commands
		addComponent(Bazooka.class);
		addComponent(Bomb.class);
		addComponent(Dismount.class);
		addComponent(Dynamite.class);
		addComponent(Fireball.class);
		addComponent(Firework.class);
		addComponent(Lightning.class);
		addComponent(Mount.class);
		addComponent(SpawnMob.class);
		addComponent(Ride.class);
		addComponent(CreateHead.class);
		addComponent(Mode.class); // TODO: Move this to another more suiting plug-in.
		addComponent(CarePackage.class);

		// Handlers
		addComponent(CarePackageHandler.class);

		// Events
		addComponent(EntityBlockFormEvent.class);
	}
}
