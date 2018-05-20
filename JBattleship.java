import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.*;

public class JBattleship implements ActionListener, MouseListener, MouseMotionListener, KeyListener{
  // Properties
  
  // JFrame, panels buttons and text areas for user inputs
  public JFrame theframe;
  public BattleshipAnim thepanel;
  public BattleshipAnim thepanel2;
  public Timer thetimer;
  public JButton enterbut, aboutbut, helpbut, help2but, help3but, menubut1, menubut2;
  public JButton playbut;
  public JButton resetbut;
  public JButton donebut;
  public int intCount = 0;
  public int intmap[][] = new int[10][10];
  public int intships[][] = new int[5][2];
  public int intshots[][] = new int[10][10];
  public JTextField chattext;
  public JTextField nettext;
  public JTextField iptext, porttext;
  public JTextArea thearea;
  public JButton sendbut;
  public JRadioButton hostbut, clientbut, theme1but, theme2but;
  public JLabel turnlabel;
  public JScrollPane thespane;
  public SuperSocketMaster ssm;
  public Thread ssmthread;
  // Booleans for ship placements
  public boolean blnmoveac1 = false;
  public boolean blnmoveac2 = false;
  public boolean blnmovebs1 = false;
  public boolean blnmovebs2 = false;
  public boolean blnmovedes1 = false;
  public boolean blnmovedes2 = false;
  public boolean blnmovepb1 = false;
  public boolean blnmovepb2 = false;
  public boolean blnmovesub1 = false;
  public boolean blnmovesub2 = false;
  public boolean blnOverlap = false;
  public boolean blnplaced1 = false;
  public boolean blnplaced2 = false;
  public boolean blnplaced3 = false;
  public boolean blnplaced4 = false;
  public boolean blnplaced5 = false;
  public boolean blnhorizontal = false;
  public boolean blnvertical = true;
  // Booleans for turns
  public boolean blnplayer1;
  public boolean blnplayer2;
  // Boolean for networking
  public boolean blnserver;
  // variables for networking
  public String strIP;
  public int intPort;
  public String strName = "";
  public String strPlayerTurn = "";
  public String strMessage;
  // Variabels to keep track of shots
  public String strShotX;
  public String strShotY;
  public String strTurn = "1";
  public int inthits = 0;
  
  public void actionPerformed(ActionEvent evt){
    // To repaint panel if needed
    if(thepanel.blnrepaint == true){
      if(evt.getSource() == thetimer){
        //theframe.requestFocus();
        thepanel.repaint();
      }
    }
    
    // Button to play game
    if(evt.getSource() == enterbut){
      theframe.requestFocus();
      theframe.setSize(600, 600);
      thepanel.blnsetup = true;
      thepanel.blnmenu = false;
      thepanel.removeAll();
      thepanel.repaint();
      thepanel.revalidate();  
      
      // To choose which theme
      thepanel.add(theme1but);
      thepanel.add(theme2but);
      thepanel.add(donebut);
      // To choose host or client
      thepanel.add(clientbut);
      thepanel.add(hostbut);
      thepanel.add(porttext);      
      thepanel.repaint();
      thepanel.revalidate();
    }
    
    // Button to go into about screen
    if(evt.getSource() == aboutbut){
      theframe.requestFocus();
      theframe.setSize(600, 400);
      thepanel.blnabout = true;
      thepanel.blnmenu = false;
      thepanel.removeAll();
      thepanel.repaint();
      thepanel.revalidate();
      
      thepanel.add(menubut2);
      thepanel.repaint();
      thepanel.revalidate();
    }
    
    // To go visit the help screens
    if(evt.getSource() == helpbut){
      theframe.requestFocus();
      theframe.setSize(815, 800);
      thepanel.blnhelp1 = true;
      thepanel.removeAll();
      thepanel.repaint();
      thepanel.revalidate();  
      
      thepanel.add(help2but);
      thepanel.repaint();
      thepanel.revalidate();
    }    
    if(evt.getSource() == help2but){
      theframe.requestFocus();
      theframe.setSize(815, 800);
      thepanel.blnhelp2 = true;
      thepanel.blnhelp1 = false;
      thepanel.removeAll();
      thepanel.repaint();
      thepanel.revalidate();  
      
      thepanel.add(help3but);
      thepanel.repaint();
      thepanel.revalidate();
    }
    if(evt.getSource() == help3but){
      theframe.requestFocus();
      theframe.setSize(815, 800);
      thepanel.blnhelp3 = true;
      thepanel.blnhelp2 = false;
      thepanel.removeAll();
      thepanel.repaint();
      thepanel.revalidate(); 
      
      thepanel.add(menubut1);
      thepanel.repaint();
      thepanel.revalidate();
    }
    // Buttons to return to main menu from about and help screens
    if(evt.getSource() == menubut1){
      theframe.requestFocus();
      theframe.setSize(800, 800);
      thepanel.blnhelp3 = false;
      thepanel.blnmenu = true;
      thepanel.removeAll();
      thepanel.repaint();
      thepanel.revalidate(); 
      thepanel.add(helpbut);
      thepanel.add(aboutbut);
      thepanel.add(enterbut);
      
      thepanel.repaint();
      thepanel.revalidate();
    }
    if(evt.getSource() == menubut2){
      theframe.requestFocus();
      theframe.setSize(800, 800);
      thepanel.blnabout = false;
      thepanel.blnmenu = true;
      thepanel.removeAll();
      thepanel.repaint();
      thepanel.revalidate(); 
      thepanel.add(helpbut);
      thepanel.add(aboutbut);
      thepanel.add(enterbut);
      
      thepanel.repaint();
      thepanel.revalidate();
    }
    // Button to press when you are hosting
    if(evt.getSource() == hostbut){
      thepanel.removeAll();
      blnserver = true;
      // makes you player 1
      strPlayerTurn = "1";
      clientbut.setSelected(false);
      
      blnplayer1 = true;
      blnplayer2 = false;
      
      // shows theme options
      thepanel.add(theme1but);
      thepanel.add(theme2but);
      
      // shows done button and port box
      thepanel.add(porttext);
      thepanel.add(donebut);
      thepanel.add(hostbut);
      thepanel.add(clientbut);
    }
    
    // Button pressed when you are teh client
    if(evt.getSource() == clientbut){
      // makes you player 2 
      strPlayerTurn = "2";
      blnserver = false;
      hostbut.setSelected(false);
      
      blnplayer1 = true;
      blnplayer2 = false;
      
      // shows the done button, IP address box and port box
      thepanel.add(donebut);
      thepanel.add(iptext);
      thepanel.add(porttext);
      thepanel.repaint();
      thepanel.revalidate();
    }
    // Area to type in IP Address
    if(evt.getSource() == iptext){
      strIP = iptext.getText();
      System.out.println(strIP);
    }
    // Area to type in port number
    if(evt.getSource() == porttext){
      intPort = Integer.parseInt(porttext.getText());
      System.out.println(intPort);
    }
    // To choose original theme
    if(evt.getSource() == theme1but){
      theme2but.setSelected(false);
      thepanel.blntheme1 = true;
      thepanel.blntheme2 = false;
    }
    // to choose school theme
    if(evt.getSource() == theme2but){
      theme1but.setSelected(false);
      thepanel.blntheme1 = false;
      thepanel.blntheme2 = true;
    }
    // When done on hosting, client, theme page
    if(evt.getSource() == donebut){
      theframe.setSize(800, 800);
      // initializes the networks
      if(blnserver){
        ssm = new SuperSocketMaster(nettext, intPort);
      }
      if(blnserver == false){
        ssm = new SuperSocketMaster(nettext, strIP, intPort);
      }
      ssmthread = new Thread(ssm);
      ssmthread.start();
      thepanel.removeAll();
      thepanel.blnsetup = false;
      thepanel.blnplaygame = true;
      
      thepanel.add(playbut);
      thepanel.add(resetbut);
    }
    // After setup ships press this button to play game
    if(evt.getSource() == playbut){
      if(blnplaced1 && blnplaced2 && blnplaced3 && blnplaced4 && blnplaced5){
        theframe.requestFocus();
        thepanel.blnplay = true;
        thepanel.blnplaygame = false;
        thepanel.removeAll();
        thepanel.repaint();
        thepanel.revalidate();       
        
        thepanel.add(chattext);
        thepanel.add(thespane);
        thepanel.add(sendbut);
        if(strPlayerTurn.equals("1")){
          turnlabel.setText("My Turn");
        }else if(strPlayerTurn.equals("2")){
          turnlabel.setText("Opponent's Turn");
        }
        thepanel.add(turnlabel);
        thepanel.repaint();
        thepanel.revalidate();
      }
    }
    
    // If you mess up on ship placement press reset button to reset all the ships
    if(evt.getSource() == resetbut){
      thepanel.blnac01 = false;
      thepanel.blnac02 = false;
      blnmoveac1 = false;
      blnmoveac2 = false;
      blnplaced1 = false;
      thepanel.blnbs01 = false;
      thepanel.blnbs02 = false;
      blnmovebs1 = false;
      blnmovebs2 = false;
      blnplaced2 = false;
      thepanel.blndes01 = false;
      thepanel.blndes02 = false;
      blnmovedes1 = false;
      blnmovedes2 = false;
      blnplaced3 = false;
      thepanel.blnsub01 = false;
      thepanel.blnsub02 = false;
      blnmovesub1 = false;
      blnmovesub2 = false;
      blnplaced4 = false;
      thepanel.blnpb01 = false;
      thepanel.blnpb02 = false;
      blnmovepb1 = false;
      blnmovepb2 = false;
      blnplaced5 = false;
    }
    
    // text area to transfer information over network
    if(evt.getSource() == nettext){
      strMessage = nettext.getText();
      
      // Determines whose turn it it
      if(strMessage.equals("p1")){
        blnplayer1 = true;
        blnplayer2 = false;
      }else if(strMessage.equals("p2")){
        blnplayer1 = false;
        blnplayer2 = true; 
      }
      
      if(strMessage.length() < 2){
        strMessage = "dnasjbnd";
      }
      
      // Determines location of shot fired and who fired it
      if(strMessage.length() == 3){
        strShotX = strMessage.substring(0, 1);
        strShotY = strMessage.substring(1, 2);
        strTurn = strMessage.substring(2, 3);
        if(strShotX.equals("0") || strShotX.equals("1") || strShotX.equals("2") || strShotX.equals("3") || strShotX.equals("4") || strShotX.equals("5") || strShotX.equals("6") || strShotX.equals("7") || strShotX.equals("8") || strShotX.equals("9")){
          if(strShotY.equals("0") || strShotY.equals("1") || strShotY.equals("2") || strShotY.equals("3") || strShotY.equals("4") || strShotY.equals("5") || strShotY.equals("6") || strShotY.equals("7") || strShotY.equals("8") || strShotY.equals("9")){
            // Tells you if it's a hit
            if(intmap[Integer.parseInt(strShotX)][Integer.parseInt(strShotY)] != 0){
              thepanel.blnhit = true;
              thepanel.intSx = Integer.parseInt(strShotX);
              thepanel.intSy = Integer.parseInt(strShotY);
              thepanel.intshot = 1;
              ssm.sendText("hit");
              // Tells you if its's a miss
            }else if(intmap[Integer.parseInt(strShotX)][Integer.parseInt(strShotY)] == 0){
              thepanel.blnhit = false;
              thepanel.intSx = Integer.parseInt(strShotX);
              thepanel.intSy = Integer.parseInt(strShotY);
              thepanel.intshot = 0;
              ssm.sendText("miss");
            }
            // Changes the turn label
            if(strTurn.equals("1")){
              turnlabel.setText("My Turn");
            }else if(strTurn.equals("2")){
              turnlabel.setText("My Turn");
            }
          }
        }
      }
      // If one player wins sends message to other player saying they lose
      if(strMessage.equals("win")){
        thepanel.blnlose = true;
        ssm.sendText("lose");
      }
      
      // If you lose boolean becomes true to show picture
      if(strMessage.equals("lose")){
        thepanel.blnwin = true;
      }
      
      // If it's a miss variable becomes 0
      if(strMessage.equals("miss")){
        thepanel.intshot2 = 0;
      }
      // If it's a hit variable becomes 1
      if(strMessage.equals("hit")){
        thepanel.intshot2 = 1;
        inthits++;
        if(inthits == 17){
          ssm.sendText("win");
        }
      }
      thearea.append(nettext.getText() + "\n");
    }else if(evt.getSource() == sendbut){
      thearea.append(chattext.getText() + "\n");
      ssm.sendText(chattext.getText());
    }
  }
  
  public void mousePressed(MouseEvent evt){
  }
  
  public void mouseReleased(MouseEvent evt){
  }
  
  public void mouseClicked(MouseEvent evt){
    thepanel.requestFocus();
    // if statements to pick up the ships
    // aircraft
    if(thepanel.blnplay == false){
      if(evt.getX() >= 50 && evt.getX()<= 90 && evt.getY() >= 180 && evt.getY() <= 380){
        if(blnplaced1 == false){
          System.out.println("ac01");
          blnmoveac1 = true;
          blnmoveac2 = false;
        }
      }
      // battleship
      if(evt.getX() >= 120 && evt.getX()<= 160 && evt.getY() >= 200 && evt.getY() <= 360){
        if(blnplaced2 == false){
          System.out.println("bs01");
          blnmovebs1 = true;
          blnmovebs2 = false;
        }
      }
      // destroyer
      if(evt.getX() >= 120 && evt.getX()<= 160 && evt.getY() >= 400 && evt.getY() <= 520){
        if(blnplaced3 == false){
          System.out.println("des01");
          blnmovedes1 = true;
          blnmovedes2 = false;
        }
      }
      // submarine
      if(evt.getX() >= 50 && evt.getX()<= 90 && evt.getY() >= 400 && evt.getY() <= 520){
        if(blnplaced4 == false){
          System.out.println("sub01");
          blnmovesub1 = true;
          blnmovesub2 = false;
        }
      }
      // patrol boat
      if(evt.getX() >= 50 && evt.getX()<= 90 && evt.getY() >= 540 && evt.getY() <= 620){
        if(blnplaced5 == false){
          System.out.println("pb01");
          blnmovepb1 = true;
          blnmovepb2 = false;
        }
      }
      // left clicks
      if(evt.getButton() == 1){
        // to move ships around and to make sure they do not overlap
        // aircraft
        if(evt.getX() >= thepanel.intacx && evt.getX()<= thepanel.intacx + 40 && evt.getY() >= thepanel.intacy && evt.getY() <= thepanel.intacy + 200){
          if(blnmoveac1 == true || blnmoveac2 == true){
            blnplaced1 = true;
            checkOverlap(0);
          }
          if(blnOverlap == false && blnmoveac1 == true){
            blnmoveac1 = false;
          }else if(blnOverlap == false && blnmoveac2 == true){
            blnmoveac2 = false;
          }
        }
        // battleship
        if(evt.getX() >= thepanel.intbsx && evt.getX()<= thepanel.intbsx + 40 && evt.getY() >= thepanel.intbsy && evt.getY() <= thepanel.intbsy + 160){
          if(blnmovebs1 == true || blnmovebs2 == true){
            blnplaced2 = true;
            checkOverlap(1);
          }
          if(blnOverlap == false && blnmovebs1 == true){
            blnmovebs1 = false;
          }else if(blnOverlap == false && blnmovebs2 == true){
            blnmovebs2 = false;
          }
        }
        // destroyer
        if(evt.getX() >= thepanel.intdesx && evt.getX()<= thepanel.intdesx + 40 && evt.getY() >= thepanel.intdesy && evt.getY() <= thepanel.intdesy + 120){
          if(blnmovedes1 == true || blnmovedes2 == true){
            blnplaced3 = true;
            checkOverlap(2);
          }
          if(blnOverlap == false && blnmovedes1 == true){
            blnmovedes1 = false;
          }else if(blnOverlap == false && blnmovedes2 == true){
            blnmovedes2 = false;
          }
        }
        // submarine
        if(evt.getX() >= thepanel.intsubx && evt.getX()<= thepanel.intsubx + 40 && evt.getY() >= thepanel.intsuby && evt.getY() <= thepanel.intsuby + 120){
          if(blnmovesub1 == true || blnmovesub2 == true){
            blnplaced4 = true;
            checkOverlap(3);
          }
          if(blnOverlap == false && blnmovesub1 == true){
            blnmovesub1 = false;
          }else if(blnOverlap == false && blnmovesub2 == true){
            blnmovesub2 = false;
          }
        }
        // patrol boat
        if(evt.getX() >= thepanel.intpbx && evt.getX()<= thepanel.intpbx + 40 && evt.getY() >= thepanel.intpby && evt.getY() <= thepanel.intpby + 80){
          if(blnmovepb1 == true || blnmovepb2 == true){
            blnplaced5 = true;
            checkOverlap(4);
          }
          if(blnOverlap == false && blnmovepb1 == true){
            blnmovepb1 = false;
          }else if(blnOverlap == false && blnmovepb2 == true){
            blnmovepb2 = false;
          }
        }
      }
      // right clicks to turn picture 90 degrees right
      // aircraft
      if(evt.getButton() == 3){
        if(blnmoveac1 || blnmoveac2){
          intCount++;
          if(intCount%2 != 0){
            thepanel.blnac01 = false;
            thepanel.blnac02 = true;
            blnmoveac1 = false;
            blnmoveac2 = true;
          }
          if(intCount%2 == 0){
            thepanel.blnac01 = true;
            thepanel.blnac02 = false;
            blnmoveac1 = true;
            blnmoveac2 = false;
          }
        }
        // battleship
        if(blnmovebs1 || blnmovebs2){
          intCount++;
          if(intCount%2 != 0){
            thepanel.blnbs01 = false;
            thepanel.blnbs02 = true;
            blnmovebs1 = false;
            blnmovebs2 = true;
          }
          if(intCount%2 == 0){
            thepanel.blnbs01 = true;
            thepanel.blnbs02 = false;
            blnmovebs1 = true;
            blnmovebs2 = false;
          }
        }
        // destroyer
        if(blnmovedes1 || blnmovedes2){
          intCount++;
          if(intCount%2 != 0){
            thepanel.blndes01 = false;
            thepanel.blndes02 = true;
            blnmovedes1 = false;
            blnmovedes2 = true;
          }
          if(intCount%2 == 0){
            thepanel.blndes01 = true;
            thepanel.blndes02 = false;
            blnmovedes1 = true;
            blnmovedes2 = false;
          }
        }
        // submarine
        if(blnmovesub1 || blnmovesub2){
          intCount++;
          if(intCount%2 != 0){
            thepanel.blnsub01 = false;
            thepanel.blnsub02 = true;
            blnmovesub1 = false;
            blnmovesub2 = true;
            blnvertical = false;
            blnhorizontal = true;
          }
          if(intCount%2 == 0){
            thepanel.blnsub01 = true;
            thepanel.blnsub02 = false;
            blnmovesub1 = true;
            blnmovesub2 = false;
            blnvertical = true;
            blnhorizontal = false;
          }
        }
        // patrol boat
        if(blnmovepb1 || blnmovepb2){
          intCount++;
          if(intCount%2 != 0){
            thepanel.blnpb01 = false;
            thepanel.blnpb02 = true;
            blnmovepb1 = false;
            blnmovepb2 = true;
            blnvertical = false;
            blnhorizontal = true;
          }
          if(intCount%2 == 0){
            thepanel.blnpb01 = true;
            thepanel.blnpb02 = false;
            blnmovepb1 = true;
            blnmovepb2 = false;
            blnvertical = true;
            blnhorizontal = false;
          }
        }
        thepanel.repaint();
      }
    }
    // To determine the turn and who gets to click
    if(thepanel.blnplay){
      if(thepanel.blnwin == false && thepanel.blnlose == false){
        if(evt.getButton() == 1){
          // When clicked inside of these coordinate, also known as the opponenet's board
          if(evt.getX() >= 250 && evt.getX() <= 650 && evt.getY() >= 250 && evt.getY() <= 650){
            System.out.println("blnplayer1 " + blnplayer1);
            System.out.println("blnplayer2 " + blnplayer2);
            System.out.println("player " + strPlayerTurn);
            // when blnplayer1 is true and it is player 1 clicking then they are allowed to click on the board, player 2 cannot do anything
            if(blnplayer1 && strPlayerTurn.equals("1")){
              
              thepanel.intMx = (evt.getX() - 250)/40;
              thepanel.intMy = (evt.getY() - 250)/40;
              ssm.sendText(Integer.toString(thepanel.intMx)+Integer.toString(thepanel.intMy)+strPlayerTurn);
              if(strPlayerTurn.equals("1")){
                turnlabel.setText("Opponent's Turn");
              }else if(strPlayerTurn.equals("2")){
                turnlabel.setText("My Turn");
              }
              blnplayer1 = false;
              
              ssm.sendText("p2");
              System.out.println("handle player1 ");
              // when blnplayer2 is true and it is player 2 clicking then they are allowed to click on the board, player 1 cannot do anything
            }else if(blnplayer2 && strPlayerTurn.equals("2")){
              //}else if(strPlayerTurn.equals("2")){
              //blnplayer2 = true;
              
              thepanel.intMx = (evt.getX() - 250)/40;
              thepanel.intMy = (evt.getY() - 250)/40;
              ssm.sendText(Integer.toString(thepanel.intMx)+Integer.toString(thepanel.intMy)+strPlayerTurn);
              if(strPlayerTurn.equals("1")){
                turnlabel.setText("My Turn");
              }else if(strPlayerTurn.equals("2")){
                turnlabel.setText("Opponenet's Turn");
              }
              blnplayer2 = false;
              ssm.sendText("p1");
              System.out.println("handle player 2");
            }            
          }
        }
      }      
    }
  }
  
  public void mouseExited(MouseEvent evt){
    
  }
  
  public void mouseEntered(MouseEvent evt){
    
  }
  
  public void mouseDragged(MouseEvent evt){
    
  }
  
  // to track the mouse movement and placement of the ships
  public void mouseMoved(MouseEvent evt){
    // aircraft
    if(blnmoveac1 == true){
      thepanel.intacx = (evt.getX()/40)*40;
      thepanel.intacy = (evt.getY()/40)*40;
      if(evt.getX() <= 200 || evt.getX() >= 400 || evt.getY() <= 200 || evt.getY() >= 420){
        thepanel.blnac01 = false;
      }
      if(evt.getX() >= 200 && evt.getX() <= 600 && evt.getY() >= 200 && evt.getY() <= 420){
        thepanel.blnac01 = true;
      }
    }
    // aircraft rotated
    if(blnmoveac2 == true){
      thepanel.intacx = (evt.getX()/40)*40;
      thepanel.intacy = (evt.getY()/40)*40;
      if(evt.getX() <= 200 || evt.getX() > 440 || evt.getY() <= 200 || evt.getY() >= 600){
        thepanel.blnac02 = false;
      }
      if(evt.getX() >= 200 && evt.getX() < 401 && evt.getY() >= 200 && evt.getY() <= 600){
        thepanel.blnac02 = true;
      }
    }
    // battleship
    if(blnmovebs1 == true){
      thepanel.intbsx = (evt.getX()/40)*40;
      thepanel.intbsy = (evt.getY()/40)*40;
      if(evt.getX() <= 200 || evt.getX() >= 400 || evt.getY() <= 200 || evt.getY() >= 460){
        thepanel.blnbs01 = false;
      }
      if(evt.getX() >= 200 && evt.getX() <= 600 && evt.getY() >= 200 && evt.getY() <= 460){
        thepanel.blnbs01 = true;
      } 
    }
    // battleship rotated
    if(blnmovebs2 == true){
      thepanel.intbsx = (evt.getX()/40)*40;
      thepanel.intbsy = (evt.getY()/40)*40;
      if(evt.getX() <= 200 || evt.getX() > 480 || evt.getY() <= 200 || evt.getY() >= 600){
        thepanel.blnbs02 = false;
      }
      if(evt.getX() >= 200 && evt.getX() < 441 && evt.getY() >= 200 && evt.getY() <= 600){
        thepanel.blnbs02 = true;
      }
    }
    // destroyer
    if(blnmovedes1 == true){
      thepanel.intdesx = (evt.getX()/40)*40;
      thepanel.intdesy = (evt.getY()/40)*40;
      if(evt.getX() <= 200 || evt.getX() >= 400 || evt.getY() <= 200 || evt.getY() >= 500){
        thepanel.blndes01 = false;
      }
      if(evt.getX() >= 200 && evt.getX() <= 600 && evt.getY() >= 200 && evt.getY() <= 500){
        thepanel.blndes01 = true;
      } 
    }
    // destroyer
    if(blnmovedes2 == true){
      thepanel.intdesx = (evt.getX()/40)*40;
      thepanel.intdesy = (evt.getY()/40)*40;
      if(evt.getX() <= 200 || evt.getX() > 520 || evt.getY() <= 200 || evt.getY() >= 600){
        thepanel.blndes02 = false;
      }
      if(evt.getX() >= 200 && evt.getX() < 481 && evt.getY() >= 200 && evt.getY() <= 600){
        thepanel.blndes02 = true;
      }
    }
    // submarine
    if(blnmovesub1 == true){
      thepanel.intsubx = (evt.getX()/40)*40;
      thepanel.intsuby = (evt.getY()/40)*40;
      if(evt.getX() <= 200 || evt.getX() >= 400 || evt.getY() <= 200 || evt.getY() >= 500){
        thepanel.blnsub01 = false;
      }
      if(evt.getX() >= 200 && evt.getX() <= 600 && evt.getY() >= 200 && evt.getY() <= 500){
        thepanel.blnsub01 = true;
      } 
    }
    // submarine rotate
    if(blnmovesub2 == true){
      thepanel.intsubx = (evt.getX()/40)*40;
      thepanel.intsuby = (evt.getY()/40)*40;
      if(evt.getX() <= 200 || evt.getX() > 520 || evt.getY() <= 200 || evt.getY() >= 600){
        thepanel.blnsub02 = false;
      }
      if(evt.getX() >= 200 && evt.getX() < 481 && evt.getY() >= 200 && evt.getY() <= 600){
        thepanel.blnsub02 = true;
      }
    }
    // patrol boat
    if(blnmovepb1 == true){
      thepanel.intpbx = (evt.getX()/40)*40;
      thepanel.intpby = (evt.getY()/40)*40;
      if(evt.getX() <= 200 || evt.getX() >= 400 || evt.getY() <= 200 || evt.getY() >= 540){
        thepanel.blnpb01 = false;
      }
      if(evt.getX() >= 200 && evt.getX() <= 600 && evt.getY() >= 200 && evt.getY() <= 540){
        thepanel.blnpb01 = true;
      } 
    }
    // patrol rotate
    if(blnmovepb2 == true){
      thepanel.intpbx = (evt.getX()/40)*40;
      thepanel.intpby = (evt.getY()/40)*40;
      if(evt.getX() <= 200 || evt.getX() > 560 || evt.getY() <= 200 || evt.getY() >= 600){
        thepanel.blnpb02 = false;
      }
      if(evt.getX() >= 200 && evt.getX() < 521 && evt.getY() >= 200 && evt.getY() <= 600){
        thepanel.blnpb02 = true;
      }
    }
  }
  
  
  public void keyPressed(KeyEvent evt){
    if(evt.getKeyCode() == KeyEvent.VK_1){
    }
  }
  public void keyReleased(KeyEvent evt){
    
  }
  
  public void keyTyped(KeyEvent evt){
    
  }
  
  public void setMap(){
    for(int intCountRow = 0; intCountRow < 10; intCountRow++){
      for(int intCountCol = 0; intCountCol < 10; intCountCol++){
        // makes map 0 for ship tracking, 0-1
        intmap[intCountRow][intCountCol] = 0;
        // makes map 0 for shot tracking, 0-2
        thepanel.intmap[intCountRow][intCountCol] = 0;
      }
    }
  }
  
  public void shipArray(){
    // the amount of space each ship takes horizontal and vertically placed
    intships[0][0] = 1;
    intships[0][1] = 5;
    intships[1][0] = 2;
    intships[1][1] = 4;
    intships[2][0] = 3;
    intships[2][1] = 3;
    intships[3][0] = 4;
    intships[3][1] = 3;
    intships[4][0] = 5;
    intships[4][1] = 2;
  }
  
  public void setShips(){
    shipArray();
    int intx = 0;
    int inty = 0;
    // intitalizes the map placement array with the number of spots the ship takes up
    if(blnplaced1 == true){
      intx = (thepanel.intacx-200)/40;
      inty = (thepanel.intacy-200)/40;
      for(intCount = 0; intCount < intships[0][1]; intCount++){ 
        intmap[intx][inty] = intships[0][0];
        if(blnmoveac1){
          inty++;
        }else if(blnmoveac2){
          intx++;
        }
      }
    }
    if(blnplaced2 == true){
      intx = (thepanel.intbsx-200)/40;
      inty = (thepanel.intbsy-200)/40;
      for(intCount = 0; intCount < intships[1][1]; intCount++){ 
        intmap[intx][inty] = intships[1][0];
        if(blnmovebs1){
          inty++;
        }else if(blnmovebs2){
          intx++;
        }
      }
    }
    if(blnplaced3 == true){
      intx = (thepanel.intdesx-200)/40;
      inty = (thepanel.intdesy-200)/40;
      for(intCount = 0; intCount < intships[2][1]; intCount++){ 
        intmap[intx][inty] = intships[2][0];
        if(blnmovedes1){
          inty++;
        }else if(blnmovedes2){
          intx++;
        }
      }
    }
    if(blnplaced4 == true){
      intx = (thepanel.intsubx-200)/40;
      inty = (thepanel.intsuby-200)/40;
      for(intCount = 0; intCount < intships[3][1]; intCount++){ 
        intmap[intx][inty] = intships[3][0];
        if(blnmovesub1){
          inty++;
        }else if(blnmovesub2){
          intx++;
        }
      }
    }
    if(blnplaced5 == true){
      intx = (thepanel.intpbx-200)/40;
      inty = (thepanel.intpby-200)/40;
      for(intCount = 0; intCount < intships[4][1]; intCount++){ 
        intmap[intx][inty] = intships[4][0];
        if(blnmovepb1){
          inty++;
        }else if(blnmovepb2){
          intx++;
        }
      }
    }
  }
  
  public void checkOverlap(int intnumbership){
    // Tracks where each ship is placed
    int intCheck = 0;
    int intx = 0;
    int inty = 0;
    // reduces the coordinates to single digits to fit into the array
    if(intships[intnumbership][0] == 1){
      intx = (thepanel.intacx-200)/40;
      inty = (thepanel.intacy-200)/40;
    }else if(intships[intnumbership][0] == 2){
      intx = (thepanel.intbsx-200)/40;
      inty = (thepanel.intbsy-200)/40;
    }else if(intships[intnumbership][0] == 3){
      intx = (thepanel.intdesx-200)/40;
      inty = (thepanel.intdesy-200)/40;
    }else if(intships[intnumbership][0] == 4){
      intx = (thepanel.intsubx-200)/40;
      inty = (thepanel.intsuby-200)/40;
    }else if(intships[intnumbership][0] == 5){
      intx = (thepanel.intpbx-200)/40;
      inty = (thepanel.intpby-200)/40;
    }
    shipArray();
    for(intCount = 0; intCount < intships[intnumbership][1]; intCount++){
      // check for overlapping
      if(intmap[intx][inty] != 0 && intmap[intx][inty] != intships[intnumbership][0]){
        blnOverlap = true;
        intCheck++;
        System.out.println(intCheck);
      }else if(intCheck == 0){ // if overlaps doesn't allow you place down the ship
        blnOverlap = false;
      }
      if(blnmoveac1 || blnmovebs1 || blnmovedes1 || blnmovesub1 || blnmovepb1){
        inty++;
      }else if(blnmoveac2 || blnmovebs2 || blnmovedes2 || blnmovesub2 || blnmovepb2){
        intx++;
      }
    }
    // Tells you if okay to place or if you need to move ship
    if(blnOverlap == false){
      System.out.println("ok to touch");
      setShips();
    }else if(blnOverlap == true){
      System.out.println("dont touch");
    }
  }
  

  // Constructor
  public JBattleship(){
    theframe = new JFrame("Battleship Java");
    theframe.setSize(800, 800);
    theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    thepanel = new BattleshipAnim();
    thepanel.addMouseListener(this);
    thepanel.addMouseMotionListener(this);
    thepanel.setLayout(null);
    
    playbut = new JButton("Play");
    playbut.setSize(100, 25);
    playbut.setLocation(275, 0);
    playbut.addActionListener(this);
    
    enterbut = new JButton("Enter Game");
    enterbut.setSize(100, 50);
    enterbut.setLocation(330, 305);
    enterbut.addActionListener(this);
    thepanel.add(enterbut);
    
    aboutbut = new JButton("About");
    aboutbut.setSize(100, 50);
    aboutbut.setLocation(330, 380);
    aboutbut.addActionListener(this);
    thepanel.add(aboutbut);
    
    helpbut = new JButton("Help");
    helpbut.setSize(100, 50);
    helpbut.setLocation(330, 455);
    helpbut.addActionListener(this);
    thepanel.add(helpbut);
    
    help2but = new JButton("Next");
    help2but.setSize(100, 25);
    help2but.setLocation(650, 0);
    help2but.addActionListener(this);
    
    help3but = new JButton("Next");
    help3but.setSize(100, 25);
    help3but.setLocation(650, 0);
    help3but.addActionListener(this);
    
    menubut2 = new JButton("Menu");
    menubut2.setSize(100, 25);
    menubut2.setLocation(450, 0);
    menubut2.addActionListener(this);
    
    menubut1 = new JButton("Menu");
    menubut1.setSize(100, 25);
    menubut1.setLocation(650, 0);
    menubut1.addActionListener(this);
    
    resetbut = new JButton("Reset");
    resetbut.setSize(100, 25);
    resetbut.setLocation(405, 0);
    resetbut.addActionListener(this);
    
    chattext = new JTextField();
    chattext.setSize(210, 50);
    chattext.setLocation(20, 600);
    
    thearea = new JTextArea(5, 20);
    thearea.setSize(210, 300);
    thearea.setLocation(20, 300);
    thearea.setVisible(true);
    thearea.setEditable(false);
    thespane = new JScrollPane(thearea);
    thespane.setBounds(20, 300, 210, 300);
    
    sendbut = new JButton("Send");
    sendbut.setSize(210, 25);
    sendbut.setLocation(20, 650);
    sendbut.addActionListener(this);
    
    hostbut = new JRadioButton();
    hostbut.setText("Host");
    hostbut.addActionListener(this);
    hostbut.setSize(75, 25);
    hostbut.setLocation(150, 300);
    
    clientbut = new JRadioButton();
    clientbut.setText("Client");
    clientbut.addActionListener(this);
    clientbut.setSize(75, 25);
    clientbut.setLocation(300, 300);
    
    theme1but = new JRadioButton();
    theme1but.setText("Original Theme");
    theme1but.addActionListener(this);
    theme1but.setSize(125, 25);
    theme1but.setLocation(150, 250);
    
    theme2but = new JRadioButton();
    theme2but.setText("School Theme");
    theme2but.addActionListener(this);
    theme2but.setSize(125, 25);
    theme2but.setLocation(300, 250);
    
    iptext = new JTextField();
    iptext.setText("Enter Your IP");
    iptext.setSize(100, 25);
    iptext.setLocation(300, 325);
    iptext.addActionListener(this);
    
    porttext = new JTextField();
    porttext.setText("Enter Your Port");
    porttext.setSize(100, 25);
    porttext.setLocation(300, 350);
    porttext.addActionListener(this);
    
    donebut = new JButton("Done");
    donebut.setSize(210, 25);
    donebut.setLocation(365, 500);
    donebut.addActionListener(this);
    
    nettext = new JTextField();
    nettext.setSize(100, 350);
    nettext.setLocation(675, 50);
    nettext.addActionListener(this);
    
    turnlabel = new JLabel();
    turnlabel.setForeground(new Color(255, 106, 106));
    turnlabel.setFont(new Font("Arial", Font.BOLD, 18)); 
    turnlabel.setSize(200, 25);
    turnlabel.setLocation(350, 175);
    
    theframe.setContentPane(thepanel);
    theframe.setVisible(true);
    theframe.addKeyListener(this);
    thetimer = new Timer(1000/30, this);
    thetimer.start();
    
    
  }
  
  public static void main(String[] args){
    JBattleship game = new JBattleship();
  }
}