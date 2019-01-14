package ca.fredericperron.untitledgame.render;

import ca.fredericperron.untitledgame.ApplicationSettings;
import ca.fredericperron.untitledgame.display.Display;
import ca.fredericperron.untitledgame.display.input.InputKey;
import org.joml.Vector3f;

/**
 * Created by Frédéric Perron on 2019-01-13. This file
 * is under copyrights© as mentioned in the README file.
 */
public class Camera {

    private final Vector3f position;
    private final Vector3f rotation;

    private InputKey key_forward = new InputKey(ApplicationSettings.CONTROL_MOVE_FORWARD);
    private InputKey key_leftward = new InputKey(ApplicationSettings.CONTROL_MOVE_LEFTWARD);
    private InputKey key_rightward = new InputKey(ApplicationSettings.CONTROL_MOVE_RIGHTWARD);
    private InputKey key_backward = new InputKey(ApplicationSettings.CONTROL_MOVE_BACKWARD);
    private InputKey key_upward = new InputKey(ApplicationSettings.CONTROL_MOVE_UPWARD);
    private InputKey key_downward = new InputKey(ApplicationSettings.CONTROL_MOVE_DOWNWARD);

    public Camera() {
        position = new Vector3f(0, 0, 0);
        rotation = new Vector3f(0, 0, 0);
    }

    public Camera(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(float x, float y, float z) {
        position.x = x;
        position.y = y;
        position.z = z;
    }

    public void movePosition(float offsetX, float offsetY, float offsetZ) {
        if (offsetZ != 0) {
            position.x += (float) Math.sin(Math.toRadians(rotation.y)) * -1.0f * offsetZ;
            position.z += (float) Math.cos(Math.toRadians(rotation.y)) * offsetZ;
        }
        if (offsetX != 0) {
            position.x += (float) Math.sin(Math.toRadians(rotation.y - 90)) * -1.0f * offsetX;
            position.z += (float) Math.cos(Math.toRadians(rotation.y - 90)) * offsetX;

        }
        position.y += offsetY;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(float x, float y, float z) {
        rotation.x = x;
        rotation.y = y;
        rotation.z = z;
    }

    public void moveRotation(float offsetX, float offsetY, float offsetZ) {
        rotation.x += offsetX;
        rotation.y += offsetY;
        rotation.z += offsetZ;
    }

    public void performMovements(){
        if(!Display.getInstance().isMouseGrabbed())
            return;
        if(key_forward.isDown())
            movePosition(0,0,-0.05f);
        if(key_backward.isDown())
            movePosition(0,0,0.04f);
        if(key_leftward.isDown())
            movePosition(-0.05f,0,0);
        if(key_rightward.isDown())
            movePosition(0.05f,0,0);
        if(key_upward.isDown())
            movePosition(0,0.05f,0);
        if(key_downward.isDown())
            movePosition(0,-0.05f,0);

        double x = Display.getInstance().getCursorX();
        double y = Display.getInstance().getCursorY();

        float dx = (float)(Display.getInstance().lastCursorX - x) * ApplicationSettings.SENSITIVITY_HORIZONTAL;
        float dy = (float)(Display.getInstance().lastCursorY - y) * ApplicationSettings.SENSITIVITY_VERTICAL;
        Display.getInstance().lastCursorX = x;
        Display.getInstance().lastCursorY = y;
        moveRotation(-dy, -dx, 0);
    }
}
