package de.my.vitnoten;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.wb.swt.SWTResourceManager;

public class VITNoten {

	protected Shell shlNoten;
	
	protected ProgressBar progressBar;
	// All the Module Spinners
	protected Spinner spinnerK1;
	protected Spinner spinnerK2;
	protected Spinner spinnerK3;
	protected Spinner spinnerK4;
	
	protected Spinner spinnerM3;
	protected Spinner spinnerM4;
	protected Spinner spinnerM5;
	protected Spinner spinnerM6;
	protected Spinner spinnerM7;
	protected Spinner spinnerM8;
	protected Spinner spinnerM9;
	protected Spinner spinnerM10;
	protected Spinner spinnerM11;
	protected Spinner spinnerM12;
	protected Spinner spinnerM13;
	protected Spinner spinnerM14;
	protected Spinner spinnerM15;
	protected Spinner spinnerM22;
	protected Spinner spinnerM34;
	protected Spinner spinnerM35;
	
	protected Spinner spinnerPraktikum;
	
	protected Spinner spinnerDiplSchr;
	protected Spinner spinnerDiplMndl;
	protected Spinner spinnerDiplPrsi;
	
	protected StyledText stResult;
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			VITNoten window = new VITNoten();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlNoten.open();
		shlNoten.layout();
		while (!shlNoten.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlNoten = new Shell(SWT.CLOSE | SWT.TITLE | SWT.MIN);
		shlNoten.setSize(585, 512);
		shlNoten.setText("VIT Noten");
		//shellNoten.setImage(SWTResourceManager.getImage("images/diplomaicon.png"));
		shlNoten.setLayout(null);
		
		Menu menu = new Menu(shlNoten, SWT.BAR);
		shlNoten.setMenuBar(menu);
		
		MenuItem mntmDatei = new MenuItem(menu, SWT.CASCADE);
		mntmDatei.setText("Datei");
		
		Menu menu_1 = new Menu(mntmDatei);
		mntmDatei.setMenu(menu_1);
		
		MenuItem mntmffnen = new MenuItem(menu_1, SWT.NONE);
		mntmffnen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(shlNoten, SWT.OPEN);
				fd.setFilterExtensions(new String[] { "*.vit", "*.*"});
				String file = fd.open();
				if(file != null) {
					readFile(file);
					setProgress();
					calculateGrade();
				}
				
			}
		});
		mntmffnen.setText("\u00D6ffnen");
		
		MenuItem mntmSpeichern = new MenuItem(menu_1, SWT.NONE);
		mntmSpeichern.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(shlNoten, SWT.SAVE);
				fd.setFilterExtensions(new String[] { "*.vit", "*.*"});
				String file = fd.open();
				if(file != null) {
					writeFile(file);
				}
				
			}
		});		
		mntmSpeichern.setText("Speichern");
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmBeenden = new MenuItem(menu_1, SWT.NONE);
		mntmBeenden.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				System.exit(0);
			}
		});
		mntmBeenden.setText("Beenden");
		
		
		MenuItem mntmAbout = new MenuItem(menu, SWT.NONE);
		mntmAbout.setText("About");
		
		progressBar = new ProgressBar(shlNoten, SWT.NONE);
		progressBar.setBounds(10, 10, 559, 17);
		
		Group grpModulnoten = new Group(shlNoten, SWT.NONE);
		grpModulnoten.setText("Modulnoten");
		grpModulnoten.setBounds(10, 33, 559, 307);
		
		Group grpGs = new Group(grpModulnoten, SWT.NONE);
		grpGs.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		grpGs.setText("GS");
		grpGs.setBounds(10, 25, 131, 207);
		
		Label lblK1 = new Label(grpGs, SWT.NONE);
		lblK1.setToolTipText("");
		lblK1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblK1.setBounds(10, 28, 55, 15);
		lblK1.setText("Klausur 1");
		
		this.spinnerK1 = new Spinner(grpGs, SWT.BORDER);
		spinnerK1.setMaximum(15);
		spinnerK1.setBounds(71, 25, 47, 22);
		
		Label lblK2 = new Label(grpGs, SWT.NONE);
		lblK2.setToolTipText("");
		lblK2.setText("Klausur 2");
		lblK2.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblK2.setBounds(10, 52, 55, 15);
		
		this.spinnerK2 = new Spinner(grpGs, SWT.BORDER);
		spinnerK2.setMaximum(15);
		spinnerK2.setBounds(71, 49, 47, 22);
		
		Label lblK3 = new Label(grpGs, SWT.NONE);
		lblK3.setToolTipText("");
		lblK3.setText("Klausur 3");
		lblK3.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblK3.setBounds(10, 76, 55, 15);
		
		this.spinnerK3 = new Spinner(grpGs, SWT.BORDER);
		spinnerK3.setMaximum(15);
		spinnerK3.setBounds(71, 73, 47, 22);
		
		this.spinnerK4 = new Spinner(grpGs, SWT.BORDER);
		spinnerK4.setMaximum(15);
		spinnerK4.setBounds(71, 97, 47, 22);
		
		Label lblK4 = new Label(grpGs, SWT.NONE);
		lblK4.setToolTipText("");
		lblK4.setText("Klausur 4");
		lblK4.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblK4.setBounds(10, 100, 55, 15);
		
		Group grpH = new Group(grpModulnoten, SWT.NONE);
		grpH.setText("H 1");
		grpH.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		grpH.setBounds(147, 25, 131, 207);
		
		Label lblM3 = new Label(grpH, SWT.NONE);
		lblM3.setToolTipText("Grundlagen von Datenbanksystemen");
		lblM3.setText("M3");
		lblM3.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblM3.setBounds(10, 28, 55, 15);
		
		this.spinnerM3 = new Spinner(grpH, SWT.BORDER);
		spinnerM3.setMaximum(15);
		spinnerM3.setBounds(71, 25, 47, 22);
		
		Label lblM4 = new Label(grpH, SWT.NONE);
		lblM4.setToolTipText("Betriebssysteme");
		lblM4.setText("M4");
		lblM4.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblM4.setBounds(10, 52, 55, 15);
		
		this.spinnerM4 = new Spinner(grpH, SWT.BORDER);
		spinnerM4.setMaximum(15);
		spinnerM4.setBounds(71, 49, 47, 22);
		
		Label lblM9 = new Label(grpH, SWT.NONE);
		lblM9.setToolTipText("Modellierung von Systemen und Prozessen");
		lblM9.setText("M9");
		lblM9.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblM9.setBounds(10, 100, 55, 15);
		
		this.spinnerM9 = new Spinner(grpH, SWT.BORDER);
		spinnerM9.setMaximum(15);
		spinnerM9.setBounds(71, 97, 47, 22);
		
		this.spinnerM11 = new Spinner(grpH, SWT.BORDER);
		spinnerM11.setMaximum(15);
		spinnerM11.setBounds(71, 121, 47, 22);
		
		Label lblM11 = new Label(grpH, SWT.NONE);
		lblM11.setToolTipText(" Grundlagen des eGovernments");
		lblM11.setText("M11");
		lblM11.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblM11.setBounds(10, 124, 55, 15);
		
		this.spinnerM22 = new Spinner(grpH, SWT.BORDER);
		spinnerM22.setMaximum(15);
		spinnerM22.setBounds(71, 145, 47, 22);
		
		Label lblM22 = new Label(grpH, SWT.NONE);
		lblM22.setToolTipText("Internes Rechnungswesen und Verwaltungsbetriebswirtschaftslehre (Teil 1 - intern)");
		lblM22.setText("M22");
		lblM22.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblM22.setBounds(10, 148, 55, 15);
		
		Label lblM34 = new Label(grpH, SWT.NONE);
		lblM34.setToolTipText(" Aufgaben der BV II (Teil 1)");
		lblM34.setText("M34");
		lblM34.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblM34.setBounds(10, 172, 55, 15);
		
		this.spinnerM34 = new Spinner(grpH, SWT.BORDER);
		spinnerM34.setMaximum(15);
		spinnerM34.setBounds(71, 169, 47, 22);
		
		Label lblM7 = new Label(grpH, SWT.NONE);
		lblM7.setBounds(10, 76, 55, 15);
		lblM7.setToolTipText("Softwareengineering und Projektmanagement (Teil 1)");
		lblM7.setText("M7");
		lblM7.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		
		this.spinnerM7 = new Spinner(grpH, SWT.BORDER);
		spinnerM7.setBounds(71, 73, 47, 22);
		spinnerM7.setMaximum(15);
		
		Group grpH_1 = new Group(grpModulnoten, SWT.NONE);
		grpH_1.setText("H 2");
		grpH_1.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		grpH_1.setBounds(284, 25, 131, 207);
		
		Label lblM5 = new Label(grpH_1, SWT.NONE);
		lblM5.setToolTipText("IT-Architekturen und Rechnernetzwerke");
		lblM5.setText("M5");
		lblM5.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblM5.setBounds(10, 28, 55, 15);
		
		this.spinnerM5 = new Spinner(grpH_1, SWT.BORDER);
		spinnerM5.setMaximum(15);
		spinnerM5.setBounds(71, 25, 47, 22);
		
		Label lblM8 = new Label(grpH_1, SWT.NONE);
		lblM8.setToolTipText("Grundlagen der Programmierung (mit JAVA)");
		lblM8.setText("M8");
		lblM8.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblM8.setBounds(10, 52, 55, 15);
		
		this.spinnerM8 = new Spinner(grpH_1, SWT.BORDER);
		spinnerM8.setMaximum(15);
		spinnerM8.setBounds(71, 49, 47, 22);
		
		Label lblM12 = new Label(grpH_1, SWT.NONE);
		lblM12.setToolTipText(" ERP-Systeme");
		lblM12.setText("M12");
		lblM12.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblM12.setBounds(10, 76, 55, 15);
		
		this.spinnerM12 = new Spinner(grpH_1, SWT.BORDER);
		spinnerM12.setMaximum(15);
		spinnerM12.setBounds(71, 73, 47, 22);
		
		this.spinnerM13 = new Spinner(grpH_1, SWT.BORDER);
		spinnerM13.setMaximum(15);
		spinnerM13.setBounds(71, 97, 47, 22);
		
		Label lblM13 = new Label(grpH_1, SWT.NONE);
		lblM13.setToolTipText("Informations- und Wissensmanagement");
		lblM13.setText("M13");
		lblM13.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblM13.setBounds(10, 100, 55, 15);
		
		this.spinnerM14 = new Spinner(grpH_1, SWT.BORDER);
		spinnerM14.setMaximum(15);
		spinnerM14.setBounds(71, 121, 47, 22);
		
		Label lblM14 = new Label(grpH_1, SWT.NONE);
		lblM14.setToolTipText("Grundlagen der Qualit\u00E4tssicherung und Evaluation");
		lblM14.setText("M14");
		lblM14.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblM14.setBounds(10, 124, 55, 15);
		
		Label lblM35 = new Label(grpH_1, SWT.NONE);
		lblM35.setToolTipText("Managementkonzepte in der BV (Teil 1)");
		lblM35.setText("M35");
		lblM35.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblM35.setBounds(10, 148, 55, 15);
		
		this.spinnerM35 = new Spinner(grpH_1, SWT.BORDER);
		spinnerM35.setMaximum(15);
		spinnerM35.setBounds(71, 145, 47, 22);
		
		Group grpH_2 = new Group(grpModulnoten, SWT.NONE);
		grpH_2.setText("H 3");
		grpH_2.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		grpH_2.setBounds(421, 25, 131, 207);
		
		Label lblM6 = new Label(grpH_2, SWT.NONE);
		lblM6.setToolTipText("IT-Sicherheit");
		lblM6.setText("M6");
		lblM6.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblM6.setBounds(10, 28, 55, 15);
		
		this.spinnerM6 = new Spinner(grpH_2, SWT.BORDER);
		spinnerM6.setMaximum(15);
		spinnerM6.setBounds(71, 25, 47, 22);
		
		Label lblM10 = new Label(grpH_2, SWT.NONE);
		lblM10.setToolTipText("Web-Technologie und Portall\u00F6sungen");
		lblM10.setText("M10");
		lblM10.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblM10.setBounds(10, 52, 55, 15);
		
		this.spinnerM10 = new Spinner(grpH_2, SWT.BORDER);
		spinnerM10.setMaximum(15);
		spinnerM10.setBounds(71, 49, 47, 22);
		
		Label lblM15 = new Label(grpH_2, SWT.NONE);
		lblM15.setToolTipText("Servicemanagement (ITIL)");
		lblM15.setText("M15");
		lblM15.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblM15.setBounds(10, 76, 55, 15);
		
		this.spinnerM15 = new Spinner(grpH_2, SWT.BORDER);
		spinnerM15.setMaximum(15);
		spinnerM15.setBounds(71, 73, 47, 22);
		
		Group grpDiplomarbeit = new Group(grpModulnoten, SWT.NONE);
		grpDiplomarbeit.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		grpDiplomarbeit.setText("Diplomarbeit");
		grpDiplomarbeit.setBounds(147, 238, 405, 60);
		
		Label lblDiplSchr = new Label(grpDiplomarbeit, SWT.NONE);
		lblDiplSchr.setBounds(10, 31, 55, 15);
		lblDiplSchr.setToolTipText("");
		lblDiplSchr.setText("Schriftlich");
		lblDiplSchr.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		
		this.spinnerDiplSchr = new Spinner(grpDiplomarbeit, SWT.BORDER);
		spinnerDiplSchr.setBounds(71, 28, 47, 22);
		spinnerDiplSchr.setMaximum(15);
		
		Label lblPrsi = new Label(grpDiplomarbeit, SWT.NONE);
		lblPrsi.setToolTipText("");
		lblPrsi.setText("Pr\u00E4si");
		lblPrsi.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblPrsi.setBounds(147, 31, 55, 15);
		
		this.spinnerDiplPrsi = new Spinner(grpDiplomarbeit, SWT.BORDER);
		spinnerDiplPrsi.setMaximum(15);
		spinnerDiplPrsi.setBounds(208, 28, 47, 22);
		
		this.spinnerDiplMndl = new Spinner(grpDiplomarbeit, SWT.BORDER);
		spinnerDiplMndl.setMaximum(15);
		spinnerDiplMndl.setBounds(348, 28, 47, 22);
		
		Label lblMndl = new Label(grpDiplomarbeit, SWT.NONE);
		lblMndl.setToolTipText("");
		lblMndl.setText("M\u00FCndl.");
		lblMndl.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblMndl.setBounds(287, 31, 55, 15);
		
		Group grpPraktikum = new Group(grpModulnoten, SWT.NONE);
		grpPraktikum.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		grpPraktikum.setText("Praktikum");
		grpPraktikum.setBounds(10, 238, 131, 60);
		
		Label lblBewertung = new Label(grpPraktikum, SWT.NONE);
		lblBewertung.setToolTipText("");
		lblBewertung.setText("Bewertung");
		lblBewertung.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblBewertung.setBounds(10, 23, 55, 15);
		
		this.spinnerPraktikum = new Spinner(grpPraktikum, SWT.BORDER);
		spinnerPraktikum.setMaximum(15);
		spinnerPraktikum.setBounds(71, 20, 47, 22);
		
		Group grpErgebnis = new Group(shlNoten, SWT.NONE);
		grpErgebnis.setText("Ergebnis");
		grpErgebnis.setBounds(10, 346, 559, 108);
		
		this.stResult = new StyledText(grpErgebnis, SWT.READ_ONLY);
		stResult.setBounds(10, 23, 539, 75);
		
		
		ModifyListener ml = new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				calculateGrade();
				setProgress();
			}
		};
		
		this.spinnerK1.addModifyListener(ml);
		this.spinnerK2.addModifyListener(ml);
		this.spinnerK3.addModifyListener(ml);
		this.spinnerK4.addModifyListener(ml);
		
		this.spinnerM3.addModifyListener(ml);
		this.spinnerM4.addModifyListener(ml);
		this.spinnerM5.addModifyListener(ml);
		this.spinnerM6.addModifyListener(ml);
		this.spinnerM7.addModifyListener(ml);
		this.spinnerM8.addModifyListener(ml);
		this.spinnerM9.addModifyListener(ml);
		this.spinnerM10.addModifyListener(ml);
		this.spinnerM11.addModifyListener(ml);
		this.spinnerM12.addModifyListener(ml);
		this.spinnerM13.addModifyListener(ml);
		this.spinnerM14.addModifyListener(ml);
		this.spinnerM15.addModifyListener(ml);
		this.spinnerM22.addModifyListener(ml);
		this.spinnerM34.addModifyListener(ml);
		this.spinnerM35.addModifyListener(ml);
		
		this.spinnerPraktikum.addModifyListener(ml);
		this.spinnerDiplSchr.addModifyListener(ml);
		this.spinnerDiplMndl.addModifyListener(ml);
		this.spinnerDiplPrsi.addModifyListener(ml);
		

	}
	
	
	private void calculateGrade() {
		double zp = (Double.valueOf(this.spinnerK1.getText()) + Double.valueOf(this.spinnerK2.getText()) + Double.valueOf(this.spinnerK3.getText()) + Double.valueOf(this.spinnerK4.getText())) / 4;
		
		double mp = (Double.valueOf(this.spinnerM3.getText()) + Double.valueOf(this.spinnerM4.getText()) + Double.valueOf(this.spinnerM5.getText()) + Double.valueOf(this.spinnerM6.getText()) +   
				Double.valueOf(this.spinnerM7.getText()) + Double.valueOf(this.spinnerM8.getText()) + Double.valueOf(this.spinnerM9.getText()) + Double.valueOf(this.spinnerM10.getText()) +    
				Double.valueOf(this.spinnerM11.getText()) + Double.valueOf(this.spinnerM12.getText()) + Double.valueOf(this.spinnerM13.getText()) + Double.valueOf(this.spinnerM14.getText()) +    
				Double.valueOf(this.spinnerM15.getText()) + Double.valueOf(this.spinnerM22.getText()) + Double.valueOf(this.spinnerM34.getText()) + Double.valueOf(this.spinnerM35.getText())) / 16;    
		
		
		double pp = Double.valueOf(this.spinnerPraktikum.getText()); 
				
		double dp = (Double.valueOf(this.spinnerDiplSchr.getText()) * 0.75) + (Double.valueOf(this.spinnerDiplPrsi.getText()) * 0.1) + (Double.valueOf(this.spinnerDiplMndl.getText()) * 0.15);
		
		double result = (zp * 0.05) + (mp * 0.7) + (pp * 0.05) + (dp * 0.2);
		double resultround = result;
		if(result > 5) {
			resultround = Math.round(result);
		}
		
		this.stResult.setText("Ergebnis Zwischenpr�fung: "+ String.valueOf(zp)+"\nErgebnis Module: "+String.valueOf(mp)+"\nErgebnis Diplompr�fung: "+String.valueOf(dp)+"\nGesamt: " + String.valueOf(resultround) + "(" + String.valueOf(result) + ")");
				
	}
	
	private void setProgress() {
		//21
		int count = 0;
		if(this.spinnerK1.getText().equals("0") && this.spinnerK2.getText().equals("0") && this.spinnerK3.getText().equals("0") && this.spinnerK4.getText().equals("0")) count++;
		
		if(this.spinnerM3.getText().equals("0")) count++;
		if(this.spinnerM4.getText().equals("0")) count++;
		if(this.spinnerM5.getText().equals("0")) count++;
		if(this.spinnerM6.getText().equals("0")) count++;
		if(this.spinnerM7.getText().equals("0")) count++;
		if(this.spinnerM8.getText().equals("0")) count++;
		if(this.spinnerM9.getText().equals("0")) count++;
		if(this.spinnerM10.getText().equals("0")) count++;
		if(this.spinnerM11.getText().equals("0")) count++;
		if(this.spinnerM12.getText().equals("0")) count++;
		if(this.spinnerM13.getText().equals("0")) count++;
		if(this.spinnerM14.getText().equals("0")) count++;
		if(this.spinnerM15.getText().equals("0")) count++;
		if(this.spinnerM22.getText().equals("0")) count++;
		if(this.spinnerM34.getText().equals("0")) count++;
		if(this.spinnerM35.getText().equals("0")) count++;
		
		if(this.spinnerPraktikum.getText().equals("0")) count++;
		if(this.spinnerDiplSchr.getText().equals("0")) count++;
		if(this.spinnerDiplMndl.getText().equals("0")) count++;
		if(this.spinnerDiplPrsi.getText().equals("0")) count++;
		
		this.progressBar.setMaximum(21);
		this.progressBar.setSelection((21 - count));
	}
	
	
	private void readFile(String filename) {
		System.out.println("Read file: " + filename);
		
		String grades = "";
		BufferedReader br = null;
	    try {
	    	br = new BufferedReader(new FileReader(filename));
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            line = br.readLine();
	        }
	        grades = sb.toString();
	    } catch (IOException e) {
	    	// nothing, not important :)
	    } finally {
	        try {
				br.close();
			} catch (IOException e) {}
	    }
	    
	    if(grades.length() > 0) {
	    	String[] parts = grades.split(";");
	    	this.spinnerK1.setSelection(Integer.valueOf(parts[0]));
	    	this.spinnerK2.setSelection(Integer.valueOf(parts[1]));
	    	this.spinnerK3.setSelection(Integer.valueOf(parts[2]));
	    	this.spinnerK4.setSelection(Integer.valueOf(parts[3]));
	    	
	    	this.spinnerM3.setSelection(Integer.valueOf(parts[4]));
	    	this.spinnerM4.setSelection(Integer.valueOf(parts[5]));
	    	this.spinnerM5.setSelection(Integer.valueOf(parts[6]));
	    	this.spinnerM6.setSelection(Integer.valueOf(parts[7]));
	    	this.spinnerM7.setSelection(Integer.valueOf(parts[8]));
	    	this.spinnerM8.setSelection(Integer.valueOf(parts[9]));
	    	this.spinnerM9.setSelection(Integer.valueOf(parts[10]));
	    	this.spinnerM10.setSelection(Integer.valueOf(parts[11]));
	    	this.spinnerM11.setSelection(Integer.valueOf(parts[12]));
	    	this.spinnerM12.setSelection(Integer.valueOf(parts[13]));
	    	this.spinnerM13.setSelection(Integer.valueOf(parts[14]));
	    	this.spinnerM14.setSelection(Integer.valueOf(parts[15]));
	    	this.spinnerM15.setSelection(Integer.valueOf(parts[16]));
	    	this.spinnerM22.setSelection(Integer.valueOf(parts[17]));
	    	this.spinnerM34.setSelection(Integer.valueOf(parts[18]));
	    	this.spinnerM35.setSelection(Integer.valueOf(parts[19]));
	    	
	    	this.spinnerPraktikum.setSelection(Integer.valueOf(parts[20]));
	    	this.spinnerDiplSchr.setSelection(Integer.valueOf(parts[21]));
	    	this.spinnerDiplPrsi.setSelection(Integer.valueOf(parts[22]));
	    	this.spinnerDiplMndl.setSelection(Integer.valueOf(parts[23]));
	    	
	    }
	}
	
	private void writeFile(String filename) {
		System.out.println("Write file: " + filename);
		
		String data = "";
		data = data + this.spinnerK1.getText() + ";";
		data = data + this.spinnerK2.getText() + ";";
		data = data + this.spinnerK3.getText() + ";";
		data = data + this.spinnerK4.getText() + ";";
		
		data = data + this.spinnerM3.getText() + ";";
		data = data + this.spinnerM4.getText() + ";";
		data = data + this.spinnerM5.getText() + ";";
		data = data + this.spinnerM6.getText() + ";";
		data = data + this.spinnerM7.getText() + ";";
		data = data + this.spinnerM8.getText() + ";";
		data = data + this.spinnerM9.getText() + ";";
		data = data + this.spinnerM10.getText() + ";";
		data = data + this.spinnerM11.getText() + ";";
		data = data + this.spinnerM12.getText() + ";";
		data = data + this.spinnerM13.getText() + ";";
		data = data + this.spinnerM14.getText() + ";";
		data = data + this.spinnerM15.getText() + ";";
		data = data + this.spinnerM22.getText() + ";";
		data = data + this.spinnerM34.getText() + ";";
		data = data + this.spinnerM35.getText() + ";";
		
		data = data + this.spinnerPraktikum.getText() + ";";
		
		data = data + this.spinnerDiplSchr.getText() + ";";
		data = data + this.spinnerDiplPrsi.getText() + ";";
		data = data + this.spinnerDiplMndl.getText() + ";";
		
		try {
			File file = new File(filename);
			if (!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(data);
			bw.close();
			
		} catch(IOException e) {
			
		}
	
	}
}
