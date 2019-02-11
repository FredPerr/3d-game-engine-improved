package ca.fredericperron.untitledgame.display;

import ca.fredericperron.untitledgame.Application;
import ca.fredericperron.untitledgame.render.model.GameObject;
import ca.fredericperron.untitledgame.render.model.Mesh;
import ca.fredericperron.untitledgame.util.OBJLoader;
import ca.fredericperron.untitledgame.util.ResourceUtil;
import org.joml.Vector3f;

/**
 * Created by KitK4t on 2019-02-09.
 */
public class Screen {

    private Application app;
    private GameObject[] objects;

    public Screen(Application application){
        this.app = application;
    }

    public void init() throws Exception{
        app.getRenderer().init();

        //------Testing---------

        Mesh mesh = OBJLoader.loadMesh(ResourceUtil.getInstance().convertByteArrayToString(ResourceUtil.getInstance().loadData(app.getResourceManager().getResourceFolder().getPath()+"/resources/rendering/models/bunny.obj")));
        mesh.setColor(new Vector3f(1,0,0));
        GameObject go = new GameObject(mesh);
        objects = new GameObject[]{go};

        //----------------------
    }

    public void render(){
        app.getRenderer().render(objects, app.getCamera());
    }

    public void cleanUp(){
        app.getRenderer().cleanUp();
        for (GameObject go : objects)
            go.getMesh().cleanUp();
    }
}
