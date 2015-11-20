import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;


public class Marathon extends JFrame{
	ArrayList<Löpare> alla = new ArrayList <Löpare>();
	JTextArea text1 = new JTextArea(10,25);
	JRadioButton startKnapp; JRadioButton namnKnapp; JRadioButton ålderKnapp; JRadioButton tidKnapp;
	Marathon(){
		super("DSV Kista Marathon");
		add(new JLabel("DSV Kista Marathon"),BorderLayout.NORTH);
		
		JScrollPane scroll = new JScrollPane(text1);
		add(scroll, BorderLayout.WEST);
		
		startKnapp = new JRadioButton("StartID",true);
		namnKnapp = new JRadioButton("Namn");
		ålderKnapp = new JRadioButton("Ålder");
		tidKnapp = new JRadioButton("Tid");
		JPanel öst = new JPanel();
		öst.setLayout(new BoxLayout(öst,BoxLayout.Y_AXIS));
		öst.add(Box.createVerticalGlue());
		öst.add(new JLabel("Sortering:"));
		öst.add(startKnapp);
		öst.add(namnKnapp);
		öst.add(ålderKnapp);
		öst.add(tidKnapp);
		add(öst, BorderLayout.EAST);
		ButtonGroup sortera = new ButtonGroup();
		sortera.add(startKnapp);
		sortera.add(namnKnapp);
		sortera.add(ålderKnapp);
		sortera.add(tidKnapp);
		
		JPanel syd = new JPanel();
		syd.setLayout(new BoxLayout(syd, BoxLayout.X_AXIS));
		JButton ny = new JButton ("Ny");
		ny.addActionListener(new NyLöpare());
		syd.add(ny);
		JButton visa = new JButton ("Visa");
		visa.addActionListener(new VisaLöpare());
		syd.add(visa);
		JButton tid = new JButton ("Tid");
		tid.addActionListener(new SetTid());
		syd.add(tid);
		syd.add(Box.createHorizontalGlue());
		add(syd, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,300);
		setVisible(true);
	}
	class NyLöpare implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JPanel ny = new JPanel ();
			ny.setLayout(new BoxLayout(ny, BoxLayout.Y_AXIS));
			JPanel rad1 = new JPanel ();
			int id = Löpare.getID();
			Löpare.setID(++id);
			rad1.add(new JLabel("Startnr: " + Löpare.getID()));
			ny.add(rad1);
			JPanel rad2 = new JPanel ();
			JTextField namn = new JTextField(10);
			rad2.add(new JLabel("Namn: "));
			rad2.add(namn);
			ny.add(rad2);
			JPanel rad3 = new JPanel();
			JTextField land = new JTextField(10);
			rad3.add(new JLabel("Land: "));
			rad3.add(land);
			ny.add(rad3);
			JPanel rad4 = new JPanel ();
			JTextField ålder = new JTextField(6);
			rad4.add(new JLabel("Ålder: "));
			rad4.add(ålder);
			ny.add(rad4);
			for(;;){
				try{		
					int värde = JOptionPane.showConfirmDialog(null, ny, "Ny löpare", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (värde == JOptionPane.OK_OPTION){
						String name = namn.getText();
						String country = land.getText();
						int age = Integer.parseInt(ålder.getText());
						if(name.equals("")|| country.equals("")){
							JPanel error = new JPanel();
							error.add(new JLabel("Namn och land måste vara ifyllda"));
							JOptionPane.showMessageDialog(null, error,"Error", JOptionPane.ERROR_MESSAGE);
						}
						else{
							Löpare l = new Löpare(Löpare.getID(),name,country,age);
							alla.add(l);
							break;
						}
					}
					else if (värde == JOptionPane.CANCEL_OPTION){
						Löpare.setID(--id);
						break;
					}
					else if (värde == JOptionPane.CLOSED_OPTION){
						Löpare.setID(--id);
						break;
					}
				}
				catch(NumberFormatException x){
					JPanel error = new JPanel();
					error.add(new JLabel("Ålder måste vara siffror"));
					JOptionPane.showMessageDialog(null, error,"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	class VisaLöpare implements ActionListener{
		public void actionPerformed (ActionEvent e){
			text1.setText("");
			if (startKnapp.isSelected()){
				Collections.sort(alla, new IdSort());
				for (Löpare l: alla){
					text1.setText(text1.getText() + l.toString());
				}
			}
			else if(namnKnapp.isSelected()){
				Collections.sort(alla, new NamnSort());
				for (Löpare l: alla){
					text1.setText(text1.getText() + l.toString());
				}
			}
			else if(ålderKnapp.isSelected()){
				Collections.sort(alla, new ÅlderSort());
				for (Löpare l: alla){
					text1.setText(text1.getText() + l.toString());
				}			
			}
			else if(tidKnapp.isSelected()){
				Collections.sort(alla, new TidSort());
				for (Löpare l: alla){
					text1.setText(text1.getText() + l.toString());
				}			
			}
		}
	}
	class SetTid implements ActionListener{
		public void actionPerformed (ActionEvent e){

			JPanel start = new JPanel();
			start.setLayout(new BoxLayout(start, BoxLayout.Y_AXIS));
			JPanel rad1 = new JPanel ();
			JTextField startNr = new JTextField(8);
			rad1.add(new JLabel("Startnr: "));
			rad1.add(startNr);
			JPanel rad2 = new JPanel();
			JTextField tid = new JTextField(8);
			rad2.add(new JLabel("Tid: "));
			rad2.add(tid);		
			start.add(rad1);
			start.add(rad2);
			for(;;){
				try{
					int värde = JOptionPane.showConfirmDialog(null, start, "Välj tid", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (värde == JOptionPane.OK_OPTION){
						int löparnr = Integer.parseInt(startNr.getText());
						int listLängd = alla.size();
						int räknare = 0;
						int ingenRast = 1;
						for (int skal=1; skal == 1;){
							for(Löpare l : alla){
								int nummer = l.getStartnr();
								if (löparnr == nummer){
									l.setTid(Double.parseDouble(tid.getText()));
									räknare++;
									break;
								}
								else if (räknare != listLängd){
									räknare++;
									if (räknare == listLängd){
										JPanel error = new JPanel();
										error.add(new JLabel("Löparen finns inte"));
										JOptionPane.showMessageDialog(null, error,"Error", JOptionPane.ERROR_MESSAGE);
										ingenRast = 0;
									}
								}
							}
							skal = 0;
						}
						if (ingenRast == 1){
							break;
						}
					}
					else if (värde == JOptionPane.CLOSED_OPTION){
						break;
					}
					else if (värde == JOptionPane.CANCEL_OPTION){
						break;
					}
				}
				catch(NumberFormatException x){
					JPanel error = new JPanel();
					error.add(new JLabel("Måste vara siffror"));
					JOptionPane.showMessageDialog(null, error,"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	public static void main (String args[]){
		new Marathon();
	}
}
