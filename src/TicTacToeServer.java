
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToeServer extends JFrame {

    int ex, ey, lx, ly, fx = 0, fy = 0, sx = 0, sy = 0;
    boolean yourTurn = true;
    ServerSocket serverSocket;
    Socket sock;
    ObjectOutputStream output;
    ObjectInputStream input;
    char[] pieces = new char[9];

    public TicTacToeServer() {
        super("Server Side");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        JPanel panel = new JPanel();

        panel.setBackground(Color.WHITE);
        add(panel);
        repaint();

        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (yourTurn) {
                    if (e.getX() < 200 && e.getY() < 200) {
                        System.out.println("HERE I AM 0");
                        if (pieces[0] == 'X' || pieces[0] == 'O') {
                            showFilledMessage();
                            return;
                        } else {
                            lx = 20;
                            ly = 35;
                            pieces[0] = 'X';
                        }
                    } else if (e.getX() > 200 && e.getX() < 400 && e.getY() < 200) {
                        System.out.println("HERE I AM 1");
                        if (pieces[1] != 'X' && pieces[1] != 'O') {
                            ly = 35;
                            lx = 220;
                            pieces[1] = 'X';
                        } else {
                            showFilledMessage();
                            return;
                        }
                    } else if (e.getX() > 400 && e.getY() < 200) {
                        System.out.println("HERE I AM 2");
                        if (pieces[2] != 'X' && pieces[2] != 'O') {
                            lx = 420;
                            ly = 35;
                            pieces[2] = 'X';
                        } else {
                            showFilledMessage();
                            return;
                        }
                    } else if (e.getX() < 200 && e.getY() > 200 && e.getY() < 400) {
                        System.out.println("HERE I AM 3");
                        if (pieces[3] != 'X' && pieces[3] != 'O') {
                            ly = 220;
                            lx = 20;
                            pieces[3] = 'X';
                        } else {
                            showFilledMessage();
                            return;
                        }
                    } else if (e.getX() > 200 && e.getX() < 400 && e.getY() > 200 && e.getY() < 400) {
                        System.out.println("HERE I AM 4");
                        if (pieces[4] != 'X' && pieces[4] != 'O') {
                            ly = 220;
                            lx = 220;
                            pieces[4] = 'X';
                        } else {
                            showFilledMessage();
                            return;
                        }
                    } else if (e.getX() > 400 && e.getY() > 200 && e.getY() < 400) {
                        System.out.println("HERE I AM 5");
                        if (pieces[5] != 'X' && pieces[5] != 'O') {
                            ly = 220;
                            lx = 420;
                            pieces[5] = 'X';
                        } else {
                            showFilledMessage();
                            return;
                        }
                    } else if (e.getX() < 200 && e.getY() > 400) {
                        System.out.println("HERE I AM 6");
                        if (pieces[6] != 'X' && pieces[6] != 'O') {
                            ly = 420;
                            lx = 20;
                            pieces[6] = 'X';
                        } else {
                            showFilledMessage();
                            return;
                        }
                    } else if (e.getX() > 200 & e.getX() < 400 && e.getY() > 400) {
                        System.out.println("HERE I AM 7");
                        if (pieces[7] != 'X' && pieces[7] != 'O') {
                            ly = 420;
                            lx = 220;
                            pieces[7] = 'X';
                        } else {
                            showFilledMessage();
                            return;
                        }
                    } else if (e.getX() > 400 && e.getY() > 400) {
                        System.out.println("HERE I AM 8");
                        if (pieces[8] != 'X' && pieces[8] != 'O') {
                            ly = 420;
                            lx = 420;
                            pieces[8] = 'X';
                        } else {
                            showFilledMessage();
                            return;
                        }
                    }
                    repaint();
                    Data data = new Data(lx, ly, pieces, fx, fy, sx, sy);
                    try {
                        output.writeObject(data);
                        System.out.println("DATA SENT");
                        yourTurn = false;

                    } catch (IOException ex) {
                        Logger.getLogger(TicTacToeServer.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

            @Override
            public void mousePressed(MouseEvent e
            ) {
            }

            @Override
            public void mouseReleased(MouseEvent e
            ) {
            }

            @Override
            public void mouseEntered(MouseEvent e
            ) {
            }

            @Override
            public void mouseExited(MouseEvent e
            ) {
            }
        });

    }

    public boolean allFilled() {
        for (int i = 0; i <= 8; i++) {
            if (pieces[i] != 'X' && pieces[i] != 'O') {
                return false;
            }
        }
        return true;
    }

    public void showAllFilledMessage() {
        JOptionPane.showMessageDialog(this, "STALEMATE");
    }

    public void showEnemyWonMessage() {
        JOptionPane.showMessageDialog(this, "ENEMY WON");
    }

    public void showYouWinMessage() {
        JOptionPane.showMessageDialog(this, "YOU WON");
    }

    public void showFilledMessage() {
        JOptionPane.showMessageDialog(this, "FILLED");
    }

    public boolean checkForWin() {

        if (pieces[0] == 'X' && pieces[1] == 'X') {
            if (pieces[2] == 'X') {
                return true;
            }
        }

        if (pieces[3] == 'X' && pieces[4] == 'X') {
            if (pieces[5] == 'X') {
                return true;
            }
        }

        if (pieces[6] == 'X' && pieces[7] == 'X') {
            if (pieces[8] == 'X') {
                return true;
            }
        }
        if (pieces[0] == 'X' && pieces[3] == 'X') {
            if (pieces[6] == 'X') {
                return true;
            }
        }

        if (pieces[1] == 'X' && pieces[4] == 'X') {
            if (pieces[7] == 'X') {
                return true;
            }
        }

        if (pieces[2] == 'X' && pieces[5] == 'X') {
            if (pieces[8] == 'X') {
                return true;
            }
        }

        if (pieces[0] == 'X' && pieces[4] == 'X') {
            if (pieces[8] == 'X') {
                return true;
            }
        }

        if (pieces[2] == 'X' && pieces[4] == 'X') {
            if (pieces[6] == 'X') {
                return true;
            }
        }

        return false;
    }

    public boolean checkForEnemyWin() {

        if (pieces[0] == 'O' && pieces[1] == 'O') {
            if (pieces[2] == 'O') {
                return true;
            }
        }

        if (pieces[3] == 'O' && pieces[4] == 'O') {
            if (pieces[5] == 'O') {
                return true;
            }
        }

        if (pieces[6] == 'O' && pieces[7] == 'O') {
            if (pieces[8] == 'O') {
                return true;
            }
        }
        if (pieces[0] == 'O' && pieces[3] == 'O') {
            if (pieces[6] == 'O') {
                return true;
            }
        }

        if (pieces[1] == 'O' && pieces[4] == 'O') {
            if (pieces[7] == 'O') {
                return true;
            }
        }

        if (pieces[2] == 'O' && pieces[5] == 'O') {
            if (pieces[8] == 'O') {
                return true;
            }
        }

        if (pieces[0] == 'O' && pieces[4] == 'O') {
            if (pieces[8] == 'O') {
                return true;
            }
        }

        if (pieces[2] == 'O' && pieces[4] == 'O') {
            if (pieces[6] == 'O') {
                return true;
            }
        }

        return false;
    }

    public void startRunning() {
        while (true) {
            waitForConnection();
            setupStreams();
            receiveInput();
        }
    }

    @Override
    public void paint(Graphics g) {
        try {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawLine(200, 0, 200, 600);
            g2.drawLine(400, 0, 400, 600);
            g2.drawLine(0, 200, 600, 200);
            g2.drawLine(0, 400, 600, 400);

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (ex > 0 && ey > 0) {
                g.drawImage(ImageIO.read(getClass().getResourceAsStream("/redCircle.png")), ex, ey, null);
            }
            if (lx > 0 && ly > 0) {
                g.drawImage(ImageIO.read(getClass().getResourceAsStream("/blueX.png")), lx, ly, null);
            }
            g2.setStroke(new BasicStroke(10));
            g2.drawLine(fx, fy, sx, sy);
            if (checkForWin() == true) {
                showYouWinMessage();
                repaint();
                System.exit(0);
            }

            if (checkForEnemyWin() == true) {
                showEnemyWonMessage();
                repaint();
                System.exit(0);
            }

            if (checkForWin() == false && checkForEnemyWin() == false) {
                if (allFilled() == true) {
                    showAllFilledMessage();
                    System.exit(0);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(TicTacToeServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        TicTacToeServer server = new TicTacToeServer();
        server.startRunning();
    }

    private void waitForConnection() {
        try {
            int port=1234;
            serverSocket = new ServerSocket(port);
            sock = serverSocket.accept();
            System.out.println("CONNECTED FROM:" + sock.getInetAddress());
        } catch (IOException ex) {
            Logger.getLogger(TicTacToeServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setupStreams() {
        try {
            output = new ObjectOutputStream(sock.getOutputStream());
            output.flush();
            input = new ObjectInputStream(sock.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(TicTacToeServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void receiveInput() {
        while (true) {
            try {
                Data data = (Data) input.readObject();
                if (input != null) {
                    this.ex = data.lx;
                    this.ey = data.ly;
                    this.fx = data.fx;
                    this.fy = data.fy;
                    this.sx = data.sx;
                    this.sy = data.sy;
                    for (int i = 0; i <= 8; i++) {
                        this.pieces[i] = data.pieces[i];
                    }
                    repaint();
                    yourTurn = true;
                }
            } catch (IOException ex) {
                Logger.getLogger(TicTacToeServer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TicTacToeServer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
