
//Don't change the class name
public class Container{
	private Point data;//Don't delete or change this field;
	private Container prevX;
	private Container nextX;
	private Container prevY;
	private Container nextY;
	
	//--------------------------------------------------//
	//Don't delete or change this function
	public Point getData(){
		return data;
	}
	public Container getPrevX(){
		return prevX;
	}
	public Container getNextX(){
		return nextX;
	}
	public Container getPrevY(){
		return prevY;
	}
	public Container getNextY(){
		return nextY;
	}
	//--------------------------------------------------//
	public Container(Point data, Container prevX, Container nextX, Container prevY, Container nextY){
		this.data = data;
		this.prevX = prevX;
		this.nextX = nextX;
		this.prevY = prevY;
		this.nextY = nextY;
	}
	public void setPrevX(Container prevX){
		this.prevX = prevX;
	}
	public void setNextX(Container nextX){
		this.nextX = nextX;
	}
	public void setY(Container prevY,Container nextY){
		this.prevY = prevY;
		this.nextY = nextY;
	}
	public void setNextY(Container nextY){
		this.nextY = nextY;
	}
	public void setPrevY(Container prevY){
		this.prevY = prevY;
	}
	//--------------------------------------------------//
}
