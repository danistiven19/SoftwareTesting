package co.edu.udea.program1.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import co.edu.udea.program1.BAL.*;
import co.edu.udea.program1.BAL.Controllers.SizeCalculator;
import co.edu.udea.program1.BAL.Controllers.Interfaces.SizeCalculatorInterface;
import co.edu.udea.program1.Common.Models.Program;
import co.edu.udea.program1.Common.Models.Response;

import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Scrollbar;
import javax.swing.JScrollBar;

public class UICalculator {

	private JFrame frame;
	private JTextArea ValuesTextField;
	private static SizeCalculator _calculator;
	private JButton btnLoadFile;
	private JFileChooser chooser;
	private String choosertitle;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UICalculator window = new UICalculator();
					_calculator = new SizeCalculator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public UICalculator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ValuesTextField = new JTextArea();
		frame.getContentPane().add(ValuesTextField, BorderLayout.CENTER);
		ValuesTextField.setColumns(10);
		
		btnLoadFile = new JButton("Load File");
		btnLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 chooser = new JFileChooser(); 
				    chooser.setCurrentDirectory(new java.io.File("."));
				    chooser.setDialogTitle(choosertitle);
				    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				    //
				    // disable the "All files" option.
				    //
				    chooser.setAcceptAllFileFilterUsed(false);
				    int returnvalue = chooser.showOpenDialog(frame);
				    if (returnvalue == JFileChooser.APPROVE_OPTION) {
				    	Response response = _calculator.ProcessProgram(chooser.getSelectedFile().getAbsolutePath());
				    	String result = "";
				    	if(response.getStatus() == 0){
				    		JOptionPane.showMessageDialog(null, response.getMessage());
				    	}else{
				    		ValuesTextField.setText("");
				    		Program p = (Program) response.getData(); 
				    		result = "Project name: "+ p.getName()+"\n";
				    		result = result + "Path: "+ p.getDirectory()+ "\n";
				    		result = result +"Total size: "+ p.getTotalSize()+ "\n \n";
				    		result = result +"Parts ---- \n";
				    		for(int i = 0; i< p.getParts().size(); i++){
				    			result = result +"Part name: "+ p.getParts().get(i).getName()+ "\n";
				    			result = result +"Number of items: "+ p.getParts().get(i).getNumberOfItems()+ "\n";
				    			result = result +"Part size: "+ p.getParts().get(i).getPartSize()+ "\n";
				    		}
				    	}
				    	ValuesTextField.setText(result);
				      }
				    else {
				      System.out.println("No Selection ");
				      }
			}
		});
		frame.getContentPane().add(btnLoadFile, BorderLayout.WEST);
	}
	

	private String[] splitText(String text){
		return text.split(",");
	}
	
}
