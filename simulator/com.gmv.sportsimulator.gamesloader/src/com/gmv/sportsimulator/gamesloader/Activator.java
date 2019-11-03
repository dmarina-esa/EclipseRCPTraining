package com.gmv.sportsimulator.gamesloader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.gmv.sportsimulator.api.Game;

public class Activator implements BundleActivator {

	/** The DEFAULT_SPORTS_DB_FILE_TXT of this Activator.java */
    private static final String DEFAULT_SPORTS_FILE = "resources/defaultSportsDbFile.txt";
    /** The SPORTS_DB_FILE of this Activator.java */
    private static final String PROP_SPORTS_DB_FILE = "sports.db.file";
    private static BundleContext context;
    
    public static GameFileReader fileReader;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		String property = System.getProperty(PROP_SPORTS_DB_FILE);
		if (property == null)
		{
		    
		    createDefaultReader(bundleContext);
		    
		}
		else
		{
		    File file = new File(property);
		    if (file.exists())
		    {
		        fileReader = new GameFileReader(file);
		    }
		    else
		    {
		        createDefaultReader(bundleContext);
		    }
		}
	}

    private static void createDefaultReader(BundleContext bundleContext) throws IOException
    {
        URL fileResource = bundleContext.getBundle().getResource(DEFAULT_SPORTS_FILE);
        InputStreamReader reader = new InputStreamReader(fileResource.openStream());
        Stream<String> linesStream = new BufferedReader(reader).lines();
        ArrayList<String> linesList = linesStream.collect(Collectors.toCollection(ArrayList::new));
        fileReader = new GameFileReader(linesList);
    }

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

	
	public static List<Game> getGamesFromFile(File databaseFile) throws IOException
    {
	    List<Game> games = new ArrayList<Game>();
	    GameFileReader gameReader = new GameFileReader(databaseFile);
        return games;
    }

}
