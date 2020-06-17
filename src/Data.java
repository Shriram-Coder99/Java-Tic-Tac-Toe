
import java.io.Serializable;

public class Data implements Serializable {

    int lx, ly, fx, fy, sx, sy;
    char[] pieces = new char[9];

    public Data(int lx, int ly, char[] pieces, int fx, int fy, int sx, int sy) {
        this.lx = lx;
        this.ly = ly;
        for (int i = 0; i <= 8; i++) {
            this.pieces[i] = pieces[i];
        }
    }
}
