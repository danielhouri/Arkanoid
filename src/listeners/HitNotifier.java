// 314712563
package listeners;

/**
 * The HitNotifier interface indicate that objects that implement it send notifications when they are being hit.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     * @param hl the listener to hit events
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl the list of listeners
     */
    void removeHitListener(HitListener hl);
}
