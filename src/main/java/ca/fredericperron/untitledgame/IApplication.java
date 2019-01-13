package ca.fredericperron.untitledgame;

/**
 * Created by Frédéric Perron on 2019-01-12. This file
 * is under copyrights© as mentioned in the README file.
 */
public interface IApplication {

    void render();

    void update();

    void cleanUp();

    void end();
}
