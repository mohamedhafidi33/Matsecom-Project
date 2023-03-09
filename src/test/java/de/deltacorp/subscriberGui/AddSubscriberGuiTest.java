package de.deltacorp.subscriberGui;

import de.deltacorp.Gui;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Verify that the "Add Subscriber" function behaves correctly.
 */
public class AddSubscriberGuiTest {

    Gui gui;

    @Before
    public void setUp() throws Exception {
        gui = new Gui();
    }

    @After
    public void tearDown() throws Exception {
        gui = null;
    }

    @Test
    public void givenNewSubscriberEmptyFields_onAddSubscriber_callErrorPrompt() {

    }

    @Test
    public void givenNewSubscriberAllFieldsCorrect_onAddSubscriber_addSubscriber() {

    }

}