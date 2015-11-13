package com.tcx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;

import org.apache.commons.io.FileUtils;

public class MainWindow extends JFrame{
	private static final long serialVersionUID = -6166338366651758854L;
	private ListModel<TCXLap> model = new javax.swing.DefaultListModel<TCXLap>();
	private JPanel container = new JPanel();
	private JComboBox<String> combo = new JComboBox<String>();
	private final JLabel label = new JLabel("Choix TCX");
	private final JLabel savelabel = new JLabel("Save TCX");
	private JList<TCXLap> listTCXLaps = new JList<TCXLap>(model);
	private Map<String,File> gtcxs;
	private JButton saveButton = new JButton();
	private static final String[] intensities = { "Active", "Resting"};
	private File selectedFile = null;
	private JComboBox<String> activityType = new JComboBox<String>(intensities);
	

	public MainWindow(Map<String,File> tcxs){
		gtcxs = tcxs;
		this.setTitle("TCX Processing for SportTracks.mobi");
		this.setSize(1024, 768);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             

		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		combo.setPreferredSize(new Dimension(200, 20));
		combo.addActionListener(new ItemAction());

		for(String fileName : gtcxs.keySet()){
			combo.addItem(fileName);
		}

		listTCXLaps.setPreferredSize(new Dimension(1000,740));
		listTCXLaps.addFocusListener(new ListAction());
		saveButton.add(savelabel);
		saveButton.addActionListener(new ButtonAction());
		
		activityType.addActionListener(new ActivityTypeAction());
		activityType.setEnabled(false);
		
		JPanel top = new JPanel();
		top.add(label);
		top.add(combo);
		top.add(activityType);
		top.add(saveButton);
		JPanel left = new JPanel();
		left.add(listTCXLaps);
	
		container.add(top, BorderLayout.NORTH);
		container.add(left, BorderLayout.CENTER);
		this.setContentPane(container);
		this.setVisible(true);            

	}

	class ItemAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			((javax.swing.DefaultListModel)listTCXLaps.getModel()).removeAllElements();
			selectedFile = gtcxs.get(combo.getSelectedItem());
			System.out.println("ActionListener : action sur " + combo.getSelectedItem() + " - " + selectedFile);
			List<TCXLap> lapsForFile = TCXHelper.processTCX(selectedFile);
			for (TCXLap lap : lapsForFile){
				((javax.swing.DefaultListModel)listTCXLaps.getModel()).addElement(lap);
			}			
			activityType.setEnabled(false);
		}               
	}
	
	class ActivityTypeAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			TCXLap selectedLap = listTCXLaps.getSelectedValue();
			selectedLap.setIntensity(activityType.getSelectedItem().toString());
			System.out.println(selectedLap);
			listTCXLaps.repaint();
		}               
	}
	
	class ButtonAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			List<TCXLap> laps = new ArrayList<TCXLap>();
			for (int i = 0; i < listTCXLaps.getModel().getSize(); i++){
				laps.add(listTCXLaps.getModel().getElementAt(i));
			}
			TCXHelper.updateTCX(selectedFile, laps);
		}               
	}
	
	class ListAction implements FocusListener{
		@Override
		public void focusGained(FocusEvent arg0) {
			TCXLap selectedLap = listTCXLaps.getSelectedValue();
			activityType.setSelectedItem(selectedLap.getIntensity());
			activityType.setEnabled(true);
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}               
	}
}
