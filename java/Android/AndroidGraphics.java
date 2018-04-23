package Android;

import android.graphics.Canvas;

public class AndroidGraphics {
    private AndroidMain main;
    private Canvas drawCanvas;

    public AndroidGraphics(AndroidMain main)
    {
        this.main=main;
        drawCanvas=main.getUpdateView().canvas;
    }
}
