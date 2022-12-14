package ca.fredericperron.untitledgame.render;

import ca.fredericperron.untitledgame.Application;
import ca.fredericperron.untitledgame.ApplicationSettings;
import ca.fredericperron.untitledgame.display.Display;
import ca.fredericperron.untitledgame.render.model.GameObject;
import ca.fredericperron.untitledgame.render.model.Mesh;
import ca.fredericperron.untitledgame.render.shader.ShaderProgram;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

import java.util.List;

/**
 * Created by Frédéric Perron on 2019-01-12. This file
 * is under copyrights© as mentioned in the README file.
 */
public class Renderer {

    private Application application;
    private ShaderProgram shaderProgram;
    private Transformation transformation;

    public Renderer(Application application){
        this.application = application;
        transformation = new Transformation();
    }

    public void init() throws Exception {
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(application.getResourceManager().getResourceFolder().extractFileAsString(ApplicationSettings.RESOURCE_VERTEX_SHADER));
        shaderProgram.createFragmentShader(application.getResourceManager().getResourceFolder().extractFileAsString(ApplicationSettings.RESOURCE_FRAGMENT_SHADER));
        shaderProgram.link();

        shaderProgram.createUniform("matrixProjection");
        shaderProgram.createUniform("matrixModelView");
        shaderProgram.createUniform("texture_sampler");
        shaderProgram.createUniform("color");
        shaderProgram.createUniform("useColor");
    }

    public void render(List<GameObject> objects, Camera camera){
        checkWindowRatio();

        clear();
        shaderProgram.bind();
        Matrix4f projectionMatrix = transformation.getProjectionMatrix(ApplicationSettings.FOV, application.getDisplay().getWidth(),
                application.getDisplay().getHeight(), ApplicationSettings.Z_NEAR, ApplicationSettings.Z_FAR);
        shaderProgram.setUniform("matrixProjection", projectionMatrix);
        Matrix4f matrixView = transformation.getViewMatrix(camera);
        shaderProgram.setUniform("texture_sampler", 0);
        for(GameObject gameItem : objects) {
            Mesh mesh = gameItem.getMesh();
            Matrix4f matrixModelView = transformation.getModelViewMatrix(gameItem, matrixView);
            shaderProgram.setUniform("matrixModelView", matrixModelView);
            shaderProgram.setUniform("color", mesh.getColor());
            shaderProgram.setUniform("useColor", mesh.isTextured() ? 0 : 1);
            mesh.render();
        }
        shaderProgram.unbind();
    }

    public void cleanUp(){
        if (shaderProgram != null)
            shaderProgram.cleanUp();
    }

    public void checkWindowRatio(){
        if ( application.getDisplay().isResized() ) {
            GL11.glViewport(0,0,application.getDisplay().getWidth(), application.getDisplay().getHeight());
        }
    }

    private void clear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

}
