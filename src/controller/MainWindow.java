package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
//import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import nconfig.NDataInfo;
import nrangedata.NDataExtractor;

@SuppressWarnings("serial")
public class MainWindow extends JFrame{

	private JPanel mainPanel;
	
	private JTextArea console;
	private JButton selectButton;
	private JButton extractButton;
	private JButton getlineButton;
	private JButton extractButton2;
	
	private NDataInfo dataInfo = NDataInfo.getDataInfoInstance();
//	private FileParser fileParser;
	private NDataExtractor dataExtractor;
//	private TimestampFormatter timestampFormatter;

	public MainWindow(){

		//non-ui part
//		fileParser = new FileParser(this);
		
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

		dataExtractor = new NDataExtractor(MainWindow.this);
		
		//		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/image/icon.png"));  
		//		this.setIconImage(imageIcon.getImage()); 
		this.setTitle("MyoDriving Data Processing");
		this.setSize(800,640);

		mainPanel = new JPanel();
		
		BoxLayout mainPaneLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(mainPaneLayout);
		
		this.setContentPane(mainPanel);

		console = new JTextArea();
		console.setSize(MainWindow.this.getSize());
		console.setMinimumSize(MainWindow.this.getSize());
		console.setMaximumSize(MainWindow.this.getSize());
		
		JScrollPane consoleScrollPane = new JScrollPane(console);
		mainPanel.add(consoleScrollPane);
		
		Box toolPanel = Box.createHorizontalBox();
		mainPanel.add(toolPanel);
		
		selectButton = new JButton("select");
		extractButton = new JButton("extract");
		getlineButton = new JButton("lines");
		extractButton2 = new JButton("extract new");
		
		selectButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				
				String dir = dataInfo.getDataDirPath();
				JFileChooser fc = new JFileChooser(dir);
				fc.showOpenDialog(MainWindow.this);
				File file = fc.getCurrentDirectory();
//				File file = fc.getSelectedFile();
				if(file!=null){
					String filePath = file.getAbsolutePath();
//					int a = filePath.lastIndexOf("\\");
//					String fileName = filePath.substring(a+1);
//					filePath = filePath.substring(0, a+1);
//					dataInfo.setDataFilePath(filePath);
//					dataInfo.setEmgFileName(fileName);
					dataInfo.setDirPath(filePath+"\\");
					
					updateSimpleConsole("Working directory: "+dataInfo.getDataDirPath());					
				}
			}
		});
		
		extractButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dataExtractor.extractData();
				updateSimpleConsole("Data extraction done.");
			}
		});
		
		getlineButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dataExtractor.getLinesList();
				updateSimpleConsole("Get line done.");
			}
		});
		
		extractButton2.addActionListener(e -> {
			dataExtractor.extractFromLines();
			updateSimpleConsole("Done");
		});
		
		toolPanel.add(selectButton);
		toolPanel.add(extractButton);
		toolPanel.add(getlineButton);
		toolPanel.add(extractButton2);
		toolPanel.add(Box.createHorizontalGlue());

		this.setMinimumSize(new Dimension(800,640));
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
	public void updateSimpleConsole(String data){
		console.append(data);
		console.append("\r\n");
		console.setCaretPosition(console.getDocument().getLength());
	}
}
