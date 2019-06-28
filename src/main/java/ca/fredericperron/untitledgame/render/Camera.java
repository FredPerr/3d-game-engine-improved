package ca.fredericperron.untitledgame.render;

import ca.fredericperron.untitledgame.Application;
import ca.fredericperron.untitledgame.ApplicationSettings;
import ca.fredericperron.untitledgame.Main;
import ca.fredericperron.untitledgame.display.Display;
import ca.fredericperron.untitledgame.display.input.InputKey;
import ca.fredericperron.untitledgame.game.movement.Direction;
import ca.fredericperron.untitledgame.game.movement.Moveable;
import org.joml.Vector3f;

/**
 * Created by Frédéric Perron on 2019-01-13. This file
 * is under copyrights© as mentioned in the README file.
 */
public class Camera extends Moveable {

    private Display display;

    private InputKey key_forward = new InputKey(ApplicationSettings.CONTROL_MOVE_FORWARD);
    private InputKey key_leftward = new InputKey(ApplicationSettings.CONTROL_MOVE_LEFTWARD);
    private InputKey key_rightward = new InputKey(ApplicationSettings.CONTROL_MOVE_RIGHTWARD);
    private InputKey key_backward = new InputKey(ApplicationSettings.CONTROL_MOVE_BACKWARD);
    private InputKey key_upward = new InputKey(ApplicationSettings.CONTROL_MOVE_UPWARD);
    private InputKey key_downward = new InputKey(ApplicationSettings.CONTROL_MOVE_DOWNWARD);
    private InputKey key_sprint = new InputKey(ApplicationSettings.CONTROL_MOVE_SPRINT);

    public Camera(Application application, Vector3f position, Vector3f rotation) {
        super(position, rotation);
        this.display = application.getDisplay();
    }

    public void performMovements(boolean yAxisCalculation){
        if(key_forward.isDown())
            move(Direction.FORWARD, yAxisCalculation, key_sprint.isDown() ? ApplicationSettings.SPEED_FORWARD+ApplicationSettings.SPEED_SPRINT_FACTOR : ApplicationSettings.SPEED_FORWARD);
        if(key_backward.isDown())
            move(Direction.BACKWARD, yAxisCalculation, ApplicationSettings.SPEED_SIDEWARD);
        if(key_leftward.isDown())
            move(Direction.LEFTWARD, yAxisCalculation, ApplicationSettings.SPEED_SIDEWARD);
        if(key_rightward.isDown())
            move(Direction.RIGHTWARD, yAxisCalculation, ApplicationSettings.SPEED_SIDEWARD);
        if(key_upward.isDown())
            move(Direction.UPWARD, yAxisCalculation, ApplicationSettings.SPEED_UPWARD);
        if(key_downward.isDown())
            move(Direction.DOWNWARD, yAxisCalculation, ApplicationSettings.SPEED_DOWNWARD);
    }

    public void performRotation(){
        double x = display.getCursorX();
        double y = display.getCursorY();

        float dx = (float)(display.lastCursorX - x) * ApplicationSettings.SENSITIVITY_HORIZONTAL;
        float dy = (float)(display.lastCursorY - y) * ApplicationSettings.SENSITIVITY_VERTICAL;

        System.out.println(getRotation().x());

        rotate(-dy, -dx, 0);


        display.lastCursorX = x;
        display.lastCursorY = y;

        if(getRotation().x() > ApplicationSettings.MIN_PITCH)
            getRotation().x =  ApplicationSettings.MIN_PITCH;
        if(getRotation().x() < ApplicationSettings.MAX_PITCH)
            getRotation().x = ApplicationSettings.MAX_PITCH;


    }

    public void performActions(){
        if(!display.isMouseGrabbed())
            return;

        performRotation();
        performMovements(false);
    }
}
