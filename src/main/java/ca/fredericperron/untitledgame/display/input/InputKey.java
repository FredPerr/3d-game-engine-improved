package ca.fredericperron.untitledgame.display.input;

import ca.fredericperron.untitledgame.Main;
import ca.fredericperron.untitledgame.display.Display;
import org.lwjgl.glfw.GLFW;

/**
 * Created by Frédéric Perron on 2019-01-12. This file
 * is under copyrights© as mentioned in the README file.
 */
public class InputKey extends Input {

    public InputKey(int keyCode){
        super(keyCode);
    }

    public boolean isDown() {
        return GLFW.glfwGetKey(Main.getApplication().getDisplay().getHandle(), getCode()) == GLFW.GLFW_PRESS;
    }
}
