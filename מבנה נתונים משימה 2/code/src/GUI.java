
//////////////// DON'T CHANGE THIS FILE ////////////////

import javax.swing.*;

import java.awt.*;

import javax.swing.border.*;

import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.awt.image.*;


public class GUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	final int TABLESIZE = 4;
    public DT dt;
	String fileName="d:\\db1.txt";
	boolean withOutLoading=false;
	
	public void setWithOutLoading(boolean withOutLoading){
		this.withOutLoading = withOutLoading; 
	}



    ImageIcon myImageIcon;
    JPanel mainPanel = new JPanel();
    JPanel inputListPanel = new JPanel();
    JPanel outputListPanel = new JPanel();
    BorderLayout borderLayout1 = new BorderLayout();
    BorderLayout borderLayout2 = new BorderLayout();
    BorderLayout borderLayout3 = new BorderLayout();
    Border border1, border2, border3;
    JMenuBar jMenuBar = new JMenuBar();
    JMenu inputMenu, workingModes;
    JCheckBoxMenuItem loadPictureMenuItem, loadTXTMenuItem;
    JRadioButtonMenuItem closestPoints, getPointsXreg, getPointsXopp, addPoint;// getPointsYreg, getPointsYopp,, halfPlaneX, halfPlaneY;
    JScrollPane inputListScrollPane;
    JScrollPane outputListScrollPane;
    String[] data = {"==="};
    JList<String> inputList = new JList<String>();
    JList<String> outputList = new JList<String>(data);
    TitledBorder titledBorder1, titledBorder2, titledBorder3, titledBorder4, titledBorder5, titledBorder6, titledBorder7;
    FlowLayout flowLayout1 = new FlowLayout();
    PicturePanel picturePanel = new PicturePanel(this);

	//private PointDataStructure PDT;


/////////////////////////////////////////////////////////////////////////
////           Construct the application                
/////////////////////////////////////////////////////////////////////////
    public GUI() 
    {
        try {
            jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

/////////////////////////////////////////////////////////////////////////
////           Main method                    
/////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        try {
            GUI myGUI = new GUI();
            myGUI.setSize(1400, 1400);
            myGUI.setResizable(false);
            myGUI.setTitle("Assignment no. 2");
            myGUI.setVisible(true);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


/////////////////////////////////////////////////////////////////////////
////           GUI method              
/////////////////////////////////////////////////////////////////////////
    private void jbInit() throws Exception {
        titledBorder1 = new TitledBorder("");
        titledBorder2 = new TitledBorder("");
        titledBorder3 = new TitledBorder("");
        titledBorder4 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(100, 140, 100)), "Output list");
        border1 = BorderFactory.createEtchedBorder(Color.black, new Color(148, 145, 140));
        titledBorder5 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(148, 145, 140)), "Input list   ");
        border2 = BorderFactory.createEmptyBorder();
        titledBorder6 = new TitledBorder(border2, "My Picture");
        border3 = BorderFactory.createEmptyBorder();
        titledBorder7 = new TitledBorder(BorderFactory.createMatteBorder(6, 6, 6, 6, Color.red), "Test Your Point");
        titledBorder7.setTitleFont(new Font("SansSerif", Font.BOLD, 30));
        mainPanel.setLayout(borderLayout1);
        this.getContentPane().setLayout(borderLayout2);
        this.getContentPane().setBackground(new Color(212, 224, 200));
        this.setJMenuBar(jMenuBar);
        JScrollPane inputListScrollPane = new JScrollPane(inputList);
        JScrollPane outputListScrollPane = new JScrollPane(outputList);
        inputListPanel.setLayout(flowLayout1);
        inputListPanel.setBackground(new Color(255, 175, 122));
        inputListPanel.setBorder(titledBorder5);
        inputListPanel.setPreferredSize(new Dimension(100, 150));
        outputListPanel.setBackground(Color.orange);
        outputListPanel.setBorder(titledBorder4);
        outputListPanel.setLayout(borderLayout3);
        mainPanel.setBackground(new Color(228, 208, 200));
        mainPanel.setBorder(titledBorder7);
        inputListScrollPane.setPreferredSize(new Dimension(100, 150));
        this.getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.add(picturePanel, BorderLayout.CENTER);
        mainPanel.add(inputListPanel, BorderLayout.EAST);
        inputListPanel.add(inputListScrollPane, null);
        inputMenu = new JMenu("Input Menu");
        workingModes = new JMenu("Test");
        jMenuBar.add(inputMenu);
        jMenuBar.add(workingModes);
        loadPictureMenuItem = new JCheckBoxMenuItem("Load The Picture");
        loadPictureMenuItem.addActionListener(new GUI_loadPictureMenuItem_actionAdapter(this));
        inputMenu.add(loadPictureMenuItem);
        inputMenu.addSeparator();
        loadTXTMenuItem = new JCheckBoxMenuItem("Load The TXT DB For Points");
        loadTXTMenuItem.addActionListener(new GUI_loadTXTMenuItem_actionAdapter(this));
        inputMenu.add(loadTXTMenuItem);
        ButtonGroup group = new ButtonGroup();
    
        getPointsXreg = new JRadioButtonMenuItem("getPointsXreg");
        getPointsXreg.setSelected(true);
        group.add(getPointsXreg);
        workingModes.add(getPointsXreg);
        
        addPoint = new JRadioButtonMenuItem("addPoint");
        group.add(addPoint);
        workingModes.add(addPoint);
        //***
        closestPoints = new JRadioButtonMenuItem("closestPoints");
        group.add(closestPoints);
        workingModes.add(closestPoints);
        
        getPointsXopp = new JRadioButtonMenuItem("getPointsXopp");
        group.add(getPointsXopp);
        workingModes.add(getPointsXopp);
        
       /* getPointsYreg = new JRadioButtonMenuItem("getPointsYreg");
        group.add(getPointsYreg);
        workingModes.add(getPointsYreg);
        
        getPointsYopp = new JRadioButtonMenuItem("getPointsYopp");
        group.add(getPointsYopp);
        workingModes.add(getPointsYopp);*/
        //***
//        halfPlaneX = new JRadioButtonMenuItem("halfPlaneX");
//        group.add(halfPlaneX);
//        workingModes.add(halfPlaneX);
//        halfPlaneY = new JRadioButtonMenuItem("halfPlaneY");
//        group.add(halfPlaneY);
//        workingModes.add(halfPlaneY);
        mainPanel.add(outputListPanel, BorderLayout.SOUTH);
        outputListPanel.add(outputListScrollPane, BorderLayout.CENTER);
    }
    
    
    
/////////////////////////////////////////////////////////////////////////
////           load Picture method                 
/////////////////////////////////////////////////////////////////////////
    void loadPictureMenuItem_actionPerformed(ActionEvent e) {

        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            picturePanel.loadPicture(file.getAbsolutePath());
            loadPictureMenuItem.setState(true);
        } else
            loadPictureMenuItem.setState(false);
    }



/////////////////////////////////////////////////////////////////////////
////           load txt DB method           
/////////////////////////////////////////////////////////////////////////
    void loadTXTMenuItem_actionPerformed(ActionEvent e) {
		try {
    		BufferedReader input=null;
    		int returnVal=0;
    		JFileChooser fc=null;
    		File file=null;
    	
    		if(withOutLoading){input = new BufferedReader(new FileReader(fileName));}
    		else{fc = new JFileChooser();	returnVal = fc.showOpenDialog(this);}
    	
        	if (returnVal == JFileChooser.APPROVE_OPTION || withOutLoading) {
            	if(!withOutLoading) file = fc.getSelectedFile();

                if (!withOutLoading) input = new BufferedReader(new FileReader(file));
                String line = null;
                String objectName;
                int objectX, objectY;
                ArrayList<String> inputPoints = new ArrayList<String>();
                ArrayList<Point> test = new ArrayList<Point>();
                
                
                String[] lineArray;
                Point[] points;
                while ((line = input.readLine()) != null) 
                {
                    lineArray = line.split(";");
                    objectName = lineArray[0];
                    objectX = Integer.parseInt(lineArray[1]);
                    objectY = Integer.parseInt(lineArray[2]);
                    inputPoints.add(line);
                    test.add(new Point(objectX,objectY,objectName));
                 }    
                points=new Point[test.size()];
                test.toArray(points);
             /*   pointCompare1 c1=new pointCompare1();
                pointCompare2 c2=new pointCompare2();
                Arrays.sort(points, c1);
                Arrays.sort(points,c2);*/
                dt=new DataStructure();
                for (int i = 0; i < points.length; i++) 
                {
					dt.addPoint(points[i]);
				}
                String[] il = new String[inputPoints.size()];
                inputPoints.toArray(il);
                updateInputList(il);
            }
            loadTXTMenuItem.setState(true);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
    }
 private class pointCompare1 implements Comparator<Point>
 {

	public int compare(Point point1, Point point2) 
	{
		if (point1.getY()<point2.getY()) return -1;
		else if (point1.getY()==point2.getY() && point1.getX()<point2.getX()) return -1;
		return 1;
	}
	 
 }
 private class pointCompare2 implements Comparator<Point>
 {

	public int compare(Point point1, Point point2) 
	{
		if (point1.getX()<point2.getX()) return -1;
		else return 1;
	}
	 
 }
    
    



/////////////////////////////////////////////////////////////////////////
////           update the input List method                    
/////////////////////////////////////////////////////////////////////////
    public void updateInputList(String[] inputPoints){
		inputList = new JList<String>(inputPoints);
        inputListScrollPane = new JScrollPane(inputList);
        inputListScrollPane.setPreferredSize(new Dimension(50, 50));
        inputListPanel.remove(0);
        inputListPanel.add(inputListScrollPane, null);
        inputListPanel.repaint();
        setVisible(true);
	}
	
/////////////////////////////////////////////////////////////////////////
////           update the update List method            
/////////////////////////////////////////////////////////////////////////
	public void updateOutputList(String[] outputPoints){
		outputList = new JList<String>(outputPoints);
        outputListScrollPane = new JScrollPane(outputList);
        outputListScrollPane.setPreferredSize(new Dimension(140, 150));
        outputListPanel.remove(0);
        outputListPanel.add(outputListScrollPane, null);
        outputListPanel.repaint();
        setVisible(true);
	}
	
	
	/*public String[] numberOfPoints(int XLeft, int XRight)//, int leftTopY, int rightBottomY )
	{
		//System.out.println("first");
		String[] ans=new String[1];
		ans[0]="Number of points in range is "+pdt.numOfPointsInRange(XLeft, XRight);//"Number of points in rectangle = "+tree.numOfPointsInRectangle(new Point(leftTopX,leftTopY),new Point(rightBottomX,rightBottomY));
		return ans;//TODO
    }
	*/
	public Point[] getPointsInRangeReg(int XLeft, int XRight)
	{
		//System.out.println("second");
		Point[] points=dt.getPointsInRangeRegAxis(XLeft, XRight, true);//new Point[0];//tree.getPointsInRectangle(new Point(leftTopX,leftTopY),new Point(rightBottomX,rightBottomY));
		return points;//TODO
	}
	
	public Point[] getPointsInRangeOpp(int XLeft, int XRight)
	{
		//System.out.println("second");
		Point[] points=dt.getPointsInRangeOppAxis(XLeft, XRight, true);//new Point[0];//tree.getPointsInRectangle(new Point(leftTopX,leftTopY),new Point(rightBottomX,rightBottomY));
		return points;//TODO
	}
	
	public Point[] closestPoints()
	{
		return dt.nearestPair();
	}
	
	public String[] addPoint(String name, int X,int Y)
	{
		this.dt.addPoint(new Point(X,Y,name));
		String[] ans=new String[1];
		ans[0]="New point, " +name +", added at ("+X+","+Y+")";
		return ans;//TODO
	}
	
	/*public int averageHeightInRange(int XLeft, int XRight)
	{
		return (int) pdt.averageHeightInRange(XLeft, XRight);
	}
	
	public Point[] getMedianPoints(int k)
	{
		Point[] points=pdt.getMedianPoints(k);//new Point[0];//tree.getPointsInRectangle(new Point(leftTopX,leftTopY),new Point(rightBottomX,rightBottomY));
		return points;
	}*/

//	public String[] halfPlaneX(int X )
//	{
//		String[] ans=new String[1];
//		ans[0]="Test";//"Number of points right of the line = "+this.tree.numOfPointsInHalfPlaneX(X, true);
//		return ans; 
//	}
	
//	public String[] halfPlaneY(int Y)
//	{
//		String[] ans=new String[1];
//		ans[0]="Test";//"Number of points above the line = "+this.tree.numOfPointsInHalfPlaneY(Y, false);
//		return ans; 
//	}
	
//	public String[] bonusSolution(int leftTopX, int rightBottomX, int leftTopY, int rightBottomY )
//	{
//		return null;
//	}
	
	public void insertDataFromDBFile(String objectName, int objectX, int objectY)
	{
		
	}

}




class PicturePanel extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 8839152089393808414L;
	private BufferedImage image;

    boolean loaded = false;

    int startX, startY;
    int tmpX, tmpY;
    int currX, currY;
    int addX, addY;
    boolean drawingNow = false, drawPoint=false, drawNames=false, drawAverageLine=false;
    GUI myGUI;
	private Point[] points;
	
	//**
	int average;
	final static float dash1[] = {10.0f};

    public PicturePanel(GUI myGUI) {
        this.myGUI = myGUI;
    }

    public void loadPicture(String path) {
        image = getBufferedImage(path, this);
        addMouseListener(this);
        addMouseMotionListener(this);
        loaded = true;
        repaint();
    }

    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        if (!loaded) return;
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        g2d.setPaint(Color.red);
        g2d.setStroke(new BasicStroke(6)); // 6-pixel wide pen
        if (drawingNow && (myGUI.getPointsXreg.isSelected() || myGUI.getPointsXopp.isSelected()))
        {
        	g2d.drawLine(Math.min(startX, tmpX), 0, Math.min(startX, tmpX), 1000);
        	g2d.drawLine(Math.max(startX, tmpX), 0, Math.max(startX, tmpX), 1000);
        	//g2d.drawRect(Math.min(startX, tmpX), Math.min(startY, tmpY), Math.abs(startX - tmpX), Math.abs(startY - tmpY));
        }
//        if (myGUI.halfPlaneX.isSelected())
//        	g2d.drawLine(currX, 0, currX, 1000);
//        if (myGUI.halfPlaneY.isSelected())
//        	g2d.drawLine(0, currY, 1000, currY);
        if (drawPoint && myGUI.addPoint.isSelected())
        {
        	g2d.setPaint(Color.pink);
        	g2d.drawOval(addX, addY, 3, 3);
        }
        if (drawNames && myGUI.closestPoints.isSelected())
        {
        	g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        	g2d.setStroke(new BasicStroke(6));
        	for (int i=0;i<points.length;i++)
        	{
        		g2d.setPaint(Color.green);
        		g2d.drawOval(points[i].getX(), points[i].getY(), 2, 2);
        		g2d.setPaint(Color.GREEN);
        		g2d.drawString(points[i].getName(), points[i].getX(), points[i].getY());
        	}
        }
        if (drawNames && (myGUI.getPointsXreg.isSelected() || myGUI.getPointsXopp.isSelected()))// || myGUI.getPointsYreg.isSelected() || myGUI.getPointsYopp.isSelected()))
        {
        	//g2d.drawRect(Math.min(startX, tmpX), Math.min(startY, tmpY), Math.abs(startX - tmpX), Math.abs(startY - tmpY));
        	g2d.drawLine(Math.min(startX, tmpX), 0, Math.min(startX, tmpX), 1000);
        	g2d.drawLine(Math.max(startX, tmpX), 0, Math.max(startX, tmpX), 1000);
        	g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        	g2d.setStroke(new BasicStroke(6));
        	for (int i=0;i<points.length;i++)
        	{
        		g2d.setPaint(Color.blue);
        		g2d.drawOval(points[i].getX(), points[i].getY(), 2, 2);
        		g2d.setPaint(Color.red);
        		g2d.drawString(""+i, points[i].getX(), points[i].getY());//points[i].getName(), points[i].getX(), points[i].getY());
        		g2d.setPaint(Color.cyan);
        		g2d.drawString(points[i].getName(), points[i].getX(), points[i].getY()+10);//points[i].getName(), points[i].getX(), points[i].getY());
        		
        	}
        }
        /*if (drawAverageLine && myGUI.averagePoints.isSelected())
        {
        	g2d.setPaint(Color.blue);

        	g2d.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f, dash1, 0.0f)); 
        	g2d.drawLine(Math.min(startX, tmpX), average, Math.max(startX, tmpX), average);
        }*/
    }

    public void mousePressed(MouseEvent e) 
    {
        startX = e.getX();
        startY = e.getY();
        drawingNow = true;
    }

    public void mouseReleased(MouseEvent e) 
    {
    	if  (myGUI.getPointsXreg.isSelected() || myGUI.getPointsXopp.isSelected());// || myGUI.getPointsYreg.isSelected() || myGUI.getPointsYopp.isSelected())
    	{
    		System.out.println("startX " + startX);
    		//System.out.println("startY " + startY);
    		System.out.println("tmpX " + tmpX);
    		//System.out.println("tmpY " + tmpY);
    		drawingNow = false;
    		drawNames=false;
    		drawAverageLine=false;
    		/// now we should calc the points that in range
    		//   int (startX, startY) , (tmpX, tmpY);
    		/*if(myGUI.numberOfPoints.isSelected()) 
    			myGUI.updateOutputList(	myGUI.numberOfPoints(Math.min(startX,tmpX),Math.max(startX,tmpX)));//Math.min(startY,tmpY),Math.max(startY,tmpY)));
*/    		if(myGUI.getPointsXreg.isSelected())
    		{
    			points=myGUI.getPointsInRangeReg(Math.min(startX,tmpX),Math.max(startX,tmpX));
    			String[] ans;
    			ans=new String[points.length];
    			for (int i=0; i<points.length; i++)
    			{
    				drawNames=true;
    				ans[i]=points[i].toString();
    			}
    			myGUI.updateOutputList(ans);
    			repaint();
    		}
			if(myGUI.getPointsXopp.isSelected())
			{
				points=myGUI.getPointsInRangeOpp(Math.min(startX,tmpX),Math.max(startX,tmpX));
				String[] ans;
				ans=new String[points.length];
				for (int i=0; i<points.length; i++)
				{
					drawNames=true;
					ans[i]=points[i].toString();
				}
				myGUI.updateOutputList(ans);
				repaint();
			}
    		/*if (myGUI.averagePoints.isSelected())
    		{
    			average=myGUI.averageHeightInRange(Math.min(startX,tmpX),Math.max(startX,tmpX));
    			drawAverageLine=true;
    			String[] ans=new String[1];
    			ans[0]="Average of points in range is "+average;
    			myGUI.updateOutputList(ans);
    			repaint();
    		}*/
//    		if(myGUI.halfPlaneX.isSelected())
//    			myGUI.updateOutputList(myGUI.halfPlaneX(Math.min(startX,tmpX),Math.max(startX,tmpX),
//    					Math.min(startY,tmpY),Math.max(startY,tmpY)));
//    		if(myGUI.halfPlaneY.isSelected())
//    			myGUI.updateOutputList(myGUI.halfPlaneY(Math.min(startX,tmpX),Math.max(startX,tmpX),
//    					Math.min(startY,tmpY),Math.max(startY,tmpY)));
    	}
    }
    public void mouseEntered(MouseEvent e)
    {
    	if(myGUI.addPoint.isSelected()) repaint();
    }
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) 
    {
    	drawAverageLine=false;
    	repaint();
    	if(myGUI.addPoint.isSelected())
    	{
    		addX=currX;
    		addY=currY;
    		    		
    		String name = JOptionPane.showInputDialog(null, "Enter point name : ", "", 1);
    		if (name==null) name="";
    		
    		
    		myGUI.updateOutputList(myGUI.addPoint(name,addX,addY));
			drawPoint=true;
			repaint();
    	}  
    	if (myGUI.closestPoints.isSelected())
    	{
    		drawNames=true;
    		drawPoint=true;
    		points=myGUI.closestPoints();
			String[] ans;
			ans=new String[points.length+1];
			for (int i=0; i<points.length; i++)
			{
				ans[i]=points[i].toString();
			}
			ans[points.length]="Distance is " + Math.sqrt(Math.pow((points[0].getX()-points[1].getX()),2)+Math.pow((points[0].getY()-points[1].getY()),2));
			myGUI.updateOutputList(ans);
			repaint();
    	}
    	/*if(myGUI.medianPoints.isSelected())
    	{   
    		String number;
    		try{
    		number = JOptionPane.showInputDialog(null, "Enter number of median points : ", "", 1);
    		if (number==null) number="";
    		int medianNumber=Integer.parseInt(number);
    		if (medianNumber>0) 
    		{
    			System.out.println("Showing "+medianNumber+" median points");
    			points=myGUI.getMedianPoints(medianNumber);
    			drawNames=true;
    			String[] ans;
    			ans=new String[points.length];
    			for (int i=0; i<points.length; i++)
    			{
    				drawNames=true;
    				ans[i]=points[i].toString();
    			}
    			myGUI.updateOutputList(ans);
    			repaint();
    		}
    		else
    		{
    			System.out.println("That's not a good number...");
    		}
    		} catch(Exception e1)
    		{
    			System.out.println("That's not a number...");
    		}
    	}    */
    }
    public void mouseDragged(MouseEvent e) {drawAverageLine=false;repaint(); if (myGUI.getPointsXreg.isSelected() || myGUI.getPointsXopp.isSelected()){tmpX = e.getX();tmpY = e.getY();}}
    public void mouseMoved(MouseEvent e) 
    {
    	currX = e.getX();
    	currY = e.getY();
    	
//    	if (myGUI.halfPlaneX.isSelected())
//    	{
//    		myGUI.updateOutputList(myGUI.halfPlaneX(currX));
//    		repaint();
//    	}
//    	if (myGUI.halfPlaneY.isSelected())
//    	{
//    		myGUI.updateOutputList(myGUI.halfPlaneY(currY));
//    		repaint();
//    	}
    }
	public static BufferedImage getBufferedImage(String imageFile,Component c) {
        Image image = c.getToolkit().getImage(imageFile);
        waitForImage(image, c);
        BufferedImage bufferedImage =new BufferedImage(image.getWidth(c), image.getHeight(c), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, c);
        return (bufferedImage);
    }
    public static boolean waitForImage(Image image, Component c) {
        MediaTracker tracker = new MediaTracker(c);
        tracker.addImage(image, 0);
        try { tracker.waitForAll();} catch (InterruptedException ie) {}
        return (!tracker.isErrorAny());
    }
    

}


class GUI_loadPictureMenuItem_actionAdapter implements java.awt.event.ActionListener {
    GUI adaptee;

    GUI_loadPictureMenuItem_actionAdapter(GUI adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.loadPictureMenuItem_actionPerformed(e);
    }
}


class GUI_loadTXTMenuItem_actionAdapter implements java.awt.event.ActionListener {
    GUI adaptee;

    GUI_loadTXTMenuItem_actionAdapter(GUI adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.loadTXTMenuItem_actionPerformed(e);
    }
}
    
    
    
    
    
