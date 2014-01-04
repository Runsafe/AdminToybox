package no.runsafe.toybox.repositories;

import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.database.IDatabase;
import no.runsafe.framework.api.database.IRow;
import no.runsafe.framework.api.database.ISet;
import no.runsafe.framework.api.database.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class LockedObjectRepository extends Repository
{
	public LockedObjectRepository(IDatabase database)
	{
		this.database = database;
	}

	@Override
	public String getTableName()
	{
		return "toybox_locked_objects";
	}

	public void storeLockedObject(ILocation location)
	{
		database.execute("INSERT INTO toybox_locked_objects (world, x, y, z) VALUES(?, ?, ?, ?)",
			location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
	}

	public void removeLockedObject(ILocation location)
	{
		database.execute(
			"DELETE FROM toybox_locked_objects WHERE world = ? AND x = ? AND y =? AND z = ?",
			location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ()
		);
	}

	public List<ILocation> getLockedObjects()
	{
		List<ILocation> locations = new ArrayList<ILocation>();
		ISet data = database.query("SELECT world, x, y, z FROM toybox_locked_objects");

		if (data != null)
			for (IRow node : data)
				locations.add(node.Location());

		return locations;
	}

	@Override
	public HashMap<Integer, List<String>> getSchemaUpdateQueries()
	{
		HashMap<Integer, List<String>> queries = new LinkedHashMap<Integer, List<String>>(1);
		List<String> sql = new ArrayList<String>();
		sql.add(
			"CREATE TABLE `toybox_locked_objects` (" +
				"`world` VARCHAR(255) NOT NULL," +
				"`x` DOUBLE NOT NULL," +
				"`y` DOUBLE NOT NULL," +
				"`z` DOUBLE NOT NULL" +
				")"
		);
		queries.put(1, sql);
		return queries;
	}

	private IDatabase database;
}