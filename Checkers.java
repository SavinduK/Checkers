/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

import java.util.Vector;
import processing.core.*;

/**
 *
 * @author savindu
 */
public class Checkers  extends PApplet{
    public static void main(String[] args) {
        PApplet.main(new String[]{"checkers.Checkers"});
    }
    
 Vector<Piece> blackpieces; 
 Vector<Piece> whitepieces;
 public void settings(){
     size(640, 640);
 }
 public void setup(){
     setPieces();
 }
 public void draw(){
     background(15, 170, 120);
     drawBoard();
     for(Piece p : whitepieces){
         p.drawPiece(this);
     }
      for(Piece p : blackpieces){
         p.drawPiece(this);
     }
 }
 int X; int Y;
 boolean selected = false;// true - piece is selected to move| false - piece not selected
 int player;// 0 White 1 Black
 int move = 0; // 0 can't move 1 can move
 int nextmove =0; // 0 turn is over | 1 can move another time
 int index = -1; // index of the slected piece
 int game = 1; // 1-in game 0 in loading screen 
 public void mousePressed(){
  X = mouseX /80;
  Y = mouseY /80;
     System.out.println(X+"__"+Y);
   if(game == 1){
    if(selected == false){
        getPieceAt(X,Y);
        if(index >= 0){
         selected = true;
         System.out.println("index "+index + "player " + player);
        }
        else{
            System.out.println("Wrong piece");
            System.out.println("index "+index + "player " + player);
        }
    } 
    else if(selected == true){
        canMoveTo();
       System.out.println("move="+move+"nextmove="+nextmove);
      if(move == 1){
       if(player == 0){
       whitepieces.get(index).setX(X);
       whitepieces.get(index).setY(Y);
      if(whitepieces.get(index).getY()==7){
       whitepieces.get(index).setType(1);
      }
       }
       if(player ==1){
       blackpieces.get(index).setX(X);
       blackpieces.get(index).setY(Y); 
       if(blackpieces.get(index).getY()==0){
          blackpieces.get(index).setType(1);
       }
       }
       if(nextmove ==0){
    if(player == 0)  {
         player =1;
     }
     else if(player == 1){
         player =0;
     }
     selected = false;
     index = -1;
     move = 0;
     nextmove =0;
       }
       else if(nextmove == 1){
        move = 0;
       
       }
      }
      else if(move == 0){
          if(nextmove ==1){
            
        }
          else{
        index =-1;
        move =0;
        selected =false;
        nextmove =0;
          }
      }
   checkwinner();
    }   
   }
 }
 //-----------------------------------------------------------------------------------------------
    public void drawBoard(){
          fill(175,200,190);
          noStroke();
        for(int i=0;i<=640;i=i+160){
          rect(i,0,80,80); rect(i,160,80,80);
          rect(i,320,80,80); rect(i,480,80,80);
          rect(i,640,80,80);
        }
         for(int i=80;i<=640;i=i+160){
          rect(i,80,80,80); rect(i,240,80,80);
          rect(i,400,80,80); rect(i,560,80,80); 
        }}
    //-------------------------------------------------------------------------------------------- 
 public void setPieces(){
   whitepieces = new Vector<>();
   blackpieces = new Vector<>();
   
   whitepieces.add(new Piece(0,0,0,0));
   whitepieces.add(new Piece(2,0,0,0));
   whitepieces.add(new Piece(4,0,0,0));
   whitepieces.add(new Piece(6,0,0,0));  
   whitepieces.add(new Piece(0,2,0,0));
   whitepieces.add(new Piece(2,2,0,0));
   whitepieces.add(new Piece(4,2,0,0));
   whitepieces.add(new Piece(6,2,0,0)); 
   whitepieces.add(new Piece(1,1,0,0));
   whitepieces.add(new Piece(3,1,0,0));
   whitepieces.add(new Piece(5,1,0,0));
   whitepieces.add(new Piece(7,1,0,0)); 
   
   blackpieces.add(new Piece(1,7,1,0));
   blackpieces.add(new Piece(3,7,1,0));
   blackpieces.add(new Piece(5,7,1,0));
   blackpieces.add(new Piece(7,7,1,0));  
   blackpieces.add(new Piece(1,5,1,0));
   blackpieces.add(new Piece(3,5,1,0));
   blackpieces.add(new Piece(5,5,1,0));
   blackpieces.add(new Piece(7,5,1,0)); 
   blackpieces.add(new Piece(0,6,1,0));
   blackpieces.add(new Piece(2,6,1,0));
   blackpieces.add(new Piece(4,6,1,0));
   blackpieces.add(new Piece(6,6,1,0)); 
   
 }
 //-----------------------------------------------------------------------------------------------
 public void getPieceAt(int x,int y){
  if(player == 0) {
    for(int i=0;i<whitepieces.size();i++){
     if(whitepieces.get(i).getX()==x && whitepieces.get(i).getY()==y){
         index = i;
     }}
  }  
  if(player == 1) {
    for(int i=0;i<blackpieces.size();i++){
     if(blackpieces.get(i).getX()==x && blackpieces.get(i).getY()==y){
         index = i;
     }}
  }  
     
 }
 //-----------------------------------------------------------------------------------------------
 public void canMoveTo(){
    if(player == 0){
        int cx = whitepieces.get(index).getX();
        int cy = whitepieces.get(index).getY();
        int ctype = whitepieces.get(index).getType();
        moveWhiteMan(X,Y,cx,cy,ctype);
       movewhiteking(X,Y,cx,cy,ctype);
      }
      if(player == 1){
        int cx = blackpieces.get(index).getX();
        int cy = blackpieces.get(index).getY();
        int ctype = blackpieces.get(index).getType();
        moveBlackMan(X,Y,cx,cy,ctype);
        moveblackking(X,Y,cx,cy,ctype);
      } 
      
 }
 
 //-----------------------------------------------------------------------------------------------
 public void moveWhiteMan(int x,int y,int cx,int cy,int ctype){
   if(player == 0 && ctype == 0){
    if(y ==cy +1){
     if(x == cx+1|x == cx-1){
      if(checkempty(X,Y) == true) {
          move =1;
           if(checkmove()){
            move =0;
            System.out.println("capture piece before moving");
        }
      } 
      if(nextmove == 1){
          move =0;
      }
      //-----
     
    }}
    if(y==cy+2){
      if(checkempty(X,Y)==true){
        if(x == cx +2) {
        if (checkblack(cx+1,cy+1) == true){
            move = 1;
            capture(cx+1,cy+1);
        }
        if(move == 1){ 
            nextmove=0;
          if(checkempty(cx+4,cy+4) == true) {
            if(checkblack(cx+3,cy+3) == true){
              nextmove =1;  
            }}
          if(checkempty(cx,cy+4) == true){
            if(checkblack(cx+1,cy+3)){
             nextmove =1;   
            }}
        }
      }  
       if(x == cx -2) {
        if (checkblack(cx-1,cy+1) == true){
            move = 1;
            capture(cx-1,cy+1);
        } 
        if(move == 1){
            nextmove=0;
          if(checkempty(cx-4,cy+4)==true){
           if(checkblack(cx-3,cy+3)){
               nextmove =1;
           }}  
          if(checkempty(cx,cy+4) == true){
            if(checkblack(cx-1,cy+3)){
             nextmove =1;   
            }}
        }
      } 
     
    }}
 }}
 //----------------------------------------------------------------------------------------------
 public void moveBlackMan(int x,int y,int cx,int cy,int ctype){
   if(player == 1 && ctype == 0){
       if(y ==cy -1){
     if(x == cx+1|x == cx-1){
      if(checkempty(X,Y) == true) {
          move =1;
           if(checkmove()){
            move =0;
            System.out.println("capture piece before moving");
        }
      } 
      if(nextmove == 1){
          move =0;
      }
    }}
    if(y==cy-2){
      if(checkempty(X,Y)==true){
        if(x == cx +2) {
        if (checkwhite(cx+1,cy-1) == true){
            move = 1;
            capture(cx+1,cy-1);
        }
        if(move == 1){ 
            nextmove =0;
          if(checkempty(cx+4,cy-4) == true) {
            if(checkwhite(cx+3,cy-3) == true){
              nextmove =1; 
               
            }}
          if(checkempty(cx,cy-4) == true){
            if(checkwhite(cx+1,cy-3)){
             nextmove =1; 
               
            }}
        }
      }  
       if(x == cx -2) {
        if (checkwhite(cx-1,cy-1) == true){
            move = 1;
            capture(cx-1,cy-1);
        } 
        if(move == 1){
            nextmove =0;
          if(checkempty(cx-4,cy-4)==true){
           if(checkwhite(cx-3,cy-3)){
               nextmove =1;
               
           }}  
          if(checkempty(cx,cy-4) == true){
            if(checkwhite(cx-1,cy-3)){
             nextmove =1;
                
            }}
        }
      } 
    
    }}
    }}
 //-----------------------------------------------------------------------------------------------
public void moveblackking(int x,int y,int cx,int cy,int ctype){
  if(player == 1 && ctype == 1) {
         if(checkempty(x,y)){
   if(y == cy +1 |y == cy-1) {
      if(x == cx+1 | x == cx -1){
        move =1; 
        if(nextmove ==1){
            move =0;
        }
         if(checkmove()){
            move =0;
            System.out.println("capture piece before moving");
        }
      }}
   //  y++
   if(y == cy+2){
       //x++
    if(x == cx +2){
     if(checkwhite(cx+1,cy+1)==true) {
         move =1;
         capture(cx+1,cy+1);
       if(move ==1){
        nextmove=0;
        if(checkempty(cx+4,cy+4)){
         if(checkwhite(cx+3,cy+3)){
           nextmove=1;  
        }}
         if(checkempty(cx,cy+4)){
         if(checkwhite(cx+1,cy+3)){
           nextmove=1;  
        }}
         if(checkempty(cx+4,cy)){
         if(checkwhite(cx+3,cy+1)){
           nextmove=1;  
        }}
       }}
    }
    //x-- 
      if(x == cx -2){
     if(checkwhite(cx-1,cy+1)==true) {
         move =1;
         capture(cx-1,cy+1);
       if(move ==1){
        nextmove=0;
        if(checkempty(cx-4,cy+4)){
         if(checkwhite(cx-3,cy+3)){
           nextmove=1;  
        }}
         if(checkempty(cx,cy+4)){
         if(checkwhite(cx-1,cy+3)){
           nextmove=1;  
        }}
         if(checkempty(cx-4,cy)){
         if(checkwhite(cx-3,cy+1)){
           nextmove=1;  
        }}
       }}
    }
   }
   // y--
    if(y == cy-2){
       //x++
    if(x == cx +2){
     if(checkwhite(cx+1,cy-1)==true) {
         move =1;
         capture(cx+1,cy-1);
       if(move ==1){
        nextmove=0;
        if(checkempty(cx+4,cy-4)){
         if(checkwhite(cx+3,cy-3)){
           nextmove=1;  
        }}
         if(checkempty(cx,cy-4)){
         if(checkwhite(cx+1,cy-3)){
           nextmove=1;  
        }}
         if(checkempty(cx+4,cy)){
         if(checkwhite(cx+3,cy-1)){
           nextmove=1;  
        }}
       }}
    }
    //x-- 
      if(x == cx -2){
     if(checkwhite(cx-1,cy-1)==true) {
         move =1;
         capture(cx-1,cy-1);
       if(move ==1){
        nextmove=0;
        if(checkempty(cx-4,cy-4)){
         if(checkwhite(cx-3,cy-3)){
           nextmove=1;  
        }}
         if(checkempty(cx,cy-4)){
         if(checkwhite(cx-1,cy-3)){
           nextmove=1;  
        }}
         if(checkempty(cx-4,cy)){
         if(checkwhite(cx-3,cy-1)){
           nextmove=1;  
        }}
       }}
    }
   }
   }  
  
  }}
 //------------------------------------------------------------------------------------------------
public void movewhiteking(int x,int y,int cx,int cy,int ctype){
  if(player==0 && ctype == 1){
      if(checkempty(x,y)){
   if(y == cy +1 |y == cy-1) {
      if(x == cx+1 | x == cx -1){
        move =1; 
        if(nextmove ==1){
            move =0;
        }
        if(checkmove()){
            move =0;
            System.out.println("capture piece before moving");
        }
      }}
   //  y++
   if(y == cy+2){
       //x++
    if(x == cx +2){
     if(checkblack(cx+1,cy+1)==true) {
         move =1;
         capture(cx+1,cy+1);
       if(move ==1){
        nextmove=0;
        if(checkempty(cx+4,cy+4)){
         if(checkblack(cx+3,cy+3)){
           nextmove=1;  
        }}
         if(checkempty(cx,cy+4)){
         if(checkblack(cx+1,cy+3)){
           nextmove=1;  
        }}
         if(checkempty(cx+4,cy)){
         if(checkblack(cx+3,cy+1)){
           nextmove=1;  
        }}
       }}
    }
    //x-- 
      if(x == cx -2){
     if(checkblack(cx-1,cy+1)==true) {
         move =1;
         capture(cx-1,cy+1);
       if(move ==1){
        nextmove=0;
        if(checkempty(cx-4,cy+4)){
         if(checkblack(cx-3,cy+3)){
           nextmove=1;  
        }}
         if(checkempty(cx,cy+4)){
         if(checkblack(cx-1,cy+3)){
           nextmove=1;  
        }}
         if(checkempty(cx-4,cy)){
         if(checkblack(cx-3,cy+1)){
           nextmove=1;  
        }}
       }}
    }
   }
   // y--
    if(y == cy-2){
       //x++
    if(x == cx +2){
     if(checkblack(cx+1,cy-1)==true) {
         move =1;
         capture(cx+1,cy-1);
       if(move ==1){
        nextmove=0;
        if(checkempty(cx+4,cy-4)){
         if(checkblack(cx+3,cy-3)){
           nextmove=1;  
        }}
         if(checkempty(cx,cy-4)){
         if(checkblack(cx+1,cy-3)){
           nextmove=1;  
        }}
         if(checkempty(cx+4,cy)){
         if(checkblack(cx+3,cy-1)){
           nextmove=1;  
        }}
       }}
    }
    //x-- 
      if(x == cx -2){
     if(checkblack(cx-1,cy-1)==true) {
         move =1;
         capture(cx-1,cy-1);
       if(move ==1){
        nextmove=0;
        if(checkempty(cx-4,cy-4)){
         if(checkblack(cx-3,cy-3)){
           nextmove=1;  
        }}
         if(checkempty(cx,cy-4)){
         if(checkblack(cx-1,cy-3)){
           nextmove=1;  
        }}
         if(checkempty(cx-4,cy)){
         if(checkblack(cx-3,cy-1)){
           nextmove=1;  
        }}
       }}
    }
   }
   }
}}
//-------------------------------------------------------------------------------------------------
public boolean checkempty(int x,int y){
    if(x>=8 |x<=-1){
        
        return false;
    }
     if(y>=8 |y<=-1){
       
        return false;
    }
    int t =0;
   for(int i = 0;i<blackpieces.size();i++){
    if(blackpieces.get(i).getX()== x && blackpieces.get(i).getY()== y){
     t=1;   
    }}
   for(int i = 0;i<whitepieces.size();i++){
    if(whitepieces.get(i).getX()== x && whitepieces.get(i).getY()== y){
     t=1;   
    }}
   if(t==0){
     return true;
   }
   else {
   return false; 
   }
}
//-------------------------------------------------------------------------------------------------
public boolean checkblack(int x,int y){
    int t =0;
    for(int i = 0;i<blackpieces.size();i++){
    if(blackpieces.get(i).getX()== x && blackpieces.get(i).getY()== y){
     t=1;   
    }}
    if(t==1){
        return true;
    }
    else{
    return false;
    }
}
//-------------------------------------------------------------------------------------------------
public boolean checkwhite(int x,int y){
    int t =0;
    for(int i = 0;i<whitepieces.size();i++){
    if(whitepieces.get(i).getX()== x &&whitepieces.get(i).getY()== y){
     t=1;   
    }}
    if(t==1){
        //System.out.println("white");
        return true;
    }
    else{
        //System.out.println(" not white");
    return false;
    } 
}//------------------------------------------------------------------------------------------------
public void capture(int x,int y){
    if(player ==0 ){
      for(int i = 0;i<blackpieces.size();i++){
    if(blackpieces.get(i).getX()== x && blackpieces.get(i).getY()== y){
     blackpieces.remove(i);
    }}  
    }
    if(player == 1){
     for(int i = 0;i<whitepieces.size();i++){
    if(whitepieces.get(i).getX()== x &&whitepieces.get(i).getY()== y){
     whitepieces.remove(i);
    }}   
    }
}
//------------------------------------------------------------------------------------------------
public boolean checkmove(){
    // makes the player to capture pieces before normal move
    // testing
    int t =0;
    if(player == 0){
         for(int i=0;i<whitepieces.size();i++){
          int tx = whitepieces.get(i).getX();
          int ty = whitepieces.get(i).getY();
          int tt = whitepieces.get(i).getType();
          if(tt == 0){
         if(checkempty(tx+2,ty+2)) {
           if(checkblack(tx+1,ty+1)){
               t=1;
           }}
          if(checkempty(tx-2,ty+2)) {
           if(checkblack(tx-1,ty+1)){
               t=1; 
           }}
          }
          if(tt == 1){
            if(checkempty(tx+2,ty+2)) {
           if(checkblack(tx+1,ty+1)){
               t=1;
           }}
          if(checkempty(tx-2,ty+2)) {
           if(checkblack(tx-1,ty+1)){
               t=1; 
           }}
           if(checkempty(tx+2,ty-2)) {
           if(checkblack(tx+1,ty-1)){
               t=1;
           }}
          if(checkempty(tx-2,ty-2)) {
           if(checkblack(tx-1,ty-1)){
               t=1; 
           }}
          }
      }
    }
   if(player == 1){
     for(int i=0;i<blackpieces.size();i++){
          int tx = blackpieces.get(i).getX();
          int ty = blackpieces.get(i).getY();
          int tt = blackpieces.get(i).getType();
          if(tt == 0){
         if(checkempty(tx+2,ty-2)) {
           if(checkwhite(tx+1,ty-1)){
               t=1;
           }}
          if(checkempty(tx-2,ty-2)) {
           if(checkwhite(tx-1,ty-1)){
               t=1; 
           }}
          }
          if(tt == 1){
            if(checkempty(tx+2,ty+2)) {
           if(checkwhite(tx+1,ty+1)){
               t=1;
           }}
          if(checkempty(tx-2,ty+2)) {
           if(checkwhite(tx-1,ty+1)){
               t=1; 
           }}
           if(checkempty(tx+2,ty-2)) {
           if(checkwhite(tx+1,ty-1)){
               t=1;
           }}
          if(checkempty(tx-2,ty-2)) {
           if(checkwhite(tx-1,ty-1)){
               t=1; 
           }}
          }
      }
   }
   if(t==1){
       return true;
   }
   else{
    return false ;
   }
}
//------------------------------------------------------------------------------------------------
public void checkwinner(){
 if(player == 0){
     if(whitepieces.isEmpty()){
         System.out.println("black wins");   
     }
     if(checkmove()==false){
         int t =0;
       for(int i=0;i<whitepieces.size();i++){
          int tx = whitepieces.get(i).getX();
          int ty = whitepieces.get(i).getY();
          int tt = whitepieces.get(i).getType();
          if(tt == 0){
            if(checkempty(tx+1,ty+1)|checkempty(tx-1,ty+1)){
                t=1;
            }}
          if(tt == 1){
            if(checkempty(tx+1,ty+1)|checkempty(tx-1,ty+1)|checkempty(tx+1,ty-1)|checkempty(tx-1,ty-1)){
                t=1;
            }}
       }
       if(t==0){
           System.out.println("black wins");   
       }
     }
        
 } 
 if(player == 1){
    if(blackpieces.isEmpty()){
         System.out.println("white wins");   
     } 
     if(checkmove()==false){
         int t =0;
       for(int i=0;i<blackpieces.size();i++){
          int tx = blackpieces.get(i).getX();
          int ty = blackpieces.get(i).getY();
          int tt = blackpieces.get(i).getType();
          if(tt == 0){
            if(checkempty(tx+1,ty-1)|checkempty(tx-1,ty-1)){
                t=1;
            }}
          if(tt == 1){
            if(checkempty(tx+1,ty+1)|checkempty(tx-1,ty+1)|checkempty(tx+1,ty-1)|checkempty(tx-1,ty-1)){
                t=1;
            }}
       }
       if(t==0){
           System.out.println("white wins");   
       }
     }
 }
}
//----------------------------------------------------------------------------------------------
}

 
