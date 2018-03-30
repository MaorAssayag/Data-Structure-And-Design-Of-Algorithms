import java.nio.channels.NetworkChannel;
import java.util.ArrayList;
import java.util.Iterator;

import javax.sound.sampled.DataLine;
import javax.swing.text.html.BlockView;
import javax.xml.crypto.Data;

//SUBMIT
public class BNode implements BNodeInterface {

	// ///////////////////BEGIN DO NOT CHANGE ///////////////////
	// ///////////////////BEGIN DO NOT CHANGE ///////////////////
	// ///////////////////BEGIN DO NOT CHANGE ///////////////////
	private final int t;
	private int numOfBlocks;
	private boolean isLeaf;
	private ArrayList<Block> blocksList;
	private ArrayList<BNode> childrenList;

	/**
	 * Constructor for creating a node with a single child.<br>
	 * Useful for creating a new root.
	 */
	public BNode(int t, BNode firstChild) {
		this(t, false, 0);
		this.childrenList.add(firstChild);
	}

	/**
	 * Constructor for creating a <b>leaf</b> node with a single block.
	 */
	public BNode(int t, Block firstBlock) {
		this(t, true, 1);
		this.blocksList.add(firstBlock);
	}

	public BNode(int t, boolean isLeaf, int numOfBlocks) {
		this.t = t;
		this.isLeaf = isLeaf;
		this.numOfBlocks = numOfBlocks;
		this.blocksList = new ArrayList<Block>();
		this.childrenList = new ArrayList<BNode>();
	}

	// For testing purposes.
	public BNode(int t, int numOfBlocks, boolean isLeaf,
			ArrayList<Block> blocksList, ArrayList<BNode> childrenList) {
		this.t = t;
		this.numOfBlocks = numOfBlocks;
		this.isLeaf = isLeaf;
		this.blocksList = blocksList;
		this.childrenList = childrenList;
	}

	@Override
	public int getT() {
		return t;
	}

	@Override
	public int getNumOfBlocks() {
		return numOfBlocks;
	}

	@Override
	public boolean isLeaf() {
		return isLeaf;
	}

	@Override
	public ArrayList<Block> getBlocksList() {
		return blocksList;
	}

	@Override
	public ArrayList<BNode> getChildrenList() {
		return childrenList;
	}

	@Override
	public boolean isFull() {
		return numOfBlocks == 2 * t - 1;
	}

	@Override
	public boolean isMinSize() {
		return numOfBlocks == t - 1;
	}
	
	@Override
	public boolean isEmpty() {
		return numOfBlocks == 0;
	}
	
	@Override
	public int getBlockKeyAt(int indx) {
		return blocksList.get(indx).getKey();
	}
	
	@Override
	public Block getBlockAt(int indx) {
		return blocksList.get(indx);
	}

	@Override
	public BNode getChildAt(int indx) {
		return childrenList.get(indx);
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((blocksList == null) ? 0 : blocksList.hashCode());
		result = prime * result
				+ ((childrenList == null) ? 0 : childrenList.hashCode());
		result = prime * result + (isLeaf ? 1231 : 1237);
		result = prime * result + numOfBlocks;
		result = prime * result + t;
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BNode other = (BNode) obj;
		if (blocksList == null) {
			if (other.blocksList != null)
				return false;
		} else if (!blocksList.equals(other.blocksList))
			return false;
		if (childrenList == null) {
			if (other.childrenList != null)
				return false;
		} else if (!childrenList.equals(other.childrenList))
			return false;
		if (isLeaf != other.isLeaf)
			return false;
		if (numOfBlocks != other.numOfBlocks)
			return false;
		if (t != other.t)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "BNode [t=" + t + ", numOfBlocks=" + numOfBlocks + ", isLeaf="
				+ isLeaf + ", blocksList=" + blocksList + ", childrenList="
				+ childrenList + "]";
	}

	// ///////////////////DO NOT CHANGE END///////////////////
	// ///////////////////DO NOT CHANGE END///////////////////
	// ///////////////////DO NOT CHANGE END///////////////////
	
	
	
	@Override
	public Block search(int key) {
		int i;
		for ( i = 0; i <numOfBlocks&&blocksList.get(i).getKey()<key; i++);
		if(i <numOfBlocks&&blocksList.get(i).getKey()==key)
			return blocksList.get(i);
		else if(isLeaf)
			return null;
		else
			return childrenList.get(i).search(key);//Recursive search
	}

	@Override
	public void insertNonFull(Block d) {
		int i;
		for ( i = numOfBlocks-1; i >= 0&& d.getKey()<blocksList.get(i).getKey(); i--);//found the right index
		i++;
		if(isLeaf){
			blocksList.add(i, d);//use Array list function 
			numOfBlocks++;
		}else{
			if(childrenList.get(i).numOfBlocks==2*t-1){
				splitChild(i);
				if(d.getKey()>blocksList.get(i).getKey())
					i++;
			}
			childrenList.get(i).insertNonFull(d);
		}
	}

	@Override
	public void delete(int key) {
		boolean found=false;
		int i=0;
		while(i<numOfBlocks&&getBlockAt(i).getKey()<=key&!found){//find the key if its in this node
			found=getBlockAt(i).getKey()==key;
			if(!found)
				i++;
		}
		if(found){
			if (isLeaf) {//case 1 , if the key in a leaf
				blocksList.remove(i);
				numOfBlocks--;	
			}else{
				if(!childrenList.get(i).isMinSize()){//case 2,If the child at index i has at least t elements 
					Block temp = childrenList.get(i).getMaxKeyBlock();//recursively delete the predecessor of key
					delete(temp.getKey());//and put the predecessor instead of key 
					blocksList.remove(i);
					blocksList.add(i,temp);
				}else if(!childrenList.get(i+1).isMinSize()){//case 3 ,If child at index i has t − 1 elements and child at index i+1 has at least t elements
					Block temp = childrenList.get(i+1).getMinKeyBlock();//recursively delete the successor of key
					delete(temp.getKey());//and put the successor instead of key 
					blocksList.remove(i);
					blocksList.add(i,temp);
				}else{
					shiftOrMergeChildIfNeeded(i);//Case 4,If both child at index i has t − 1 elements and child at index i+1 have t − 1 elements
					delete(key);//recursively delete k from the merged node
				}
			}
		}else{
			if(!isLeaf){
				   if(childrenList.get(i).isMinSize()){//Search the key
					   shiftOrMergeChildIfNeeded(i);
					   delete(key);
				   }else{
					   childrenList.get(i).delete(key);
				   }
				}
			
		}
		
	}

	@Override
	public MerkleBNode createHashNode() {
		if(isLeaf){//create hash code with my blocks (b1*b2*b3.....)
			ArrayList<byte[]> dataList = new ArrayList<byte[]>();
			for (int i = 0; i < numOfBlocks; i++) {
				dataList.add(blocksList.get(i).getData());
			}
			return new MerkleBNode(new HashUtils().sha1Hash(dataList));
		}
		ArrayList<MerkleBNode> childrenList2 = new ArrayList<MerkleBNode>();//create Merkle subtree , the hash code with my block and the hash code of my children (b1*hash(c1)*b2*hash(c2).....)
		for (int i = 0; i < childrenList.size(); i++) {
			childrenList2.add(childrenList.get(i).createHashNode());
		}
		int i;
		ArrayList<byte[]> mergelist = new ArrayList<byte[]>();
		for (i = 0; i < numOfBlocks; i++) {
			mergelist.add(childrenList2.get(i).getHashValue());
			mergelist.add(blocksList.get(i).getData());
		}
		mergelist.add(childrenList2.get(i).getHashValue());
		return new  MerkleBNode(new HashUtils().sha1Hash(mergelist),childrenList2);
	}
	/**
	* Splits the child node at childIndex into 2 nodes.
	* @param childIndex
	*/
	//done
	public void splitChild(int childIndex){
			BNode y=childrenList.get(childIndex);// Before entering a node, if the node	has 2t − 1 elements, perform node splitting on the node	in order to reduce the number of elements
			BNode z=new BNode(t, y.isLeaf, t-1);
			for (int i = 0; i < t-1; i++) {//copy y blocks (from index t to 2t-1) to z
				z.blocksList.add(y.blocksList.remove(t));
				y.numOfBlocks--;
				}
			if(!y.isLeaf){//copy y children (from index t to 2t) to z
				for (int i = 0; i < t; i++) {
					z.childrenList.add(y.childrenList.remove(t));
				}
			}
			childrenList.add(childIndex+1, z);//add z to children list
			blocksList.add(childIndex, y.blocksList.remove(t-1));//add block
			y.numOfBlocks--;
			numOfBlocks++;
		
		}
	/**
	* True iff the child node at childIndx-1 exists and has more than t-1 blocks.
	* @param childIndx
	* @return
	*/
	private boolean childHasNonMinimalLeftSibling(int childIndx){
		return (childrenList.size()>=(childIndx))&&(childIndx>0)&&(childrenList.get(childIndx-1).numOfBlocks>t-1);
		}
	/**
	* True iff the child node at childIndx+1 exists and has more than t-1 blocks.
	* @param childIndx
	* @return
	*/
	private boolean childHasNonMinimalRightSibling(int childIndx){
		return (childrenList.size()>=(childIndx+2))&&(childrenList.get(childIndx+1).numOfBlocks>t-1);

		}
	/**
	* Verifies the child node at childIndx has at least t blocks.<br>
	* If necessary a shift or merge is performed.
	*
	* @param childIndxxs
	*/
	private void shiftOrMergeChildIfNeeded(int childIndx){
		if(childrenList.get(childIndx).numOfBlocks < t){
    	   if (childHasNonMinimalLeftSibling(childIndx)){//If child at index childindx+1 has an immediate left sibling and has at least t elements
    		   shiftFromLeftSibling(childIndx);
    	   }
    	   else if (childHasNonMinimalRightSibling(childIndx)){//If child at index childindx has an immediate right sibling and has at least t elements
    		   shiftFromRightSibling(childIndx);
    	   
			}else {
    		   mergeChildWithSibling(childIndx);//merging child at index childindx the parent and child at index childindx+1
    	   }
    	   
		}
       
	}
	/**
	* Add additional block to the child node at childIndx, by shifting from left sibling.
	* @param childIndx
	*/
	private void shiftFromLeftSibling(int childIndx){
		childrenList.get(childIndx).blocksList.add(0,blocksList.remove(childIndx-1));//parent block to min block v
		childrenList.get(childIndx).numOfBlocks++;
		if(!childrenList.get(childIndx-1).isLeaf){
			childrenList.get(childIndx).childrenList.add(0,childrenList.get(childIndx-1).
					childrenList.remove(childrenList.get(childIndx-1).childrenList.size()-1));//u most left child pointer to min pointer v
		}
		blocksList.add(childIndx-1,childrenList.get(childIndx-1)
				.blocksList.remove(childrenList.get(childIndx-1).numOfBlocks-1));//add to parent the max block of u
		childrenList.get(childIndx-1).numOfBlocks--;
	}
	/**
	* Add additional block to the child node at childIndx, by shifting from right sibling.
	* @param childIndx
	*/
	private void shiftFromRightSibling(int childIndx){
		childrenList.get(childIndx).blocksList.add(blocksList.remove(childIndx));//parent block to min block v
		childrenList.get(childIndx).numOfBlocks++;
		if(!childrenList.get(childIndx+1).isLeaf){
			childrenList.get(childIndx).childrenList.add(childrenList.get(childIndx+1).
					childrenList.remove(0));//w most right child pointer to max pointer v
		}
		blocksList.add(childIndx,childrenList.get(childIndx+1)
				.blocksList.remove(0));//add to parent the min block of w
		childrenList.get(childIndx+1).numOfBlocks--;
	
	}
	/**
	* Merges the child node at childIndx with its left or right sibling.
	* @param childIndx
	*/
	private void mergeChildWithSibling(int childIndx){
		if (childIndx!=0){ //it has a left sibiling
			childrenList.get(childIndx).blocksList.add(0,blocksList.remove(childIndx-1));
			numOfBlocks--;
			childrenList.get(childIndx).numOfBlocks++;
			mergeWithLeftSibling(childIndx);	
			
		}
		else{ //it doesnt have a left sibiling
			childrenList.get(childIndx).blocksList.add(blocksList.remove(childIndx));
			numOfBlocks--;
			childrenList.get(childIndx).numOfBlocks++;
			mergeWithRightSibling(childIndx);
			
		}
		if(numOfBlocks==0){
			blocksList=childrenList.get(0).blocksList;
			numOfBlocks=blocksList.size();
			childrenList=childrenList.get(0).childrenList;
			if(childrenList==null || childrenList.size()==0){
				isLeaf=true;
			}
		}
	}
	/**
	* Merges the child node at childIndx with its left sibling.<br>
	* The left sibling node is removed.
	* @param childIndx
	*/
	private void mergeWithLeftSibling(int childIndx){
	  for (int i =childrenList.get(childIndx-1).numOfBlocks-1 ; i>=0; i--) {//copy blocks from the left sibling 
		  childrenList.get(childIndx).blocksList.add(0,childrenList.get(childIndx-1).getBlockAt(i));
		  childrenList.get(childIndx).numOfBlocks++;
	  }
	  for (int i = childrenList.get(childIndx-1).childrenList.size()-1
			  ; i >=0; i--) {//copy children from the left sibling 
		  childrenList.get(childIndx).childrenList.
		  add(0,childrenList.get(childIndx-1).childrenList.get(i));
	  }  
	  childrenList.remove(childIndx-1);
	}
	/**
	* Merges the child node at childIndx with its right sibling.<br>
	* The right sibling node is removed.
	* @param childIndx
	*/
	private void mergeWithRightSibling(int childIndx){
		 for (int i =0 ; i<=childrenList.get(childIndx+1).numOfBlocks-1; i++) {//copy blocks from the right sibling 
			  childrenList.get(childIndx).blocksList.add(childrenList.get(childIndx+1).getBlockAt(i));
			  
			  childrenList.get(childIndx).numOfBlocks++;
		  }
		  for (int i = 0;i <childrenList.get(childIndx+1).childrenList.size(); i++) {//copy children from the right sibling
			  childrenList.get(childIndx).childrenList.
			  add(childrenList.get(childIndx+1).childrenList.get(i));
		  }  
		  childrenList.remove(childIndx+1);
		  }
	/**
	* Finds and returns the block with the min key in the subtree.
	* @return min key block
	*/
	private Block getMinKeyBlock(){
		if(childrenList.size()>0){
			return childrenList.get(0).getMinKeyBlock();
		}else{
			return blocksList.get(0);	
	    }
	}
	/**
	* Finds and returns the block with the max key in the subtree.
	* @return max key block
	*/
	private Block getMaxKeyBlock(){
		if(childrenList.size()>0){
			
			return childrenList.get(childrenList.size()-1).getMaxKeyBlock();
		}else{
		    return blocksList.get(numOfBlocks-1);
		}
		}
	

}
