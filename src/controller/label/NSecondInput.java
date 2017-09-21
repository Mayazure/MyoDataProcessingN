package controller.label;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NSecondInput extends JPanel{

	private NSecondInputCallback callback;
	private JTextField input;
	private JButton cancel;

	public NSecondInput(NSecondInputCallback callback) {
		this("0", callback);
	}

	public NSecondInput(String num, NSecondInputCallback callback) {
		super();
		this.callback = callback;

		BoxLayout mainLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(mainLayout);

		Box titleBox = Box.createHorizontalBox();
		titleBox.add(new JLabel("input"));
		this.add(titleBox);

		input = new JTextField();
		input.setPreferredSize(new Dimension(80, 25));
		input.setMaximumSize(new Dimension(80, 25));
		input.setMinimumSize(new Dimension(80, 25));
		input.setText(num);
		this.add(input);

		Box box = Box.createHorizontalBox();
		cancel = new JButton("Cancel");
		box.add(cancel);
		this.add(box);

		input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e){
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ENTER:
					NSecondInfo info = NSecondInput.this.getCurrentInfo();
					NSecondInput.this.callback.secondInputCallback(info);
					break;
				case KeyEvent.VK_ESCAPE:
					NSecondInput.this.callback.secondInputCancelLast();
				default:
					break;
				}
			}
		});

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				NSecondInput.this.callback.secondInputCancelLast();
			}
		});
	}

	public NSecondInfo getCurrentInfo(){
		NSecondInfo info = new NSecondInfo();
		String text = input.getText();
		info.setMinute(Integer.parseInt(text.substring(0, 2)));
		info.setSecond(Integer.parseInt(text.substring(2, 4)));
		info.setMillisecond(Integer.parseInt(text.substring(4, 7)));
		int action = Integer.parseInt(text.substring(7, 8));
		int naction = 0;

		switch(action){
		case 0:
			naction = NSecondInfo.ACTION_NONE;
			break;
		case 4: 
			naction = NSecondInfo.ACTION_LEFT_STEERING;
			break;
		case 6:
			naction = NSecondInfo.ACTION_RIGHT_STEERING;
			break;
		default:
			naction = NSecondInfo.ACTION_OTHERS;

		}
		info.setAction(naction);
		return info;

	}

	public void selectAll(){
		input.selectAll();
	}
}