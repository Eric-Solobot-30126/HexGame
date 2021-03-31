package main.HexGame;

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;

public class GraphicInterface extends JFrame {

    private static final int width = 720, height = 480;
    private static final int  widthBorder=width/50 , heightBorder=height/50 , blockWidth =2*width/5 , blockHeight=height/10 ;
    private static final Font font = new Font("Verdana", Font.BOLD, 4*blockHeight/10);
    private static final Color color1 = new Color(200, 200, 200), color2 = new Color(111, 111, 111), color3 = new Color(50, 50, 50);
    int imageSize;
    boolean win=false;
    private Save save = new Save();
    JLabel instructions = new JLabel();
    JLabel LBackground = new JLabel();
    JLabel LLevel = new JLabel("Level " + save.getLvl());
    JLabel LMoney = new JLabel("Money " + save.getMoney());
    JLabel LGameBackground = new JLabel();
    JButton Shop = new JButton("Shop level " + save.getShop());
    private main.HexGame.HexMap HexMap;
    int TutorialNumberOfMoves = 0;
    JButton[][] Piece;
    JLabel LEnterName = new JLabel("Enter Name");
    JTextField TEnterName = new JTextField("");
    JLabel LFirstStateBoxBackground = new JLabel();
    JButton BEnterName = new JButton("Play");

    public GraphicInterface() {
        CreateWard();
        if(save.getLvl()==0) FirstState();
        else Game();
    }

    public void CreateWard() {
        setTitle("HexPuzzleGame");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(width+18, height+47);
        setLayout(null);
        Shop.setFont(font);
        Shop.setForeground(color1);
        Shop.setContentAreaFilled(false);
        Shop.setBorderPainted(false);
        Shop.setMnemonic('O');
        Shop.setMargin(new Insets(0,0,0,0));
        Shop.setBorder(null);
        Shop.setBounds(width-blockWidth-widthBorder,heightBorder,blockWidth,blockHeight);
        Shop.addActionListener(e -> { if(save.getMoney()>=save.getPrice()){save.setMoney(save.getMoney()-save.getPrice());save.setShop(save.getShop()+1);} });
        LLevel.setFont(font);
        LLevel.setForeground(color1);
        LLevel.setHorizontalAlignment(SwingConstants.CENTER);
        LLevel.setBounds((width-blockWidth)/2,heightBorder,blockWidth,blockHeight);
        LMoney.setFont(font);
        LMoney.setForeground(color1);
        LMoney.setHorizontalAlignment(SwingConstants.CENTER);
        LMoney.setBounds(widthBorder,heightBorder,blockWidth,blockHeight);
        if(save.getLvl()!=0) { add(Shop); add(LLevel); add(LMoney); }
        instructions.setHorizontalAlignment(SwingConstants.CENTER);
        LBackground.setBounds(0,0,width,height);
        LBackground.setBackground(color3);
        LBackground.setOpaque(true);
        JButton UpgradeDesign = new JButton("Upgrade Design");
        UpgradeDesign.setFont(font);
        UpgradeDesign.setBounds(width-blockWidth-blockWidth,heightBorder,blockWidth,blockHeight);
        UpgradeDesign.addActionListener(e -> {
            if(save.getMoney()>save.getPrice()) { save.setShop(save.getShop()+1); save.setPrice(2*save.getPrice()); }
        });
    }

    public void FirstState() {
        LFirstStateBoxBackground.setBackground(color2);
        LFirstStateBoxBackground.setOpaque(true);
        LEnterName.setFont(font);
        TEnterName.setFont(font);
        BEnterName.setFont(font);
        LEnterName.setForeground(color1);
        TEnterName.setForeground(color1);
        BEnterName.setForeground(color1);
        BEnterName.setContentAreaFilled(false);
        BEnterName.setBorderPainted(false);
        BEnterName.setMnemonic('O');
        BEnterName.setMargin(new Insets(0,0,0,0));
        BEnterName.setBorder(null);
        TEnterName.setBorder(BorderFactory.createLineBorder(color3,3,true));
        TEnterName.setBackground(color2);
        LEnterName.setHorizontalAlignment(SwingConstants.CENTER);
        LEnterName.setBounds((width-blockWidth)/2,(height-3*blockHeight)/2-heightBorder,blockWidth,blockHeight);
        TEnterName.setBounds((width-blockWidth)/2,(height-blockHeight)/2,blockWidth,blockHeight);
        BEnterName.setBounds((width-blockWidth)/2,(height+blockHeight)/2+heightBorder,blockWidth,blockHeight);
        LFirstStateBoxBackground.setBounds((width-blockWidth)/2-widthBorder,(height-3*blockHeight)/2-2*heightBorder,blockWidth+2*widthBorder,3*blockHeight+4*heightBorder);
        add(BEnterName);
        add(LEnterName);
        add(TEnterName);
        add(LFirstStateBoxBackground);
        add(LBackground);

        BEnterName.addActionListener(e -> {
            if(!TEnterName.getText().equals("")) {
                save.setName(TEnterName.getText());
                remove(BEnterName);
                remove(LEnterName);
                remove(TEnterName);
                remove(LFirstStateBoxBackground);
                remove(LBackground);
                revalidate();
                repaint();
                Tutorial();
            }
        });
    }

    public void Tutorial(){
        CreateTutorialMap();
        instructions.setVerticalTextPosition(SwingConstants.CENTER);
        instructions.setVerticalAlignment(SwingConstants.CENTER);
        instructions.setFont(font);
        instructions.setBounds(width/2-2*blockWidth , height/50,4*blockWidth,blockHeight);
        add(instructions);
        DisplayHexPieces();
    }

    public void CreateTutorialMap(){
        this.HexMap = new HexMap(3,3);
        HexPiece[][] TutorialHexPieces = new HexPiece[3][3];
        TutorialHexPieces[0][0] = new HexPiece(new Boolean[]{false,false,false,false,false,false});
        TutorialHexPieces[0][1] = new HexPiece(new Boolean[]{true,false,true,false,false,false});
        TutorialHexPieces[0][2] = new HexPiece(new Boolean[]{false,true,false,false,false,true});
        TutorialHexPieces[1][0] = new HexPiece(new Boolean[]{true,false,false,false,true,false});
        TutorialHexPieces[1][1] = new HexPiece(new Boolean[]{false,false,false,false,false,false});
        TutorialHexPieces[1][2] = new HexPiece(new Boolean[]{false,true,false,true,false,false});
        TutorialHexPieces[2][0] = new HexPiece(new Boolean[]{false,false,false,false,false,false});
        TutorialHexPieces[2][1] = new HexPiece(new Boolean[]{true,false,false,false,true,false});
        TutorialHexPieces[2][2] = new HexPiece(new Boolean[]{true,false,false,false,true,false});
        this.HexMap.setMap(TutorialHexPieces);
    }

    public void Game() {

        setLayout(null);

        int[] size = {save.getLvl()/17+3,save.getLvl()/17+3};
        this.HexMap = new HexMap(size[0],size[1]);
        DisplayHexPieces();
        LGameBackground.setBackground(color2);
        LGameBackground.setOpaque(true);
        LGameBackground.setBounds(Piece[0][0].getX()-widthBorder,Piece[0][0].getY()-heightBorder,HexMap.getWidth()*imageSize+imageSize/2+2*widthBorder,(1+3*HexMap.getHeight())*imageSize/4+2*heightBorder);
        add(LGameBackground);
        add(LBackground);

    }

    public void Win(){
        if(save.getLvl()==1) { add(LMoney); add(LLevel);instructions.setVisible(false);remove(instructions);add(Shop);}
        save.setLvl(save.getLvl()+1);
        LLevel.setText("Level " + save.getLvl());
        LMoney.setText("Money " + save.getMoney());
        for(int i = 0 ; i < HexMap.getWidth() ; i++){ for(int j = 0 ; j < HexMap.getHeight() ; j++) { Piece[i][j].setVisible(false); remove(Piece[i][j]); } }
        save.setMoney(save.getMoney()+ HexMap.getHeight());
        remove(LBackground);
        remove(LGameBackground);
        win=false;
        Game();
    }

    public void WinAnimation(){

        win=true;

        for (int i = 0 ; i < HexMap.getWidth() ; i++) {
            for (int j = 0; j < HexMap.getHeight(); j++) {
                setImagePiece(Piece[i][j] , i , j , "win");
            }
        }
    }

    public void DisplayHexPieces(){
        imageSize = Math.min((width-2*widthBorder)/HexMap.getWidth() , (height-3*heightBorder-blockHeight)/HexMap.getHeight());
        Piece = new JButton[HexMap.getWidth()][HexMap.getHeight()];
        for(int i = 0 ; i < HexMap.getWidth() ; i++){
            for(int j = 0 ; j < HexMap.getHeight() ; j++){
                Piece[i][j] = new JButton();
                setImagePiece(Piece[i][j] , i , j,"");
                if(j%2==0) Piece[i][j].setBounds((width-HexMap.getWidth()*imageSize-imageSize/2)/2+i*imageSize-2*i,(height-blockHeight-3*heightBorder)/2-HexMap.getHeight()*imageSize/2+3*imageSize*j/4+blockHeight+2*heightBorder-2*j,imageSize,imageSize);
                else Piece[i][j].setBounds((width-HexMap.getWidth()*imageSize-imageSize/2)/2+i*imageSize+imageSize/2-2*i,(height-blockHeight-3*heightBorder)/2-HexMap.getHeight()*imageSize/2+3*imageSize*j/4+blockHeight+2*heightBorder-2*j,imageSize,imageSize);
                int finalI = i, finalJ=j ;
                Piece[i][j].addActionListener(e -> {
                    if(!win) {
                        if (save.getLvl() == 0) {
                            TutorialNumberOfMoves++;
                            if (TutorialNumberOfMoves == 1)
                                instructions.setText("Make the lines connect with each other");
                            if (TutorialNumberOfMoves == 5) instructions.setText("Good Job, just connect them all");
                            if (TutorialNumberOfMoves == 10) instructions.setText("Keep going");
                            if (TutorialNumberOfMoves == 30) instructions.setText("You can do it");
                        }
                        HexPiece[][] a = this.HexMap.getMap();
                        Boolean[] b = a[finalJ][finalI].getConnector();
                        b = new Boolean[]{b[1], b[2], b[3], b[4], b[5], b[0]};
                        a[finalJ][finalI].setConnector(b);
                        this.HexMap.setMap(a);
                        setImagePiece(Piece[finalI][finalJ], finalI, finalJ, "");
                        if (HexMap.CheckWin()) WinAnimation();
                    }
                    else Win();
                });
                add(Piece[i][j]);
            }
        }
    }

    private void setImagePiece(JButton Piece , int i , int j , String win) {
        Piece.setContentAreaFilled(false);
        Piece.setBorderPainted(false);
        Piece.setMnemonic('O');
        Piece.setMargin(new Insets(0,0,0,0));
        Piece.setBackground(color2);
        Piece.setBorder(null);
        Integer[] iconName = new Integer[6];
        for(int k=0;k<iconName.length;k++) iconName[k] = (HexMap.getMap()[j][i].getConnector()[k])? 1:0;
        String URL="resources\\Piece[" + iconName[0] + "," + iconName[1] + "," + iconName[2] + "," + iconName[3] + "," + iconName[4] + "," + iconName[5] + "][" + save.getShop() + "]"+win+".png";
        Image resizable = new ImageIcon(URL).getImage().getScaledInstance(imageSize,imageSize,Image.SCALE_SMOOTH);
        Piece.setIcon(new ImageIcon(resizable));
    }

}