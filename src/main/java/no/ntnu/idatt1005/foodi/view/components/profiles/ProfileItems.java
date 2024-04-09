package no.ntnu.idatt1002.view.components.profiles;

import java.util.List;
import javafx.scene.layout.HBox;
import no.ntnu.idatt1002.view.utils.ColorUtils;
import no.ntnu.idatt1002.view.utils.CssUtils;

/**
 * Class for creating a horizontal list of {@link ProfileItem}. Extends {@link HBox} and implements
 * {@link CssUtils}.
 *
 * @author Henrik Kvamme
 * @version 1.0
 */
public class ProfileItems extends HBox implements CssUtils {
  // TODO: When integrating with the backend, the constructor should take a list of profile items
  //    public ProfileItems(ArrayList<Profile> profileItems) {
  //        super();
  ////        Assign color from Profile object
  //    }

  /**
   * Constructor for the ProfileItems class.
   *
   * @param profileNames List of profile names
   */
  public ProfileItems(List<String> profileNames) {
    super();
    addStylesheet("components/profiles/profile-items");
    addClass("profile-items");

    for (String name : profileNames) {
      // Init ProfileItem
      final Runnable onClick = () -> System.out.println("Clicked on " + name + "!");
      ProfileItem profileItem = new ProfileItem(name, ColorUtils.getRandomColor(), onClick);
      getChildren().add(profileItem);
    }

    final Runnable onClick = () -> System.out.println("Add profile");
    getChildren().add(new AddProfileButton(onClick));
  }
}