package store;

import store.mediator.StoreMediator;

public class Application {
    public static void main(String[] args) {
        final StoreMediator storeMediator = new StoreMediator();
        storeMediator.run();
    }
}
