package main.HexGame;

public class HexMap {

    private static final int MaxHeight=20 , MaxWidth=20 , MinHeight=3 , MinWidth=3;
    private int width , height ;
    private HexPiece[][] Map;


    public HexMap(int width , int height) {
        this.width=Math.max(Math.min(width , MaxWidth) , MinWidth);
        this.height=Math.max(Math.min(height , MaxHeight) , MinHeight);
        this.Map=new HexPiece[this.width][this.height];
        for(int i = 0; i< this.width ; i++){
            for(int j = 0 ; j < this.height ; j++){
                this.Map[i][j]=new HexPiece();
                this.Map[i][j].setConnector(Edge( i , j , Map[i][j].getConnector() ));
            }
        }
        setPiece();
        Shuffle();

    }

    public Boolean[] Edge(int i, int j , Boolean[] edge){
        if ( i == 0 || ( j == this.width-1 && i % 2 == 1 ) ) { edge[0] = false; }
        if ( j == this.width-1 ) { edge[1] = false; }
        if ( i == this.height-1 || ( j == this.width-1 && i % 2 == 1 )) { edge[2]=false; }
        if ( i == this.height-1 || ( j == 0 && i % 2 == 0 ) ) { edge[3]=false; }
        if ( j == 0 ) { edge[4] = false; }
        if ( i == 0 || ( j == 0 && i % 2 == 0 ) ) { edge[5]= false; }
        return edge;
    }

    public void setPiece() {
        for(int i = 0; i< this.width ; i++){
            for(int j = 0 ; j < this.height ; j++){
                if (this.Map[i][j].getConnector()[0] && i%2==0){ Map[i-1][j].getConnector()[3] = true; }
                if (this.Map[i][j].getConnector()[0] && i%2==1){ Map[i-1][j+1].getConnector()[3] = true; }
                if (this.Map[i][j].getConnector()[1]){ Map[i][j+1].getConnector()[4] = true; }
                if (this.Map[i][j].getConnector()[2] && i%2==0){ Map[i+1][j].getConnector()[5] = true; }
                if (this.Map[i][j].getConnector()[2] && i%2==1){ Map[i+1][j+1].getConnector()[5] = true; }
                if (this.Map[i][j].getConnector()[3] && i%2==0){ Map[i+1][j-1].getConnector()[0] = true; }
                if (this.Map[i][j].getConnector()[3] && i%2==1){ Map[i+1][j].getConnector()[0] = true; }
                if (this.Map[i][j].getConnector()[4]){ Map[i][j-1].getConnector()[1] = true; }
                if (this.Map[i][j].getConnector()[5] && i%2==0){ Map[i-1][j-1].getConnector()[2] = true; }
                if (this.Map[i][j].getConnector()[5] && i%2==1){ Map[i-1][j].getConnector()[2] = true; }
            }
        }


    }

    public void Shuffle(){
        for(int i = 0; i< this.width ; i++){
            for(int j = 0 ; j < this.height ; j++){
                Map[i][j].ShuffleRotate();
            }
        }

    }

    public Boolean CheckWin(){
        for (int i = 0 ; i< width ; i++){
            for (int j = 0 ; j < height ; j++){
                if(!checkConnected(i,j)) return false;
            }
        }
        return true;
    }

    public boolean checkConnected(int i , int j) {
        if(i%2==0)
            return (CzechOneSideConnection(i,j,-1,0,3,0) &&
                CzechOneSideConnection(i,j,0,1,4,1) &&
                CzechOneSideConnection(i,j,1,0,5,2) &&
                CzechOneSideConnection(i,j,1,-1,0,3) &&
                CzechOneSideConnection(i,j,0,-1,1,4) &&
                CzechOneSideConnection(i,j,-1,-1,2,5));
        else
            return ( CzechOneSideConnection(i,j,-1,1,3,0) &&
                CzechOneSideConnection(i,j,0,1,4,1) &&
                CzechOneSideConnection(i,j,1,1,5,2) &&
                CzechOneSideConnection(i,j,1,0,0,3) &&
                CzechOneSideConnection(i,j,0,-1,1,4) &&
                CzechOneSideConnection(i,j,-1,0,2,5) );
    }

    public boolean CzechOneSideConnection(int i , int j , int pozI, int pozJ , int lat1 , int lat2){
        try{ return this.Map[i+pozI][j+pozJ].getConnector()[lat1] == this.Map[i][j].getConnector()[lat2]; }
        catch (ArrayIndexOutOfBoundsException ex){ return !Map[i][j].getConnector()[lat2];}
    }

    public void setMap(HexPiece[][] map) { this.Map = map; }
    public HexPiece[][] getMap() { return Map; }
    public int getHeight() { return height; }
    public int getWidth() { return width; }
}
