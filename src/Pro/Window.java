package Pro;

import Util.CmdHelper;
import Util.MyJButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

interface WindowCallBack{
    public  boolean checkData();
    public  String commandBuilder();
}

/**
 * Created by asus on 2017/12/4.
 */
public class Window {
    private CmdHelper cmdHelper;
    private String[] stopCmd;
    private WindowCallBack callBack;

    public JFrame frame;
    public MyJButton submitBtn = new MyJButton();
    public TextArea info = new TextArea("information" , 10 , 25 , TextArea.SCROLLBARS_VERTICAL_ONLY);
    public TextArea warningText = new TextArea("warning" , 10 , 25 , TextArea.SCROLLBARS_VERTICAL_ONLY);

    public JDialog information;
    public JDialog warning;

    public Window(JFrame frame , String[] stopCmd ){
        this.frame = frame;
        this.cmdHelper = new CmdHelper(info , information);
        this.stopCmd = stopCmd;
        information = new JDialog(frame , "information" , false);
        warning = new JDialog(frame , "warning" , true);
        initWindowView();
        initWindowEvent();
    }

    private void initWindowView(){
        info.setEditable(false);
        info.setFont(R.infoFont);
        info.setColumns(25);
        information.add(info);
        info.setBackground(Color.white);
        information.setSize(1000 , 500);
        information.setResizable(false);
        information.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        warningText.setFont(R.infoFont);
        warning.add(warningText);
        warning.setResizable(false);
        warning.setSize(1000 , 500);
        submitBtn.setIcon(new ImageIcon("src/Resource/submit.png"));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.white);
        frame.setSize(R.frame_width , R.frame_height);
    }
    private void initWindowEvent(){
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(! information.isVisible() && callBack.checkData()){
                    try {
                        information.setVisible(true);
                        cmdHelper.execCmd(callBack.commandBuilder());
                        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                else {
                    if(! callBack.checkData()){
                        warning.setTitle("warning");
                        warning.setVisible(true);
                    }
                }

            }
        });

        information.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm =  JOptionPane.showConfirmDialog(frame ,
                        "do you really want to stop the process ?" , "warning" , JOptionPane.YES_NO_OPTION);
                if(confirm == JOptionPane.YES_OPTION ){
                    try {
                        System.out.println(confirm);
                        cmdHelper.stopCmd(stopCmd);
                        information.dispose();
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        });
    }

    public boolean textFieldEmpty(JTextField text , Color color , String notice  ){
        boolean isEmpty;
        if(text.getText().length() == 0){
            isEmpty =true;
            warningText.append(notice);
            text.setOpaque(true);
            text.setBackground(color);
        }else {
            isEmpty = false;
            text.setBackground(Color.white);
        }
        return isEmpty;
    }

    public void addComp(GridBagConstraints constraints , int x , int y , int gridWidth , int gridHeight , Insets insets){
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridheight = gridHeight;
        constraints.gridwidth = gridWidth;
        constraints.insets = insets;
    }

    public void setCallBack(WindowCallBack callBack){
        this.callBack = callBack;
    }

}
