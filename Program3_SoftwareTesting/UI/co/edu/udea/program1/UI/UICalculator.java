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
import co.edu.udea.program1.Common.Models.MathResult;
import co.edu.udea.program1.Common.Models.Regression;
import co.edu.udea.program1.Common.Models.Response;

import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;

public class UICalculator {

	private JFrame frame;
	private static CalculatorInterface _calculator;
	private JTextField xValues;
	private JTextField yValues;
	private JLabel lblYValues;
	private JTextArea results;
	private JLabel lblResults;
	private JTextField evaluateX;
	private JButton btnEvaluate;
	private JLabel lblX;
	private Regression RegressionResult;
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
		frame.setBounds(100, 100, 450, 383);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblXValues = new JLabel("X values:");
		
		xValues = new JTextField();
		xValues.setColumns(10);
		
		yValues = new JTextField();
		yValues.setColumns(10);
		
		lblYValues = new JLabel("Y values:");
		
		results = new JTextArea();
		results.setEditable(false);
		
		lblResults = new JLabel("Results");
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String x = xValues.getText();
				String y = yValues.getText();
				String[] xArray = splitText(x);
				String[] yArray = splitText(y);
				Response response = _calculator.CalculateRegressionAndCorrelation(xArray, yArray);
				if(response.getStatus() == 1){
					MathResult mr = (MathResult) response.getData();
					RegressionResult = mr.getResultRegression();
					results.setText(" * The linear regression is: \n"+ mr.getResultRegression().toString()+
							"\n * The Correlation is: \n"+ mr.getResultCorrelation().getCorrelation()+
							"\n * The Coefficient Determination is: \n"+ mr.getResultCorrelation().getCoefficientDetermination());
				}else{
					JOptionPane.showMessageDialog(null, "The following error has ocurred :"+ response.getMessage());
				}
			}
		});
		
		evaluateX = new JTextField();
		evaluateX.setColumns(10);
		
		btnEvaluate = new JButton("Evaluate");
		btnEvaluate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(RegressionResult != null){
					
					
				Response response = _calculator.ResolveRegression(RegressionResult, evaluateX.getText());
				if(response.getStatus() == 1){
					Regression result = (Regression) response.getData();
					JOptionPane.showMessageDialog(null, "The Y value is: "+ result.getyValue());
				}else{
					JOptionPane.showMessageDialog(null, "The following error has ocurred: "+ response.getMessage());
				}
				}else{
					JOptionPane.showMessageDialog(null, "The regression must be calculated before evaluate");
				}
			}
		});
		
		lblX = new JLabel("Evaluate X");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(lblXValues)
					.addGap(10)
					.addComponent(xValues, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(lblYValues)
					.addGap(10)
					.addComponent(yValues, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(btnCalculate))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(lblResults, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(results, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(lblX)
					.addGap(10)
					.addComponent(evaluateX, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnEvaluate))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(9)
							.addComponent(lblXValues))
						.addComponent(xValues, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(38)
							.addComponent(lblYValues))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(29)
							.addComponent(yValues, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnCalculate, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblResults))
						.addComponent(results, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblX))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(evaluateX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnEvaluate))
					.addGap(12))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	

	private String[] splitText(String text){
		return text.split(",");
	}
}
