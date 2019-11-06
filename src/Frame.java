import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Frame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private ImageIcon circleIcon = new ImageIcon("C:\\Users\\Adam\\eclipse-workspace\\CrossAndCircle\\circle6.png");
	private ImageIcon crossIcon = new ImageIcon("C:\\Users\\Adam\\eclipse-workspace\\CrossAndCircle\\146-512.png");
	private ImageIcon blank = new ImageIcon("C:\\Users\\Adam\\eclipse-workspace\\CrossAndCircle\\BLANK_ICON.png");
	
	private JButton array[][] = new JButton[3][3];
	
	private static int moves = 0;
	private static int table[][] = new int[3][3];
	
	Frame(){
		
		setSize(400, 400);		
		GridLayout layout = new GridLayout(3, 3);
		setLayout(layout);
	
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				array[i][j] = new JButton();
				add(array[i][j]);
				array[i][j].addActionListener(this);
				array[i][j].setIcon(blank);
				
			}
		}
						
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public ImageIcon createCrircleIcon() {
		
		Image img = circleIcon.getImage();
		Image newImg = img.getScaledInstance(340/3, 340/3, java.awt.Image.SCALE_SMOOTH );
		circleIcon = new ImageIcon(newImg);
		
		return circleIcon;
	}
	
	public ImageIcon createCrossIcon() {
		
		Image img = crossIcon.getImage();
		Image newImg = img.getScaledInstance(350/3, 350/3, java.awt.Image.SCALE_SMOOTH );
		crossIcon = new ImageIcon(newImg);
		
		return crossIcon;
	}
	
	public void reset() {
		for(int n = 0; n < 3; n++) {
			for(int m = 0; m < 3; m++) {
				
				remove(array[n][m]);
				System.out.println("Im in loop");
				array[n][m] = new JButton();
				add(array[n][m]);
				array[n][m].setIcon(blank);
				array[n][m].addActionListener(this);
				table[n][m] = 0;
			}
		}
	}
	
	public void makeMove(ActionEvent e, ImageIcon icon, int num) {
		
		Object source = e.getSource();
		
			for(int i = 0; i<3; i++) {
				for(int j = 0; j < 3; j++) {
					if(source.equals(array[i][j])) {
						array[i][j].setIcon(icon);
						array[i][j].removeActionListener(this);
						table[i][j] = num;
						moves++;
						if(checkIfWon()) {
							int choice = JOptionPane.showConfirmDialog(null,
									"Do you want to play again ?", "Play again ?", JOptionPane.YES_NO_OPTION);
								
								System.out.println(choice);
								
							if(choice == 0) {
								
								reset();
								
							}else {
								System.exit(1);
							}
						}
						
					}
				}
			}		
		}			
	

	@Override
	public void actionPerformed(ActionEvent e) {
			
		if(moves == 0 || moves % 2 == 0) {
		
			makeMove(e, createCrircleIcon(), 1);
		
		}else {
			
			makeMove(e, createCrossIcon(), 2);

		}
		
	}
	
	public boolean checkRow(int row) {
		
		int counter = 0;
		int counter1 = 0;

		
		for(int j = 0; j<3; j++) {
			if((table[row][j] == 1)) {
				
				counter++;
				
				if(counter == 3) {
					System.out.println("row number "+ (row+1) + " done");
					return true;

				}
				
			}else if(table[row][j] == 2) {
				
				counter1++;
				
				if(counter1 == 3) {
					System.out.println("row number "+ (row+1) + " done");
					return true;

				}
			}
		}
		
		return false;	

	}
	
	public boolean checkColumn(int column) {
		
		int counter1 = 0;
		int counter2 = 0;
		
		for(int j = 0; j<3; j++) {
			if((table[j][column] == 1)) {
	
				counter1++;
				
				if(counter1 == 3) {
					System.out.println("column number " + (column+1) + " done");
					counter1 = 0;
					return true;
				}
				
			}else if((table[j][column] == 2)) {
				
				counter2++;
				
				if(counter2 == 3) {
					System.out.println("column number " + (column+1) + " done");
					counter2 = 0;
					return true;
				}
				
			}
				
		}
		
		return false;
		
	}
	
	public boolean checkDiagonal(int number) {
		
		if(number==1) {
			if((table[0][0]==1 & table[1][1]==1 & table[2][2]==1) || (table[0][0]==2 & table[1][1]==2 && table[2][2]==2)) {
			
				System.out.println("firts diagonal fullfilled");
			
				return true;
			}
		}else if(number == 2) {
			if((table[2][0]==1 & table[1][1]==1 & table[0][2]==1) || (table[2][0]==2 & table[1][1]==2 & table[0][2]==2)) {
								
				System.out.println("second diagonal fullfilled");

				return true;
			}
		}
				
		return false;
	
	}	

	public boolean checkIfWon() {

				for(int i = 0; i<3; i++) {
					
					if((checkRow(i)||checkColumn(i))==true) {
						return true;
					}else if(checkDiagonal(1)||checkDiagonal(2)) {
						return true;
					}else if(checkDraw()){
						return true;
					}
					
				}		
						
			return false;
		
		}
	
	public boolean checkDraw() {
		int counter = 0;
		
		for(int i = 0; i<3; i++) {
			for(int j = 0; j < 3; j++) {
				
				if(table[i][j]!=0) {
					counter++;
					if(counter == 9) {
						return true;
					}
				}else {
					return false;
				}
				
			}
		}
		return false;
	}
	
}
