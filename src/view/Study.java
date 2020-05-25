package view;

import domain.Course;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Study extends JFrame {
    /**
     * 视频播放器的窗体 由视频主程序调用
     */

    //定义需要的各种组件
    private JPanel contentPane; //顶层容器，整个播放页面的容器
    private JMenuBar menuBar;   //菜单栏
    private JMenu mnFile,mnSetting,mnHelp;  //文件菜单
    private JMenuItem mnOpenVideo,mnExit;   //文件菜单子目录，打开视屏、退出
    private JPanel panel;   //控制区域容器
    private JProgressBar progress;  //进度条
    private JPanel progressPanel;   //进度条容器
    private JPanel controlPanel;    //控制按钮容器
    private JButton btnStop,btnPlay,btnPause;   //控制按钮，停止、播放、暂停
    private JSlider slider;     //声音控制块
    private JList list;

    EmbeddedMediaPlayerComponent playerComponent;   //媒体播放器组件


    /**
     * Create the frame.
     */
    public Study(Course course) {

        //组件的初始化操作
        setTitle("三月慕课播放器");
        setSize(1024,768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        contentPane=new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0,0));
        setContentPane(contentPane);

        /*视屏窗口中播放界面部分*/
        JPanel videoPane=new JPanel();
        contentPane.add(videoPane, BorderLayout.CENTER);
        videoPane.setLayout(new BorderLayout(0,0));

        playerComponent=new EmbeddedMediaPlayerComponent();
        videoPane.add(playerComponent);

        /*视屏窗口中控制部分*/

        panel=new JPanel();     //实例化控制区域容器
        videoPane.add(panel,BorderLayout.SOUTH);

        progressPanel=new JPanel(); //实例化进度条容器
        panel.add(progressPanel, BorderLayout.NORTH);

        //添加进度条
        progress=new JProgressBar();
        progressPanel.add(progress);
        progress.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){     //点击进度条调整视屏播放进度
                int x=e.getX();
                PlayerMain.jumpTo((float)x/progress.getWidth());
            }
        });
        progress.setStringPainted(true);


        controlPanel=new JPanel();      //实例化控制按钮容器
        panel.add(controlPanel,BorderLayout.SOUTH);

        //添加停止按钮
        btnStop=new JButton("停止");
        btnStop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                PlayerMain.stop();
            }
        });
        controlPanel.add(btnStop);

        //添加播放按钮
        btnPlay=new JButton("播放");
        btnPlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                PlayerMain.play();
            }
        });
        controlPanel.add(btnPlay);

        //添加暂停按钮
        btnPause=new JButton("暂停");
        btnPause.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                PlayerMain.pause();
            }
        });
        controlPanel.add(btnPause);

        //添加声音控制块
        slider=new JSlider();
        slider.setValue(80);
        slider.setMaximum(100);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                PlayerMain.setVol(slider.getValue());
            }
        });
        controlPanel.add(slider);

        list = new JList();
        DefaultListModel<String> model = new DefaultListModel();
        for (int i=1;i<=course.getCtime();i++){
            model.addElement("第"+i+"课");
        }
        list.setModel(model);
        videoPane.add(list, BorderLayout.EAST);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickTimes = e.getClickCount();
                if (clickTimes == 2) {
                    String s = course.getCpath() + "\\" + String.format("%02d", list.getSelectedIndex()+1) + ".mp4";
                    PlayerMain.openVideo(s);
                }
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                PlayerMain.stop();
            }
        });
        setVisible(true);

    }

    //获取播放媒体实例（某个视频）
    public EmbeddedMediaPlayer getMediaPlayer() {
        return playerComponent.getMediaPlayer();
    }

    //获取进度条实例
    public JProgressBar getProgressBar() {
        return progress;
    }

}