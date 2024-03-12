//Stephanie Deng periods 3-4
import java.util.ArrayList;
public class HappyFarm {
    private Plot[][] farmPlots;

    public HappyFarm(Plot[][] p) {
        farmPlots = p;
    }

    public Plot[][] getFarmPlots() {
        return farmPlots;
    }

    /** Returns the plot with the highest yield
     * for a given crop type, as described in part (a).
     */
    public Plot getTopYield(String c) {
        int count = 0;
        int record = 0;
        int ind = 0;
        int row = 0;
        int col = 0;
        Plot[] temp = new Plot[farmPlots.length * farmPlots[0].length];
        for (int i = 0; i < farmPlots.length; i++) {
            for (int j = 0; j < farmPlots[0].length; j++) {
                if (farmPlots[i][j].equals(c)) {
                    temp[count] = farmPlots[i][j];
                    count++;
                }
            }
        }
        if (count == 0) {
            return null;
        } else {
            for (int i = 0; i < temp.length; i++) {
                if (temp[i].equals(c) && temp[i].getCropYield() > record) {
                    record = temp[i].getCropYield();
                    ind = i;
                }
            }
            for (int i = 0; i < farmPlots.length; i++) {
                for (int j = 0; j < farmPlots[0].length; j++) {
                    if (farmPlots[i][j].getCropType().equals(temp[ind].getCropType()) && farmPlots[i][j].getCropYield() == record) {
                        row = i;
                        col = j;
                    }
                }
            }
        }
        return farmPlots[row][col];
    }

    /** Returns true if all plots in a given column in
     * the two-dimensional array farmPlots
     * contain the same type of crop, or false otherwise,
     * as described in part (b).
     */
    public boolean identicalCrop(int col) {
        for (int i = 0; i < farmPlots.length; i++) {
            for (int j = 0; j < col; j++) {
                if (!(farmPlots[i][j].getCropType().equals(farmPlots[0][col].getCropType()))) {
                    return true;
                }
            }
        }
        return false;
    }

    /** Returns an arraylist of Plots
     * that are growing the type of crop indicated by
     * the parameter crop, as described in part (c).
     */
    public ArrayList<Plot> plotsContainingCrop(String crop) {
        ArrayList<Plot> cropPlots = new ArrayList<Plot>();
        for (int i = 0; i < farmPlots.length; i++) {
            for (int j = 0; j < farmPlots[0].length; j++) {
                if (farmPlots[i][j].getCropType().equals(crop)) {
                    cropPlots.add(farmPlots[i][j]);
                }
            }
        }
        return cropPlots;
    }

    /** Updates farmPlots to be a new 2D array composed of
     * its current plots, as well as the Plots in newPlots
     * added as a new column on the right, as described in part (d)
     *
     * THIS METHOD MODIFIES farmPlots
     */
    public void widenFarm(Plot[] newPlots) {
        if (newPlots.length > farmPlots.length) {
            Plot[][] temp = new Plot[farmPlots.length + (newPlots.length - farmPlots.length)][farmPlots[0].length];
            for (int i = 0; i < farmPlots.length; i++) {
                for (int j = 0; j < farmPlots[0].length; j++) {
                    temp[i][j] = farmPlots[i][j];
                }
            }
            for (int k = 0; k < temp.length; k++) {
                temp[k][temp[0].length] = newPlots[k];
            }
            for (int l = 0; l < temp.length; l++) {
                for (int m = 0; m < temp[0].length; m++) {
                    if (temp[l][m] == null) {
                        temp[l][m] = new Plot("soil", 0);
                    }
                }
            }
        }
    }
}
