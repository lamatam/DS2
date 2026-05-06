package com.myproject.ds_project;

public interface List<T> {
	public boolean empty ();
	public boolean last ();
	public boolean full ();
	public void findFirst ();
	public void findNext ();	
	public T retrieve ();
	public void update (T val);
	public void insert (T val);
        public void insertAtBegin (T val);//**
        public void insertBefore (T val);//**
	public void remove ();
        public int getSize();//**
	}
