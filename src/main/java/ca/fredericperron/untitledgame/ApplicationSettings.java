package ca.fredericperron.untitledgame;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

/**
 * Created by Frédéric Perron on 2019-01-10. This file
 * is under copyrights© as mentioned in the README file.
 */
public class ApplicationSettings {

    //DEBUG
    public static final boolean DEBUG = true;

    //Display
    public static final String DISPLAY_TITLE = "Untitled Display";
    public static final int DISPLAY_WIDTH = 720;
    public static final int DISPLAY_HEIGHT = 480;
    public static final boolean DISPLAY_RESIZABLE = true;
    public static final boolean VSYNC = false;

    //UPDATER
    public static final int FPS = 60;
    public static final int UPS = 60;

    //CONTROLS
    public static final int CONTROL_MOVE_SPRINT = GLFW.GLFW_KEY_LEFT_CONTROL;
    public static final int CONTROL_MOVE_FORWARD = GLFW.GLFW_KEY_W;
    public static final int CONTROL_MOVE_LEFTWARD = GLFW.GLFW_KEY_A;
    public static final int CONTROL_MOVE_RIGHTWARD = GLFW.GLFW_KEY_D;
    public static final int CONTROL_MOVE_BACKWARD = GLFW.GLFW_KEY_S;
    public static final int CONTROL_MOVE_UPWARD = GLFW.GLFW_KEY_SPACE;
    public static final int CONTROL_MOVE_DOWNWARD = GLFW.GLFW_KEY_LEFT_SHIFT;
    public static final int CONTROL_UNGRAB_MOUSE = GLFW.GLFW_KEY_ESCAPE;

    //FOLDER
    public static final String RESOURCE_FOLDER_NAME = ".untitledgame";
    public static final String RESOURCE_VERTEX_SHADER = "/resources/rendering/shaders/defaultshader.vs";
    public static final String RESOURCE_FRAGMENT_SHADER = "/resources/rendering/shaders/defaultshader.fs";

    //CAMERA
    public static final float SPEED_FORWARD = 0.03f,
                                SPEED_SIDEWARD = 0.02f,
                                SPEED_UPWARD = 0.025f,
                                SPEED_DOWNWARD = 0.015f,
                                SPEED_SPRINT_FACTOR = 0.01f;
    public static final float FOV = (float) Math.toRadians(90);
    public static final float Z_NEAR = 0.01f;
    public static final float Z_FAR = 1000.f;
    public static final float SENSITIVITY_HORIZONTAL = 0.13f;
    public static final float SENSITIVITY_VERTICAL = 0.1f;
    public static final float MAX_PITCH = -90;
    public static final float MIN_PITCH = 90;

    //WORLD
    public static final Vector3f DEFAULT_COLOR = new Vector3f(1,1,1);

}
