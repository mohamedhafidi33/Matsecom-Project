package de.deltacorp;

import org.junit.Test;

import java.awt.event.WindowEvent;

import static org.junit.Assert.*;


/**
 * Verify that the GUI works at all.
 */
public class GuiTest {

    @Test
    public void givenRuntime_onStart_callMainWithoutCrash() {
        Gui.main(new String[]{});
        TestUtil.sleep(200L);

        assertTrue(Gui.frame.isActive());
    }

    @Test
    public void givenJFrameOpen_onClose_closeWithoutCrash() {
        Gui.main(new String[]{});
        TestUtil.sleep(200L);
        Gui.frame.dispatchEvent(new WindowEvent(Gui.frame, WindowEvent.WINDOW_CLOSING));
        TestUtil.sleep(200L);

        assertFalse(Gui.frame.isActive());
    }

}