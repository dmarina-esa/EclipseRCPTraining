package com.gmv.sportsimulator.soccer;

import com.gmv.course.sportsimulator.api.service.ISportServiceFactory;
import com.gmv.sportsimulator.api.Location;
import com.gmv.sportsimulator.api.Team;
import com.gmv.sportsimulator.api.Team.TeamGender;
import com.gmv.sportsimulator.api.service.ISportService;

public class SoccerServiceFactory implements ISportServiceFactory
{

    private SoccerService soccerService;


    public SoccerServiceFactory()
    {
        // TODO Auto-generated constructor stub
    }

    /** {@inheritDoc} */
    @Override
    public ISportService getSportService()
    {
        if (this.soccerService == null)
        {
            SoccerService soccerService = new SoccerService();
            this.soccerService = soccerService;
            createTeam("R.Madrid", "SPA", TeamGender.FEMALE);
            createTeam("A.Madrid", "SPA", TeamGender.FEMALE);
            createTeam("Barcelona", "SPA", TeamGender.FEMALE);
            createTeam("R.Munich", "GER", TeamGender.FEMALE);
            createTeam("PSG", "FRA", TeamGender.FEMALE);
            createTeam("Juventus", "ITA", TeamGender.FEMALE);
            createTeam("Chelsea", "ENG", TeamGender.FEMALE);
            createTeam("Frankfurt", "GER", TeamGender.FEMALE);

            soccerService.shuffleTeams();
            for (int i = 0; i < soccerService.getTeams().size(); i = i + 2)
            {
                this.soccerService.registerGame("Game " + (((i + 1) / 2) + 1), soccerService.getTeams().get(i),
                                                soccerService.getTeams().get(i + 1),
                                                new Location("Berlin"));
            }
        }
        return this.soccerService;
    }

    /**
     * @param string
     * @param string2
     * @param female
     */
    private void createTeam(String string, String string2, TeamGender female)
    {
        this.soccerService.registerTeam(new Team(string, string2, female));

    }

}

// -----------------------------------------------------------------------------
