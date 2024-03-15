package com.ordermanagement.assignment2ordermanagement.model;
import java.util.ArrayList;
import java.util.List;
public class ShoppingCart {
        private List<Product> items;

        public ShoppingCart() {
            this.items = new ArrayList<>();
        }

        public List<Product> getItems() {
            return items;
        }

        public void addItem(Product product) {
            items.add(product);
        }

        public void removeItem(Product product) {
            items.remove(product);
        }

        public void clearCart() {
            items.clear();
        }
    }
