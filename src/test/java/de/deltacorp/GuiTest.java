package de.deltacorp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class GuiTest {

    @Test
    public void main() {
        assert(true);
    }

    @Before
    public void setUp() throws Exception {
        Gui gui = new Gui();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testMain() {
        Gui.main(new String[]{});
    }
}