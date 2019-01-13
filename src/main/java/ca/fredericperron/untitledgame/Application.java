package ca.fredericperron.untitledgame;

import ca.fredericperron.untitledgame.display.Display;
import ca.fredericperron.untitledgame.display.Updater;
import ca.fredericperron.untitledgame.display.input.InputKey;
import ca.fredericperron.untitledgame.display.input.InputMouse;
import ca.fredericperron.untitledgame.render.Camera;
import ca.fredericperron.untitledgame.render.Renderer;
import ca.fredericperron.untitledgame.render.model.GameObject;
import ca.fredericperron.untitledgame.render.model.Mesh;
import ca.fredericperron.untitledgame.storage.ResourceManager;
import org.lwjgl.glfw.GLFW;

/**
 * Created by Frédéric Perron on 2019-01-12. This file
 * is under copyrights© as mentioned in the README file.
 */
public class Application implements IApplication {

    private ResourceManager resourceManager;

    private InputKey key_forward = new InputKey(ApplicationSettings.CONTROL_MOVE_FORWARD);
    private InputKey key_leftward = new InputKey(ApplicationSettings.CONTROL_MOVE_LEFTWARD);
    private InputKey key_rightward = new InputKey(ApplicationSettings.CONTROL_MOVE_RIGHTWARD);
    private InputKey key_backward = new InputKey(ApplicationSettings.CONTROL_MOVE_BACKWARD);
    private InputKey key_upward = new InputKey(ApplicationSettings.CONTROL_MOVE_UPWARD);
    private InputKey key_downward = new InputKey(ApplicationSettings.CONTROL_MOVE_DOWNWARD);

    private Renderer renderer;

    private GameObject[] objects;
    private Camera camera;

    public Application(){
        instance = this;
        resourceManager = new ResourceManager();
        this.camera = new Camera();
        this.renderer = new Renderer();
        //last method to be called.
        new Updater().start();
    }

    public void init() throws Exception{
        Display.getInstance();
        renderer.init();

        float[] positions = new float[] {
                // VO
                -0.5f, 0.5f, 0.5f,
                // V1
                -0.5f, -0.5f, 0.5f,
                // V2
                0.5f, -0.5f, 0.5f,
                // V3
                0.5f, 0.5f, 0.5f,
                // V4
                -0.5f, 0.5f, -0.5f,
                // V5
                0.5f, 0.5f, -0.5f,
                // V6
                -0.5f, -0.5f, -0.5f,
                // V7
                0.5f, -0.5f, -0.5f,
        };
        int[] indices = new int[] {
                // Front face
                0, 1, 3, 3, 1, 2,
                // Top Face
                4, 0, 3, 5, 4, 3,
                // Right face
                3, 2, 7, 5, 3, 7,
                // Left face
                6, 1, 0, 6, 0, 4,
                // Bottom face
                2, 1, 6, 2, 6, 7,
                // Back face
                7, 6, 4, 7, 4, 5,
        };
        float[] colors = new float[]{
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,
        };
        Mesh mesh = new Mesh(positions, colors, indices);
        GameObject  go = new GameObject(mesh);
        objects = new GameObject[]{go};
    }

    public void update(){
        Display.getInstance().updateGrabbing();
    }

    public void render(){
        moveCamera();
        renderer.render(objects, camera);
    }

    public void end(){

    }

    public void cleanUp(){
        renderer.cleanUp();
        for (GameObject go : objects)
            go.getMesh().cleanUp();

    }

    private void moveCamera(){
        if(!Display.getInstance().isMouseGrabbed())
            return;
        if(key_forward.isDown())
            camera.movePosition(0,0,-0.05f);
        if(key_backward.isDown())
            camera.movePosition(0,0,0.04f);
        if(key_leftward.isDown())
            camera.movePosition(-0.05f,0,0);
        if(key_rightward.isDown())
            camera.movePosition(0.05f,0,0);
        if(key_upward.isDown())
            camera.movePosition(0,0.05f,0);
        if(key_downward.isDown())
            camera.movePosition(0,-0.05f,0);

        double x = Display.getInstance().getCursorX();
        double y = Display.getInstance().getCursorY();

        float dx = (float)(Display.getInstance().lastCursorX - x) * ApplicationSettings.SENSIVITY_HORIZONTAL;
        float dy = (float)(Display.getInstance().lastCursorY - y) * ApplicationSettings.SENSIVITY_VERTICAL;
        Display.getInstance().lastCursorX = x;
        Display.getInstance().lastCursorY = y;
        camera.moveRotation(-dy, -dx, 0);
    }

    public ResourceManager getResourceManager(){
        return this.resourceManager;
    }

    private static Application instance;

    public static Application getInstance(){
        return instance;
    }
}
