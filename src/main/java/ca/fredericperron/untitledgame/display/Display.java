package ca.fredericperron.untitledgame.display;

import static ca.fredericperron.untitledgame.ApplicationSettings.*;
import static org.lwjgl.glfw.GLFW.*;
import ca.fredericperron.untitledgame.util.LibraryUtil;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryUtil;

import java.nio.IntBuffer;

/**
 * Created by Frédéric Perron on 2019-01-10. This file
 * is under copyrights© as mentioned in the README file.
 */
public class Display {

    private IntBuffer tempBuff;
    private long handle;

    public Display(){
        instance = this;

        tempBuff = MemoryUtil.memAllocInt(1);

        try {
            create(DISPLAY_TITLE, DISPLAY_WIDTH, DISPLAY_HEIGHT, DISPLAY_RESIZABLE);
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private long create(String title, int width, int height, boolean resizable) throws Exception{

        LibraryUtil.getInstance().setErrorCallBack(System.err);

        try {
            LibraryUtil.getInstance().initGLFW();
        } catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, resizable ? GLFW_TRUE : GLFW_FALSE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);

        handle = glfwCreateWindow(width, height, title, 0/*Windowed mode when creating*/, 0);

        if(handle == 0)
            throw new Exception("The window could not be initialized.");

        glfwMakeContextCurrent(handle);
        centerOnScreen();
        glfwShowWindow(handle);
        return handle;
    }

    public void pollEvents(){
        glfwPollEvents();
    }

    public void swapBuffers(){
        glfwSwapBuffers(getHandle());
    }

    public void centerOnScreen(){
        glfwSetWindowPos(getHandle(),
                    (getVideoMode().width() - getWidth()) / 2,
                    (getVideoMode().height() - getHeight()) / 2);
    }

    public boolean shouldClose(){
        return glfwWindowShouldClose(getHandle());
    }

    public int getWidth(){
        glfwGetWindowSize(getHandle(), tempBuff, null);
        return tempBuff.get(0);
    }


    public int getHeight(){
        glfwGetWindowSize(getHandle(), null, tempBuff);
        return tempBuff.get(0);
    }

    public GLFWVidMode getVideoMode(){
        return GLFW.glfwGetVideoMode(glfwGetPrimaryMonitor());
    }

    public long getHandle(){
        return this.handle;
    }

    private static Display instance;

    public static Display getInstance(){
        return instance;
    }
}
