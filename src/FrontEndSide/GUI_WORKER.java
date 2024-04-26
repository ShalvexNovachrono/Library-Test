package FrontEndSide;

import BackEndSide.organiser.Book;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

class Front_End_Element_Code {

    static Color FrameBackGroundColour = new Color(255, 255, 255);
    static Color ButtonBackGroundColour = new Color(5, 74, 140);
    static Color ButtonForeGroundColour = new Color(255, 255, 255);
    static Color SelectedObjectColour = new Color(255, 209, 209);
    static Color HoverObjectColour = new Color(235, 213, 213);
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
        temp_input.setFont(new java.awt.Font(FrameDefaultFontName, Font.BOLD, 14));
        if ((Width != 0 && Height != 0) && (column == 0))
            temp_input.setBounds(x, y, Width, Height );
        return temp_input;
    }

    public static JLabel Create_Label(int x, int y, String text, int Width, int Height) {
        JLabel temp_label = new JLabel(text);
        temp_label.setForeground(Color.darkGray);
        temp_label.setFont(new java.awt.Font(FrameDefaultFontName, Font.BOLD, 14));
        if (Width != 0 && Height != 0)
            temp_label.setBounds(x, y, Width, Height );
        return temp_label;
    }

    @SuppressWarnings("rawtypes")
    public static JList Create_List_Container(int x, int y, DefaultListModel List_Items, int Width, int Height) {
        @SuppressWarnings("unchecked")
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

/*
 *  
 *                  You use this code to make hover effect and shit ok ? yes (forced)
 * 
 */
// class MyMouseListener implements MouseListener {
//     static Boolean isClicked = false;
//     static Boolean isHovering = false;
//     public void mouseClicked(MouseEvent event) {
//         isClicked = true;
//     }
//     public void mouseEntered(MouseEvent event) {
//         isHovering = true;
//     }
//     public void mouseExited(MouseEvent event) {
//         isHovering = false;
//     }
//     public void mousePressed(MouseEvent event) {
//         isClicked = true;
//     }
//     public void mouseReleased(MouseEvent event) {
//         isClicked = false;
//     }
// }


public class GUI_WORKER extends Front_End_Element_Code {
    public static JFrame Main_Frame;
    public static ArrayList<Integer> LastVistPage = new ArrayList<Integer>();
    public static Boolean isLoggedIn = false;
    public static String Username = "";

    static void Take_Me_Back_To_LastPage_That_I_Have_Visited() {
        try {
            int LastInt = LastVistPage.get(LastVistPage.size() - 2);
            LastVistPage.remove(LastVistPage.size() - 2);
            LastVistPage.remove(LastVistPage.size() - 1);
            switch (LastInt) {
                case 1:
                    Panel_Number_1();
                    break;
                case 2:
                    Panel_Number_2();
                    break;
                case 3:
                    Panel_Number_3();
                    break;
            }
        } catch (Exception e) {
            if (LastVistPage.size() > 1)
                LastVistPage.removeAll(LastVistPage);
        }
    }

    public static void Frame(String Title) {
        Main_Frame = Create_Frame(Title, PathDestinationToAssetsFolder + "local_library.png",1000, 600);
    }

    public static void RefreshFrame() {
        Main_Frame.repaint();
        Main_Frame.revalidate();
    }

    // this is the welcome screen
    public static void Panel_Number_1() {
        LastVistPage.add(1);
        Clear_Frame(Main_Frame);

        JPanel Panel1 = Create_Panel(0, 0, Main_Frame.getWidth(), 100);

        JButton LoginButton = Create_Button(Main_Frame.getWidth() - 260, (Panel1.getHeight() / 2) - 25, "Login", 100, 50, 40);

        JButton RegisterButton = Create_Button(Main_Frame.getWidth() - 130, (Panel1.getHeight() / 2) - 25, "Register", 100, 50, 40);


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
                LoginButton.setBounds(Main_Frame.getWidth() - 220, (Panel1.getHeight() / 2) - 25, 75, 50);
                RegisterButton.setBounds(Main_Frame.getWidth() - 130, (Panel1.getHeight() / 2) - 25, 100, 50);
                Panel2.setBounds(0, 100, Main_Frame.getWidth(), Main_Frame.getHeight() - 100);
                label1[0].setBounds((Panel2.getWidth() / 2) - 150, (Panel2.getHeight() / 2)  - 200, 400, 100);
                label2[0].setBounds((Panel2.getWidth() / 2) - 150, (Panel2.getHeight() / 2)  - 125, 400, 100);
                label3[0].setBounds((Panel2.getWidth() / 2) - 150, (Panel2.getHeight() / 2)  - 50, 400, 100);
                label4[0].setBounds((Panel2.getWidth() / 2) - 150, (Panel2.getHeight() / 2), 400, 100);
                Main_Frame.repaint();
            }
        });

        LoginButton.addActionListener(v -> {
            Panel_Number_2();
        });


        RegisterButton.addActionListener(v -> {
            Panel_Number_3();
        });
    }


    // this is the login page, make sure to add the components in it

    public static JTextField Login_Username, Login_Password, Register_Username, Register_Password;

    public static void Panel_Number_2() {
        LastVistPage.add(2);
        Clear_Frame(Main_Frame);

        JPanel Panel1 = Create_Panel(0, 0, Main_Frame.getWidth(), 100);

        JButton RegisterButton = Create_Button(Panel1.getWidth() - 130, (Panel1.getHeight() / 2) - 25, "Register", 100, 50, 40);
        RegisterButton.setRolloverEnabled(false);
        JButton BackButton = Create_Button(Panel1.getWidth() - 260, (Panel1.getHeight() / 2) - 25, "Back", 100, 50, 40);
        BackButton.setRolloverEnabled(false);

        Panel1.add(RegisterButton);
        Panel1.add(BackButton);

        JPanel Panel2 = Create_Panel(0, 100, Main_Frame.getWidth(), Main_Frame.getHeight() - 100);

        final JLabel[] Login_Username_Label = {Create_Label(
                (Panel2.getWidth() / 2) - 100,
                (Panel2.getHeight() / 2) - 225,
                "Username:",
                250,
                50
        )};
        Login_Username_Label[0].setFont(new java.awt.Font(FrameDefaultFontName, Font.BOLD, 14 ));

        Login_Username = Create_TextField(
                (Panel2.getWidth() / 2) - 100,
                (Panel2.getHeight() / 2) - 175,
                0,
                250,
                50
        );

        final JLabel[] Login_Password_Label = {Create_Label(
                (Panel2.getWidth() / 2) - 100,
                (Panel2.getHeight() / 2) - 125,
                "Password",
                250,
                50
        )};

        Login_Password_Label[0].setFont(new java.awt.Font(FrameDefaultFontName, Font.BOLD, 14 ));

        Login_Password = Create_TextField(
                (Panel2.getWidth() / 2) - 100,
                (Panel2.getHeight() / 2) - 75,
                0,
                250,
                50
        );


        JButton SubmitLoginButton = Create_Button(
                (Panel2.getWidth() / 2) - 50,
                (Panel2.getHeight() / 2) + 25,
                "Login",
                100,
                50,
                40
        );

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
                BackButton.setBounds(Panel1.getWidth() - 260, (Panel1.getHeight() / 2) - 25, 100, 50);
                RegisterButton.setBounds(Panel1.getWidth() - 130, (Panel1.getHeight() / 2) - 25, 100, 50);

                Panel2.setBounds(0, 100, Main_Frame.getWidth(), Main_Frame.getHeight() - 100);
                Login_Username_Label[0].setBounds(
                        (Panel2.getWidth() / 2) - 100,
                        (Panel2.getHeight() / 2) - 225,
                        250,
                        50
                );
                Login_Username.setBounds(
                        (Panel2.getWidth() / 2) - 100,
                        (Panel2.getHeight() / 2) - 175,
                        250,
                        50
                );

                Login_Password_Label[0].setBounds(
                        (Panel2.getWidth() / 2) - 100,
                        (Panel2.getHeight() / 2) - 125,
                        250,
                        50
                );
                Login_Password.setBounds(
                        (Panel2.getWidth() / 2) - 100,
                        (Panel2.getHeight() / 2) - 75,
                        250,
                        50
                );

                SubmitLoginButton.setBounds(
                        (Panel2.getWidth() / 2) - 50,
                        (Panel2.getHeight() / 2) + 25,
                        100,
                        50
                );
                Main_Frame.repaint();

            }
        });

        RegisterButton.addActionListener(v -> {
            Panel_Number_3();
        });

        BackButton.addActionListener(v -> {
            Take_Me_Back_To_LastPage_That_I_Have_Visited();
        });

        SubmitLoginButton.addActionListener(v -> {
            LastVistPage.add(LastVistPage.get(LastVistPage.size() - 1));
            BackEndSide.File_Worker.Login(Login_Username.getText(), Login_Password.getText());
        });
    }

    // Register page basically login page
    public static void Panel_Number_3() {
        LastVistPage.add(3);
        Clear_Frame(Main_Frame);

        JPanel Panel1 = Create_Panel(0, 0, Main_Frame.getWidth(), 100);

        JButton BackButton = Create_Button(Panel1.getWidth() - 260, (Panel1.getHeight() / 2) - 25, "Back", 100, 50, 40 );
        BackButton.setRolloverEnabled(false);

        JButton LoginButton = Create_Button(Panel1.getWidth() - 130, (Panel1.getHeight() / 2) - 25, "Login", 100, 50, 40);
        LoginButton.setRolloverEnabled(false);


        Panel1.add(LoginButton);
        Panel1.add(BackButton);

        JPanel Panel2 = Create_Panel(0, 100, Main_Frame.getWidth(), Main_Frame.getHeight() - 100);

        final JLabel[] Register_Username_Label = {Create_Label(
                (Panel2.getWidth() / 2) - 100,
                (Panel2.getHeight() / 2) - 225,
                "Username:",
                250,
                50
        )};
        Register_Username_Label[0].setFont(new java.awt.Font(FrameDefaultFontName, Font.BOLD, 14 ));

        Register_Username = Create_TextField(
                (Panel2.getWidth() / 2) - 100,
                (Panel2.getHeight() / 2) - 175,
                0,
                250,
                50
        );

        final JLabel[] Register_Password_Label = {Create_Label(
                (Panel2.getWidth() / 2) - 100,
                (Panel2.getHeight() / 2) - 125,
                "Password",
                250,
                50
        )};

        Register_Password_Label[0].setFont(new java.awt.Font(FrameDefaultFontName, Font.BOLD, 14 ));

        Register_Password = Create_TextField(
                (Panel2.getWidth() / 2) - 100,
                (Panel2.getHeight() / 2) - 75,
                0,
                250,
                50
        );


        JButton SubmitRegisterButton = Create_Button(
                (Panel2.getWidth() / 2) - 50,
                (Panel2.getHeight() / 2) + 25,
                "Register",
                100,
                50,
                40
        );

        SubmitRegisterButton.setRolloverEnabled(false);

        Panel2.add(Register_Username_Label[0]);
        Panel2.add(Register_Username);
        Panel2.add(Register_Password_Label[0]);
        Panel2.add(Register_Password);

        Panel2.add(SubmitRegisterButton);

        Main_Frame.add(Panel1);
        Main_Frame.add(Panel2);

        Main_Frame.repaint();
        Main_Frame.revalidate();

        Main_Frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                Panel1.setBounds(0, 0, Main_Frame.getWidth(), 100);
                BackButton.setBounds(Panel1.getWidth() - 260, (Panel1.getHeight() / 2) - 25, 100, 50);
                LoginButton.setBounds(Panel1.getWidth() - 130, (Panel1.getHeight() / 2) - 25, 100, 50);


                Panel2.setBounds(0, 100, Main_Frame.getWidth(), Main_Frame.getHeight() - 100);
                Register_Username_Label[0].setBounds(
                        (Panel2.getWidth() / 2) - 100,
                        (Panel2.getHeight() / 2) - 225,
                        250,
                        50
                );
                Register_Username.setBounds(
                        (Panel2.getWidth() / 2) - 100,
                        (Panel2.getHeight() / 2) - 175,
                        250,
                        50
                );

                Register_Password_Label[0].setBounds(
                        (Panel2.getWidth() / 2) - 100,
                        (Panel2.getHeight() / 2) - 125,
                        250,
                        50
                );
                Register_Password.setBounds(
                        (Panel2.getWidth() / 2) - 100,
                        (Panel2.getHeight() / 2) - 75,
                        250,
                        50
                );

                SubmitRegisterButton.setBounds(
                        (Panel2.getWidth() / 2) - 50,
                        (Panel2.getHeight() / 2) + 25,
                        100,
                        50
                );
            }
        });

        LoginButton.addActionListener(v -> {
            Panel_Number_2();
        });

        BackButton.addActionListener(v -> {
            Take_Me_Back_To_LastPage_That_I_Have_Visited();
        });
        
        SubmitRegisterButton.addActionListener(v -> {
            LastVistPage.add(LastVistPage.get(LastVistPage.size() - 1));
            BackEndSide.File_Worker.Register(Register_Username.getText(), Register_Password.getText());
        });
    }

    // Message page

    public static void Panel_Error_Message(String Title, String Message) {
        Clear_Frame(Main_Frame);

        JPanel Panel1 = Create_Panel(0, 0, Main_Frame.getWidth(), 100);

        JButton BackButton = Create_Button(Panel1.getWidth() - 260, (Panel1.getHeight() / 2) - 25, "Back", 100, 50, 40 );
        BackButton.setRolloverEnabled(false);

        Panel1.add(BackButton);

        JPanel Panel2 = Create_Panel(0, 100, Main_Frame.getWidth(), Main_Frame.getHeight() - 100);

        final JLabel[] Error_Title_Label = {Create_Label(
            ((Panel2.getWidth() / 2) - 125),
            ((Panel2.getHeight() / 2) - 25) - 225,
            "Error Title:",
            250,
            50
        )};

        
        Error_Title_Label[0].setFont(new java.awt.Font(FrameDefaultFontName, Font.BOLD, 14 ));
        Error_Title_Label[0].setForeground(new Color(255,51,51));

        final JLabel[] Error_Title_Label_Message = {Create_Label(
            ((Panel2.getWidth() / 2) - 125) + 80,
            ((Panel2.getHeight() / 2) - 25)  - 225,
            Title,
            250,
            50
        )};

        Error_Title_Label_Message[0].setFont(new java.awt.Font(FrameDefaultFontName, Font.BOLD, 14 ));
        Error_Title_Label_Message[0].setForeground(new Color(255,51,51));

        final JLabel[] Error_Message_Label_Message = {Create_Label(
            ((Panel2.getWidth() / 2) - 125),
            ((Panel2.getHeight() / 2) - 25)  - 200,
            Message,
            250,
            50
        )};

        Error_Message_Label_Message[0].setFont(new java.awt.Font(FrameDefaultFontName, Font.BOLD, 14 ));
        Error_Message_Label_Message[0].setForeground(new Color(255,51,51));

        Panel2.add(Error_Title_Label[0]);
        Panel2.add(Error_Title_Label_Message[0]);
        Panel2.add(Error_Message_Label_Message[0]);

        Main_Frame.add(Panel1);
        Main_Frame.add(Panel2);

        Main_Frame.repaint();
        Main_Frame.revalidate();

        Main_Frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                Panel1.setBounds(0, 0, Main_Frame.getWidth(), 100);
                BackButton.setBounds(130, (Panel1.getHeight() / 2) - 25, 100, 50);

                Panel2.setBounds(0, 100, Main_Frame.getWidth(), Main_Frame.getHeight() - 100);
                Error_Title_Label[0].setBounds(
                    ((Panel2.getWidth() / 2) - 125),
                    ((Panel2.getHeight() / 2) - 25)  - 225,
                    250,
                    50
                );

                Error_Title_Label_Message[0].setBounds(
                    ((Panel2.getWidth() / 2) - 125) + 80,
                    ((Panel2.getHeight() / 2) - 25)  - 225,
                    250,
                    50
                );

                Error_Message_Label_Message[0].setBounds(
                    ((Panel2.getWidth() / 2) - 125),
                    ((Panel2.getHeight() / 2) - 25) - 200,
                    250,
                    50
                );
            }
        });

        BackButton.addActionListener(v -> {
            Take_Me_Back_To_LastPage_That_I_Have_Visited();
        });
    }
    public static ArrayList<String> Selected_Book_Covers = new ArrayList<>();
    public static HashMap<String, Color> Book_Cover_Colour_Holder = new HashMap<>();
    public static int[] SelectedObjectCount = {0};

    public static void Panel_Number_4() {
        LastVistPage.add(4);
        Clear_Frame(Main_Frame);

        JButton SelectButton = Create_Button(Main_Frame.getWidth() - 200, Main_Frame.getHeight() - 95, "Select", 150,50, 45);
        SelectButton.setVisible(false);
        Main_Frame.add(SelectButton);

        JPanel Panel1 = Create_Panel(0, 0, Main_Frame.getWidth(), 100);
        
        JTextField SearchInput = new JTextField("", 0);
        SearchInput.setBounds(((Panel1.getWidth() / 2) - 150), ((Panel1.getHeight() / 2) - 25), 200, 50);

        JButton SearchButton = Create_Button((SearchInput.getX() + 225), ((Panel1.getHeight() / 2) - 25), "Search", 100, 50, 40);

        Panel1.add(SearchInput);
        Panel1.add(SearchButton);

        JPanel Panel2 = Create_Panel(0, 100, Main_Frame.getWidth(), Main_Frame.getHeight() - 100);

        int Grid_Count_Col = 0;
        int Grid_Count_Row = 0;

        int Grid_Count = 1;
        Loop:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < Math.round(BackEndSide.organiser.BookShelf_.getAllBooks().size() / 5 + 0.5); j++) {
                if (!(Grid_Count <= BackEndSide.organiser.BookShelf_.getAllBooks().size())) {
                    Grid_Count_Row = j;
                    Grid_Count_Col = i;
                    break Loop;
                } else {
                    Grid_Count++;
                }
            }
        }
        System.out.println("col: " + Grid_Count_Col + " row: " + Grid_Count_Row);


        JPanel This_Book_Shelf = new JPanel();

        GridLayout layout = new GridLayout(0,3);
        layout.setHgap(10);
        layout.setVgap(10);

        This_Book_Shelf.setLayout(layout);

        // Create a JScrollPane and add the containerPanel to it
        JScrollPane Scroll_View = new JScrollPane(This_Book_Shelf);
        Scroll_View.setBounds(0, 0, Panel2.getWidth() - 15, Panel2.getHeight() - 50); // - 15 so the scroll bar can be seen
        // Set scroll bar policies (optional)
        Scroll_View.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        Scroll_View.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Scroll_View.getVerticalScrollBar().setUnitIncrement(16);

        // Add your panels to the container panel
        for (Book book : BackEndSide.organiser.BookShelf_.getAllBooks()) {                    
            JPanel Book_Cover = new JPanel();
            Book_Cover.setPreferredSize(new Dimension(200, 150));
            Book_Cover.setLayout(new GridLayout(0, 2));
            Book_Cover.setAlignmentX(Component.LEFT_ALIGNMENT);
            Book_Cover.setName(Integer.toString(book.getID()));
            
            JPanel Book_Cover_Image_Panel = Create_Panel(0, 0, 50, 50);
            JLabel Image = Create_Label_With_ImageIcon((Book_Cover.getWidth() / 2) + 25, (Book_Cover.getHeight() / 2) + 25, PathDestinationToAssetsFolder + "local_library.png", 100, 100, true);
            Book_Cover_Image_Panel.add(Image);

            JPanel Book_Cover_ = Create_Panel(0, 0, Book_Cover.getWidth(), 100);

            JLabel Title = Create_Label(25, 0, book.getBookName(), Panel2.getWidth(), 25);
            Title.setFont(new java.awt.Font(FrameDefaultFontName, Font.BOLD, 17));

            JLabel Description = Create_Label(25, Title.getX() + 25, book.getBookDescription(), Panel2.getWidth(), 25);

            JLabel Author = Create_Label(25, Description.getX() + 55, book.getAuthor(),  Panel2.getWidth(), 25);

            Book_Cover_.add(Title);
            Book_Cover_.add(Description);
            Book_Cover_.add(Author);

            Book_Cover.add(Book_Cover_Image_Panel);
            Book_Cover.add(Book_Cover_);

            Border blackline = BorderFactory.createLineBorder(Color.black);
            Book_Cover.setBorder(blackline);
        
            Book_Cover.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println(SelectedObjectCount[0] + " " + Book_Cover.getName());
                    if (!Selected_Book_Covers.contains(Book_Cover.getName()) && SelectedObjectCount[0] < 3) {
                        Book_Cover.getComponent(0).setBackground(SelectedObjectColour);
                        Book_Cover.getComponent(1).setBackground(SelectedObjectColour);
                        Selected_Book_Covers.add(Book_Cover.getName());
                        SelectedObjectCount[0]++;
                    } else if (Selected_Book_Covers.contains(Book_Cover.getName())) {
                        Book_Cover.getComponent(0).setBackground(Color.WHITE);
                        Book_Cover.getComponent(1).setBackground(Color.WHITE);
                        Selected_Book_Covers.remove(Book_Cover.getName());
                        SelectedObjectCount[0]--;
                    }
                    if (SelectedObjectCount[0] > 0) {
                        Panel2.setBounds(0, 100, Main_Frame.getWidth(), Main_Frame.getHeight() - 150);
                        SelectButton.setVisible(true);
                    } else {
                        Panel2.setBounds(0, 100, Main_Frame.getWidth(), Main_Frame.getHeight() - 100);
                        SelectButton.setVisible(false);
                    }

                    RefreshFrame();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (Book_Cover_Colour_Holder.get(Book_Cover.getName()) == null && !Selected_Book_Covers.contains(Book_Cover.getName())) {
                        Book_Cover_Colour_Holder.put(Book_Cover.getName(), Book_Cover.getBackground());
                        Book_Cover.getComponent(0).setBackground(HoverObjectColour);
                        Book_Cover.getComponent(1).setBackground(HoverObjectColour);
                    }
                        
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (Book_Cover_Colour_Holder.get(Book_Cover.getName()) != null && !Selected_Book_Covers.contains(Book_Cover.getName())) {
                        Book_Cover.getComponent(0).setBackground(Color.WHITE);
                        Book_Cover.getComponent(1).setBackground(Color.WHITE);
                        Book_Cover_Colour_Holder.clear();
                    }
                }
            });


            // Add the Book_Cover to the This_Book_Shelf
            This_Book_Shelf.add(Book_Cover);


            RefreshFrame();
        }


        Panel2.add(Scroll_View);


        Main_Frame.add(Panel1);
        Main_Frame.add(Panel2);

        RefreshFrame();

        Main_Frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                SelectButton.setBounds(Main_Frame.getWidth() - 200, Main_Frame.getHeight() - 95, 150,50);
                Panel1.setBounds(0, 0, Main_Frame.getWidth(), 100);
                SearchInput.setBounds(
                    ((Panel1.getWidth() / 2) - 150),
                    ((Panel1.getHeight() / 2) - 25),
                    200,
                    50
                );
                SearchButton.setBounds(
                    (SearchInput.getX() + 225),
                    ((Panel1.getHeight() / 2) - 25),
                    200,
                    50
                );
                if (SelectedObjectCount[0] > 0) {
                    Panel2.setBounds(0, 100, Main_Frame.getWidth(), Main_Frame.getHeight() - 150);
                } else {
                    Panel2.setBounds(0, 100, Main_Frame.getWidth(), Main_Frame.getHeight() - 100);
                }
                Scroll_View.setBounds(0, 0, Panel2.getWidth() - 15, Panel2.getHeight() - 50);


                RefreshFrame();
            }
        });


        // remove all the books

        SearchButton.addActionListener(v -> {
            This_Book_Shelf.removeAll();
            String looking_for = SearchInput.getText();
            ArrayList<Integer> IDs = BackEndSide.organiser.BookShelf_.searchBook_getIDs(looking_for);
            for (int i = 0; i < IDs.size(); i++) {
                Book book = BackEndSide.organiser.BookShelf_.getBookByIndex(IDs.get(i));                
                JPanel Book_Cover = new JPanel();
                Book_Cover.setPreferredSize(new Dimension(200, 150));
                Book_Cover.setLayout(new GridLayout(0, 2));
                Book_Cover.setAlignmentX(Component.LEFT_ALIGNMENT);
                Book_Cover.setName(Integer.toString(book.getID()));
                
                JPanel Book_Cover_Image_Panel = Create_Panel(0, 0, 50, 50);
                JLabel Image = Create_Label_With_ImageIcon((Book_Cover.getWidth() / 2) + 25, (Book_Cover.getHeight() / 2) + 25, PathDestinationToAssetsFolder + "local_library.png", 100, 100, true);
                Book_Cover_Image_Panel.add(Image);
    
                JPanel Book_Cover_ = Create_Panel(0, 0, Book_Cover.getWidth(), 100);
    
                JLabel Title = Create_Label(25, 0, book.getBookName(), Panel2.getWidth(), 25);
                Title.setFont(new java.awt.Font(FrameDefaultFontName, Font.BOLD, 17));
    
                JLabel Description = Create_Label(25, Title.getX() + 25, book.getBookDescription(), Panel2.getWidth(), 25);
    
                JLabel Author = Create_Label(25, Description.getX() + 55, book.getAuthor(),  Panel2.getWidth(), 25);
    
                Book_Cover_.add(Title);
                Book_Cover_.add(Description);
                Book_Cover_.add(Author);
    
                Book_Cover.add(Book_Cover_Image_Panel);
                Book_Cover.add(Book_Cover_);
    
                Border blackline = BorderFactory.createLineBorder(Color.black);
                Book_Cover.setBorder(blackline);
            
                Book_Cover.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println(SelectedObjectCount[0] + " " + Book_Cover.getName());
                        if (!Selected_Book_Covers.contains(Book_Cover.getName()) && SelectedObjectCount[0] < 3) {
                            Book_Cover.getComponent(0).setBackground(SelectedObjectColour);
                            Book_Cover.getComponent(1).setBackground(SelectedObjectColour);
                            Selected_Book_Covers.add(Book_Cover.getName());
                            SelectedObjectCount[0]++;
                        } else if (Selected_Book_Covers.contains(Book_Cover.getName())) {
                            Book_Cover.getComponent(0).setBackground(Color.WHITE);
                            Book_Cover.getComponent(1).setBackground(Color.WHITE);
                            Selected_Book_Covers.remove(Book_Cover.getName());
                            SelectedObjectCount[0]--;
                        }
                        if (SelectedObjectCount[0] > 0) {
                            Panel2.setBounds(0, 100, Main_Frame.getWidth(), Main_Frame.getHeight() - 150);
                            SelectButton.setVisible(true);
                            Main_Frame.repaint();
                            Main_Frame.revalidate();
                        } else {
                            Panel2.setBounds(0, 100, Main_Frame.getWidth(), Main_Frame.getHeight() - 100);
                            SelectButton.setVisible(false);
                            Main_Frame.repaint();
                            Main_Frame.revalidate();
                        }
                    }
    
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if (Book_Cover_Colour_Holder.get(Book_Cover.getName()) == null && !Selected_Book_Covers.contains(Book_Cover.getName())) {
                            Book_Cover_Colour_Holder.put(Book_Cover.getName(), Book_Cover.getBackground());
                            Book_Cover.getComponent(0).setBackground(HoverObjectColour);
                            Book_Cover.getComponent(1).setBackground(HoverObjectColour);
                        }
                            
                    }
    
                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (Book_Cover_Colour_Holder.get(Book_Cover.getName()) != null && !Selected_Book_Covers.contains(Book_Cover.getName())) {
                            Book_Cover.getComponent(0).setBackground(Color.WHITE);
                            Book_Cover.getComponent(1).setBackground(Color.WHITE);
                            Book_Cover_Colour_Holder.clear();
                        }
                    }
                });

                if (Selected_Book_Covers.contains(Book_Cover.getName())) {
                    Book_Cover.getComponent(0).setBackground(SelectedObjectColour);
                    Book_Cover.getComponent(1).setBackground(SelectedObjectColour);
                } 
    
    
                // Add the Book_Cover to the This_Book_Shelf
                This_Book_Shelf.add(Book_Cover);


                RefreshFrame();
            }




            RefreshFrame();
        });
    }
}
