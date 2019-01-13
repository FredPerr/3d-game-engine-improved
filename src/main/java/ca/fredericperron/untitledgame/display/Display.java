package ca.fredericperron.untitledgame.display;

import static ca.fredericperron.untitledgame.ApplicationSettings.*;
import static org.lwjgl.glfw.GLFW.*;
import ca.fredericperron.untitledgame.ApplicationSettings;
import ca.fredericperron.untitledgame.util.LibraryUtil;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

import java.nio.IntBuffer;

/**
 * Created by Frédéric Perron on 2019-01-10. This file
 * is under copyrights© as mentioned in the README file.
 */
public class Display {

    private long handle;
    private int width, height;
    private boolean resized;

    private Display(){
        instance = this;
        resized = false;

        try {
            create(DISPLAY_TITLE, DISPLAY_WIDTH, DISPLAY_HEIGHT, DISPLAY_RESIZABLE);
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private long create(String title, int width, int height, boolean resizable) throws Exception{
        this.width = width;
        this.height = height;
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

        glfwSetFramebufferSizeCallback(getHandle(), (window, w, h) ->{
            this.width = w;
            this.height = h;
            this.resized = true;
        });

        glfwMakeContextCurrent(handle);

        GL.createCapabilities();

        if(ApplicationSettings.VSYNC)
            glfwSwapInterval(1);

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

    public void setResized(boolean resized){
        this.resized = resized;
    }

    public boolean shouldClose(){
        return glfwWindowShouldClose(getHandle());
    }

    public GLFWVidMode getVideoMode(){
        return GLFW.glfwGetVideoMode(glfwGetPrimaryMonitor());
    }

    public long getHandle(){
        return this.handle;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public boolean isResized(){
        return this.resized;
    }

    private static Display instance;

    public static Display getInstance(){
        return instance == null ? instance = new Display() : instance;
    }
}
