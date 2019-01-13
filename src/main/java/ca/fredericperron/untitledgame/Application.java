package ca.fredericperron.untitledgame;

import ca.fredericperron.untitledgame.display.Display;
import ca.fredericperron.untitledgame.display.Updater;
import ca.fredericperron.untitledgame.display.input.Input;
import ca.fredericperron.untitledgame.display.input.InputKey;
import ca.fredericperron.untitledgame.render.Renderer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

/**
 * Created by Frédéric Perron on 2019-01-12. This file
 * is under copyrights© as mentioned in the README file.
 */
public class Application implements IApplication {

    private InputKey key_forward = new InputKey(ApplicationSettings.CONTROL_MOVE_FORWARD);
    private InputKey key_leftward = new InputKey(ApplicationSettings.CONTROL_MOVE_LEFTWARD);
    private InputKey key_rightward = new InputKey(ApplicationSettings.CONTROL_MOVE_RIGHTWARD);
    private InputKey key_backward = new InputKey(ApplicationSettings.CONTROL_MOVE_BACKWARD);
    private InputKey key_upward = new InputKey(ApplicationSettings.CONTROL_MOVE_UPWARD);
    private InputKey key_downward = new InputKey(ApplicationSettings.CONTROL_MOVE_DOWNWARD);

    private Renderer renderer;

    public Application(){
        instance = this;

        renderer = new Renderer();
        //last method to be called.
        new Updater().start();
    }

    public void init(){
        Display.getInstance();
        renderer.init();
    }

    public void update(){
    }

    public void render(){
        checkWindowRatio();

        renderer.clear();
    }

    public void end(){

    }

    public void cleanUp(){
    }

    private void checkWindowRatio(){
        if ( Display.getInstance().isResized() ) {
            GL11.glViewport(0,0,Display.getInstance().getWidth(), Display.getInstance().getHeight());
            Display.getInstance().setResized(false);
        }
    }


    private static Application instance;

    public static Application getInstance(){
        return instance;
    }
}
