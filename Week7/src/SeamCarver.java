import java.awt.Color;

import edu.princeton.cs.algs4.Picture;


public class SeamCarver {
	private Picture _picture;
	private double[][] _dualEnergy;
	
	public SeamCarver(Picture picture) {
		if(picture == null) {
			throw new java.lang.IllegalArgumentException();
		}
		
		this._picture = new Picture(picture);
		this._dualEnergy = new double[this._picture.width()][this._picture.height()];
		for(int i = 0; i < this._picture.width(); i++) {
			for(int j = 0; j < this._picture.height(); j++) {
				this._dualEnergy[i][j] = energy(i, j);
			}
		}
		
	}
	
	public Picture Picture() {
		return this._picture; 
		
	}
	
	public int width() {
		return this._picture.width(); 
	}
	
	public int height() {
		return this._picture.height(); 
	}
	
	public double energy(int x, int y) {
		if(x < 0 || x > width() - 1 || y < 0 || y > height() - 1) {
			throw new java.lang.IllegalArgumentException();
		}
		
		if(x == 0 || y == 0 || x == this.width() -1 || y == this.height() - 1) {
			return 1000; 
		}
		
		return Math.sqrt(xEnergy(x, y) + yEnergy(x, y));
	}
	
	private double xEnergy(int x, int y) {
		Color col1 = this._picture.get(x + 1, y);
		Color col2 = this._picture.get(x - 1, y);
		return squareIt(col1, col2);
	}
	
	private double yEnergy(int x, int y) {
		Color col1 = this._picture.get(x, y + 1);
		Color col2 = this._picture.get(x, y - 1);
		return squareIt(col1, col2);
		
	}
	
	private double squareIt(Color col1, Color col2) {
		int redDiff = col1.getRed() - col2.getRed();
		int blueDiff = col1.getBlue() - col2.getBlue();
		int greenDiff = col1.getGreen() - col2.getGreen(); 
		double diffSqua = Math.pow(redDiff, 2) + Math.pow(blueDiff, 2) + Math.pow(greenDiff, 2);
		return diffSqua;
	}
	
	public int[] findHorizontalSeam() {
		return new int[1];
	}
	
	public int[] findVerticalSeam() {
		return new int[1];
	}
	
	public void removeHorizontalSeam(int[] seam) {
		if(seam == null || this.height() <= 1 || seam.length > this.width()) {
			throw new java.lang.IllegalArgumentException();
		}
	}
	
	public void removeVerticalSeam(int[] seam) {
		if(seam == null || this.width() <= 1 || seam.length > this.height()) {
			throw new java.lang.IllegalArgumentException();
		}
	}

}
