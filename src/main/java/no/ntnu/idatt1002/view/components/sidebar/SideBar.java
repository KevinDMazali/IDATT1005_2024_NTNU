package no.ntnu.idatt1002.view.components.sidebar;

import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import no.ntnu.idatt1002.view.utils.CssUtils;
import no.ntnu.idatt1002.view.utils.LoadUtils;

/**
 * A simple sidebar component.
 *
 * @author Leif Mørstad
 * @version 1.0
 */
public class SideBar extends VBox implements CssUtils {
  /**
   * The constructor of the sidebar component.
   */
  public SideBar() {
    super();
    addStylesheet("sidebar");
    addClass("sidebar");

    String image = LoadUtils.getImage("icon.png");
    if (image != null) {
      ImageView logo = new ImageView(image);
      logo.setFitWidth(138);
      logo.setFitHeight(50);
      getChildren().add(logo);
    }

    getChildren().addAll(
      new SideBarItem(
          "Inventory",
          "inventory",
          new SideBarSubItem("Add item", "inventory/add"),
          new SideBarSubItem("Add item", "inventory/add")
      ),
      new SideBarItem(
          "Cookbook",
          "cookbook",
          new SideBarSubItem("Add item", "cookbook/add")
      ),
      new SideBarItem(
          "Shopping List",
          "shopping-list",
          new SideBarSubItem("Add item", "shopping-list/add")
      ),
      new SideBarItem(
          "About",
          "about"
      )
    );
  }
}