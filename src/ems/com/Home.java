package ems.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Home::new);
    }

    Home() {
        JFrame f = new JFrame("Employee Management System");
        f.setSize(700, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);

        // ✅ Custom Background Panel (Gradient)
        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Gradient background
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(58, 123, 213),
                        getWidth(), getHeight(), new Color(0, 210, 255)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        bgPanel.setLayout(null);

        // Title
        JLabel title = new JLabel("EMS - Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setBounds(180, 20, 350, 60);
        bgPanel.add(title);

        // Buttons
        JButton b1 = createStyledButton("View All Employees", new Color(76, 175, 80), new Color(56, 142, 60));
        JButton b2 = createStyledButton("View Employee", new Color(255, 193, 7), new Color(255, 160, 0));
        JButton b3 = createStyledButton("Add Employee", new Color(33, 150, 243), new Color(30, 136, 229));
        JButton b4 = createStyledButton("Delete Employee", new Color(244, 67, 54), new Color(211, 47, 47));
        JButton b5 = createStyledButton("Update Employee", new Color(156, 39, 176), new Color(123, 31, 162));

        int x1 = 120, x2 = 380, y = 120;
        b1.setBounds(x1, y, 180, 40);
        b2.setBounds(x2, y, 180, 40);
        b3.setBounds(x1, y + 70, 180, 40);
        b4.setBounds(x2, y + 70, 180, 40);
        b5.setBounds(250, y + 150, 180, 40);

        bgPanel.add(b1);
        bgPanel.add(b2);
        bgPanel.add(b3);
        bgPanel.add(b4);
        bgPanel.add(b5);

        f.setContentPane(bgPanel);
        f.setVisible(true);

        // Button actions
        b1.addActionListener(e -> new DisplayAll());
        b2.addActionListener(e -> new SingleEmployee());
        b3.addActionListener(e -> new OnboardEmployee());
        b4.addActionListener(e -> new DeBoardEmployee());
        b5.addActionListener(e -> new UpdateEmployee());
    }

    private JButton createStyledButton(String text, Color normal, Color hover) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setBackground(normal);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        button.setOpaque(true);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(normal);
            }
        });

        button.setBorder(new RoundedBorder(20));
        return button;
    }

    // Rounded border class
    static class RoundedBorder implements javax.swing.border.Border {
        private final int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 1, radius + 1, radius + 1, radius + 1);
        }

        public boolean isBorderOpaque() {
            return false;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.WHITE);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
