package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import lejos.nxt.Motor;
import lejos.pc.comm.NXTCommException;
import command.Commando;
import command.impl.Control;
import command.interfaces.IClawControl;
import command.interfaces.IControl;

/** GUI af styring til debug..
 * 
 *  
 * 
 * @author Morten Hulvej
 */
public class BasicControlGui extends javax.swing.JFrame {

	private static final long serialVersionUID = -693154801145671834L;
	IControl controller;
	IClawControl claw;
	Commando con;
	boolean connected;

	/** Creates new form BasicControlGui */
	public BasicControlGui() throws UnsupportedLookAndFeelException,
			ClassNotFoundException, IllegalAccessException,
			InstantiationException {
		
        connected = false;
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		initComponents();

		Thread.currentThread().setName("BasicGUI");
		
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        movePanel = new javax.swing.JPanel();
        leftBtn = new javax.swing.JButton();
        rightBtn = new javax.swing.JButton();
        fwrBtn = new javax.swing.JButton();
        BckwBtn = new javax.swing.JButton();
        clawCloseBtn = new javax.swing.JButton();
        clawOpenBtn = new javax.swing.JButton();
        stopBtn = new javax.swing.JButton();
        isReversedChkBox = new javax.swing.JCheckBox();
        debugBtn = new javax.swing.JButton();
        connectBtn = new javax.swing.JButton();
        statusLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        statusPtyLabel = new javax.swing.JLabel();
        sensorPanel = new javax.swing.JPanel();
        batteryLevelBar = new javax.swing.JProgressBar();
        speedPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        speedSlider = new javax.swing.JSlider();
        turnspeedSlider = new javax.swing.JSlider();
        clawspeedSlider = new javax.swing.JSlider();
        aboutBtn = new javax.swing.JButton();
        robotChoiceComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("B.E.R.T.A | Basic Controls");
        setBounds(new java.awt.Rectangle(0, 0, 451, 470));
        setLocationByPlatform(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BasicControlGui.this.keyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BasicControlGui.this.keyReleased(evt);
            }
        });

        movePanel.setFocusable(false);

        leftBtn.setText("<");
        leftBtn.setBorderPainted(false);
        leftBtn.setEnabled(false);
        leftBtn.setFocusPainted(false);
        leftBtn.setFocusable(false);
        leftBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftBtnActionPerformed(evt);
            }
        });

        rightBtn.setText(">");
        rightBtn.setBorderPainted(false);
        rightBtn.setEnabled(false);
        rightBtn.setFocusPainted(false);
        rightBtn.setFocusable(false);
        rightBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rightBtnActionPerformed(evt);
            }
        });

        fwrBtn.setText("/\\");
            fwrBtn.setBorderPainted(false);
            fwrBtn.setEnabled(false);
            fwrBtn.setFocusPainted(false);
            fwrBtn.setFocusable(false);
            fwrBtn.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    fwrBtnActionPerformed(evt);
                }
            });

            BckwBtn.setText("\\/");
            BckwBtn.setBorderPainted(false);
            BckwBtn.setEnabled(false);
            BckwBtn.setFocusPainted(false);
            BckwBtn.setFocusable(false);
            BckwBtn.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    BckwBtnActionPerformed(evt);
                }
            });

            clawCloseBtn.setText(">-<");
            clawCloseBtn.setToolTipText("Luk KLO");
            clawCloseBtn.setBorderPainted(false);
            clawCloseBtn.setEnabled(false);
            clawCloseBtn.setFocusPainted(false);
            clawCloseBtn.setFocusable(false);
            clawCloseBtn.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    clawCloseBtnActionPerformed(evt);
                }
            });

            clawOpenBtn.setText("<->");
            clawOpenBtn.setToolTipText("Åbn KLO");
            clawOpenBtn.setBorderPainted(false);
            clawOpenBtn.setEnabled(false);
            clawOpenBtn.setFocusPainted(false);
            clawOpenBtn.setFocusable(false);
            clawOpenBtn.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    clawOpenBtnActionPerformed(evt);
                }
            });

            stopBtn.setFont(new java.awt.Font("Tahoma", 1, 11));
            stopBtn.setForeground(new java.awt.Color(255, 0, 0));
            stopBtn.setText("STOP ALT");
            stopBtn.setToolTipText("Stop alt !");
            stopBtn.setBorderPainted(false);
            stopBtn.setEnabled(false);
            stopBtn.setFocusPainted(false);
            stopBtn.setFocusable(false);
            stopBtn.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    stopBtnActionPerformed(evt);
                }
            });

            isReversedChkBox.setText("Reverse");
            isReversedChkBox.setEnabled(false);
            isReversedChkBox.setFocusPainted(false);
            isReversedChkBox.setFocusable(false);

            debugBtn.setText("jButton1");
            debugBtn.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    debugBtnActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout movePanelLayout = new javax.swing.GroupLayout(movePanel);
            movePanel.setLayout(movePanelLayout);
            movePanelLayout.setHorizontalGroup(
                movePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(movePanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(movePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(stopBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                        .addGroup(movePanelLayout.createSequentialGroup()
                            .addGroup(movePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(clawCloseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(leftBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(movePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(BckwBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                                .addComponent(fwrBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                                .addGroup(movePanelLayout.createSequentialGroup()
                                    .addComponent(debugBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)))
                            .addGroup(movePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(movePanelLayout.createSequentialGroup()
                                    .addGap(9, 9, 9)
                                    .addComponent(clawOpenBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(movePanelLayout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addGroup(movePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(isReversedChkBox)
                                        .addComponent(rightBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addContainerGap())
            );
            movePanelLayout.setVerticalGroup(
                movePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(movePanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(movePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(movePanelLayout.createSequentialGroup()
                            .addComponent(fwrBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(BckwBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(movePanelLayout.createSequentialGroup()
                            .addGroup(movePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(clawOpenBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(movePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(clawCloseBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                    .addComponent(debugBtn)))
                            .addGroup(movePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(movePanelLayout.createSequentialGroup()
                                    .addGap(46, 46, 46)
                                    .addComponent(leftBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                                .addGroup(movePanelLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rightBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(11, 11, 11)
                            .addComponent(isReversedChkBox)))
                    .addGap(15, 15, 15)
                    .addComponent(stopBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                    .addContainerGap())
            );

            connectBtn.setText("CONNECT!");
            connectBtn.setFocusable(false);
            connectBtn.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    connectBtnActionPerformed(evt);
                }
            });

            statusLabel.setText("Status:");
            statusLabel.setFocusable(false);

            statusPtyLabel.setText("NOT CONNECTED");
            statusPtyLabel.setFocusable(false);

            sensorPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Batteri"));
            sensorPanel.setFocusable(false);

            batteryLevelBar.setOrientation(1);
            batteryLevelBar.setToolTipText("Batteri-niveau");
            batteryLevelBar.setFocusable(false);

            javax.swing.GroupLayout sensorPanelLayout = new javax.swing.GroupLayout(sensorPanel);
            sensorPanel.setLayout(sensorPanelLayout);
            sensorPanelLayout.setHorizontalGroup(
                sensorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(sensorPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(batteryLevelBar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            sensorPanelLayout.setVerticalGroup(
                sensorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(sensorPanelLayout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(batteryLevelBar, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addContainerGap())
            );

            speedPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Speed"));
            speedPanel.setFocusable(false);

            jLabel1.setText("Hastighed . . . . . .");

            jLabel2.setText("Vende-hastighed  .");

            jLabel3.setText("Klo . . . . . . . . . . .");

            speedSlider.setPaintTicks(true);
            speedSlider.setEnabled(false);
            speedSlider.setFocusable(false);

            turnspeedSlider.setPaintTicks(true);
            turnspeedSlider.setEnabled(false);
            turnspeedSlider.setFocusable(false);

            clawspeedSlider.setMaximum(50);
            clawspeedSlider.setPaintTicks(true);
            clawspeedSlider.setValue(25);
            clawspeedSlider.setEnabled(false);
            clawspeedSlider.setFocusable(false);

            javax.swing.GroupLayout speedPanelLayout = new javax.swing.GroupLayout(speedPanel);
            speedPanel.setLayout(speedPanelLayout);
            speedPanelLayout.setHorizontalGroup(
                speedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(speedPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(speedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(speedPanelLayout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(4, 4, 4)
                            .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(speedPanelLayout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(turnspeedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(speedPanelLayout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(clawspeedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(85, Short.MAX_VALUE))
            );
            speedPanelLayout.setVerticalGroup(
                speedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(speedPanelLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(speedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(speedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(turnspeedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(speedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(clawspeedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
            );

            aboutBtn.setContentAreaFilled(false);
            aboutBtn.setFocusPainted(false);
            aboutBtn.setFocusable(false);
            aboutBtn.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    aboutBtnActionPerformed(evt);
                }
            });

            robotChoiceComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BERTA", "PROP" }));

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(statusLabel)
                                            .addGap(18, 18, 18)
                                            .addComponent(statusPtyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                                        .addComponent(connectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(robotChoiceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(aboutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(62, 62, 62))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(speedPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(movePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(sensorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(connectBtn)
                                .addComponent(robotChoiceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(statusLabel)
                                .addComponent(statusPtyLabel)))
                        .addComponent(aboutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(4, 4, 4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(sensorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(movePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(speedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(35, 35, 35))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

        private void aboutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutBtnActionPerformed
            
        	Image icon = new ImageIcon(getClass().getResource("/start/BERTA.png")).getImage().getScaledInstance(-1, 480, Image.SCALE_FAST);
		JOptionPane.showMessageDialog(null,
                "Orn'lig syg about box til basis kontrol-HQ-2theMaX!\n"
                + "Brugt til test af orn'lig syg B.E.R.T.A!"
                + "\n"
                + "For�r 2011 (c) Gruppe 4 - CDIO Projekt - Morten Hulvej", "Orn'lig syg",
			JOptionPane.WARNING_MESSAGE,new ImageIcon(icon));
        }//GEN-LAST:event_aboutBtnActionPerformed

        private void debugBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debugBtnActionPerformed
            try {
            	controller.move(50, false);
				
				// Open claw
				controller.openClaw();
				Thread.sleep(1000);
				controller.stopClaw();
				
				// Move forwards
				Thread.sleep(2000);
				// System.out.println("Cake delivered");
				controller.stop();
				Thread.sleep(200);
				
				// Move backwards
				controller.move(50, true);
				Thread.sleep(2000);
				
				// Close claw
				controller.closeClaw();
				Thread.sleep(1000);
				controller.stopClaw();
				controller.stop();
			allStop();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            
        }//GEN-LAST:event_debugBtnActionPerformed

	private void connectBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_connectBtnActionPerformed
		if (connected) {
			try {
				con.disconnect();
			} finally {
				reset();
			}
		} else {
			
			
			
			con = new Commando(robotChoiceComboBox.getSelectedIndex());
			
			controller = con.getControls()[robotChoiceComboBox.getSelectedIndex()];
			connected = true;
			
			statusPtyLabel.setText("Forbundet til robot ");
			statusPtyLabel.setForeground(Color.green);
			enableControls(true);
			
//			int result = -1;
//			setCursor(new Cursor(Cursor.WAIT_CURSOR));
//			try {
//				result = con.searchAndConnect(false);
//				controller = new Control(con.getNxtCommand());
//			} catch (NXTCommException e) {
//				JOptionPane.showMessageDialog(this, e.getMessage(), "Fejl",
//						JOptionPane.ERROR_MESSAGE);
//			} finally {
//				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//			}
//
//
//
//			switch (result) {
//			case IBTConnector.BERTA_FOUND:
//				statusPtyLabel.setText("Forbundet til B.E.R.T.A.");
//				statusPtyLabel.setForeground(Color.green);
//				enableControls(true);
//				connected = true;
//                                try {
//                                    batteryLevelBar.setValue(controller.getBatteryLevel());
//                                } catch (IOException e) {
//                                    JOptionPane.showMessageDialog(this, "Kunne ikke læse batteri-niveau", "Fejl",
//                                                    JOptionPane.ERROR_MESSAGE);
//                                }
//				break;
//			case IBTConnector.NO_NXT_FOUND:
//				statusPtyLabel.setText("Ingen NXT-enheder fundet");
//				statusPtyLabel.setForeground(Color.red);
//				reset();
//				break;
//			case IBTConnector.NO_BERTA_FOUND:
//				statusPtyLabel.setText("Enheder fundet, men ingen B.E.R.T.A.");
//				statusPtyLabel.setForeground(Color.yellow);
//				break;
//			default:
//				reset();
//				break;
//			}
		}

	}// GEN-LAST:event_connectBtnActionPerformed

	private void fwrBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_fwrBtnActionPerformed
		if (isReversedChkBox.isSelected())
			moveBackward();
		else
			moveForward();
	}// GEN-LAST:event_fwrBtnActionPerformed

	private void BckwBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BckwBtnActionPerformed
		if (isReversedChkBox.isSelected())
			moveForward();
		else
			moveBackward();
	}// GEN-LAST:event_BckwBtnActionPerformed

	private void stopBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_stopBtnActionPerformed
		allStop();
	}// GEN-LAST:event_stopBtnActionPerformed

	private void leftBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_leftBtnActionPerformed
		moveLeft();
	}// GEN-LAST:event_leftBtnActionPerformed

	private void rightBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_rightBtnActionPerformed
		moveRight();
	}// GEN-LAST:event_rightBtnActionPerformed

	private void clawCloseBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_clawCloseBtnActionPerformed
		closeClaw();
	}// GEN-LAST:event_clawCloseBtnActionPerformed

	private void clawOpenBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_clawOpenBtnActionPerformed
		openClaw();
	}// GEN-LAST:event_clawOpenBtnActionPerformed

	private void keyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_keyPressed
		int key = evt.getKeyCode();

//		System.out.println("KEY PRESSED: " + key);
		switch (key) {
		case KeyEvent.VK_UP:
			moveForward();
			break;
		case KeyEvent.VK_DOWN:
			moveBackward();
			break;
		case KeyEvent.VK_LEFT:
			moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			moveRight();
			break;
		case KeyEvent.VK_SPACE:
			closeClaw();
			break;
               case KeyEvent.VK_CONTROL:
			openClaw();
			break;
                default:
			break;
		}
	}// GEN-LAST:event_keyPressed

        // GEN-FIRST:event_keyReleased
	private void keyReleased(java.awt.event.KeyEvent evt) {
		int key = evt.getKeyCode();
		System.out.println("KEY RELEASED: " + key);

             // skal indtil videre stoppe alt, uanset hvilken knap man slipper
                
		switch (key) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_RIGHT:
			allStop();
			break;
        case KeyEvent.VK_SPACE:
			stopClaw();
			break;
        case KeyEvent.VK_CONTROL:
			stopClaw();
			break;
		default:
			break;
		}

	}// GEN-LAST:event_keyReleased
        // GEN-FIRST:event_formWindowClosing
	private void formWindowClosing(java.awt.event.WindowEvent evt) {

		//if (JOptionPane.CANCEL_OPTION == JOptionPane.showConfirmDialog(this,
		//		"Luk forbindelse?", "Exit?", JOptionPane.OK_CANCEL_OPTION))
		//	return;
		if (con != null)
			con.disconnect();
		System.exit(0);
	}// GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BckwBtn;
    private javax.swing.JButton aboutBtn;
    private javax.swing.JProgressBar batteryLevelBar;
    private javax.swing.JButton clawCloseBtn;
    private javax.swing.JButton clawOpenBtn;
    private javax.swing.JSlider clawspeedSlider;
    private javax.swing.JButton connectBtn;
    private javax.swing.JButton debugBtn;
    private javax.swing.JButton fwrBtn;
    private javax.swing.JCheckBox isReversedChkBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton leftBtn;
    private javax.swing.JPanel movePanel;
    private javax.swing.JButton rightBtn;
    private javax.swing.JComboBox robotChoiceComboBox;
    private javax.swing.JPanel sensorPanel;
    private javax.swing.JPanel speedPanel;
    private javax.swing.JSlider speedSlider;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel statusPtyLabel;
    private javax.swing.JButton stopBtn;
    private javax.swing.JSlider turnspeedSlider;
    // End of variables declaration//GEN-END:variables

	public void reset() {
		connectBtn.setText("CONNECT!");
		statusPtyLabel.setText("NOT CONNECTED");
		statusPtyLabel.setForeground(Color.black);
		connected = false;
        enableControls(connected);
        
	}

	public void enableControls(boolean en) {
		Component[] c = movePanel.getComponents();
		for (Component comp : c) {
			comp.setEnabled(en);
		}
		c = speedPanel.getComponents();
		for (Component comp : c) {
			comp.setEnabled(en);
		}
		if (en) connectBtn.setText("Disconnect");
	}

	public void moveForward() {
		try {
			controller.move(speedSlider.getValue(), false);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Fejl",
					JOptionPane.ERROR_MESSAGE);
			reset();
		}
	}

	public void moveBackward() {
		try {
			controller.move(speedSlider.getValue(), true);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Fejl",
					JOptionPane.ERROR_MESSAGE);
			reset();
		}
	}

	public void moveLeft() {
		try {
			controller.left(turnspeedSlider.getValue());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Fejl",
					JOptionPane.ERROR_MESSAGE);
			reset();
		}
	}

	public void moveRight() {
		try {
			controller.right(turnspeedSlider.getValue());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Fejl",
					JOptionPane.ERROR_MESSAGE);
			reset();
		}
	}

	public void openClaw() {
		try {
			controller.openClaw(clawspeedSlider.getValue());
//			claw.openClaw(clawspeedSlider.getValue());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Fejl",
					JOptionPane.ERROR_MESSAGE);
			reset();
		}
	}

	public void closeClaw() {
		try {
			controller.closeClaw(clawspeedSlider.getValue());
//			claw.closeClaw(clawspeedSlider.getValue());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Fejl",
					JOptionPane.ERROR_MESSAGE);
			reset();
		}
	}
	public void stopClaw(){
		try {
			controller.stopClaw();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Fejl",
					JOptionPane.ERROR_MESSAGE);
			reset();
		}
	}

	public void allStop() {
		try {
			controller.stop();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Fejl",
					JOptionPane.ERROR_MESSAGE);
			reset();
		}
	}
        public void updateReadings(){
        try {
            System.out.print("Updating..");
            if(connected){
                int voltage = controller.getBatteryLevel();
                batteryLevelBar.setValue(voltage);
                System.out.println("OK");
                if (voltage < 50) JOptionPane.showMessageDialog(this, 
                        "Batteri-spændingen er nu under 50%.\n"
                        + "Bemærk at spændingen IKKE falder linært,\n"
                        + "hvorfor det anbefales at skifte/lade batteri.", "Advarsel",
					JOptionPane.WARNING_MESSAGE);
            }else	System.out.println("FAIL");
                
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Kunne ikke læse batteri-niveau", "Fejl",
						JOptionPane.ERROR_MESSAGE);
        }


        }
}
