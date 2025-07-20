package ems.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class OnboardEmployee {
    JFrame f;
    JTextField tf1, tf2, tf3, tf4;

    public OnboardEmployee() {
        f = new JFrame("Employee Onboarding");
        f.setSize(500, 400);
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

        JLabel title = new JLabel("Onboard New Employee", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBounds(100, 20, 300, 40);
        bgPanel.add(title);

        JLabel l1 = createStyledLabel("Name:");
        JLabel l2 = createStyledLabel("Salary:");
        JLabel l3 = createStyledLabel("Department:");
        JLabel l4 = createStyledLabel("Position:");

        tf1 = new JTextField(); tf2 = new JTextField(); tf3 = new JTextField(); tf4 = new JTextField();

        l1.setBounds(100, 80, 100, 30); tf1.setBounds(210, 80, 180, 30);
        l2.setBounds(100, 120, 100, 30); tf2.setBounds(210, 120, 180, 30);
        l3.setBounds(100, 160, 100, 30); tf3.setBounds(210, 160, 180, 30);
        l4.setBounds(100, 200, 100, 30); tf4.setBounds(210, 200, 180, 30);

        JButton b1 = createStyledButton("Onboard", new Color(76, 175, 80), new Color(56, 142, 60));
        JButton b2 = createStyledButton("Back", new Color(244, 67, 54), new Color(211, 47, 47));

        b1.setBounds(130, 270, 100, 35);
        b2.setBounds(260, 270, 100, 35);

        bgPanel.add(l1); bgPanel.add(tf1);
        bgPanel.add(l2); bgPanel.add(tf2);
        bgPanel.add(l3); bgPanel.add(tf3);
        bgPanel.add(l4); bgPanel.add(tf4);
        bgPanel.add(b1); bgPanel.add(b2);

        f.setContentPane(bgPanel);
        f.setVisible(true);

        b1.addActionListener(e -> onboardEmployee());
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
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
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

    public void onboardEmployee() {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO employees(name, salary, department, position) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tf1.getText().trim());
            ps.setInt(2, Integer.parseInt(tf2.getText().trim()));
            ps.setString(3, tf3.getText().trim());
            ps.setString(4, tf4.getText().trim());
            ps.executeUpdate();

            // Optional: re-number IDs to avoid gaps
            DBConnection.renumberEmployeeIds();

            JOptionPane.showMessageDialog(f, "✅ ONBOARDING SUCCESSFUL");
            f.dispose();
            new Home();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(f, "⚠ Salary must be a number.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(f, "❌ Error: " + ex.getMessage());
        }
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
