package no.runsafe.toybox;

import no.runsafe.framework.RunsafePlugin;

public class Plugin extends RunsafePlugin
{
	@Override
	protected void PluginSetup()
	{
		addComponent(Component.class);
	}
}
