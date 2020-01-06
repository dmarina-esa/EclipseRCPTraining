//-----------------------------------------------------------------------------
//
// (C) 2019 European Space Agency
// European Space Operations Centre
// Darmstadt, Germany
//
//-----------------------------------------------------------------------------
//
// System : EGOS USER DESKTOP
//
// Sub-System : com.gmv.course.sportsimulator.display
//
// File Name : SportsLabelProvider.java
//
// Author : David Marina
//
// Creation Date : 3 Nov 2019
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.course.sportsimulator.display;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.gmv.sportsimulator.api.Game;
import com.gmv.sportsimulator.api.Team;
import com.gmv.sportsimulator.api.Team.TeamGender;

/**
 * @author David Marina
 *
 */
public class SportsLabelProvider extends BaseLabelProvider implements ILabelProvider
{
    private static final String TENNIS_ICON_PATH = "icons/sports/sport-tennis.png";

    private static final String TENNIS = "TennisId";

    private static final String SOCCER_ICON_PATH = "icons/sports/sport-soccer.png";

    private static final String SOCCER = "SoccerId";

    private static final String BASKETBALL_ICON_PATH = "icons/sports/sport-basketball.png";

    private static final String BASKETBALL = "BasketballId";

    private static final String MALE_GREEN_ICON_PATH = "icons/players/user-green.png";

    private static final String MALE_GREEN = "MaleGreenId";

    private static final String FEMALE_YELLOW_ICON_PATH = "icons/players/user-yellow-female.png";

    private static final String FEMALE_YELLOW = "FemaleYellowId";

    private static final String MIXED_TEAM_ICON_PATH = "icons/players/users.png";

    private static final String MIXED_TEAM = "MixedTeamId";


    @Override
    public Image getImage(Object element)
    {
        if (element instanceof Game)
        {
            Game game = (Game) element;
            String gameType = game.getGameType();
            if (gameType.equals("Tennis"))
            {
                return getImageFromPath(TENNIS, TENNIS_ICON_PATH);
            }
            else if (gameType.equals("Soccer"))
            {
                return getImageFromPath(SOCCER, SOCCER_ICON_PATH);
            }
            else if (gameType.equals("Basketball"))
            {
                return getImageFromPath(BASKETBALL, BASKETBALL_ICON_PATH);
            }
            else
            {
                return null;
            }
        }

        if (element instanceof Team)
        {
            Team team = (Team) element;
            TeamGender gender = team.getGender();
            if (gender == TeamGender.MALE)
            {
                return getImageFromPath(MALE_GREEN, MALE_GREEN_ICON_PATH);
            }
            else if (gender == TeamGender.FEMALE)
            {
                return getImageFromPath(FEMALE_YELLOW, FEMALE_YELLOW_ICON_PATH);
            }
            else
            {
                return getImageFromPath(MIXED_TEAM, MIXED_TEAM_ICON_PATH);
            }
        }
        return null;
    }

    @Override
    public String getText(Object element)
    {

        if (element instanceof Game)
        {
            Game game = (Game) element;
            return game.getName();
        }

        if (element instanceof Team)
        {
            Team team = (Team) element;
            return team.getTeamName();
        }

        return null;
    }

    /**
     * @param tennisIconPath
     * @return
     */
    private static Image getImageFromPath(String id, String path)
    {
        Image image = JFaceResources.getImageRegistry().get(path);
        if (image == null || image.getDevice().isDisposed())
        {
            image = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, path).createImage(PlatformUI
                    .getWorkbench().getDisplay());
            JFaceResources.getImageRegistry().put(path, image);
        }
        return image;
    }

}

// -----------------------------------------------------------------------------
