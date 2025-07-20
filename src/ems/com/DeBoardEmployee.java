package ems.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeBoardEmployee {
    DeBoardEmployee() {
        JFrame f = new JFrame("Delete Employee");
        f.setSize(400, 200);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(58, 123, 213),
                        getWidth(), getHeight(), new Color(0, 210, 255)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        bgPanel.setLayout(null);

        JLabel title = new JLabel("Delete Employee", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setBounds(100, 10, 200, 30);
        bgPanel.add(title);

        JLabel l = createStyledLabel("Employee ID:");
        JTextField tf = new JTextField();
        JButton b1 = createStyledButton("Delete", new Color(244, 67, 54), new Color(211, 47, 47));
        JButton b2 = createStyledButton("Back", new Color(76, 175, 80), new Color(56, 142, 60));

        l.setBounds(50, 60, 100, 30);
        tf.setBounds(160, 60, 100, 30);
        b1.setBounds(50, 110, 100, 30);
        b2.setBounds(170, 110, 100, 30);

        bgPanel.add(l);
        bgPanel.add(tf);
        bgPanel.add(b1);
        bgPanel.add(b2);

        f.setContentPane(bgPanel);
        f.setVisible(true);

        b1.addActionListener(e -> {
            try (Connection con = DBConnection.getConnection()) {
                String sql = "DELETE FROM employees WHERE id=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(tf.getText()));
                int i = ps.executeUpdate();

                if (i > 0) {
                    DBConnection.renumberEmployeeIds();
                    JOptionPane.showMessageDialog(f, "DEBOARDING SUCCESSFUL ✅");
                } else {
                    JOptionPane.showMessageDialog(f, "Employee not found!");
                }

                f.dispose();
                new Home();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(f, "Error: " + ex.getMessage());
            }
        });

        b2.addActionListener(e -> {
            f.dispose();
            new Home();
        });
    }

    private JLabel createStyledLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    private JButton createStyledButton(String text, Color normal, Color hover) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setBackground(normal);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
