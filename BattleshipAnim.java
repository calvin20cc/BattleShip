import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class BattleshipAnim extends JPanel{
  public boolean blnrepaint = true;
  // set picture appearance boolean pictures
  public boolean blnac01 = false;
  public boolean blnac02 = false;
  public boolean blnbs01 = false;
  public boolean blnbs02 = false;
  public boolean blndes01 = false;
  public boolean blndes02 = false;
  public boolean blnpb01 = false;
  public boolean blnpb02 = false;
  public boolean blnsub01 = false;
  public boolean blnsub02 = false;
  // set theme boolean variables
  public boolean blntheme1 = true;
  public boolean blntheme2 = false;
  // set boolean for player hit
  public boolean blnhit = false;
  public boolean blnEhit = false;
  // set boolean for win or lose
  public boolean blnwin = false;
  public boolean blnlose = false;
  // set boolean panel appearance
  public boolean blnmenu = true;
  public boolean blnabout = false;
  public boolean blnhelp1 = false;
  public boolean blnhelp2 = false;
  public boolean blnhelp3 = false;
  public boolean blnsetup = false;
  public boolean blnplaygame = false;
  public boolean blnplay = false;
  // to print out miss and hit
  public int intmap[][] = new int[10][10];
  public int intmapE[][] = new int[10][10];
  public int intshot = 2;
  public int intshot2 = 2;
  // image coordinates
  public int intacx = 200;
  public int intacy = 200;
  public int intbsx = 200;
  public int intbsy = 200;
  public int intdesx = 200;
  public int intdesy = 200;
  public int intpbx = 200;
  public int intpby = 200;
  public int intsubx = 200;
  public int intsuby = 200;
  public int intSx;
  public int intSy;
  public int intMx;
  public int intMy;

  // Override paintComponent method
  public void paintComponent(Graphics g){
    // cast to Graphics2D
    Graphics2D g2d = (Graphics2D)g;
    // clear rectangle
    g2d.clearRect(0, 0, 800, 800);
    g2d.setColor(Color.BLACK);
    // Menu Screen
    if(blnmenu){
      BufferedImage imagemenu =  null;
      try{
        imagemenu = ImageIO.read(new File("menu.png"));
      }catch(IOException e){
      }
      g2d.drawImage(imagemenu, 0, 0, null);
    }
    // About Screen
    if(blnabout){
      BufferedImage imageabout =  null;
      try{
        imageabout = ImageIO.read(new File("about.png"));
      }catch(IOException e){
      }
      g2d.drawImage(imageabout, 0, 0, null);
    }
    // Set Up screen
    if(blnsetup){
      BufferedImage imagesetup =  null;
      try{
        imagesetup = ImageIO.read(new File("setup.jpg"));
      }catch(IOException e){
      }
      g2d.drawImage(imagesetup, 0, 0, null);
    }
    // help screen 1
    if(blnhelp1){
      BufferedImage imagehelp1 =  null;
      try{
        imagehelp1 = ImageIO.read(new File("help1.png"));
      }catch(IOException e){
      }
      g2d.drawImage(imagehelp1, 0, 0, null);
    }
    // help screen 2
    if(blnhelp2){
      BufferedImage imagehelp2 =  null;
      try{
        imagehelp2 = ImageIO.read(new File("help2.png"));
      }catch(IOException e){
      }
      g2d.drawImage(imagehelp2, 0, 0, null);
    }
    // help screen 3
    if(blnhelp3){
      BufferedImage imagehelp3 =  null;
      try{
        imagehelp3 = ImageIO.read(new File("help3.png"));
      }catch(IOException e){
      }
      g2d.drawImage(imagehelp3, 0, 0, null);
    }
    // set your board screen
    if(blnplaygame == true){
      background(g2d);
      imageArray(g2d);
      shipBoard(g2d);
    }
    // play game scren
    if(blnplay == true){
      background(g2d);
      imageArray(g2d);
      drawGameBoard(g2d);
      endBanner(g2d);
    }
  }
  
  public void drawGameBoard(Graphics2D g2d){
    int intCount;
    int intx = 250;
    int inty = 250;
    // vertical
    for(intCount = 0; intCount < 11; intCount++){
      g2d.drawLine(intx, 250, intx, 650);
      intx = intx + 40;
    }
    // horizontal
    for(intCount = 0; intCount < 11; intCount++){
      g2d.drawLine(250, inty, 650, inty);
      inty = inty + 40;
    }
    int intx2 = 50;
    int inty2 = 50;
    // vertical
    for(intCount = 0; intCount < 11; intCount++){
      g2d.drawLine(intx2, 50, intx2, 250);
      intx2 = intx2 + 20;
    }
    // horizontal
    for(intCount = 0; intCount < 11; intCount++){
      g2d.drawLine(50, inty2, 250, inty2);
      inty2 = inty2 + 20;
    }
  }
  public void shipBoard(Graphics2D g2d){
    int intCount;
    int intx = 200;
    int inty = 200;
    // vertical
    for(intCount = 0; intCount < 11; intCount++){
      g2d.drawLine(intx, 200, intx, 600);
      intx = intx + 40;
    }
    // horizontal
    for(intCount = 0; intCount < 11; intCount++){
      g2d.drawLine(200, inty, 600, inty);
      inty = inty + 40;
    }
  }

  public void endBanner(Graphics2D g2d){
    // win banner
    if(blnwin == true){
      BufferedImage imagewin = null;
      try{
        imagewin = ImageIO.read(new File("win.jpg"));
      }catch(IOException e){
      }
      g2d.drawImage(imagewin, 0, 0, null);
    }
    // lose banner
    if(blnlose == true){
      BufferedImage imagelose = null;
      try{
        imagelose = ImageIO.read(new File("lose.jpg"));
      }catch(IOException e){
      }
      g2d.drawImage(imagelose, 0, 0, null);
    }
  }
  
  public void shotFire(Graphics2D g2d){ 
    // Record hit or miss into array
    if(blnhit || blnhit == false){
      if(intshot == 1){
        intmap[intSx][intSy] = 2;
        intshot = 2;
      }
      if(intshot == 0){
        intmap[intSx][intSy] = 1;
        intshot = 2;
      }
    }
    if(blnEhit || blnEhit == false){
      if(intshot2 == 1){
        intmapE[intMx][intMy] = 2;
        intshot2 = 2;
      }
      if(intshot2 == 0){
        intmapE[intMx][intMy] = 1;
        intshot2 = 2;
      }
    }
  }
  
  public void background(Graphics2D g2d){
    BufferedImage imagebattleship = null;
    BufferedImage imagebattleship2 = null;
    BufferedImage imageclassattack = null;
    BufferedImage imageclassattack2 =  null;
    try{
      imagebattleship = ImageIO.read(new File("Battleship Play Game.jpg"));
      imageclassattack = ImageIO.read(new File("School Play Game.jpg"));
      imagebattleship2 = ImageIO.read(new File("Battleship In Game.jpg"));
      imageclassattack2 = ImageIO.read(new File("School In Game.jpg"));
    }catch(IOException e){
    }
    // draw out background for theme 1
    if(blntheme1){
      g2d.drawImage(imagebattleship, 0, 0, null);
      if(blnplay){
        g2d.drawImage(imagebattleship2, 0, 0, null);
      }
    }
    // draw out background for theme 2
    if(blntheme2){
      g2d.drawImage(imageclassattack, 0, 0, null);
      if(blnplay){
        g2d.drawImage(imageclassattack2, 0, 0, null);
      }
    }
  }
  public void imageArray(Graphics2D g2d){
    String strFile = "";
    if(blntheme1){
      strFile = "BattleShip.txt";
    }else if(blntheme2){
      strFile = "ClassAttack.txt";
    }
    // set and initialize varibles
    String strImage[];
    String strLine = "";
    int intCount = -1;
    FileReader fread = null;
    BufferedReader bread = null;
    // open file
    try{
      fread = new FileReader(strFile);
      bread = new BufferedReader(fread);
    }catch(IOException e){
      System.out.println("File not found");
    }
    // read line
    while(strLine != null){
      try{
        strLine = bread.readLine();
      }catch(IOException e){
        System.out.println("Unable to read line"); 
      }
      intCount++;
    }
    // close file
    try{
      bread.close();
      fread.close();
      fread = new FileReader(strFile);
      bread = new BufferedReader(fread);
    }catch(IOException e){
      System.out.println("File not found");
    }
    // set the image file names into string array
    strImage = new String[intCount];
    
    for(int intCounter = 0; intCounter < intCount; intCounter++){
      try{
        strLine = bread.readLine();
        strImage[intCounter] = strLine;
      }catch(IOException e){
        System.out.println("Unable to read line"); 
      }
    }
    // close file
    try{
      bread.close();
      fread.close();
    }catch(IOException e){
      System.out.println("File not found");
    }
    // set the image array
    BufferedImage images[] = new BufferedImage[intCount];
    for(int intCounter = 0; intCounter < intCount; intCounter++){
      images[intCounter] = null;
      try{
        images[intCounter] = ImageIO.read(new File(strImage[intCounter]));
      }catch(IOException e){
      }
    }
    if(blnplaygame){
      int intx = 200;
      int inty = 200;
      // print out the pictures for the board
      for(intCount = 0; intCount < 10; intCount++){
        for(int intCount1 = 0; intCount1 < 10; intCount1++){
          g2d.drawImage(images[0], intx, inty, null);
          intx = intx + 40;
        }
        inty = inty +40;
        intx = 200;
      }
      // print out the side ship pictures
      g2d.drawImage(images[2], 50, 180, null);
      g2d.drawImage(images[7], 120, 200, null);
      g2d.drawImage(images[12], 50, 400, null);
      g2d.drawImage(images[17], 120, 400, null);
      g2d.drawImage(images[22], 50, 540, null);
      g2d.drawRect(5, 177, 170, 450);
      // determine and print out ship appearance pictures
      if(blnac01 == true){
        g2d.drawImage(images[3], intacx, intacy, null);
      }
      if(blnac02 == true){
        g2d.drawImage(images[4], intacx, intacy, null);
      }
      if(blnbs01 == true){
        g2d.drawImage(images[8], intbsx, intbsy, null);
      }
      if(blnbs02 == true){
        g2d.drawImage(images[9], intbsx, intbsy, null);
      }
      if(blndes01 == true){
        g2d.drawImage(images[18], intdesx, intdesy, null);
      }
      if(blndes02 == true){
        g2d.drawImage(images[19], intdesx, intdesy, null);
      }
      if(blnsub01 == true){
        g2d.drawImage(images[13], intsubx, intsuby, null);
      }
      if(blnsub02 == true){
        g2d.drawImage(images[14], intsubx, intsuby, null);
      }
      if(blnpb01 == true){
        g2d.drawImage(images[23], intpbx, intpby, null);
      }
      if(blnpb02 == true){
        g2d.drawImage(images[24], intpbx, intpby, null);
      }
    }
    if(blnplay){
      // print out board pictures and hit or miss 
      shotFire(g2d);
      int intx = 250;
      int inty = 250;
      int intx1 = 50;
      int inty1 = 50;
      for(intCount = 0; intCount < 10; intCount++){
        for(int intCount1 = 0; intCount1 < 10; intCount1++){
          if(intmapE[intCount1][intCount] == 0){
            g2d.drawImage(images[0], intx, inty, null);
          }else if(intmapE[intCount1][intCount] == 1){
            g2d.drawImage(images[27], intx, inty, null);
          }else if(intmapE[intCount1][intCount] == 2){
            g2d.drawImage(images[29], intx, inty, null);
          }
          intx = intx + 40;
        }
        inty = inty +40;
        intx = 250;
      }
      // print out pictures for small board
      for(intCount = 0; intCount < 10; intCount++){
        for(int intCount1 = 0; intCount1 < 10; intCount1++){
          g2d.drawImage(images[1], intx1, inty1, null);
          intx1 = intx1 + 20;
        }
        inty1 = inty1 +20;
        intx1 = 50;
      }
      // print out ships for small board
      if(blnac01 == true){
        intx = (intacx - 200)/2+50;
        inty = (intacy - 200)/2+50;
        g2d.drawImage(images[5],intx ,inty , null);
      }
      if(blnac02 == true){
        intx = (intacx - 200)/2+50;
        inty = (intacy - 200)/2+50;
        g2d.drawImage(images[6],intx ,inty , null);
      }
      if(blnbs01 == true){
        intx = (intbsx - 200)/2+50;
        inty = (intbsy - 200)/2+50;
        g2d.drawImage(images[10],intx ,inty , null);
      }
      if(blnbs02 == true){
        intx = (intbsx - 200)/2+50;
        inty = (intbsy - 200)/2+50;
        g2d.drawImage(images[11],intx ,inty , null);
      }
      if(blndes01 == true){
        intx = (intdesx - 200)/2+50;
        inty = (intdesy - 200)/2+50;
        g2d.drawImage(images[20],intx ,inty , null);
      }
      if(blndes02 == true){
        intx = (intdesx - 200)/2+50;
        inty = (intdesy - 200)/2+50;
        g2d.drawImage(images[21],intx ,inty , null);
      }
      if(blnpb01 == true){
        intx = (intpbx - 200)/2+50;
        inty = (intpby - 200)/2+50;
        g2d.drawImage(images[25],intx ,inty , null);
      }
      if(blnpb02 == true){
        intx = (intpbx - 200)/2+50;
        inty = (intpby - 200)/2+50;
        g2d.drawImage(images[26],intx ,inty , null);
      }
      if(blnsub01 == true){
        intx = (intsubx - 200)/2+50;
        inty = (intsuby - 200)/2+50;
        g2d.drawImage(images[15],intx ,inty , null);
      }
      if(blnsub02 == true){
        intx = (intsubx - 200)/2+50;
        inty = (intsuby - 200)/2+50;
        g2d.drawImage(images[16],intx ,inty , null);
      }
      // print out hit or miss for the small board
      intx1 = 50;
      inty1 = 50;
      for(intCount = 0; intCount < 10; intCount++){
        for(int intCount1 = 0; intCount1 < 10; intCount1++){
          if(intmap[intCount1][intCount] == 1){
            g2d.drawImage(images[28], intx1, inty1, null);
          }else if(intmap[intCount1][intCount] == 2){
            g2d.drawImage(images[30], intx1, inty1, null);
          }
          intx1 = intx1 + 20;
        }
        inty1 = inty1 +20;
        intx1 = 50;
      }
    }
  }
  
  public BattleshipAnim(){
    super();
  }
}
  