package seedu.jarvis.logic.commands.finance;

import static seedu.jarvis.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.jarvis.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jarvis.testutil.TypicalIndexes.INDEX_FIRST_PURCHASE;
import static seedu.jarvis.testutil.address.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.jarvis.commons.core.Messages;
import seedu.jarvis.commons.core.index.Index;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.ModelManager;
import seedu.jarvis.model.cca.CcaTracker;
import seedu.jarvis.model.course.CoursePlanner;
import seedu.jarvis.model.financetracker.FinanceTracker;
import seedu.jarvis.model.financetracker.purchase.Purchase;
import seedu.jarvis.model.financetracker.purchase.PurchaseDescription;
import seedu.jarvis.model.financetracker.purchase.PurchaseMoneySpent;
import seedu.jarvis.model.history.HistoryManager;
import seedu.jarvis.model.planner.Planner;
import seedu.jarvis.model.userprefs.UserPrefs;

public class RemovePaidCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new CcaTracker(), new HistoryManager(), new FinanceTracker(), getTypicalAddressBook(),
                new UserPrefs(), new Planner(), new CoursePlanner());
        model.addPurchase(new PurchaseStub());
        model.addPurchase(new PurchaseStub());
        model.addPurchase(new PurchaseStub());
    }

    /**
     * Verifies that checking {@code RemovePaidCommand} for the availability of inverse execution returns true.
     */
    @Test
    public void hasInverseExecution() {
        RemovePaidCommand removePaidCommand = new RemovePaidCommand(INDEX_FIRST_PURCHASE);
        Assertions.assertTrue(removePaidCommand.hasInverseExecution());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() throws CommandException {
        Purchase purchaseToDelete = model.getPurchase(INDEX_FIRST_PURCHASE.getOneBased());
        RemovePaidCommand removePaidCommand = new RemovePaidCommand(INDEX_FIRST_PURCHASE);

        String expectedMessage = String.format(RemovePaidCommand.MESSAGE_DELETE_PURCHASE_SUCCESS,
                purchaseToDelete);

        ModelManager expectedModel = new ModelManager(model.getCcaTracker(), model.getHistoryManager(),
                model.getFinanceTracker(), model.getAddressBook(), new UserPrefs(),
                model.getPlanner(), model.getCoursePlanner());
        expectedModel.deletePurchase(INDEX_FIRST_PURCHASE.getOneBased());

        assertCommandSuccess(removePaidCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPurchaseList().size() + 1);
        RemovePaidCommand removePaidCommand = new RemovePaidCommand(outOfBoundIndex);

        assertCommandFailure(removePaidCommand, model, Messages.MESSAGE_INVALID_PURCHASE_DISPLAYED_INDEX);
    }

    private static class PurchaseStub extends Purchase {
        public PurchaseStub() {
            super(new PurchaseDescription("lunch at Saizerya"), new PurchaseMoneySpent("5.00"));
        }
    }
}
