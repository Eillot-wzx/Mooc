package view;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import domain.Course;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import javax.swing.*;
import java.awt.*;

public class PlayerMain {
	static Study frame;

	/**
	 * 慕课播放器的主函数页面
	 *
	 * @param course
	 */

	public static void Start(Course course) {

		// 环境配置，将vlc sdk导入到

		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "lib"); // 导入的路径是vlc的安装路径
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

		// 创建主程序界面运行窗体
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					frame = new Study(course);
					frame.setVisible(true);
					// frame.getMediaPlayer().playMedia("F:\\Videos\\Let.mp4");
					// //直接播放视屏，参数是视屏文件的绝对路径
					// frame.getMediaPlayer().prepareMedia("F:\\Videos\\Let_It_Go.mp4"); //控制播放视屏
					new SwingWorker<String, Integer>() {

						@Override
						protected String doInBackground() throws Exception {
							// TODO Auto-generated method stub
							while (true) { // 获取视频播放进度并且按百分比显示
								long total = frame.getMediaPlayer().getLength();
								long curr = frame.getMediaPlayer().getTime();
								float percent = (float) curr / total;
								publish((int) (percent * 100));
								Thread.sleep(100);
							}

						}

						protected void process(java.util.List<Integer> chunks) {
							for (int v : chunks) {
								frame.getProgressBar().setValue(v);
							}
						}
					}.execute();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 打开文件
	 *
	 * @param str
	 */
	public static void openVideo(String str) {
		frame.getMediaPlayer().playMedia(str);

	}

	/**
	 * 退出播放
	 */
	public static void exit() {
		frame.getMediaPlayer().release();
		System.exit(0);
	}

	/**
	 * 实现播放按钮的方法
	 */
	public static void play() {
		frame.getMediaPlayer().play();
	}

	/**
	 * 实现暂停按钮的方法
	 */
	public static void pause() {
		frame.getMediaPlayer().pause();
	}

	/**
	 * 实现停止按钮的方法
	 */
	public static void stop() {
		frame.getMediaPlayer().stop();
	}

	/**
	 * 实现点击进度条跳转的方法
	 *
	 * @param to
	 */
	public static void jumpTo(float to) {
		frame.getMediaPlayer().setTime((long) (to * frame.getMediaPlayer().getLength()));
	}

	/**
	 * 实现控制声音的方法
	 *
	 * @param v
	 */
	public static void setVol(int v) {
		frame.getMediaPlayer().setVolume(v);
	}

}