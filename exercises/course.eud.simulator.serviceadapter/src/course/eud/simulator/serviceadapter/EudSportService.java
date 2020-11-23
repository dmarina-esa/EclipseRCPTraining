//-----------------------------------------------------------------------------
//
// (C) 2020 European Space Agency
// European Space Operations Centre
// Darmstadt, Germany
//
//-----------------------------------------------------------------------------
//
// System : EGOS USER DESKTOP
//
// Sub-System : course.eud.simulator.serviceadapter
//
// File Name : EudSportService.java
//
// Author : <ADD YOUR NAME IN TEMPLATE under Window->
//
// Creation Date : 22 Nov 2020
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package course.eud.simulator.serviceadapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gmv.sportsimulator.api.Game;
import com.gmv.sportsimulator.api.Location;
import com.gmv.sportsimulator.api.Result;
import com.gmv.sportsimulator.api.Team;
import com.gmv.sportsimulator.api.service.ISportService;
import com.gmv.sportsimulator.api.service.ISportServiceListener;
import com.gmv.sportsimulator.api.service.SimulationSpeed;

import esa.open.lib.core.service.impl.Preference;

/**
 * @author <TODO: Specify Full name of initial Author in template>
 *
 */
public class EudSportService implements IEudSportService
{

    private final ISportService sportBackend;


    /**
     * Creates a new EudSportService
     * 
     * @param sportBackend
     */
    public EudSportService(final ISportService sportBackend)
    {
        this.sportBackend = sportBackend;
        // TODO Auto-generated constructor stub
    }

    /**
     * @param team
     * @see com.gmv.sportsimulator.api.service.ISportService#registerTeam(com.gmv.sportsimulator.api.Team)
     */
    @Override
    public void registerTeam(final Team team)
    {
        this.sportBackend.registerTeam(team);
    }

    /**
     * @param team
     * @see com.gmv.sportsimulator.api.service.ISportService#removeTeam(com.gmv.sportsimulator.api.Team)
     */
    @Override
    public void removeTeam(final Team team)
    {
        this.sportBackend.removeTeam(team);
    }

    /**
     * @param teamA
     * @param teamB
     * @param location
     * @see com.gmv.sportsimulator.api.service.ISportService#registerGame(com.gmv.sportsimulator.api.Team,
     *      com.gmv.sportsimulator.api.Team,
     *      com.gmv.sportsimulator.api.Location)
     */
    @Override
    public void registerGame(final Team teamA, final Team teamB, final Location location)
    {
        this.sportBackend.registerGame(teamA, teamB, location);
    }

    /**
     * @param name
     * @param teamA
     * @param teamB
     * @param location
     * @see com.gmv.sportsimulator.api.service.ISportService#registerGame(java.lang.String,
     *      com.gmv.sportsimulator.api.Team, com.gmv.sportsimulator.api.Team,
     *      com.gmv.sportsimulator.api.Location)
     */
    @Override
    public void registerGame(final String name, final Team teamA, final Team teamB, final Location location)
    {
        this.sportBackend.registerGame(name, teamA, teamB, location);
    }

    /**
     * @param teamA
     * @param teamB
     * @param location
     * @param metadata
     * @see com.gmv.sportsimulator.api.service.ISportService#registerGame(com.gmv.sportsimulator.api.Team,
     *      com.gmv.sportsimulator.api.Team,
     *      com.gmv.sportsimulator.api.Location, java.util.Map)
     */
    @Override
    public void registerGame(final Team teamA,
                             final Team teamB,
                             final Location location,
                             final Map<String, String> metadata)
    {
        this.sportBackend.registerGame(teamA, teamB, location, metadata);
    }

    /**
     * @param name
     * @param teamA
     * @param teamB
     * @param location
     * @param metadata
     * @see com.gmv.sportsimulator.api.service.ISportService#registerGame(java.lang.String,
     *      com.gmv.sportsimulator.api.Team, com.gmv.sportsimulator.api.Team,
     *      com.gmv.sportsimulator.api.Location, java.util.Map)
     */
    @Override
    public void registerGame(final String name,
                             final Team teamA,
                             final Team teamB,
                             final Location location,
                             final Map<String, String> metadata)
    {
        this.sportBackend.registerGame(name, teamA, teamB, location, metadata);
    }

    /**
     * @param game
     * @param newName
     * @return
     * @see com.gmv.sportsimulator.api.service.ISportService#renameGame(com.gmv.sportsimulator.api.Game,
     *      java.lang.String)
     */
    @Override
    public String renameGame(final Game game, final String newName)
    {
        return this.sportBackend.renameGame(game, newName);
    }

    /**
     * @param game
     * @param speed
     * @see com.gmv.sportsimulator.api.service.ISportService#simulateGame(com.gmv.sportsimulator.api.Game,
     *      com.gmv.sportsimulator.api.service.SimulationSpeed)
     */
    @Override
    public void simulateGame(final Game game, final SimulationSpeed speed)
    {
        this.sportBackend.simulateGame(game, speed);
    }

    /**
     * @param listener
     * @see com.gmv.sportsimulator.api.service.ISportService#registerServiceListener(com.gmv.sportsimulator.api.service.ISportServiceListener)
     */
    @Override
    public void registerServiceListener(final ISportServiceListener listener)
    {
        this.sportBackend.registerServiceListener(listener);
    }

    /**
     * @param speed
     * @see com.gmv.sportsimulator.api.service.ISportService#simulateAllGames(com.gmv.sportsimulator.api.service.SimulationSpeed)
     */
    @Override
    public void simulateAllGames(final SimulationSpeed speed)
    {
        this.sportBackend.simulateAllGames(speed);
    }

    /**
     * @param game
     * @see com.gmv.sportsimulator.api.service.ISportService#stopSimulation(com.gmv.sportsimulator.api.Game)
     */
    @Override
    public void stopSimulation(final Game game)
    {
        this.sportBackend.stopSimulation(game);
    }

    /**
     * 
     * @see com.gmv.sportsimulator.api.service.ISportService#stopAllSimulations()
     */
    @Override
    public void stopAllSimulations()
    {
        this.sportBackend.stopAllSimulations();
    }

    /**
     * 
     * @see com.gmv.sportsimulator.api.service.ISportService#resetAllGames()
     */
    @Override
    public void resetAllGames()
    {
        this.sportBackend.resetAllGames();
    }

    /**
     * @return
     * @see com.gmv.sportsimulator.api.service.ISportService#getRegisteredGames()
     */
    @Override
    public List<EudGame> getRegisteredGames()
    {
        List<EudGame> games = new ArrayList<>();
        for (Game g : this.sportBackend.getRegisteredGames())
        {
            games.add(new EudGame(g));
        }
        return games;
    }

    /**
     * @return
     * @see com.gmv.sportsimulator.api.service.ISportService#getStartedGames()
     */
    @Override
    public List<Game> getStartedGames()
    {
        return this.sportBackend.getStartedGames();
    }

    /**
     * @return
     * @see com.gmv.sportsimulator.api.service.ISportService#isOngoingSimulation()
     */
    @Override
    public boolean isOngoingSimulation()
    {
        return this.sportBackend.isOngoingSimulation();
    }

    /**
     * 
     * @see com.gmv.sportsimulator.api.service.ISportService#shuffleTeams()
     */
    @Override
    public void shuffleTeams()
    {
        this.sportBackend.shuffleTeams();
    }

    /**
     * @return
     * @see com.gmv.sportsimulator.api.service.ISportService#getTeams()
     */
    @Override
    public List<Team> getTeams()
    {
        return this.sportBackend.getTeams();
    }

    /**
     * @param team
     * @return
     * @see com.gmv.sportsimulator.api.service.ISportService#isPlaying(com.gmv.sportsimulator.api.Team)
     */
    @Override
    public boolean isPlaying(final Team team)
    {
        return this.sportBackend.isPlaying(team);
    }

    /**
     * @return
     * @see com.gmv.sportsimulator.api.service.ISportService#getSportTypes()
     */
    @Override
    public String[] getSportTypes()
    {
        return this.sportBackend.getSportTypes();
    }

    /**
     * @param bidder
     * @param game
     * @param result
     * @param amountToBid
     * @see com.gmv.sportsimulator.api.service.ISportService#placeBet(java.lang.String,
     *      com.gmv.sportsimulator.api.Game, com.gmv.sportsimulator.api.Result,
     *      int)
     */
    public void placeBet(final String bidder, final Game game, final Result result, final int amountToBid)
    {
        this.sportBackend.placeBet(bidder, game, result, amountToBid);
    }

    /** {@inheritDoc} */
    @Override
    public String getName()
    {
        return "Sport Service";
    }

    /** {@inheritDoc} */
    @Override
    public Map<String, Preference> getPreferences()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void setPreferences(final Map<String, Preference> preferences)
    {
        // TODO Auto-generated method stub

    }

}

// -----------------------------------------------------------------------------
