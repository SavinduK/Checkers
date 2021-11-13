
package checkers;
//@author savindu

import processing.core.PApplet;
import processing.core.PImage;

public class Piece {
  private int x;
  private int y;
  private int color; // black or white
  private int type; // man or king
  
String BlackKingPath = "C:\\Users\\savindu\\Documents\\NetBeansProjects\\Checkers\\blackking.png";
String BlackManPath = "C:\\Users\\savindu\\Documents\\NetBeansProjects\\Checkers\\blackman.png";
String WhiteKingPath = "C:\\Users\\savindu\\Documents\\NetBeansProjects\\Checkers\\whiteking.png";
String WhiteManPath = "C:\\Users\\savindu\\Documents\\NetBeansProjects\\Checkers\\whiteman.png";
PImage BlackKing ;PImage BlackMan ;PImage WhiteKing ;PImage WhiteMan ;
 public Piece(int x, int y, int color, int type) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.type = type;
    }

    public int getColor() {
        return color;
    }

    public int getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setType(int type) {
        this.type = type;
    }
   
    public void drawPiece(PApplet app){
      BlackMan = app.loadImage(BlackManPath, "png");
      BlackKing = app.loadImage(BlackKingPath, "png");
      WhiteMan = app.loadImage(WhiteManPath, "png");
      WhiteKing = app.loadImage(WhiteKingPath, "png");
      if(this.color == 0){
        // white
       if(this.type==0){
           app.image(WhiteMan, x*80, y*80, 80, 80);
       }
        if(this.type==1){
           app.image(WhiteKing, x*80, y*80, 80, 80);
       } }  
      
       if(this.color == 1){
        // black
       if(this.type==0){
           app.image(BlackMan, x*80, y*80, 80, 80);
       }
        if(this.type==1){
           app.image(BlackKing, x*80, y*80, 80, 80);
       } }  
     
    }
  
}
