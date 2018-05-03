package Interfaces;

import android.graphics.Canvas;

public interface UpdateView {
    /**
     * 每帧物理更新完调用,用于更新视图
     * @param updateCanvas 用于绘制的Canvas
     */
    void updateView(Canvas updateCanvas);
}
