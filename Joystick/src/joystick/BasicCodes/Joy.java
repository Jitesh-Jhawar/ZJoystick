/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joystick.BasicCodes;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;
import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;

/**
 *
 * @author user
 */
public abstract class Joy {

    /**
     * Given value of axis in percentage. Percentages increases from left/top to
     * right/bottom. If idle (in center) returns 50, if joystick axis is pushed
     * to the left/top edge returns 0 and if it's pushed to the right/bottom
     * returns 100.
     *
     * @return value of axis in percentage.
     */
    public int getAxisValueInPercentage(float axisValue) {
        int y = (int) (((2 - (1 - axisValue)) * 100) / 2);
        System.out.print("FLOAT=" + axisValue + "INT%=" + y);
        return (int) (((2 - (1 - axisValue)) * 100) / 2);
    }

//    private ArrayList<Controller> foundControllers;

    public Joy() {
  ArrayList<Controller> foundControllers = new ArrayList<>();
        JFrameWindow window = new JFrameWindow();
        searchForControllers(window,foundControllers);
        // If at least one controller was found we start showing controller data on window.
        if (!foundControllers.isEmpty()) {
            startShowingControllerData(window,foundControllers);
        } else {
            window.addControllerName("No controller found!");
        }
    }

    /**
     * Starts showing controller data on the window.
     */
    private void startShowingControllerData(JFrameWindow window,ArrayList<Controller> foundControllers) {
        while (true) {
            // Currently selected controller.
            int selectedControllerIndex = window.getSelectedControllerName();
            Controller controller = foundControllers.get(selectedControllerIndex);
            // Pull controller for current data, and break while loop if controller is disconnected.
            if (!controller.poll()) {
                window.showControllerDisconnected();
                break;
            }
            // X axis and Y axis
            int xAxisPercentage = 0;
            int yAxisPercentage = 0;
            // JPanel for other axes.
            JPanel axesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 2));
            axesPanel.setBounds(0, 0, 200, 190);
            // JPanel for controller buttons
            JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 1));
            buttonsPanel.setBounds(6, 19, 246, 110);
            // Go trough all components of the controller.
            Component[] components = controller.getComponents();
            for (int i = 0; i < components.length; i++) {
                Component component = components[i];
                Identifier componentIdentifier = component.getIdentifier();
                // Buttons
                //if(component.getName().contains("Button")){ // If the language is not english, this won't work.
                if (componentIdentifier.getName().matches("^[0-9]*$")) { // If the component identifier name contains only numbers, then this is a button.
                    // Is button pressed?
                    boolean isItPressed = true;
                    if (component.getPollData() == 0.0f) {
                        isItPressed = false;
                    }
                    // Button index
                    String buttonIndex;
                    buttonIndex = component.getIdentifier().toString();
                    // Create and add new button to panel.
                    JToggleButton aToggleButton = new JToggleButton(buttonIndex, isItPressed);
                    aToggleButton.setPreferredSize(new Dimension(48, 25));
                    aToggleButton.setEnabled(false);
                    buttonsPanel.add(aToggleButton);
                    // We know that this component was button so we can skip to next component.
                    continue;
                }
                // Hat switch
                if (componentIdentifier == Component.Identifier.Axis.POV) {
                    float hatSwitchPosition = component.getPollData();
                    window.setHatSwitch(hatSwitchPosition);
                    // We know that this component was hat switch so we can skip to next component.
                    continue;
                }
                // Axes
                if (component.isAnalog()) {
                    float axisValue = component.getPollData();
                    int axisValueInPercentage = getAxisValueInPercentage(axisValue);
                    // X axis
                    if (componentIdentifier == Component.Identifier.Axis.X) {
                        xAxisPercentage = axisValueInPercentage;
                        continue; // Go to next component.
                    }
                    // Y axis
                    if (componentIdentifier == Component.Identifier.Axis.Y) {
                        yAxisPercentage = axisValueInPercentage;
                        continue; // Go to next component.
                    }
                    // Other axis
                    JLabel progressBarLabel = new JLabel(component.getName());
                    JProgressBar progressBar = new JProgressBar(0, 100);
                    progressBar.setValue(axisValueInPercentage);
                    axesPanel.add(progressBarLabel);
                    axesPanel.add(progressBar);
                }
            }
            // Now that we go trough all controller components,
            // we add butons panel to window,
            window.setControllerButtons(buttonsPanel);
            // set x and y axes,
            window.setXYAxis(xAxisPercentage, yAxisPercentage);
            // add other axes panel to window.
            window.addAxisPanel(axesPanel);
            // We have to give processor some rest.
            try {
                Thread.sleep(25);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void searchForControllers(JFrameWindow window,ArrayList<Controller> a) {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }    // TODO code application logic here

    /**
     * Use it to see which button has been clicked but stimulate the keys first
     *
     * @param a
     */
    public abstract void setJoystickListner(Button a);

    private static class Button {

        int a, b[];

        public Button() {

        }

    }

}
