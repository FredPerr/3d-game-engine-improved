package ca.fredericperron.untitledgame;

import ca.fredericperron.untitledgame.display.Display;
import ca.fredericperron.untitledgame.display.Updater;
import ca.fredericperron.untitledgame.display.input.InputKey;
import ca.fredericperron.untitledgame.display.input.InputMouse;
import ca.fredericperron.untitledgame.render.Camera;
import ca.fredericperron.untitledgame.render.Renderer;
import ca.fredericperron.untitledgame.render.model.GameObject;
import ca.fredericperron.untitledgame.render.model.Mesh;
import ca.fredericperron.untitledgame.render.model.Texture;
import ca.fredericperron.untitledgame.storage.Image;
import ca.fredericperron.untitledgame.storage.ResourceManager;
import org.lwjgl.glfw.GLFW;

/**
 * Created by Frédéric Perron on 2019-01-12. This file
 * is under copyrights© as mentioned in the README file.
 */
public class Application implements IApplication {

    private ResourceManager resourceManager;
    private Renderer renderer;
    private GameObject[] objects;
    private Camera camera;
    private Texture texture;

    public Application(){
        instance = this;
        this.resourceManager = new ResourceManager();
        this.camera = new Camera();
        this.renderer = new Renderer();
        new Updater().start();
    }

    public void init() throws Exception{
        Display.getInstance();
        renderer.init();
        texture = new Texture(new Image(getResourceManager().getResourceFolder().getPath("/resources/rendering/textures/test.png")));
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
        float[] textCoords = new float[]{
                0.0f, 0.0f,
                0.0f, 0.5f,
                0.5f, 0.5f,
                0.5f, 0.0f,
                0.0f, 0.0f,
                0.5f, 0.0f,
                0.0f, 0.5f,
                0.5f, 0.5f,
                // For text coords in top face
                0.0f, 0.5f,
                0.5f, 0.5f,
                0.0f, 1.0f,
                0.5f, 1.0f,
                // For text coords in right face
                0.0f, 0.0f,
                0.0f, 0.5f,
                // For text coords in left face
                0.5f, 0.0f,
                0.5f, 0.5f,
                // For text coords in bottom face
                0.5f, 0.0f,
                1.0f, 0.0f,
                0.5f, 0.5f,
                1.0f, 0.5f,};
        Mesh mesh = new Mesh(positions, textCoords, indices, texture);
        GameObject  go = new GameObject(mesh);
        objects = new GameObject[]{go};
    }

    public void update(){
        Display.getInstance().updateGrabbing();
    }

    public void render(){
        camera.performMovements();
        renderer.render(objects, camera);
    }

    public void end(){}

    public void cleanUp(){
        renderer.cleanUp();
        for (GameObject go : objects)
            go.getMesh().cleanUp();
        //TODO clear textures.
    }

    public ResourceManager getResourceManager(){
        return this.resourceManager;
    }

    private static Application instance;

    public static Application getInstance(){
        return instance;
    }
}
