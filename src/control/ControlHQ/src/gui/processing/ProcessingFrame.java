package gui.processing;

import java.awt.Point;
import java.awt.image.BufferedImage;

import controller.MainController;


import dk.dtu.imm.c02343.grp4.dto.interfaces.ILocations;
import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.IImageProcessor;
import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.Thresholds;
import dk.dtu.imm.c02343.grp4.imageprocessing.testimageprocessing.ImagePanel;
import gui.FramePlaceHolder;

/**
 *
 * @author Morten Hulvej
 */
public class ProcessingFrame extends javax.swing.JInternalFrame {
		
	//custom varables
	private int THRESHOLDS_BUFFER_SIZE = 15;
	
    /** Creates new form ImageFrame */
    public ProcessingFrame() {
        initComponents();
        FramePlaceHolder.setProcessingFrame(this);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        imageProcessPanel = new javax.swing.JPanel();
        imagePanel = new javax.swing.JPanel();
        processedImagePanel = new javax.swing.JPanel();
        imageToolbar = new javax.swing.JToolBar();
        webcamBtn = new javax.swing.JButton();
        pauseTBtn = new javax.swing.JToggleButton();
        updateIntvlabel = new javax.swing.JLabel();
        updateIntervalSpinner = new javax.swing.JSpinner();
        jSeparator1 = new javax.swing.JToolBar.Separator();

        setIconifiable(true);
        setResizable(true);
        setTitle("Processing");
        setNormalBounds(new java.awt.Rectangle(490, 10, 650, 528));

        imageProcessPanel.setLayout(new java.awt.GridBagLayout());

        imagePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Raw Image"));
        imagePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imagePanelMousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        imageProcessPanel.add(imagePanel, gridBagConstraints);

        processedImagePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Processed Image"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        imageProcessPanel.add(processedImagePanel, gridBagConstraints);

        imageToolbar.setRollover(true);

        webcamBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/webcam_icon.png"))); // NOI18N
        webcamBtn.setText("Start webcam & info feed");
        webcamBtn.setToolTipText("Start Webcam");
        webcamBtn.setFocusable(false);
        webcamBtn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        webcamBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webcamBtnActionPerformed(evt);
            }
        });
        imageToolbar.add(webcamBtn);

        pauseTBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pause.png"))); // NOI18N
        pauseTBtn.setText("Pause Webcam");
        pauseTBtn.setToolTipText("Pause Webcam");
        pauseTBtn.setEnabled(false);
        pauseTBtn.setFocusable(false);
        pauseTBtn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        imageToolbar.add(pauseTBtn);

        updateIntvlabel.setLabelFor(updateIntervalSpinner);
        updateIntvlabel.setText("Update interval:");
        updateIntvlabel.setEnabled(false);
        imageToolbar.add(updateIntvlabel);

        updateIntervalSpinner.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.100000024f), Float.valueOf(0.05f), Float.valueOf(10.0f), Float.valueOf(0.1f)));
        updateIntervalSpinner.setMaximumSize(new java.awt.Dimension(50, 20));
        updateIntervalSpinner.setMinimumSize(new java.awt.Dimension(30, 20));
        updateIntervalSpinner.setPreferredSize(new java.awt.Dimension(50, 20));
        imageToolbar.add(updateIntervalSpinner);
        imageToolbar.add(jSeparator1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imageToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
            .addComponent(imageProcessPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imageToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imageProcessPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Deprecated
    private void webcamBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webcamBtnActionPerformed
    	
    	
    }//GEN-LAST:event_webcamBtnActionPerformed

    private void imagePanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagePanelMousePressed
    	  		
    		Thresholds thresH = pickColorFromImage(srcImgPanel.getImage());

    		//Cake
    		if(FramePlaceHolder.getImgThresholdFrame().isCakeColorPickerSelected()){
    			System.out.println("Sætter CAKE farven");
    			MainController.getInstance().getImageProcessor().setThresholds(IImageProcessor.CAKE, thresH);
    			FramePlaceHolder.getImgThresholdFrame().setCakeColorLabel(
    					thresH.getMaxR()-THRESHOLDS_BUFFER_SIZE, thresH.getMaxG()-THRESHOLDS_BUFFER_SIZE, thresH.getMaxB()-THRESHOLDS_BUFFER_SIZE);
    			FramePlaceHolder.getImgThresholdFrame().resetCakeColorToggleBtn();
    		}

    		//Obstacle
    		if(FramePlaceHolder.getImgThresholdFrame().isObsColorPickerSelected()){
    			System.out.println("Sætter OBSTACLE farven");
    			MainController.getInstance().getImageProcessor().setThresholds(IImageProcessor.OBSTACLE, thresH);
    			FramePlaceHolder.getImgThresholdFrame().setObsColorLabel(
    					thresH.getMaxR()-THRESHOLDS_BUFFER_SIZE, thresH.getMaxG()-THRESHOLDS_BUFFER_SIZE, thresH.getMaxB()-THRESHOLDS_BUFFER_SIZE);
    			FramePlaceHolder.getImgThresholdFrame().resetObsColorToggleBtn();
    		}
    		//Robot 1 (Berta)
    		if(FramePlaceHolder.getImgThresholdFrame().isR1ColorPickerSelected()){
    			//FrontTab
    			if(FramePlaceHolder.getImgThresholdFrame().isR1FrontTabShowing()){
    				System.out.println("Sætter R1-FRONT farven");
    				IImageProcessor.ROBOT1_N_THRESHOLDS.setThresholds(
    						thresH.getMinR(), thresH.getMinG(), thresH.getMinB(),thresH.getMaxR(), thresH.getMaxG(), thresH.getMaxB());
    				FramePlaceHolder.getImgThresholdFrame().setR1FrontColorLabel(
    						thresH.getMaxR()-THRESHOLDS_BUFFER_SIZE, thresH.getMaxG()-THRESHOLDS_BUFFER_SIZE, thresH.getMaxB()-THRESHOLDS_BUFFER_SIZE);
    				FramePlaceHolder.getImgThresholdFrame().resetR1ColorToggleBtn();
    			}
    			//BackTab
    			if(FramePlaceHolder.getImgThresholdFrame().isR1BackTabShowing()){
    				System.out.println("Sætter R1-BACK farven");
    				IImageProcessor.ROBOT1_S_THRESHOLDS.setThresholds(
    						thresH.getMinR(), thresH.getMinG(), thresH.getMinB(),thresH.getMaxR(), thresH.getMaxG(), thresH.getMaxB());
    				FramePlaceHolder.getImgThresholdFrame().setR1BackColorLabel(
    						thresH.getMaxR()-THRESHOLDS_BUFFER_SIZE, thresH.getMaxG()-THRESHOLDS_BUFFER_SIZE, thresH.getMaxB()-THRESHOLDS_BUFFER_SIZE);
    				FramePlaceHolder.getImgThresholdFrame().resetR1ColorToggleBtn();
    			}
    		}
    		//Robot 2 (Prop)
    		if(FramePlaceHolder.getImgThresholdFrame().isR2ColorPickerSelected()){
    			//FrontTab
    			if(FramePlaceHolder.getImgThresholdFrame().isR2FrontTabShowing()){
    				System.out.println("Sætter R2-FRONT farven");
    				IImageProcessor.ROBOT2_N_THRESHOLDS.setThresholds(
    						thresH.getMinR(), thresH.getMinG(), thresH.getMinB(),thresH.getMaxR(), thresH.getMaxG(), thresH.getMaxB());
    				FramePlaceHolder.getImgThresholdFrame().setR2FrontColorLabel(
    						thresH.getMaxR()-THRESHOLDS_BUFFER_SIZE, thresH.getMaxG()-THRESHOLDS_BUFFER_SIZE, thresH.getMaxB()-THRESHOLDS_BUFFER_SIZE);
    				FramePlaceHolder.getImgThresholdFrame().resetR2ColorToggleBtn();
    			}
    			//BackTab
    			if(FramePlaceHolder.getImgThresholdFrame().isR2BackTabShowing()){
    				System.out.println("Sætter R2-BACK farven");
    				IImageProcessor.ROBOT2_S_THRESHOLDS.setThresholds(
    						thresH.getMinR(), thresH.getMinG(), thresH.getMinB(),thresH.getMaxR(), thresH.getMaxG(), thresH.getMaxB());
    				FramePlaceHolder.getImgThresholdFrame().setR2BackColorLabel(
    						thresH.getMaxR()-THRESHOLDS_BUFFER_SIZE, thresH.getMaxG()-THRESHOLDS_BUFFER_SIZE, thresH.getMaxB()-THRESHOLDS_BUFFER_SIZE);
    				FramePlaceHolder.getImgThresholdFrame().resetR2ColorToggleBtn();
    			}
    		}

    }//GEN-LAST:event_imagePanelMousePressed
    
    public void updateImagePanel() {
    	
        
    	ILocations locations = MainController.getInstance().getInformation();
    	
    	// kilde billede
    	BufferedImage sourceImg = locations.getSourceImage();
    	

    	BufferedImage tileImg = locations.getTileImage();
		
        srcImgPanel.setImage(sourceImg);
        srcImgPanel.repaint();

        tileImgPanel.setImage(tileImg);
        tileImgPanel.repaint();

        
	}
        
    private ImagePanel srcImgPanel;
    private ImagePanel tileImgPanel;
    
    private RobotColorChangeListener stateChangeListener;
    
    boolean webcamRunning = true;
    boolean webcamFeedPaused = false;
    float time_slice = 0.1f;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JPanel imagePanel;
    private javax.swing.JPanel imageProcessPanel;
    private javax.swing.JToolBar imageToolbar;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToggleButton pauseTBtn;
    private javax.swing.JPanel processedImagePanel;
    private javax.swing.JSpinner updateIntervalSpinner;
    private javax.swing.JLabel updateIntvlabel;
    private javax.swing.JButton webcamBtn;
    // End of variables declaration//GEN-END:variables

    
    public void updateImages(){
    	
    	System.out.println("updataImages");
				
    	
    	new Thread("Update images thread"){
    		
    		@Override
    		public void run()
    		{
    			try
    			{
    				
    				do{
    					Thread.sleep(500);
    				}while(!MainController.getInstance().isProcessorRunning() || MainController.getInstance().getInformation() == null);
    				
    				ILocations locations = MainController.getInstance().getInformation();
    		    	
    		    	// kilde billede
    		    	BufferedImage sourceImg = locations.getSourceImage();
    		    	
    		    	// behandlet billede
    		    	BufferedImage tileImg = locations.getTileImage();
    				
    		    	// Opret JFrame samt panel til input-billede
    		        srcImgPanel = new ImagePanel(sourceImg);
    		        imagePanel.add(srcImgPanel);

    		        // Opret JFrame samt panel til tile-billede
    		        tileImgPanel = new ImagePanel(tileImg);
    		        processedImagePanel.add(tileImgPanel);
    				
    				while(true){					
    					Thread.sleep(50);
    					updateImagePanel();
    				}
    			} catch (InterruptedException e)
    			{
    				e.printStackTrace();
    			}
    		}
    	}.start();
    	
    					
    	
    	
    	
    	
    	
    }
    public Thresholds pickColorFromImage(BufferedImage image)
    {
    	int[] rgb = new int[3];
    	int rgbVal;
    	int[] minVals = new int[3];
    	int[] maxVals = new int[3];
    	Point p = imagePanel.getMousePosition();    	
    	System.out.println("imagePanel "+imagePanel);
    	
    	rgbVal = image.getRGB((int)p.getX(), (int)p.getY());
    	
    	System.out.println("RGB val "+rgbVal);
    	
    	// Beregn RGB-komponenter vha. bit-shifting og bitwise AND
    	//fra ImageProcessor2.java (Per)
    	rgb[2] = rgbVal & 0xFF;
    	rgb[1] = (rgbVal >> 8) & 0xFF;
    	rgb[0] = (rgbVal >> 16) & 0xFF;

    	//Sanity check på RGB værdierne
    	for (int i = 0; i < rgb.length; i++)
    	{
    		if ( rgb[i]+THRESHOLDS_BUFFER_SIZE>256)
    			maxVals[i] = 256;
    		else if  (rgb[i]-THRESHOLDS_BUFFER_SIZE < 0)
    			minVals[i] = 0;
    		else {
    			maxVals[i]=rgb[i]+THRESHOLDS_BUFFER_SIZE;
    			minVals[i]=rgb[i]-THRESHOLDS_BUFFER_SIZE;
    		}
    	}
    	Thresholds t = new Thresholds(minVals[0], minVals[1], minVals[2], maxVals[0], maxVals[1],maxVals[2]);    	
		return  t;
    }

}
