package no.ntnu.idatt1002.view.utils;

import javafx.collections.ObservableList;
import javafx.scene.control.DialogPane;

/**
 * Utils class that can be used with {@code implements} to add shorthands for easier css handling.
 *
 * @author Leif Mørstad
 * @version 1.1
 */
public interface DialogCssUtils extends CssUtils {

  @Override
  default ObservableList<String> getStylesheets() {
    return getDialogPane().getStylesheets();
  }

  @Override
  default ObservableList<String> getStyleClass() {
    return getDialogPane().getStyleClass();
  }

  DialogPane getDialogPane();
}
