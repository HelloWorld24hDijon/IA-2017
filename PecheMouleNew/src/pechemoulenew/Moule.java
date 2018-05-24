package pechemoulenew;

/**
 * ModElise des moules, objets � r�cup�rer
 */
public class Moule extends item {

	private int Points; //points que rapporte la moule au joueur
        
        public Moule(int x, int y, int points){
            super(x, y);
            this.Points = points;
        }

	public int getPoints() {
		return Points;
	}

	/**
	 * 
	 * @param Points
	 */
	public void setPoints(int Points) {
		this.Points = Points;
	}

}