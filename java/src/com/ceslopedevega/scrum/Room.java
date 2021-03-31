package com.ceslopedevega.scrum;

public class Room {
	
	private static final String DEFAULT_NAME = "Sala inicial";
	private static final String DEFAULT_DESCRIPTION = "Primera sala del juego";
	private static final int DEFAULT_N = 1;
	private static final int DEFAULT_S = 1;
	private static final int DEFAULT_W = 1;
	private static final int DEFAUL_E = 1;

	
	private String name;
	private String description;
	private int n, s, w, e;
	
	public Room () {
		
	}
	
	public Room(String name, String description, int n, int s, int w, int e) {
        this.name = name;
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

    void setW(int w) {
        this.w = w;
    }
	
}
