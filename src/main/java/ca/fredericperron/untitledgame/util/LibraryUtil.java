package ca.fredericperron.untitledgame.util;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

import java.io.PrintStream;

/**
 * Created by Frédéric Perron on 2019-01-10. This file
 * is under copyrights© as mentioned in the README file.
 */
public class LibraryUtil {

    public void setErrorCallBack(PrintStream stream){
        GLFWErrorCallback.createPrint(stream).set();
    }

    public void initGLFW() throws Exception {
        if(!GLFW.glfwInit())
            throw new Exception("Could not initialize GLFW library.");
    }

    private static LibraryUtil instance;

    public static LibraryUtil getInstance(){
        return instance== null ? instance = new LibraryUtil() : instance;
    }
}
