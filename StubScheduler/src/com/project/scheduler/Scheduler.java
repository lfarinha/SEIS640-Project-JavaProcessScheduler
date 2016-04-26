package com.project.scheduler;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;

public class Scheduler {

	private JFrame frame;
	private JTable table;
	private JTextField quantum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Scheduler window = new Scheduler();
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
	public Scheduler() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 767, 428);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		String[] columnNames = {"Process ID", "Arrival Time", "Start Time", "Finish Time"," Waiting time", "Turnaround Time"};
		Object[][] rows = {
				{"a","b","c","d","e","f"}
		};
		table = new JTable(rows, columnNames);	
		table.setBounds(65, 53, 635, 193);
		table.setFillsViewportHeight(true);
		frame.getContentPane().add(table);

		
		JButton btnExecute = new JButton("Execute");
		btnExecute.setBounds(303, 312, 89, 23);
		frame.getContentPane().add(btnExecute);
		
		quantum = new JTextField();
		quantum.setFont(new Font("Droid Sans", Font.PLAIN, 14));
		quantum.setBounds(149, 313, 86, 20);
		frame.getContentPane().add(quantum);
		quantum.setColumns(10);
	}
}
