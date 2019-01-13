package ca.fredericperron.untitledgame;

/**
 * Created by Frédéric Perron on 2019-01-10. This file
 * is under copyrights© as mentioned in the README file.
 */
public class Main {

    public static void main(String[] args){
        try {
            new Application();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
