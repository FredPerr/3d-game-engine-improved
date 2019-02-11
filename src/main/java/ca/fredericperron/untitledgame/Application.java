package ca.fredericperron.untitledgame;

import ca.fredericperron.untitledgame.display.Display;
import ca.fredericperron.untitledgame.display.Screen;
import ca.fredericperron.untitledgame.display.Updater;
import ca.fredericperron.untitledgame.render.Camera;
import ca.fredericperron.untitledgame.render.Renderer;
import ca.fredericperron.untitledgame.storage.ResourceManager;
import org.joml.Vector3f;

/**
 * Created by Frédéric Perron on 2019-01-12. This file
 * is under copyrights© as mentioned in the README file.
 */
public class Application implements IApplication {

    private Display display;
    private Screen screen;
    private ResourceManager resourceManager;
    private Renderer renderer;
    private Camera camera;

    public Application(){
        this.resourceManager = new ResourceManager();
        this.renderer = new Renderer(this);
        this.screen = new Screen(this);
        new Updater(this).start();
    }

    public void init() throws Exception{
        this.display = new Display(
                ApplicationSettings.DISPLAY_TITLE,
                ApplicationSettings.DISPLAY_WIDTH,
                ApplicationSettings.DISPLAY_HEIGHT,
                ApplicationSettings.DISPLAY_RESIZABLE
        );
        this.camera = new Camera(this, new Vector3f(0,0,-2), new Vector3f());
        getCamera().blockPitch(true, -90, 90);
        getScreen().init();
    }

    public void update(){
        getDisplay().updateGrabbingState();
    }

    public void render(){
        getCamera().performActions();
        getScreen().render();
    }

    public void cleanUp(){
        getScreen().cleanUp();
    }

    public void end(){}

    public ResourceManager getResourceManager(){
        return this.resourceManager;
    }

    public Renderer getRenderer(){
        return this.renderer;
    }

    public Camera getCamera(){
        return this.camera;
    }

    public Display getDisplay(){
        return this.display;
    }

    public Screen getScreen(){
        return this.screen;
    }
}
