package ca.fredericperron.untitledgame.display;

import ca.fredericperron.untitledgame.Application;
import ca.fredericperron.untitledgame.ApplicationSettings;
import ca.fredericperron.untitledgame.display.input.Input;

/**
 * Created by Frédéric Perron on 2019-01-12. This file
 * is under copyrights© as mentioned in the README file.
 */
public class Updater implements Runnable {

    private Application application;
    private final Thread updaterThread;
    private boolean running;

    public Updater(Application application){
        this.application = application;
        this.updaterThread = new Thread(this, "UPDATER_THREAD");
    }

    public void run() {
        try {
            application.init();
            loop();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void start(){
        running = true;
        if(System.getProperty("os.name").contains("Mac"))
            updaterThread.run();
        else
            updaterThread.start();
    }

    public void stop(){
        running = false;
    }

    public void loop(){
        long initialTime = System.nanoTime();
        final double timeU = 1000000000 / ApplicationSettings.UPS;
        final double timeF = 1000000000 / ApplicationSettings.FPS;
        double deltaU = 0, deltaF = 0;
        int frames = 0, ticks = 0;
        long timer = System.currentTimeMillis();

        while (running) {

            long currentTime = System.nanoTime();
            deltaU += (currentTime - initialTime) / timeU;
            deltaF += (currentTime - initialTime) / timeF;
            initialTime = currentTime;

            if (deltaU >= 1) {
                application.getDisplay().pollEvents();
                application.update();
                Input.update();
                if(application.getDisplay().shouldClose())
                    stop();
                ticks++;
                deltaU--;
            }

            if (deltaF >= 1) {
                application.render();
                application.getDisplay().swapBuffers();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                System.out.println(String.format("UPS: %s, FPS: %s", ticks, frames));
                frames = 0;
                ticks = 0;
                timer += 1000;
            }
        }
        application.end();
        application.cleanUp();
        System.exit(0);
    }
}
