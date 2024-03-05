import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ObjectOutputStream;

public class TTTFrame extends JFrame implements MouseListener {
    // Display message
    private String text = "";
    // the letter you are playing as
    private char player;
    // stores all the game data
    private GameData gameData = null;
    // output stream to the server
    ObjectOutputStream os;
    BufferedImage buffer = new BufferedImage(600,800,BufferedImage.TYPE_4BYTE_ABGR);
    public TTTFrame(GameData gameData, ObjectOutputStream os, char player)
    {
        super("Chat Room");
        // sets the attributes
        this.gameData = gameData;
        this.os = os;
        this.player = player;

        // adds a KeyListener to the Frame
        addMouseListener(this);

        // makes closing the frame close the program
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set initial frame message
        if(player == 'R')
            text = "Waiting for Black to Connect";

        setSize(600,580);
        setResizable(false);
        setAlwaysOnTop(true);
        setVisible(true);


        WindowListener wl = new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    os.writeObject(new CommandFromClient(CommandFromClient.DISCONNECT, ""));
                    Thread.sleep(1000);
                }
                catch (Exception a){
                    a.printStackTrace();
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        };
        addWindowListener(wl);
    }

    public void paint(Graphics gg)
    {
        Graphics2D g2 = (Graphics2D) buffer.getGraphics();
        g2.setColor(new Color(237, 202, 133));
        g2.fillRect(0,0,getWidth(),getHeight());
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(4));
        g2.drawRect(20,80,getWidth()-40,getHeight()-100);

        // draws the display text to the screen
        g2.setColor(Color.GRAY);
        g2.setFont(new Font("Times New Roman",Font.BOLD,30));
        g2.drawString(text,20,55);

        // draws the circle grid lines to the screen
        int spacing = 75;
        g2.setColor(Color.WHITE);



        gg.drawImage(buffer,0,0,null);
    }


}
