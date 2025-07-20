package ems.com;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class SingleEmployee {
    SingleEmployee() {
        JFrame f = new JFrame("Employee Details");
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

        JLabel title = new JLabel("EMPLOYEE DETAILS", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setBounds(150, 10, 200, 30);
        bgPanel.add(title);

        JLabel lid = createStyledLabel("Employee ID:");
        JTextField tid = new JTextField();

        lid.setBounds(50, 50, 100, 30);
        tid.setBounds(160, 50, 100, 30);

        JButton submit = createStyledButton("Submit", new Color(76, 175, 80), new Color(56, 142, 60));
        submit.setBounds(280, 50, 90, 30);

        JButton back = createStyledButton("Back", new Color(244, 67, 54), new Color(211, 47, 47));
        back.setBounds(200, 320, 100, 35);

        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 100, 380, 180);

        bgPanel.add(lid);
        bgPanel.add(tid);
        bgPanel.add(submit);
        bgPanel.add(scrollPane);
        bgPanel.add(back);

        f.setContentPane(bgPanel);
        f.setVisible(true);

        submit.addActionListener(e -> {
            try (Connection con = DBConnection.getConnection()) {
                String sql = "SELECT * FROM employees WHERE id = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(tid.getText()));
                ResultSet rs = ps.executeQuery();

                Vector<String> columnNames = new Vector<>();
                columnNames.add("ID");
                columnNames.add("Name");
                columnNames.add("Salary");
                columnNames.add("Department");
                columnNames.add("Position");

                Vector<Vector<Object>> data = new Vector<>();

                while (rs.next()) {
                    Vector<Object> row = new Vector<>();
                    row.add(rs.getInt(1));
                    row.add(rs.getString(2));
                    row.add(rs.getInt(3));
                    row.add(rs.getString(4));
                    row.add(rs.getString(5));
                    data.add(row);
                }

                if (data.isEmpty()) {
                    JOptionPane.showMessageDialog(f, "Employee not found!");
                    return;
                }

                DefaultTableModel model = new DefaultTableModel(data, columnNames);
                table.setModel(model);

                JTableHeader header = table.getTableHeader();
                header.setBackground(new Color(0, 128, 0));
                header.setForeground(Color.WHITE);
                header.setFont(new Font("Segoe UI", Font.BOLD, 13));

                table.setRowHeight(30);
                table.setFont(new Font("Segoe UI", Font.PLAIN, 13));

                table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable tbl, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                        Component c = super.getTableCellRendererComponent(tbl, value, isSelected, hasFocus, row, col);
                        int id = (int) tbl.getValueAt(row, 0);
                        if (id % 2 == 0) {
                            c.setBackground(new Color(173, 216, 230)); // light blue for even ID
                        } else {
                            c.setBackground(new Color(255, 182, 193)); // light red for odd ID
                        }
                        c.setForeground(Color.BLACK);
                        return c;
                    }
                });

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(f, "Error fetching data.");
            }
        });

        back.addActionListener(e -> {
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
