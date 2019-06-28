package ca.fredericperron.untitledgame;

import ca.fredericperron.untitledgame.render.model.GameObject;
import ca.fredericperron.untitledgame.render.model.Mesh;
import ca.fredericperron.untitledgame.render.model.Model;
import ca.fredericperron.untitledgame.util.OBJLoader;
import ca.fredericperron.untitledgame.util.ResourceUtil;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Frederic on 2019-04-04.
 */
public class Framework {

    private ArrayList<GameObject> gameObjects;
    private Application app;

    public Framework(Application application){
        gameObjects = new ArrayList<>();
        this.app = application;
    }

    public void init() throws Exception{






        /*Mesh mesh = loadModel(Model.BUNNY);
        mesh.setColor(new Vector3f(0,1,1));
        addObject(mesh.toGameObject());*/
    }

    public void addObject(GameObject gameObject){
        gameObjects.add(gameObject);
    }

    public Mesh loadModel(Model model) throws Exception {
        return OBJLoader.loadMesh(ResourceUtil.getInstance().convertByteArrayToString(ResourceUtil.getInstance().loadData(app.getResourceManager().getResourceFolder().getPath()+"/resources/rendering/models/"+model.getFileName())));
    }

    public ArrayList<GameObject> getGameObjects(){
        return this.gameObjects;
    }
}
