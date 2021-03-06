package newyeargame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel {
	Random rnd = new Random();
	Image img = null;// ?????????? ???????
	Image bag = null;//?????????
	Image fon = null;//
	int fonx =0;//
	int[] imgy = {-30, -250, -530};//
	int[] imgx = {30, 120, 230};//
	int bagx = 100;//
    int napr = 0;//
    int score = 0;//
    int lives=3;//????????? ??????
    
    boolean gameOver = false;

	public GamePanel() {// ?????(???????????) ??????? ?????????? ??? ????????
						// ?????? ?????????? ??????.???????? ???????? ????? ?? ???????? new gamePanel

		try {// ???????? ??????? ????????? ?????? ? ???????? ???????
			img = ImageIO.read(new File("podarok1.png"));//????? read ????????? ??????
			bag = ImageIO.read(new File("bag.png"));//??????????? ????? ????? ??????? ? ?????? ??????
			fon = ImageIO.read(new File("fon.jpg"));//??????? ???? ???????? ??? ? ImageIO
		} catch (IOException e) {//?????? ??????? ????? ????????
			e.printStackTrace();// ????? ? ??????? ?????????? ?? ??????
		}

		Timer timer = new Timer(10, new ActionListener() {// ????????? ? ??????? ???????? ???????? ??? ????? ?? ????????? ???????????? ???????
															// ????? ?????
															// ActionListener

			@Override
			public void actionPerformed(ActionEvent arg0) {//?? ??? ??????????? ?????? ???? ???? ??????
				//? ?????? ?????????? ???????? ??? ?????? ???? ?????.? ???? ?????? ?? ???????? ???????? ?????????? 
			    //??? ????????? ???? ??????? ? ?????
				
				
				if (napr > 0 && bagx < 750) {//napr ??????????? ?????, ???? ??????????? ???????? <0 ?? ???????? ?????????? ?????? ?? 10
					bagx += 15;
				}

				
				
				if (napr < 0 && bagx > 0) {//???? ??????????? ????? ?????? 0 ???????? ?????????? ????? ????? ?? 10
					bagx -= 15;
				}
				for(int i=0; i<imgx.length;i++){//???? ??? ???? ??? ?? ?????????? ??? ??????? ?? ???????.?? ????? ?????????? ? ??????????? ????? ????????
				
               if (imgx[i]> bagx && imgx[i] + 30 < bagx + 150 && imgy[i] > 420){//????? ?????? ?????? ?????? ??????? ? ??? ????? ?????? ? ?????
					imgy[i] = -100;
					imgx[i] = rnd.nextInt(720);//????? ??????? ?????????? ????????? ????? ?? ???? ?? ?????? ?????? ?????? ????? ?????? ???????
					score += 1;//?????????? ? ????? ???????
					if (score %5 ==0){
						lives+=1;
					}
				}
				imgy[i] += 5;// ???????? ??????? ?? ???? ????? ??????? ??????
				if (imgy[i] > 600) {//???? ??????? ???? ?????? ??? ?????? ?????? ????????? ???? ?????
					imgy[i] = -100;
					imgx[i] = rnd.nextInt(720);
					lives-=1;
				}
				if (lives==0){gameOver=true;}

				
				

				repaint();// ??????????? ???????????
			}}
		});
		timer.start();//???????? ??????

	}

	@Override
	protected void paintComponent(Graphics gr) {//??? ????? ??????? ?????? ??????????? ???????????
		super.paintComponent(gr);// ??????? ??????? ????? ??????
		 gr.drawImage(fon, fonx, 0, 900, 600, null);//?????? ???
		if(gameOver){//????????? ??????????? ?? ????
			gr.setFont(new Font("serif", 0, 50 ));//?????? ????????? ??????
			gr.setColor(Color.WHITE);
			gr.drawString("Game Over", 300, 300);//?????? ???? ????????
			
			
		}else{ 
		for (int i=0; i<imgx.length; i++){//?? ?????????? ? ????? ??????? ?????? ???
		gr.drawImage(img, imgx[i], imgy[i], 30, 30, null);// ??????????
		}
		gr.drawImage(bag, bagx, 420, 150, 150, null);//?????????? ?????
		
		gr.setColor(Color.WHITE);
		gr.setFont(new Font("serif", 0, 35));//????????? ??????
		gr.drawString("Score:" + score, 100, 30);//???????? ????????
	    gr.drawString("Lives:"+ lives, 100, 65);
		}
		
		
	}

}
