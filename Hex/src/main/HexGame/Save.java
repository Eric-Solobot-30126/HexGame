package main.HexGame;

import java.io.*;

public class Save {

    private int lvl, money, price , shop;
    private String Name;

    public Save() {
        this.Name=null;
        this.lvl = 0;
        this.money = 0;
        this.price = 10;
        this.shop = 1;
        download();
    }

    public void download() {
        Decrypt();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\main\\HexGame\\SaveFile"));
            this.Name=bufferedReader.readLine().replaceAll("Name= ","");
            this.lvl=Integer.parseInt(bufferedReader.readLine().replaceAll("lvl= ",""));
            this.money=Integer.parseInt(bufferedReader.readLine().replaceAll("money= ",""));
            this.price= Integer.parseInt(bufferedReader.readLine().replaceAll("price= ",""));
            this.shop=Integer.parseInt(bufferedReader.readLine().replaceAll("shop= ",""));
            bufferedReader.close();
        } catch (IOException | NullPointerException | NumberFormatException ex1) {
            new File("src\\main\\HexGame\\SaveFile");
            writeInFile();
        }
        Encrypt();
    }

    public void upload() { Decrypt(); writeInFile(); Encrypt(); }

    private  void writeInFile() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src\\main\\HexGame\\SaveFile"));
            bufferedWriter.write("Name= " + this.Name);bufferedWriter.newLine();
            bufferedWriter.write("lvl= " + this.lvl);bufferedWriter.newLine();
            bufferedWriter.write("money= " + this.money);bufferedWriter.newLine();
            bufferedWriter.write("price= " + this.price);bufferedWriter.newLine();
            bufferedWriter.write("shop= " + this.shop);
            bufferedWriter.close();
        }
        catch (IOException e){ upload();}
    }

    private int getNextPrimeNumber(int prime){
        while(true){
            prime++;
            for(int i=3 ; i<Math.sqrt(prime) ; i++){
                if (prime % i == 0) {
                    return prime;
                }
            }
        }
    }

    private void Encrypt() {
        try {
            int prime = 132;
            StringBuilder EncryptString= new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\main\\HexGame\\SaveFile"));
            for(int j = 0 ; j < 5 ; j++) {
                String str = bufferedReader.readLine();
                for (int i = 1; i <= str.length() - 1; i++) {
                    prime = getNextPrimeNumber(prime);
                    str = str.substring(0, i) + (char) ((int) str.charAt(i) + prime) + str.substring(i + 1);
                }
                EncryptString.append(str).append('\n');
            }
            bufferedReader.close();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src\\main\\HexGame\\SaveFile"));
            bufferedWriter.write(EncryptString.toString());
            bufferedWriter.close();


        } catch (IOException ex ){
            new File("src\\main\\HexGame\\SaveFile");
            writeInFile();
        }

    }

    private void Decrypt(){
        try {
            int prime = 132;
            StringBuilder EncryptString= new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\main\\HexGame\\SaveFile"));
            for(int j = 0 ; j < 5 ; j++) {
                String str = bufferedReader.readLine();
                for (int i = 1; i <= str.length() - 1; i++) {
                    prime = getNextPrimeNumber(prime);
                    str = str.substring(0, i) + (char) ((int) str.charAt(i) - prime) + str.substring(i + 1);
                }
                EncryptString.append(str).append('\n');
            }
            bufferedReader.close();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src\\main\\HexGame\\SaveFile"));
            bufferedWriter.write(EncryptString.toString());
            bufferedWriter.close();


        } catch (IOException | NullPointerException ex ){
            new File("src\\main\\HexGame\\SaveFile");
            writeInFile();
        }

    }

    public int getPrice() { return price; }
    public int getMoney() { return money; }
    public int getLvl() { return lvl; }
    public int getShop() { return shop; }
    public String getName() { return Name; }
    public void setPrice(int price) { this.price = price; upload();}
    public void setLvl(int lvl) { this.lvl = lvl; upload();}
    public void setShop(int shop) { this.shop = shop; upload();}
    public void setName(String name) { Name = name; upload();}
    public void setMoney(int money) { this.money = money; upload();}

}
