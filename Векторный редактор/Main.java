






import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

class Figure {
    public static final int LINE_TOOL = 1;
    public static final int ELLIPSE_TOOL = 2;
    public static final int RECT_TOOL = 3;
    public static final int SQ_TOOL = 4;
    public static final int OKR_TOOL = 5;

    public static final int ZAL = 1;
    public static final int CONT = 2;
    public static final int ZAL_CONT = 3;

    public int x1;
    public int y1;
    public int x2;
    public int y2;
    public int fign;
    public Color color;
    public int type;
    public Color colorzal;

    public Figure(int x1, int y1, int x2, int y2, int fign, Color color, int type, Color colorzal) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.fign = fign;
        this.color = color;
        this.type = type;
        this.colorzal = colorzal;
    }

    @Override
    public String toString() {
        return " ";
    }

    public void draw(Graphics g) {
        if (fign == LINE_TOOL) {
            g.setColor(color);
            g.drawLine(x1, y1, x2, y2);
        }
        if (fign == ELLIPSE_TOOL) {
            int xmn = Math.min(x2, x1);
            int ymn = Math.min(y2, y1);
            int w = Math.max(x2, x1) - xmn;
            int h = Math.max(y2, y1) - ymn;
            if(type == ZAL || type == ZAL_CONT) {
                g.setColor(colorzal);
                g.fillOval(xmn, ymn, w, h);
            }if(type == CONT || type == ZAL_CONT) {
                g.setColor(color);
                g.drawOval(xmn, ymn, w, h);
            }

        }
        if (fign == RECT_TOOL) {
            int xmn = Math.min(x2, x1);
            int ymn = Math.min(y2, y1);
            int w = Math.max(x2, x1) - xmn;
            int h = Math.max(y2, y1) - ymn;
            if(type == ZAL || type == ZAL_CONT) {
                g.setColor(colorzal);
                g.fillRect(xmn, ymn, w, h);
            }if(type == CONT || type == ZAL_CONT) {
                g.setColor(color);
                g.drawRect(xmn, ymn, w, h);
            }
        }
        if (fign == OKR_TOOL) {
            int r = (int)Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
            if(type == ZAL || type == ZAL_CONT) {
                g.setColor(colorzal);
                g.fillOval(x1-r, y1-r, 2*r, 2*r);
            }if(type == CONT || type == ZAL_CONT) {
                g.setColor(color);
                g.drawOval(x1-r, y1-r, 2*r, 2*r);
            }
        }
        if (fign == SQ_TOOL) {
            int r = (int)Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
            if(type == ZAL || type == ZAL_CONT) {
                g.setColor(colorzal);
                g.fillRect(x1-r, y1-r, 2*r, 2*r);
            }if(type == CONT || type == ZAL_CONT) {
                g.setColor(color);
                g.drawRect(x1-r, y1-r, 2*r, 2*r);
            }
        }

    }

    public void update(int x, int y) {
        x2 = x;
        y2 = y;
    }
}

class Line {
    public double x1;
    public double y1;
    public double x2;
    public double y2;

    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public String toString() {
        return " ";
    }
}

public class Main extends JFrame {
    // Тут вставляем все "глобальные переменные"
    int ChoiceFigure = 1;
    Color color = new Color(0, 0, 0);
    int type = 2;
    Color colorzal = new Color(0, 0, 0);

    MyPanel panel;

    public Main(String title) {
        super(title);
        setBounds(100, 50, 600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createMenuPoint());
        menuBar.add(createMenuPoint2());
        menuBar.add(createMenuPoint4());
        menuBar.add(createMenuPoint3());
        menuBar.add(createMenuPoint5());

        setJMenuBar(menuBar);
        panel = new MyPanel(true);
        //panel.addMouseListener(panel);
        add(panel);
        setVisible(true);
    }
    private JMenu createMenuPoint5() {

        JMenu menu5 = new JMenu("Цвет заливки...");
        JMenuItem red = new JMenuItem("Красный");
        JMenuItem bl = new JMenuItem("Синий");
        JMenuItem gr = new JMenuItem("Зеленый");
        JMenuItem black = new JMenuItem("Черный");
        menu5.add(red);
        menu5.addSeparator();
        menu5.add(bl);
        menu5.addSeparator();
        menu5.add(gr);
        menu5.addSeparator();
        menu5.add(black);


        JFileChooser fileChooser = new JFileChooser();
        red.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                colorzal = new Color(255, 100, 100);
            }
        });
        bl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                colorzal = new Color(100, 100, 255);
                System.out.println("Changed");
            }
        });
        gr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                colorzal = new Color(100, 255, 100);
            }
        });
        black.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                colorzal = new Color(0, 0, 0);
            }
        });
        return menu5;
    }
    private JMenu createMenuPoint4() {

        JMenu menu4 = new JMenu("Вид...");
        JMenuItem zal = new JMenuItem("Заливка");
        JMenuItem con = new JMenuItem("Контур");
        JMenuItem zalcon = new JMenuItem("Контур и заливка");
        menu4.add(zal);
        menu4.addSeparator();
        menu4.add(con);
        menu4.addSeparator();
        menu4.add(zalcon);


        JFileChooser fileChooser = new JFileChooser();
        zal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                type = 1;
            }
        });
        con.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                type = 2;
            }
        });
        zalcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                type = 3;
            }
        });
        return menu4;
    }

    private JMenu createMenuPoint3() {

        JMenu menu3 = new JMenu("Цвет контура...");
        JMenuItem red = new JMenuItem("Красный");
        JMenuItem bl = new JMenuItem("Синий");
        JMenuItem gr = new JMenuItem("Зеленый");
        JMenuItem black = new JMenuItem("Черный");
        menu3.add(red);
        menu3.addSeparator();
        menu3.add(bl);
        menu3.addSeparator();
        menu3.add(gr);
        menu3.addSeparator();
        menu3.add(black);

        JFileChooser fileChooser = new JFileChooser();
        red.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                color = new Color(255, 0, 0);
            }
        });
        bl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                color = new Color(0, 0, 255);
            }
        });
        gr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                color = new Color(0, 255, 0);
            }
        });
        black.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                color = new Color(0, 0, 0);
            }
        });
        return menu3;
    }
    private JMenu createMenuPoint2() {

        JMenu menu2 = new JMenu("Фигура...");
        JMenuItem line = new JMenuItem("Отрезок");
        JMenuItem rect = new JMenuItem("Прямоугольник");
        JMenuItem ell = new JMenuItem("Эллипс");
        JMenuItem sq = new JMenuItem("Квадрат");
        JMenuItem ocr = new JMenuItem("Окружность");
        menu2.add(line);
        menu2.addSeparator();
        menu2.add(rect);
        menu2.addSeparator();
        menu2.add(ell);
        menu2.addSeparator();
        menu2.add(sq);
        menu2.addSeparator();
        menu2.add(ocr);

        JFileChooser fileChooser = new JFileChooser();
        line.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ChoiceFigure = 1;
                System.out.println(ChoiceFigure);
            }
        });
        rect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ChoiceFigure = 3;
                System.out.println(ChoiceFigure + " * ");
            }
        });
        ell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ChoiceFigure = 2;
                System.out.println(ChoiceFigure + " * ");
            }
        });
        sq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ChoiceFigure = 4;
                System.out.println(ChoiceFigure + " * ");

            }
        });
        ocr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ChoiceFigure = 5;
                System.out.println(ChoiceFigure + " * ");
            }
        });
        return menu2;
    }

    private JMenu createMenuPoint() {

        JMenu menu = new JMenu("Файл");
        JMenuItem open = new JMenuItem("Открыть...");
        JMenuItem save = new JMenuItem("Сохранить как...");
        JMenuItem exit = new JMenuItem("Выход");
        menu.add(open);
        menu.add(save);
        menu.addSeparator();
        menu.add(exit);

        JFileChooser fileChooser = new JFileChooser();
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fileChooser.setDialogTitle("Выбрите файл, который нужно открыть");
                if (fileChooser.showOpenDialog(Main.this) ==
                        JFileChooser.APPROVE_OPTION) {
                    Scanner fin;
                    try {
                        fin = new Scanner(fileChooser.getSelectedFile());
                        panel.kalF = 0;
                        while(fin.hasNextLine()) {
                            String s = fin.nextLine();
                            String[] args = s.split(" ");
                            int x1 = Integer.parseInt(args[0]);
                            int y1 = Integer.parseInt(args[1]);
                            int x2 = Integer.parseInt(args[2]);
                            int y2 = Integer.parseInt(args[3]);
                            int fign = Integer.parseInt(args[4]);
                            panel.alF[panel.kalF++] = new Figure(x1, y1, x2, y2, fign, color, type, colorzal);
                        }
                        fin.close();

                    } catch (FileNotFoundException e) {
                        System.out.println("error");
                    }
                }
                repaint();
                panel.count++;
                System.out.println("Щелкнули по меню Открыть " +
                        panel.count + " раз");
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fileChooser.setDialogTitle("Выбрите папку и имя, куда нужно сохранить");
                if (fileChooser.showSaveDialog(Main.this) ==
                        JFileChooser.APPROVE_OPTION) {
                    System.out.println(fileChooser.getSelectedFile());
                    // Здесь будем открывать файл
                    System.out.println("Выбрали файл " +
                            fileChooser.getSelectedFile());
                    try {
                        PrintStream fout = new PrintStream(fileChooser.getSelectedFile());
                        for (int i = 0 ; i < panel.kalF ; i++) {
                            fout.println(panel.alF[i].x1 + " " + panel.alF[i].y1 + " " +
                                    panel.alF[i].x2 + " " + panel.alF[i].y2 + " " + panel.alF[i].fign +
                                    " " + panel.alF[i].color + " " + panel.alF[i].type + " " + panel.alF[i].colorzal);
                        }
                        fout.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return menu;
    }

    public static void main(String[] args) {
        Main mainWindow = new Main("Заголовок моего окна");
    }

    class MyPanel extends JPanel implements MouseListener, MouseMotionListener {
        public int count = 0;
        private Figure[] alF = new Figure[100];

        private int kalF = 0;
        private int xlinepressed = 0;
        private int ylinepressed = 0;

        public MyPanel(boolean isDoubleBuffered) {
            super(isDoubleBuffered);
            addMouseListener(this);
            addMouseMotionListener(this);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int i = 0; i < kalF; i++) {
                alF[i].draw(g);
            }
            repaint();
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            int x = mouseEvent.getX();
            int y = mouseEvent.getY();
            xlinepressed = x;
            ylinepressed = y;
            alF[kalF] = new Figure(xlinepressed, ylinepressed, x, y, ChoiceFigure, color, type, colorzal);
            kalF++;
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            alF[kalF - 1].update(x, y);
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }
}


//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//class Line
//{
//    private double a;
//    private double b;
//    private double c;
//    public double x1;
//    public double y1;
//    public double x2;
//    public double y2;
//    public Line(double x1, double y1, double x2, double y2) {
//        this.x1 = x1;
//        this.y1 = y1;
//        this.x2 = x2;
//        this.y2 = y2;
//    }
//    @Override
//    public String toString() {
//        return " ";
//    }
//}
//public class Main extends JFrame implements MouseListener {
//    private Point[] points = new Point[1000];
//    private Point[] points2 = new Point[1000];
//    private Point[] points3 = new Point[1000];
//    private Line[] Lines = new Line[1000];
//    private int kPoints = 0;
//    private int kPoints2 = 0;
//    private int kLines = 0;
//    private int xlinepressed = 0;
//    private int ylinepressed = 0;
//    int cx = this.getWidth() / 2;
//    int cy = this.getHeight() / 2;
//    private int ii1, ii2;
//    private double d = 9999;
//    private int jj1, jj2;
//    private double d1 = 0;
//    private int i0, i1;
//    private double m = 9999;
//    private double m1 = 9999;
//    private int xp1, yp1, xp2, yp2;
//    private int xpp1, ypp1, xpp2, ypp2;
//    private double dist = 0;
//
//    private int minlineid = 0;
//    public Main(String title) {
//        super(title);
//        setBounds(200, 150, 600, 400);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        addMouseListener(this);
//        setVisible(true);
//    }
//
//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//        for(int i = 0; i < kLines; i++) {
//            g.drawLine((int)Lines[i].x1, (int)Lines[i].y1, (int)Lines[i].x2, (int)Lines[i].y2);
//        }
//        for (int i = 0; i < kPoints; i++) {
//            g.setColor(Color.YELLOW);
//            g.fillOval(points[i].x - 5, points[i].y - 5, 9, 9);
//            g.setColor(Color.BLACK);
//            g.drawOval(points[i].x - 5, points[i].y - 5, 9, 9);
//        }
//        if (kPoints > 0) {
//            // NUMBER 1
//            g.drawLine(0, 0, points[i0].x, points[i0].y);
//            g.drawOval(points[i0].x - 5, points[i0].y - 5, 9, 9);
//            g.setColor(Color.GREEN);
//            g.fillOval(points[i0].x - 5, points[i0].y - 5, 9, 9);
//            // NUMBER 2
//            g.setColor(Color.BLACK);
//            g.drawLine(cx, cy, points[i1].x, points[i1].y);
//            g.drawOval(points[i1].x - 5, points[i1].y - 5, 9, 9);
//            g.setColor(Color.ORANGE);
//            g.fillOval(points[i1].x - 5, points[i1].y - 5, 9, 9);
//            // NUMBER 3
//            g.setColor(Color.BLACK);
//            g.drawLine(points[ii1].x, points[ii1].y, points[ii2].x, points[ii2].y);
//            g.drawOval(points[ii1].x - 5, points[ii1].y - 5, 9, 9);
//            g.drawOval(points[ii2].x - 5, points[ii2].y - 5, 9, 9);
//            g.setColor(Color.RED);
//            g.fillOval(points[ii1].x - 5, points[ii1].y - 5, 9, 9);
//            g.fillOval(points[ii2].x - 5, points[ii2].y - 5, 9, 9);
//            // NUMBER 4
//            g.setColor(Color.BLACK);
//            g.drawLine(points[jj1].x, points[jj1].y, points[jj2].x, points[jj2].y);
//            g.drawOval(points[jj1].x - 5, points[jj1].y - 5, 9, 9);
//            g.drawOval(points[jj2].x - 5, points[jj2].y - 5, 9, 9);
//            g.setColor(Color.BLUE);
//            g.fillOval(points[jj1].x - 5, points[jj1].y - 5, 9, 9);
//            g.fillOval(points[jj2].x - 5, points[jj2].y - 5, 9, 9);
//            // NUMBER 5
//            g.setColor(Color.BLACK);
//            g.drawLine(xp1, yp1, xp2, yp2);
//            // NUMBER 6
//            g.setColor(Color.RED);
//            g.drawLine(xpp1, ypp1, xpp2, ypp2);
//            // NUMBER 7 (UP)
//            for (int i = 0; i < kPoints; i++) {
//                if(kPoints == kPoints2) {
//                    g.setColor(Color.PINK);
//                    g.drawLine(points[i].x, points[i].y, points2[i].x, points2[i].y);
//                }
//            }
//            // NUMBER 8
//            g.setColor(Color.orange);
//            g.drawLine(Lines[hj].x1, Lines[hj].y1, Lines[hj].x2, Lines[hj].y2);
//        }
//
//
//
//
//    }
//
//    public static void main(String[] args) {
//        Main mainWindow = new Main("+=+=+");
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent mouseEvent) {
//        int x = mouseEvent.getX();
//        int y = mouseEvent.getY();
//        points[kPoints] = new Point(x, y);
//        kPoints++;
//        ii1 = 0;
//        ii2 = 0;
//        d = 9999;
//        xp1 = 0;
//        yp1 = 0;
//        xp2 = 0;
//        yp2 = 0;
//        xpp1 = 0;
//        ypp1 = 0;
//        xpp2 = 0;
//        ypp2 = 0;
//        dist = 0;
//        cx = this.getWidth() / 2;
//        cy = this.getHeight() / 2;
//        for (int i = 0; i < kLines; i++) {
//
//        }
//        for (int i = 0; i < kPoints; i++) {
//            for (int j = 0; j < kPoints; j++) {
//                if (j != i) {
//                    if (Math.sqrt(Math.pow(points[i].x - points[j].x, 2) + Math.pow(points[i].y - points[j].y, 2)) < d) {
//                        d = Math.sqrt(Math.pow(points[i].x - points[j].x, 2) + Math.pow(points[i].y - points[j].y, 2));
//                        ii1 = j;
//                        ii2 = i;
//                    }
//                }
//            }
//            for (int j = i+1; j < kPoints; j++) {
//                /*if (j != i)*/ {
//                    if (Math.sqrt(Math.pow(points[i].x - points[j].x, 2) + Math.pow(points[i].y - points[j].y, 2)) > d1) {
//                        d1 = Math.sqrt(Math.pow(points[i].x - points[j].x, 2) + Math.pow(points[i].y - points[j].y, 2));
//                        jj1 = j;
//                        jj2 = i;
//                    }
//                    double k, b;
//                    if(points[jj2].x == points[jj1].x) {
//                        xpp1 = xp1;
//                        ypp1 = 7;
//                        xpp2 = xp2;
//                        ypp2 = this.getHeight();
//                    }
//                    else {
//                        k = (double) (points[jj1].y - points[jj2].y) / (points[jj1].x - points[jj2].x);
//                        b = (points[jj2].y - (k * points[jj2].x));
//                        xp1 = 7;
//                        yp1 = (int) (k * 7 + b);
//                        xp2 = this.getWidth() - 7;
//                        yp2 = (int) (k * xp2 + b);
//                        // NUMBER 5
//                        if (Math.pow(xp1 - xp2, 2) + Math.pow(yp1 - yp2, 2) > dist) {
//                            dist = Math.pow(xp1 - xp2, 2) + Math.pow(yp1 - yp2, 2);
//                            xpp1 = xp1;
//                            ypp1 = yp1;
//                            xpp2 = xp2;
//                            ypp2 = yp2;
//                        }
//                    }
//                }
//            }
//            if (Math.sqrt(points[i].x * points[i].x + points[i].y * points[i].y) < m) {
//                m = Math.sqrt(points[i].x * points[i].x + points[i].y * points[i].y);
//                i0 = i;
//            }
//            if (Math.sqrt(Math.pow(points[i].x - cx, 2) + Math.pow(points[i].y - cy, 2)) < m1) {
//                m1 = Math.sqrt(Math.pow(points[i].x - cx, 2) + Math.pow(points[i].y - cy, 2));
//                i1= i;
//            }
//        }
//    }
//
//
//    @Override
//    public void mousePressed(MouseEvent mouseEvent) {
//        int x = mouseEvent.getX();
//        int y = mouseEvent.getY();
//        xlinepressed = x;
//        ylinepressed = y;
//        points3[kPoints2] = new Point(x, y);
//        kPoints2++;
//
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent mouseEvent) {
//        int x = mouseEvent.getX();
//        int y = mouseEvent.getY();
//        points2[kLines] = new Point(x, y);
//        Lines[kLines] = new Line(xlinepressed, ylinepressed, x, y);
//        kLines++;
//        repaint();
//
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent mouseEvent) {
//    }
//
//    @Override
//    public void mouseExited(MouseEvent mouseEvent) {
//    }
//}