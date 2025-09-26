import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PharmacyManagementGUI extends JFrame implements ActionListener {
    private Pharmacy pharmacy;
    private double totalSales;
    private int capacity;

    public PharmacyManagementGUI(int capacity) {
        this.capacity = capacity;
        pharmacy = new Pharmacy(capacity);
        totalSales = 0.0;

        setTitle("Pharmacy Management System");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Pharmacy Management System");
        titleLabel.setBounds(150, 20, 200, 30);
        panel.add(titleLabel);

        JButton addDrugButton = new JButton("Add Drug");
        addDrugButton.setBounds(50, 50, 150, 30);
        addDrugButton.addActionListener(this);
        panel.add(addDrugButton);

        JButton removeDrugButton = new JButton("Remove Drug");
        removeDrugButton.setBounds(250, 50, 150, 30);
        removeDrugButton.addActionListener(this);
        panel.add(removeDrugButton);

        JButton placeOrderButton = new JButton("Place Order");
        placeOrderButton.setBounds(50, 100, 150, 30);
        placeOrderButton.addActionListener(this);
        panel.add(placeOrderButton);

        JButton displayDrugsButton = new JButton("Display Drugs");
        displayDrugsButton.setBounds(250, 100, 150, 30);
        displayDrugsButton.addActionListener(this);
        panel.add(displayDrugsButton);

        JButton totalSalesButton = new JButton("Get Total Sales");
        totalSalesButton.setBounds(50, 150, 150, 30);
        totalSalesButton.addActionListener(this);
        panel.add(totalSalesButton);

        JButton exitButton = new JButton("Exit the program");
        exitButton.setBounds(250, 150, 150, 30);
        exitButton.addActionListener(this);
        panel.add(exitButton);
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        if (action.equals("Add Drug")) {
            try {
                String name = JOptionPane.showInputDialog("Enter drug name:");
                if (name == null || name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Drug name cannot be empty.");
                    return;
                }

                int id;
                try {
                    id = Integer.parseInt(JOptionPane.showInputDialog("Enter drug ID:"));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Drug ID must be a valid number.");
                    return;
                }

                double price;
                try {
                    price = Double.parseDouble(JOptionPane.showInputDialog("Enter drug price:"));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Drug price must be a valid number.");
                    return;
                }

                String[] categories = { "Cosmetics", "Prescription Drugs", "Other" };
                String category = (String) JOptionPane.showInputDialog(null, "Choose drug category:",
                        "Select Drug Category", JOptionPane.PLAIN_MESSAGE, null, categories, categories[0]);
                if (category == null) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please select a drug category.");
                    return;
                }

                int quantity;
                try {
                    quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter drug quantity:"));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Drug quantity must be a valid number.");
                    return;
                }

                pharmacy.addDrug(name, id, price, category, quantity);
                JOptionPane.showMessageDialog(null, "Drug added successfully.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Failed to add drug: " + ex.getMessage());
            }

        } else if (action.equals("Remove Drug")) {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Enter drug ID to remove:"));
                pharmacy.removeDrug(id);
                JOptionPane.showMessageDialog(null, "Drug removed successfully.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
            } catch (DrugNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Failed to remove drug: " + ex.getMessage());
            }
        } else if (action.equals("Place Order")) {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Enter drug ID to order:"));
                int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter drug quantity:"));
                String result = pharmacy.placeOrder(id, quantity);
                JOptionPane.showMessageDialog(null, result);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
            } catch (DrugNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Failed to place order: " + ex.getMessage());
            }
        } else if (action.equals("Display Drugs")) {
            String drugsInfo = pharmacy.displayDrugs();
            JOptionPane.showMessageDialog(null, drugsInfo);
        } else if (action.equals("Get Total Sales")) {
            double totalSales = pharmacy.getTotalSales();
            JOptionPane.showMessageDialog(null, "Total Sales: " + totalSales);
        } else if (action.equals("Exit the program")) {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Thank you for using our Pharmacy Management Program.");
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        int capacity = 0;
        boolean validCapacity = false;
        JOptionPane.showMessageDialog(null, "Welcome to our Pharmacy Management System.");
        do {
            try {
                capacity = Integer.parseInt(JOptionPane.showInputDialog("Enter pharmacy capacity:"));
                validCapacity = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
            }
        } while (!validCapacity);

        PharmacyManagementGUI gui = new PharmacyManagementGUI(capacity);
        gui.setVisible(true);
    }
}