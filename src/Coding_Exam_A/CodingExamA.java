package Coding_Exam_A;

import java.awt.Color;

import javax.swing.JOptionPane;

import org.jointheleague.graphical.robot.Robot;

public class CodingExamA {
	
	Robot[] robots;
	Thread[] robotThreads;
	Color color;
	int sideCount;
	public static void main(String[] args) {
		/*
		 * Write a program that asks the user for three pieces of information.
		 * 1. How many robots
		 * 2. The color of the shapes
		 * 3. How many sides each shape will have
		 * 
		 * Once the information has been collected, the program will then make the requested number of robots
		 * each draw the requested shape in the requested color. The robots should execute at the same time so 
		 * Threads will need to be used. Arrange the robots so that the shapes do not overlap.
		 * For full credit, define the Thread functions using lambdas.
		 * 
		 * See the Coding_Exam_A_Demo.jar for an example of what the finished product should look like.
		 */
		CodingExamA simulation = new CodingExamA();
		simulation.setup();
		simulation.threadRobots();
		
	

	}
	
	void setup() {
		//Robot Count
		String robotCount = JOptionPane.showInputDialog("How many robots?");
		try {
			int rC = Integer.valueOf(robotCount);
			robots = new Robot[rC];
		}
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "The response was not a number.");
			System.exit(0);
		}
		
		//Positioning Robot and Choosing Screen Size
		
		for(int j = 0; j < robots.length; j++) {
			robots[j] = new Robot();
			robots[j].setWindowSize(300*robots.length, 500);
			robots[j].setX(j*200+100);
			robots[j].setY(250);
		}
		
		//Color
		String[] options = {"Red","Green","Blue"};
		int chosenOption = JOptionPane.showOptionDialog(null, "Which color?", "Choose an Option", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		switch(chosenOption) {
		case 0:
			color = Color.RED;
			break;
		case 1:
			color = Color.GREEN;
			break;
		case 2:
			color = Color.BLUE;
			break;
		}
		
		//Side Count
		String sideC = JOptionPane.showInputDialog("How many sides?");
		try {
			sideCount = Integer.valueOf(sideC);
		}
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "The response was not a number.");
			System.exit(0);
		}
	
	}
	
	void threadRobots() {
		robotThreads = new Thread[robots.length];
		for(int j = 0; j < robotThreads.length; j++) {
			threadARobot(robots[j], j);
		}
		for(int j = 0; j < robotThreads.length; j++) {
			robotThreads[j].start();
		}
	}
	
	void threadARobot(Robot rbt, int index) {
		robotThreads[index] = new Thread(() -> {
			drawShape(robots[index]);
		});
	}
	void drawShape(Robot target) {
		target.setPenColor(color);
		target.penDown();
		
		for(int j = 0; j < sideCount; j++) {
			target.move(70);
			target.turn(360/sideCount);
		}
	}
}
