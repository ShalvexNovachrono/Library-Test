package FrontEndSide;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

class Front_End_Element_Code {

    static Color FrameBackGroundColour = new Color(255, 255, 255);
    static Color ButtonBackGroundColour = new Color(5, 74, 140);
    static Color ButtonForeGroundColour = new Color(255, 255, 255);
    static String FrameDefaultFontName = "Merriweather";


    static Path CurrentRelativePath = Paths.get("");
    static String CurrentRelativePathString = CurrentRelativePath.toAbsolutePath().toString();
    static String PathDestinationToAssetsFolder = CurrentRelativePathString + "\\src\\Assets\\";
    public static JFrame Create_Frame(String name, String LogoDestination, int width, int height) {
        JFrame frame = new JFrame(name);
        frame.getContentPane().setBackground(FrameBackGroundColour);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        ImageIcon logo = new ImageIcon(LogoDestination);
        frame.setIconImage(logo.getImage());
        frame.setVisible(true);
        frame.setLayout(null);
        return frame;
    }

    public static JPanel Create_Panel(int x, int y, int Width, int Height) {
        JPanel Temp_Panel = new JPanel();
        Temp_Panel.setBounds(x, y, Width, Height);
        Temp_Panel.setBackground(FrameBackGroundColour);
        Temp_Panel.setLayout(null);
        return Temp_Panel;
    }

    public static JButton Create_Button(int x, int y, String text, int Width, int Height, int borderRadius) {
        JButton temp_button = new RoundedButton(borderRadius, text);
        temp_button.setFont(new java.awt.Font(FrameDefaultFontName, Font.BOLD, 14));
        temp_button.setBackground(ButtonBackGroundColour);
        temp_button.setForeground(ButtonForeGroundColour);
        temp_button.setBounds(x, y, Width, Height);
        temp_button.setContentAreaFilled(false);
        temp_button.setBorderPainted(false);
        return temp_button;
    }

    public static JTextField Create_TextField(int x, int y, int column, int Width, int Height) {
        JTextField temp_input = new JTextField(column);
        temp_input.setFont(new java.awt.Font(FrameDefaultFontName, Font.PLAIN, 14));
        if ((Width != 0 && Height != 0) && (column == 0))
            temp_input.setBounds(x, y, Width, Height );
        return temp_input;
    }

    public static JLabel Create_Label(int x, int y, String text, int Width, int Height) {
        JLabel temp_label = new JLabel(text);
        temp_label.setForeground(Color.darkGray);
        temp_label.setFont(new java.awt.Font(FrameDefaultFontName, Font.PLAIN, 11));
        if (Width != 0 && Height != 0)
            temp_label.setBounds(x, y, Width, Height );
        return temp_label;
    }

    public static JList Create_List_Container(int x, int y, DefaultListModel List_Items, int Width, int Height) {
        JList Temp_List = new JList(List_Items);
        Temp_List.setBounds(x, y, Width, Height);
        return Temp_List;
    }

    public static JScrollPane Create_ScrollPane(int x, int y, int Width, int Height) {
        JScrollPane Temp_ScrollPane = new JScrollPane();
        Temp_ScrollPane.setBounds(x, y, Width, Height);
        return Temp_ScrollPane;
    }

    public static ImageIcon Create_Image_Icon(String Image_Destination) {
        ImageIcon Temp_ImageIcon = new ImageIcon(Image_Destination);
        return Temp_ImageIcon;
    }
    public static JLabel Create_Label_With_ImageIcon(int x, int y, String Image_Destination, int Width, int Height, Boolean resizeImage) {
        ImageIcon image = Create_Image_Icon(Image_Destination);
        JLabel temp_label;
        if (resizeImage)
            temp_label = new JLabel(new ImageIcon(image.getImage().getScaledInstance(Width, Height, Image.SCALE_AREA_AVERAGING)));
        else
            temp_label = new JLabel(new ImageIcon(image.getImage().getScaledInstance(25, 25, Image.SCALE_AREA_AVERAGING)));
        if (Width != -1 && Height != -1)
            temp_label.setBounds(x, y, Width, Height);
        else
            temp_label.setBounds(x, y, image.getIconWidth(), image.getIconHeight());
        return temp_label;
    }


    static void Clear_Frame(JFrame Current_Frame) {
        Current_Frame.getContentPane().removeAll();
        Current_Frame.repaint();
    }
}

class RoundedButton extends JButton {

    private int radius;

    public RoundedButton(int radius, String text) {
        super(text);
        this.radius = radius;
        setContentAreaFilled(false); // Required when overriding paintComponent
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Call parent's paintComponent for basic functionality

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground()); // Use button's background color
        g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

        // Draw the text with some adjustment based on insets
        int textX = (getWidth() - getFontMetrics(getFont()).stringWidth(getText())) / 2;
        int textY = (getHeight() + getFontMetrics(getFont()).getAscent()) / 2;
        g2d.setColor(getForeground()); // Use button's foreground color for text
        g2d.drawString(getText(), textX, textY);
    }
}
public class GUI_WORKER extends Front_End_Element_Code {
    public static JFrame Main_Frame;
    public static JButton LoginButton, RegisterButton;
    public ArrayList<String> BookList = new ArrayList<String>(100);

    public static void Frame(String Title) {
        Main_Frame = Create_Frame(Title, PathDestinationToAssetsFolder + "local_library.png",800, 500);
    }

    // this is the welcome screen
    public static void Panel_Number_1() {
        Clear_Frame(Main_Frame);

        JPanel Panel1 = Create_Panel(0, 0, Main_Frame.getWidth(), 100);

        LoginButton = Create_Button(Main_Frame.getWidth() - 220, 5, "Login", 75, 50, 40);

        RegisterButton = Create_Button(Main_Frame.getWidth() - 130, 5, "Register", 100, 50, 40);


        LoginButton.setRolloverEnabled(false);
        RegisterButton.setRolloverEnabled(false);

        Panel1.add(LoginButton);
        Panel1.add(RegisterButton);

        JPanel Panel2 = Create_Panel(0, 100, Main_Frame.getWidth(), Main_Frame.getHeight() - 100);

        final JLabel[] label1 = {Create_Label((Panel2.getWidth() / 2) - 150, (Panel2.getHeight() / 2) - 200, "Book,", 400, 100)};
        label1[0].setFont(new java.awt.Font("League Spartan", Font.BOLD, 75));
        label1[0].setForeground(new Color(0,0,0));

        final JLabel[] label2 = {Create_Label((Panel2.getWidth() / 2) - 150, (Panel2.getHeight() / 2) - 125, "fast", 400, 100)};
        label2[0].setFont(new java.awt.Font("League Spartan", Font.BOLD, 75));
        label2[0].setForeground(new Color(0,0,0));

        final JLabel[] label3 = {Create_Label((Panel2.getWidth() / 2) - 150, (Panel2.getHeight() / 2) - 50, "& read.", 400, 100)};
        label3[0].setFont(new java.awt.Font("League Spartan", Font.BOLD, 75));
        label3[0].setForeground(new Color(0,0,0));

        final JLabel[] label4 = {Create_Label((Panel2.getWidth() / 2) - 150, (Panel2.getHeight() / 2), "Local Library", 400, 100)};
        label4[0].setFont(new java.awt.Font("Merriweather", Font.PLAIN, 15));
        label4[0].setForeground(new Color(0,0,0));

        Panel2.add(label1[0]);
        Panel2.add(label2[0]);
        Panel2.add(label3[0]);
        Panel2.add(label4[0]);

        Main_Frame.add(Panel1);
        Main_Frame.add(Panel2);

        Main_Frame.repaint();
        Main_Frame.revalidate();

        Main_Frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                Panel1.setBounds(0, 0, Main_Frame.getWidth(), 100);
                LoginButton.setBounds(Main_Frame.getWidth() - 220, 5,75, 50);
                RegisterButton.setBounds(Main_Frame.getWidth() - 130, 5, 100, 50);
                Panel2.setBounds(0, 100, Main_Frame.getWidth(), Main_Frame.getHeight() - 100);
                label1[0].setBounds((Panel2.getWidth() / 2) - 150, (Panel2.getHeight() / 2)  - 200, 400, 100);
                label2[0].setBounds((Panel2.getWidth() / 2) - 150, (Panel2.getHeight() / 2)  - 125, 400, 100);
                label3[0].setBounds((Panel2.getWidth() / 2) - 150, (Panel2.getHeight() / 2)  - 50, 400, 100);
                label4[0].setBounds((Panel2.getWidth() / 2) - 150, (Panel2.getHeight() / 2), 400, 100);
                Main_Frame.repaint();
            }
        });
    }


    // this is the login page, make sure to add the components in it

    public static JButton SubmitLoginButton;
    public static JTextField Login_Username, Login_Password;

    public static void Panel_Number_2() {
        Clear_Frame(Main_Frame);

        JPanel Panel1 = Create_Panel(0, 0, Main_Frame.getWidth(), 100);

        RegisterButton = Create_Button(Main_Frame.getWidth() - 130, 5, "Register", 100, 50, 40);
        RegisterButton.setRolloverEnabled(false);

        Panel1.add(RegisterButton);

        JPanel Panel2 = Create_Panel(0, 100, Main_Frame.getWidth(), Main_Frame.getHeight() - 100);

        final JLabel[] Login_Username_Label = {Create_Label(
                (Panel2.getWidth() / 2) - 75,
                (Panel2.getHeight() / 2) - 200,
                "Username",
                150,
                50
        )};
        Login_Username = Create_TextField((Panel2.getWidth() / 2) - 100, (Panel2.getHeight() / 2) - 170,0, 200, 50);

        final JLabel[] Login_Password_Label = {Create_Label(
                (Panel2.getWidth() / 2) - 75,
                (Panel2.getHeight() / 2) - 125,
                "Password",
                150,
                50
        )};
        Login_Password = Create_TextField((Panel2.getWidth() / 2) - 100, (Panel2.getHeight() / 2) - 65,0, 200, 50);

        SubmitLoginButton = Create_Button((Panel2.getWidth() / 2) - 50, (Panel2.getHeight() / 2) + 25, "Login", 100, 50, 40);
        SubmitLoginButton.setRolloverEnabled(false);

        Panel2.add(Login_Username_Label[0]);
        Panel2.add(Login_Username);
        Panel2.add(Login_Password_Label[0]);
        Panel2.add(Login_Password);

        Panel2.add(SubmitLoginButton);

        Main_Frame.add(Panel1);
        Main_Frame.add(Panel2);

        Main_Frame.repaint();
        Main_Frame.revalidate();

        Main_Frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                Panel1.setBounds(0, 0, Main_Frame.getWidth(), 100);
                LoginButton.setBounds(Main_Frame.getWidth() - 220, 5,75, 50);
                RegisterButton.setBounds(Main_Frame.getWidth() - 130, 5, 100, 50);

                Panel2.setBounds(0, 100, Main_Frame.getWidth(), Main_Frame.getHeight() - 100);
                Login_Username_Label[0].setBounds((Panel2.getWidth() / 2) - 75, (Panel2.getHeight() / 2) - 200, 150, 50);
                Login_Username.setBounds((Panel2.getWidth() / 2) - 100, (Panel2.getHeight() / 2) - 170, 200, 50);
                Login_Password_Label[0].setBounds((Panel2.getWidth() / 2) - 75, (Panel2.getHeight() / 2) - 125, 150, 50);
                Login_Password.setBounds((Panel2.getWidth() / 2) - 100, (Panel2.getHeight() / 2) - 65, 200, 50);

                SubmitLoginButton.setBounds((Panel2.getWidth() / 2) - 50, (Panel2.getHeight() / 2) + 25, 100, 50);
                Main_Frame.repaint();

            }
        });
    }
}
