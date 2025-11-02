package ds1;

import java.util.Arrays;

public class OverflowHash {
    int primarySize;
    int overflowSize;
    int overflowIdx = 0;
    int[] primaryArea;
    int[] overflowArea;

    public OverflowHash(int primarySize, int overflowSize){
        this.primarySize = primarySize;
        this.overflowSize = overflowSize;
        this.primaryArea = new int[primarySize];
        this.overflowArea = new int[overflowSize];
        Arrays.fill(primaryArea, -1);  //we use -1 standing for emptyness
        Arrays.fill(overflowArea, -1);
    }

    public boolean insert(int key) {
        int idx = hashGeneral(key);
        if (primaryArea[idx] < 0) {
            primaryArea[idx] = key;
            return true;
        } else {
            if (overflowIdx < overflowSize) {
                overflowArea[overflowIdx] = key;
                overflowIdx++;
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean search(int key) {
        int idx = hashGeneral(key);
        if (primaryArea[idx] >= 0 && primaryArea[idx] == key) {
            return true;
        }
        for (int i = 0; i < overflowIdx; i++) {
            if (overflowArea[i] == key) {
                return true;
            }
        }
        return false;
    }

    public boolean delete(int key) {
        int idx = hashGeneral(key);
        if (primaryArea[idx] >= 0 && primaryArea[idx] == key) {
            primaryArea[idx] = -2;
            return true;
        }
        for (int i = 0; i < overflowIdx; i++) {
            if (overflowArea[i] != -1 && overflowArea[i] == key) {
                overflowArea[i] = -2;
                return true;
            }
        }
        return false;
    }

    private int hashGeneral(int key) {
        return key % primarySize;
    }

}
