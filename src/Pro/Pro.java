package Pro;


import Util.MyJButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by asus on 2017/11/27.
 */
public class Pro{

    private int currentWindow = 0;

    private JFrame frame = new JFrame();

    private JPanel topPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel rdPanel = new JPanel();
    private JPanel udPanel = new JPanel();
    private JPanel dbSearchPanel = new JPanel();
    private JPanel plSearchPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();

    private MyJButton rdBtn = new MyJButton();
    private MyJButton udBtn = new MyJButton();
    private MyJButton dbSearchBtn = new MyJButton();
    private MyJButton plSearchBtn = new MyJButton();
    private MyJButton submitBtn = new MyJButton();
    private JLabel maizegoLabel = new JLabel();
    private JLabel crisprLabel = new JLabel();

    private ReferenceGenome referenceGenome;
    private UserData userData;
    private GeneSearch geneSearch;
    private PlSearch plSearch;

    private GridBagConstraints constraints = new GridBagConstraints();
    private GridBagLayout layout = new GridBagLayout();

    private JPanel mainPanel;

    private JLabel title = new JLabel("CRISPR-Local");
    private JLabel viceTitle = new JLabel("A local tool for high-throughput CRISPR single-guide RNA (sgRNA) design in plants.");
    private MyJButton helpBtn = new MyJButton();
    private TextArea helpText = new TextArea("help text" , 10 , 25 , TextArea.SCROLLBARS_VERTICAL_ONLY);
    private JDialog help = new JDialog(frame , "help text" , true);


    public void initView(){
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        rdBtn.setIcon(new ImageIcon("src/Resource/R.png"));
        udBtn.setIcon(new ImageIcon("src/Resource/U.png"));
        dbSearchBtn.setIcon(new ImageIcon("src/Resource/D.png"));
        plSearchBtn.setIcon(new ImageIcon("src/Resource/P.png"));
        helpBtn.setIcon(new ImageIcon("src/Resource/help.png"));
        submitBtn.setIcon(new ImageIcon("src/Resource/submit.png"));
        maizegoLabel.setIcon(new ImageIcon("src/Resource/maizego_logo.png"));
        crisprLabel.setIcon(new ImageIcon("src/Resource/CRISPR-P.png"));


        title.setFont(R.titleFont);
        title.setFont(R.titleFont);
        viceTitle.setFont(R.viceTitleFont);

        helpText.setFont(R.infoFont);
        help.add(helpText);
        help.setResizable(false);
        help.setSize(1000 , 500);

        referenceGenome = new ReferenceGenome(rdPanel, frame);
        userData = new UserData(udPanel, frame);
        geneSearch = new GeneSearch(dbSearchPanel, frame);
        plSearch = new PlSearch(plSearchPanel , frame);
        referenceGenome.initView();
        userData.initView();
        geneSearch.initView();
        plSearch.initView();
        plSearchPanel.setVisible(false);
        udPanel.setVisible(false);
        dbSearchPanel.setVisible(false);
        rdPanel.setVisible(true);
        topPanel.setLayout(layout);

//        constraints.anchor = GridBagConstraints.WEST;
        addComp(constraints , 0 , 0 , 1, 1 , new Insets(10 , 10 , 10 , 100) );
        layout.setConstraints(title  , constraints);
        topPanel.add(title);


        addComp(constraints , 5, 0 , 1 , 1 , new Insets(10 , 100 , 10 , 10));
        layout.setConstraints(helpBtn , constraints);
        topPanel.add(helpBtn);

        addComp(constraints , 0 , 1 , 4 , 1, new Insets(10 , 10 , 10 , 10));
                layout.setConstraints( viceTitle, constraints);
                topPanel.add(viceTitle);

        topPanel.setBackground(Color.white);
        frame.add(topPanel , BorderLayout.NORTH);

        constraints.fill = GridBagConstraints.NONE;

        //setLayout后才会生效
        leftPanel.setLayout(layout);
        leftPanel.setBackground(Color.white);

        addComp(constraints , 0 , 0 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(rdBtn, constraints);
        leftPanel.add(rdBtn);

        addComp(constraints , 0 , 1 , 1 ,1 , new Insets(10 , 10 , 10 , 10));
                layout.setConstraints(udBtn, constraints);
                leftPanel.add(udBtn);

        addComp(constraints , 0 , 2 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(dbSearchBtn, constraints);
        leftPanel.add(dbSearchBtn);

        addComp(constraints ,  0, 3  , 1 ,1 , new Insets(10 , 10 , 10 , 10));
                layout.setConstraints( plSearchBtn, constraints);
                leftPanel.add(plSearchBtn);

//        addComp(constraints ,  0,  4, 1 ,1 , new Insets(200 , 10 , 10 , 10));
//                layout.setConstraints(maizegoLabel, constraints);
//                leftPanel.add(maizegoLabel);


        frame.add(leftPanel , BorderLayout.WEST);

        bottomPanel.setBackground(Color.white);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

        bottomPanel.add(Box.createHorizontalStrut(50));
        bottomPanel.add(crisprLabel);
        bottomPanel.add(Box.createHorizontalStrut(10));
        bottomPanel.add(maizegoLabel);
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(submitBtn);
        bottomPanel.add(Box.createHorizontalStrut(50));

        frame.add(bottomPanel , BorderLayout.SOUTH);

//        addComp(constraints , 0, 0 ,  1, 1, new Insets(10 , 10 , 10 , 10));
//                layout.setConstraints( crisprLabel, constraints);
//                bottomPanel.add(crisprLabel);
//
//        addComp(constraints ,1  , 0 ,  1, 1, new Insets(10 , 10 , 10 , 10));
//                layout.setConstraints( maizegoLabel, constraints);
//                bottomPanel.add(maizegoLabel);
//
//                addComp(constraints , 2 , 0 ,  1, 1, new Insets(10 , 500 , 10 , 10));
//                        layout.setConstraints(submitBtn , constraints);
//                        bottomPanel.add(submitBtn);
//
//        frame.add(bottomPanel , BorderLayout.SOUTH);

        mainPanel = rdPanel;
        frame.add(mainPanel , BorderLayout.CENTER);
        frame.setVisible(true);

    }

    public void initData(){
        referenceGenome.initData();
        geneSearch.initData();
        userData.initData();
        plSearch.initData();
    }

    public void initEvent(){
        maizegoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("http://www.maizego.org/"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });
        crisprLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("http://crispr.hzau.edu.cn/CRISPR2/"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });
        referenceGenome.initEvent();
        geneSearch.initEvent();
        userData.initEvent();
        plSearch.initEvent();

        ArrayList<MyJButton> btns = new ArrayList<>();
        ArrayList<JPanel> jPanels = new ArrayList<>();
        ArrayList<Window> windows = new ArrayList<>();
        btns.add(rdBtn);
        btns.add(udBtn);
        btns.add(dbSearchBtn);
        btns.add(plSearchBtn);

        jPanels.add(rdPanel);
        jPanels.add(udPanel);
        jPanels.add(dbSearchPanel);
        jPanels.add(plSearchPanel);

        windows.add(referenceGenome);
        windows.add(userData);
        windows.add(geneSearch);
        windows.add(plSearch);

        for (int i = 0; i < btns.size(); i++) {
            int finalI = i;
            btns.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.add(topPanel , BorderLayout.NORTH);
                    mainPanel = jPanels.get(finalI);
                    jPanels.get(finalI).setVisible(true);
                    currentWindow = finalI;
                    frame.add(mainPanel , BorderLayout.CENTER);
                    for(int j = 0 ; j < jPanels.size() ; j ++){
                        if(j != finalI){
                            jPanels.get(j).setVisible(false);
                        }
                    }
                }
            });
        }

        helpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader reader;
                    if(System.getProperty("os.name").toLowerCase().startsWith("lin")
                            || System.getProperty("os.name").toLowerCase().startsWith("ubu")){
                        reader = new BufferedReader(new InputStreamReader(
                                new FileInputStream("src/Resource/help.txt") , "GBK"
                        ));
                    }else {
                        reader = new BufferedReader(new InputStreamReader(
                                new FileInputStream("src/Resource/help.txt")
                        ));
                    }
                    String temp = "";
                    helpText.setText("");
                    while ((temp = reader.readLine()) != null ){
                        helpText.append(temp + "\n");
                    }
                    reader.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                help.setTitle("help text");
                help.setVisible(true);
            }
        });

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windows.get(currentWindow).onSubmit();
            }
        });
    }

    private void addComp(GridBagConstraints constraints , int x , int y , int gridWidth , int gridHeight , Insets insets){
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridheight = gridHeight;
        constraints.gridwidth = gridWidth;
        constraints.insets = insets;
    }


    public static void main(String[] args){
        Pro pro = new Pro();
        pro.initView();
        pro.initData();
        pro.initEvent();
    }

}
