package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ntime.NTimeParserOld;

@SuppressWarnings("serial")
public class Timestamping extends JFrame{
	
	private JButton dateLabel;
	private JLabel timeLabel;
	private JLabel timestampLabel;
	
	public Timestamping(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		//		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/image/icon.png"));  
		//		this.setIconImage(imageIcon.getImage()); 
		
		this.setTitle("Timestamp");
		this.setSize(260,150);

		Box mainPanel = Box.createVerticalBox();
		this.setContentPane(mainPanel);
		mainPanel.setForeground(Color.WHITE);
		mainPanel.setBackground(Color.WHITE);
		this.setBackground(Color.WHITE);
		
		NTimeParserOld tp = new NTimeParserOld();
		
		timeLabel = new JLabel("0");
		timestampLabel = new JLabel("0");
		dateLabel = new JButton(tp.getDate());
		
		Font fontB = new Font("Default",Font.BOLD,30);
		timeLabel.setFont(fontB);
		timestampLabel.setFont(fontB);

		mainPanel.add(timeLabel);
		mainPanel.add(timestampLabel);
		mainPanel.add(dateLabel);
		
		dateLabel.addActionListener(new ActionListener() {
			
			Timer timer;
			boolean running = false;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!running){
					timer = new Timer();
					timer.start();
					running = true;
				}
				else{
					timer.stopTimer();
					running = false;
				}
				
			}
		});

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	public void updateLabels(String time, String timestamp){
		timeLabel.setText(time);
		timestampLabel.setText(timestamp);
	}
	
	private class Timer extends Thread{
		
		private NTimeParserOld tp = new NTimeParserOld();
		private boolean running = false;
		
		@Override
		public void run(){
			running = true;
			while(running){
				long timestamp = System.currentTimeMillis();
				String timestampString = String.valueOf(timestamp);
				String time = tp.getTime(timestamp);
				Timestamping.this.updateLabels(time, timestampString);
			}
//			System.out.println(this.getId()+"stop.");
		}
		
		public void stopTimer(){
			this.running = false;
		}
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Timestamping ts = new Timestamping();
	}
}