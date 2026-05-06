package com.myproject.ds_project;

class Node<T> {
	public T data;
	public Node<T> next;
	
	public Node (T val) {
		data = val;
		next = null;
	}
}

public class LinkedList<T> implements List<T> {
	private Node<T> head;
	private Node<T> current;
        public int size; //**
	
	public LinkedList () {
		head = current = null;
                size =0;//**
	}
	
	public boolean empty () {
		return head == null;
	}
	
	public boolean last () {
		return current.next == null;
	}
	
	public boolean full () {
		return false;
	}
	
	public void findFirst () {
		current = head;
	}
	public void findNext () {
		current = current.next;
	}
	
	public T retrieve () {
		return current.data;
	}
	
	public void update (T val) {
		current.data = val;
	}
	
	public void insert (T val) {
		Node<T> tmp;
		if (empty()) {
			current = head = new Node<T> (val);
		}
		else {
			tmp = current.next;
			current.next = new Node<T> (val);
			current = current.next;
			current.next = tmp;
		}
	}
        ////////////++++++++++++++++++++++++++++
            // add before first node 
    public void insertAtBegin (T val) {
            Node<T> tmp;
            if (empty()) {
                    current = head = new Node<T> (val);
            }
            else {
                    tmp = new Node<T> (val);
                    tmp.next = head;
                    current = head = tmp;
            }
            size ++;
    }

    // before current 
    public void insertBefore (T val) {
            Node<T> tmp;
            if (empty()) {
                    current = head = new Node<T> (val);
            }
            else
            {
                    Node<T> before = head;
                    while (before.next != current)
                        before = before.next;
                    
                    tmp  = new Node<T> (val);
                    tmp.next = current;
                    before.next = tmp;
                    current = tmp;
            }
            size ++;
    }  ////////////////////////++++++++++++++++++++++++++++++
	
	public void remove () {
		if (current == head) {
			head = head.next;
		}
		else {
			Node<T> tmp = head;
			while (tmp.next != current)
				tmp = tmp.next;
			tmp.next = current.next;
		}
		if (current.next == null)
			current = head;
		else
			current = current.next;
	}

    public int getSize()
    {
            return size;
    }
}
