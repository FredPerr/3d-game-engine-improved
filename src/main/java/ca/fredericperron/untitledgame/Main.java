package ca.fredericperron.untitledgame;

/**
 * Created by Frédéric Perron on 2019-01-10. This file
 * is under copyrights© as mentioned in the README file.
 */
public class Main {

    private static Application application = null;

    public static Application getApplication(){
        return application;
    }

    public static void main(String[] args){
        try {
            application = new Application();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
