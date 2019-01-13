package ca.fredericperron.untitledgame.render;

import org.lwjgl.opengl.GL11;

/**
 * Created by Frédéric Perron on 2019-01-12. This file
 * is under copyrights© as mentioned in the README file.
 */
public class Renderer {

    public Renderer() {
    }

    public void init() {
    }

    public void clear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }
}
