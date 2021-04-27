package com.gmv.course.exercise.utils.exercise02;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Utility class for Exercise 02.
 */
public class Exercise02Utils {

	/**
	 * Private constructor.
	 */
	private Exercise02Utils() {
		// Do nothing
	}

	/**
	 * Create a basic content for the view in Exercise 02.
	 * 
	 * @param parent
	 *            The parent composite provided by Eclipse to the
	 *            createPartControl() method.
	 */
	public static void createViewContents(Composite parent) {
		GridLayoutFactory.fillDefaults().numColumns(3).applyTo(parent);

		Label label = new Label(parent, SWT.NONE);
		label.setText("This is a label");
		GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).grab(false, false).applyTo(label);

		Text text = new Text(parent, SWT.BORDER);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).applyTo(text);

		Button button = new Button(parent, SWT.PUSH);
		button.setText("Change");
		GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.CENTER).grab(false, false).applyTo(button);

		Label changeLabel = new Label(parent, SWT.NONE);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(3, 1).applyTo(changeLabel);

		button.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				changeLabel.setText(text.getText());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// Do nothing
			}
		});

	}
}
