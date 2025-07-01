package fr.minuskube.inv.opener;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public interface InventoryOpener {

    Inventory open(SmartInventory inv, Player player);
    boolean supports(InventoryType type);

    default void fill(Inventory handle, InventoryContents contents) {
        ClickableItem[][] items = contents.all();

        int width;
        switch (handle.getType()) {
            case HOPPER:
                width = 5;
                break;
            case DROPPER:
            case DISPENSER:
            case CRAFTING:
            case WORKBENCH:
            case MERCHANT:
                width = 3;
                break;
            case ANVIL:
            case FURNACE:
                width = 2;
                break;
            case BEACON:
            case ENCHANTING:
                width = 1;
                break;
            default:
                width = 9;
                break;
        }

        for(int row = 0; row < items.length; row++) {
            for(int column = 0; column < items[row].length; column++) {
                if(items[row][column] != null)
                    handle.setItem(width * row + column, items[row][column].getItem());
            }
        }
    }

}
