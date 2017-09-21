package controller.label;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.BadLocationException;

import nconfig.NLabelInfo;
import nfileoperator.NFileOperator;
import ntime.NTimeParser;


public class LabelWindow extends JFrame implements NSecondInputCallback, NNumInputCallback{	
	
	private Box toolPanel;	
	private JTextArea console;
	private JLabel countLabel;
	
	private NNumInput exp;
	private NNumInput year;
	private NNumInput month;
	private NNumInput day;
	private NNumInput hour;
	private NSecondInput secondInput;
	
	private JButton start;
	private JButton stop;
	
	private NTimeParser timeParser;
	private NLabelInfo labelInfo = NLabelInfo.getNLabelInfoInstance();
	private NFileOperator fileOperator;
	private int count = 0;
	private boolean canceled = true;
	
	public LabelWindow(){
		super();
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
		this.setTitle("Label Tool");
		this.setSize(486,800);
		
		timeParser = new NTimeParser();
		fileOperator = new NFileOperator();
		
		JPanel mainPanel = new JPanel();
		BoxLayout mainPanelLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(mainPanelLayout);
		this.setContentPane(mainPanel);
	
		toolPanel = Box.createHorizontalBox();
		mainPanel.add(toolPanel);
		
		exp = new NNumInput("Exp","2");
		exp.setListener(this);
		year = new NNumInput("Year","2019");
		month = new NNumInput("Month","09");
		day = new NNumInput("Day","20");
		hour = new NNumInput("Hour","23");
		toolPanel.add(exp);
		toolPanel.add(year);
		toolPanel.add(month);
		toolPanel.add(day);
		toolPanel.add(hour);
		
		secondInput = new NSecondInput("39344756",this);
		toolPanel.add(secondInput);
		toolPanel.add(Box.createHorizontalGlue());
		
		start = new JButton("Start");
		stop = new JButton("Stop");
		Box controlBar = Box.createHorizontalBox();
		controlBar.add(start);
		controlBar.add(stop);
		controlBar.add(Box.createHorizontalGlue());
		mainPanel.add(controlBar);
		
		console = new JTextArea();
		mainPanel.add(console);
		
		Box stateBar = Box.createHorizontalBox();
		stateBar.add(new JLabel("Total: "));
		countLabel = new JLabel(count+"");
		stateBar.add(countLabel);
		this.add(stateBar);		
		
		this.setMinimumSize(new Dimension(486,300));
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		LabelWindow lw = new LabelWindow();
	}

	@Override
	public void secondInputCallback(NSecondInfo info) {
		StringBuilder builder = new StringBuilder();
		builder.append(year.getText()).append("-");
		builder.append(month.getText()).append("-");
		builder.append(day.getText()).append(" ");
		builder.append(hour.getText()).append(":");
		builder.append(info.getMinute()).append(":");
		builder.append(info.getSecond()).append(":");
		builder.append(info.getMillisecond());
		
		String datetime = builder.toString();
		long timestamp = timeParser.getTimestamp(datetime);
		String output = datetime + "," + timestamp + "," + info.getAction();
		
		updateConsole(output);
		updateCount(1);
		canceled = false;
		
		secondInput.selectAll();
//		console.append("Action: " + info.getAction());
//		console.append("Minute: " + info.getMinute());
//		console.append("Second: " + info.getSecond());
//		console.append("Millisecond" + info.getMillisecond());
	}
	
	@Override
	public void secondInputCancelLast() {
		if(canceled){
			return;
		}
		int lines = console.getLineCount();
		int linestart = 0;
		try {
			linestart = console.getLineStartOffset(lines-2);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		int len = console.getText().length();
		console.insert("# ", linestart);
		console.setCaretPosition(console.getText().length());
		updateCount(0);
		canceled = true;
	}
	
	@Override
	public void nNumInputCallback(NNumInfo info) {
		labelInfo.setWorkingFile(info.getData());
		fileOperator.closeOut();
		fileOperator.loadWriteFile(labelInfo.getWorkingDir()+labelInfo.getWorkingFile());
	}
	
	private void updateConsole(String data){
		console.append(data);
		console.append("\r\n");
	}
	
	private void updateCount(int flag){
		switch (flag) {
		case 0:
			count--;
			break;
		case 1:
			count++;
			break;
		default:
			break;
		}
		countLabel.setText(count+"");
	}
}