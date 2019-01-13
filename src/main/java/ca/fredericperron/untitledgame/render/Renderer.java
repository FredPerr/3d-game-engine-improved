package ca.fredericperron.untitledgame.render;

import ca.fredericperron.untitledgame.Application;
import ca.fredericperron.untitledgame.ApplicationSettings;
import ca.fredericperron.untitledgame.display.Display;
import ca.fredericperron.untitledgame.render.model.GameObject;
import ca.fredericperron.untitledgame.render.shader.ShaderProgram;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

/**
 * Created by Frédéric Perron on 2019-01-12. This file
 * is under copyrights© as mentioned in the README file.
 */
public class Renderer {

    private ShaderProgram shaderProgram;
    private Transformation transformation;

    public Renderer(){
        transformation = new Transformation();
    }

    public void init() throws Exception {
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(Application.getInstance().getResourceManager().getResourceFolder().extractFileAsString(ApplicationSettings.RESOURCE_VERTEX_SHADER));
        shaderProgram.createFragmentShader(Application.getInstance().getResourceManager().getResourceFolder().extractFileAsString(ApplicationSettings.RESOURCE_FRAGMENT_SHADER));
        shaderProgram.link();

        shaderProgram.createUniform("matrixProjection");
        shaderProgram.createUniform("matrixModelView");
    }

    public void render(GameObject[] objects, Camera camera){
        checkWindowRatio();

        clear();
        shaderProgram.bind();
        Matrix4f projectionMatrix = transformation.getProjectionMatrix(ApplicationSettings.FOV, Display.getInstance().getWidth(),
                Display.getInstance().getHeight(), ApplicationSettings.Z_NEAR, ApplicationSettings.Z_FAR);
        shaderProgram.setUniform("matrixProjection", projectionMatrix);
        Matrix4f matrixView = transformation.getViewMatrix(camera);
        for(GameObject gameItem : objects) {
            Matrix4f matrixModelView = transformation.getModelViewMatrix(gameItem, matrixView);
            shaderProgram.setUniform("matrixModelView", matrixModelView);
            gameItem.getMesh().render();
        }
        shaderProgram.unbind();
    }

    public void cleanUp(){
        if (shaderProgram != null)
            shaderProgram.cleanUp();
    }

    public void checkWindowRatio(){
        if ( Display.getInstance().isResized() ) {
            GL11.glViewport(0,0,Display.getInstance().getWidth(), Display.getInstance().getHeight());
        }
    }

    private void clear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

}
