/*
 * The MIT License
 *
 * Copyright (c) 2009-2021 PrimeTek
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.primefaces.showcase.menu;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Named
@ApplicationScoped
public class AppMenu {

    private List<MenuCategory> menuCategories;
    private List<MenuItem> menuItems;

    // CHECKSTYLE:OFF
    @PostConstruct
    public void init() {
        menuCategories = new ArrayList<>();
        menuItems = new ArrayList<>();

        //BUTTON CATEGORY START
        List<MenuItem> clientMenuItems = new ArrayList<>();
        clientMenuItems.add(new MenuItem("Store", "/ui/client/store"));
        clientMenuItems.add(new MenuItem("My Shopping cart", "/ui/client/mycart"));
        clientMenuItems.add(new MenuItem("deposit money", "/ui/client/deposit"));
        clientMenuItems.add(new MenuItem("My Orders", "/ui/client/myorders"));
        menuCategories.add(new MenuCategory("Client Web App", clientMenuItems));


        for (MenuCategory category : menuCategories) {
            for (MenuItem menuItem : category.getMenuItems()) {
                menuItem.setParentLabel(category.getLabel());
                if (menuItem.getUrl() != null) {
                    menuItems.add(menuItem);
                }
                if (menuItem.getMenuItems() != null) {
                    for (MenuItem item : menuItem.getMenuItems()) {
                        item.setParentLabel(menuItem.getLabel());
                        if (item.getUrl() != null) {
                            menuItems.add(item);
                        }
                    }
                }
            }
        }
    }

    public List<MenuItem> completeMenuItem(String query) {
        String queryLowerCase = query.toLowerCase();
        List<MenuItem> filteredItems = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.getUrl() != null && (item.getLabel().toLowerCase().contains(queryLowerCase)
                    || item.getParentLabel().toLowerCase().contains(queryLowerCase))) {
                filteredItems.add(item);
            }
            else if (item.getBadge() != null) {
                if (item.getBadge().toLowerCase().contains(queryLowerCase)) {
                    filteredItems.add(item);
                }
            }
        }
        filteredItems.sort(Comparator.comparing(MenuItem::getParentLabel));
        return filteredItems;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public List<MenuCategory> getMenuCategories() {
        return menuCategories;
    }
}
