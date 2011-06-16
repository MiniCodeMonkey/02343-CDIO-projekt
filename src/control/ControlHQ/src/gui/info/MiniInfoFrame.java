/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MiniInfoFrame.java
 *
 * Created on 08-06-2011, 13:17:30
 */

package gui.info;

import gui.FramePlaceHolder;
import controller.MainController;
import javax.swing.ImageIcon;

import dk.dtu.imm.c02343.grp4.pathfinding.dat.Location;

/**
 *
 * @author Morten Hulvej
 */
public class MiniInfoFrame extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = -2479815477127221942L;
	
	/** Creates new form MiniInfoFrame */
    public MiniInfoFrame() {
        initComponents();
        FramePlaceHolder.setMinInfoFrame(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bertaInfoPanel = new javax.swing.JPanel();
        BertaNameLabel = new javax.swing.JLabel();
        BertaStartStopLabel = new javax.swing.JLabel();
        bertaStateLabel = new javax.swing.JLabel();
        bertaPosLabel = new javax.swing.JLabel();
        bertaAngleLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        bertaTargetLabel = new javax.swing.JLabel();
        bertaTargelAngleLabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        propInfoPanel = new javax.swing.JPanel();
        PropNameLabel = new javax.swing.JLabel();
        PropStartStopLabel = new javax.swing.JLabel();
        PropStateLabel = new javax.swing.JLabel();
        PropPosLabel = new javax.swing.JLabel();
        PropAngleLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        propTargetLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        propTargetAngleLabel = new javax.swing.JLabel();

        setIconifiable(true);
        setResizable(true);
        setTitle("Robot Information");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/info_black-32.png"))); // NOI18N

        bertaInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("B.E.R.T.A."));
        bertaInfoPanel.setLayout(new java.awt.GridBagLayout());

        BertaNameLabel.setText("Connected");
        BertaNameLabel.setToolTipText("Connection");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        bertaInfoPanel.add(BertaNameLabel, gridBagConstraints);

        BertaStartStopLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stop.png"))); // NOI18N
        BertaStartStopLabel.setToolTipText("Connection");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        bertaInfoPanel.add(BertaStartStopLabel, gridBagConstraints);

        bertaStateLabel.setText("N/A");
        bertaStateLabel.setToolTipText("State");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        bertaInfoPanel.add(bertaStateLabel, gridBagConstraints);

        bertaPosLabel.setText("N/A");
        bertaPosLabel.setToolTipText("Position");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        bertaInfoPanel.add(bertaPosLabel, gridBagConstraints);

        bertaAngleLabel.setText("N/A");
        bertaAngleLabel.setToolTipText("Robot Angle");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        bertaInfoPanel.add(bertaAngleLabel, gridBagConstraints);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/state.png"))); // NOI18N
        jLabel1.setToolTipText("State");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        bertaInfoPanel.add(jLabel1, gridBagConstraints);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/gps.png"))); // NOI18N
        jLabel2.setToolTipText("Position");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        bertaInfoPanel.add(jLabel2, gridBagConstraints);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/angle.png"))); // NOI18N
        jLabel3.setToolTipText("Robot Angle");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        bertaInfoPanel.add(jLabel3, gridBagConstraints);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/target.gif"))); // NOI18N
        jLabel7.setToolTipText("Target Location");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        bertaInfoPanel.add(jLabel7, gridBagConstraints);

        bertaTargetLabel.setText("N/A");
        bertaTargetLabel.setToolTipText("Target Location");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        bertaInfoPanel.add(bertaTargetLabel, gridBagConstraints);

        bertaTargelAngleLabel.setText("N/A");
        bertaTargelAngleLabel.setToolTipText("Target Angle");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        bertaInfoPanel.add(bertaTargelAngleLabel, gridBagConstraints);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/target_angle.png"))); // NOI18N
        jLabel10.setToolTipText("Target Angle");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 0);
        bertaInfoPanel.add(jLabel10, gridBagConstraints);

        propInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("P.R.O.P."));
        propInfoPanel.setLayout(new java.awt.GridBagLayout());

        PropNameLabel.setText("Connected");
        PropNameLabel.setToolTipText("Connection");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        propInfoPanel.add(PropNameLabel, gridBagConstraints);

        PropStartStopLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stop.png"))); // NOI18N
        PropStartStopLabel.setToolTipText("Connection");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        propInfoPanel.add(PropStartStopLabel, gridBagConstraints);

        PropStateLabel.setText("N/a");
        PropStateLabel.setToolTipText("State");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        propInfoPanel.add(PropStateLabel, gridBagConstraints);

        PropPosLabel.setText("N/A");
        PropPosLabel.setToolTipText("Position");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        propInfoPanel.add(PropPosLabel, gridBagConstraints);

        PropAngleLabel.setText("N/A");
        PropAngleLabel.setToolTipText("Robot Angle");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        propInfoPanel.add(PropAngleLabel, gridBagConstraints);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/state.png"))); // NOI18N
        jLabel4.setToolTipText("State");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        propInfoPanel.add(jLabel4, gridBagConstraints);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/gps.png"))); // NOI18N
        jLabel5.setToolTipText("Position");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        propInfoPanel.add(jLabel5, gridBagConstraints);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/angle.png"))); // NOI18N
        jLabel6.setToolTipText("Robot Angle");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        propInfoPanel.add(jLabel6, gridBagConstraints);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/target.gif"))); // NOI18N
        jLabel8.setToolTipText("Target Location");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        propInfoPanel.add(jLabel8, gridBagConstraints);

        propTargetLabel.setText("N/A");
        propTargetLabel.setToolTipText("Target Location");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        propInfoPanel.add(propTargetLabel, gridBagConstraints);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/target_angle.png"))); // NOI18N
        jLabel9.setToolTipText("Target Angle");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 0);
        propInfoPanel.add(jLabel9, gridBagConstraints);

        propTargetAngleLabel.setText("N/A");
        propTargetAngleLabel.setToolTipText("Target Angle");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        propInfoPanel.add(propTargetAngleLabel, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(bertaInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(propInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(bertaInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(propInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BertaNameLabel;
    private javax.swing.JLabel BertaStartStopLabel;
    private javax.swing.JLabel PropAngleLabel;
    private javax.swing.JLabel PropNameLabel;
    private javax.swing.JLabel PropPosLabel;
    private javax.swing.JLabel PropStartStopLabel;
    private javax.swing.JLabel PropStateLabel;
    private javax.swing.JLabel bertaAngleLabel;
    private javax.swing.JPanel bertaInfoPanel;
    private javax.swing.JLabel bertaPosLabel;
    private javax.swing.JLabel bertaStateLabel;
    private javax.swing.JLabel bertaTargelAngleLabel;
    private javax.swing.JLabel bertaTargetLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel propInfoPanel;
    private javax.swing.JLabel propTargetAngleLabel;
    private javax.swing.JLabel propTargetLabel;
    // End of variables declaration//GEN-END:variables

	// Berta Info Update
    
	/**
	 * FIXME
	 */
	private void updateBertaOnOff()
	{
		if (MainController.getInstance().isBertaConnected())
		{
			BertaStartStopLabel.setIcon(new ImageIcon("/icons/on_lille"));
		} else
		{
			BertaStartStopLabel.setIcon(new ImageIcon("/icons/off_lille"));
		}

	}

	/**
	 * 
	 */
	private void updateBertaState()
	{
		String text = MainController.getInstance().getBertaState().toString();
		if (!text.equals(""))
		{
			bertaStateLabel.setText(text);
		} else
		{
			bertaStateLabel.setText("N/A");
		}
	}

    /**
     * 
     */
    private void updateBertaPos(){

		int[] yx = MainController.getInstance().getInformation().getRobots().get(0).getPos();

		int x = yx[1];
		int y = yx[0];
		bertaPosLabel.setText("(" + x + "," + y + ")");
	}

	/**
	 * 
	 */
	private void updateBertaAngle()
	{
		double radAngle = MainController.getInstance().getInformation().getRobots().get(0).getAngle();
		bertaAngleLabel.setText((int) Math.toDegrees(radAngle) + "" + (char)176);
	}

	/**
	 * 
	 */
	private void updateBertaTargetLocation()
	{
		Location target = MainController.getInstance().getBertaTargetLocation();
		bertaTargetLabel.setText("(" + target.GetX() + "," + target.GetY() + ")");
	}

	/**
	 * 
	 */
	private void updateBertaTargetAngle()
	{
		double angle = MainController.getInstance().getBertaTargetAngle();
		bertaTargelAngleLabel.setText(angle + "" + (char)176);
	}

	// Prop Info Update
	
	private void updatePropOnOff()
	{
		if (MainController.getInstance().isPropConnected())
		{
			PropStartStopLabel.setIcon(new ImageIcon("/icons/on_lille"));
		} else
		{
			PropStartStopLabel.setIcon(new ImageIcon("/icons/off_lille"));
		}
	}

	private void updatePropState()
	{
		String text = MainController.getInstance().getPropState().toString();
		if (!text.equals(""))
		{
			PropStateLabel.setText(text);
		} else
		{
			PropStateLabel.setText("N/A");
		}
	}

	private void updatePropPos()
	{
		int[] yx = MainController.getInstance().getInformation().getRobots().get(0).getPos();

		int x = yx[1];
		int y = yx[0];
		PropPosLabel.setText("(" + x + "," + y + ")");
	}

	private void updatePropAngle()
	{
		double radAngle = MainController.getInstance().getInformation().getRobots().get(0).getAngle();
		PropAngleLabel.setText((int) Math.toDegrees(radAngle) + "" + (char)176);
	}
    private void updatePropTargetLocation()
	{
    	Location target = MainController.getInstance().getPropTargetLocation();
		propTargetLabel.setText("(" + target.GetX() + "," + target.GetY() + ")");
	}
    private void updatePropTargetAngle()
	{
    	double angle = MainController.getInstance().getPropTargetAngle();
		propTargetAngleLabel.setText(angle + "" + (char)176);
	}
    


	public void updateBertaInfo()
	{
		updateBertaAngle();
		updateBertaOnOff();
		updateBertaPos();
		updateBertaState();
		updateBertaTargetLocation();
		updateBertaTargetAngle();
	}

	public void updatePropInfo()
	{
		updatePropAngle();
		updatePropOnOff();
		updatePropPos();
		updatePropState();
		updatePropTargetLocation();
		updatePropTargetAngle();
	}

	public void updateAllInfo()
	{
		if (MainController.getInstance().isBertaConnected())
			updateBertaInfo();
		if (MainController.getInstance().isPropConnected())
			updatePropInfo();
		
		updateBertaOnOff();
		updatePropOnOff();
	}


}
