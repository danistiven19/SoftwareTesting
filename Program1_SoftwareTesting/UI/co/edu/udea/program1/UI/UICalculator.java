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
import co.edu.udea.program1.BAL.Controllers.Calculator;
import co.edu.udea.program1.BAL.Controllers.Interfaces.CalculatorInterface;
import co.edu.udea.program1.Common.Models.Response;

import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UICalculator {

	private JFrame frame;
	private JTextField ValuesTextField;
	private static Calculator _calculator;
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
					_calculator = new Calculator();
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
		
		ValuesTextField = new JTextField();
		frame.getContentPane().add(ValuesTextField, BorderLayout.CENTER);
		ValuesTextField.setColumns(10);
		
		JButton btnMean = new JButton("Mean");
		btnMean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String text = ValuesTextField.getText();
				System.out.println(text);
				String[] a = splitText(text);
				Response response = _calculator.Mean(a);
				if(response.getStatus() == 1){
					JOptionPane.showMessageDialog(null, "The mean is :"+ response.getData().toString());
				}else{
					JOptionPane.showMessageDialog(null, "The following error has ocurred :"+ response.getMessage());
				}
			}
		});
		frame.getContentPane().add(btnMean, BorderLayout.SOUTH);
		
		JButton btnStandardDeviation = new JButton("Standard Deviation");
		btnStandardDeviation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String text = ValuesTextField.getText();
				System.out.println(text);
				String[] a = splitText(text);
				Response response = _calculator.StandardDeviation(a);
				if(response.getStatus() == 1){
					JOptionPane.showMessageDialog(null, "The standard deviation is :"+ response.getData().toString());
				}else{
					JOptionPane.showMessageDialog(null, "The following error has ocurred :"+ response.getMessage());
				}
			}
		});
		frame.getContentPane().add(btnStandardDeviation, BorderLayout.EAST);
		
		btnLoadFile = new JButton("Load File");
		btnLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 chooser = new JFileChooser(); 
				    chooser.setCurrentDirectory(new java.io.File("."));
				    chooser.setDialogTitle(choosertitle);
				    //
				    // disable the "All files" option.
				    //
				    chooser.setAcceptAllFileFilterUsed(false);
				    int returnvalue = chooser.showOpenDialog(frame);
				    if (returnvalue == JFileChooser.APPROVE_OPTION) { 
				    	Response response = _calculator.ReadValues(chooser.getSelectedFile().getName());
				    	ValuesTextField.setText(response.getData().toString());
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
