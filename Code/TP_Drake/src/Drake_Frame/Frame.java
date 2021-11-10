package Drake_Frame;

import javax.swing.event.*; 
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Vector;

import javax.swing.*; 

public class Frame extends JFrame implements ActionListener, ChangeListener, Runnable{
	JPanel panel = new JPanel();
	JLabel label = new JLabel("N = (R*) * fp * ne * fl * fi * fc * L");
    JSlider SliderR = new JSlider(0, 100, 0);
    JLabel labelR1 = new JLabel("R*");
    JLabel labelR2 = new JLabel("0");
    JSlider Sliderfp = new JSlider(0, 100, 0);
    JLabel labelfp1 = new JLabel("fp");
    JLabel labelfp2 = new JLabel("0");
    JSlider Sliderne = new JSlider(0, 500, 0);
    JLabel labelne1 = new JLabel("ne");
    JLabel labelne2 = new JLabel("0");
    JSlider Sliderfl = new JSlider(0, 100, 0);
    JLabel labelfl1 = new JLabel("fl");
    JLabel labelfl2 = new JLabel("0");
    JSlider Sliderfi = new JSlider(0, 100, 0);
    JLabel labelfi1 = new JLabel("fi");
    JLabel labelfi2 = new JLabel("0");
    JSlider Sliderfc = new JSlider(0, 100, 0);
    JLabel labelfc1 = new JLabel("fc");
    JLabel labelfc2 = new JLabel("0");
    JSlider SliderL = new JSlider(0, 100000, 0);
    JLabel labelL1 = new JLabel("L");
    JLabel labelL2 = new JLabel("0");
    JLabel N = new JLabel("N =");
    JLabel result = new JLabel("0");
    GridBagConstraints c = new GridBagConstraints();
    GridBagConstraints c1 = new GridBagConstraints();
    JButton generate = new JButton("Génère");
    JButton start = new JButton("Start");
    JButton stop = new JButton("Stop");
    Thread t;
    SwingWorker worker;
    
	public Frame() {
        this.setTitle("Équation de DRAKE");
		this.setSize(500,500);
        this.setLocationRelativeTo(null);              
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel.setLayout(new GridBagLayout());

        c1.fill = GridBagConstraints.VERTICAL;
        c1.gridwidth = GridBagConstraints.REMAINDER;
        c1.gridx = 0;
        c1.gridy = 0;
        panel.add(label, c1);
        
        PlaceJSLider(1, labelR1, SliderR, labelR2, panel);
        PlaceJSLider(2, labelfp1, Sliderfp, labelfp2, panel);
        PlaceJSLider(3, labelne1, Sliderne, labelne2, panel);
        PlaceJSLider(4, labelfl1, Sliderfl, labelfl2, panel);
        PlaceJSLider(5, labelfi1, Sliderfi, labelfi2, panel);
        PlaceJSLider(6, labelfc1, Sliderfc, labelfc2, panel);
        PlaceJSLider(7, labelL1, SliderL, labelL2, panel);

        c.weightx = 1;
        c.anchor = GridBagConstraints.LINE_END;
        c.gridx = 0;
        c.gridy = 8;
        panel.add(N, c);
        
        c.weightx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 1;
        c.gridy = 8;
        panel.add(result, c);        

        generate.addActionListener(this);

        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 2;
        c.gridy = 8;
        panel.add(generate, c);
        
        start.addActionListener(this);
        stop.addActionListener(this);
        
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 9;
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(start, c);
        
        c.weightx = 1;
        c.gridx = 2;
        c.gridy = 9;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(stop, c);
        
        this.add(panel);

        t = new Thread(this);
		
		setVisible(true);
	}
	
	public void PlaceJSLider(int y, JLabel L1, JSlider Slider, JLabel L2, JPanel panel) {
        c.weightx = 2;
        c.weighty = 8;
        
        c.gridx = 0;
        c.gridy = y;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(L1, c);
		
		Slider.setPaintTrack(true); 
		Slider.setPaintTicks(true); 
		Slider.setPaintLabels(true); 
        
		Slider.addChangeListener(this); 
		
        c.gridx = 1;
        c.gridy = y;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(Slider, c);
        
        c.gridx = 2;
        c.gridy = y;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(L2, c);
	}
	
	public void TrouveLaVie() {
		double N, fl, fi, fc = fi = fl = N = 0;
		while(N<1) {
			fl = (double)(Math.random());
			fi = (double)(Math.random());
			fc = (double)(Math.random());
			N = 10*0.5*2*1*5000*fl/fi*fc;
		}
		SliderR.setValue(10);
		Sliderfp.setValue(5);
		Sliderne.setValue(2*100);
		SliderL.setValue(5000);
		Sliderfl.setValue((int)(fl*100));
		Sliderfi.setValue((int)(fi*100));
		Sliderfc.setValue((int)(fc*100));

		labelR2.setText("" + 10);
		labelfp2.setText("" + 0.5);
		labelne2.setText("" + 0.2);
		labelL2.setText("" + 5000);
		BigDecimal bd = new BigDecimal(fl).setScale(2, RoundingMode.HALF_UP);
		labelfl2.setText("" + bd.doubleValue()); 
		bd = new BigDecimal(fi).setScale(2, RoundingMode.HALF_UP);
		labelfi2.setText("" + bd.doubleValue()); 
		bd = new BigDecimal(fi).setScale(2, RoundingMode.HALF_UP);
		labelfc2.setText("" + bd.doubleValue()); 
		bd = new BigDecimal(N).setScale(0, RoundingMode.HALF_UP);
		result.setText("" + bd.intValue()); 
		
	}
	
	public static void main(String [] args){
		JFrame frame = new Frame();
	}
	
	public void stateChanged(ChangeEvent e){ 
		labelR2.setText("" + SliderR.getValue()); 
		labelfp2.setText("" + Sliderfp.getValue()/100d); 
		labelne2.setText("" + Sliderne.getValue()/100d); 
		labelfl2.setText("" + Sliderfl.getValue()/100d); 
		labelfi2.setText("" + Sliderfi.getValue()/100d); 
		labelfc2.setText("" + Sliderfc.getValue()/100d); 
		labelL2.setText("" + SliderL.getValue()); 
		
		double value = SliderR.getValue() * Sliderfp.getValue()/100d * Sliderne.getValue()/100d * Sliderfl.getValue()/100d * Sliderfi.getValue()/100d * Sliderfc.getValue()/100d * SliderL.getValue();
		result.setText("" + value);
	} 

	
	public void run() {
		while(true) {
			try {
				TrouveLaVie();
			}catch(Exception e) {;}
		}
	}
	
	public void Lenomqutuveuxonsenfou() {
		worker = new SwingWorker() {

			protected Object doInBackground() throws Exception {
				String a = "a";
				while(a.equals(a)) {
					TrouveLaVie();
					Thread.sleep(1000);
				}
				return null;
			}			
		};
		worker.execute();
	}
	
	public void AutoVie() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case "Génère":
				TrouveLaVie();
				break;
			case "Start":
				Lenomqutuveuxonsenfou();
				break;
			case "Stop":
				try {
					worker.cancel(true);
				}catch(Exception ex) {;}
				break;
		}

	}
	
}
