//////////////// DON'T CHANGE THIS FILE ////////////////

public interface DT 
{
	
	//O(n)
	void addPoint(Point point); 
	
	//O(n)
	Point[] getPointsInRangeRegAxis(int min, int max, Boolean axis);
	
	//O(n)
	Point[] getPointsInRangeOppAxis(int min, int max, Boolean axis);

	//O(1)
	double getDensity();

	//O(|A|) - |A| is the number of points that will be deleted from the data structure
	void narrowRange(int min, int max, Boolean axis);
	
	//O(1)
	Boolean	getLargestAxis();	

	//O(n)
	Container getMedian(Boolean axis);
	
	//O(|B|) - B is the number of points in the strip
	Point[]	nearestPairInStrip(Container container, double width, Boolean axis);

	//??? - you need to compute the running time of this funtion.
	Point[]	nearestPair();
}