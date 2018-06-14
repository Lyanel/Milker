package modele.carac;

import java.util.EventListener;

public interface QuantityListener extends EventListener {
    void quantityChanged(double oldQuantity, double newQuantity);
}
