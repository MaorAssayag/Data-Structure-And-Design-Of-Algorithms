import java.math.*;
import java.awt.print.Printable;
import java.lang.reflect.Array;
import java.util.Arrays;
/*** @author's MaorAssayag, RefahelShitrit***/
public class DataStructure implements DT {
	private Container minx;
	private Container maxx;
	private Container miny;
	private Container maxy;
	private Container current; //current point 
	private int size;
	//------------------mission 1 O(1)----------------------//
	//////////////// DON'T DELETE THIS CONSTRUCTOR ////////////////
	public DataStructure() {
		this.minx = null;
		this.maxx = null;
		this.miny = null;
		this.maxy = null;
		this.current = null;
		size = 0;
	}	
//------------------mission 2 O(n) -------------------------------------------------------//
	@Override
	public void addPoint(Point point) {
		if (size==0){
			current = new Container(point,null,null,null,null);
			this.minx=current;
			this.maxx=current;
			this.miny=current;
			this.maxy=current;
			this.size=1;
		}
		else{
			int counter=0;
			Container toAdd;
			//---------------------find the right place in order,axis x---------------//
			this.current = this.minx;
			Point currentData = this.current.getData();
			while (counter<this.size-1 && currentData.getX() < point.getX()){ // counter<=this.size => this.current.getNext()!=null
				this.current = this.current.getNextX();
				currentData = this.current.getData();
				counter++;
			}
			if (currentData.getX() < point.getX()){
				toAdd = new Container(point,this.current,this.current.getNextX(),null,null);
				if (this.current.getNextX()!=null)
					this.current.getNextX().setPrevX(toAdd);
				this.current.setNextX(toAdd);
				counter++; 
			}
			else { 
				toAdd = new Container(point,this.current.getPrevX(),this.current,null,null);
				if (this.current.getPrevX()!=null)
					this.current.getPrevX().setNextX(toAdd);
				this.current.setPrevX(toAdd); 	
			}
	
			if (counter==0)
				this.minx=toAdd;
			if (counter==this.size) 
				this.maxx=toAdd;
			//----------------------find the right place in order,axis y----------------//
			counter=0;
			currentData = this.miny.getData();
			this.current = this.miny;
			while (counter<this.size-1 && currentData.getY() < point.getY()){
				this.current = this.current.getNextY();
				currentData = this.current.getData();
				counter++;
			}
			if (currentData.getY() < point.getY()){
				toAdd.setY(this.current, this.current.getNextY());
				if (this.current.getNextY()!=null)
					this.current.getNextY().setPrevY(toAdd);
				this.current.setNextY(toAdd);
				counter++; 
			}else { 
				toAdd.setY(this.current.getPrevY(), this.current);
				if (this.current.getPrevY()!=null)
					this.current.getPrevY().setNextY(toAdd);
				this.current.setPrevY(toAdd); 
			}
			if (counter==0)
				this.miny=toAdd;
			if (counter==this.size) 
				this.maxy=toAdd;		
			this.size++;	
		}
	}
	
//-----------------------------------------------------------------------------------//	
//------------------mission 3 O(n) -------------------------------------------------------//
	public Point[] getPointsInRangeRegAxis(int min, int max, Boolean axis) {
		int Number = NumInRangeAxis(min, max, axis); //how much points are there ?
		Point[] pointsInRange = new Point[Number];
		if (Number==0) return pointsInRange;
		int i = 0;
		if (axis){
			this.current=this.minx;
			while (i<Number){
				if (this.current.getData().getX()>=min && this.current.getData().getX()<=max){
					pointsInRange[i]=this.current.getData();
					i++;}
				this.current=this.current.getNextX();
			}
		}
		else {
			this.current=this.miny;
			while (i<Number){
				if (this.current.getData().getY()>=min && this.current.getData().getY()<=max){
					pointsInRange[i]=this.current.getData();
					i++;}
				this.current=this.current.getNextY();
			}
		}
		return pointsInRange;
	}
	//---------------------Aid Function - how much points are in the bounds-------------------------------//	
	private int NumInRangeAxis(int min, int max, Boolean axis){
		int counter = 0;
		boolean stop = false;
		if (this.size==0) return 0;
		if (axis){ //axis x
			if (min>this.maxx.getData().getX() || max<this.minx.getData().getX()) stop=true;
			this.current=this.minx;
			while (!stop && this.current!=null){
				if (this.current.getData().getX()>=min && this.current.getData().getX() <=max)
					counter++;
				if (this.current.getData().getX() >= max) stop=true;
				this.current=this.current.getNextX();
			}}
		else { //axis y
			if (min>this.maxy.getData().getY() || max<this.miny.getData().getY()) stop=true;
			this.current=this.miny;
			while (!stop && this.current!=null){
				if (this.current.getData().getY()>=min && this.current.getData().getY() <=max)
					counter++;
				if (this.current.getData().getY() >= max) stop=true;
				this.current=this.current.getNextY();
			}}
		return counter;
	}	
//-----------------------------------------------------------------------------------//	
//------------------mission 4 O(n) - same logic as mission 3-------------------------------------------------------//
	public Point[] getPointsInRangeOppAxis(int min, int max, Boolean axis) {
		int Number = NumInRangeAxis(min, max, axis);
		Point[] pointsInRange = new Point[Number];
		if (Number==0) return pointsInRange;
		int i = 0;
		if (axis){ // sorted by axis y
			this.current=this.miny;
			while (i<Number){
				if (this.current.getData().getX()>=min && this.current.getData().getX() <=max){
					pointsInRange[i]=this.current.getData();
					i++;}
				this.current=this.current.getNextY();
			}	
		}
		else {// sorted by axis x
			this.current=this.minx;
			while (i<Number){
				if (this.current.getData().getY()>=min && this.current.getData().getY() <=max){
					pointsInRange[i]=this.current.getData();
					i++;}
				this.current=this.current.getNextX();
			}
		}
		return pointsInRange;
	}
//-----------------------------------------------------------------------------------//	
//------------------mission 5 O(1)--------------------------------------------------//	
	public double getDensity() {
		double widthX = this.maxx.getData().getX()-this.minx.getData().getX();
		double widthY = this.maxy.getData().getY()-this.miny.getData().getY();
		return (this.size)/(widthX * widthY);
	}
//-----------------------------------------------------------------------------------//	
//---------------mission 6 O(|A|),A is the numbers of points to erase---------------//	
	public void narrowRange(int min, int max, Boolean axis) {
		int counter=0; // how much points we erasing total ?
		boolean erasingAll=false; // if we eventually erasing all the database
		// by axis X-----------------------------------------//
		if (axis){ 
			this.current = this.minx;
			while (!erasingAll && this.current.getData().getX()<min){
				narrowOppPoint(this.current,axis); // deleting this point in y axis
				this.current = this.current.getNextX(); 
				counter++;
				if (this.current==null) //if we get to the edge of the DS
					erasingAll=true;
			}
			if (!erasingAll)
				this.current.setPrevX(null);
			this.minx = this.current; // the actual erasing
			//---//
			this.current = this.maxx;
			while (!erasingAll && this.current.getData().getX() > max){
				narrowOppPoint(this.current,axis);
				this.current = this.current.getPrevX(); 
				counter++;
				if (this.current==null) //if we get to the edge of the DS
					erasingAll=true;
			}
			if (!erasingAll)
				this.current.setNextX(null);
			this.maxx = this.current; //the actual erasing
		}
		// by axis Y -----------------------------------------------------------//
		else{ 
			this.current = this.miny;
			while (!erasingAll && this.current.getData().getY()<min){
				narrowOppPoint(this.current,axis);
				this.current = this.current.getNextY(); 
				counter++;
				if (this.current==null) //if we get to the edge of the DS
					erasingAll=true;
			}
			if (!erasingAll)
				this.current.setPrevY(null);
			this.miny = this.current; //the actual erasing
			//--//
			this.current = this.maxy;
			while (!erasingAll && this.current.getData().getY() > max){
				narrowOppPoint(this.current,axis);
				this.current = this.current.getPrevY(); 
				counter++;
				if (this.current==null) //if we get to the edge of the DS
					erasingAll=true;
			}
			if (!erasingAll)
				this.current.setNextY(null);
			this.maxy = this.current; //the actual erasing
		}
		this.size = this.size - counter; // Update the size of the DS
	}

//---------------------Aid Function-------------------------------//
	private void narrowOppPoint(Container point,boolean axis){
		if (axis){
			if(point.getPrevY()!=null) // delete the point in the y axis
				point.getPrevY().setNextY(point.getNextY());
			if(point.getNextY()!=null)
				point.getNextY().setPrevY(point.getPrevY());
		}
		else {
			if(point.getPrevX()!=null) // delete the point in the x axis
				point.getPrevX().setNextX(point.getNextX());
			if (point.getNextX()!=null)
				point.getNextX().setPrevX(point.getPrevX());
		}
	}
//-----------------------------------------------------------------------------------//	
//---------------mission 7 O(1)-----------------------------------------------------//
	public Boolean getLargestAxis() {
		double widthX = this.maxx.getData().getX()-this.minx.getData().getX();
		double widthY = this.maxy.getData().getY()-this.miny.getData().getY();
		return (widthX > widthY);
	}
//-----------------------------------------------------------------------------------//	
//---------------mission 8 O(n)-----------------------------------------------------//
	public Container getMedian(Boolean axis) {
		int Number = (this.size)/2 ;
		if (axis){
			this.current = this.minx;
			while (Number>0){
				this.current=this.current.getNextX();
				Number--;
			}
		}
		else{
			this.current = this.miny;
			while (Number>0){
				this.current=this.current.getNextY();
				Number--;
			}
		}
		return this.current;
	}
//--------------------------------------------------------------------------------//	
//---------------mission 9 -----------------------------------------------------//	
	public Point[] nearestPairInStrip(Container container, double width, Boolean axis) {
		double leftGap,rightGap;
		int minIndex;
		int numInStrip = 0;
		this.current = container;
		Point currentData = this.current.getData();
		if (axis){ 	//width of the strip by axis
			  leftGap = currentData.getX() + (width/2);
		   	  rightGap = currentData.getX() - (width/2);}
		else{ leftGap = container.getData().getY() + (width/2);
			  rightGap = container.getData().getY() - (width/2);}	
		
		// we count the number of points in the strip with aid function
		Point[] setup = {new Point(0, 0)}; 
		Point[] strip = NumInStripAxis(container,rightGap,leftGap,axis,setup); // return in strip[0].getX() the num of points in the strip
		numInStrip = strip[0].getX(); // number of points in the strip
		minIndex = numInStrip - strip[0].getY(); // index in the array that we will find the min there, later on
		Point[] insert = new Point[numInStrip]; // build array in this size
		// insert the right points to the array
		if (numInStrip>=2){
			insert[0] = new Point(numInStrip, 1); // to insert points to the array we need thus values in [0]
			strip = NumInStripAxis(container,rightGap,leftGap,axis,insert); // O(numInStrip) - the array is not sorted yet
		}else return null; // there is less then 2 points in the strip
		
		// now we sort the "strip" array	
		if (numInStrip*Math.log10(numInStrip)/Math.log10(2)<this.size){// sort by O(numInStrip*log(numInStrip))
			if (axis) {Arrays.sort(strip, new CompareY());} // if the strip width is axis x then sort by Y
			else 	  {Arrays.sort(strip, new CompareX());}
		}else{ // sort by O(n)
			int min,max;
			if (axis){//X
				if (numInStrip==this.size){
					min = this.minx.getData().getX();
					max = this.maxx.getData().getX();}
				else{// if we don't have this.size points in our strip
					min = strip[minIndex].getX();
					max = strip[0].getX();} // when we build the array the max point in the axis will be in the 0 index
			}
			else{ //Y
				if (numInStrip==this.size){
					min = this.miny.getData().getY();
					max = this.maxy.getData().getY();}
				else{// if we don't have all the data structure in our strip
					min = strip[minIndex].getY();
					max = strip[0].getY(); // when we build the array the max point in the axis will be in the 0 index
				}
			}
			strip = getPointsInRangeOppAxis(min, max, axis); // O(n) - to sorted this "strip" array
		}
		// now we want to know which of the points is the nearest points in the "strip" array
		if (strip.length>=2) return nearestPointInArray(strip);
		else return null;
	}
	
	//---------------------Aid Function calculate the nearest point O(sizeOftheArray) ,min sizeOftheArray=2 -------------------------------//
	private Point[] nearestPointInArray(Point[] strip){
		int j = 1; // with each point we measure the distance with the following 6 point's
		Point[] currentMinPoints = {strip[0],strip[1]};
		double currentMinDistance = Distance(currentMinPoints[0], currentMinPoints[1]);
		double currentDistance;	
		for (int i=0; i< strip.length; i++){
			while (j<8 && i+j < strip.length){
				currentDistance = Distance(strip[i], strip[i+j]);
				if (currentDistance<currentMinDistance){
					currentMinDistance = currentDistance;
					currentMinPoints[0] = strip[i];
					currentMinPoints[1] = strip[i+j];
				}
				j++;
			}
			j=1;
		}
		return currentMinPoints;
	}
	//---------------------Aid Function calculate the Distance between 2 point O(1)-------------------------------//
	private double Distance(Point first, Point second){ // distance between two points 
		return Math.sqrt(Math.pow(second.getY()-first.getY(),2) + Math.pow(second.getX()-first.getX(),2));
	}
	
	//-------------------Aid Function build an array with the points in the strip O(numInStrip)-------------------------------//	
	private Point[] NumInStripAxis(Container container,double min, double max, Boolean axis, Point[] strip){
		int counter = strip[0].getX();
		int insert = strip[0].getY(); // the parameter tell us if we want to count the number of the points or insert them.
		int minIndex; // for later on, we want to know the index of the min point in the axis
		if (axis){ //axis x
			this.current=container;
			while (this.current!=null && this.current.getData().getX()>min){ 
				if (insert==1){
					strip[counter-1]=this.current.getData();
					counter--;
				}
				else counter++;
				this.current=this.current.getPrevX();
			}
			minIndex=counter;
			this.current=container.getNextX();
			while (this.current!=null && this.current.getData().getX()<max){
				if (insert==1){
					strip [counter-1]=this.current.getData();
					counter--;
					}
				else counter++;
				this.current=this.current.getNextX();
			}
		}
		else { //axis y
			this.current=container;
			while (this.current!=null && this.current.getData().getY()>min){
				if (insert==1){
					strip [counter-1]=this.current.getData();
					counter--;
				}
				else counter++;
				this.current=this.current.getPrevY();
			}
			minIndex=counter;
			this.current=container.getNextY();
			while (this.current!=null && this.current.getData().getY()<max){
				if (insert==1){
					strip [counter-1]=this.current.getData();
					counter--;
				}
				else counter++;
				this.current=this.current.getNextY();
			}
		}
		if (insert!=1) //we count the number of points in the strip and calculate the min index for later
			strip[0]=new Point(counter,minIndex);
		return strip;
	}
	//--------------------------------------------------------------------------------//	
	//---------------mission 10 -----------------------------------------------------//	
		public Point[] nearestPair() {
			Point[] Answer = new Point[2];
			if (this.size<2)	return null; //step 1
			if (this.size==2){
				Answer[0]=this.minx.getData();
				Answer[1]=this.maxx.getData();
				return Answer;			
			}
			double MinDistance=-1; // for sure it will be change in the next section, just for avoid warrning.
			double MinDistanceInStrip=-1;
			Point[] MinPointsLeft = new Point[2];
			Point[] MinPointsRight = new Point[2];
			Point[] MinPointsInStrip = new Point[2]; // around the median
			boolean LargestAxis = getLargestAxis(); //step 2
			Container median = getMedian(LargestAxis); //step 3
			if (LargestAxis){// step 4 - calling the recursive function nearestPairRec
				MinPointsLeft = nearestPairRec(getPointsInRangeRegAxis(this.minx.getData().getX(), median.getData().getX(),LargestAxis), LargestAxis);
				MinPointsRight =nearestPairRec(getPointsInRangeRegAxis(median.getNextX().getData().getX(), this.maxx.getData().getX(),LargestAxis), LargestAxis);
			}
			else{
				MinPointsLeft = nearestPairRec(getPointsInRangeRegAxis(this.miny.getData().getY(), median.getData().getY(),LargestAxis), LargestAxis);
				MinPointsRight =nearestPairRec(getPointsInRangeRegAxis(median.getNextY().getData().getY(), this.maxy.getData().getY(),LargestAxis), LargestAxis);
			}
			//step 5
			if (MinPointsLeft!=null && MinPointsRight!=null){
				if (Distance(MinPointsLeft[0], MinPointsLeft[1]) > Distance(MinPointsRight[0], MinPointsRight[1])){
					MinDistance = Distance(MinPointsRight[0], MinPointsRight[1]);
					Answer = MinPointsRight;
				}else{
					MinDistance = Distance(MinPointsLeft[0], MinPointsLeft[1]);
					Answer = MinPointsLeft;
				}
			}
			else if (MinPointsLeft!=null) {
				MinDistance = Distance(MinPointsLeft[0], MinPointsLeft[1]);
				Answer = MinPointsLeft;
			}
			else if (MinPointsRight!=null){
				MinDistance = Distance(MinPointsRight[0], MinPointsRight[1]);
				Answer = MinPointsRight;
			}
			//step 6 - nearest point around the median
			if (MinDistance==-1) MinDistance=0;
			MinPointsInStrip = nearestPairInStrip(median, MinDistance*2, LargestAxis);
			if (MinPointsInStrip != null){
				MinDistanceInStrip = Distance(MinPointsInStrip[0], MinPointsInStrip[1]);
				if (MinDistanceInStrip < MinDistance) return MinPointsInStrip;
			}
			return Answer;
		}
		//------------------------Aid Function Recursive -------------------//
		private Point[] nearestPairRec(Point[] range, boolean axis) {
			Point[] Answer = new Point[2];
			if (range.length < 4) return nearestPair3Points(range);
			Point[] MinPointsLeft = new Point[2];
			Point[] MinPointsRight = new Point[2];
			Point[] MinPointsInStrip = new Point[2];
			double MinDistance = -1; //it will be change for sure, because we pass the array only if it containes 4 points and above.
			double MinDistanceInStrip;
			//step 4
			if (axis){
				MinPointsLeft = nearestPairRec(getPointsInRangeRegAxis(range[0].getX(), range[(range.length)/2].getX(), axis), axis);
				MinPointsRight =nearestPairRec(getPointsInRangeRegAxis(range[((range.length)/2)+1].getX(), range[range.length-1].getX() ,axis), axis);
			}
			else{
				MinPointsLeft = nearestPairRec(getPointsInRangeRegAxis(range[0].getY(), range[(range.length)/2].getY(), axis), axis);
				MinPointsRight =nearestPairRec(getPointsInRangeRegAxis(range[((range.length)/2)+1].getY(), range[range.length-1].getY() ,axis), axis);
			}
			//step 5
			if (MinPointsLeft!=null && MinPointsRight!=null){
				if (Distance(MinPointsLeft[0], MinPointsLeft[1]) > Distance(MinPointsRight[0], MinPointsRight[1])){
					MinDistance = Distance(MinPointsRight[0], MinPointsRight[1]);
					Answer = MinPointsRight;
				}else{
					MinDistance = Distance(MinPointsLeft[0], MinPointsLeft[1]);
					Answer = MinPointsLeft;
				}
			}else if (MinPointsLeft!=null && MinPointsRight==null) {
				MinDistance = Distance(MinPointsLeft[0], MinPointsLeft[1]);
				Answer = MinPointsLeft;
			}else if (MinPointsRight!=null && MinPointsLeft==null){
				MinDistance = Distance(MinPointsRight[0], MinPointsRight[1]);
				Answer = MinPointsRight;
			}
			//step 6 find the nearest point around the median
			int upper;
			int lower;
			if (MinDistance==-1) MinDistance = 0;
			if (axis){
				upper = (int) (range[(range.length)/2].getX()+MinDistance);
				lower = (int) (range[(range.length)/2].getX()-MinDistance);
			}
			else{
				upper = (int) (range[(range.length)/2].getY()+MinDistance);
				lower = (int) (range[(range.length)/2].getY()-MinDistance);
			}
			range = getPointsInRangeOppAxis(lower, upper, axis);
			if (range.length>=2) MinPointsInStrip = nearestPointInArray(range);
			if (MinPointsInStrip[0]!=null){
				MinDistanceInStrip = Distance(MinPointsInStrip[0], MinPointsInStrip[1]);
				if (MinDistanceInStrip < MinDistance) return MinPointsInStrip;
			}
			return Answer;
		}		
//------------------------Aid Function return the nearest points in max 3 point-------------------//
		private Point[] nearestPair3Points(Point[] range) {
			if (range.length < 2)	return null; 
			if (range.length == 2)	return range; 
			//else - its mean that we have 3 points in the array
			Point[] Answer = new Point[2];
			double currentMinDistance = Distance(range[0], range[1]);
			Answer[0]= range[0];
			Answer[1]= range[1];
			if (Distance(range[0], range[2]) < currentMinDistance){
				currentMinDistance = Distance(range[0], range[2]);
				Answer[0]= range[0];
				Answer[1]= range[2];
			}
			if (Distance(range[1], range[2]) < currentMinDistance){
				Answer[0]= range[1];
				Answer[1]= range[2];
			}
			return Answer;
		}	
	///End of class
}