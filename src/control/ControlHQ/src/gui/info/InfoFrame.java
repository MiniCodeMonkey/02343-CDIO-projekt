package gui.info;

import controller.MainController;

/**
 *
 * @author Terkel
 */
public class InfoFrame extends javax.swing.JInternalFrame {

    /** Creates new form InfoFrame2 */
    public InfoFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Cousre = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        obstacleCount_Lbl = new javax.swing.JLabel();
        cake_Count_Lbl = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        Tile_X_Count_label = new javax.swing.JLabel();
        Tile_Y_Count_Label = new javax.swing.JLabel();
        Berta_info = new javax.swing.JTabbedPane();
        Berta_Overview_panel = new javax.swing.JPanel();
        Berta_Battery_panel = new javax.swing.JPanel();
        Berta_BatteryLevelBar = new javax.swing.JProgressBar();
        Berta_State_panel = new javax.swing.JPanel();
        Berta_State_label = new javax.swing.JLabel();
        Berta_Speed_panel = new javax.swing.JPanel();
        Berta_Speed_Lbl = new javax.swing.JLabel();
        Berta_CakeID_panel = new javax.swing.JPanel();
        Berta_CakeID_Lbl = new javax.swing.JLabel();
        Berta_Pos_panel2 = new javax.swing.JPanel();
        Berta_Pos_Lbl2 = new javax.swing.JLabel();
        Berta_Mortor_panel = new javax.swing.JPanel();
        Berta_TacoCount_panel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Berta_rWheel_Lbl = new javax.swing.JLabel();
        Berta_lWheel_Lbl = new javax.swing.JLabel();
        Berta_Claw_Lbl = new javax.swing.JLabel();
        Berta_Dist_panel = new javax.swing.JPanel();
        Berta_Dist_Lbl = new javax.swing.JLabel();
        Berta_WheelDia_panel = new javax.swing.JPanel();
        Berta_WheelDia_Lbl = new javax.swing.JLabel();
        Berta_Pos_panel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Berta_Pos_lbl = new javax.swing.JLabel();
        Berta_Angle_panel = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        Berta_SupAngle_lbl = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel39 = new javax.swing.JLabel();
        Berta_ActualAngle_lbl = new javax.swing.JLabel();
        Berta_TargetLoc_panel = new javax.swing.JPanel();
        Berta_TargetLoc_Lbl = new javax.swing.JLabel();
        Prop_info = new javax.swing.JTabbedPane();
        Prop_Overview_panel = new javax.swing.JPanel();
        Prop_Battery_panel = new javax.swing.JPanel();
        Prop_BatteryLevelBar = new javax.swing.JProgressBar();
        Prop_State_panel = new javax.swing.JPanel();
        Prop_State_label = new javax.swing.JLabel();
        Prop_Speed_panel = new javax.swing.JPanel();
        Prop_Speed_Lbl = new javax.swing.JLabel();
        Prop_CakeID_panel = new javax.swing.JPanel();
        Prop_CakeID_Lbl = new javax.swing.JLabel();
        Prop_Pos_panel2 = new javax.swing.JPanel();
        Prop_Pos_Lbl2 = new javax.swing.JLabel();
        Prop_Mortor_panel = new javax.swing.JPanel();
        Prop_TacoCount_panel = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        Prop_rWheel_Lbl = new javax.swing.JLabel();
        Prop_lWheel_Lbl = new javax.swing.JLabel();
        Prop_Claw_Lbl = new javax.swing.JLabel();
        Prop_Dist_panel = new javax.swing.JPanel();
        Prop_Dist_Lbl = new javax.swing.JLabel();
        Prop_WheelDia_panel = new javax.swing.JPanel();
        Prop_WheelDia_Lbl = new javax.swing.JLabel();
        Prop_Pos_panel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        Prop_Pos_lbl = new javax.swing.JLabel();
        Prop_Angle_panel = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        Prop_SupAngle_lbl = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel40 = new javax.swing.JLabel();
        Prop_ActualAngle_lbl = new javax.swing.JLabel();
        Prop_TargetLoc_panel = new javax.swing.JPanel();
        Prop_TargetLoc_Lbl = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setNormalBounds(new java.awt.Rectangle(440, 550, 820, 321));

        Cousre.setBorder(javax.swing.BorderFactory.createTitledBorder("Course Info"));

        jLabel3.setText("Number of obstacles:");

        obstacleCount_Lbl.setText("12");

        cake_Count_Lbl.setText("23");

        jLabel10.setText("Number of Cakes:");

        jLabel15.setText("nr X-tiles:");

        jLabel16.setText("nr Y-tiles:");

        Tile_X_Count_label.setText("XX");

        Tile_Y_Count_Label.setText("YY");

        javax.swing.GroupLayout CousreLayout = new javax.swing.GroupLayout(Cousre);
        Cousre.setLayout(CousreLayout);
        CousreLayout.setHorizontalGroup(
            CousreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CousreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CousreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, CousreLayout.createSequentialGroup()
                        .addGroup(CousreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(CousreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cake_Count_Lbl, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(obstacleCount_Lbl, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(CousreLayout.createSequentialGroup()
                        .addGroup(CousreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CousreLayout.createSequentialGroup()
                                .addGroup(CousreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16))
                                .addGap(62, 62, 62))
                            .addComponent(jSeparator2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(CousreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Tile_X_Count_label)
                            .addComponent(Tile_Y_Count_Label))))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        CousreLayout.setVerticalGroup(
            CousreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CousreLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CousreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CousreLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addGap(8, 8, 8)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CousreLayout.createSequentialGroup()
                        .addComponent(obstacleCount_Lbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cake_Count_Lbl)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CousreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(Tile_X_Count_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CousreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(Tile_Y_Count_Label)))
        );

        Berta_info.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "B.E.R.T.A.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        Berta_Battery_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Battery"));

        Berta_BatteryLevelBar.setOrientation(1);
        Berta_BatteryLevelBar.setToolTipText("Batteri-niveau");
        Berta_BatteryLevelBar.setFocusable(false);

        javax.swing.GroupLayout Berta_Battery_panelLayout = new javax.swing.GroupLayout(Berta_Battery_panel);
        Berta_Battery_panel.setLayout(Berta_Battery_panelLayout);
        Berta_Battery_panelLayout.setHorizontalGroup(
            Berta_Battery_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Berta_Battery_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Berta_BatteryLevelBar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Berta_Battery_panelLayout.setVerticalGroup(
            Berta_Battery_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Berta_Battery_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Berta_BatteryLevelBar, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
        );

        Berta_State_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("State"));

        Berta_State_label.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        Berta_State_label.setText("RUNNING LIKE HELL");
        Berta_State_label.setToolTipText("Hvilken state er B.E.R.T.A. i. I.E. Hvad laver hun");
        Berta_State_label.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout Berta_State_panelLayout = new javax.swing.GroupLayout(Berta_State_panel);
        Berta_State_panel.setLayout(Berta_State_panelLayout);
        Berta_State_panelLayout.setHorizontalGroup(
            Berta_State_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Berta_State_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Berta_State_label, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        Berta_State_panelLayout.setVerticalGroup(
            Berta_State_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Berta_State_panelLayout.createSequentialGroup()
                .addComponent(Berta_State_label)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        Berta_Speed_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Speed"));

        Berta_Speed_Lbl.setFont(new java.awt.Font("Verdana", 1, 11));
        Berta_Speed_Lbl.setText("100 m/s");

        javax.swing.GroupLayout Berta_Speed_panelLayout = new javax.swing.GroupLayout(Berta_Speed_panel);
        Berta_Speed_panel.setLayout(Berta_Speed_panelLayout);
        Berta_Speed_panelLayout.setHorizontalGroup(
            Berta_Speed_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Berta_Speed_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Berta_Speed_Lbl)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Berta_Speed_panelLayout.setVerticalGroup(
            Berta_Speed_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Berta_Speed_Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        Berta_CakeID_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Cake ID"));

        Berta_CakeID_Lbl.setFont(new java.awt.Font("Verdana", 1, 11));
        Berta_CakeID_Lbl.setText("35");

        javax.swing.GroupLayout Berta_CakeID_panelLayout = new javax.swing.GroupLayout(Berta_CakeID_panel);
        Berta_CakeID_panel.setLayout(Berta_CakeID_panelLayout);
        Berta_CakeID_panelLayout.setHorizontalGroup(
            Berta_CakeID_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Berta_CakeID_panelLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(Berta_CakeID_Lbl)
                .addContainerGap())
        );
        Berta_CakeID_panelLayout.setVerticalGroup(
            Berta_CakeID_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Berta_CakeID_Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        Berta_Pos_panel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Position"));

        Berta_Pos_Lbl2.setFont(new java.awt.Font("Verdana", 0, 14));
        Berta_Pos_Lbl2.setText("(Y,X)");

        javax.swing.GroupLayout Berta_Pos_panel2Layout = new javax.swing.GroupLayout(Berta_Pos_panel2);
        Berta_Pos_panel2.setLayout(Berta_Pos_panel2Layout);
        Berta_Pos_panel2Layout.setHorizontalGroup(
            Berta_Pos_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Berta_Pos_panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Berta_Pos_Lbl2)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        Berta_Pos_panel2Layout.setVerticalGroup(
            Berta_Pos_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Berta_Pos_panel2Layout.createSequentialGroup()
                .addComponent(Berta_Pos_Lbl2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Berta_Overview_panelLayout = new javax.swing.GroupLayout(Berta_Overview_panel);
        Berta_Overview_panel.setLayout(Berta_Overview_panelLayout);
        Berta_Overview_panelLayout.setHorizontalGroup(
            Berta_Overview_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Berta_Overview_panelLayout.createSequentialGroup()
                .addGroup(Berta_Overview_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Berta_State_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Berta_Overview_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(Berta_Overview_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Berta_Speed_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Berta_CakeID_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Berta_Pos_panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Berta_Battery_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Berta_Overview_panelLayout.setVerticalGroup(
            Berta_Overview_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Berta_Overview_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Berta_Overview_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Berta_Battery_panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Berta_Overview_panelLayout.createSequentialGroup()
                        .addComponent(Berta_State_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Berta_Speed_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Berta_Overview_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Berta_CakeID_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Berta_Pos_panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Berta_info.addTab("Overview", Berta_Overview_panel);

        Berta_TacoCount_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Taco Count"));

        jLabel11.setText("Right Wheel:");

        jLabel12.setText("Left Wheel:");

        jLabel13.setText("Claw:");

        Berta_rWheel_Lbl.setText("1000");

        Berta_lWheel_Lbl.setText("1000");

        Berta_Claw_Lbl.setText("1000");

        javax.swing.GroupLayout Berta_TacoCount_panelLayout = new javax.swing.GroupLayout(Berta_TacoCount_panel);
        Berta_TacoCount_panel.setLayout(Berta_TacoCount_panelLayout);
        Berta_TacoCount_panelLayout.setHorizontalGroup(
            Berta_TacoCount_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Berta_TacoCount_panelLayout.createSequentialGroup()
                .addGroup(Berta_TacoCount_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Berta_TacoCount_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Berta_lWheel_Lbl, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Berta_rWheel_Lbl, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Berta_Claw_Lbl, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Berta_TacoCount_panelLayout.setVerticalGroup(
            Berta_TacoCount_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Berta_TacoCount_panelLayout.createSequentialGroup()
                .addGroup(Berta_TacoCount_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(Berta_rWheel_Lbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Berta_TacoCount_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(Berta_lWheel_Lbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Berta_TacoCount_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(Berta_Claw_Lbl)))
        );

        Berta_Dist_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Distance"));

        Berta_Dist_Lbl.setFont(new java.awt.Font("Verdana", 0, 24));
        Berta_Dist_Lbl.setText("50 m");

        javax.swing.GroupLayout Berta_Dist_panelLayout = new javax.swing.GroupLayout(Berta_Dist_panel);
        Berta_Dist_panel.setLayout(Berta_Dist_panelLayout);
        Berta_Dist_panelLayout.setHorizontalGroup(
            Berta_Dist_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Berta_Dist_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Berta_Dist_Lbl)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        Berta_Dist_panelLayout.setVerticalGroup(
            Berta_Dist_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Berta_Dist_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Berta_Dist_Lbl)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        Berta_WheelDia_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Wheel Diameter"));

        Berta_WheelDia_Lbl.setText("13 cm");

        javax.swing.GroupLayout Berta_WheelDia_panelLayout = new javax.swing.GroupLayout(Berta_WheelDia_panel);
        Berta_WheelDia_panel.setLayout(Berta_WheelDia_panelLayout);
        Berta_WheelDia_panelLayout.setHorizontalGroup(
            Berta_WheelDia_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Berta_WheelDia_panelLayout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(Berta_WheelDia_Lbl)
                .addContainerGap())
        );
        Berta_WheelDia_panelLayout.setVerticalGroup(
            Berta_WheelDia_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Berta_WheelDia_panelLayout.createSequentialGroup()
                .addComponent(Berta_WheelDia_Lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout Berta_Mortor_panelLayout = new javax.swing.GroupLayout(Berta_Mortor_panel);
        Berta_Mortor_panel.setLayout(Berta_Mortor_panelLayout);
        Berta_Mortor_panelLayout.setHorizontalGroup(
            Berta_Mortor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Berta_Mortor_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Berta_Mortor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Berta_WheelDia_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Berta_Mortor_panelLayout.createSequentialGroup()
                        .addComponent(Berta_TacoCount_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Berta_Dist_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        Berta_Mortor_panelLayout.setVerticalGroup(
            Berta_Mortor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Berta_Mortor_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Berta_Mortor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Berta_Dist_panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Berta_TacoCount_panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Berta_WheelDia_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        Berta_info.addTab("Motor", Berta_Mortor_panel);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Position"));

        Berta_Pos_lbl.setFont(new java.awt.Font("Verdana", 0, 24));
        Berta_Pos_lbl.setText("(Y,X)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Berta_Pos_lbl)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(Berta_Pos_lbl)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Berta_Angle_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Angel"));

        jLabel25.setText("Supposed:");

        Berta_SupAngle_lbl.setFont(new java.awt.Font("Tahoma", 1, 12));
        Berta_SupAngle_lbl.setText("34");

        jLabel39.setText("Actual:");

        Berta_ActualAngle_lbl.setFont(new java.awt.Font("Tahoma", 1, 12));
        Berta_ActualAngle_lbl.setText("45");

        javax.swing.GroupLayout Berta_Angle_panelLayout = new javax.swing.GroupLayout(Berta_Angle_panel);
        Berta_Angle_panel.setLayout(Berta_Angle_panelLayout);
        Berta_Angle_panelLayout.setHorizontalGroup(
            Berta_Angle_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Berta_Angle_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Berta_Angle_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Berta_Angle_panelLayout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Berta_ActualAngle_lbl))
                    .addGroup(Berta_Angle_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Berta_Angle_panelLayout.createSequentialGroup()
                            .addComponent(jLabel25)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Berta_SupAngle_lbl))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Berta_Angle_panelLayout.setVerticalGroup(
            Berta_Angle_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Berta_Angle_panelLayout.createSequentialGroup()
                .addGroup(Berta_Angle_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(Berta_SupAngle_lbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Berta_Angle_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(Berta_ActualAngle_lbl))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Berta_TargetLoc_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Target Location"));

        Berta_TargetLoc_Lbl.setFont(new java.awt.Font("Verdana", 0, 24));
        Berta_TargetLoc_Lbl.setText("(Y,X)");

        javax.swing.GroupLayout Berta_TargetLoc_panelLayout = new javax.swing.GroupLayout(Berta_TargetLoc_panel);
        Berta_TargetLoc_panel.setLayout(Berta_TargetLoc_panelLayout);
        Berta_TargetLoc_panelLayout.setHorizontalGroup(
            Berta_TargetLoc_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Berta_TargetLoc_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Berta_TargetLoc_Lbl)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Berta_TargetLoc_panelLayout.setVerticalGroup(
            Berta_TargetLoc_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Berta_TargetLoc_panelLayout.createSequentialGroup()
                .addComponent(Berta_TargetLoc_Lbl)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Berta_Pos_panelLayout = new javax.swing.GroupLayout(Berta_Pos_panel);
        Berta_Pos_panel.setLayout(Berta_Pos_panelLayout);
        Berta_Pos_panelLayout.setHorizontalGroup(
            Berta_Pos_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Berta_Pos_panelLayout.createSequentialGroup()
                .addGroup(Berta_Pos_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Berta_Pos_panelLayout.createSequentialGroup()
                        .addContainerGap(119, Short.MAX_VALUE)
                        .addComponent(Berta_Angle_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(92, 92, 92))
            .addGroup(Berta_Pos_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Berta_Pos_panelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Berta_TargetLoc_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(183, Short.MAX_VALUE)))
        );
        Berta_Pos_panelLayout.setVerticalGroup(
            Berta_Pos_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Berta_Pos_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Berta_Angle_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
            .addGroup(Berta_Pos_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Berta_Pos_panelLayout.createSequentialGroup()
                    .addContainerGap(107, Short.MAX_VALUE)
                    .addComponent(Berta_TargetLoc_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(48, 48, 48)))
        );

        Berta_info.addTab("Position", Berta_Pos_panel);

        Prop_info.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "P.R.O.P.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        Prop_Battery_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Battery"));

        Prop_BatteryLevelBar.setOrientation(1);
        Prop_BatteryLevelBar.setToolTipText("Batteri-niveau");
        Prop_BatteryLevelBar.setFocusable(false);

        javax.swing.GroupLayout Prop_Battery_panelLayout = new javax.swing.GroupLayout(Prop_Battery_panel);
        Prop_Battery_panel.setLayout(Prop_Battery_panelLayout);
        Prop_Battery_panelLayout.setHorizontalGroup(
            Prop_Battery_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Prop_Battery_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Prop_BatteryLevelBar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Prop_Battery_panelLayout.setVerticalGroup(
            Prop_Battery_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Prop_Battery_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Prop_BatteryLevelBar, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
        );

        Prop_State_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("State"));

        Prop_State_label.setFont(new java.awt.Font("Verdana", 1, 14));
        Prop_State_label.setText("RUNNING LIKE HELL");
        Prop_State_label.setToolTipText("Hvilken state er B.E.R.T.A. i. I.E. Hvad laver hun");
        Prop_State_label.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout Prop_State_panelLayout = new javax.swing.GroupLayout(Prop_State_panel);
        Prop_State_panel.setLayout(Prop_State_panelLayout);
        Prop_State_panelLayout.setHorizontalGroup(
            Prop_State_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Prop_State_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Prop_State_label, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        Prop_State_panelLayout.setVerticalGroup(
            Prop_State_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Prop_State_panelLayout.createSequentialGroup()
                .addComponent(Prop_State_label)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        Prop_Speed_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Speed"));

        Prop_Speed_Lbl.setFont(new java.awt.Font("Verdana", 1, 11));
        Prop_Speed_Lbl.setText("100 m/s");

        javax.swing.GroupLayout Prop_Speed_panelLayout = new javax.swing.GroupLayout(Prop_Speed_panel);
        Prop_Speed_panel.setLayout(Prop_Speed_panelLayout);
        Prop_Speed_panelLayout.setHorizontalGroup(
            Prop_Speed_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Prop_Speed_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Prop_Speed_Lbl)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Prop_Speed_panelLayout.setVerticalGroup(
            Prop_Speed_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Prop_Speed_Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        Prop_CakeID_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Cake ID"));

        Prop_CakeID_Lbl.setFont(new java.awt.Font("Verdana", 1, 11));
        Prop_CakeID_Lbl.setText("35");

        javax.swing.GroupLayout Prop_CakeID_panelLayout = new javax.swing.GroupLayout(Prop_CakeID_panel);
        Prop_CakeID_panel.setLayout(Prop_CakeID_panelLayout);
        Prop_CakeID_panelLayout.setHorizontalGroup(
            Prop_CakeID_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Prop_CakeID_panelLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(Prop_CakeID_Lbl)
                .addContainerGap())
        );
        Prop_CakeID_panelLayout.setVerticalGroup(
            Prop_CakeID_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Prop_CakeID_Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        Prop_Pos_panel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Position"));

        Prop_Pos_Lbl2.setFont(new java.awt.Font("Verdana", 0, 14));
        Prop_Pos_Lbl2.setText("(Y,X)");

        javax.swing.GroupLayout Prop_Pos_panel2Layout = new javax.swing.GroupLayout(Prop_Pos_panel2);
        Prop_Pos_panel2.setLayout(Prop_Pos_panel2Layout);
        Prop_Pos_panel2Layout.setHorizontalGroup(
            Prop_Pos_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Prop_Pos_panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Prop_Pos_Lbl2)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        Prop_Pos_panel2Layout.setVerticalGroup(
            Prop_Pos_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Prop_Pos_panel2Layout.createSequentialGroup()
                .addComponent(Prop_Pos_Lbl2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Prop_Overview_panelLayout = new javax.swing.GroupLayout(Prop_Overview_panel);
        Prop_Overview_panel.setLayout(Prop_Overview_panelLayout);
        Prop_Overview_panelLayout.setHorizontalGroup(
            Prop_Overview_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Prop_Overview_panelLayout.createSequentialGroup()
                .addGroup(Prop_Overview_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Prop_State_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Prop_Overview_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(Prop_Overview_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Prop_Speed_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Prop_CakeID_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Prop_Pos_panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Prop_Battery_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Prop_Overview_panelLayout.setVerticalGroup(
            Prop_Overview_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Prop_Overview_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Prop_Overview_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Prop_Battery_panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Prop_Overview_panelLayout.createSequentialGroup()
                        .addComponent(Prop_State_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Prop_Speed_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Prop_Overview_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Prop_CakeID_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Prop_Pos_panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Prop_info.addTab("Overview", Prop_Overview_panel);

        Prop_TacoCount_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Taco Count"));

        jLabel14.setText("Right Wheel:");

        jLabel17.setText("Left Wheel:");

        jLabel18.setText("Claw:");

        Prop_rWheel_Lbl.setText("1000");

        Prop_lWheel_Lbl.setText("1000");

        Prop_Claw_Lbl.setText("1000");

        javax.swing.GroupLayout Prop_TacoCount_panelLayout = new javax.swing.GroupLayout(Prop_TacoCount_panel);
        Prop_TacoCount_panel.setLayout(Prop_TacoCount_panelLayout);
        Prop_TacoCount_panelLayout.setHorizontalGroup(
            Prop_TacoCount_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Prop_TacoCount_panelLayout.createSequentialGroup()
                .addGroup(Prop_TacoCount_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel14)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Prop_TacoCount_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Prop_lWheel_Lbl, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Prop_rWheel_Lbl, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Prop_Claw_Lbl, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Prop_TacoCount_panelLayout.setVerticalGroup(
            Prop_TacoCount_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Prop_TacoCount_panelLayout.createSequentialGroup()
                .addGroup(Prop_TacoCount_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(Prop_rWheel_Lbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Prop_TacoCount_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(Prop_lWheel_Lbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Prop_TacoCount_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(Prop_Claw_Lbl)))
        );

        Prop_Dist_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Distance"));

        Prop_Dist_Lbl.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        Prop_Dist_Lbl.setText("50 m");

        javax.swing.GroupLayout Prop_Dist_panelLayout = new javax.swing.GroupLayout(Prop_Dist_panel);
        Prop_Dist_panel.setLayout(Prop_Dist_panelLayout);
        Prop_Dist_panelLayout.setHorizontalGroup(
            Prop_Dist_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Prop_Dist_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Prop_Dist_Lbl)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        Prop_Dist_panelLayout.setVerticalGroup(
            Prop_Dist_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Prop_Dist_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Prop_Dist_Lbl)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        Prop_WheelDia_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Wheel Diameter"));

        Prop_WheelDia_Lbl.setText("13 cm");

        javax.swing.GroupLayout Prop_WheelDia_panelLayout = new javax.swing.GroupLayout(Prop_WheelDia_panel);
        Prop_WheelDia_panel.setLayout(Prop_WheelDia_panelLayout);
        Prop_WheelDia_panelLayout.setHorizontalGroup(
            Prop_WheelDia_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Prop_WheelDia_panelLayout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(Prop_WheelDia_Lbl)
                .addContainerGap())
        );
        Prop_WheelDia_panelLayout.setVerticalGroup(
            Prop_WheelDia_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Prop_WheelDia_panelLayout.createSequentialGroup()
                .addComponent(Prop_WheelDia_Lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout Prop_Mortor_panelLayout = new javax.swing.GroupLayout(Prop_Mortor_panel);
        Prop_Mortor_panel.setLayout(Prop_Mortor_panelLayout);
        Prop_Mortor_panelLayout.setHorizontalGroup(
            Prop_Mortor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Prop_Mortor_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Prop_Mortor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Prop_WheelDia_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Prop_Mortor_panelLayout.createSequentialGroup()
                        .addComponent(Prop_TacoCount_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Prop_Dist_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        Prop_Mortor_panelLayout.setVerticalGroup(
            Prop_Mortor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Prop_Mortor_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Prop_Mortor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Prop_Dist_panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Prop_TacoCount_panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Prop_WheelDia_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        Prop_info.addTab("Motor", Prop_Mortor_panel);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Position"));

        Prop_Pos_lbl.setFont(new java.awt.Font("Verdana", 0, 24));
        Prop_Pos_lbl.setText("(Y,X)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Prop_Pos_lbl)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(Prop_Pos_lbl)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Prop_Angle_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Angel"));

        jLabel28.setText("Supposed:");

        Prop_SupAngle_lbl.setFont(new java.awt.Font("Tahoma", 1, 12));
        Prop_SupAngle_lbl.setText("34");

        jLabel40.setText("Actual:");

        Prop_ActualAngle_lbl.setFont(new java.awt.Font("Tahoma", 1, 12));
        Prop_ActualAngle_lbl.setText("45");

        javax.swing.GroupLayout Prop_Angle_panelLayout = new javax.swing.GroupLayout(Prop_Angle_panel);
        Prop_Angle_panel.setLayout(Prop_Angle_panelLayout);
        Prop_Angle_panelLayout.setHorizontalGroup(
            Prop_Angle_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Prop_Angle_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Prop_Angle_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Prop_Angle_panelLayout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Prop_ActualAngle_lbl))
                    .addGroup(Prop_Angle_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Prop_Angle_panelLayout.createSequentialGroup()
                            .addComponent(jLabel28)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Prop_SupAngle_lbl))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Prop_Angle_panelLayout.setVerticalGroup(
            Prop_Angle_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Prop_Angle_panelLayout.createSequentialGroup()
                .addGroup(Prop_Angle_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(Prop_SupAngle_lbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Prop_Angle_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(Prop_ActualAngle_lbl))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Prop_TargetLoc_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Target Location"));

        Prop_TargetLoc_Lbl.setFont(new java.awt.Font("Verdana", 0, 24));
        Prop_TargetLoc_Lbl.setText("(Y,X)");

        javax.swing.GroupLayout Prop_TargetLoc_panelLayout = new javax.swing.GroupLayout(Prop_TargetLoc_panel);
        Prop_TargetLoc_panel.setLayout(Prop_TargetLoc_panelLayout);
        Prop_TargetLoc_panelLayout.setHorizontalGroup(
            Prop_TargetLoc_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Prop_TargetLoc_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Prop_TargetLoc_Lbl)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Prop_TargetLoc_panelLayout.setVerticalGroup(
            Prop_TargetLoc_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Prop_TargetLoc_panelLayout.createSequentialGroup()
                .addComponent(Prop_TargetLoc_Lbl)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Prop_Pos_panelLayout = new javax.swing.GroupLayout(Prop_Pos_panel);
        Prop_Pos_panel.setLayout(Prop_Pos_panelLayout);
        Prop_Pos_panelLayout.setHorizontalGroup(
            Prop_Pos_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Prop_Pos_panelLayout.createSequentialGroup()
                .addGroup(Prop_Pos_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Prop_Pos_panelLayout.createSequentialGroup()
                        .addContainerGap(119, Short.MAX_VALUE)
                        .addComponent(Prop_Angle_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(238, 238, 238))
            .addGroup(Prop_Pos_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Prop_Pos_panelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Prop_TargetLoc_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(183, Short.MAX_VALUE)))
        );
        Prop_Pos_panelLayout.setVerticalGroup(
            Prop_Pos_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Prop_Pos_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Prop_Angle_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
            .addGroup(Prop_Pos_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Prop_Pos_panelLayout.createSequentialGroup()
                    .addContainerGap(107, Short.MAX_VALUE)
                    .addComponent(Prop_TargetLoc_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(48, 48, 48)))
        );

        Prop_info.addTab("Position", Prop_Pos_panel);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Cousre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(Berta_info, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Prop_info, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Prop_info, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cousre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Berta_info, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Berta_ActualAngle_lbl;
    private javax.swing.JPanel Berta_Angle_panel;
    private javax.swing.JProgressBar Berta_BatteryLevelBar;
    private javax.swing.JPanel Berta_Battery_panel;
    private javax.swing.JLabel Berta_CakeID_Lbl;
    private javax.swing.JPanel Berta_CakeID_panel;
    private javax.swing.JLabel Berta_Claw_Lbl;
    private javax.swing.JLabel Berta_Dist_Lbl;
    private javax.swing.JPanel Berta_Dist_panel;
    private javax.swing.JPanel Berta_Mortor_panel;
    private javax.swing.JPanel Berta_Overview_panel;
    private javax.swing.JLabel Berta_Pos_Lbl2;
    private javax.swing.JLabel Berta_Pos_lbl;
    private javax.swing.JPanel Berta_Pos_panel;
    private javax.swing.JPanel Berta_Pos_panel2;
    private javax.swing.JLabel Berta_Speed_Lbl;
    private javax.swing.JPanel Berta_Speed_panel;
    private javax.swing.JLabel Berta_State_label;
    private javax.swing.JPanel Berta_State_panel;
    private javax.swing.JLabel Berta_SupAngle_lbl;
    private javax.swing.JPanel Berta_TacoCount_panel;
    private javax.swing.JLabel Berta_TargetLoc_Lbl;
    private javax.swing.JPanel Berta_TargetLoc_panel;
    private javax.swing.JLabel Berta_WheelDia_Lbl;
    private javax.swing.JPanel Berta_WheelDia_panel;
    private javax.swing.JTabbedPane Berta_info;
    private javax.swing.JLabel Berta_lWheel_Lbl;
    private javax.swing.JLabel Berta_rWheel_Lbl;
    private javax.swing.JPanel Cousre;
    private javax.swing.JLabel Prop_ActualAngle_lbl;
    private javax.swing.JPanel Prop_Angle_panel;
    private javax.swing.JProgressBar Prop_BatteryLevelBar;
    private javax.swing.JPanel Prop_Battery_panel;
    private javax.swing.JLabel Prop_CakeID_Lbl;
    private javax.swing.JPanel Prop_CakeID_panel;
    private javax.swing.JLabel Prop_Claw_Lbl;
    private javax.swing.JLabel Prop_Dist_Lbl;
    private javax.swing.JPanel Prop_Dist_panel;
    private javax.swing.JPanel Prop_Mortor_panel;
    private javax.swing.JPanel Prop_Overview_panel;
    private javax.swing.JLabel Prop_Pos_Lbl2;
    private javax.swing.JLabel Prop_Pos_lbl;
    private javax.swing.JPanel Prop_Pos_panel;
    private javax.swing.JPanel Prop_Pos_panel2;
    private javax.swing.JLabel Prop_Speed_Lbl;
    private javax.swing.JPanel Prop_Speed_panel;
    private javax.swing.JLabel Prop_State_label;
    private javax.swing.JPanel Prop_State_panel;
    private javax.swing.JLabel Prop_SupAngle_lbl;
    private javax.swing.JPanel Prop_TacoCount_panel;
    private javax.swing.JLabel Prop_TargetLoc_Lbl;
    private javax.swing.JPanel Prop_TargetLoc_panel;
    private javax.swing.JLabel Prop_WheelDia_Lbl;
    private javax.swing.JPanel Prop_WheelDia_panel;
    private javax.swing.JTabbedPane Prop_info;
    private javax.swing.JLabel Prop_lWheel_Lbl;
    private javax.swing.JLabel Prop_rWheel_Lbl;
    private javax.swing.JLabel Tile_X_Count_label;
    private javax.swing.JLabel Tile_Y_Count_Label;
    private javax.swing.JLabel cake_Count_Lbl;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel obstacleCount_Lbl;
    // End of variables declaration//GEN-END:variables

    //Course Info updates
    private void updateNumberOfCake(){
        cake_Count_Lbl.setText(MainController.getInstance().currentCakesCount()+"");
    }
    private void updateNumberOfObstacles(int ob_nr){
        obstacleCount_Lbl.setText(ob_nr+"");
    }
    private void updateXtiles(int x){
        Tile_X_Count_label.setText(x+"");
        
    }
    private void updateYtiles(int y){
        Tile_Y_Count_Label.setText(y+"");
    }
    
    //B.E.R.T.A. Panel
    //Overview
    private void updateBertaState(){
        String text = MainController.getInstance().getBertaState().toString();
    if(!text.equals("")){
        Berta_Speed_Lbl.setText(text);
    }else{
        Berta_Speed_Lbl.setText("N/A");
    }
    }
    
    private void updateBertaSpeed(){
        //TODO: Lav speed update metode. Nok noget med wheeldiameter og tacocount
    }
    private void updateBertaCakeID(int cake){
        Berta_CakeID_Lbl.setText(cake+"");
    }
    private void updateBertaPos(int x, int y){
        Berta_Pos_Lbl2.setText("("+x+","+y+")");
        Berta_Pos_lbl.setText("("+x+","+y+")");
    }
   private void updateBertaBattery(){
       //TODO: Lav batteri opdaterings metode
   }
   //Motor
   private void updateBertaTacoCount(int left, int right, int claw){
       Berta_lWheel_Lbl.setText(left+"");
       Berta_rWheel_Lbl.setText(right+"");
       Berta_Claw_Lbl.setText(claw+"");
   }
   private void updateBertaDistance(){
       //TODO: lav distance metode
   }
   private void updateBertaWheelDiameter(int diameter){
       Berta_WheelDia_Lbl.setText(diameter+"");
   }
   //Position
   private void updateBertaTargetPos(int x, int y){
       Berta_TargetLoc_Lbl.setText("("+x+","+y+")");
   }
   
   private void updateBertaSupposed2BeAngle(int angle){
    Berta_SupAngle_lbl.setText(angle+"°");          
   }
   private void updateBertaActualAngle(int angle){
       Berta_ActualAngle_lbl.setText(angle+"°");
   }
   
   //P.R.O.P. Panel
    //Overview
    private void updatePropState(){
    Prop_State_label.setText(MainController.getInstance().getPropState()+"");
    }
    private void updatePropSpeed(){
        //TODO: Lav speed update metode. Nok noget med wheeldiameter og tacocount
    }
    private void updatePropCakeID(int cake){
        Prop_CakeID_Lbl.setText(cake+"");
    }
    private void updatePropPos(int x, int y){
        Prop_Pos_Lbl2.setText("("+x+","+y+")");
        Prop_Pos_lbl.setText("("+x+","+y+")");
    }
   private void updatePropBattery(){
       //TODO: Lav batteri opdaterings metode
   }
   //Motor
   private void updatePropTacoCount(int left, int right, int claw){
       Prop_lWheel_Lbl.setText(left+"");
       Prop_rWheel_Lbl.setText(right+"");
       Prop_Claw_Lbl.setText(claw+"");
   }
   private void updatePropDistance(){
       //TODO: lav distance metode
   }
   private void updatePropWheelDiameter(int diameter){
       Prop_WheelDia_Lbl.setText(diameter+"");
   }
   //Position
   private void updatePropTargetPos(int x, int y){
       Prop_TargetLoc_Lbl.setText("("+x+","+y+")");
   }
   
   private void updatePropSupposed2BeAngle(int angle){
    Prop_SupAngle_lbl.setText(angle+"°");          
   }
   private void updatePropActualAngle(int angle){
       Prop_ActualAngle_lbl.setText(angle+"°");
   }
}
