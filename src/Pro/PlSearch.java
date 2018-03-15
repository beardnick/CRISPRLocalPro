package Pro;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlSearch extends GeneSearch{

    public PlSearch(JPanel jpanel , JFrame frame) {
        super(jpanel , frame);
    }

//    @Override
//    public boolean checkData() {
//        return false;
//    }
//
//    @Override
//    public String commandBuilder() {
//        return null;
//    }
//
//    @Override
//    public String[] stopCmdBuilder() {
//        return new String[0];
//    }


    @Override
    public void initData() {
        super.initData();
        getLabelText().setText("PL_search");
    }

    @Override
    public String commandBuilder() {
        setScriptHead("perl PL-search.pl");
        return super.commandBuilder();
    }
}
