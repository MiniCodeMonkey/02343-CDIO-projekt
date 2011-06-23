package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import command.Commando;
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
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

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
        clawCloseLabel = new javax.swing.JLabel();
        clawOpenLabel = new javax.swing.JLabel();
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
        speedValueLabel = new javax.swing.JLabel();
        turnValueLabel = new javax.swing.JLabel();
        clawValueLabel = new javax.swing.JLabel();
        speedResetButton = new javax.swing.JButton();
        turnResetButton = new javax.swing.JButton();
        clawResetButton = new javax.swing.JButton();
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
        movePanel.setLayout(new java.awt.GridBagLayout());

        leftBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/left.png"))); // NOI18N
        leftBtn.setBorderPainted(false);
        leftBtn.setEnabled(false);
        leftBtn.setFocusPainted(false);
        leftBtn.setFocusable(false);
        leftBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = -17;
        gridBagConstraints.ipady = 17;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(26, 65, 0, 0);
        movePanel.add(leftBtn, gridBagConstraints);

        rightBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/right.png"))); // NOI18N
        rightBtn.setBorderPainted(false);
        rightBtn.setEnabled(false);
        rightBtn.setFocusPainted(false);
        rightBtn.setFocusable(false);
        rightBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rightBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = -14;
        gridBagConstraints.ipady = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(29, 6, 0, 0);
        movePanel.add(rightBtn, gridBagConstraints);

        fwrBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/up.png"))); // NOI18N
        fwrBtn.setBorderPainted(false);
        fwrBtn.setEnabled(false);
        fwrBtn.setFocusPainted(false);
        fwrBtn.setFocusable(false);
        fwrBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fwrBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 12;
        gridBagConstraints.ipady = -5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 6, 0, 0);
        movePanel.add(fwrBtn, gridBagConstraints);

        BckwBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/down.png"))); // NOI18N
        BckwBtn.setBorderPainted(false);
        BckwBtn.setEnabled(false);
        BckwBtn.setFocusPainted(false);
        BckwBtn.setFocusable(false);
        BckwBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BckwBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.ipadx = 12;
        gridBagConstraints.ipady = -5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 6, 0, 0);
        movePanel.add(BckwBtn, gridBagConstraints);

        clawCloseBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close_50.png"))); // NOI18N
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 0, 0);
        movePanel.add(clawCloseBtn, gridBagConstraints);

        clawOpenBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/open_50.png"))); // NOI18N
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 9, 0, 10);
        movePanel.add(clawOpenBtn, gridBagConstraints);

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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.ipadx = 236;
        gridBagConstraints.ipady = 39;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 10, 11, 10);
        movePanel.add(stopBtn, gridBagConstraints);

        isReversedChkBox.setText("Reverse");
        isReversedChkBox.setEnabled(false);
        isReversedChkBox.setFocusPainted(false);
        isReversedChkBox.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 0, 0);
        movePanel.add(isReversedChkBox, gridBagConstraints);

        debugBtn.setText("jButton1");
        debugBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debugBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = -45;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 34, 0, 0);
        movePanel.add(debugBtn, gridBagConstraints);

        clawCloseLabel.setText("SPACE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 51, 0, 0);
        movePanel.add(clawCloseLabel, gridBagConstraints);

        clawOpenLabel.setText("CTRL");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 55, 0, 0);
        movePanel.add(clawOpenLabel, gridBagConstraints);

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
        speedPanel.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Hastighed . . . . . .");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(31, 16, 0, 0);
        speedPanel.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Vende-hastighed  .");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 16, 0, 0);
        speedPanel.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Klo . . . . . . . . . . .");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 16, 0, 0);
        speedPanel.add(jLabel3, gridBagConstraints);

        speedSlider.setPaintTicks(true);
        speedSlider.setEnabled(false);
        speedSlider.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 164;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(31, 3, 0, 0);
        speedPanel.add(speedSlider, gridBagConstraints);

        turnspeedSlider.setPaintTicks(true);
        turnspeedSlider.setEnabled(false);
        turnspeedSlider.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 164;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 4, 0, 91);
        speedPanel.add(turnspeedSlider, gridBagConstraints);

        clawspeedSlider.setMaximum(50);
        clawspeedSlider.setPaintTicks(true);
        clawspeedSlider.setValue(25);
        clawspeedSlider.setEnabled(false);
        clawspeedSlider.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 164;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 4, 7, 91);
        speedPanel.add(clawspeedSlider, gridBagConstraints);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, speedSlider, org.jdesktop.beansbinding.ELProperty.create("${value}"), speedValueLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        speedPanel.add(speedValueLabel, gridBagConstraints);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, turnspeedSlider, org.jdesktop.beansbinding.ELProperty.create("${value}"), turnValueLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        speedPanel.add(turnValueLabel, gridBagConstraints);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, clawspeedSlider, org.jdesktop.beansbinding.ELProperty.create("${value}"), clawValueLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        speedPanel.add(clawValueLabel, gridBagConstraints);

        speedResetButton.setText("reset");
        speedResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speedResetButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        speedPanel.add(speedResetButton, gridBagConstraints);

        turnResetButton.setText("reset");
        turnResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                turnResetButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        speedPanel.add(turnResetButton, gridBagConstraints);

        clawResetButton.setText("reset");
        clawResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clawResetButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        speedPanel.add(clawResetButton, gridBagConstraints);

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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(movePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sensorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(speedPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(statusLabel)
                                                .addGap(18, 18, 18)
                                                .addComponent(statusPtyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                                            .addComponent(connectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(robotChoiceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(aboutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(62, 62, 62)))
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
                .addContainerGap())
        );

        bindingGroup.bind();

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

        private void turnResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_turnResetButtonActionPerformed
            turnspeedSlider.setValue(50);
        	// TODO add your handling code here:
        }//GEN-LAST:event_turnResetButtonActionPerformed

        private void speedResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speedResetButtonActionPerformed
            speedSlider.setValue(50);
        	// TODO add your handling code here:
        }//GEN-LAST:event_speedResetButtonActionPerformed

        private void clawResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clawResetButtonActionPerformed
            clawspeedSlider.setValue(25);
        	// TODO add your handling code here:
        }//GEN-LAST:event_clawResetButtonActionPerformed

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
			fwrBtn.doClick();
			moveForward();
			break;
		case KeyEvent.VK_DOWN:
			BckwBtn.doClick();
			moveBackward();
			break;
		case KeyEvent.VK_LEFT:
			leftBtn.doClick();
			moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			rightBtn.doClick();
			moveRight();
			break;
		case KeyEvent.VK_SPACE:
			clawCloseBtn.doClick();
			closeClaw();
			break;
               case KeyEvent.VK_CONTROL:
            	clawOpenBtn.doClick();   
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
    private javax.swing.JLabel clawCloseLabel;
    private javax.swing.JButton clawOpenBtn;
    private javax.swing.JLabel clawOpenLabel;
    private javax.swing.JButton clawResetButton;
    private javax.swing.JLabel clawValueLabel;
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
    private javax.swing.JButton speedResetButton;
    private javax.swing.JSlider speedSlider;
    private javax.swing.JLabel speedValueLabel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel statusPtyLabel;
    private javax.swing.JButton stopBtn;
    private javax.swing.JButton turnResetButton;
    private javax.swing.JLabel turnValueLabel;
    private javax.swing.JSlider turnspeedSlider;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
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
