package Util;

import javax.swing.*;
import java.awt.*;

/**
 * Created by asus on 2017/11/4.
 */

public class MyJButton extends JButton {
    private Font font;
    private String str;
    public MyJButton(){
        super();
        setMargin(new Insets(0,0,0,0));//设置边距
        setContentAreaFilled(false);//不绘制按钮区域
        setBorderPainted(false);//不绘制边框
        setIcon(new ImageIcon("2_1.png"));//设置默认图片
    }
}