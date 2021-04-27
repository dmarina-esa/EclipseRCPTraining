/**
 * 
 */
package com.gmv.course.exercise.utils.exercise03;

import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;

import com.gmv.sportsimulator.api.Game;
import com.gmv.sportsimulator.api.Team;

/**
 * @author miar
 *
 */
public class Exercise03LabelProvider extends BaseLabelProvider implements ILabelProvider {

	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getText(Object element) {

		if (element instanceof Game) {
		    Game game = (Game) element;
			return game.getName();
		}

		if (element instanceof Team) {
		    Team team = (Team) element;
			return team.getTeamName();
		}

		return null;
	}

}
