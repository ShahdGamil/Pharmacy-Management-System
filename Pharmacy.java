import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class Pharmacy {
    private List<Drug> drugs;
    private int maxCapacity;
    private double totalSales;

    public Pharmacy(int maxCapacity) {
        this.drugs = new ArrayList<>();
        this.maxCapacity = maxCapacity;
        this.totalSales = 0.0;
    }

    public void addDrug(String name, int id, double price, String category, int availableQuantity) throws Exception {
        if (drugs.size() >= maxCapacity) {
            throw new Exception("Pharmacy has reached maximum capacity.");
        }

        Drug newDrug = new Drug(name, id, price, category, availableQuantity);
        drugs.add(newDrug);
    }

    public void removeDrug(int id) throws DrugNotFoundException {
        Drug drugToRemove = null;
        for (Drug drug : drugs) {
            if (drug.getId() == id) {
                drugToRemove = drug;
                break;
            }
        }

        if (drugToRemove != null) {
            drugs.remove(drugToRemove);
        } else {
            throw new DrugNotFoundException("Drug with ID " + id + " not found.");
        }
    }

    public String placeOrder(int id, int quantity) throws DrugNotFoundException {
        StringBuilder result = new StringBuilder();
        Drug drug = findDrugById(id);
        double unitPrice = drug.getPrice();
        double totalPrice = unitPrice * quantity;

        result.append("Drug ID: ").append(id).append("\n");
        result.append("Unit Price: ").append(unitPrice).append("\n");
        if (drug.getCategory().equalsIgnoreCase("Cosmetics")) {
            totalPrice = unitPrice * 1.2 * quantity;
            result.append("Price (Cosmetics): ").append(totalPrice).append("\n");
        } else {
            result.append("Price: ").append(totalPrice).append("\n");
        }

        int currentAvailableQuantity = drug.getAvailableQuantity();
        if (currentAvailableQuantity >= quantity) {
            drug.setAvailableQuantity(currentAvailableQuantity - quantity);
            totalSales += totalPrice;
            result.append("Order placed successfully.");
        } else {
            result.append("Insufficient quantity available for the order.");
        }

        return result.toString();
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void printDrugAvailability(int id) throws DrugNotFoundException {
        Drug drug = findDrugById(id);
        double unitPrice = drug.getPrice();
        double price = drug.getCategory().equalsIgnoreCase("Cosmetics") ? unitPrice * 1.2 : unitPrice;

        System.out.println("Drug ID: " + id);
        System.out.println("Price: " + price);
    }

    public String displayDrugs() {
        StringBuilder drugsInfo = new StringBuilder();
        try {
            if (drugs.isEmpty()) {
                throw new Exception("No drugs available in the pharmacy.");
            }

            drugsInfo.append("Drugs in the Pharmacy:\n");
            for (Drug drug : drugs) {
                drugsInfo.append(drug.getDrugInfo()).append("\n");
                drugsInfo.append("-------------------\n");
            }
        } catch (Exception e) {
            drugsInfo.append("Error: ").append(e.getMessage());
        }

        return drugsInfo.toString();
    }

    private Drug findDrugById(int id) throws DrugNotFoundException {
        for (Drug drug : drugs) {
            if (drug.getId() == id) {
                return drug;
            }
        }
        throw new DrugNotFoundException("Drug with ID " + id + " not found.");
    }
}
