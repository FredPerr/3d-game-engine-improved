package ca.fredericperron.untitledgame.render;

import ca.fredericperron.untitledgame.Application;
import ca.fredericperron.untitledgame.ApplicationSettings;
import ca.fredericperron.untitledgame.display.Display;
import ca.fredericperron.untitledgame.render.model.Mesh;
import ca.fredericperron.untitledgame.render.shader.ShaderProgram;
import ca.fredericperron.untitledgame.util.ResourceUtil;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL30.*;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

/**
 * Created by Frédéric Perron on 2019-01-12. This file
 * is under copyrights© as mentioned in the README file.
 */
public class Renderer {

    private ShaderProgram shaderProgram;

    public void init() throws Exception {
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(Application.getInstance().getResourceManager().getResourceFolder().extractFileAsString(ApplicationSettings.RESOURCE_VERTEX_SHADER));
        shaderProgram.createFragmentShader(Application.getInstance().getResourceManager().getResourceFolder().extractFileAsString(ApplicationSettings.RESOURCE_FRAGMENT_SHADER));
        shaderProgram.link();
    }

    public void render(){
        clear();
        checkWindowRatio();

    }

    public void clear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    public void cleanUp(){
        if (shaderProgram != null) {
            shaderProgram.cleanUp();
        }
    }

    private void checkWindowRatio(){
        if ( Display.getInstance().isResized() ) {
            GL11.glViewport(0,0,Display.getInstance().getWidth(), Display.getInstance().getHeight());
            Display.getInstance().setResized(false);
        }
    }
}
