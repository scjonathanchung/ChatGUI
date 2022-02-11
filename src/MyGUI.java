import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyGUI {

    public static JTextField textField;
    public static JTextPane TextPane;
    public static JScrollPane scrollPane;
    public static JPanel panel;
    public final static String newline = "\n";


    public static void main(String[] args) {

        SimpleAttributeSet attrset = new SimpleAttributeSet();//修改字体大小和颜色，JTextArea不具备这个功能
        StyleConstants.setFontSize(attrset,12);//所以改成JTextPane
        StyleConstants.setForeground(attrset, new Color(175,177,179));

        JFrame myJFrame = new JFrame("ChatBox");
        myJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭界面程序结束运行
        UIManager.put("JFrame.activeTitleBackground", Color.red);

        textField = new JTextField();
        textField.setBounds(0,375,400,25);
        textField.setBackground(new Color(60,63,65));
        textField.setForeground(new Color(175,177,179));
        textField.setBorder(new LineBorder(new Color(60,63,65))); //边框颜色
        textField.setCaretColor(new Color(175,177,179));//设置插入符号（光标）颜色

        TextPane=new JTextPane();
        TextPane.setSize(400,375);
        TextPane.setBackground(new Color(43,43,43));
        TextPane.setBorder(new LineBorder(new Color(60,63,65)));
//        TextPane.setEditable(false);

        scrollPane = new JScrollPane(TextPane);
        scrollPane.setBounds(0,0,400,377); //超出frame就不会显示进度条，更美观
        scrollPane.setBackground(new Color(60,63,65));
        scrollPane.setBorder(new LineBorder(new Color(60,63,65)));

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                Document docs = TextPane.getDocument();
                try {
                    docs.insertString(docs.getLength(), text+"\n", attrset);//getLength()是为了选择插入的位置
                }
                catch(BadLocationException e2){
                    e2.printStackTrace();
                }
                textField.setText(""); //回车之后清空输入栏
            }
        });

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height;

        myJFrame.add(textField);
        myJFrame.add(scrollPane);
        myJFrame.setSize(400,438);
        myJFrame.getContentPane().setBackground(new Color(60,63,65));
        myJFrame.setResizable(false); //框架不允许缩放
        myJFrame.setLocation((screenWidth-400)/2,(screenHeight-438)/2);
        myJFrame.setLayout(null);
        myJFrame.setVisible(true);

    }
}
