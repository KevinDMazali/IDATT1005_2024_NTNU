package no.ntnu.idatt1005.foodi.view.location;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.layout.BorderPane;
import no.ntnu.idatt1005.foodi.view.Page;
import org.jetbrains.annotations.NotNull;

/**
 * A router that displays different nodes depending on the current location.
 *
 * @author Leif Mørstad
 * @version 1.0
 */
public class Router extends BorderPane {

  /**
   * A map of routes and their corresponding nodes.
   */
  HashMap<String, Page> routes = new HashMap<>();

  /**
   * Creates an empty router.
   */
  public Router() {
    super();
    LocationHandler.subscribe(this::updateLocation);
  }

  /**
   * Changes the shown node depending on the given location.
   *
   * @param location The new location
   */
  private void updateLocation(@NotNull String location) {
    if (routes.containsKey(location)) {
      setPage(routes.get(location));
      return;
    }

    for (Map.Entry<String, Page> entry : routes.entrySet()) {
      String route = entry.getKey();
      Page page = entry.getValue();

      if (LocationHandler.isLocationFuzzy(route)) {
        setPage(page);
        return;
      }
    }

    setCenter(null);
  }

  /**
   * Sets the center of the router to the given page and refreshes it.
   *
   * @param page The page to show
   */
  private void setPage(@NotNull Page page) {
    setCenter(page.getNode());
    page.update();
  }

  /**
   * Adds a route to the router, which is shown when the given route is active.
   *
   * @param path The path of the route
   * @param page The page to show
   * @throws IllegalArgumentException If the route already exists
   */
  public void addRoute(@NotNull String path, @NotNull Page page) throws IllegalArgumentException {
    if (routes.containsKey(path)) {
      throw new IllegalArgumentException("Route already exists");
    }

    routes.put(path, page);
    if (LocationHandler.isLocationFuzzy(path)) {
      setPage(page);
    }
  }
}
