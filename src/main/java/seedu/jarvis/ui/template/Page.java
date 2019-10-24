package seedu.jarvis.ui.template;

import javafx.scene.Node;

import seedu.jarvis.logic.Logic;
import seedu.jarvis.model.Model;
import seedu.jarvis.ui.MainWindow;
import seedu.jarvis.ui.UiPart;

/**
 * Base class of all pages.
 * It is the responsibility of implementing classes to ensure components
 * specific to them are initialised and filled with data properly.
 *
 * @param <T> JavaFX base node type from which this page is constructed.
 */
public abstract class Page<T extends Node> extends UiPart<T> {

    protected MainWindow mainWindow;
    protected Logic logic;
    protected Model model;

    protected Page(String fxml, MainWindow mainWindow, Logic logic, Model model) {
        super(fxml);
        this.mainWindow = mainWindow;
        this.logic = logic;
        this.model = model;
    }

    /**
     * The callback function {@code CommandUpdater} to run after executing a command successfully.
     */
    public abstract void fillPage();
}
