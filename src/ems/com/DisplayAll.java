package ems.com;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class DisplayAll {
    DisplayAll() {
        JFrame f = new JFrame("All Employees");
        f.setSize(700, 500);
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

        JLabel title = new JLabel("LIST OF ALL EMPLOYEES", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setBounds(200, 10, 300, 30);
        bgPanel.add(title);

        String[] columns = {"ID", "Name", "Salary", "Department", "Position"};
        Vector<Vector<Object>> data = new Vector<>();

        try (Connection con = DBConnection.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees");

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt(1));
                row.add(rs.getString(2));
                row.add(rs.getInt(3));
                row.add(rs.getString(4));
                row.add(rs.getString(5));
                data.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(f, "Error fetching data.");
        }

        DefaultTableModel model = new DefaultTableModel(data, new Vector<>(java.util.List.of(columns)));
        JTable table = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    if (row % 2 == 0) {
                        comp.setBackground(new Color(173, 216, 230)); // light blue
                    } else {
                        comp.setBackground(new Color(255, 182, 193)); // light pink
                    }
                } else {
                    comp.setBackground(new Color(255, 255, 153)); // selected yellow
                }
                return comp;
            }
        };
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setForeground(Color.BLACK);

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(0, 128, 0)); // green
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 60, 630, 350);

        JButton back = createStyledButton("Back", new Color(244, 67, 54), new Color(211, 47, 47));
        back.setBounds(300, 420, 100, 35);

        bgPanel.add(scrollPane);
        bgPanel.add(back);

        f.setContentPane(bgPanel);
        f.setVisible(true);

        back.addActionListener(e -> {
            f.dispose();
            new Home();
        });
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
