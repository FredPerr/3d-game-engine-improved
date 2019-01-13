package ca.fredericperron.untitledgame.display.input;

import ca.fredericperron.untitledgame.display.Display;
import org.lwjgl.glfw.GLFW;

/**
 * Created by Frédéric Perron on 2019-01-12. This file
 * is under copyrights© as mentioned in the README file.
 */
public class InputMouse extends Input {

    public InputMouse(int buttonCode){
        super(buttonCode);
    }

    public boolean isDown() {
        return GLFW.glfwGetMouseButton(Display.getInstance().getHandle(), getCode()) == GLFW.GLFW_PRESS;
    }
}
