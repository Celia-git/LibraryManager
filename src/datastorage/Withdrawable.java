/*
 * Interface Withdrawable implemented by both dataStorage object types
 */
package datastorage;

/**
 *
 * @author Celia
 */
public interface Withdrawable {

    /**
     *
     * @return boolean true if implemented object can withdraw or can be
     * withdrawn
     */
    boolean canWithdraw();

}