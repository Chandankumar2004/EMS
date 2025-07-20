package ems.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateEmployee {
    JFrame f;

    public UpdateEmployee() {
        f = new JFrame("Update Employee");
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

        JLabel title = new JLabel("Update Employee", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBounds(150, 20, 200, 40);
        bgPanel.add(title);

        JLabel l = createStyledLabel("Employee ID:");
        JTextField tf = new JTextField();
        JButton fetchBtn = createStyledButton("Submit", new Color(33, 150, 243), new Color(30, 136, 229));

        l.setBounds(80, 80, 120, 30);
        tf.setBounds(200, 80, 100, 30);
        fetchBtn.setBounds(320, 80, 100, 30);

        bgPanel.add(l);
        bgPanel.add(tf);
        bgPanel.add(fetchBtn);

        fetchBtn.addActionListener(e -> {
            int id = Integer.parseInt(tf.getText());
            showForm(bgPanel, id);
        });

        f.setContentPane(bgPanel);
        f.setVisible(true);
    }

    void showForm(JPanel panel, int empId) {
        panel.removeAll();

        JLabel title = new JLabel("Edit Employee Details", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setBounds(150, 20, 250, 30);
        panel.add(title);

        JLabel l1 = createStyledLabel("Name:");
        JLabel l2 = createStyledLabel("Salary:");
        JLabel l3 = createStyledLabel("Department:");
        JLabel l4 = createStyledLabel("Position:");

        JTextField tf1 = new JTextField();
        JTextField tf2 = new JTextField();
        JTextField tf3 = new JTextField();
        JTextField tf4 = new JTextField();

        JButton updateBtn = createStyledButton("Update", new Color(76, 175, 80), new Color(56, 142, 60));
        JButton backBtn = createStyledButton("Back", new Color(244, 67, 54), new Color(211, 47, 47));

        l1.setBounds(100, 70, 100, 30); tf1.setBounds(210, 70, 180, 30);
        l2.setBounds(100, 110, 100, 30); tf2.setBounds(210, 110, 180, 30);
        l3.setBounds(100, 150, 100, 30); tf3.setBounds(210, 150, 180, 30);
        l4.setBounds(100, 190, 100, 30); tf4.setBounds(210, 190, 180, 30);

        updateBtn.setBounds(150, 250, 90, 35);
        backBtn.setBounds(260, 250, 90, 35);

        panel.add(l1); panel.add(tf1);
        panel.add(l2); panel.add(tf2);
        panel.add(l3); panel.add(tf3);
        panel.add(l4); panel.add(tf4);
        panel.add(updateBtn); panel.add(backBtn);

        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM employees WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tf1.setText(rs.getString("name"));
                tf2.setText(String.valueOf(rs.getInt("salary")));
                tf3.setText(rs.getString("department"));
                tf4.setText(rs.getString("position"));
            } else {
                JOptionPane.showMessageDialog(f, "Employee not found!");
                f.dispose();
                new Home();
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        updateBtn.addActionListener(e -> {
            try (Connection con = DBConnection.getConnection()) {
                String sql = "UPDATE employees SET name=?, salary=?, department=?, position=? WHERE id=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, tf1.getText());
                ps.setInt(2, Integer.parseInt(tf2.getText()));
                ps.setString(3, tf3.getText());
                ps.setString(4, tf4.getText());
                ps.setInt(5, empId);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(f, "Update SUCCESSFUL ✅");
                f.dispose();
                new Home();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        backBtn.addActionListener(e -> {
            f.dispose();
            new Home();
        });

        panel.revalidate();
        panel.repaint();
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
