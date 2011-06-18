package gui.processing;

import gui.FramePlaceHolder;

import java.awt.Component;

import javax.swing.JSlider;
import javax.swing.JSpinner;

import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.IImageProcessor;
import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.Thresholds;

/**
 *
 * @author Morten Hulvej
 */
public class ImageThresholdsFrame extends javax.swing.JInternalFrame {

    /** Creates new form NewJInternalFrame */
    public ImageThresholdsFrame() {
        initComponents();
        FramePlaceHolder.setImgThresholdFrame(this);
        
        initCommonObjectListeners();
        initRobotColorListeners();
        initThresholdValues();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        thresholdPanel = new javax.swing.JPanel();
        commonPanel = new javax.swing.JPanel();
        obsMinRed = new javax.swing.JSpinner();
        obsMinGreen = new javax.swing.JSpinner();
        obsMinBlue = new javax.swing.JSpinner();
        obsMaxRed = new javax.swing.JSpinner();
        obsMaxGreen = new javax.swing.JSpinner();
        obsMaxBlue = new javax.swing.JSpinner();
        obsCanvas = new java.awt.Canvas();
        cakeMinRed = new javax.swing.JSpinner();
        cakeMinGreen = new javax.swing.JSpinner();
        cakeMinBlue = new javax.swing.JSpinner();
        cakeMaxRed = new javax.swing.JSpinner();
        cakeMaxGreen = new javax.swing.JSpinner();
        cakeMaxBlue = new javax.swing.JSpinner();
        cakeCanvas = new java.awt.Canvas();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        bufferSlider = new javax.swing.JSlider();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        robot1Panel = new javax.swing.JPanel();
        robot1Tabs = new javax.swing.JTabbedPane();
        r1FrontTab = new javax.swing.JPanel();
        r1MinRed = new javax.swing.JSpinner();
        r1MinGreen = new javax.swing.JSpinner();
        r1MinBlue = new javax.swing.JSpinner();
        r1MaxRed = new javax.swing.JSpinner();
        r1MaxGreen = new javax.swing.JSpinner();
        r1MaxBlue = new javax.swing.JSpinner();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        r1BackTab = new javax.swing.JPanel();
        r1MinRed1 = new javax.swing.JSpinner();
        r1MinGreen1 = new javax.swing.JSpinner();
        r1MinBlue1 = new javax.swing.JSpinner();
        r1MaxRed1 = new javax.swing.JSpinner();
        r1MaxGreen1 = new javax.swing.JSpinner();
        r1MaxBlue1 = new javax.swing.JSpinner();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        robot1Canvas = new java.awt.Canvas();
        robot2Panel = new javax.swing.JPanel();
        robot2Tabs = new javax.swing.JTabbedPane();
        r2FrontTab = new javax.swing.JPanel();
        r2MinRed = new javax.swing.JSpinner();
        r2MinGreen = new javax.swing.JSpinner();
        r2MinBlue = new javax.swing.JSpinner();
        r2MaxRed = new javax.swing.JSpinner();
        r2MaxGreen = new javax.swing.JSpinner();
        r2MaxBlue = new javax.swing.JSpinner();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        r2BackTab = new javax.swing.JPanel();
        r2MinRed1 = new javax.swing.JSpinner();
        r2MinGreen1 = new javax.swing.JSpinner();
        r2MinBlue1 = new javax.swing.JSpinner();
        r2MaxRed1 = new javax.swing.JSpinner();
        r2MaxGreen1 = new javax.swing.JSpinner();
        r2MaxBlue1 = new javax.swing.JSpinner();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        robot2Canvas = new java.awt.Canvas();
        jSeparator2 = new javax.swing.JSeparator();

        setClosable(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        thresholdPanel.setLayout(new java.awt.GridBagLayout());

        commonPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Common"));
        commonPanel.setLayout(new java.awt.GridBagLayout());

        obsMinRed.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        commonPanel.add(obsMinRed, gridBagConstraints);

        obsMinGreen.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        commonPanel.add(obsMinGreen, gridBagConstraints);

        obsMinBlue.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        commonPanel.add(obsMinBlue, gridBagConstraints);

        obsMaxRed.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        commonPanel.add(obsMaxRed, gridBagConstraints);

        obsMaxGreen.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        commonPanel.add(obsMaxGreen, gridBagConstraints);

        obsMaxBlue.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        commonPanel.add(obsMaxBlue, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        commonPanel.add(obsCanvas, gridBagConstraints);

        cakeMinRed.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        commonPanel.add(cakeMinRed, gridBagConstraints);

        cakeMinGreen.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        commonPanel.add(cakeMinGreen, gridBagConstraints);

        cakeMinBlue.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        commonPanel.add(cakeMinBlue, gridBagConstraints);

        cakeMaxRed.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        commonPanel.add(cakeMaxRed, gridBagConstraints);

        cakeMaxGreen.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        commonPanel.add(cakeMaxGreen, gridBagConstraints);

        cakeMaxBlue.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        commonPanel.add(cakeMaxBlue, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        commonPanel.add(cakeCanvas, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Obstacles");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        commonPanel.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Cakes");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        commonPanel.add(jLabel4, gridBagConstraints);

        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("min");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        commonPanel.add(jLabel5, gridBagConstraints);

        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("max");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        commonPanel.add(jLabel6, gridBagConstraints);

        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("min");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        commonPanel.add(jLabel7, gridBagConstraints);

        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("max");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        commonPanel.add(jLabel8, gridBagConstraints);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        commonPanel.add(jSeparator3, gridBagConstraints);

        bufferSlider.setMajorTickSpacing(10);
        bufferSlider.setMaximum(50);
        bufferSlider.setMinorTickSpacing(2);
        bufferSlider.setPaintTicks(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        commonPanel.add(bufferSlider, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 12));
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("RED");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 15);
        commonPanel.add(jLabel13, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 12));
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("GREEN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 15);
        commonPanel.add(jLabel14, gridBagConstraints);

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 12));
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("BLUE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 15);
        commonPanel.add(jLabel15, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setText("BufferZone");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        commonPanel.add(jLabel9, gridBagConstraints);

        jLabel10.setBackground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("\t\t\t");
        jLabel10.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        commonPanel.add(jLabel10, gridBagConstraints);

        jLabel11.setBackground(new java.awt.Color(0, 255, 0));
        jLabel11.setText("\t\t\t");
        jLabel11.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        commonPanel.add(jLabel11, gridBagConstraints);

        jLabel12.setBackground(new java.awt.Color(0, 0, 255));
        jLabel12.setText("\t\t\t");
        jLabel12.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        commonPanel.add(jLabel12, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, bufferSlider, org.jdesktop.beansbinding.ELProperty.create("${value}"), jLabel16, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        commonPanel.add(jLabel16, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        thresholdPanel.add(commonPanel, gridBagConstraints);

        robot1Panel.setBorder(javax.swing.BorderFactory.createTitledBorder("B.E.R.T.A."));
        robot1Panel.setLayout(new java.awt.GridBagLayout());

        r1FrontTab.setLayout(new java.awt.GridBagLayout());

        r1MinRed.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        r1FrontTab.add(r1MinRed, gridBagConstraints);

        r1MinGreen.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        r1FrontTab.add(r1MinGreen, gridBagConstraints);

        r1MinBlue.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        r1FrontTab.add(r1MinBlue, gridBagConstraints);

        r1MaxRed.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        r1FrontTab.add(r1MaxRed, gridBagConstraints);

        r1MaxGreen.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        r1FrontTab.add(r1MaxGreen, gridBagConstraints);

        r1MaxBlue.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        r1FrontTab.add(r1MaxBlue, gridBagConstraints);

        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("min");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        r1FrontTab.add(jLabel23, gridBagConstraints);

        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setText("max");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        r1FrontTab.add(jLabel24, gridBagConstraints);

        jLabel20.setFont(new java.awt.Font("Century Gothic", 1, 12));
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("RED");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 15);
        r1FrontTab.add(jLabel20, gridBagConstraints);

        jLabel21.setFont(new java.awt.Font("Century Gothic", 1, 12));
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("GREEN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 15);
        r1FrontTab.add(jLabel21, gridBagConstraints);

        jLabel22.setFont(new java.awt.Font("Century Gothic", 1, 12));
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("BLUE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 15);
        r1FrontTab.add(jLabel22, gridBagConstraints);

        jLabel17.setBackground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("\t\t\t");
        jLabel17.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        r1FrontTab.add(jLabel17, gridBagConstraints);

        jLabel18.setBackground(new java.awt.Color(0, 255, 0));
        jLabel18.setText("\t\t\t");
        jLabel18.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        r1FrontTab.add(jLabel18, gridBagConstraints);

        jLabel19.setBackground(new java.awt.Color(0, 0, 255));
        jLabel19.setText("\t\t\t");
        jLabel19.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        r1FrontTab.add(jLabel19, gridBagConstraints);

        robot1Tabs.addTab("Front", r1FrontTab);

        r1BackTab.setLayout(new java.awt.GridBagLayout());

        r1MinRed1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        r1BackTab.add(r1MinRed1, gridBagConstraints);

        r1MinGreen1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        r1BackTab.add(r1MinGreen1, gridBagConstraints);

        r1MinBlue1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        r1BackTab.add(r1MinBlue1, gridBagConstraints);

        r1MaxRed1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        r1BackTab.add(r1MaxRed1, gridBagConstraints);

        r1MaxGreen1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        r1BackTab.add(r1MaxGreen1, gridBagConstraints);

        r1MaxBlue1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        r1BackTab.add(r1MaxBlue1, gridBagConstraints);

        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText("min");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        r1BackTab.add(jLabel25, gridBagConstraints);

        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("max");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        r1BackTab.add(jLabel26, gridBagConstraints);

        jLabel27.setFont(new java.awt.Font("Century Gothic", 1, 12));
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("RED");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 15);
        r1BackTab.add(jLabel27, gridBagConstraints);

        jLabel28.setFont(new java.awt.Font("Century Gothic", 1, 12));
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("GREEN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 15);
        r1BackTab.add(jLabel28, gridBagConstraints);

        jLabel29.setFont(new java.awt.Font("Century Gothic", 1, 12));
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("BLUE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 15);
        r1BackTab.add(jLabel29, gridBagConstraints);

        jLabel30.setBackground(new java.awt.Color(255, 0, 0));
        jLabel30.setText("\t\t\t");
        jLabel30.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        r1BackTab.add(jLabel30, gridBagConstraints);

        jLabel31.setBackground(new java.awt.Color(0, 255, 0));
        jLabel31.setText("\t\t\t");
        jLabel31.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        r1BackTab.add(jLabel31, gridBagConstraints);

        jLabel32.setBackground(new java.awt.Color(0, 0, 255));
        jLabel32.setText("\t\t\t");
        jLabel32.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        r1BackTab.add(jLabel32, gridBagConstraints);

        robot1Tabs.addTab("Back", r1BackTab);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        robot1Panel.add(robot1Tabs, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        robot1Panel.add(robot1Canvas, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        thresholdPanel.add(robot1Panel, gridBagConstraints);

        robot2Panel.setBorder(javax.swing.BorderFactory.createTitledBorder("P.R.O.P."));
        robot2Panel.setLayout(new java.awt.GridBagLayout());

        r2FrontTab.setLayout(new java.awt.GridBagLayout());

        r2MinRed.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        r2FrontTab.add(r2MinRed, gridBagConstraints);

        r2MinGreen.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        r2FrontTab.add(r2MinGreen, gridBagConstraints);

        r2MinBlue.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        r2FrontTab.add(r2MinBlue, gridBagConstraints);

        r2MaxRed.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        r2FrontTab.add(r2MaxRed, gridBagConstraints);

        r2MaxGreen.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        r2FrontTab.add(r2MaxGreen, gridBagConstraints);

        r2MaxBlue.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        r2FrontTab.add(r2MaxBlue, gridBagConstraints);

        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setText("min");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        r2FrontTab.add(jLabel33, gridBagConstraints);

        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setText("max");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        r2FrontTab.add(jLabel34, gridBagConstraints);

        jLabel35.setFont(new java.awt.Font("Century Gothic", 1, 12));
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("RED");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 15);
        r2FrontTab.add(jLabel35, gridBagConstraints);

        jLabel36.setFont(new java.awt.Font("Century Gothic", 1, 12));
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("GREEN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 15);
        r2FrontTab.add(jLabel36, gridBagConstraints);

        jLabel37.setFont(new java.awt.Font("Century Gothic", 1, 12));
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("BLUE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 15);
        r2FrontTab.add(jLabel37, gridBagConstraints);

        jLabel38.setBackground(new java.awt.Color(255, 0, 0));
        jLabel38.setText("\t\t\t");
        jLabel38.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        r2FrontTab.add(jLabel38, gridBagConstraints);

        jLabel39.setBackground(new java.awt.Color(0, 255, 0));
        jLabel39.setText("\t\t\t");
        jLabel39.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        r2FrontTab.add(jLabel39, gridBagConstraints);

        jLabel40.setBackground(new java.awt.Color(0, 0, 255));
        jLabel40.setText("\t\t\t");
        jLabel40.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        r2FrontTab.add(jLabel40, gridBagConstraints);

        robot2Tabs.addTab("Front", r2FrontTab);

        r2BackTab.setLayout(new java.awt.GridBagLayout());

        r2MinRed1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        r2BackTab.add(r2MinRed1, gridBagConstraints);

        r2MinGreen1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        r2BackTab.add(r2MinGreen1, gridBagConstraints);

        r2MinBlue1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        r2BackTab.add(r2MinBlue1, gridBagConstraints);

        r2MaxRed1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        r2BackTab.add(r2MaxRed1, gridBagConstraints);

        r2MaxGreen1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        r2BackTab.add(r2MaxGreen1, gridBagConstraints);

        r2MaxBlue1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        r2BackTab.add(r2MaxBlue1, gridBagConstraints);

        jLabel41.setForeground(new java.awt.Color(51, 51, 51));
        jLabel41.setText("min");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        r2BackTab.add(jLabel41, gridBagConstraints);

        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setText("max");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        r2BackTab.add(jLabel42, gridBagConstraints);

        jLabel43.setFont(new java.awt.Font("Century Gothic", 1, 12));
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("RED");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 15);
        r2BackTab.add(jLabel43, gridBagConstraints);

        jLabel44.setFont(new java.awt.Font("Century Gothic", 1, 12));
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("GREEN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 15);
        r2BackTab.add(jLabel44, gridBagConstraints);

        jLabel45.setFont(new java.awt.Font("Century Gothic", 1, 12));
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("BLUE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 15);
        r2BackTab.add(jLabel45, gridBagConstraints);

        jLabel46.setBackground(new java.awt.Color(255, 0, 0));
        jLabel46.setText("\t\t\t");
        jLabel46.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        r2BackTab.add(jLabel46, gridBagConstraints);

        jLabel47.setBackground(new java.awt.Color(0, 255, 0));
        jLabel47.setText("\t\t\t");
        jLabel47.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        r2BackTab.add(jLabel47, gridBagConstraints);

        jLabel48.setBackground(new java.awt.Color(0, 0, 255));
        jLabel48.setText("\t\t\t");
        jLabel48.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        r2BackTab.add(jLabel48, gridBagConstraints);

        robot2Tabs.addTab("Back", r2BackTab);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        robot2Panel.add(robot2Tabs, gridBagConstraints);

        robot2Canvas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        robot2Panel.add(robot2Canvas, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        thresholdPanel.add(robot2Panel, gridBagConstraints);

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 16, 0);
        thresholdPanel.add(jSeparator2, gridBagConstraints);

        getContentPane().add(thresholdPanel, new java.awt.GridBagConstraints());

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider bufferSlider;
    private java.awt.Canvas cakeCanvas;
    private javax.swing.JSpinner cakeMaxBlue;
    private javax.swing.JSpinner cakeMaxGreen;
    private javax.swing.JSpinner cakeMaxRed;
    private javax.swing.JSpinner cakeMinBlue;
    private javax.swing.JSpinner cakeMinGreen;
    private javax.swing.JSpinner cakeMinRed;
    private javax.swing.JPanel commonPanel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private java.awt.Canvas obsCanvas;
    private javax.swing.JSpinner obsMaxBlue;
    private javax.swing.JSpinner obsMaxGreen;
    private javax.swing.JSpinner obsMaxRed;
    private javax.swing.JSpinner obsMinBlue;
    private javax.swing.JSpinner obsMinGreen;
    private javax.swing.JSpinner obsMinRed;
    private javax.swing.JPanel r1BackTab;
    private javax.swing.JPanel r1FrontTab;
    javax.swing.JSpinner r1MaxBlue;
    javax.swing.JSpinner r1MaxBlue1;
    javax.swing.JSpinner r2MaxBlue;
    javax.swing.JSpinner r2MaxBlue1;
    javax.swing.JSpinner r1MaxGreen;
    javax.swing.JSpinner r1MaxGreen1;
    javax.swing.JSpinner r2MaxGreen;
    javax.swing.JSpinner r2MaxGreen1;
    javax.swing.JSpinner r1MaxRed;
    javax.swing.JSpinner r1MaxRed1;
    javax.swing.JSpinner r2MaxRed;
    javax.swing.JSpinner r2MaxRed1;
    javax.swing.JSpinner r1MinBlue;
    javax.swing.JSpinner r1MinBlue1;
    javax.swing.JSpinner r2MinBlue;
    javax.swing.JSpinner r2MinBlue1;
    javax.swing.JSpinner r1MinGreen;
    javax.swing.JSpinner r1MinGreen1;
    javax.swing.JSpinner r2MinGreen;
    javax.swing.JSpinner r2MinGreen1;
    javax.swing.JSpinner r1MinRed;
    javax.swing.JSpinner r1MinRed1;
    javax.swing.JSpinner r2MinRed;
    javax.swing.JSpinner r2MinRed1;
    private javax.swing.JPanel r2BackTab;
    private javax.swing.JPanel r2FrontTab;
    private java.awt.Canvas robot1Canvas;
    private javax.swing.JPanel robot1Panel;
    private javax.swing.JTabbedPane robot1Tabs;
    private java.awt.Canvas robot2Canvas;
    private javax.swing.JPanel robot2Panel;
    private javax.swing.JTabbedPane robot2Tabs;
    private javax.swing.JPanel thresholdPanel;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
	
    // custom variables
    private RobotColorChangeListener robotChangeListener = new RobotColorChangeListener();
    private CommonObjectColorChangeListener commonChangeListener = new CommonObjectColorChangeListener();
    
    /**
     * Setting spinners in GUI to default values
     */
    public void initThresholdValues()
	{
    	
    	//COMMON
    	//Cakes
    	Thresholds cakes = IImageProcessor.CAKE_THRESHOLDS;
    	
    	cakeMinRed.setValue(cakes.getMinR());
    	cakeMinGreen.setValue(cakes.getMinG());
    	cakeMinBlue.setValue(cakes.getMinB());
    	
    	cakeMaxRed.setValue(cakes.getMaxR());
    	cakeMaxGreen.setValue(cakes.getMaxG());
    	cakeMaxBlue.setValue(cakes.getMaxB());
    	
    	//Obstacles
    	//TODO: Lav det
    	Thresholds obs = IImageProcessor.OBSTACLE_THRESHOLDS;
    	
    	obsMinRed.setValue(obs.getMinR());
    	obsMinGreen.setValue(obs.getMinR());
    	obsMinBlue.setValue(obs.getMinB());
    	
    	obsMaxRed.setValue(obs.getMaxR());
    	obsMaxGreen.setValue(obs.getMaxG());
    	obsMaxBlue.setValue(obs.getMaxB());
    	
    	// Robots
    	// robot 1
    	Thresholds robot1N = IImageProcessor.ROBOT1_N_THRESHOLDS;
    	Thresholds robot1S = IImageProcessor.ROBOT1_S_THRESHOLDS;
    	
    		// front (green)
	    	r1MinRed.setValue(robot1N.getMinR());
	    	r1MinGreen.setValue(robot1N.getMinG()); 
	    	r1MinBlue.setValue(robot1N.getMinB());
	
	    	r1MaxRed.setValue(robot1N.getMaxR()); 
	    	r1MaxGreen.setValue(robot1N.getMaxG()); 
	    	r1MaxBlue.setValue(robot1N.getMaxB());
    	
    		// back (blue)
	    	r1MinRed1.setValue(robot1S.getMinR());
	    	r1MinGreen1.setValue(robot1S.getMinG());
	    	r1MinBlue1.setValue(robot1S.getMinB());
	    	
	    	r1MaxRed1.setValue(robot1S.getMaxR());
	    	r1MaxGreen1.setValue(robot1S.getMaxG()); 
	    	r1MaxBlue1.setValue(robot1S.getMaxB());

    	// robot 2
	    Thresholds robot2N = IImageProcessor.ROBOT2_N_THRESHOLDS;
	    Thresholds robot2S = IImageProcessor.ROBOT2_N_THRESHOLDS;
	    
	    	// front (orange)
	    	r2MinRed.setValue(robot2N.getMinR());
	    	r2MinGreen.setValue(robot2N.getMinG()); 
	    	r2MinBlue.setValue(robot2N.getMinB()); 
	    	
	    	r2MaxRed.setValue(robot2N.getMaxR());
	    	r2MaxGreen.setValue(robot2N.getMaxG()); 
	    	r2MaxBlue.setValue(robot2N.getMaxB());
	    	
	    	// back (yellow)
	    	r2MinRed1.setValue(robot2S.getMinR());
	    	r2MinGreen1.setValue(robot2S.getMinG());
	    	r2MinBlue1.setValue(robot2S.getMinB()); 
	    	
	    	r2MaxRed1.setValue(robot2S.getMaxR());
	    	r2MaxGreen1.setValue(robot2S.getMaxG());
	    	r2MaxBlue1.setValue(robot2S.getMaxB());
    	
    	 
	}
    
    public void initCommonObjectListeners()
	{
    	for (Component c : getCommonPanel().getComponents())
		{
			if (c instanceof JSpinner)
			{
				JSpinner spinner = (JSpinner) c;
				
				spinner.addChangeListener(commonChangeListener);
			}
			else if (c instanceof JSlider)
			{
				JSlider slider = (JSlider) c;
				slider.addChangeListener(commonChangeListener);
			}
		}
	}
    public void initRobotColorListeners()
	{
    	for (Component c : getR1FrontTab().getComponents())
		{
			if (c instanceof JSpinner)
			{
				JSpinner spinner = (JSpinner) c;
				spinner.addChangeListener(robotChangeListener);
			}
		}
    	for (Component c : getR1BackTab().getComponents())
		{
			if (c instanceof JSpinner)
			{
				JSpinner spinner = (JSpinner) c;
				spinner.addChangeListener(robotChangeListener);
			}
		}
    	for (Component c : getR2FrontTab().getComponents())
		{
			if (c instanceof JSpinner)
			{
				JSpinner spinner = (JSpinner) c;
				spinner.addChangeListener(robotChangeListener);
			}
		}
    	for (Component c : getR2BackTab().getComponents())
		{
			if (c instanceof JSpinner)
			{
				JSpinner spinner = (JSpinner) c;
				spinner.addChangeListener(robotChangeListener);
			}
		}
	}
    
    public javax.swing.JSlider getBufferSlider()
	{
		return bufferSlider;
	}

	public java.awt.Canvas getCakeCanvas()
	{
		return cakeCanvas;
	}

	public javax.swing.JSpinner getCakeMaxBlue()
	{
		return cakeMaxBlue;
	}

	public javax.swing.JSpinner getCakeMaxGreen()
	{
		return cakeMaxGreen;
	}

	public javax.swing.JSpinner getCakeMaxRed()
	{
		return cakeMaxRed;
	}

	public javax.swing.JSpinner getCakeMinBlue()
	{
		return cakeMinBlue;
	}

	public javax.swing.JSpinner getCakeMinGreen()
	{
		return cakeMinGreen;
	}

	public javax.swing.JSpinner getCakeMinRed()
	{
		return cakeMinRed;
	}

	public javax.swing.JPanel getCommonPanel()
	{
		return commonPanel;
	}

	public java.awt.Canvas getObsCanvas()
	{
		return obsCanvas;
	}

	public javax.swing.JSpinner getObsMaxBlue()
	{
		return obsMaxBlue;
	}

	public javax.swing.JSpinner getObsMaxGreen()
	{
		return obsMaxGreen;
	}

	public javax.swing.JSpinner getObsMaxRed()
	{
		return obsMaxRed;
	}

	public javax.swing.JSpinner getObsMinBlue()
	{
		return obsMinBlue;
	}

	public javax.swing.JSpinner getObsMinGreen()
	{
		return obsMinGreen;
	}

	public javax.swing.JSpinner getObsMinRed()
	{
		return obsMinRed;
	}

	public javax.swing.JPanel getR1BackTab()
	{
		return r1BackTab;
	}

	public javax.swing.JPanel getR1FrontTab()
	{
		return r1FrontTab;
	}

	public javax.swing.JSpinner getR1MaxBlue()
	{
		return r1MaxBlue;
	}

	public javax.swing.JSpinner getR1MaxBlue1()
	{
		return r1MaxBlue1;
	}

	public javax.swing.JSpinner getR1MaxBlue2()
	{
		return r2MaxBlue;
	}

	public javax.swing.JSpinner getR1MaxBlue3()
	{
		return r2MaxBlue1;
	}

	public javax.swing.JSpinner getR1MaxGreen()
	{
		return r1MaxGreen;
	}

	public javax.swing.JSpinner getR1MaxGreen1()
	{
		return r1MaxGreen1;
	}

	public javax.swing.JSpinner getR1MaxGreen2()
	{
		return r2MaxGreen;
	}

	public javax.swing.JSpinner getR1MaxGreen3()
	{
		return r2MaxGreen1;
	}

	public javax.swing.JSpinner getR1MaxRed()
	{
		return r1MaxRed;
	}

	public javax.swing.JSpinner getR1MaxRed1()
	{
		return r1MaxRed1;
	}

	public javax.swing.JSpinner getR1MaxRed2()
	{
		return r2MaxRed;
	}

	public javax.swing.JSpinner getR1MaxRed3()
	{
		return r2MaxRed1;
	}

	public javax.swing.JSpinner getR1MinBlue()
	{
		return r1MinBlue;
	}

	public javax.swing.JSpinner getR1MinBlue1()
	{
		return r1MinBlue1;
	}

	public javax.swing.JSpinner getR1MinBlue2()
	{
		return r2MinBlue;
	}

	public javax.swing.JSpinner getR1MinBlue3()
	{
		return r2MinBlue1;
	}

	public javax.swing.JSpinner getR1MinGreen()
	{
		return r1MinGreen;
	}

	public javax.swing.JSpinner getR1MinGreen1()
	{
		return r1MinGreen1;
	}

	public javax.swing.JSpinner getR1MinGreen2()
	{
		return r2MinGreen;
	}

	public javax.swing.JSpinner getR1MinGreen3()
	{
		return r2MinGreen1;
	}

	public javax.swing.JSpinner getR1MinRed()
	{
		return r1MinRed;
	}

	public javax.swing.JSpinner getR1MinRed1()
	{
		return r1MinRed1;
	}

	public javax.swing.JSpinner getR1MinRed2()
	{
		return r2MinRed;
	}

	public javax.swing.JSpinner getR1MinRed3()
	{
		return r2MinRed1;
	}

	public javax.swing.JPanel getR2BackTab()
	{
		return r2BackTab;
	}

	public javax.swing.JPanel getR2FrontTab()
	{
		return r2FrontTab;
	}

	public java.awt.Canvas getRobot1Canvas()
	{
		return robot1Canvas;
	}

	public javax.swing.JPanel getRobot1Panel()
	{
		return robot1Panel;
	}

	public javax.swing.JTabbedPane getRobot1Tabs()
	{
		return robot1Tabs;
	}

	public java.awt.Canvas getRobot2Canvas()
	{
		return robot2Canvas;
	}

	public javax.swing.JPanel getRobot2Panel()
	{
		return robot2Panel;
	}

	public javax.swing.JTabbedPane getRobot2Tabs()
	{
		return robot2Tabs;
	}

	public javax.swing.JPanel getThresholdPanel()
	{
		return thresholdPanel;
	}

}
