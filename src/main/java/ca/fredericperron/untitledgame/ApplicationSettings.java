package ca.fredericperron.untitledgame;

import org.lwjgl.glfw.GLFW;

/**
 * Created by Frédéric Perron on 2019-01-10. This file
 * is under copyrights© as mentioned in the README file.
 */
public class ApplicationSettings {

    //Dsiplay
    public static final String DISPLAY_TITLE = "Untitled Display";
    public static final int DISPLAY_WIDTH = 720;
    public static final int DISPLAY_HEIGHT = 480;
    public static final boolean DISPLAY_RESIZABLE = true;
    public static final boolean VSYNC = true;

    //UPDATER
    public static final int FPS = 60;
    public static final int UPS = 40;

    //CONTROLS
    public static final int CONTROL_MOVE_FORWARD = GLFW.GLFW_KEY_W;
    public static final int CONTROL_MOVE_LEFTWARD = GLFW.GLFW_KEY_A;
    public static final int CONTROL_MOVE_RIGHTWARD = GLFW.GLFW_KEY_D;
    public static final int CONTROL_MOVE_BACKWARD = GLFW.GLFW_KEY_S;
    public static final int CONTROL_MOVE_UPWARD = GLFW.GLFW_KEY_SPACE;
    public static final int CONTROL_MOVE_DOWNWARD = GLFW.GLFW_KEY_LEFT_SHIFT;
}
