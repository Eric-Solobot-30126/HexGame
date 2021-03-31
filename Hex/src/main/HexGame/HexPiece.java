package main.HexGame;

import java.util.Arrays;
import java.util.Random;

public class HexPiece {

    private Boolean[] connector = new Boolean[6];
    private int angle;
    private static final int chance=40;

    public HexPiece(Boolean[] connector){
        this.connector=connector;
    }

    public HexPiece() {
        Random random = new Random();
        angle=random.nextInt(6);
        this.connector = new Boolean[]{true,true,true,true,true,true};
        for (int i = 0 ; i < connector.length ; i++){ connector[i]=random.nextInt(100)<chance; }
    }

    public Boolean[] getConnector() { return connector; }

    public void setConnector(Boolean[] connector) { this.connector = connector; }

    public void ShuffleRotate(){
        for(int i=0 ; i<angle ; i++)
            setConnector(new Boolean[]{getConnector()[1],getConnector()[2],getConnector()[3],getConnector()[4],getConnector()[5],getConnector()[0]});
    }

}
