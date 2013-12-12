import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import author.AuthorView;


public class CreateAuthorViewListener implements ActionListener {

    @Override
    public void actionPerformed (ActionEvent e) {
       new AuthorView();   
    }

}
