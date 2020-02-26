package model.data_structures;

public class Queue<T extends Comparable<T>> implements IQueue<T>{

	private DataNode<T> front;
	
	private DataNode<T> back;
	
	public void enqueue(T nodeInfo) {
		
		DataNode<T> dataNode = new DataNode<T>(nodeInfo);
		
		if( front == null ){
			front = dataNode;
		}
		else if( back == null ){
			back = dataNode;
			front.setNext(back);
		}
		else{
			back.setNext(dataNode);
			back = dataNode;
		}
		
	}

	
	public T dequeue() {
		
		DataNode<T> nodeToDequeue = front;
		
		if( nodeToDequeue != null )
			front = nodeToDequeue.getNext();
		
		return nodeToDequeue.getNodeInfo();
	}

	
	public int size() {
		
		DataNode<T> actualNode = front;
		int size = 0;
		
		if( actualNode != null ){
			size++;
		
			while(actualNode.getNext() != null ){
				size++;
				actualNode = actualNode.getNext();
			}
		}
		
		return size;
	}

	
	public boolean isEmpty() {
		
		return front == null ? true : false;
	}

	
	public T getFront() {
		
		return front.getNodeInfo();
	}
	
	public T getBack(){
		return back.getNodeInfo();
	}

}
