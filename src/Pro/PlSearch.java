package Pro;

import javax.swing.JFrame;

public class PlSearch extends Window implements WindowCallBack{

    public PlSearch(JFrame frame) {
        super(frame);
    }

    @Override
    public boolean checkData() {
        return false;
    }

    @Override
    public String commandBuilder() {
        return null;
    }

    @Override
    public String[] stopCmdBuilder() {
        return new String[0];
    }
}
