import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class SidePanel extends JPanel {
    public SidePanel(JFrame parentFrame, JPanel parentPanel) throws IOException{
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, parentFrame.getWidth(), parentFrame.getHeight());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBounds(80, 0, (parentFrame.getWidth() - 80), parentFrame.getHeight());

        //For sidebar
        JPanel sidePanel = new JPanel();
        sidePanel.setBounds(0, 0, 80, parentFrame.getHeight());
        sidePanel.setBackground(Color.decode("#AEDCEB"));
        sidePanel.setLayout(new GridBagLayout());
        dashboard.setBorder(15, 0, 0, 0, sidePanel);

        JPanel sideOptions = new JPanel();
        sideOptions.setLayout(new BoxLayout(sideOptions, BoxLayout.Y_AXIS));
        sideOptions.setOpaque(false);
        sideOptions.setMaximumSize(new Dimension(Integer.MAX_VALUE, sideOptions.getPreferredSize().height));

        BufferedImage original = ImageIO.read(new File("res/doctorProf.jpg"));
        int diameter = 70;

        BufferedImage rounded = roundImage(original, diameter);

        JLabel profileLabel = new JLabel(new ImageIcon(rounded));

        BufferedImage dashboardImage = ImageIO.read(new File("res/dashboardIcon.png"));
        BufferedImage dashboardResized = resizeImage(dashboardImage, 50, 50);
        JLabel dashboardLabel = new JLabel(new ImageIcon(dashboardResized));

        BufferedImage patientImage = ImageIO.read(new File("res/patientIcon.png"));
        BufferedImage patientResized = resizeImage(patientImage, 50, 50);
        JLabel patientLabel = new JLabel(new ImageIcon(patientResized));

        BufferedImage consultImage = ImageIO.read(new File("res/consultationIcon.png"));
        BufferedImage consultResized = resizeImage(consultImage, 50, 50);
        JLabel consultLabel = new JLabel(new ImageIcon(consultResized));

        BufferedImage appointmentImage = ImageIO.read(new File("res/appointmentIcon.png"));
        BufferedImage appointmentResized = resizeImage(appointmentImage, 50, 50);
        JLabel appointmentLabel = new JLabel(new ImageIcon(appointmentResized));

        JPanel DIContainer = dashboard.sidePanelIconContainers(dashboardLabel);
        JPanel PIContainer = dashboard.sidePanelIconContainers(patientLabel);
        JPanel CIContainer = dashboard.sidePanelIconContainers(consultLabel);
        JPanel AIContainer = dashboard.sidePanelIconContainers(appointmentLabel);

        DIContainer.setOpaque(true);
        DIContainer.setBackground(Color.decode("#56C7D1"));

        dashboard.setBorder(20, 10 , 20, 0, dashboardLabel);
        dashboard.setBorder(20, 10 , 20, 0, patientLabel, consultLabel, appointmentLabel);

        sidePanel.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent e){
                dashboard.expandSidebar(sidePanel, profileLabel, dashboardLabel, patientLabel, consultLabel, appointmentLabel, mainPanel, original);
            }

            public void mouseExited(java.awt.event.MouseEvent e){
                dashboard.collapseSidebar(sidePanel, profileLabel, dashboardLabel, patientLabel, consultLabel, appointmentLabel, mainPanel, original);
            }
        });

        DIContainer.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent e){
                DIContainer.setBackground(Color.decode("#98F3F5"));
                DIContainer.setBorder(BorderFactory.createLineBorder(Color.decode("#56C7D1"), 2));

                dashboard.expandSidebar(sidePanel, profileLabel, dashboardLabel, patientLabel, consultLabel, appointmentLabel, mainPanel, original);
            }
            public void mouseExited(java.awt.event.MouseEvent e){
                DIContainer.setBackground(Color.decode("#56C7D1"));
                DIContainer.setBorder(null);

                dashboard.collapseSidebar(sidePanel, profileLabel, dashboardLabel, patientLabel, consultLabel, appointmentLabel, mainPanel, original);
            }
        });

        PIContainer.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent e){
                PIContainer.setOpaque(true);
                PIContainer.setBackground(Color.decode("#98F3F5"));
                PIContainer.setBorder(BorderFactory.createLineBorder(Color.decode("#56C7D1"), 2));

                dashboard.expandSidebar(sidePanel, profileLabel, dashboardLabel, patientLabel, consultLabel, appointmentLabel, mainPanel, original);
            }
            public void mouseExited(java.awt.event.MouseEvent e){
                PIContainer.setOpaque(false);
                PIContainer.setBorder(null);

                dashboard.collapseSidebar(sidePanel, profileLabel, dashboardLabel, patientLabel, consultLabel, appointmentLabel, mainPanel, original);
            }

            public void mousePressed(java.awt.event.MouseEvent e){
                new ConsultationOverlay();
            }
        });

        CIContainer.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent e){
                CIContainer.setOpaque(true);
                CIContainer.setBackground(Color.decode("#98F3F5"));
                CIContainer.setBorder(BorderFactory.createLineBorder(Color.decode("#56C7D1"), 2));

                dashboard.expandSidebar(sidePanel, profileLabel, dashboardLabel, patientLabel, consultLabel, appointmentLabel, mainPanel, original);
            }
            public void mouseExited(java.awt.event.MouseEvent e){
                CIContainer.setOpaque(false);
                CIContainer.setBorder(null);

                dashboard.collapseSidebar(sidePanel, profileLabel, dashboardLabel, patientLabel, consultLabel, appointmentLabel, mainPanel, original);
            }
        });

        AIContainer.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent e){
                AIContainer.setOpaque(true);
                AIContainer.setBackground(Color.decode("#98F3F5"));
                AIContainer.setBorder(BorderFactory.createLineBorder(Color.decode("#56C7D1"), 2));

                dashboard.expandSidebar(sidePanel, profileLabel, dashboardLabel, patientLabel, consultLabel, appointmentLabel, mainPanel, original);
            }
            public void mouseExited(java.awt.event.MouseEvent e){
                AIContainer.setOpaque(false);
                AIContainer.setBorder(null);

                dashboard.collapseSidebar(sidePanel, profileLabel, dashboardLabel, patientLabel, consultLabel, appointmentLabel, mainPanel, original);
            }
        });

        layeredPane.add(mainPanel, Integer.valueOf(0));
        //sidepanel
        layeredPane.add(sidePanel, Integer.valueOf(1));
        sidePanel.add(profileLabel, gbc(0, 0, GridBagConstraints.NORTH, 1, 0.05, GridBagConstraints.NONE));
        sideOptions.add(DIContainer);
        sideOptions.add(PIContainer);
        sideOptions.add(CIContainer);
        sideOptions.add(AIContainer);
        sidePanel.add(sideOptions, gbc(0, 1, GridBagConstraints.NORTHWEST, 1, 1, GridBagConstraints.HORIZONTAL));
        mainPanel.add(parentPanel);

        this.setLayout(new BorderLayout());
        this.add(layeredPane, BorderLayout.CENTER);
    }

    public static GridBagConstraints gbc(int x, int y, int anchor, double weightx, double weighty, int fill){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = anchor;
        gbc.fill = fill;
        gbc.weightx = weightx;
        gbc.weighty = weighty;

        return gbc;
    }

    public static BufferedImage resizeImage(BufferedImage original, int width, int height){
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = resized.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        return resized;
    }

    public static JPanel sidePanelIconContainers(JLabel label){
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.WEST);

        return panel;
    }

    public static BufferedImage roundImage(BufferedImage original, int diameter){
        BufferedImage rounded = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = rounded.createGraphics();
        g2.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, diameter, diameter));
        g2.drawImage(original, 0, 0, diameter, diameter, null);
        g2.dispose();

        return rounded;
    }
}