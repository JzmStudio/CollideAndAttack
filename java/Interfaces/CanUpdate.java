package Interfaces;

public interface CanUpdate {
	/**
	 * 每帧回调函数,用于物理更新
	 */
	void update(long deltaMillisecond);
}
