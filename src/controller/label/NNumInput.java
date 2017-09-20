package controller.label;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class NNumInput extends JPanel{

	private JLabel title; 
	private JTextField input;
	private JButton increase;
	private JButton decrease;
	private Document document;

	public NNumInput(){
		this("","0");
	}
	
	public NNumInput(String titString){
		this(titString,"0");
	}
	
	public NNumInput(String titleString, String num){
		super();

		input = new JTextField();
		increase = new JButton("+");
		decrease = new JButton("-");

		BoxLayout mainLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(mainLayout);

		Box titleBox = Box.createHorizontalBox();
		title = new JLabel(titleString);
		titleBox.add(title);
		this.add(titleBox);		
		
		input.setText(num);
		input.setMaximumSize(new Dimension(80, 25));
//		input.setMinimumSize(new Dimension(80, 25));
		document = input.getDocument();

		Box buttonBar = Box.createHorizontalBox();
		buttonBar.add(decrease);
		buttonBar.add(increase);

		this.add(input);
		this.add(buttonBar);

		decrease.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String text = input.getText();
				if(text!=null&&!text.equals("")){
					int num = Integer.parseInt(text);
					input.setText(String.valueOf(num-1));
				}
			}
		});

		increase.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String text = input.getText();
				if(text!=null&&!text.equals("")){
					int num = Integer.parseInt(text);
					input.setText(String.valueOf(num+1));
				}
			}
		});

		document.addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				Document document = e.getDocument();
				String text = null;
				try {
					text = document.getText(0, document.getLength());
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	public String getText(){
		return input.getText();
	}
}
