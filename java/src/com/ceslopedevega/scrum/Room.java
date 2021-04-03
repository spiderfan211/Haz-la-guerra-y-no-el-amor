package com.ceslopedevega.scrum;

public class Room {
	

	private static final String DEFAULT_NAME = "Sala inicial";
	private static final int DEFAULT_INDEX = 0;
	private static final String DEFAULT_DESCRIPTION = "Primera sala del juego";
	private static final int DEFAULT_N = 1;
	private static final int DEFAULT_S = 1;
	private static final int DEFAULT_W = 1;
	private static final int DEFAUL_E = 1;

	
	private String name;
	private int index;
	private String description;
	private int n, s, w, e;
	
	public Room () {
        this( DEFAULT_NAME, DEFAULT_INDEX, DEFAULT_DESCRIPTION, DEFAULT_N, DEFAULT_S, DEFAULT_W, DEFAUL_E);
	}
	
	public Room(String name, int index, String description, int n, int s, int w, int e) {
        this.name = name;
        this.index = index;
        this.description = description;
        this.n = n;
        this.s = s;
        this.w = w;
        this.e = e;
    }

    // norte
    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n; 
    }

    // sur
    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    // este
    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }

    // oeste
    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
