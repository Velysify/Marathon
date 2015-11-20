import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;


public class Marathon extends JFrame{
	ArrayList<L�pare> alla = new ArrayList <L�pare>();
	JTextArea text1 = new JTextArea(10,25);
	JRadioButton startKnapp; JRadioButton namnKnapp; JRadioButton �lderKnapp; JRadioButton tidKnapp;
	Marathon(){
		super("DSV Kista Marathon");
		add(new JLabel("DSV Kista Marathon"),BorderLayout.NORTH);
		
		JScrollPane scroll = new JScrollPane(text1);
		add(scroll, BorderLayout.WEST);
		
		startKnapp = new JRadioButton("StartID",true);
		namnKnapp = new JRadioButton("Namn");
		�lderKnapp = new JRadioButton("�lder");
		tidKnapp = new JRadioButton("Tid");
		JPanel �st = new JPanel();
		�st.setLayout(new BoxLayout(�st,BoxLayout.Y_AXIS));
		�st.add(Box.createVerticalGlue());
		�st.add(new JLabel("Sortering:"));
		�st.add(startKnapp);
		�st.add(namnKnapp);
		�st.add(�lderKnapp);
		�st.add(tidKnapp);
		add(�st, BorderLayout.EAST);
		ButtonGroup sortera = new ButtonGroup();
		sortera.add(startKnapp);
		sortera.add(namnKnapp);
		sortera.add(�lderKnapp);
		sortera.add(tidKnapp);
		
		JPanel syd = new JPanel();
		syd.setLayout(new BoxLayout(syd, BoxLayout.X_AXIS));
		JButton ny = new JButton ("Ny");
		ny.addActionListener(new NyL�pare());
		syd.add(ny);
		JButton visa = new JButton ("Visa");
		visa.addActionListener(new VisaL�pare());
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
	class NyL�pare implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JPanel ny = new JPanel ();
			ny.setLayout(new BoxLayout(ny, BoxLayout.Y_AXIS));
			JPanel rad1 = new JPanel ();
			int id = L�pare.getID();
			L�pare.setID(++id);
			rad1.add(new JLabel("Startnr: " + L�pare.getID()));
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
			JTextField �lder = new JTextField(6);
			rad4.add(new JLabel("�lder: "));
			rad4.add(�lder);
			ny.add(rad4);
			for(;;){
				try{		
					int v�rde = JOptionPane.showConfirmDialog(null, ny, "Ny l�pare", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (v�rde == JOptionPane.OK_OPTION){
						String name = namn.getText();
						String country = land.getText();
						int age = Integer.parseInt(�lder.getText());
						if(name.equals("")|| country.equals("")){
							JPanel error = new JPanel();
							error.add(new JLabel("Namn och land m�ste vara ifyllda"));
							JOptionPane.showMessageDialog(null, error,"Error", JOptionPane.ERROR_MESSAGE);
						}
						else{
							L�pare l = new L�pare(L�pare.getID(),name,country,age);
							alla.add(l);
							break;
						}
					}
					else if (v�rde == JOptionPane.CANCEL_OPTION){
						L�pare.setID(--id);
						break;
					}
					else if (v�rde == JOptionPane.CLOSED_OPTION){
						L�pare.setID(--id);
						break;
					}
				}
				catch(NumberFormatException x){
					JPanel error = new JPanel();
					error.add(new JLabel("�lder m�ste vara siffror"));
					JOptionPane.showMessageDialog(null, error,"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	class VisaL�pare implements ActionListener{
		public void actionPerformed (ActionEvent e){
			text1.setText("");
			if (startKnapp.isSelected()){
				Collections.sort(alla, new IdSort());
				for (L�pare l: alla){
					text1.setText(text1.getText() + l.toString());
				}
			}
			else if(namnKnapp.isSelected()){
				Collections.sort(alla, new NamnSort());
				for (L�pare l: alla){
					text1.setText(text1.getText() + l.toString());
				}
			}
			else if(�lderKnapp.isSelected()){
				Collections.sort(alla, new �lderSort());
				for (L�pare l: alla){
					text1.setText(text1.getText() + l.toString());
				}			
			}
			else if(tidKnapp.isSelected()){
				Collections.sort(alla, new TidSort());
				for (L�pare l: alla){
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
					int v�rde = JOptionPane.showConfirmDialog(null, start, "V�lj tid", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (v�rde == JOptionPane.OK_OPTION){
						int l�parnr = Integer.parseInt(startNr.getText());
						int listL�ngd = alla.size();
						int r�knare = 0;
						int ingenRast = 1;
						for (int skal=1; skal == 1;){
							for(L�pare l : alla){
								int nummer = l.getStartnr();
								if (l�parnr == nummer){
									l.setTid(Double.parseDouble(tid.getText()));
									r�knare++;
									break;
								}
								else if (r�knare != listL�ngd){
									r�knare++;
									if (r�knare == listL�ngd){
										JPanel error = new JPanel();
										error.add(new JLabel("L�paren finns inte"));
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
					else if (v�rde == JOptionPane.CLOSED_OPTION){
						break;
					}
					else if (v�rde == JOptionPane.CANCEL_OPTION){
						break;
					}
				}
				catch(NumberFormatException x){
					JPanel error = new JPanel();
					error.add(new JLabel("M�ste vara siffror"));
					JOptionPane.showMessageDialog(null, error,"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	public static void main (String args[]){
		new Marathon();
	}
}
