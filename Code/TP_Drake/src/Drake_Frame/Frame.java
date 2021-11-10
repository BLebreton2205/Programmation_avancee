package Drake_Frame;

import javax.swing.event.*; 
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*; 

public class Frame extends JFrame implements ActionListener, ChangeListener, Runnable{
	JPanel panel = new JPanel();
	JLabel label = new JLabel("N = (R*) * fp * ne * fl * fi * fc * L");
    JSlider SliderR = new JSlider(0, 100, 0);
    JLabel labelR1 = new JLabel("R*");
    JLabel labelR2 = new JLabel("0");
    JSlider Sliderfp = new JSlider(0, 10, 0);
    JLabel labelfp1 = new JLabel("fp");
    JLabel labelfp2 = new JLabel("0");
    JSlider Sliderne = new JSlider(0, 50, 0);
    JLabel labelne1 = new JLabel("ne");
    JLabel labelne2 = new JLabel("0");
    JSlider Sliderfl = new JSlider(0, 10, 0);
    JLabel labelfl1 = new JLabel("fl");
    JLabel labelfl2 = new JLabel("0");
    JSlider Sliderfi = new JSlider(0, 10, 0);
    JLabel labelfi1 = new JLabel("fi");
    JLabel labelfi2 = new JLabel("0");
    JSlider Sliderfc = new JSlider(0, 10, 0);
    JLabel labelfc1 = new JLabel("fc");
    JLabel labelfc2 = new JLabel("0");
    JSlider SliderL = new JSlider(0, 100000, 0);
    JLabel labelL1 = new JLabel("L");
    JLabel labelL2 = new JLabel("0");
    JLabel N = new JLabel("N =");
    JLabel result = new JLabel("0");
    GridBagConstraints c = new GridBagConstraints();
    JButton generate = new JButton("Génère");

	public Frame() {
        this.setTitle("Équation de DRAKE");
		this.setSize(1000,500);
        this.setLocationRelativeTo(null);              
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel.setLayout(new GridBagLayout());

        c.fill = GridBagConstraints.VERTICAL;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(label, c);
        
        PlaceJSLider(1, labelR1, SliderR, labelR2, panel);
        PlaceJSLider(2, labelfp1, Sliderfp, labelfp2, panel);
        PlaceJSLider(3, labelne1, Sliderne, labelne2, panel);
        PlaceJSLider(4, labelfl1, Sliderfl, labelfl2, panel);
        PlaceJSLider(5, labelfi1, Sliderfi, labelfi2, panel);
        PlaceJSLider(6, labelfc1, Sliderfc, labelfc2, panel);
        PlaceJSLider(7, labelL1, SliderL, labelL2, panel);
        
        c.fill = GridBagConstraints.VERTICAL;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 8;
        panel.add(N, c);
        
        c.ipady = 0;
        c.gridx = 1;
        c.gridy = 8;
        panel.add(result, c);
        

        generate.addActionListener(this);
        
        c.ipady = 0;
        c.gridx = 2;
        c.gridy = 8;
        panel.add(generate, c);
        
        this.add(panel);
		
		setVisible(true);
	}
	
	public void PlaceJSLider(int y, JLabel L1, JSlider Slider, JLabel L2, JPanel panel) {
        c.fill = GridBagConstraints.VERTICAL;
        c.ipady = 0;
        c.weightx = 2;
        c.weighty = 8;
        
        c.gridx = 0;
        c.gridy = y;
        panel.add(L1, c);
		
		Slider.setPaintTrack(true); 
		Slider.setPaintTicks(true); 
		Slider.setPaintLabels(true); 
        
		Slider.addChangeListener(this); 
		
        c.gridx = 1;
        c.gridy = y;
        panel.add(Slider, c);
        
        c.gridx = 2;
        c.gridy = y;
        panel.add(L2, c);
	}
	
	public void TrouveLaVie() {
		double N, fl, fi, fc = fi = fl = N = 0;
		while(N<1) {
			fl = (double)(Math.random() * (2));
			fi = (double)(Math.random() * (2));
			fc = (double)(Math.random() * (2));
			N = 10*0.5*2*1*5000*fl/fi*fc;
		}
		SliderR.setValue(10);
		Sliderfp.setValue(5);
		Sliderne.setValue(20);
		SliderL.setValue(5000);
		Sliderfl.setValue((int)fl);
		Sliderfi.setValue((int)fi);
		Sliderfc.setValue((int)fc);

		labelR2.setText("" + 10);
		labelfp2.setText("" + 0.5);
		labelne2.setText("" + 0.2);
		labelL2.setText("" + 5000);
		labelfl2.setText("" + fl); 
		labelfi2.setText("" + fi); 
		labelfc2.setText("" + fc); 
		result.setText("" + N); 
		
	}
	
	public static void main(String [] args){
		JFrame frame = new Frame();
	}
	
	public void stateChanged(ChangeEvent e){ 
		labelR2.setText("" + SliderR.getValue()); 
		labelfp2.setText("" + Sliderfp.getValue()/10d); 
		labelne2.setText("" + Sliderne.getValue()/10d); 
		labelfl2.setText("" + Sliderfl.getValue()/10d); 
		labelfi2.setText("" + Sliderfi.getValue()/10d); 
		labelfc2.setText("" + Sliderfc.getValue()/10d); 
		labelL2.setText("" + SliderL.getValue()); 
		
		double value = SliderR.getValue() * Sliderfp.getValue()/10d * Sliderne.getValue()/10d * Sliderfl.getValue()/10d * Sliderfi.getValue()/10d * Sliderfc.getValue()/10d * SliderL.getValue();
		result.setText("" + value);
	} 

	
	public void run() {
		TrouveLaVie();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Thread t = new Thread(this);
		t.start();

	}
	
}
