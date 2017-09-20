package controller.label;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NSecondInput extends JPanel{

	private NSecondInputCallback callback;
	private JTextField input;

	public NSecondInput(NSecondInputCallback callback) {
		super();
		this.callback = callback;

		BoxLayout mainLayout = new BoxLayout(this, BoxLayout.X_AXIS);
		this.setLayout(mainLayout);

		input = new JTextField();
		input.setMaximumSize(new Dimension(100, 25));
		//		input.setMinimumSize(new Dimension(100, 25));
		this.add(input);

		input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					NSecondInfo info = NSecondInput.this.getCurrentInfo();
					NSecondInput.this.callback.secondInputCallback(info);
				}
			}
		});

		this.setMinimumSize(new Dimension(100, 25));
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