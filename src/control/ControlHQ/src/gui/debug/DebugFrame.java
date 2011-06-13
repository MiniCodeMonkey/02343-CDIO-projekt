package gui.debug;

import java.io.IOException;

import com.sun.media.rtsp.protocol.SetupMessage;

import command.interfaces.IControl;

import gui.FramePlaceHolder;

/**
 *
 * @author Morten Hulvej
 */
public class DebugFrame extends javax.swing.JInternalFrame implements IControl {

    /** Creates new form DebugFrame */
    public DebugFrame() {
        initComponents();
        FramePlaceHolder.setDebugf(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        upLabel = new javax.swing.JLabel();
        rightLabel = new javax.swing.JLabel();
        leftLabel = new javax.swing.JLabel();
        downLabel = new javax.swing.JLabel();
        clawCLabel = new javax.swing.JLabel();
        clawOLabel = new javax.swing.JLabel();

        jLabel1.setText("Robot:");

        upLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        upLabel.setText("UP");
        upLabel.setEnabled(false);

        rightLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rightLabel.setText("RIGHT");
        rightLabel.setEnabled(false);

        leftLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        leftLabel.setText("LEFT");
        leftLabel.setEnabled(false);

        downLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        downLabel.setText("DOWN");
        downLabel.setEnabled(false);

        clawCLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        clawCLabel.setText("CLAWCLOSE");
        clawCLabel.setEnabled(false);

        clawOLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        clawOLabel.setText("CLAW_OPEN");
        clawOLabel.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(85, 85, 85)
                .addComponent(upLabel)
                .addContainerGap(145, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(downLabel)
                .addContainerGap(135, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(clawCLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clawOLabel)
                .addGap(80, 80, 80))
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(leftLabel)
                .addGap(61, 61, 61)
                .addComponent(rightLabel)
                .addContainerGap(87, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(upLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(leftLabel)
                    .addComponent(rightLabel))
                .addGap(18, 18, 18)
                .addComponent(downLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clawOLabel)
                    .addComponent(clawCLabel))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel clawCLabel;
    private javax.swing.JLabel clawOLabel;
    private javax.swing.JLabel downLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel leftLabel;
    private javax.swing.JLabel rightLabel;
    private javax.swing.JLabel upLabel;
    // End of variables declaration//GEN-END:variables


	@Override
	public void move(int speed, boolean reverse) throws IOException {
		// TODO impl SPEED
		
		move(reverse);
		
	}

	@Override
	public void move(boolean reverse) throws IOException {
		if (!reverse)
			upLabel.setEnabled(true);
		else
			downLabel.setEnabled(true);
		
	}

	@Override
	public void left(int turnSpeed) throws IOException {
		// TODO impl TURNSPEED
		left();
		
	}

	@Override
	public void left() throws IOException {
		leftLabel.setEnabled(true);
		
	}

	@Override
	public void right(int turnSpeed) throws IOException {
		// TODO impl TURNSPEED
		right();
	}

	@Override
	public void right() throws IOException {
		rightLabel.setEnabled(true);
	}

	@Override
	public void stop() throws IOException {
		upLabel.setEnabled(false);
		downLabel.setEnabled(false);
		leftLabel.setEnabled(false);
		rightLabel.setEnabled(false);
		clawCLabel.setEnabled(false);
		clawOLabel.setEnabled(false);
	}

	@Override
	public int getBatteryLevel() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDistanceToNearestObject() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void openClaw(int clawMotor) throws IOException {
		// TODO impl SPEED
		openClaw();
	}

	@Override
	public void openClaw() throws IOException {
		clawOLabel.setEnabled(true);
	}

	@Override
	public void closeClaw(int clawMotor) throws IOException {
		// TODO impl SPEED
		closeClaw();
	}

	@Override
	public void closeClaw() throws IOException {
		clawCLabel.setEnabled(true);
	}

	@Override
	public void stopClaw() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isClawMoving() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasCake() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reverse(int speed, int duration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnect() throws IOException {
		// TODO Auto-generated method stub
		
	}

}
